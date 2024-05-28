package com.ericramirezs.tasklistapp.web.controller;

import com.ericramirezs.tasklistapp.entity.TaskEntity;
import com.ericramirezs.tasklistapp.model.task.CreateTaskDto;
import com.ericramirezs.tasklistapp.model.task.PatchTaskDto;
import com.ericramirezs.tasklistapp.model.task.UpdateTaskDto;
import com.ericramirezs.tasklistapp.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static com.ericramirezs.tasklistapp.utils.TaskHelper.createTask;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void givenTasks_whenGetTasks_thenReturnJsonArray() throws Exception {
        // Given
        TaskEntity task1 = createTask(1L, "Task 1");
        TaskEntity task2 = createTask(2L, "Task 2");

        Collection<TaskEntity> tasks = Arrays.asList(task1, task2);

        // When
        when(taskService.getAll()).thenReturn(tasks);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Task 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("Task 2"));
    }

    @Test
    public void givenTaskDto_whenCreateTask_thenReturnCreatedTaskDto() throws Exception {
        // Given
        CreateTaskDto createTaskDto = new CreateTaskDto();
        createTaskDto.setDescription("New Task");

        TaskEntity task = createTask(1L, "New Task");

        ObjectMapper objectMapper = new ObjectMapper();

        // When
        when(taskService.create(any(TaskEntity.class))).thenReturn(task);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTaskDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("New Task"));
    }

    @Test
    public void givenTaskExists_whenGetTaskById_thenReturnTaskDto() throws Exception {
        // Given
        TaskEntity task = createTask(1L, "Task 1");

        // When
        when(taskService.getById(1L)).thenReturn(Optional.of(task));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Task 1"));
    }

    @Test
    public void givenTaskDoesNotExist_whenGetTaskById_thenThrowNotFound() throws Exception {
        // Given No Task

        // When
        when(taskService.getById(1L)).thenReturn(Optional.empty());

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void givenUpdatedTaskDto_whenUpdateTask_thenReturnUpdatedTaskDto() throws Exception {
        // Given
        UpdateTaskDto updateTaskDto = new UpdateTaskDto();
        updateTaskDto.setDescription("Updated Task");
        updateTaskDto.setActive(true);

        TaskEntity existingTask = createTask(1L, "Task 1", false);

        TaskEntity updatedTask = createTask(1L, "Updated Task", true);

        ObjectMapper objectMapper = new ObjectMapper();

        // When
        when(taskService.getById(1L)).thenReturn(Optional.of(existingTask));
        when(taskService.update(any(TaskEntity.class))).thenReturn(updatedTask);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTaskDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Updated Task"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active").value(true));
    }

    @Test
    public void givenPatchTaskDto_whenPatchTask_thenReturnPatchedTaskDto() throws Exception {
        // Given
        PatchTaskDto patchTaskDto = new PatchTaskDto();
        patchTaskDto.setDescription("Patched Task");

        TaskEntity existingTask = createTask(1L, "Task 1", true);

        TaskEntity patchedTask = createTask(1L, "Patched Task", true);

        ObjectMapper objectMapper = new ObjectMapper();

        // When
        when(taskService.getById(1L)).thenReturn(Optional.of(existingTask));
        when(taskService.update(any(TaskEntity.class))).thenReturn(patchedTask);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patchTaskDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Patched Task"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active").value(true));
    }


    @Test
    public void givenTaskExists_whenDeleteTaskById_thenReturnDeletedTaskDto() throws Exception {
        // Given
        TaskEntity task = createTask(1L, "Task 1");

        // When
        when(taskService.getById(1L)).thenReturn(Optional.of(task));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Task 1"));
    }

    @Test
    public void givenTaskDoesNotExist_whenDeleteTaskById_thenThrowNotFound() throws Exception {
        //Given No Task

        // When
        when(taskService.getById(1L)).thenReturn(Optional.empty());

        // Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
