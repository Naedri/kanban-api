package com.naedri.kanban_api.domain.model;

import com.naedri.kanban_api.domain.enums.TaskPriority;
import com.naedri.kanban_api.domain.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Models a task the user plans to do.
 */
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus taskStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private TaskPriority taskPriority;

    @Column(name = "created", nullable = false, updatable = false)
    private Instant created;

    @Column(name = "updated", nullable = false)
    private Instant updated;

    public static Task create(
            String title,
            String description,
            LocalDate dueDate,
            TaskPriority priority) {
        Instant now = Instant.now();

        Task task = new Task();
        task.id = UUID.randomUUID();
        task.title = title;
        task.description = description;
        task.dueDate = dueDate;
        task.taskStatus = TaskStatus.OPEN;
        task.taskPriority = priority;
        task.created = now;
        task.updated = now;

        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

