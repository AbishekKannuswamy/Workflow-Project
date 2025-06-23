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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sprint_backlog")
public class SprintBacklog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sprint_backlog_id")
	private Long sprintBacklogId;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "sprint_id", referencedColumnName = "sprint_id")
	@JsonBackReference
	@ToString.Exclude
	private Sprint sprint;

	public Long getSprintBacklogId() {
		return sprintBacklogId;
	}

	public void setSprintBacklogId(Long sprintBacklogId) {
		this.sprintBacklogId = sprintBacklogId;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

}
