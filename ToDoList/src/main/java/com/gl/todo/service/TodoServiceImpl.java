package com.gl.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.todo.dto.TodoDto;
import com.gl.todo.entity.Todo;
import com.gl.todo.exception.ResourceNotFoundException;
import com.gl.todo.mapper.TodoMapper;
import com.gl.todo.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService{

	@Autowired
	TodoRepository todoRepository;


	@Override
	public TodoDto createTodo(TodoDto todoDto) {
		Todo todo=TodoMapper.mapToTodo(todoDto);
		Todo savedTodo=todoRepository.save(todo);
		return TodoMapper.mapToTodoDto(savedTodo);
	}

	@Override
	public TodoDto getTodoById(int id) {
		Todo todo=todoRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee With Id "+id+" Not Found"));
		return TodoMapper.mapToTodoDto(todo);
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todos=todoRepository.findAll();
		return todos.stream().map((todo)->TodoMapper.mapToTodoDto(todo)).collect(Collectors.toList());

	}

	@Override
	public TodoDto updateTodoById(int id, TodoDto todoDto) {
		Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee With Id "+id+" Not Found"));
		todo.setTaskDescription(todoDto.getTaskDescription());
		todo.setTaskStatus(todoDto.isTaskStatus());
		todo.setTaskTitle(todoDto.getTaskTitle());
		todoRepository.save(todo);
		return TodoMapper.mapToTodoDto(todo);
	}

	@Override
	public void deleteTodo(int id) {
		Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee With Id "+id+" Not Found"));
		todoRepository.deleteById(id);
	}

	@Override
	public TodoDto completeTodo(int id) {

		Todo todo=todoRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee With Id "+id+" Not Found"));
		todo.setTaskStatus(true);
		Todo dto=todoRepository.save(todo);
		return TodoMapper.mapToTodoDto(todo);
	}

	@Override
	public TodoDto inCompleteTodo(int id) {
		Todo todo=todoRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee With Id "+id+" Not Found"));
		todo.setTaskStatus(false);
		Todo dto=todoRepository.save(todo);
		return TodoMapper.mapToTodoDto(todo);
	}

}
