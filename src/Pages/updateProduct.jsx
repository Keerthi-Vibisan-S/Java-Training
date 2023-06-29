import { useState } from "react";
import { useLocation } from "react-router-dom";
import ErrorToast from "../Components/Toast/errorToast";
import Axios from "axios";
import { serverv1 } from "../utils/apis";

export default function UpdateProduct() {
    const location = useLocation();
    const data = location.state;

    const [name, setName] = useState(data.name);
    const [description, setDescription] = useState(data.description);
    const [type, setType] = useState(data.type);
    const [price, setPrice] = useState(data.price);

    const [toast, setToast] = useState(false);
    const [errorMsg, setErrorMsg] = useState("An Error Occurred");
    const [toastStatus, setToastStatus] = useState(false);

    async function updateProduct(e) {
        e.preventDefault();
        const d = {
            name: name,
            type: type,
            price: price,
            description: description
        }

        try {
            let res = await Axios.patch(`${serverv1}/${data.id}`, d);
            if(res.data.status === 202) {
                setErrorMsg(res.data.message);
                setToastStatus(true);
                setToast(true);
            }

        } catch(error) {
            console.log(error.response);
            if(error.response.status === 409) {
                setErrorMsg(error.response.data.error);
            } else {
                setErrorMsg(error.message);
            }
            setToastStatus(false);
            setToast(true);
        }

        setTimeout(() => {
            setToast(false);
        }, 5000);
    }

    return (
        <section className="flex flex-col justify-center items-center">
            {toast?<ErrorToast status={toastStatus} close={setToast} error={errorMsg} />:""}

            <p className="font-semibold text-3xl text-center">Update Product</p>
            
            <form onSubmit={(e) => updateProduct(e)} className="w-[80%]">
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
                <button type="submit" className="text-white float-right bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Update Product</button>
            </form>

        </section>
    );
}