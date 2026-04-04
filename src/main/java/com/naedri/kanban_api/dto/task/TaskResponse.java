package com.naedri.kanban_api.dto.task;


import com.naedri.kanban_api.domain.enums.TaskPriority;
import com.naedri.kanban_api.domain.enums.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

/**
 * A DTO representing a task.
 * This class is owned by the presentation layer.
 */
public record TaskResponse(
        UUID id,
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority,
        TaskStatus status) {
}
