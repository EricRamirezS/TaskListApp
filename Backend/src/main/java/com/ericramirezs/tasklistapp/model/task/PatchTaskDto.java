package com.ericramirezs.tasklistapp.model.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for updating a task.
 * Allows partial updates to a task's description and active status.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatchTaskDto {

    /**
     * A brief description of the task.
     * The description must be between 1 and 60 characters in length if provided.
     * Validation messages are provided for size constraints.
     */
    @Size(min = 1, message = "{validation.name.size.too_short}")
    @Size(max = 60, message = "{validation.name.size.too_long}")
    @Schema(minLength = 1, maxLength = 60, example = "I'm a task")
    private String description;

    /**
     * Indicates whether the task is active.
     * This field is optional and can be null.
     */
    private Boolean active;
}
