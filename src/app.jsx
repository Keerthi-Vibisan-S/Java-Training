import Home from './Pages/home';
import UpdateProduct from './Pages/updateProduct';
import AddProduct from './Pages/addProduct';
import Sidebar from './Components/Sidebar';
import { Routes, Route} from 'react-router-dom';
import Modal from './Components/Modal';
import { useState } from 'react';

export default function App() {
    const [modal, setModal] = useState(false);
    return(
    <section className='min-h-[102vh] max-h-[100vh] md:flex'>
        <Sidebar modal={setModal} />
        <section className='w-[100%]'>
            {modal?<Modal status={setModal} />:""}
            <Routes>
                <Route path="/" element={ <Home/> } />
                <Route path="/update" element={ <UpdateProduct/> } />
                <Route path="/addproduct" element={ <AddProduct /> } />
        </Routes>
      </section>
    </section>
    );
}