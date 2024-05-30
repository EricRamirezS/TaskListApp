package com.ericramirezs.tasklistapp.service;

import com.ericramirezs.tasklistapp.entity.TaskEntity;
import jakarta.persistence.PersistenceException;

import java.util.Collection;
import java.util.Optional;

/**
 * Service interface for managing tasks.
 * Provides methods for performing CRUD operations on {@link TaskEntity}.
 */
public interface TaskService {
    /**
     * Retrieves a task by its unique identifier.
     *
     * @param id the unique identifier of the task
     * @return an {@link Optional} containing the task if found, or empty if not found
     */
    Optional<TaskEntity> getById(long id);

    /**
     * Retrieves all tasks.
     *
     * @return a collection of all {@link TaskEntity} objects
     */
    Collection<TaskEntity> getAll();

    /**
     * Creates a new task.
     *
     * @param taskEntity the task entity to create
     * @return the created {@link TaskEntity}
     * @throws PersistenceException if there is an error during the creation process
     */
    TaskEntity create(TaskEntity taskEntity) throws PersistenceException;

    /**
     * Updates an existing task.
     *
     * @param taskEntity the task entity to update
     * @return the updated {@link TaskEntity}
     * @throws PersistenceException if there is an error during the update process
     */
    TaskEntity update(TaskEntity taskEntity) throws PersistenceException;

    /**
     * Deletes a task by its unique identifier.
     *
     * @param id the unique identifier of the task to delete
     */
    void deleteById(long id);

    /**
     * Deletes all tasks.
     */
    void deleteAll();
}
