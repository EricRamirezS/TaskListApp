import {
    LOAD_TASKS,
    ADD_TASK,
    MARK_UNCOMPLETED,
    MARK_COMPLETED,
    SAVE_EDIT_DESCRIPTION,
    DELETE_TASK,
    FILTER_TASK,
    UPDATE_SEARCH_TERM, ENABLE_EDIT_DESCRIPTION
} from './actionTypes.js';

export const loadTasks = (tasks) => ({
    type: LOAD_TASKS,
    payload: {
        tasks,
    }
});


export const addTask = (id, description, createdAt) => ({
    type: ADD_TASK,
    payload: {
        id,
        description,
        createdAt,
    }
});

export const markCompleted = (id) => ({
    type: MARK_COMPLETED,
    payload: {
        id,
    }
});

export const markUncompleted = (id) => ({
    type: MARK_UNCOMPLETED,
    payload: {
        id,
    }
});

export const saveEditDescription = (id, description) => ({
    type: SAVE_EDIT_DESCRIPTION,
    payload: {
        id,
        description,
    }
});

export const enableEditDescription = (id) => ({
    type: ENABLE_EDIT_DESCRIPTION,
    payload: {
        id,
    }
});

export const deleteTask = (id) => ({
    type: DELETE_TASK,
    payload: {
        id,
    }
});

export const filterTask = (filter) => ({
    type: FILTER_TASK,
    payload: {
        filter,
    }
});

export const updateSearchTerm = (searchTerm) => ({
    type: UPDATE_SEARCH_TERM,
    payload: {
        searchTerm,
    }
});