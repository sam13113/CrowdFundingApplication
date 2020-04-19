
package com.spring.crowdfunding.navsam.service;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.spring.crowdfunding.navsam.entity.UserAccount;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * Nr.   Name       Date         Release/Description
 *---------------------------------------------------
 * 02
 * 01   Sarat     20 04 2020      New Class
 *
 * @author Sarat
 *
 *
 */
@Service
@Slf4j
@Getter
public class MailService {
	private static final String USER = "user";

	private static final String BASE_URL = "baseUrl";

	private static final String BASE_URL_PATH = "app.base.url.path";

	private static final String MAIL_FROM_PATH = "app.mail.from";

	private Environment env;

	private final JavaMailSender javaMailSender;

	private final MessageSource messageSource;

	private final SpringTemplateEngine templateEngine;

	@Autowired
	public MailService(final JavaMailSender javaMailSender, final MessageSource messageSource,
			final SpringTemplateEngine templateEngine, final Environment env) {
		this.javaMailSender = javaMailSender;
		this.messageSource = messageSource;
		this.templateEngine = templateEngine;
		this.env = env;
	}

	@Async
	public void sendActivationEmail(final UserAccount user) {
		log.debug("Sending activation email to '{}'", user.getEmail());
		sendEmailFromTemplate(user, "mail/activationEmail", "email.activation.title");
	}

	@Async
	public void sendCreationEmail(final UserAccount user) {
		log.debug("Sending creation email to '{}'", user.getEmail());
		sendEmailFromTemplate(user, "mail/creationEmail", "email.activation.title");
	}

	@Async
	public void sendPasswordResetMail(final UserAccount user) {
		log.debug("Sending password reset email to '{}'", user.getEmail());
		sendEmailFromTemplate(user, "mail/passwordResetEmail", "email.reset.title");
	}

	@Async
	public void sendEmailFromTemplate(final UserAccount user, final String templateName, final String titleKey) {
		Locale locale = Locale.forLanguageTag("en-GB");
		Context context = new Context(locale);
		context.setVariable(USER, user);
		context.setVariable(BASE_URL, env.getProperty(BASE_URL_PATH));
		String content = templateEngine.process(templateName, context);
		String subject = "Welcome to Crowdfunding";// messageSource.getMessage(titleKey,
													// null, locale);
		// TODO use messageSource to have custom email
		sendEmail(user.getEmail(), subject, content, false, true);
	}

	@Async
	public void sendEmail(final String to, final String subject, final String content, final boolean isMultipart,
			final boolean isHtml) {
		log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}", isMultipart,
				isHtml, to, subject, content);

		// Prepare message using a Spring helper
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
			message.setTo(to);
			message.setFrom(env.getProperty(MAIL_FROM_PATH));
			message.setSubject(subject);
			message.setText(content, isHtml);
			javaMailSender.send(mimeMessage);
			log.debug("Sent email to User '{}'", to);
		} catch (MailException | MessagingException e) {
			log.warn("Email could not be sent to user '{}'", to, e);
		}
	}

}
