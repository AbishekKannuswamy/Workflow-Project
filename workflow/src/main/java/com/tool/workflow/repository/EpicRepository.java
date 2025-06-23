package com.tool.workflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tool.workflow.model.Epic;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Long> {

	Boolean existsByTitle(String title);

	Optional<Epic> findByTitle(String title);
}
