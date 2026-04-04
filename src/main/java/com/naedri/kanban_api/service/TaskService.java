package com.naedri.kanban_api.service;

import com.naedri.kanban_api.domain.model.Task;
import com.naedri.kanban_api.dto.task.CreateTaskRequestDto;

public interface TaskService {
    Task createTask(CreateTaskRequestDto dto);
}
