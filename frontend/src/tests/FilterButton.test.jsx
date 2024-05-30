import React from 'react';
import {render, fireEvent} from '@testing-library/react';
import {Provider} from 'react-redux';
import configureStore from 'redux-mock-store';
import FilterButton from '../components/FilterButton.jsx';

describe('FilterButton', () => {
    const mockStore = configureStore([]);
    let store;
    let component;

    beforeEach(() => {
        store = mockStore({
            filter: 'ALL', // Initial state
        });

        component = render(
            <Provider store={store}>
                <FilterButton/>
            </Provider>
        );
    });

    it('renders the default filter button correctly', () => {
        const {getByText} = component;
        expect(getByText('Default')).toBeInTheDocument();
    });

    it('dispatches filterTask action when selecting a filter', () => {
        const {getByRole} = component;

        // Simulate selecting a filter
        fireEvent.change(getByRole('combobox'), {target: {value: 'COMPLETED'}});

        // Check if the filterTask action is dispatched with the correct filter value
        const actions = store.getActions();
        expect(actions).toHaveLength(1);
        expect(actions[0].type).toEqual('FILTER_TASK');
        expect(actions[0].payload.filter).toEqual('COMPLETED');
    });
});
