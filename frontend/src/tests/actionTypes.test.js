import {
    LOAD_TASKS,
    ADD_TASK,
    MARK_UNCOMPLETED,
    MARK_COMPLETED,
    SAVE_EDIT_DESCRIPTION,
    DELETE_TASK,
    FILTER_TASK,
    UPDATE_SEARCH_TERM,
    ENABLE_EDIT_DESCRIPTION
} from '../redux/actionTypes.js';

describe("Action Type Constants", () => {
    it("should have LOAD_TASKS as string 'LOAD_TASKS'", () => {
        expect(LOAD_TASKS).toEqual('LOAD_TASKS');
    });

    it("should have ADD_TASK as string 'ADD_TASK'", () => {
        expect(ADD_TASK).toEqual('ADD_TASK');
    });

    it("should have MARK_UNCOMPLETED as string 'MARK_UNCOMPLETED'", () => {
        expect(MARK_UNCOMPLETED).toEqual('MARK_UNCOMPLETED');
    });

    it("should have MARK_COMPLETED as string 'MARK_COMPLETED'", () => {
        expect(MARK_COMPLETED).toEqual('MARK_COMPLETED');
    });

    it("should have SAVE_EDIT_DESCRIPTION as string 'SAVE_EDIT_DESCRIPTION'", () => {
        expect(SAVE_EDIT_DESCRIPTION).toEqual('SAVE_EDIT_DESCRIPTION');
    });

    it("should have DELETE_TASK as string 'DELETE_TASK'", () => {
        expect(DELETE_TASK).toEqual('DELETE_TASK');
    });

    it("should have FILTER_TASK as string 'FILTER_TASK'", () => {
        expect(FILTER_TASK).toEqual('FILTER_TASK');
    });

    it("should have UPDATE_SEARCH_TERM as string 'UPDATE_SEARCH_TERM'", () => {
        expect(UPDATE_SEARCH_TERM).toEqual('UPDATE_SEARCH_TERM');
    });

    it("should have ENABLE_EDIT_DESCRIPTION as string 'ENABLE_EDIT_DESCRIPTION'", () => {
        expect(ENABLE_EDIT_DESCRIPTION).toEqual('ENABLE_EDIT_DESCRIPTION');
    });
});
