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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_stories", uniqueConstraints = { @UniqueConstraint(columnNames = "userStoryTitle") })
public class UserStories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userStory_id")
	private Long userStoryId;

	@NotBlank
	@Column(name = "title")
	private String userStoryTitle;

	@NotBlank
	@Column(name = "description", columnDefinition = "TEXT")
	private String userStoryDescription;

	@NotBlank
	@Column(name = "acceptance_criteria", columnDefinition = "TEXT")
	private String userStoryAcceptanceCriteria;

	@NotBlank
	@Column(name = "priority")
	private String userStoryPriority;

	@Column(name = "status")
	private String userStoryStatus;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "epic_id", referencedColumnName = "epic_id")
	@JsonBackReference
	@ToString.Exclude
	private Epic epic;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "sprint_backlog_id", referencedColumnName = "sprint_backlog_id")
	@JsonBackReference
	@ToString.Exclude
	private SprintBacklog sprintBacklog;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;

	public Long getUserStoryId() {
		return userStoryId;
	}

	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	public String getUserStoryTitle() {
		return userStoryTitle;
	}

	public void setUserStoryTitle(String userStoryTitle) {
		this.userStoryTitle = userStoryTitle;
	}

	public String getUserStoryDescription() {
		return userStoryDescription;
	}

	public void setUserStoryDescription(String userStoryDescription) {
		this.userStoryDescription = userStoryDescription;
	}

	public String getUserStoryAcceptanceCriteria() {
		return userStoryAcceptanceCriteria;
	}

	public void setUserStoryAcceptanceCriteria(String userStoryAcceptanceCriteria) {
		this.userStoryAcceptanceCriteria = userStoryAcceptanceCriteria;
	}

	public String getUserStoryPriority() {
		return userStoryPriority;
	}

	public void setUserStoryPriority(String userStoryPriority) {
		this.userStoryPriority = userStoryPriority;
	}

	public String getUserStoryStatus() {
		return userStoryStatus;
	}

	public void setUserStoryStatus(String userStoryStatus) {
		this.userStoryStatus = userStoryStatus;
	}

	public Epic getEpic() {
		return epic;
	}

	public void setEpic(Epic epic) {
		this.epic = epic;
	}

	public SprintBacklog getSprintBacklog() {
		return sprintBacklog;
	}

	public void setSprintBacklog(SprintBacklog sprintBacklog) {
		this.sprintBacklog = sprintBacklog;
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

}
