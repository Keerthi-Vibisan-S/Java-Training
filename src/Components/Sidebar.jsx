import * as ai from 'react-icons/ai';
import { Link } from 'react-router-dom';
import { serverv2 } from '../utils/apis';

export default function Sidebar(props) {

    const {modal} = props;

    function closeModal() {
        modal(false);
    }

    return(
        <section className='p-2 md:p-0 w-[100%] md:w-fit flex md:flex-col justify-start md:justify-center mb-2 text-white'>
            <Link to="/" onClick={() => closeModal()} className='bg-blue-600 md:h-[25%] flex items-center md:w-[100%] p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiOutlineHome size={28}/></Link>
            <Link to="/addproduct" onClick={() => closeModal()} className='bg-green-600 flex items-center md:h-[25%] md:w-[100%] p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiFillPlusSquare size={28} /></Link>
            <button onClick={() => modal(true)} className='bg-purple-600 md:h-[25%] md:w-[100%] p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiFillFileAdd size={28} /></button>
            <a href={serverv2} download={'data.csv'} rel="noopener"  onClick={() => {closeModal()}} className='bg-orange-600 md:h-[25%] flex items-center md:w-[100%] w-fit p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiOutlineCloudDownload size={28} /></a>
        </section>
    );
}