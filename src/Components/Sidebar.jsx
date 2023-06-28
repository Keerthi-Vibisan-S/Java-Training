import { useState } from 'react';
import * as ai from 'react-icons/ai';
import * as si from 'react-icons/si';

export default function Sidebar() {
    return(
        <section className='p-2 w-[100%] flex justify-end mb-2 text-white'>
            <button className='bg-blue-600 p-2 mr-4 cursor-pointer hover:scale-105 transition-all'><ai.AiOutlineHome size={28}/></button>
            <button className='bg-green-600 p-2 mr-4 cursor-pointer hover:scale-105 transition-all'><ai.AiFillPlusSquare size={28} /></button>
            <button className='bg-purple-600 p-2 mr-4 cursor-pointer hover:scale-105 transition-all'><ai.AiFillFileAdd size={28} /></button>
            <button className='bg-orange-600 p-2 cursor-pointer hover:scale-105 transition-all'><ai.AiOutlineCloudDownload size={28} /></button>
        </section>
    );
}