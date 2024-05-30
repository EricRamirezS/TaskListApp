package com.ericramirezs.tasklistapp.repository;

import com.ericramirezs.tasklistapp.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repository interface for {@link TaskEntity}.
 * Extends {@link JpaRepository} to provide CRUD operations and custom query methods for TaskEntity.
 */
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    /**
     * Finds all task entities ordered by their active status in descending order
     * and then by their creation timestamp in descending order.
     *
     * @return a collection of {@link TaskEntity} objects sorted by active status and creation timestamp.
     */
    Collection<TaskEntity> findAllByOrderByActiveDescCreatedAtDesc();
}
