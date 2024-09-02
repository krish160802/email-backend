package com.training.Service;

import java.io.File;
import java.io.InputStream;

public interface EmailService {
	void sendEmail(String to,String sub,String msg);
	void sendEmail(String []to,String sub,String msg);
	void sendEmailWithHtml(String to,String sub,String htmlContent);
	void sendEmailWithFile(String to,String sub,String msg,File file);
	void sendEmailWithFile(String to,String sub,String msg,InputStream inFile);
}
