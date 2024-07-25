package com.gl.todo.service;

import java.util.List;

import com.gl.todo.dto.TodoDto;

public interface TodoService {

	TodoDto createTodo(TodoDto todoDto);
	
	TodoDto getTodoById(int id);
	
	List<TodoDto> getAllTodos();
	
	TodoDto updateTodoById(int id,TodoDto todoDto);
	
	void deleteTodo(int id);
	
	TodoDto completeTodo(int id);
	
	TodoDto inCompleteTodo(int id);



}
