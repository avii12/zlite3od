package Orders;

import java.util.Date;

public class OrderForTableView {
	
	private Order order;
	private double TotalPrice;
	private TypeOfSupply OrderType;
	private Date date;
	private OrderStatus orderstatus;
	
	public OrderForTableView(Order order) {
		this.order = order;
	    this.TotalPrice = order.getTotalPrice();
	    this.OrderType=order.getSupplyType();
	    this.date=order.getOrderDate();
	    this.orderstatus=order.getOrderstatus();
	  
		
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OrderStatus getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(OrderStatus orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	
	

}
