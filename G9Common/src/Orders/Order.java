package Orders;

import java.io.Serializable;
import java.sql.Timestamp;


public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String OrderNumber;
	public String CustomerID;
	public Branch branch;
	public OrderStatus orderstatus;
	public Timestamp OrderDate;
	public Timestamp EstimatedDate;
	public Timestamp ActualDate;
	public TypeOfSupply SupplyType;
	public double TotalPrice;
	public ItemCategory Item;
	public String CustomerName;
	public double DeliveryCost;
	public String DeliveryAddress;
	public String AllItems;
	public String PhoneNumber;
	public String GreetingCard;
    public RefundStatus refundStatus;

	public Order(String orderNumber, String customerID, Branch branch, OrderStatus orderstatus, Timestamp orderDate,
			Timestamp estimatedDate, Timestamp actualDate, TypeOfSupply supplyType, double totalPrice, ItemCategory item,
			String customerName, double deliveryCost, String deliveryAddress,String Items,String GreetingCard,RefundStatus refundstatus ) {

		super();
		OrderNumber = orderNumber;
		CustomerID = customerID;
		this.branch = branch;
		this.orderstatus = orderstatus;
		OrderDate = orderDate;
		EstimatedDate = estimatedDate;
		ActualDate = actualDate;
		SupplyType = supplyType;
		TotalPrice = totalPrice;
		Item = item;
		CustomerName = customerName;
		DeliveryCost = deliveryCost;
		DeliveryAddress = deliveryAddress;
		AllItems = Items;
		refundStatus=refundstatus;
	}

	public Order(String OrderNumber, String CustomerName, String DeliveryAddress) {

		this.OrderNumber = OrderNumber;
		this.CustomerName = CustomerName;
		this.DeliveryAddress = DeliveryAddress;
	}

	public Order(String CustomerId, Branch branch) {

		this.OrderNumber = null; // order unique number is created in the DB
		this.CustomerID = CustomerId;
		this.branch = branch;
		this.orderstatus = null; // default when creating new order.
		// this.issueDateTime =
		// DateTimeHandler.buildMySqlDateTimeFormatFromTextFields("1970/01/01",
		// "00:00"); // the order
		this.EstimatedDate = null;
		// this.actualSupplyDateTime =
		// DateTimeHandler.buildMySqlDateTimeFormatFromTextFields("1970/01/01",
		// "00:00");
		this.orderstatus = OrderStatus.PENDING; // default when creating new order.
		// this.OrderDate =
		// DateTimeConvert.buildMySqlDateTimeFormatFromTextFields("1970/01/01",
		// "00:00"); // the order
		this.EstimatedDate = null;
		// this.ActualDate=
		// DateTimeConvert.buildMySqlDateTimeFormatFromTextFields("1970/01/01",
		// "00:00");
		this.SupplyType = null;
		this.TotalPrice = 0.0;
	}

	public Order(String orderNumber2, String customerID2, Branch branch2, OrderStatus orderStatus2,
			Timestamp orderDate2, Timestamp estimatedDate2, Timestamp actualDate2, TypeOfSupply supplyType,
			double totalPrice2, double deliveryCost2, String customerName2, String deliveryAddress2, String Item,String GreetingCard,RefundStatus refundstatus) {

		this.OrderNumber = orderNumber2;
		this.CustomerID = customerID2;
		this.branch = branch2;
		this.orderstatus = orderStatus2;
		this.orderstatus = orderStatus2;
		this.OrderDate = orderDate2;
		this.EstimatedDate = estimatedDate2;
		this.ActualDate = actualDate2;
		this.SupplyType = supplyType;
		this.TotalPrice = totalPrice2;
		this.CustomerName = customerName2;
		this.DeliveryCost = 10.0;
		this.DeliveryAddress = deliveryAddress2;
		this.AllItems = Item;
		this.GreetingCard = GreetingCard;
		this.refundStatus=refundstatus;

	}

	public Order(Order order) {
		this.OrderNumber = order.getOrderNumber();
		this.CustomerID = order.getCustomerID();
		this.branch = order.getBranch();
		this.orderstatus = order.getOrderstatus();
		this.OrderDate = order.getOrderDate();
		this.EstimatedDate = order.getEstimatedDate();
		this.ActualDate = order.getActualDate();
		this.SupplyType = order.getSupplyType();
		this.TotalPrice = order.getTotalPrice();
		this.CustomerName = order.getCustomerName();
		this.DeliveryCost = order.getDeliveryCost();
		this.DeliveryAddress = order.getDeliveryAddress();
		this.AllItems = order.getAllItems();
		this.GreetingCard=order.getGreetingCard();
		this.refundStatus=order.getRefundStatus();
	}
	
	

	public RefundStatus getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(RefundStatus refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getGreetingCard() {
		return GreetingCard;
	}

	public void setGreetingCard(String greetingCard) {
		GreetingCard = greetingCard;
	}

	public ItemCategory getItem() {
		return Item;
	}

	public void setItem(ItemCategory item) {
		Item = item;
	}

	public void setDeliveryCost(double deliveryCost) {
		DeliveryCost = deliveryCost;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public double getDeliveryCost() {
		return DeliveryCost;
	}

	public String getAllItems() {
		return AllItems;
	}

	public void setAllItems(String allItems) {
		AllItems = allItems;
	}

	public void setOrderCategory(ItemCategory item) {

		Item = item;
	}

	public ItemCategory getIteamCategory() {

		return Item;
	}

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public OrderStatus getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(OrderStatus orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Timestamp getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		OrderDate = orderDate;
	}

	public Timestamp getEstimatedDate() {
		return EstimatedDate;
	}

	public void setEstimatedDate(Timestamp estimatedDate) {
		EstimatedDate = estimatedDate;
	}

	public Timestamp getActualDate() {
		return ActualDate;
	}

	public void setActualDate(Timestamp actualDate) {
		ActualDate = actualDate;

	}

	public TypeOfSupply getSupplyType() {
		return SupplyType;
	}

	public void setSupplyType(TypeOfSupply supplyType) {
		SupplyType = supplyType;
	}

	public double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		TotalPrice = totalPrice;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getDeliveryAddress() {
		return DeliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		DeliveryAddress = deliveryAddress;
	}

	@Override
	public String toString() {
		return "Order [OrderNumber=" + OrderNumber + ", CustomerID=" + CustomerID + ", branch=" + branch
				+ ", orderstatus=" + orderstatus + ", OrderDate=" + OrderDate + ", EstimatedDate=" + EstimatedDate
				+ ", ActualDate=" + ActualDate + ", SupplyType=" + SupplyType + ", TotalPrice=" + TotalPrice + ", Item="
				+ Item + ", CustomerName=" + CustomerName + ", DeliveryCost=" + DeliveryCost + ", DeliveryAddress="
				+ DeliveryAddress + ", AllItems=" + AllItems + ", PhoneNumber=" + PhoneNumber + ", GreetingCard="
				+ GreetingCard + ", refundStatus=" + refundStatus + "]";
	}

	
}
