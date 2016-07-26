package com.noq.groceryselector.model;

import java.util.Collections;
import java.util.List;

import com.google.common.base.MoreObjects;

public class OrderSummaryResponse extends AbstractPaginationResponse<List<OrderSummary>> {

	public List<OrderSummary> GetOrders() {
		if (result != null) {
			Collections.sort(result, OrderSummary.DueDateComparator);
		}
		return result;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("errors", errors)
			.add("hasErrors", hasErrors)
			.add("hasNoErrors", hasNoErrors)
			.add("orderCount", result.size())
			.add("meta", meta)
			.toString();
	}
}
