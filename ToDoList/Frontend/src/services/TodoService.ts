import axios from "axios";

const Rest_API_Base_URL = 'http://localhost:8080/api/todos';

export const listAllTodos = () => axios.get(Rest_API_Base_URL);

export const createTodo = (todo: any) => axios.post(Rest_API_Base_URL, todo);

export const getTodo = (taskId: number) => axios.get(Rest_API_Base_URL + '/' + taskId);

export const updateTodo = (taskId: number, todo: any) => axios.put(Rest_API_Base_URL + '/' + taskId, todo);

export const deleteTodo = (taskId: number) => axios.delete(Rest_API_Base_URL + '/' + taskId);

export const completeTodo = (taskId: number) => axios.patch(Rest_API_Base_URL + '/' + taskId + '/complete');

export const inCompleteTodo = (taskId: number) => axios.patch(Rest_API_Base_URL + '/' + taskId + '/in-complete');