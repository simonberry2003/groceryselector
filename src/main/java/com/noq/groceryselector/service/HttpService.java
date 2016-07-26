package com.noq.groceryselector.service;

public interface HttpService {
	<Request, Response> Response Post(String url, Request request, Class<Response> responseClass);
	<Response> Response get(String url, Class<Response> responseClass, String token);
}
