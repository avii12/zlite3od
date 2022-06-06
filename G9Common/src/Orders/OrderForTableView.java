package Orders;

import java.sql.Timestamp;

/**
 *  this class is for the order details we put in the order table
 * 
 * @author Obied ,Haddad.
 * @author Ebrahem ,Enbtawe.
 * @author Mario ,Rohana

 */

public class OrderForTableView {

	/**
	 *  the order we want to put in the table
	 */
	private Order order;
	/**
	 *  the total price of the order
	 */
	private double TotalPrice;
	/**
	 * order Type
	 */
	private TypeOfSupply OrderType;
	/**
	 *  order date
	 */
	private Timestamp date;
	/**
	 * order status 
	 */
	private OrderStatus orderstatus;
	/**
	 * number of the order
	 */
	private String OrderNumber;
	/**
	 * the items that the orders contain
	 */
	private String allItems;
	/**
	 *  branch of the order
	 */
	private Branch branch;

	/**
	 * @param order
	 */
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
	
	/**
	 * 
	 * setters and getters
	 */

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



	/**
	 *  to print the order information
	 */
	@Override
	public String toString() {
		return "OrderForTableView [order=" + order + ", TotalPrice=" + TotalPrice + ", OrderType=" + OrderType
				+ ", date=" + date + ", orderstatus=" + orderstatus + ", OrderNumber=" + OrderNumber + ", allItems="
				+ allItems + ", branch=" + branch + "]";
	}
	
	

}
