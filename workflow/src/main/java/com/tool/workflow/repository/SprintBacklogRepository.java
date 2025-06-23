package com.tool.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tool.workflow.model.SprintBacklog;

@Repository
public interface SprintBacklogRepository extends JpaRepository<SprintBacklog, Long> {

}
