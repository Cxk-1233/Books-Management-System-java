package utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.security.GeneralSecurityException;
import java.util.Properties;

public class SendEamil {
	public SendEamil(String email, String title,String text) throws MessagingException,
			GeneralSecurityException {
		// 创建一个配置文件并保存
		Properties properties = new Properties();
		properties.setProperty("mail.host", "smtp.qq.com");
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		//SSL加密
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("256618757@qq.com",
								"exbwqdabcpjhbjbe");
					}
				});
		session.setDebug(true);
		Transport transport = session.getTransport();
		// 连接服务器
		transport.connect("smtp.qq.com", "256618757@qq.com", "exbwqdabcpjhbjbe");
		// 创建邮件对象
		MimeMessage mimeMessage = new MimeMessage(session);
		// 邮件发送人
		mimeMessage.setFrom(new InternetAddress("256618757@qq.com"));
		// 邮件接收人
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		// 邮件标题
		mimeMessage.setSubject(title);
		// 邮件内容
		mimeMessage.setContent(text, "text/html;charset=UTF-8");
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
	}
	
	public SendEamil(){
		
	}
}