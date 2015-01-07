package cn.edu.bfsu.exmail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class MailUtil {

	/**
	 *
	 * 获取用于单点登录的auth_key
	 * 
	 * @param email
	 * @return auth_key
	 */
	public static String getAuthKey(String email) {
		String url = "http://openapi.exmail.qq.com:12211/openapi/mail/authkey";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("alias", email));
		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("Authorization", "Bearer " + AccessTokenManager.getTokenString()));
		@SuppressWarnings("unchecked")
		Map<String, String> map = SimpleHttpClient.doPost(url, headers, nvps, Map.class);
		System.out.println(map);
		String authKey = map.get("auth_key");
		return authKey;
	}

	/**
	 * 获取未读邮件数
	 * 
	 * @param email
	 * @return 未读邮件数
	 */
	public static String getUnreadCount(String email) {
		String url = "http://openapi.exmail.qq.com:12211/openapi/mail/newcount";
		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		String accessToken = AccessTokenManager.getTokenString();
		headers.add(new BasicNameValuePair("Authorization", " Bearer " + accessToken));
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("alias", email));
		@SuppressWarnings("unchecked")
		Map<String, String> map = SimpleHttpClient.doPost(url, headers, nvps, Map.class);
		return map.get("NewCount");
	}

	/**
	 * 获取单点登录URL
	 * 
	 * @param email
	 * @return URL
	 */
	public static String getSSOUrl(String email) {
		String authKey = getAuthKey(email);
		String url = "https://exmail.qq.com/cgi-bin/login?fun=bizopenssologin&method=bizauth&agent=bfsu.edu&user="
				+ email + "&ticket=" + authKey;
		return url;
	}
	
	/**
	 * 通过pop3 协议验证用户名密码
	 * 
	 * @param email
	 * @param password
	 * @return  boolean
	 */
	public static boolean validate(String email, String password){
		return new POP3().validate("mail.bfsu.edu.cn", email, password);
	}

	public static void main(String[] args) {
		System.out.println(validate("hexin@bfsu.edu.cn", "你的邮箱密码"));
	}
}
