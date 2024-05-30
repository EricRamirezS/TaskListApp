package com.ericramirezs.tasklistapp.model.task;

import com.ericramirezs.tasklistapp.entity.TaskEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.util.Collection;

/**
 * Data Transfer Object for a task.
 * Encapsulates the data of a task, including its ID, description, creation timestamp, and active status.
 * Provides a constructor for creating a TaskDto from a TaskEntity and a method for converting a collection of
 * TaskEntity objects to a collection of TaskDto objects.
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class TaskDto {
    /**
     * The unique identifier for the task.
     * Cannot be null.
     */
    @NotNull
    private final Long id;
    /**
     * A brief description of the task.
     * The description cannot be null and must be between 1 and 60 characters in length.
     * Validation messages are provided for size constraints.
     */
    @NotNull
    @Size(min = 1, message = "{validation.name.size.too_short}")
    @Size(max = 60, message = "{validation.name.size.too_long}")
    private final String description;
    /**
     * The timestamp when the task was created.
     * Cannot be null.
     */
    @NotNull
    private final OffsetDateTime createdAt;
    /**
     * Indicates whether the task is active.
     * Cannot be null.
     */
    @NotNull
    private final Boolean active;

    /**
     * Constructs a TaskDto from a TaskEntity.
     *
     * @param taskEntity the TaskEntity to convert
     */
    public TaskDto(@NotNull TaskEntity taskEntity) {
        this.id = taskEntity.getId();
        this.description = taskEntity.getDescription();
        this.createdAt = taskEntity.getCreatedAt();
        this.active = taskEntity.getActive();
    }

    /**
     * Converts a collection of TaskEntity objects to a collection of TaskDto objects.
     *
     * @param entities the collection of TaskEntity objects to convert
     * @return a collection of TaskDto objects
     */
    public static Iterable<TaskDto> ofEntityList(@NotNull Collection<TaskEntity> entities) {
        return entities.stream().map(TaskDto::new).toList();
    }
}
