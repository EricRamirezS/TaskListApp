import { render, fireEvent, waitFor } from '@testing-library/react';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';
import { TaskItem } from '../components/TaskItem.jsx';

// Mock Redux store
const mockStore = configureStore([]);

describe('TaskItem component', () => {
    let store;
    let task;
    let index;

    beforeEach(() => {
        task = {
            id: 1,
            description: 'Test task',
            active: true,
            createdAt: Date.now(),
            editMode: false
        };
        store = mockStore({
            tasks: [task],
            filter: 'ALL',
            searchTerm: ''
        });
        index = 0;
    });

    it('renders task item correctly', () => {
        const { getByText } = render(
            <Provider store={store}>
                <TaskItem task={task} index={index} />
            </Provider>
        );

        expect(getByText(task.description)).toBeInTheDocument();
    });
});
