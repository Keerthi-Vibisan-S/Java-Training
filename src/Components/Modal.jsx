import { useEffect, useState } from 'react';
import * as ai from 'react-icons/ai';
import ErrorToast from './Toast/errorToast';
import  Axios  from 'axios';
import { fileUpload } from '../utils/apis';
import { useNavigate } from 'react-router-dom';

export default function Modal(props) 
{
    const [files, setFiles] = useState([]);
    const [file, setFile] = useState([]); // To store filtered files
    const [toast, setToast] = useState(false);
    const [process, setProcess] = useState(false);
    const [errorMsg, setErrorMsg] = useState("An Error Occurred");
    const [toastStatus, setToastStatus] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        // Shows error while using map
        setFile([]);
        for (const file of files) {
            let extension = file.name.substring(file.name.lastIndexOf('.') + 1);
            if(extension !== "csv") {
                setErrorMsg("Only csv files are allowed");
                setToastStatus(false);
                setToast(true);
                continue;
            }
            setFile(prev => [...prev, file]);
          }
          toastOff();
    }, [files])

    async function sendFiles() {
        const data = new FormData();
        file.forEach((f, k) => {
            data.append("files", f);
        })
        // Sending API Request
        try{
            setProcess(true);
        const res = await Axios.post(fileUpload, data);
        if(res.data.status === 201) {
            setProcess(false);
            setToastStatus(true);
            setErrorMsg("Files Uploaded Successfully");
            setToast(true);
            setFile([]); setFiles([]);

            setTimeout(() => {
                props.status(false);
                navigate("/");
            }, 2000);
        }

        else {
            setErrorMsg("An Error occurred at Server Side");
            setToastStatus(false);
            setToast(true);
        }

        } catch(error) {
            setErrorMsg(error.message);
            setToastStatus(false);
            setToast(true);
        }
        toastOff();
    }

    const toastOff = () => setTimeout(() => {
        setToast(false);
    }, 5000);

    return(
        <div className={`bg-white shadow-2xl whitespace-pre-wrap z-50 fixed lg:absolute top-[50%] right-[50%] transform translate-x-[50%] translate-y-[-50%] w-[60%] overflow-y-scroll max-h-[100vh] lg:h-[90%] outline-none`}>
            <div className="border-none h-[100%] shadow-xl relative flex flex-col w-full pointer-events-auto bg-clip-padding rounded-md outline-none text-current">
                <div className="sticky top-0 z-10  flex flex-shrink-0 items-center justify-between p-4 border-b border-gray-200 bg-white rounded-t-md">
                    <p className='text-xl'>Choose file to upload</p>
                    <div className="flex items-center justify-center">
                        <ai.AiFillCloseCircle size={34} className="text-red-600 cursor-pointer hover:scale-110" onClick={() => {props.status(false)}}/>
                    </div>
                </div>
                <div className="relative h-[100%] p-4">
                    <div className="">
                        <label htmlFor='fileupload' className='py-4 border border-slate-200 flex justify-center items-center'><ai.AiFillFileAdd className="text-slate-600" size={80} /></label>
                        <input id="fileupload" name="fileupload" type='file' onChange={(e) => setFiles(e.target.files)} accept=".csv" className='w-[0%]' multiple/>
                    </div>
                    <div>
                        File Count: {file.length} 
                        <div className='grid grid-cols-5 grid-flow-row'>
                            {
                                file.map((item, key) => {
                                    return (
                                        <div key={key} className='flex flex-col items-center min-w-[20%] mt-4'>
                                            <ai.AiFillFileText size={60} className='text-blue-500' />
                                            <p>{item.name}</p>
                                        </div>
                                    );
                                })
                            }
                        </div>
                        {file.length > 0 ? <button onClick={() => {if(!process)sendFiles()}} className={`${process?'bg-indigo-500':'bg-green-500 hover:bg-green-600'} fixed p-2 px-4 rounded-md  bottom-5 right-5 text-white font-semibold`}>{process?<ai.AiOutlineLoading3Quarters className='animate-spin' size={28}/>:<p>Export</p>}</button>:""}
                    </div>
                </div>
            </div>
            {toast?<ErrorToast status={toastStatus} close={setToast} error={errorMsg} />:""}
        </div>
    );
}