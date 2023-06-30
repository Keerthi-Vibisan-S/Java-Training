import * as ai from 'react-icons/ai';
import { Link } from 'react-router-dom';
import { serverv2 } from '../utils/apis';

export default function Sidebar(props) {

    const {modal} = props;

    function closeModal() {
        modal(false);
    }

    return(
        <section className='p-3 px-3 w-[100%] my-bg-black flex justify-between mb-2 shadow-xl'>
            <div className='flex items-center my-text-teal text-2xl'>
                Logo Here
            </div>
            <div className='flex justify-end'>
                <Link to="/" onClick={() => closeModal()} className='my-bg-grey rounded-lg flex items-center p-2 mr-4 cursor-pointer hover:scale-105 transition-all'><ai.AiOutlineHome className='mr-1' size={24}/> <p className='hidden lg:block'>Home</p></Link>
                <Link to="/addproduct" onClick={() => closeModal()} className='my-bg-grey rounded-lg flex items-center p-2 mr-4 cursor-pointer hover:scale-105 transition-all'><ai.AiFillPlusSquare className='mr-1' size={24} /><p className='hidden lg:block'>Add Product</p></Link>
                <button onClick={() => modal(true)} className='my-bg-grey rounded-lg flex items-center p-2 mr-4 cursor-pointer hover:scale-105 transition-all'><ai.AiFillFileAdd className='mr-1' size={22} /><p className='hidden lg:block'>Export</p></button>
                <a href={serverv2} download={'data.csv'} rel="noopener"  onClick={() => {closeModal()}} className='rounded-lg my-bg-grey p-2 cursor-pointer hover:scale-105 flex items-center transition-all'><ai.AiOutlineCloudDownload className='mr-1' size={28} /><p className='hidden lg:block'>Download</p></a>
            </div>
        </section>
    );
}