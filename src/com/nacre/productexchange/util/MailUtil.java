package com.nacre.productexchange.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	
	private static Properties mailProperties;
	private static Session mailSession;
	private static String fromEmail = null;
	private static String password = null;

	static {
		InputStream inStream = MailUtil.class.getClassLoader().getResourceAsStream("mail.properties");
		mailProperties = new Properties();

		try {
			mailProperties.load(inStream);
		
			fromEmail = mailProperties.getProperty("emailId");
			password = mailProperties.getProperty("password");
			System.out.println(fromEmail);
			System.out.println(password);
			mailProperties.put("mail.smtp.host", "smtp.gmail.com");
			mailProperties.put("mail.smtp.port", "587");
			mailProperties.put("mail.smtp.auth", "true");
			mailProperties.put("mail.smtp.starttls.enable", "true");
			mailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

			mailSession = Session.getInstance(mailProperties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in MailUtil class ---->"+e.getMessage());
			//e.printStackTrace();
		}catch(Exception e){
			//net not connected
			System.out.println("Servier not found");
		}
		
	}

	public static boolean sendEmail(String toAddress, String subject, String messageContent) {

		try {
			System.out.println("now control is in sendmail()");
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(fromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));

			MimeBodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText(messageContent, "UTF-8", "text/html");
			message.setSubject(subject);
			message.setText(messageContent);

			Transport.send(message);

			System.out.println("Email Send Success");
			return true;
		} catch (Exception e) {
			System.out.println("Email not Sended");
			System.out.println("Exception in mailutil class on sendEmail()");
			e.printStackTrace();
			return false;
		}
	}

	/*public static void main(String[] args) {
		boolean flag=sendEmail("pothinadharma@gmail.com","Hi","Hallo good evng");
		System.out.println(flag);
	}*/
}