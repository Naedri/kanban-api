package com.naedri.kanban_api.mapper.impl;

import com.naedri.kanban_api.domain.CreateTaskRequest;
import com.naedri.kanban_api.domain.dto.CreateTaskRequestDto;
import com.naedri.kanban_api.domain.dto.TaskDto;
import com.naedri.kanban_api.domain.entity.Task;
import com.naedri.kanban_api.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public CreateTaskRequest fromDto(CreateTaskRequestDto dto) {
        return new CreateTaskRequest(dto.title(), dto.description(), dto.dueDate(), dto.priority());
    }

    @Override
    public TaskDto toDto(Task task) {
        if (null == task) return null;
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(), task.getTaskPriority(), task.getTaskStatus());
    }

}
