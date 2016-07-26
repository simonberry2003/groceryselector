package com.noq.groceryselector.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.base.Preconditions;
import com.noq.groceryselector.model.OrderSummary;
import com.noq.groceryselector.model.OrderSummaryResponse;
import com.noq.groceryselector.type.OrderStatus;

@Singleton
public class OrderServiceImpl implements OrderService {

	private final HttpService httpService;

	@Inject
	public OrderServiceImpl(HttpService httpService) {
		this.httpService = Preconditions.checkNotNull(httpService);
	}

	@Override
	public List<OrderSummary> get(int storeId, OrderStatus status, String token) {
		String url = "https://development.noq-servers.net/api/v1/grocery-selector/store/" + storeId + "/order?states=" + status;
		OrderSummaryResponse response = httpService.get(url, OrderSummaryResponse.class, token);
		return response.GetOrders();
	}
}
