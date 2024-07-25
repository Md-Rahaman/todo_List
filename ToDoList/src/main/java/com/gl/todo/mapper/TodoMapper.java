package com.gl.todo.mapper;

import com.gl.todo.dto.TodoDto;
import com.gl.todo.entity.Todo;

public class TodoMapper {
	
	public static TodoDto mapToTodoDto(Todo todo) {
		
		return new TodoDto(todo.getTaskId(), todo.getTaskTitle(), todo.getTaskDescription(), todo.isTaskStatus());	
	
	}
	
	public static Todo mapToTodo(TodoDto todoDto) {
		return new Todo(todoDto.getTaskId(), todoDto.getTaskTitle(), todoDto.getTaskDescription(), todoDto.isTaskStatus());
		
	}

}
