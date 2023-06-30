import Axios from "axios";
import { useEffect, useState, useRef } from "react";
import { getAllProducts, serverv1 } from "../utils/apis";
import * as ai from 'react-icons/ai';
import ErrorToast from "../Components/Toast/errorToast";
import { useNavigate } from "react-router-dom";

export default function Home() {

    const [data, setData] = useState([]);
    const [errorTost, setErrorToast] = useState(false);
    const [errorMsg, setErrorMsg] = useState("No Data Available");
    const [toastStatus, setToastStatus] = useState(false);
    const currentPage = useRef(0);
    const [totalElements, setTotalElements] = useState(0);
    const [totalPages, setTotalPages] = useState(0);


    const navigate = useNavigate();

    function navigatePage(data) {
        console.log(data);
        navigate("/update", {state:data});
    }

    useEffect(() => {
        getAllData();
    }, []);
    
    async function getAllData() {
        try {
            let result = await Axios.get(`${getAllProducts}/${currentPage.current}/10`);
            setData(result.data.data.content);
            setTotalElements(result.data.data.totalElements);
            setTotalPages(result.data.data.totalPages);
        }
        catch(error) {
            // console.log("ERRROR ",error);
            setErrorMsg(error.message);
            setToastStatus(false);
            setErrorToast(true);
        }
        toastOff();
    }


    function setPage(page) {
        if(page===NaN || page==="") {
            currentPage.current="";
            return;
        }
        //if(page.trim==="") return;
        if(page > totalPages-1) currentPage.current = 0;
        else if(page <= -1) currentPage.current = totalPages-1;
        else currentPage.current = page;

        getAllData();
    }

    async function deleteData(id) {
        let con = window.confirm(`Are you sure do you want to delete product with ID: ${id}`);
        console.log(con);
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

                        {data.map((item, key) => {
                            return (
                                <tr key={key} className="border-b border-blue-gray-50">
                                    <td className="p-3">{item.id}</td>
                                    <td className="p-3">{item.name}</td>
                                    <td className="p-3">{item.type}</td>
                                    <td className="p-3">{item.description}</td>
                                    <td className="p-3">{item.price}</td>
                                    <td className="p-3 flex justify-evenly"><ai.AiFillEdit onClick={() => navigatePage(item)} className="text-yellow-800 cursor-pointer" size={20}/><ai.AiFillDelete onClick={() => deleteData(item.id)} className="text-red-600 cursor-pointer" size={20}/> </td>
                                </tr>
                            );
                        })}

                    </tbody>
                </table>
                <div className="flex justify-between mt-3 p-2">
                    <p>Number of Record's: <b>{totalElements}</b></p>
                    <div className="flex">
                        <div className="flex">
                            <input type="number" className="w-fit outline-none text-right font-bold" onChange={(e) => setPage(e.target.value-1)} value={parseInt(currentPage.current)+1} /><p className="p-1">/ {totalPages}</p>
                        </div>

                        <button onClick={() => setPage(currentPage.current-1, "prev")} className="" ><ai.AiFillStepBackward size={30}/></button>
                        <button onClick={() => setPage(currentPage.current+1, "next")} className=""><ai.AiFillStepForward size={30}/></button>
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

