package com.training.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.training.Entity.Email;
import com.training.Service.EmailService;

@RestController
@RequestMapping("/api/email")
@CrossOrigin("*")
public class EmailController {
	
	private EmailService emailService;
	
	

	public EmailController(EmailService emailService) {
		super();
		this.emailService = emailService;
	}



	@PostMapping("/send")
	public ResponseEntity<?> sendEmail(@RequestBody Email req){
		emailService.sendEmail(req.getTo(), req.getSub(), req.getMsg());
		return new ResponseEntity<>("Email Sent",HttpStatus.OK);
	}
	
	@PostMapping("/sendItem")
	public ResponseEntity<?> sendEmailWithFile(@RequestPart Email req,@RequestPart MultipartFile file) throws IOException{
		emailService.sendEmailWithFile(req.getTo(), req.getSub(), req.getMsg(),file.getInputStream());
		return new ResponseEntity<>("Email Sent",HttpStatus.OK);
	}
}
