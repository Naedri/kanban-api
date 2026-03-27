package com.naedri.kanban_api.mapper;

import com.naedri.kanban_api.domain.CreateTaskRequest;
import com.naedri.kanban_api.domain.dto.CreateTaskRequestDto;
import com.naedri.kanban_api.domain.dto.TaskDto;
import com.naedri.kanban_api.domain.entity.Task;

public interface TaskMapper {
    CreateTaskRequest fromDto(CreateTaskRequestDto dto);

    TaskDto toDto(Task task);
}
