package com.noq.groceryselector.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public abstract class AbstractResponse<T> {

	@SerializedName("Errors")
	protected List<ServiceError> errors;
	
	@SerializedName("HasErrors")
	protected boolean hasErrors;

	@SerializedName("HasNoErrors")
	protected boolean hasNoErrors;

	@SerializedName("Result")
	protected T result;
}
