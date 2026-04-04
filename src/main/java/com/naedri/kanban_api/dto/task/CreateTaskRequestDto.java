package com.naedri.kanban_api.dto.task;

import com.naedri.kanban_api.domain.enums.TaskPriority;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

/**
 * An object the Rest API will accept to create a task (calling the service layer)
 * This class is owned by the presentation layer.
 * Its constraints allow to validate it.
 */
public record CreateTaskRequestDto(
        @NotBlank(message = ERROR_MESSAGE_TITLE_LENGTH)
        @Length(max = 255)
        String title,
        @Length(max = 1000, message = ERROR_MESSAGE_DESCRIPTION_LENGTH)
        @Nullable
        String description,
        @NotNull(message = ERROR_MESSAGE_TASK_PRIORITY)
        TaskPriority priority,
        @FutureOrPresent(message = ERROR_MESSAGE_DUE_DATE_VALUE)
        @Nullable
        LocalDate dueDate

) {
    private static final String ERROR_MESSAGE_TITLE_LENGTH = "Title must be between 1 and 255 characters.";
    private static final String ERROR_MESSAGE_DESCRIPTION_LENGTH = "Description must be less than 1000 characters.";
    private static final String ERROR_MESSAGE_TASK_PRIORITY = "Task priority must not be empty.";
    private static final String ERROR_MESSAGE_DUE_DATE_VALUE = "Due cate must be the future.";

}
