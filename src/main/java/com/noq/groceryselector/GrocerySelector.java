package com.noq.groceryselector;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Preconditions;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.noq.groceryselector.guice.GrocerySelectorModule;
import com.noq.groceryselector.model.OrderSummary;
import com.noq.groceryselector.service.LogonService;
import com.noq.groceryselector.service.OrderService;
import com.noq.groceryselector.type.OrderStatus;
import com.noq.groceryselector.view.OrderTable;

public class GrocerySelector {

	private final ExecutorService executorService = Executors.newCachedThreadPool();
	private final LogonService logonService;
	private final OrderService orderService;
	
	private String token;
	private TabFolder folder;
	private OrderTable submittedTable;
	private OrderTable readyForPickingTable;
	private OrderTable pickingTable;
    private OrderTable pickedTable;
	private OrderTable finalisedTable;
	private Text storeText;
	private Text userText;
	private Text passwordText;
	
	@Inject
	public GrocerySelector(LogonService logonService, OrderService orderService) {
		this.logonService = Preconditions.checkNotNull(logonService);
		this.orderService = Preconditions.checkNotNull(orderService);
	}
	
	public static void main(String[] args) throws Exception {
		Injector guice = Guice.createInjector(new GrocerySelectorModule());
		GrocerySelector grocerySelector = guice.getInstance(GrocerySelector.class);
		grocerySelector.Run();
	}
	
	private void Run() throws Exception {

		Display display = new Display ();
		final Shell shell = new Shell(display);
		shell.setText("Grocery Selector");
		shell.setLayout(new GridLayout());

		new Label(shell, SWT.None).setText("User:");
		userText = new Text(shell, SWT.BORDER);
		userText.setText("mohammad.alinia@noqapps.com.au");
		new Label(shell, SWT.None).setText("Password:");
		passwordText = new Text(shell, SWT.BORDER | SWT.PASSWORD);

		Button logonButton = new Button(shell, SWT.None);
		logonButton.setText("Logon");
		logonButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				token = logonService.logon(userText.getText(), passwordText.getText());
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
				messageBox.setText("Logon");
		        messageBox.setMessage(token == null ? "Login Failed" : token);
		        messageBox.open();
			}
		});

		new Label(shell, SWT.None).setText("Store:");
		storeText = new Text(shell, SWT.BORDER);
		
		Button refreshButton = new Button(shell, SWT.None);
		refreshButton.setText("Refresh");
		refreshButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshData();
			}
		});
		
	    folder = new TabFolder(shell, SWT.None);
	    folder.setLayoutData(new GridData(GridData.FILL_BOTH));
	    
	    submittedTable = new OrderTable(folder);
	    TabItem submitted = new TabItem(folder, SWT.NONE);
	    submitted.setText("Submitted");
	    submitted.setControl(submittedTable);	    
 
	    readyForPickingTable = new OrderTable(folder);
	    TabItem readyForPicking = new TabItem(folder, SWT.NONE);
	    readyForPicking.setText("Ready For Picking");
	    readyForPicking.setControl(readyForPickingTable);	    

	    pickingTable = new OrderTable(folder);
	    TabItem picking = new TabItem(folder, SWT.NONE);
	    picking.setText("Picking");
	    picking.setControl(pickingTable);

	    pickedTable = new OrderTable(folder);
	    TabItem picked = new TabItem(folder, SWT.NONE);
	    picked.setText("Picked");
	    picked.setControl(pickedTable);	    

	    finalisedTable = new OrderTable(folder);
	    TabItem finalised = new TabItem(folder, SWT.NONE);
	    finalised.setText("Finalised");
	    finalised.setControl(finalisedTable);	    

	    shell.pack();
	    shell.setSize(1500, 800);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		executorService.shutdown();
	}

	protected void refreshData() {

		final int storeId = Integer.parseInt(storeText.getText());
		
		Future<List<OrderSummary>> submittedOrders = executorService.submit(new Callable<List<OrderSummary>>() {
			@Override
			public List<OrderSummary> call() throws Exception {
				return orderService.get(storeId, OrderStatus.Submitted, token);
			}
		});

	    Future<List<OrderSummary>> readyForPickingOrders = executorService.submit(new Callable<List<OrderSummary>>() {
			@Override
			public List<OrderSummary> call() throws Exception {
				return orderService.get(storeId, OrderStatus.ReadyForPicking, token);
			}
		});

	    Future<List<OrderSummary>> pickingOrders = executorService.submit(new Callable<List<OrderSummary>>() {
			@Override
			public List<OrderSummary> call() throws Exception {
				return orderService.get(storeId, OrderStatus.Picking, token);
			}
		});

	    Future<List<OrderSummary>> pickedOrders = executorService.submit(new Callable<List<OrderSummary>>() {
			@Override
			public List<OrderSummary> call() throws Exception {
				return orderService.get(storeId, OrderStatus.Picked, token);
			}
		});

	    Future<List<OrderSummary>> finalisedOrders = executorService.submit(new Callable<List<OrderSummary>>() {
			@Override
			public List<OrderSummary> call() throws Exception {
				return orderService.get(storeId, OrderStatus.PaymentFinalised, token);
			}
		});

	    try {
	    	submittedTable.setOrders(submittedOrders.get());
	    	readyForPickingTable.setOrders(readyForPickingOrders.get());
	    	pickingTable.setOrders(pickingOrders.get());
	    	pickedTable.setOrders(pickedOrders.get());
	    	finalisedTable.setOrders(finalisedOrders.get());
	    }
	    catch (Exception e) {
	    	throw new RuntimeException(e);
	    }
	}
}
