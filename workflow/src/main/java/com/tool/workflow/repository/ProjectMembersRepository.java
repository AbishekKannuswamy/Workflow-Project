package com.tool.workflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tool.workflow.model.ProjectMembers;

@Repository
public interface ProjectMembersRepository extends JpaRepository<ProjectMembers, Long> {
	Boolean existsByEmail(String email);

	Optional<ProjectMembers> findByEmail(String email);
}
