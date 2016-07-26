package com.noq.groceryselector.model;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

public class Meta {

	@SerializedName("Pagination")
	private Pagination pagination;
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("pagination", pagination)
			.toString();
	}
}
