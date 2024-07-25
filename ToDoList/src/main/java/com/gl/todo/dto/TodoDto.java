package com.gl.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDto {

	private int taskId;

	private String taskTitle;

	private String taskDescription;

	private boolean taskStatus;

}
