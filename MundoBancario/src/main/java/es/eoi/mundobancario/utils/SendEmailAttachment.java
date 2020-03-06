package es.eoi.mundobancario.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailAttachment {

	public void Enviar(String path, String to) {
		String from = "elmakinadelaeoi@gmail.com";
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("elmakinadelaeoi@gmail.com", "123piruliazul");
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Información de EOI Bank solicitada");
			Multipart multipart = new MimeMultipart();
			MimeBodyPart attachmentPart = new MimeBodyPart();
			MimeBodyPart textPart = new MimeBodyPart();
			try {
				File f = new File(path);
				attachmentPart.attachFile(f);
				textPart.setText(
						"Saludos cliente,\n\n Le enviamos la información que nos ha solicitado.\n\nEOI Bank,\nLa banca de los makinotes.");
				multipart.addBodyPart(textPart);
				multipart.addBodyPart(attachmentPart);
			} catch (IOException e) {
				e.printStackTrace();
			}
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
