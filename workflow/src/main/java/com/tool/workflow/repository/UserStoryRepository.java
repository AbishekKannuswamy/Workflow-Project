package com.tool.workflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tool.workflow.model.UserStories;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStories, Long> {

	Boolean existsByUserStoryTitle(String userStoryTitle);

	Optional<UserStories> findByUserStoryTitle(String userStoryTitle);
}
