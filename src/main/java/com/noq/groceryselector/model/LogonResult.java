package com.noq.groceryselector.model;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

public class LogonResult {
	
	@SerializedName("UserId")
	private int userId;
	
	@SerializedName("Token")
	private String token;

	public String GetToken() {
		return token;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("userId", userId)
			.add("token", token)
			.toString();
	}
}
