package com.ericramirezs.tasklistapp.web.controller;

import com.ericramirezs.tasklistapp.entity.TaskEntity;
import com.ericramirezs.tasklistapp.model.task.CreateTaskDto;
import com.ericramirezs.tasklistapp.model.task.PatchTaskDto;
import com.ericramirezs.tasklistapp.model.task.TaskDto;
import com.ericramirezs.tasklistapp.model.task.UpdateTaskDto;
import com.ericramirezs.tasklistapp.service.TaskService;
import com.ericramirezs.tasklistapp.util.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller class for managing tasks.
 * Provides RESTful endpoints for performing CRUD operations on tasks.
 */
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Task Management Controller", description = "Controller class for managing tasks with CRUD operations")
public class TaskController {

    private final TaskService taskService;

    /**
     * Retrieves all tasks.
     *
     * @return a ResponseEntity containing a collection of {@link TaskDto} objects, representing all tasks
     */
    @GetMapping
    @Operation(summary = "Get all tasks", description = "Retrieve all tasks stored in the system.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TaskDto.class))))
    public ResponseEntity<Iterable<TaskDto>> getTasks() {
        return ResponseEntity.ok(TaskDto.ofEntityList(taskService.getAll()));
    }

    /**
     * Creates a new task.
     *
     * @param taskDto the data of the task to create
     * @return a ResponseEntity containing the created {@link TaskDto} object
     */
    @PostMapping
    @Operation(summary = "Create a new task", description = "Create a new task with the provided details.")
    @ApiResponse(
            responseCode = "201",
            description = "Task created successfully",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class))})
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskDto taskDto) {
        TaskEntity task = TaskEntity.builder()
                .description(taskDto.getDescription())
                .active(true).build();

        task = taskService.create(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskDto(task));
    }

    /**
     * Deletes all tasks.
     *
     * @return a ResponseEntity containing the collection of deleted {@link TaskDto} objects
     */
    @DeleteMapping
    @Operation(summary = "Delete all tasks", description = "Delete all tasks stored in the system.")
    public ResponseEntity<Iterable<TaskDto>> deleteAllTask() {
        Iterable<TaskDto> tasks = TaskDto.ofEntityList(taskService.getAll());

        taskService.deleteAll();

        return ResponseEntity.ok(tasks);
    }

    /**
     * Retrieves a task by its unique identifier.
     *
     * @param id the unique identifier of the task
     * @return a ResponseEntity containing the {@link TaskDto} object if found, or 404 Not Found status otherwise
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a task by ID", description = "Retrieve a task by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Task found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskDto.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Task not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        TaskEntity task = taskService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(new TaskDto(task));
    }

    /**
     * Updates an existing task.
     *
     * @param id      the unique identifier of the task to update
     * @param taskDto the data to update the task with
     * @return a ResponseEntity containing the updated {@link TaskDto} object
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a task by ID", description = "Update an existing task with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Task updated successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskDto.class))}),
            @ApiResponse(
            responseCode = "404",
            description = "Task not found",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskDto taskDto) {
        TaskEntity task = taskService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        task.setDescription(taskDto.getDescription());
        task.setActive(taskDto.getActive());
        task = taskService.update(task);

        return ResponseEntity.ok(new TaskDto(task));
    }

    /**
     * Partially updates an existing task.
     *
     * @param id      the unique identifier of the task to update
     * @param taskDto the data to patch the task with
     * @return a ResponseEntity containing the patched {@link TaskDto} object
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Partially update a task by ID", description =
            "Partially update an existing task with the " + "provided details.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Task patched successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskDto.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Task not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity<TaskDto> patchTask(@PathVariable Long id, @Valid @RequestBody PatchTaskDto taskDto) {
        TaskEntity task = taskService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        TaskEntity patchData = TaskEntity.builder()
                .description(taskDto.getDescription())
                .active(taskDto.getActive())
                .build();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(patchData, task);

        taskService.update(task);

        return ResponseEntity.ok(new TaskDto(task));
    }

    /**
     * Deletes a task by its unique identifier.
     *
     * @param id the unique identifier of the task to delete
     * @return a ResponseEntity containing the deleted {@link TaskDto} object if found, or 404 Not Found status
     * otherwise
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task by ID", description = "Delete a task by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Task deleted successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskDto.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Task not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity<TaskDto> deleteTask(@PathVariable Long id) {
        TaskEntity task = taskService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        taskService.deleteById(task.getId());

        return ResponseEntity.ok(new TaskDto(task));
    }
}
