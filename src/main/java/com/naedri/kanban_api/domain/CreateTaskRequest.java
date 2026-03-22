package com.naedri.kanban_api.domain;

import com.naedri.kanban_api.domain.entity.TaskPriority;

import java.time.LocalDate;

/**
 * TODO think about moving it to kanban_api.dto.task.CreateTaskCommand
 * TODO to highlight it has to be used by the service (business logic not Controller logic)
 * Models a request to create a new task.
 * This class is owned by the service layer.
 */
public record CreateTaskRequest(
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority
) {

}
