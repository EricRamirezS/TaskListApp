import taskReducer from '../redux/reducers.js';
import {
    ADD_TASK,
    DELETE_TASK,
    SAVE_EDIT_DESCRIPTION,
    FILTER_TASK,
    MARK_COMPLETED,
    MARK_UNCOMPLETED,
    UPDATE_SEARCH_TERM,
    ENABLE_EDIT_DESCRIPTION,
    LOAD_TASKS
} from '../redux/actionTypes.js';
import Task from '../models/Task';

describe('taskReducer', () => {
    it('should return the initial state', () => {
        expect(taskReducer(undefined, {})).toEqual({
            tasks: [],
            filter: 'ALL',
            searchTerm: ''
        });
    });

    it('should handle LOAD_TASKS', () => {
        const tasks = [new Task(1, 'Task 1', true, '2024-05-30')];
        const action = {
            type: LOAD_TASKS,
            payload: { tasks }
        };
        const initialState = {
            tasks: [],
            filter: 'ALL',
            searchTerm: ''
        };
        expect(taskReducer(initialState, action)).toEqual({
            tasks,
            filter: 'ALL',
            searchTerm: ''
        });
    });

    it('should handle ADD_TASK', () => {
        const initialState = {
            tasks: [],
            filter: 'ALL',
            searchTerm: ''
        };
        const action = {
            type: ADD_TASK,
            payload: { id: 1, description: 'New Task', createdAt: '2024-05-30' }
        };
        const newState = taskReducer(initialState, action);
        expect(newState.tasks.length).toBe(1);
        expect(newState.tasks[0].description).toBe('New Task');
    });

    it('should handle DELETE_TASK', () => {
        const initialState = {
            tasks: [new Task(1, 'Task 1', true, '2024-05-30')],
            filter: 'ALL',
            searchTerm: ''
        };
        const action = {
            type: DELETE_TASK,
            payload: { id: 1 }
        };
        const newState = taskReducer(initialState, action);
        expect(newState.tasks.length).toBe(0);
    });

    it('should handle SAVE_EDIT_DESCRIPTION', () => {
        const initialState = {
            tasks: [new Task(1, 'Task 1', true, '2024-05-30')],
            filter: 'ALL',
            searchTerm: ''
        };
        const action = {
            type: SAVE_EDIT_DESCRIPTION,
            payload: { id: 1, description: 'Updated Task Description' }
        };
        const newState = taskReducer(initialState, action);
        expect(newState.tasks[0].description).toBe('Updated Task Description');
    });

    it('should handle MARK_COMPLETED', () => {
        const initialState = {
            tasks: [new Task(1, 'Task 1', true, '2024-05-30')],
            filter: 'ALL',
            searchTerm: ''
        };
        const action = {
            type: MARK_COMPLETED,
            payload: { id: 1 }
        };
        const newState = taskReducer(initialState, action);
        expect(newState.tasks[0].active).toBe(false);
    });

    it('should handle MARK_UNCOMPLETED', () => {
        const initialState = {
            tasks: [new Task(1, 'Task 1', false, '2024-05-30')],
            filter: 'ALL',
            searchTerm: ''
        };
        const action = {
            type: MARK_UNCOMPLETED,
            payload: { id: 1 }
        };
        const newState = taskReducer(initialState, action);
        expect(newState.tasks[0].active).toBe(true);
    });

    it('should handle ENABLE_EDIT_DESCRIPTION', () => {
        const initialState = {
            tasks: [new Task(1, 'Task 1', true, '2024-05-30')],
            filter: 'ALL',
            searchTerm: ''
        };
        const action = {
            type: ENABLE_EDIT_DESCRIPTION,
            payload: { id: 1 }
        };
        const newState = taskReducer(initialState, action);
        expect(newState.tasks[0].editMode).toBe(true);
    });

    it('should handle FILTER_TASK', () => {
        const initialState = {
            tasks: [],
            filter: 'ALL',
            searchTerm: ''
        };
        const action = {
            type: FILTER_TASK,
            payload: { filter: 'COMPLETED' }
        };
        const newState = taskReducer(initialState, action);
        expect(newState.filter).toBe('COMPLETED');
    });

    it('should handle UPDATE_SEARCH_TERM', () => {
        const initialState = {
            tasks: [],
            filter: 'ALL',
            searchTerm: ''
        };
        const action = {
            type: UPDATE_SEARCH_TERM,
            payload: { searchTerm: 'example' }
        };
        const newState = taskReducer(initialState, action);
        expect(newState.searchTerm).toBe('example');
    });});
