package com.spring.Service;

import java.io.File;
import java.util.Date;
import java.util.Iterator;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.Model.Email;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendHtmlMsg(Email email) {
		System.out.println(email);
		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			if ((email != null) && (email.getTo() != null) && (email.getTo().length != 0)
					&& (!StringUtils.isEmpty(email.getFrom())) && (!StringUtils.isEmpty(email.getText()))) {
				helper.setFrom(email.getFrom());
				helper.setTo(email.getTo());
				helper.setSubject(email.getSubject());
				String text = "<html><body>&nbsp;<br><p>" + email.getText() + "</p><br>"
						+ "<b>Thanks & Regards,</b><br>" + " " + "<b>MetaAid Team</b><br>"
						+ "<img src='http://engineering.apssdc.in/andromeda/images/APSSDC_Logo.jpg' height='120' width='150' alt='APSSDC logo'/>"
						+ "<html><body>";

				helper.setText(text, true);
			}
			if ((email != null) && (email.getCc() != null) && (email.getCc().length != 0)) {
				helper.setCc(email.getCc());
			}
			if (email.getAttachments() != null) {
				Iterator<?> iterator = email.getAttachments().iterator();
				while (iterator.hasNext()) {
					FileSystemResource file = new FileSystemResource(new File((String) iterator.next()));
					helper.addAttachment(file.getFilename(), file);
				}
			}
			Date date = new Date();
			helper.setSentDate(date);
			try {
				this.mailSender.send(mimeMessage);
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
