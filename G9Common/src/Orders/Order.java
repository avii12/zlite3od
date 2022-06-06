package Orders;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  class description:
 *  order class contains all the order information
 *  
 * 
 * @author Obied ,Haddad.
 * @author Ebrahem ,Enbtawe.
 * @author Mario ,Rohana

 */
public class Order implements Serializable {


	private static final long serialVersionUID = 1L;

	/**
	 * the order number
	 */
	public String OrderNumber;
	/**
	 * customer id
	 */
	public String CustomerID;
	/**
	 * order branch
	 */
	public Branch branch;
	/**
	 *  order status(pending, approval...)
	 */
	public OrderStatus orderstatus;
	/**
	 * order date
	 */
	public Timestamp OrderDate;
	/**
	 * the estimated time
	 */
	public Timestamp EstimatedDate;
	/**
	 *  the actual date of the order
	 */
	public Timestamp ActualDate;
	/**
	 * order supply type
	 */
	public TypeOfSupply SupplyType;
	/**
	 *  price of the order
	 */
	public double TotalPrice;
	/**
	 * bouquet or flower
	 */
	public ItemCategory Item;
	/**
	 * the customer name that made the order
	 */
	public String CustomerName;
	/**
	 * the delivery cost 
	 */
	public double DeliveryCost;
	/**
	 *  delivery address of the customer
	 */
	public String DeliveryAddress;
	/**
	 * the items that the order contains
	 */
	public String AllItems;
	/**
	 * the phone number of the customer that made the order
	 */
	public String PhoneNumber;
	/**
	 * the added greeting card of the order
	 */
	public String GreetingCard;

	/**
	 * @param orderNumber
	 * @param customerID
	 * @param branch
	 * @param orderstatus
	 * @param orderDate
	 * @param estimatedDate
	 * @param actualDate
	 * @param supplyType
	 * @param totalPrice
	 * @param item
	 * @param customerName
	 * @param deliveryCost
	 * @param deliveryAddress
	 * @param Items
	 * @param GreetingCard
	 */
	public Order(String orderNumber, String customerID, Branch branch, OrderStatus orderstatus, Timestamp orderDate,
			Timestamp estimatedDate, Timestamp actualDate, TypeOfSupply supplyType, double totalPrice,
			ItemCategory item, String customerName, double deliveryCost, String deliveryAddress, String Items,
			String GreetingCard) {

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
	}

	/**
	 * @param OrderNumber
	 * @param CustomerName
	 * @param DeliveryAddress
	 */
	public Order(String OrderNumber, String CustomerName, String DeliveryAddress) {

		this.OrderNumber = OrderNumber;
		this.CustomerName = CustomerName;
		this.DeliveryAddress = DeliveryAddress;
	}

	
	/**
	 * @param orderNumber2
	 * @param customerID2
	 * @param branch2
	 * @param orderStatus2
	 * @param orderDate2
	 * @param estimatedDate2
	 * @param actualDate2
	 * @param supplyType
	 * @param totalPrice2
	 * @param deliveryCost2
	 * @param customerName2
	 * @param deliveryAddress2
	 * @param Item
	 * @param GreetingCard
	 */
	public Order(String orderNumber2, String customerID2, Branch branch2, OrderStatus orderStatus2,
			Timestamp orderDate2, Timestamp estimatedDate2, Timestamp actualDate2, TypeOfSupply supplyType,
			double totalPrice2, double deliveryCost2, String customerName2, String deliveryAddress2, String Item,
			String GreetingCard) {

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

	}   

	/**
	 * @param order
	 */
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
		this.GreetingCard = order.getGreetingCard();
	}

	
	
	
	/**
	 * setters and getters
	 */
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
				+ GreetingCard + ", refundStatus=" + "]";
	}

}
