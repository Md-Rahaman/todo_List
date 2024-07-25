package com.gl.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.todo.dto.TodoDto;
import com.gl.todo.service.TodoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@PostMapping
	public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto){
		TodoDto savedTodo=todoService.createTodo(todoDto);
		return new ResponseEntity<TodoDto>(savedTodo,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos(){
		List<TodoDto> todos=todoService.getAllTodos();
		return new ResponseEntity<List<TodoDto>>(todos,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<TodoDto> deleteTodoById(@PathVariable("id") int id) {
		todoService.deleteTodo(id);
		return new ResponseEntity("Deleted Succesfully",HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodoById(@PathVariable("id") int id,@RequestBody TodoDto todoDto){
		TodoDto dto=todoService.updateTodoById(id, todoDto);
		return new ResponseEntity<TodoDto>(dto,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") int id){
		TodoDto dto=todoService.getTodoById(id);
		return new ResponseEntity<TodoDto>(dto,HttpStatus.OK);
	}
	

	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") int id){
		TodoDto dto=todoService.completeTodo(id);
		return new ResponseEntity<TodoDto>(dto,HttpStatus.OK);
	}
	
	@PatchMapping("{id}/in-complete")
	public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") int id){
		TodoDto dto=todoService.inCompleteTodo(id);
		return new ResponseEntity<TodoDto>(dto,HttpStatus.OK);
	}

}
