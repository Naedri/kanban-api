package com.naedri.kanban_api.mapper.impl;

import com.naedri.kanban_api.domain.model.Task;
import com.naedri.kanban_api.dto.task.CreateTaskRequestDto;
import com.naedri.kanban_api.dto.task.TaskDto;
import com.naedri.kanban_api.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(CreateTaskRequestDto dto) {

        if (dto == null) {
            return null;
        }

        return Task.create(
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.priority()
        );
    }

    @Override
    public TaskDto toDto(Task task) {

        if (task == null) {
            return null;
        }

        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getTaskPriority(),
                task.getTaskStatus()
        );
    }
}