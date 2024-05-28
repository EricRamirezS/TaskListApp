package com.ericramirezs.tasklistapp.model.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for updating a task.
 * Encapsulates the data required to update an existing task, including validation constraints.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDto {
    /**
     * A brief description of the task.
     * The description cannot be null and must be between 1 and 60 characters in length.
     * Validation messages are provided for size constraints.
     */
    @NotNull
    @Size(min = 1, message = "{validation.name.size.too_short}")
    @Size(max = 60, message = "{validation.name.size.too_long}")
    @Schema(minLength = 1, maxLength = 60, example = "I'm a task")
    private String description;
    /**
     * Indicates whether the task is active.
     * Cannot be null.
     */
    @NotNull
    private Boolean active;
}
