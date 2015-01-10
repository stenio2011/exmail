package cn.edu.bfsu.exmail;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleHttpClient {

	public static <T> T doPost(String url, List<NameValuePair> headers, List<NameValuePair> params, Class<T> t) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		if (headers != null) {
			for (NameValuePair pair : headers) {
				httpPost.addHeader(pair.getName(), pair.getValue());
			}
		}
		CloseableHttpResponse response = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			ObjectMapper mapper = new ObjectMapper();
			T m = mapper.readValue(entity.getContent(), t);
			EntityUtils.consume(entity);
			return m;
		} catch (IOException e) {
			return null;
		} finally {
			try {
				response.close();
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
