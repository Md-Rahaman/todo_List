import { FormEvent, useEffect, useState } from "react"
import { Container, Form } from "react-bootstrap"
import { useHistory, useParams } from "react-router-dom";
import { createTodo, getTodo, listAllTodos, updateTodo } from "./services/TodoService";
import { error } from "console";

interface RouteParam {
    id: string;
}



const TodoComponent = () => {

    let navigator = useHistory();

    let [taskTitle, setTitle] = useState<string>("");
    let [taskDescription, setDescription] = useState<string>("");
    let [taskStatus, setCompleted] = useState<string>("");
    const { id } = useParams<RouteParam>();

    useEffect(() => {
        if (id)
            getTodoById(Number(id))
    }, [id])

    function getTodoById(id: number) {
        getTodo(Number(id)).
            then((response) => {
                console.log(response.data);
                setTitle(response.data.taskTitle);
                setDescription(response.data.taskDescription);
                setCompleted(response.data.taskStatus)
            }).catch((err) => console.log(err))
    }

    function saveOrUpdateTodo(e: FormEvent) {
        e.preventDefault();

        const todo = { taskTitle, taskDescription, taskStatus };
        console.log(todo);
        if (id) {
            updateTodo(Number(id), todo)
                .then((response) => {
                    console.log(response.data);
                    navigator.push("/");
                })
        }
        else {
            createTodo(todo)
                .then((response) => {
                    console.log(response.data);
                    navigator.push("/");
                })
                .catch((err) => console.log(err))
        }

    }



    return (<>
        <Container style={{ marginTop: "50px" }}>
            <Form onSubmit={(e) => saveOrUpdateTodo(e)}>
                <Form.Group className="mb-3" controlId="title">
                    <Form.Label>Todo Title</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter Task Title"
                        value={taskTitle}
                        onChange={(e) => setTitle(e.target.value)}
                    />
                </Form.Group>
                <Form.Group className="mb-3" controlId="description">
                    <Form.Label>Todo Description</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter Task Description"
                        value={taskDescription}
                        onChange={(e) => setDescription(e.target.value)}
                    />
                </Form.Group>
                <Form.Group className="mb-3" controlId="completed">
                    <Form.Label>Completed ?</Form.Label>
                    <select
                        className='form-control'
                        value={taskStatus}
                        onChange={(e) => setCompleted(e.target.value)}
                    >
                        <option value="false">No</option>
                        <option value="true">Yes</option>

                    </select>
                </Form.Group>
                <button type="submit" className="btn btn-success" >Submit</button>
            </Form>
        </Container>

    </>)
}
export default TodoComponent
