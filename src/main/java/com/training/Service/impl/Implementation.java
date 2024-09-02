package com.training.Service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.training.Service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class Implementation implements EmailService{

	private JavaMailSender mailSender;
	
	private Logger logger = LoggerFactory.getLogger(Implementation.class);
	
	public Implementation(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}
	


	@Override
	public void sendEmail(String to, String sub, String msg) {
		// TODO Auto-generated method stub
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(sub);
		simpleMailMessage.setText(msg);
		simpleMailMessage.setFrom("krishkhera16@gmail.com");
		
		mailSender.send(simpleMailMessage);
		logger.info("Email has been sent..");
	}
	
	@Override
	public void sendEmail(String[] to, String sub, String msg) {
		// TODO Auto-generated method stub
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(sub);
		simpleMailMessage.setText(msg);
		simpleMailMessage.setFrom("krishkhera16@gmail.com");
		
		mailSender.send(simpleMailMessage);
		logger.info("Email has been sent..");
		
	}

	@Override
	public void sendEmailWithHtml(String to, String sub, String htmlContent) {
		// TODO Auto-generated method stub
		
		MimeMessage simpleMailMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage, true,"UTF-8");
			
			helper.setTo(to);
			helper.setSubject(sub);
			helper.setText(htmlContent, true);
			helper.setFrom("krishkhera16@gmail.com");
			
			mailSender.send(simpleMailMessage);
			logger.info("Email has been sent..");
		}
		catch(MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void sendEmailWithFile(String to, String sub, String msg, File file) {
		// TODO Auto-generated method stub
		
        MimeMessage simpleMailMessage = mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage, true);
			
			helper.setTo(to);
			helper.setSubject(sub);
			helper.setText(msg);
			helper.setFrom("krishkhera16@gmail.com");
			
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			
 			mailSender.send(simpleMailMessage);
			logger.info("Email has been sent..");
		}
		
		catch(MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}



	@Override
	public void sendEmailWithFile(String to, String sub, String msg, InputStream inFile) {
		// TODO Auto-generated method stub
		
MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			
			helper.setTo(to);
			helper.setSubject(sub);
			helper.setText(msg);
			helper.setFrom("krishkhera16@gmail.com");
			
			File file = new File("test.jpg");
			Files.copy(inFile, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			
 			mailSender.send(mimeMessage);
			logger.info("Email has been sent..");
		}
		
		catch(MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

}
