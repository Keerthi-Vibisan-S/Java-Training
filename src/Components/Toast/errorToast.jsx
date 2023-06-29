import * as bi from 'react-icons/bi';
import * as gr from 'react-icons/gr';

export default function ErrorToast(props) {

  // status --- false error
  // true good
    const {close, error, status} = props;
  return (
    
    <div id="toast-default" className={`${status?'bg-green-500':'bg-red-500'} fixed bottom-10 right-10 flex items-center w-full max-w-xs p-4 text-white  rounded-lg shadow`} role="alert">
        <div className={`inline-flex items-center justify-center flex-shrink-0 w-8 h-8 ${status?'bg-green-100 text-green-500':'text-red-500 bg-red-100 '}  rounded-lg`}>
            {status?<gr.GrStatusGood size={28} />:<bi.BiErrorCircle size={30}/>}
        </div>
        <div className="ml-3 text-sm font-normal">{error}</div>
        <button onClick={() => close(false)} type="button" className="ml-auto -mx-1.5 -my-1.5 bg-white text-gray-400 hover:text-gray-900 rounded-lg focus:ring-2 focus:ring-gray-300 p-1.5 hover:bg-gray-100 inline-flex h-8 w-8 dark:text-gray-500 dark:hover:text-white dark:bg-gray-800 dark:hover:bg-gray-700" data-dismiss-target="#toast-default" aria-label="Close">
            <span className="sr-only">Close</span>
            <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fillRule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clipRule="evenodd"></path></svg>
        </button>
    </div>

  )
}