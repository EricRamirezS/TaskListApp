package com.ericramirezs.tasklistapp.service;

import com.ericramirezs.tasklistapp.entity.TaskEntity;
import com.ericramirezs.tasklistapp.repository.TaskRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskServiceImpl taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void givenTaskId_whenGetById_thenReturnTask() {
        // Given
        TaskEntity task = new TaskEntity();
        task.setId(1L);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // When
        Optional<TaskEntity> found = taskService.getById(1L);

        // Then
        assertTrue(found.isPresent(), "Expected task to be found");
        assertEquals(task.getId(), found.get().getId(), "Expected correct task ID");
    }

    @Test
    public void givenNoTasks_whenGetAll_thenReturnEmptyCollection() {
        // Given
        when(taskRepository.findAllByOrderByActiveDescCreatedAtDesc()).thenReturn(List.of());

        // When
        Collection<TaskEntity> tasks = taskService.getAll();

        // Then
        assertTrue(tasks.isEmpty(), "Expected empty collection of tasks");
    }

    @Test
    public void givenTask_whenCreate_thenTaskCreated() {
        // Given
        TaskEntity task = new TaskEntity();

        // When
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(task);
        TaskEntity createdTask = taskService.create(task);

        // Then
        assertNotNull(createdTask, "Expected task to be created");
    }

    @Test
    public void givenTask_whenUpdate_thenTaskUpdated() {
        // Given
        TaskEntity task = new TaskEntity();
        task.setId(1L);

        // When
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(task);
        TaskEntity updatedTask = taskService.update(task);

        // Then
        assertNotNull(updatedTask, "Expected task to be updated");
    }

    @Test
    public void givenTaskId_whenDeleteById_thenTaskDeleted() {
        // Given
        long taskId = 1L;

        // When
        taskService.deleteById(taskId);

        // Then
        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    public void whenDeleteAll_thenAllTasksDeleted() {
        // When
        taskService.deleteAll();

        // Then
        verify(taskRepository, times(1)).deleteAll();
    }

    @Test
    public void givenTaskWithId_whenCreate_thenThrowPersistenceException() {
        // Given
        TaskEntity task = new TaskEntity();
        task.setId(1L);

        // Then
        assertThrows(PersistenceException.class, () -> taskService.create(task));
    }

    @Test
    public void givenTaskWithoutId_whenUpdate_thenThrowPersistenceException() {
        // Given
        TaskEntity task = new TaskEntity();

        // Then
        assertThrows(PersistenceException.class, () -> taskService.update(task));
    }
}
