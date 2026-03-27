package com.naedri.kanban_api.domain.dto;


import com.naedri.kanban_api.domain.entity.TaskPriority;
import com.naedri.kanban_api.domain.entity.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

/**
 * A DTO representing a task.
 * This class is owned by the presentation layer.
 */
public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority,
        TaskStatus status) {
}
