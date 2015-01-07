package cn.edu.bfsu.exmail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

/**
 * @author stenio
 *
 */
public class POP3 {

	/**
	 * @param popServer pop3服务器地址
	 * @param popUser   邮箱用户名
	 * @param popPassword  邮箱密码
	 * @return
	 */
	public boolean validate(String popServer, String popUser, String popPassword) {
		boolean b = false;
		final String u = popUser;
		final String p = popPassword;
		Properties props = new Properties();
		Session mailsession = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(u, p);
			}
		});

		try {
			Store store = mailsession.getStore("pop3");
			store.connect(popServer, u, p);
			store.close();
			b = true;
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return b;
	}
}