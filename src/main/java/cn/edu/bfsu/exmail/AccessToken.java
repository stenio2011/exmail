package cn.edu.bfsu.exmail;

public class AccessToken {
	
	public static final String KEY = "3dd1b0808cb48dd3af0487aff64e7827";
	
	private long expires_in;
	
	private long access_time;
	
	private String token_type;
	
	private String refresh_token;
	
	private String access_token;
	
	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

	public long getAccess_time() {
		return access_time;
	}

	public void setAccess_time(long access_time) {
		this.access_time = access_time;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	
}
