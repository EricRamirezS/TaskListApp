package com.ericramirezs.tasklistapp.model.task;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for creating a new task.
 * Encapsulates the data required to create a new task, including validation constraints.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTaskDto {

    /**
     * A brief description of the task.
     * The description cannot be null and must be between 1 and 60 characters in length.
     * Validation messages are provided for size constraints.
     */
    @NotNull
    @Size(min = 1, message = "{validation.name.size.too_short}")
    @Size(max = 60, message = "{validation.name.size.too_long}")
    private String description;
}
