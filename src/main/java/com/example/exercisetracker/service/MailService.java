package com.example.exercisetracker.service;

import com.example.exercisetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;

@Service
public class MailService {
	private TemplateEngine templateEngine;
	private JavaMailSender mailSender;

	@Autowired
	public MailService(TemplateEngine templateEngine, JavaMailSender mailSender) {
		this.templateEngine = templateEngine;
		this.mailSender = mailSender;
	}

	public void sendAccountVerification(User user) {
		this.mailSender.send(mimeMessage -> {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
			message.setFrom("system.administrator@example.com");
			message.setTo(user.getEmail());
			message.setSubject("Please verify your account");

			Context context = new Context();
			context.setVariable("name", user.getFullName());
			context.setVariable("token", user.getVerificationToken().getValue());

			String content = this.templateEngine.process("mail/verification", context);

			message.setText(content);
		});
	}
}
