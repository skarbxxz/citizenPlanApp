package com.excelr.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String subject, String body, String to, File f) {
		
		// sometimes smtp server will creates issues thats y keeping in try catch
		try {
			
			MimeMessage mimeMsg = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg,true);
			
			helper.setSubject(subject);
			helper.setText(body, true); // here true means in body we can include html tags
			helper.setTo(to);
			helper.addAttachment("Plans-Info", f);
			mailSender.send(mimeMsg);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return true;
	}

}

