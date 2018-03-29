package com.qds.sa.util.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.qds.sa.domain.ForgotAccess;
import com.qds.sa.domain.RequestAccess;
import com.qds.sa.domain.UserProfile;

@Service
public class EmailService {
	private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender)
	{
		this.javaMailSender=javaMailSender;
	}
	public void sendRequestEmail(RequestAccess user)throws MailException
	{
		try {
			SimpleMailMessage mail=new SimpleMailMessage();
			mail.setTo(user.getUemailid());
			mail.setFrom("akanth1994@gmail.com");
			mail.setSubject("Access Request for new user");
			
			mail.setText("Hello,\n"
					+ "New User has requested to join \"id\": "+user.getUid()+ "\n" +
					"Please welcome with a new UserId and Password. "	
					+"Click link: http://localhost:4200/admin to on board user. \n"
					+ "Thanks");
			javaMailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	public void sendForgotEmail(ForgotAccess res)throws MailException
	{
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(res.getUemailid());
		mail.setFrom("akanth1994@gmail.com");
		mail.setSubject("Forgot Username / Password");
		mail.setText("Hello,\n"
				+ "New User has requested to join \"id\": "+res.getUid()+ "\n" +
				"Please welcome with a new UserId and Password. "	
				+"Click link: http://localhost:4200/admin to on board user. \n"
				+ "Thanks");
		javaMailSender.send(mail);
	}
	
	public void userAddesEmail(UserProfile user)throws MailException
	{
		try {
			SimpleMailMessage mail=new SimpleMailMessage();
			mail.setTo(user.getUemailid());
			mail.setFrom("akanth1994@gmail.com");
			mail.setSubject("User has been added");
			mail.setText("Hello,\n"
					+ "New User has requested has been accepted \"id\": "+user.getUqueryid() + " and \"Password\": "+user.getUpassword()+"\n"
					+"Click link: http://localhost:4200/ to Login. \n Thanks");
			javaMailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}