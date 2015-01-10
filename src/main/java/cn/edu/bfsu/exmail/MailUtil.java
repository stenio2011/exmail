package cn.edu.bfsu.exmail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class MailUtil {

	private static String getAuthKey(String email) {
		String url = "http://openapi.exmail.qq.com:12211/openapi/mail/authkey";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("alias", email));
		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("Authorization", "Bearer " + AccessTokenManager.getTokenString()));
		@SuppressWarnings("unchecked")
		Map<String, String> map = SimpleHttpClient.doPost(url, headers, nvps, Map.class);
		String authKey = map.get("auth_key");
		return authKey;
	}

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

	public static String getSSOUrl(String email) {
		String authKey = getAuthKey(email);
		String url = "https://exmail.qq.com/cgi-bin/login?fun=bizopenssologin&method=bizauth&agent=bfsu.edu&user="
				+ email + "&ticket=" + authKey;
		return url;
	}
}
