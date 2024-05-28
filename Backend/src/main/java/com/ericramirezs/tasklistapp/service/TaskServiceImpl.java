package com.ericramirezs.tasklistapp.service;

import com.ericramirezs.tasklistapp.entity.TaskEntity;
import com.ericramirezs.tasklistapp.repository.TaskRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


/**
 * Service implementation for managing tasks.
 * Provides methods for performing CRUD operations on {@link TaskEntity}.
 * Utilizes {@link TaskRepository} for database interactions.
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TaskEntity> getById(long id) {
        return taskRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<TaskEntity> getAll() {
        return taskRepository.findAllByOrderByActiveDescCreatedAtDesc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskEntity create(@NotNull TaskEntity taskEntity) throws PersistenceException {
        if (taskEntity.getId() == null || taskEntity.getId() == 0) {
            return taskRepository.save(taskEntity);
        } else {
            throw new PersistenceException("New entities should not have id defined");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskEntity update(@NotNull TaskEntity taskEntity) throws PersistenceException {
        if (taskEntity.getId() == null || taskEntity.getId() == 0) {
            throw new PersistenceException("Existing entities should have id defined\n");
        } else {
            return taskRepository.save(taskEntity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(long id) {
        taskRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }
}
