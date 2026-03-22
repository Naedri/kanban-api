package com.naedri.kanban_api.service.impl;

import com.naedri.kanban_api.domain.CreateTaskRequest;
import com.naedri.kanban_api.domain.entity.Task;
import com.naedri.kanban_api.domain.entity.TaskStatus;
import com.naedri.kanban_api.repository.TaskRepository;
import com.naedri.kanban_api.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(CreateTaskRequest request) {
        Instant now = Instant.now();
        Task task = new Task(
                null,
                request.title(),
                request.description(),
                request.dueDate(),
                TaskStatus.OPEN,
                request.priority(),
                now,
                now
        );
        return taskRepository.save(task);
    }
}
