import Axios from "axios";
import { useEffect, useState } from "react";
import { getAllProducts } from "../utils/apis";
import * as ai from 'react-icons/ai';
import ErrorToast from "../Components/Toast/errorToast";

export default function Home() {

    const [data, setData] = useState([]);
    const [errorTost, setErrorToast] = useState(false);
    const [errorMsg, setErrorMsg] = useState("No Data Available");

    useEffect(() => {
        async function getAllData() {
            try {
                let result = await Axios.get(getAllProducts);
                setData(result.data.data);
            }
            catch(error) {
                console.log("ERRROR ",error);
                setErrorMsg(error.message);
                setErrorToast(true);
            }
    
            setTimeout(() => {
                setErrorToast(false);
            }, 6000);
        }
        getAllData();
    }, []);

    // Paging
    const [currentPage, setCurrentPage] = useState(1);
    const recordsPerPage = 8;
    const lastIndex = currentPage * recordsPerPage;
    const firstIndex = lastIndex - recordsPerPage;
    const records = data.slice(firstIndex, lastIndex);
    const npage = Math.ceil(data.length/recordsPerPage);

    function setPage(page, move) {
        if(page == 0) setCurrentPage(1);
        else if(page > npage) setCurrentPage(npage);
        else setCurrentPage(page);
    }

    return (
        <section>
            {errorTost?<ErrorToast close={setErrorToast} error={errorMsg}/>:""}
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
                                    <td className="p-4 flex justify-evenly"><ai.AiFillDelete className="text-red-600" size={20}/> <ai.AiFillEdit className="text-yellow-800" size={20}/></td>
                                </tr>
                            );
                        })}

                    </tbody>
                </table>
                <div className="flex justify-end mt-3">

                    <div className="flex">
                        <input type="number" className="w-fit outline-none text-right font-bold" onChange={(e) => setPage(e.target.value)} value={currentPage} /><p className="p-1">/ {npage}</p>
                    </div>

                    <button onClick={() => setPage(currentPage-1, "prev")} className="" ><ai.AiFillBackward size={32}/></button>
                    <button onClick={() => setPage(currentPage+1, "next")} className=""><ai.AiFillForward size={32}/></button>
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

