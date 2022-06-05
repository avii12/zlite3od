package Orders;

import java.sql.Timestamp;

public class OrderForTableView {

	private Order order;
	private double TotalPrice;
	private TypeOfSupply OrderType;
	private Timestamp date;
	private OrderStatus orderstatus;
	private String OrderNumber;
	private String allItems;
	private Branch branch;

	public OrderForTableView(Order order) {
		this.order = order;
		this.TotalPrice = order.getTotalPrice();
		this.OrderType = order.getSupplyType();
		this.date = order.getOrderDate();
		this.orderstatus = order.getOrderstatus();
		this.OrderNumber = order.getOrderNumber();
		this.allItems = order.getAllItems();
		this.branch = order.getBranch();

	}
	
	

	public Branch getBranch() {
		return branch;
	}



	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public String getAllItems() {
		return allItems;
	}

	public void setAllItems(String allItems) {
		this.allItems = allItems;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		TotalPrice = totalPrice;
	}

	public TypeOfSupply getOrderType() {
		return OrderType;
	}

	public void setOrderType(TypeOfSupply orderType) {
		OrderType = orderType;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public OrderStatus getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(OrderStatus orderstatus) {
		this.orderstatus = orderstatus;
	}



	@Override
	public String toString() {
		return "OrderForTableView [order=" + order + ", TotalPrice=" + TotalPrice + ", OrderType=" + OrderType
				+ ", date=" + date + ", orderstatus=" + orderstatus + ", OrderNumber=" + OrderNumber + ", allItems="
				+ allItems + ", branch=" + branch + "]";
	}
	
	

}
