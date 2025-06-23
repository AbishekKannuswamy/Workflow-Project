package com.tool.workflow.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendPasswordResetEmail(String to, String resetUrl) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("WorkFlow: Password Reset Request");
		message.setText("Click the link to reset your password: " + resetUrl);
		mailSender.send(message);
	}

	public void sendProjectAssignmentEmail(Map<String, String> emailAndRole, String scrumMasterOTP,
			String SDETEngineerOTP, String projectName, String projectDescription, String productOwnerName,
			String productOwnerEmail) {

		for (String email : emailAndRole.keySet()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			String role = emailAndRole.get(email);
			if (role.equals("scrumMaster")) {

				message.setSubject("WorkFlow: Project Assignment - Scrum Master");
				String emailBody = String.format(
						"Hello,\n\n" + "You have been assigned to a new project. Here are the details:\n\n"
								+ "Project Name: %s\n" + "Project Description: %s\n\n" + "Your Role: Scrum Master\n"
								+ "Product Owner: %s\n" + "Product Owner Email: %s\n\n"
								+ "Please use the following OTP to register: %s\n\n" + "Best regards,\n" + "WorkFlow",
						projectName, projectDescription, productOwnerName, productOwnerEmail, scrumMasterOTP

				);
				message.setText(emailBody);

				mailSender.send(message);
			} else {
				message.setSubject("WorkFlow: Project Assignment - SDET Engineer");
				String emailBody = String.format(
						"Hello,\n\n" + "You have been assigned to a new project. Here are the details:\n\n"
								+ "Project Name: %s\n" + "Project Description: %s\n\n" + "Your Role: SDET Engineer\n"
								+ "Product Owner: %s\n" + "Product Owner Email: %s\n\n"
								+ "Please use the following OTP to register: %s\n\n" + "Best regards,\n" + "WorkFlow",
						projectName, projectDescription, productOwnerName, productOwnerEmail, SDETEngineerOTP);
				message.setText(emailBody);

				mailSender.send(message);
			}
		}
	}

}