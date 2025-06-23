package com.tool.workflow.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@Table(name = "project", uniqueConstraints = { @UniqueConstraint(columnNames = "projectName") })
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Long projectId;

	@NotBlank
	@Column(name = "project_name")
	private String projectName;

	@NotBlank
	@Column(name = "scrum_master_code")
	private String scrumMasterCode;

	@NotBlank
	@Column(name = "SDET_Engineer_code")
	private String sDETEngineerCode;

	@NotBlank
	@Lob
	@Column(name = "project_description")
	private String projectDescription;

	@Column(name = "project_status")
	@NotBlank
	private String projectStatus;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonBackReference
	@ToString.Exclude
	private User user;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;

	public Project() {

	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getScrumMasterCode() {
		return scrumMasterCode;
	}

	public void setScrumMasterCode(String scrumMasterCode) {
		this.scrumMasterCode = scrumMasterCode;
	}

	public String getsDETEngineerCode() {
		return sDETEngineerCode;
	}

	public void setsDETEngineerCode(String sDETEngineerCode) {
		this.sDETEngineerCode = sDETEngineerCode;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Project(Long projectId, @NotBlank @Size(max = 20) String projectName, @NotBlank String scrumMasterCode,
			@NotBlank String sDETEngineerCode, @NotBlank String projectDescription, User user,
			LocalDateTime createdDate, LocalDateTime updatedDate) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.scrumMasterCode = scrumMasterCode;
		this.sDETEngineerCode = sDETEngineerCode;
		this.projectDescription = projectDescription;
		this.user = user;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Project(@NotBlank @Size(max = 20) String projectName, @NotBlank String projectDescription) {

		this.projectName = projectName;
		this.projectDescription = projectDescription;
	}

}
