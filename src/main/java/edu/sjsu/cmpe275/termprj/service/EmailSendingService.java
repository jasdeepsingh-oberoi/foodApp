package edu.sjsu.cmpe275.termprj.service;

public interface EmailSendingService {
	public void sendEmail(String toEmail, String fromEmail, String subject, String message);
}
