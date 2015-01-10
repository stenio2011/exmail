package cn.edu.bfsu.exmail;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AccessTokenManager {

	private static AccessToken token;

	public static String getTokenString() {
		return getToken().getAccess_token();
	}

	private static boolean validate() {
		if (token == null) {
			return false;
		}
		long now = System.currentTimeMillis();
		long access_time = token.getAccess_time();
		long expire_time = token.getExpires_in();
		if (access_time == 0 || expire_time == 0) {
			return false;
		}
		if (now - access_time > token.getExpires_in()) {
			return false;
		}
		return true;
	}

	private static AccessToken getToken() {
		if (token == null) {
			generateAccessToken();
		}
		if (!validate()) {
			generateAccessToken();
		}
		return token;
	}

	private static void generateAccessToken() {
		String url = "https://exmail.qq.com/cgi-bin/token";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("client_id", "bfsu.edu"));
		nvps.add(new BasicNameValuePair("client_secret", AccessToken.KEY));
		nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
		AccessTokenManager.token = SimpleHttpClient.doPost(url, null, nvps, AccessToken.class);
		AccessTokenManager.token.setAccess_time(System.currentTimeMillis());
	}

	public static void main(String[] args) {
		System.out.println(getTokenString());

		System.out.println(getTokenString());
		System.out.println(getTokenString());
		System.out.println(getTokenString());
		System.out.println(getTokenString());
	}
}
