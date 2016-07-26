package com.noq.groceryselector.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.gson.annotations.SerializedName;

public class LogonRequest {

	@SerializedName("Email")
	private final String email;
	
	@SerializedName("Password")
	private final String password;

	public LogonRequest(String email, String password) {
		this.email = Preconditions.checkNotNull(email);
		this.password = Preconditions.checkNotNull(password);
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("email", email)
			.add("password", password)
			.toString();
	}
}
