import {deleteTask, saveEditDescription, markCompleted, markUncompleted, enableEditDescription} from '../redux/actions';
import {useDispatch} from 'react-redux';
import {FaCheck, FaEdit, FaSave, FaSquare, FaTrash} from 'react-icons/fa';
import {useState} from 'react';
import {useDeleteTask, usePatchTask} from '../service/useApi.js';

export const TaskItem = ({task, index}) => {
        const dispatch = useDispatch();
        const [editedDescription, setEditedDescription] = useState('');

        const handleMarkCompleted = async () => {
            let result = await usePatchTask(task.id, null, false);
            if (result) {
                dispatch(markCompleted(result.id));
            }
        };
        const handleMarkUncompleted = async () => {
            let result = await usePatchTask(task.id, null, true);
            if (result) {
                dispatch(markUncompleted(result.id));
            }
        };
        const handleUpdateDescription = async () => {
            let result = await usePatchTask(task.id, editedDescription, null);
            if (result) {
                dispatch(saveEditDescription(result.id, result.description));
            }
        };

        const handleDelete = async () => {
            let result = await useDeleteTask(task.id);
            if (result) {
                dispatch(deleteTask(result.id));
            }
        };

        const handleKeyPressedOnEdit = async (event) => {
            if (event.key === 'Enter') {
                await handleUpdateDescription();
            }
        }

        return (
            <li className="w-full flex flex-col sm:flex-row sm:items-center justify-between border-b-2 py-2 gap-0">
                <div className="flex-none space-x-3 ml-2">
                    {task.active && (
                        <button onClick={() => handleMarkCompleted()}
                                className="mr-2 text-sm bg-green-500 test-white sm:px-2 py-1 px-1 rounded">
                            <FaCheck/>
                        </button>)
                    }
                    {!task.active && (
                        <button onClick={() => handleMarkUncompleted()}
                                className="mr-2 text-sm bg-red-500 test-white sm:px-2 py-1 px-1 rounded">
                            <FaSquare/>
                        </button>)
                    }

                </div>
                <div className="flex grow text-wrap text-left text-sm">
                    <article>
                        <p>
                            <span className="mr-4 text-gray-500">{index + 1}</span>
                            {task.editMode && (
                                <input defaultValue={task.description}
                                       onChange={(e) => setEditedDescription(e.target.value)}
                                       type="text"
                                       name={`editTask${task.id}`}
                                       maxLength="55"
                                       id={`editTask${task.id}`}
                                       onKeyDown={e => handleKeyPressedOnEdit(e)}
                                       className="flex-grow p-2 border-b-2 border-gray-300 focus:outline-none focus:border-blue-500"/>
                            )}
                            {!task.editMode && (
                                <span
                                    className={`mr-0 ${task.active ? '' : 'line-through text-gray-500'}`}>{task.description}</span>
                            )}
                        </p>
                    </article>
                </div>
                <div className="flex text-left text-sm">
                <span
                    className={`mr-0 ${task.active ? '' : 'line-through text-gray-500'}`}>{new Date(task.createdAt).toLocaleString()
                }</span>

                </div>
                <div className="flex-none space-x-1 ml-1 justify-between">
                    {task.editMode && (
                        <button onClick={() => handleUpdateDescription()}
                                className="text-sm bg-blue-500 test-white sm:px-2 py-1 px-1 rounded">
                            <FaSave/>
                        </button>
                    )}
                    <button onClick={() => dispatch(enableEditDescription(task.id))}
                            className="mr-1 text-sm bg-yellow-500 test-white sm:px-2 py-1 px-1 rounded">
                        <FaEdit/>
                    </button>
                    <button onClick={() => handleDelete()}
                            className="mr-1 text-sm bg-red-500 test-white sm:px-2 py-1 px-1 rounded">
                        <FaTrash/>
                    </button>
                </div>
            </li>
        );
    }
;