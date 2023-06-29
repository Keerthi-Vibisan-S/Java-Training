import ReactDom from 'react-dom';
import "./index.css";
import { BrowserRouter } from "react-router-dom";
import App from './app';


function Index() {
  return(
    <BrowserRouter>
      <App />
    </BrowserRouter>
  );
}


ReactDom.render(<Index />, document.getElementById('root'));
