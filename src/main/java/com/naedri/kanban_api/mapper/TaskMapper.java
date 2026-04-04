package com.naedri.kanban_api.mapper;

import com.naedri.kanban_api.domain.model.Task;
import com.naedri.kanban_api.dto.task.CreateTaskRequestDto;
import com.naedri.kanban_api.dto.task.TaskDto;

public interface TaskMapper {
    Task fromDto(CreateTaskRequestDto dto);

    TaskDto toDto(Task task);
}
