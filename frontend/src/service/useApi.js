import {useState, useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {loadTasks} from '../redux/actions.js';
import Task from '../models/Task.js';

const BASE_URL = import.meta.env.VITE_API_URL ?? 'http://127.0.0.1:8080/api/tasks';
const API_KEY = import.meta.env.VITE_API_KEY ?? 'sample_key';

const parseDataToTask = (data) => {
    return new Task(data.id, data.description, data.active, new Date(data.createdAt));
};

export const useFetchTasks = () => {
    const dispatch = useDispatch();
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        setLoading(true);
        fetch(BASE_URL, {
            method: 'GET',
            headers: {
                'X-API-KEY': API_KEY,
                'Accept': 'application/json',
                'Cross-origin': '*',
            }
        }).then((response) => response.json())
            .then((data) => {
                for (let i = 0; i < data.length; i++) {
                    data[i] = parseDataToTask(data[i]);
                }
                setData(data);
                dispatch(loadTasks(data));
            })
            .finally(() => setLoading(false));
    }, []);

    return {data, loading};
};

export const usePatchTask = async (id, description, active) => {
    try {
        let response = await fetch(`${BASE_URL}/${id}`, {
            method: 'PATCH',
            headers: {
                'X-API-KEY': API_KEY,
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Cross-origin': '*',
            },
            body: JSON.stringify({
                description: description,
                active: active,
            })
        });
        console.log(response)
        if (response.status === 200) {
            let data = await response.json();
            return parseDataToTask(data);
        }
        return null;
    } catch (ex) {
        return null;
    }
};


export const usePostTask = async (description) => {
    try {
        let response = await fetch(BASE_URL, {
            method: 'POST',
            headers: {
                'X-API-KEY': API_KEY,
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Cross-origin': '*',
            },
            body: JSON.stringify({
                description: description,
            })
        });
        console.log(response)
        if (response.status === 201) {
            let data = await response.json();
            return parseDataToTask(data);
        }
        return null;
    } catch (ex) {
        return null;
    }
};

export const useDeleteTask = async (id) => {
    try {
        let response = await fetch(`${BASE_URL}/${id}`, {
            method: 'DELETE',
            headers: {
                'X-API-KEY': API_KEY,
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Cross-origin': '*',
            },
        });
        console.log(response)
        if (response.status === 200) {
            let data = await response.json();
            return parseDataToTask(data);
        }
        return null;
    } catch (ex) {
        return null;
    }
};
