package com.noq.groceryselector.model;

import com.google.common.base.MoreObjects;

public class LogonResponse extends AbstractResponse<LogonResult> {

	public String GetToken() {
		return result == null ? null : result.GetToken();
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("errors", errors)
			.add("hasErrors", hasErrors)
			.add("hasNoErrors", hasNoErrors)
			.add("result", result)
			.toString();
	}
}
