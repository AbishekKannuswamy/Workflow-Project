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
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "project_members")
public class ProjectMembers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_member_id")
	private Long projectmemberId;

	@NotBlank
	@Column(name = "email")
	private String email;

	@NotBlank
	@Column(name = "role")
	private String role;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "scrum_team_id", referencedColumnName = "scrum_team_id")
	@JsonBackReference
	@ToString.Exclude
	private ScrumTeams scrumTeam;

	public Long getProjectmemberId() {
		return projectmemberId;
	}

	public void setProjectmemberId(Long projectmemberId) {
		this.projectmemberId = projectmemberId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ScrumTeams getScrumTeam() {
		return scrumTeam;
	}

	public void setScrumTeam(ScrumTeams scrumTeam) {
		this.scrumTeam = scrumTeam;
	}

}
