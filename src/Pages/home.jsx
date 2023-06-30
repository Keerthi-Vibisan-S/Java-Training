import Axios from "axios";
import { useEffect, useState } from "react";
import { getAllProducts, serverv1 } from "../utils/apis";
import * as ai from 'react-icons/ai';
import ErrorToast from "../Components/Toast/errorToast";
import { useNavigate } from "react-router-dom";

export default function Home() {

    const [data, setData] = useState([]);
    const [errorTost, setErrorToast] = useState(false);
    const [errorMsg, setErrorMsg] = useState("No Data Available");
    const [toastStatus, setToastStatus] = useState(false);

    const navigate = useNavigate();

    function navigatePage(data) {
        // console.log(data);
        navigate("/update", {state:data});
    }

    useEffect(() => {
        getAllData();
    }, []);
    
    async function getAllData() {
        try {
            let result = await Axios.get(getAllProducts);
            setData(result.data.data);
        }
        catch(error) {
            // console.log("ERRROR ",error);
            setErrorMsg(error.message);
            setToastStatus(false);
            setErrorToast(true);
        }
        toastOff();
    }

    // Paging
    const [currentPage, setCurrentPage] = useState(1);
    const recordsPerPage = 8;
    const lastIndex = currentPage * recordsPerPage;
    const firstIndex = lastIndex - recordsPerPage;
    const records = data.slice(firstIndex, lastIndex);
    const npage = Math.ceil(data.length/recordsPerPage);

    function setPage(page) {
        if(page > npage) setCurrentPage(1);
        else if(page <= 0) setCurrentPage(npage);
        else setCurrentPage(page);
    }

    async function deleteData(id) {
        let con = window.confirm(`Are you sure do you want to delete product with ID: ${id}`);
        //console.log(con);
        if(con) {
            try{
                let res = await Axios.delete(`${serverv1}/${id}`);
                if(res.data.data !== null) getAllData();
                else {
                    setErrorMsg(res.data.error);
                    setToastStatus(false);
                    setErrorToast(true);
                }
            } catch(error) {
                console.log(error.response);
            }
            toastOff();
        }
    }

    const toastOff = () => setTimeout(() => {
        setErrorToast(false);
    }, 5000);

    return (
        <section>
            {errorTost?<ErrorToast status={toastStatus} close={setErrorToast} error={errorMsg}/>:""}
            <div>

            </div>
            <div>
            {data.length !== 0?<>
                <table className="w-full min-w-max table-auto text-left">
                    <thead>
                        <tr className="bg-slate-200 border-b border-blue-gray-100 bg-blue-gray-50">
                            <th className="p-4">ID</th>
                            <th className="p-4">Name</th>
                            <th className="p-4">Type</th>
                            <th className="p-4">Descripton</th>
                            <th className="p-4">Price</th>
                            <th className="p-4">Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        {records.map((item, key) => {
                            return (
                                <tr key={key} className="border-b border-blue-gray-50">
                                    <td className="p-4">{item.id}</td>
                                    <td className="p-4">{item.name}</td>
                                    <td className="p-4">{item.type}</td>
                                    <td className="p-4">{item.description}</td>
                                    <td className="p-4">{item.price}</td>
                                    <td className="p-4 flex justify-evenly"><ai.AiFillEdit onClick={() => navigatePage(item)} className="text-yellow-800 cursor-pointer" size={20}/><ai.AiFillDelete onClick={() => deleteData(item.id)} className="text-red-600 cursor-pointer" size={20}/> </td>
                                </tr>
                            );
                        })}

                    </tbody>
                </table>
                <div className="flex justify-between mt-3">
                    <p>Number of Record's: <b>{data.length}</b></p>
                    <div className="flex">
                        <div className="flex">
                            <input type="number" className="w-fit outline-none text-right font-bold" onChange={(e) => setPage(e.target.value)} value={currentPage} /><p className="p-1">/ {npage}</p>
                        </div>

                        <button onClick={() => setPage(currentPage-1, "prev")} className="" ><ai.AiFillStepBackward size={30}/></button>
                        <button onClick={() => setPage(currentPage+1, "next")} className=""><ai.AiFillStepForward size={30}/></button>
                    </div>
                </div>
                </>
                :
                <div className="border w-[100%] p-5 text-center">
                    <p className="">{errorMsg}</p>
                </div>
                }
            </div>
        </section>
    );

}

