import Axios from "axios";
import { useState } from "react";
import { serverv1 } from "../utils/apis";
import ErrorToast from "../Components/Toast/errorToast";

export default function AddProduct() {

    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [type, setType] = useState("");
    const [price, setPrice] = useState("");

    const [toast, setToast] = useState(false);
    const [errorMsg, setErrorMsg] = useState("An Error Occurred");
    const [toastStatus, setToastStatus] = useState(false);

    async function addProduct(e) {
        e.preventDefault();
        const data = {
            name: name,
            type: type,
            price: price,
            description: description
        }

        try{
            const res = await Axios.post(serverv1, data);
            console.log(res.data);
            if(res.data.status === 201) {
                console.log("done");
                setErrorMsg(res.data.message);
                setToastStatus(true);
                setToast(true);
                clearAll();
            } 
        } catch(error) {
            console.log(error.response.data.message);
            if(error.response.status===409) {
                setErrorMsg(error.response.data.message);
                setToastStatus(false);
                setToast(true);
            }
        }
    }

    function clearAll() {
        setName("");
        setDescription("");
        setType("");
        setPrice("");
    }

    return (
        <section className="flex flex-col justify-center items-center">
            {toast?<ErrorToast status={toastStatus} close={setToast} error={errorMsg} />:""}

            <p className="font-semibold text-3xl text-center">Add Product</p>
            
            <form onSubmit={(e) => addProduct(e)} className="w-[80%]">
                <div className="relative z-0 w-full mb-6 group">
                    <input value={name} onChange={(e) => setName(e.target.value)} type="text" id="name" className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
                    <label htmlFor="name" className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Name</label>
                </div>
                <div className="relative z-0 w-full mb-6 group">
                    <input value={description} onChange={(e) => setDescription(e.target.value)} type="text" name="Description" id="Description" className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
                    <label htmlFor="Description" className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Description</label>
                </div>
                
                <div className="grid md:grid-cols-2 md:gap-6">
                    <div className="relative z-0 w-full mb-6 group">
                        <input value={type} onChange={(e) => setType(e.target.value)} type="text" name="type" id="type" className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
                        <label htmlFor="type" className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Category</label>
                    </div>
                    <div className="relative z-0 w-full mb-6 group">
                        <input value={price} onChange={(e) => setPrice(e.target.value)} type="text" name="price" id="price" className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
                        <label htmlFor="price" className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Price</label>
                    </div>
                </div>
                <button type="submit" className="text-white float-right bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Add Product</button>
            </form>

        </section>
    );
}