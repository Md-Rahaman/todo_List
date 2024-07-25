import { BrowserRouter, Route, Switch } from "react-router-dom";
import ListOfTodo from "./ListOfTodo";
import TodoComponent from "./TodoComponent";

const App = () => {
    return (<>
        <BrowserRouter>
            <Switch>
                <Route exact path="/" component={ListOfTodo}></Route>
                <Route path="/add-todo" component={TodoComponent}></Route>
                <Route path="/update-todo/:id" component={TodoComponent}></Route>

            </Switch>
        </BrowserRouter>
    </>)
}

export default App;