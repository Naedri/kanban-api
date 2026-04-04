package com.naedri.kanban_api.mapper;

import com.naedri.kanban_api.domain.model.Task;
import com.naedri.kanban_api.dto.task.CreateTaskRequest;
import com.naedri.kanban_api.dto.task.TaskResponse;

public interface TaskMapper {
    Task fromDto(CreateTaskRequest dto);

    TaskResponse toDto(Task task);
}
