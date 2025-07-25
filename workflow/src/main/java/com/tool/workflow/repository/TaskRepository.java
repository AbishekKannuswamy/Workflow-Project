package com.tool.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tool.workflow.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
