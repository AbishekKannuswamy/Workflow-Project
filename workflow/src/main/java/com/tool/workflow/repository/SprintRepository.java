package com.tool.workflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tool.workflow.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

	Optional<Sprint> findBySprintStatus(String sprintStatus);

	Boolean existsBySprintStatus(String sprintStatus);
}
