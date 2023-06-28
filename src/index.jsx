import ReactDom from 'react-dom';
import "./index.css";
import Home from './Pages/home';
import Sidebar from './Components/Sidebar';

function Index() {
  return(
    <section className='p-5 min-h-[100vh] max-h-[100vh]'>
      <Sidebar />
      <Home />
    </section>
  );
}


ReactDom.render(<Index />, document.getElementById('root'));
