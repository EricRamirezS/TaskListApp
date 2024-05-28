package com.ericramirezs.tasklistapp.repository;

import com.ericramirezs.tasklistapp.entity.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import static com.ericramirezs.tasklistapp.utils.TaskHelper.createTask;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TestEntityManager entityManager;

    /**
     * Test case to verify that an empty repository returns an empty collection.
     */
    @Test
    public void givenEmptyRepository_whenFindAllByOrderByActiveDescCreatedAtDesc_thenEmptyCollection() {
        // Given an empty repository

        // When
        Collection<TaskEntity> tasks = taskRepository.findAllByOrderByActiveDescCreatedAtDesc();

        // Then
        assertTrue(tasks.isEmpty(), "Expected an empty collection from an empty repository");
    }

    /**
     * Test case to verify that the repository returns tasks in the correct order.
     */
    @Test
    public void givenTasksInRepository_whenFindAllByOrderByActiveDescCreatedAtDesc_thenOrderedCollection() {
        // Given
        TaskEntity task1 = createTask("Task 1", true, OffsetDateTime.now().minusDays(1));
        TaskEntity task2 = createTask("Task 2", false, OffsetDateTime.now().plusDays(1));
        TaskEntity task3 = createTask("Task 3", true, OffsetDateTime.now());
        entityManager.persist(task1);
        entityManager.persist(task2);
        entityManager.persist(task3);
        entityManager.flush();

        String[] expectedOrder = {"Task 3", "Task 1", "Task 2"};

        // When
        Collection<TaskEntity> tasks = taskRepository.findAllByOrderByActiveDescCreatedAtDesc();

        // Then
        assertEquals(3, tasks.size(), "Expected 3 tasks in the repository");
        assertTrue(tasks.contains(task1), "Expected Task 1 in the repository");
        assertTrue(tasks.contains(task2), "Expected Task 2 in the repository");
        assertTrue(tasks.contains(task3), "Expected Task 3 in the repository");
        int i = 0;
        for (TaskEntity taskEntity : tasks) {
            String expectedTask = expectedOrder[i++];
            assertEquals(expectedTask, taskEntity.getDescription(),
                    "Expected %s to be the element N°%d in the collection".formatted(expectedTask, i));
        }
    }

    @Test
    public void givenTaskInRepository_whenFindById_thenTaskReturned() {
        // Given
        TaskEntity task = createTask("Task 1", true);
        entityManager.persist(task);
        entityManager.flush();

        // When
        Optional<TaskEntity> found = taskRepository.findById(task.getId());

        // Then
        assertTrue(found.isPresent(), "Expected task to be found");
        assertEquals(task.getDescription(), found.get().getDescription(), "Expected correct task description");
        assertEquals(task.getActive(), found.get().getActive(), "Expected correct activity status");
    }


    @Test
    public void givenAnotherTaskInRepository_whenFindById_thenNoTaskReturned() {
        // Given
        TaskEntity task = createTask("Task 1", true);
        entityManager.persist(task);
        entityManager.flush();

        // When
        Optional<TaskEntity> found = taskRepository.findById(task.getId() + 1); // Using wrong ID

        // Then
        assertFalse(found.isPresent(), "Expected task not to be found with wrong ID");
    }

    @Test
    public void givenTaskInRepository_whenDeleteById_thenTaskDeleted() {
        // Given
        TaskEntity task = createTask("Task 1", true);
        entityManager.persist(task);
        entityManager.flush();

        // When
        taskRepository.deleteById(task.getId());

        // Then
        Optional<TaskEntity> deletedTask = taskRepository.findById(task.getId());
        assertFalse(deletedTask.isPresent(), "Expected task to be deleted");
    }


    @Test
    public void givenTasksInRepository_whenDeleteAll_thenAllTasksDeleted() {
        // Given
        TaskEntity task1 = createTask("Task 1", true);
        TaskEntity task2 = createTask("Task 2", false);
        entityManager.persist(task1);
        entityManager.persist(task2);
        entityManager.flush();

        // When
        taskRepository.deleteAll();

        // Then
        assertTrue(taskRepository.findAll().isEmpty(), "Expected all tasks to be deleted");
    }
}