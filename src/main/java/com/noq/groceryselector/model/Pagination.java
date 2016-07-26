package com.noq.groceryselector.model;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

public class Pagination {

	@SerializedName("Skip")
	private int skip;

	@SerializedName("Take")
	private int take;

	@SerializedName("TotalNumberOfRecords")
	private int totalNumberOfRecords;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("skip", skip)
			.add("take", take)
			.add("totalNumberOfRecords", totalNumberOfRecords)
			.toString();
	}
}
