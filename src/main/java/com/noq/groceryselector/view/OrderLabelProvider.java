package com.noq.groceryselector.view;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.noq.groceryselector.model.OrderSummary;

public class OrderLabelProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener arg0) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {
	}

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		return null;
	}

	@Override
	public String getColumnText(Object order, int column) {
		return ((OrderSummary)order).getRow()[column];
	}
}
