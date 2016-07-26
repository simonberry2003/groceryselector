package com.noq.groceryselector.view;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.noq.groceryselector.model.OrderSummary;

public class OrderContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object orders) {
		return ((Collection<OrderSummary>)orders).toArray();
	}
}
