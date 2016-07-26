package com.noq.groceryselector.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.base.Preconditions;
import com.noq.groceryselector.model.LogonRequest;
import com.noq.groceryselector.model.LogonResponse;

@Singleton
public class LogonServiceImpl implements LogonService {

	private final HttpService httpService;

	@Inject
	public LogonServiceImpl(HttpService httpService) {
		this.httpService = Preconditions.checkNotNull(httpService);
	}
	
	
	@Override
	public String logon(String username, String password) {
		LogonResponse logonResponse = httpService.Post("https://development.noq-servers.net/api/v1/grocery-selector/login", new LogonRequest(username, password), LogonResponse.class);
		return logonResponse.GetToken();
	}
}
