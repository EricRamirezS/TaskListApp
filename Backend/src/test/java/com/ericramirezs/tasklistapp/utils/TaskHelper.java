package com.ericramirezs.tasklistapp.utils;

import com.ericramirezs.tasklistapp.entity.TaskEntity;

import java.time.OffsetDateTime;

/**
 * Helper methods to create a task.
 */
public class TaskHelper {


    public static TaskEntity createTask(String description, boolean isActive, OffsetDateTime dateTime) {
        return TaskEntity.builder()
                .description(description)
                .active(isActive)
                .createdAt(dateTime)
                .build();
    }

    public static TaskEntity createTask(String description, boolean isActive) {
        return createTask(description, isActive, OffsetDateTime.now());
    }

    public static TaskEntity createTask(Long id, String description, boolean isActive, OffsetDateTime dateTime) {
        return TaskEntity.builder()
                .id(id)
                .description(description)
                .active(isActive)
                .createdAt(dateTime)
                .build();
    }

    public static TaskEntity createTask(Long id, String description, boolean isActive) {
        return createTask(id, description, isActive, OffsetDateTime.now());
    }

    public static TaskEntity createTask(Long id, String description) {
        return createTask(id, description, true);
    }
}
