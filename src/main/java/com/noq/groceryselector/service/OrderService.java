package com.noq.groceryselector.service;

import java.util.List;

import com.noq.groceryselector.model.OrderSummary;
import com.noq.groceryselector.type.OrderStatus;

public interface OrderService {
	List<OrderSummary> get(int storeId, OrderStatus status, String token);
}
