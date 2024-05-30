import {useSelector} from 'react-redux';
import {TaskItem} from './TaskItem';

const TaskList = () => {
    const filteredTasks = useSelector((state) => {
        const tasks = state.tasks;
        const filter = state.filter;
        const searchTerm = state.searchTerm;

        return tasks.filter(task => {
            const matchesFilter = (filter === 'ALL') ||
                (filter === 'COMPLETED' && !task.active) ||
                (filter === 'UNCOMPLETED' && task.active);

            const matchesSearch = searchTerm === '' || task.description.toLowerCase().includes(searchTerm.toLowerCase());

            return matchesFilter && matchesSearch;
        });
    });

    return (
        <ul className='w-full'>
            <li className="my-2 text-sm italic">Tasks ...</li>
            {
                filteredTasks.map((task, index) => (
                    <TaskItem key={task.id} task={task} index={index}/>
                ))
            }
        </ul>
    );
};

export default TaskList;