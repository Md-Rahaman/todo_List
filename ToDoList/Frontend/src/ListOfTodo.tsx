import { useEffect, useState } from "react";
import { Container, Table } from "react-bootstrap";
import { Link, useHistory } from "react-router-dom";
import { completeTodo, deleteTodo, inCompleteTodo, listAllTodos, updateTodo } from "./services/TodoService";
import { error } from "console";

type todo = {
    taskId: number,
    taskTitle: string,
    taskDescription: string,
    taskStatus: boolean
}

const ListOfTodo = () => {

    let navigator = useHistory();
    let [todos, setTodo] = useState<todo[]>([]);

    useEffect(() => getTodos(), []);

    function editTodo(id: number) {
        navigator.push(`/update-todo/${id}`)
    }

    function complete(id: number) {
        completeTodo(id)
            .then((response) => {
                console.log(response.data);
                getTodos();
            })
            .catch((error) => console.log(error))
    }

    function inComplete(id: number) {
        inCompleteTodo(id)
            .then((response) => {
                console.log(response.data);
                getTodos();
            })
            .catch((error) => console.log(error))
    }

    function removeTodo(id: number) {
        deleteTodo(id)
            .then((response) => {
                console.log(response.data);
                getTodos();
            })
            .catch((error) => console.log(error))
    }

    function getTodos() {
        listAllTodos()
            .then((response) => {
                console.log(response.data);
                setTodo(response.data);
            })
            .catch((err) => console.log(err))
    }
    return (<>
        <h1 style={{ textAlign: "center" }}>List of Task</h1>
        <div style={{ marginTop: "20px" }}>
            <Link to="/add-todo" className="btn btn-primary" style={{ marginBottom: "10px" }}>Add Task</Link>
            <Table striped bordered hover >
                <thead>
                    <tr>
                        <th >Task Title</th>
                        <th >Task Description</th>
                        <th >Task Completed</th>
                        <th >Actions </th>
                    </tr>
                </thead>
                <tbody>
                    {
                        todos.map((todo) =>
                            <tr key={todo.taskId}>
                                <td>{todo.taskTitle}</td>
                                <td>{todo.taskDescription}</td>
                                <td>{todo.taskStatus ? "Yes" : "No"}</td>
                                <td>
                                    <button onClick={() => editTodo(todo.taskId)}
                                        className="btn btn-info" style={{ marginRight: "10px", fontWeight: "500" }}>Update</button>

                                    <button className="btn btn-danger" onClick={() => removeTodo(todo.taskId)}
                                        style={{ marginRight: "10px" }}>Delete</button>

                                    <button className="btn btn-success" onClick={() => complete(todo.taskId)}
                                        style={{ marginRight: "10px" }}>Complete</button>

                                    <button className="btn btn-info" onClick={() => inComplete(todo.taskId)}
                                        style={{ marginRight: "10px", fontWeight: "500" }}>In Complete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </Table>
        </div>

    </>)
}

export default ListOfTodo;


