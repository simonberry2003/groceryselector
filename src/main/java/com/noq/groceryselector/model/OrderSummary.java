package com.noq.groceryselector.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;
import com.noq.groceryselector.type.FulfillmentMethod;
import com.noq.groceryselector.type.OrderStatus;

public class OrderSummary {

	public static final String[] Columns = {
		"Id",
		"Reference",
		"Submitted Date",
		"Due Date",
		"Ordered Items",
		"Ordered Subtotal",
		"Actual Items",
		"Actual Subtotal",
		"Customer Name",
		"Type",
		"Auth Date",
		"Paid Externally"
	};

	public static final Comparator<? super OrderSummary> DueDateComparator = new Comparator<OrderSummary>() {
		@Override
		public int compare(OrderSummary o1, OrderSummary o2) {
			return o1.dueDate.compareTo(o2.dueDate);
		}
	};
	
	@SerializedName("Id")
	private int id = 10;
	
	@SerializedName("ActualNumberOfItems")
	private int actualNumberOfItems;

	@SerializedName("ActualSubTotal")
	private BigDecimal actualSubTotal;

	@SerializedName("CustomerName")
	private String customerName;

	@SerializedName("DueDate")
	private Date dueDate;

	@SerializedName("FulfillmentMethod")
	private FulfillmentMethod fulfillmentMethod;

	@SerializedName("GrandTotal")
	private BigDecimal grandTotal;

	@SerializedName("OrderedNumberOfItems")
	private int orderedNumberOfItems;

	@SerializedName("OrderedSubTotal")
	private BigDecimal orderedSubTotal;

	@SerializedName("PaymentAcceptedExternally")
	private boolean paymentAcceptedExternally;

	@SerializedName("Reference")
	private String reference;

	@SerializedName("ScheduledAuthorisationTime")
	private Date scheduledAuthorisationTime;

	@SerializedName("Status")
	private OrderStatus status;

	@SerializedName("SubmittedDate")
	private Date submittedDate;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", id)
			.add("actualNumberOfItems", actualNumberOfItems)
			.add("orderedSubTotal", orderedSubTotal)
			.add("submittedDate", submittedDate)
			.toString();
	}

	public String[] getRow() {
		return new String[]
		{
			"" + id,
			reference,
			submittedDate.toString(),
			dueDate.toString(),
			"" + orderedNumberOfItems,
			"" + orderedSubTotal,
			"" + actualNumberOfItems,
			"" + actualSubTotal,
			customerName,
			fulfillmentMethod.name(),
			scheduledAuthorisationTime == null ? "" : scheduledAuthorisationTime.toString(),
			"" +  	paymentAcceptedExternally
		};
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(reference);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof OrderSummary)) {
			return false;
		}
		OrderSummary other = (OrderSummary)obj;
		return Objects.equal(reference, other.reference); 
	}
}
