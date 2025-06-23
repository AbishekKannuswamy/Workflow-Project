package com.tool.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tool.workflow.model.ScrumTeams;

@Repository
public interface ScrumTeamsRepository extends JpaRepository<ScrumTeams, Long> {

}
