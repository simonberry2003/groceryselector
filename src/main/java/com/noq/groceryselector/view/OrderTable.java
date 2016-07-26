package com.noq.groceryselector.view;

import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.noq.groceryselector.model.OrderSummary;

public class OrderTable extends Composite {

	private final TableViewer tableViewer;
	
	public OrderTable(Composite parent) {
		super(parent, SWT.None);

		setLayout(new GridLayout(1, false));

		tableViewer = new TableViewer(this);
	    tableViewer.setContentProvider(new OrderContentProvider());
	    tableViewer.setLabelProvider(new OrderLabelProvider());

	    Table table = tableViewer.getTable();
	    table.setLayoutData(new GridData(GridData.FILL_BOTH));

	    for (String columnName : OrderSummary.Columns)
	    {
	    	TableColumn tc = new TableColumn(table, SWT.LEFT);
	    	tc.setText(columnName);
	    }

	    table.setHeaderVisible(true);
	    table.setLinesVisible(true);

	    for (int i = 0, n = table.getColumnCount(); i < n; i++) {
	        table.getColumn(i).pack();
	    }
	}
	
	public void setOrders(List<OrderSummary> orders) {
		tableViewer.setInput(orders);
		Table table = tableViewer.getTable();
		table.setRedraw(false);
	    for (int i = 0, n = table.getColumnCount(); i < n; i++) {
	        table.getColumn(i).pack();
	    }
	    table.setRedraw(true);
	}
}
