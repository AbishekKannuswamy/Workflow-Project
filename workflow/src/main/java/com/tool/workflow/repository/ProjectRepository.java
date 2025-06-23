package com.tool.workflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tool.workflow.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	Boolean existsByProjectName(String projectName);

	Optional<Project> findByProjectName(String projectName);

	Optional<Project> findByProjectId(Long id);
}
