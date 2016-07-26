package com.noq.groceryselector.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;

@Singleton
public class HttpServiceImpl implements HttpService {

	private final Gson gson;

	@Inject
	public HttpServiceImpl(Gson gson) {
		this.gson = Preconditions.checkNotNull(gson);
	}
	
	@Override
	public <Request, Response> Response Post(String url, Request request, Class<Response> responseClass) {
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost post = new HttpPost(url);
			post.addHeader("content-type", "application/json");
			post.setEntity(new StringEntity(gson.toJson(request)));
			
			try (CloseableHttpResponse response = httpClient.execute(post))
			{
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity);
				return gson.fromJson(responseString, responseClass);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <Response> Response get(String url, Class<Response> responseClass, String token) {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpGet request = new HttpGet(url);
			request.addHeader("token", token);
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity);
			return gson.fromJson(responseString, responseClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
