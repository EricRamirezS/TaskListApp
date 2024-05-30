import './App.css';
import {Provider, useDispatch} from 'react-redux';
import store from './redux/store';
import Task from './components/Task';

function App() {
    return (
        <Provider store={store}>
            <Task></Task>
        </Provider>
    );
}

export default App;
