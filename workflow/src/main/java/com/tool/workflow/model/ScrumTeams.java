package com.tool.workflow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "scrum_teams", uniqueConstraints = { @UniqueConstraint(columnNames = "scrumTeamNamepo") })

public class ScrumTeams {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scrum_team_id")
	private Long scrumTeamId;

	@NotBlank
	@Column(name = "scrum_team_name")
	private String scrumTeamName;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "project_id", referencedColumnName = "project_id")
	@JsonBackReference
	@ToString.Exclude
	private Project project;

	public ScrumTeams() {

	}

	public Long getScrumTeamId() {
		return scrumTeamId;
	}

	public void setScrumTeamId(Long scrumTeamId) {
		this.scrumTeamId = scrumTeamId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getScrumTeamName() {
		return scrumTeamName;
	}

	public void setScrumTeamName(String scrumTeamName) {
		this.scrumTeamName = scrumTeamName;
	}

	public ScrumTeams(Project project, String scrumTeamName) {
		super();
		this.project = project;
		this.scrumTeamName = scrumTeamName;
	}

}
