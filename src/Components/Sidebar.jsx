import * as ai from 'react-icons/ai';
import { Link } from 'react-router-dom';
import { serverv2 } from '../utils/apis';

// import React from 'react'
import { useState } from 'react';

export default function Sidebar(props) {
    const [items, setItems]  = useState(false);

    const {modal} = props;

    function closeModal() {
        modal(false);
    }
    
  return (
      <>
        <section className='shadow-2xl rounded-xl fixed z-50 top-[2%] right-[3%] lg:right-[1.5%] w-fit flex flex-col justify-center p-2 text-white bg-gray-800'>
            <div className='sidebar-icons group mt-0 cursor-pointer' onClick={() => setItems(!items)}>
                <ai.AiOutlineMenu size={28} />
                {/* <span className='sidebar-tooptip group-hover:scale-100'>Menu</span> */}
            </div>
        </section>
        <section className={`${items?"scale-y-100":"scale-y-0"} transition-all duration-200 rounded-xl fixed z-10 top-[10%] lg:right-[1%] right-[3%] w-fit flex flex-col justify-center text-white bg-slate-800 p-2`}>
            <Link tooltip="Home" to="/" onClick={() => closeModal()} className='bg-slate-500 hover:bg-blue-500 mb-2 rounded-md md:h-[25%] flex items-center md:w-[100%] p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiOutlineHome size={28}/></Link>
            <Link to="/addproduct" onClick={() => closeModal()} className='bg-slate-500 mb-2 hover:bg-blue-500 rounded-md flex items-center md:h-[25%] md:w-[100%] p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiFillPlusSquare size={28} /></Link>
            <button onClick={() => modal(true)}  className='bg-slate-500 md:h-[25%] mb-2 hover:bg-blue-500 md:w-[100%] p-2 rounded-md cursor-pointer hover:scale-105 transition-all'><ai.AiFillFileAdd size={28} /></button>
            <a href={serverv2} download={'data.csv'} rel="noopener"  onClick={() => {closeModal()}} className='bg-slate-500 hover:bg-blue-500 rounded-md md:h-[25%] flex items-center md:w-[100%] w-fit p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiOutlineCloudDownload size={28} /></a>
        </section>
    </>
  )
}


// export default function Sidebar(props) {

//     const {modal} = props;

    // function closeModal() {
    //     modal(false);
    // }

//     return(
//         <section className='p-2 md:p-0 w-[100%] md:w-fit flex md:flex-col justify-start md:justify-center mb-2 text-white'>
            // <Link to="/" onClick={() => closeModal()} className='bg-blue-600 md:h-[25%] flex items-center md:w-[100%] p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiOutlineHome size={28}/></Link>
            // <Link to="/addproduct" onClick={() => closeModal()} className='bg-green-600 flex items-center md:h-[25%] md:w-[100%] p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiFillPlusSquare size={28} /></Link>
            // <button onClick={() => modal(true)} className='bg-purple-600 md:h-[25%] md:w-[100%] p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiFillFileAdd size={28} /></button>
            // <a href={serverv2} download={'data.csv'} rel="noopener"  onClick={() => {closeModal()}} className='bg-orange-600 md:h-[25%] flex items-center md:w-[100%] w-fit p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiOutlineCloudDownload size={28} /></a>
//         </section>
//     );
// }