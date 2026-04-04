package com.naedri.kanban_api.controller;

import com.naedri.kanban_api.domain.model.Task;
import com.naedri.kanban_api.dto.task.CreateTaskRequest;
import com.naedri.kanban_api.dto.task.TaskResponse;
import com.naedri.kanban_api.mapper.TaskMapper;
import com.naedri.kanban_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/rest/v1/task")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid
            @RequestBody
            CreateTaskRequest createTaskRequest) {
        Task task = taskService.createTask(createTaskRequest);
        TaskResponse createdTaskResponse = taskMapper.toDto(task);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdTaskResponse);
    }
}
