import {BsPlus, BsSearch} from 'react-icons/bs';
import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {addTask, updateSearchTerm} from '../redux/actions';
import FilterButton from './FilterButton';
import TaskList from './TaskList';

const Task = () => {
    const dispatch = useDispatch();

    const [newTaskDescription, setNewTaskDescription] = useState('');
    const [searchTerm, setSearchTerm] = useState('');

    const handleAddTask = (description) => {
        dispatch(addTask(description));
    };
    const handleAddTaskClick = () => {
        if (newTaskDescription.trim() !== '') {
            handleAddTask(newTaskDescription);
            setNewTaskDescription("")
        }
    };

    const handleSearchChange = (description) => {
        console.log(description)
        setSearchTerm(description);
        dispatch(updateSearchTerm(description))
    }
    return (
        <div className="max-w-6xl mx-auto sm:mt-8 p-2 bg-gray-100 rounded">
            <h2 className="mt-3 mb-6 text-2xl font-bold text-center">To do Task</h2>

            {/* input text and button */}
            <div className="flex items-center mb-4">
                <input value={newTaskDescription}
                       onChange={(e) => setNewTaskDescription(e.target.value)}
                       type="text"
                       maxLength="55"
                       name="addTaskInput"
                       id="addTaskInput"
                       placeholder="Add Task"
                       className="flex-grow p-2 border-b-2 border-gray-300 focus:outline-none focus:border-blue-500"/>
                <button className="ml-4 p-2 bg-blue-500 text-white rounded hover:bg-blue-600 focus:outline-none"
                        onClick={handleAddTaskClick}>
                    <BsPlus></BsPlus>
                </button>
            </div>

            {/* filter and Search */}
            <div className='flex items-center mb-4 justify-between'>
                <FilterButton/>
                <div className='flex items-center mb-4'>
                    <input value={searchTerm}
                           onChange={(e) => handleSearchChange(e.target.value)}
                           type="text"
                           name="addTaskInput"
                           maxLength="55"
                           id="addTaskInput"
                           placeholder="Seach"
                           className="flex-grow p-2 border-b-2 border-gray-300 focus:outline-none focus:border-blue-500"/>
                    <span className="ml-4 p-2 bg-blue-800 text-white rounded focus:outline-none">
                        <BsSearch/>
                    </span>
                </div>
            </div>

            {/*Task List*/}
            <div className='flex items-center mb-4 justify-between'>
                <TaskList/>
            </div>
        </div>
    );
};

export default Task;