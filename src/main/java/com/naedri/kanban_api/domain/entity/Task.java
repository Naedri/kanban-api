package com.naedri.kanban_api.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Key Improvements Explained
 * 1. Removal of Duplicate Code
 * I removed the manual constructors, toString, and equals/hashCode.
 * <p>
 * The Trap: You had @NoArgsConstructor and @AllArgsConstructor AND manual versions of the same constructors. This is redundant.
 * <p>
 * Lombok's Role: If you use the annotations, delete the manual code. It makes the file 60% shorter and 100% easier to read.
 * <p>
 * 2. The @Builder.Default Fix
 * In your original code, if you used Task.builder().title("Test").build(), the taskStatus would have been null instead of OPEN.
 * <p>
 * Why? The Builder pattern ignores field initializations unless you add @Builder.Default.
 * <p>
 * 3. Optimized toString
 * Using @ToString(onlyExplicitlyIncluded = true) is a professional best practice for JPA.
 * <p>
 * Why? If you later add a List<Comment> comments to this Task, a default toString would try to load all comments from the database every time you log a Task. This causes "LazyInitializationExceptions" or massive performance hits. Including only the id and title is safer.
 * <p>
 * 4. The equals/hashCode Dilemma
 * I kept a simplified version of your equals.
 * <p>
 * Rule of thumb: In JPA, never use @Data or @EqualsAndHashCode on all fields. It breaks when entities are put into Sets (like HashSet) before they are saved to the DB (because the ID is null).
 * <p>
 * Best Practice: Compare only by the Business Key or the ID (if it's not null).
 */


/**
 * Models a task for the Kanban board.
 * Uses JPA Auditing to automatically manage timestamps.
 */
@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class) // Enables automatic created/updated dates@Getter
@Setter
@NoArgsConstructor // Required by JPA
@AllArgsConstructor // For your custom builders/constructors
@Builder // Useful for creating tasks in tests or services
@ToString(onlyExplicitlyIncluded = true) // Safer for JPA (prevents lazy loading loops)
public class Task {

    @Builder.Default // Required so Lombok Builder doesn't ignore the default value
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private final TaskStatus taskStatus = TaskStatus.OPEN;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private final TaskPriority taskPriority = TaskPriority.MEDIUM;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    @ToString.Include // Only include the ID in toString for performance/safety
    private UUID id;
    @Column(name = "title", nullable = false)
    @ToString.Include
    private String title;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    private Instant created;

    @LastModifiedDate
    @Column(name = "updated", nullable = false)
    private Instant updated;

    // --- Why I removed manual equals/hashCode ---
    // For JPA Entities, comparing by ID is correct.
    // If you prefer Lombok to do it, use @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    // and mark @EqualsAndHashCode.Include on the id field.

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return id != null && id.equals(task.id);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}