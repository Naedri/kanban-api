package com.naedri.kanban_api.service;

import com.naedri.kanban_api.domain.CreateTaskRequest;
import com.naedri.kanban_api.domain.entity.Task;

public interface TaskService {
    Task createTask(CreateTaskRequest request);
}
