package com.noq.groceryselector.model;

import com.google.gson.annotations.SerializedName;

public class ServiceError {

	@SerializedName("Code")
	private String code;

	@SerializedName("Message")
	private String message;
}
