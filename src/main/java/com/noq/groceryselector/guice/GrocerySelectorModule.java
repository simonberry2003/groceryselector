package com.noq.groceryselector.guice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.noq.groceryselector.service.HttpService;
import com.noq.groceryselector.service.HttpServiceImpl;
import com.noq.groceryselector.service.LogonService;
import com.noq.groceryselector.service.LogonServiceImpl;
import com.noq.groceryselector.service.OrderService;
import com.noq.groceryselector.service.OrderServiceImpl;

public class GrocerySelectorModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Gson.class).toInstance(new GsonBuilder().create());
		bind(LogonService.class).to(LogonServiceImpl.class);
		bind(HttpService.class).to(HttpServiceImpl.class);
		bind(OrderService.class).to(OrderServiceImpl.class);
	}
}
