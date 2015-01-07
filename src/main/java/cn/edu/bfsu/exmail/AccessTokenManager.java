package cn.edu.bfsu.exmail;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author stenio
 *
 */
public class AccessTokenManager {

	private static AccessToken token;

	public static String getTokenString() {
		return getToken().getAccess_token();
	}

	/**
	 * 
	 * 验证token是否可用
	 * @param token
	 * @return
	 */
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
	
	private static AccessToken getToken(){
		if (token == null) {
			token = generateAccessToken();
		}
		if(!validate()){
			token = generateAccessToken();
		}
		return token;
	}

	/**
	 * 生成AccessToken
	 * 
	 * @return AccessToken
	 */
	private static AccessToken generateAccessToken() {
		String url = "https://exmail.qq.com/cgi-bin/token";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("client_id", "管理员帐号"));  // 管理员帐号
		nvps.add(new BasicNameValuePair("client_secret", AccessToken.KEY));
		nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));

		AccessToken token = SimpleHttpClient.doPost(url, null, nvps, AccessToken.class);
		token.setAccess_time(System.currentTimeMillis());
		return token;
	}

	public static void main(String[] args) {
		System.out.println(getTokenString());
		
		System.out.println(getTokenString());
		System.out.println(getTokenString());
		System.out.println(getTokenString());
		System.out.println(getTokenString());
	}
}
