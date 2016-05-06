package edu.sjsu.cmpe275.termprj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import edu.sjsu.cmpe275.termprj.service.EmailSendingService;

@Service
public class EmailSendingServiceImpl implements EmailSendingService {
	
	@Autowired
	private MailSender mailSender;
	
	public void sendEmail(String toEmail, String fromEmail, String subject, String message){
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(fromEmail);
			msg.setTo(toEmail);
			msg.setSubject(subject);
			msg.setText(message);
			mailSender.send(msg);
	}
}
