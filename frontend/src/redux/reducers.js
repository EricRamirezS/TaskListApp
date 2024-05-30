import {
    ADD_TASK,
    DELETE_TASK,
    SAVE_EDIT_DESCRIPTION,
    FILTER_TASK,
    MARK_COMPLETED,
    MARK_UNCOMPLETED,
    UPDATE_SEARCH_TERM, ENABLE_EDIT_DESCRIPTION, LOAD_TASKS
} from './actionTypes.js';
import Task from '../models/Task.js';

let task_id = 0;


const initialState = {
    tasks: [],
    filter: 'ALL',
    searchTerm: ''
};

const taskReducer = (state = initialState, action) => {
    if (!action.payload) return state;

    switch (action.type) {
        case LOAD_TASKS:
            return {
                tasks: action.payload.tasks,
                filter: state.filter,
                searchTerm: state.searchTerm
            };
        case ADD_TASK:
            return {
                tasks: [new Task(action.payload.id, action.payload.description, true, action.payload.createdAt), ...state.tasks],
                filter: state.filter,
                searchTerm: state.searchTerm
            };
        case MARK_COMPLETED:
            let toCompleteTask = state.tasks.find(task => task.id === action.payload.id);
            if (toCompleteTask) {
                toCompleteTask.active = false;
            }
            return {
                tasks: state.tasks,
                filter: state.filter,
                searchTerm: state.searchTerm
            };
        case MARK_UNCOMPLETED:
            let toRestartTask = state.tasks.find(task => task.id === action.payload.id);
            if (toRestartTask) {
                toRestartTask.active = true;
            }
            return {
                tasks: state.tasks,
                filter: state.filter,
                searchTerm: state.searchTerm
            };

        case DELETE_TASK:
            return {
                tasks: state.tasks.filter(task => task.id !== action.payload.id),
                filter: state.filter,
                searchTerm: state.searchTerm
            };
        case SAVE_EDIT_DESCRIPTION:
            let toEditTask = state.tasks.find(task => task.id === action.payload.id);
            if (toEditTask) {
                toEditTask.description = action.payload.description;
                toEditTask.editMode = !toEditTask.editMode;
            }
            return {
                tasks: state.tasks,
                filter: state.filter,
                searchTerm: state.searchTerm
            };
        case ENABLE_EDIT_DESCRIPTION:
            let toEnableEditTask = state.tasks.find(task => task.id === action.payload.id);
            if (toEnableEditTask) {
                toEnableEditTask.editMode = !toEnableEditTask.editMode;
            }
            return {
                tasks: state.tasks,
                filter: state.filter,
                searchTerm: state.searchTerm
            };
        case FILTER_TASK:
            return {
                tasks: state.tasks,
                filter: action.payload.filter,
                searchTerm: state.searchTerm
            };
        case UPDATE_SEARCH_TERM:
            return {
                tasks: state.tasks,
                filter: state.filter,
                searchTerm: action.payload.searchTerm
            };
        default:
            return state;
    }
};

export default taskReducer;