import {useDispatch, useSelector} from 'react-redux';
import {filterTask} from '../redux/actions';

const FilterButton = () => {

    const dispatch = useDispatch();
    const currentFilter = useSelector((state) => state.filter);

    const handleFilterChange = (filter) => {
        dispatch(filterTask(filter));
    };

    return (
        <div className="flex space-x-4 items-center">
            <select
                value={currentFilter}
                onChange={(e) => handleFilterChange(e.target.value)}
                className="text-sm px-2 py-1 rounded border border-gray-300 focus:outline-none">
                <option value="ALL">Default</option>
                <option value="COMPLETED">Completed</option>
                <option value="UNCOMPLETED">Uncompleted</option>
            </select>
        </div>
    );
};

export default FilterButton;