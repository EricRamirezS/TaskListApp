package com.ericramirezs.tasklistapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

/**
 * Represents a task entity in the task list application.
 * This class is mapped to the "tasks" table in the database.
 * It includes fields for the task's ID, description, creation timestamp, and active status.
 * Utilizes JPA annotations for ORM mapping and Lombok annotations for boilerplate code reduction.
 * Includes validation constraints to ensure data integrity.
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class TaskEntity {

    /**
     * The unique identifier for a task.
     * Uses a sequence generator for ID generation.
     */
    @Id
    @SequenceGenerator(name = "tasks_id_seq", sequenceName = "tasks_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_id_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * A brief description of the task.
     * The description cannot be null and has a maximum length of 60 characters.
     */
    @Size(max = 60)
    @NotNull
    @Column(name = "description", nullable = false, length = 60)
    private String description;

    /**
     * The timestamp when the task was created.
     * Automatically set when the task entity is created and cannot be updated.
     */
    @NotNull
    @CreationTimestamp
    @Column(name = "created_at",
            updatable = false,
            nullable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    /**
     * Indicates whether the task is active.
     * Cannot be null.
     */
    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
    }
}
