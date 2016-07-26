package com.noq.groceryselector.model;

import com.google.gson.annotations.SerializedName;

public abstract class AbstractPaginationResponse<T> extends AbstractResponse<T> {

	@SerializedName("Meta")
	protected Meta meta;
}
