import * as actions from '../redux/actions'; // import your action creators
import * as actionTypes from '../redux/actionTypes'; // import your action types

describe('Action Creators', () => {
    it('should create an action to load tasks', () => {
        const tasks = [{id: 1, description: 'Task 1', createdAt: '2024-05-30'}];
        const expectedAction = {
            type: actionTypes.LOAD_TASKS,
            payload: {tasks},
        };
        expect(actions.loadTasks(tasks)).toEqual(expectedAction);
    });

    it('should create an action to add a task', () => {
        const id = 1;
        const description = 'New Task';
        const createdAt = '2024-05-30';
        const expectedAction = {
            type: actionTypes.ADD_TASK,
            payload: {id, description, createdAt},
        };
        expect(actions.addTask(id, description, createdAt)).toEqual(expectedAction);
    });

    it('should create an action to mark a task as completed', () => {
        const id = 1;
        const expectedAction = {
            type: actionTypes.MARK_COMPLETED,
            payload: {id},
        };
        expect(actions.markCompleted(id)).toEqual(expectedAction);
    });

    it('should create an action to mark a task as uncompleted', () => {
        const id = 1;
        const expectedAction = {
            type: actionTypes.MARK_UNCOMPLETED,
            payload: {id},
        };
        expect(actions.markUncompleted(id)).toEqual(expectedAction);
    });

    it('should create an action to save edited description of a task', () => {
        const id = 1;
        const description = 'Updated Task Description';
        const expectedAction = {
            type: actionTypes.SAVE_EDIT_DESCRIPTION,
            payload: {id, description},
        };
        expect(actions.saveEditDescription(id, description)).toEqual(expectedAction);
    });

    it('should create an action to enable editing description of a task', () => {
        const id = 1;
        const expectedAction = {
            type: actionTypes.ENABLE_EDIT_DESCRIPTION,
            payload: {id},
        };
        expect(actions.enableEditDescription(id)).toEqual(expectedAction);
    });

    it('should create an action to delete a task', () => {
        const id = 1;
        const expectedAction = {
            type: actionTypes.DELETE_TASK,
            payload: {id},
        };
        expect(actions.deleteTask(id)).toEqual(expectedAction);
    });

    it('should create an action to filter tasks', () => {
        const filter = 'completed';
        const expectedAction = {
            type: actionTypes.FILTER_TASK,
            payload: {filter},
        };
        expect(actions.filterTask(filter)).toEqual(expectedAction);
    });

    it('should create an action to update search term', () => {
        const searchTerm = 'example';
        const expectedAction = {
            type: actionTypes.UPDATE_SEARCH_TERM,
            payload: {searchTerm},
        };
        expect(actions.updateSearchTerm(searchTerm)).toEqual(expectedAction);
    });
});