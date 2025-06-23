package com.tool.workflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tool.workflow.model.AppRole;
import com.tool.workflow.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleName(AppRole appRole);

}
