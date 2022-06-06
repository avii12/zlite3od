package queries;

import java.sql.ResultSet;
import java.sql.SQLException;//github.com/avii12/ZliRepository.git
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import AllUsers.ConfirmationStatus;
import AllUsers.Customer;
import AllUsers.User;
import Orders.Branch;
import Orders.DateTimeConvert;
import Orders.DominantColor;
import Orders.FlowerColor;
import Orders.Item;
import Orders.ItemCategory;
import Orders.ItemType;
import Orders.Order;
import Orders.OrderStatus;
import Orders.RefundStatus;
import Orders.TypeOfSupply;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;

public class OrderQuery {

	public static FullMessage CheckIfFirstOrder(FullMessage messageFromClient) throws SQLException {

		FullMessage returnMessageToClient = messageFromClient;
		ResultSet rs = mainQuery.SelectAllFromDB("orders");
		int c = 0;
		try {
			// If the row doesn't exist in login Table
			if (!rs.isBeforeFirst()) {
				returnMessageToClient.setResponse(Response.NO_ORDER_FOUND);
				return returnMessageToClient;
			}

			while (rs.next()) {
				c++;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		returnMessageToClient.setObject(c);
		returnMessageToClient.setResponse(Response.NOT_FIRST_ORDER);
		return returnMessageToClient;
	}


	private static Item convertToItem(ResultSet rs) {
		try {
			String itemID = rs.getString(1);
			ItemCategory itemCategory = ItemCategory.valueOf(rs.getString(2));
			FlowerColor flowercolor = FlowerColor.valueOf(rs.getString(3));
			String name = rs.getString(4);
			double price = rs.getDouble(5);
			String picturePath = rs.getString(6);
			String greetingCard = rs.getString(7);
			ItemType itemType = ItemType.valueOf(rs.getString(8));
			DominantColor dominantColor = DominantColor.valueOf(rs.getString(9));

			int amount = rs.getInt(10);

			return new Item(itemID, itemCategory, flowercolor, name, price, picturePath, greetingCard, itemType,
					dominantColor, amount);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static FullMessage AcceptOrder(FullMessage messageFromClient) throws SQLException {
		FullMessage returnMessageToClient = messageFromClient;
		List<Order> orderList = new ArrayList<Order>();
		String BranchName = null;

		ResultSet rs1 = mainQuery.getTuple("branchmanager", "ID='" + returnMessageToClient.getObject() + "'");
		if (rs1.next()) {
			BranchName = rs1.getString(9);
		}
		rs1.close();

		ResultSet rs = mainQuery.getTuple("orders",
				"Branch='" + BranchName + "'AND (OrderStatus='" + OrderStatus.PENDING.toString() + "')");
		try {
			// If the row doesn't exist in login Table
			if (!rs.isBeforeFirst()) {
				returnMessageToClient.setResponse(Response.NO_ORDER_FOUND);
				returnMessageToClient.setObject(null);
				return returnMessageToClient;
			}
			while (rs.next()) {
				Order orderFromDB = convertToOrder(rs);
				if (orderFromDB != null) {
					orderList.add(orderFromDB);

				}

			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		returnMessageToClient.setObject(orderList);
		returnMessageToClient.setResponse(Response.ORDER_FOUND);
		return returnMessageToClient;
	}

	public static FullMessage DeliveryOrder(FullMessage messageFromClient) throws SQLException {
		FullMessage returnMessageToClient = messageFromClient;
		List<Order> orderList = new ArrayList<Order>();
		String BranchName = null;

		ResultSet rs1 = mainQuery.getTuple("deliveryperson", "ID='" + returnMessageToClient.getObject() + "'");
		if (rs1.next()) {
			BranchName = rs1.getString(9);
		}
		rs1.close();

		ResultSet rs = mainQuery.getTuple("orders",
				"Branch='" + BranchName + "'AND (SupplyType='" + TypeOfSupply.DELIVERY.toString() + "')"
						+ "AND (OrderStatus='" + OrderStatus.APPROVED.toString() + "')");
		try {
			// If the row doesn't exist in login Table
			if (!rs.isBeforeFirst()) {
				returnMessageToClient.setResponse(Response.NO_ORDER_FOUND);
				returnMessageToClient.setObject(null);
				return returnMessageToClient;
			}
			while (rs.next()) {
				Order orderFromDB = convertToOrder(rs);
				if (orderFromDB != null) {
					orderList.add(orderFromDB);

				}

			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		returnMessageToClient.setObject(orderList);
		returnMessageToClient.setResponse(Response.ORDER_FOUND);
		return returnMessageToClient;
	}

	public static FullMessage CancelOrder(FullMessage messageFromClient) throws SQLException {

		FullMessage returnMessageToClient = messageFromClient;
		List<Order> orderList = new ArrayList<Order>();
		ResultSet rs = mainQuery.getTuple("orders",
				"CustomerID='" + returnMessageToClient.getObject() + "'AND (OrderStatus='"
						+ OrderStatus.PENDING.toString() + "')" + "OR (OrderStatus='" + OrderStatus.APPROVED.toString()
						+ "')");

		try {
			if (!rs.isBeforeFirst()) {
				returnMessageToClient.setResponse(Response.NO_ORDER_FOUND);
				returnMessageToClient.setObject(null);
				return returnMessageToClient;
			}
			while (rs.next()) {
				Order orderFromDB = convertToOrder(rs);
				if (orderFromDB != null) {
					orderList.add(orderFromDB);
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		returnMessageToClient.setObject(orderList);
		returnMessageToClient.setResponse(Response.ORDER_FOUND);
		return returnMessageToClient;
	}

	public static FullMessage ManagerCancelOrder(FullMessage messageFromClient) throws SQLException {

		FullMessage returnMessageToClient = messageFromClient;
		List<Order> orderList = new ArrayList<Order>();
		String BranchName = null;

		ResultSet rs1 = mainQuery.getTuple("branchmanager", "ID='" + returnMessageToClient.getObject() + "'");
		if (rs1.next()) {
			BranchName = rs1.getString(9);
		}
		rs1.close();

		ResultSet rs = mainQuery.getTuple("orders",
				"Branch='" + BranchName + "'AND (OrderStatus='" + OrderStatus.CANCEL.toString() + "')");

		if (!rs.isBeforeFirst()) {
			returnMessageToClient.setResponse(Response.NO_ORDER_FOUND);
			returnMessageToClient.setObject(null);
			return returnMessageToClient;
		}
		while (rs.next()) {
			Order orderFromDB = convertToOrder(rs);
			if (orderFromDB != null) {
				orderList.add(orderFromDB);
			}
		}
		rs.close();
		returnMessageToClient.setResponse(Response.ORDER_FOUND_FOR_MANAGER);
		returnMessageToClient.setObject(orderList);
		return returnMessageToClient;

	}

	public static FullMessage GetOrderForCustomer(FullMessage messageFromClient) throws SQLException {

		FullMessage returnMessageToClient = messageFromClient;
		String id = (String) returnMessageToClient.getObject();
		ArrayList<Order> orderList = new ArrayList<Order>();

		ResultSet rs = mainQuery.getTuple("orders",
				"CustomerID='" + id + "'AND (OrderStatus='" + OrderStatus.COMPLETED.toString() + "')");

		if (!rs.isBeforeFirst()) {
			returnMessageToClient.setResponse(Response.NO_ORDER_FOUND);
			returnMessageToClient.setObject(null);
			return returnMessageToClient;
		}
		while (rs.next()) {
			Order orderFromDB = convertToOrder(rs);
			if (orderFromDB != null) {
				orderList.add(orderFromDB);
			}
		}
		rs.close();
		returnMessageToClient.setResponse(Response.ORDER_FOUND_FOR_MANAGER);
		returnMessageToClient.setObject(orderList);
		return returnMessageToClient;

	}

	private static Order convertToOrder(ResultSet rs) {
		try {
			String OrderNumber = rs.getString(1);
			String CustomerID = rs.getString(2);
			Branch branch = Branch.valueOf(rs.getString(3));
			OrderStatus orderStatus = OrderStatus.valueOf(rs.getString(4));
			Timestamp OrderDate = rs.getTimestamp(5);
			Timestamp EstimatedDate = rs.getTimestamp(6);
			Timestamp ActualDate = rs.getTimestamp(7);
			TypeOfSupply SupplayType = TypeOfSupply.valueOf(rs.getString(8));
			double TotalPrice = rs.getDouble(9);
			double DeliveryCost = rs.getDouble(10);
			String CustomerName = rs.getString(11);
			String DeliveryAddress = rs.getString(12);
			String AllItems = rs.getString(13);
			String GreetingCard = rs.getString(15);

			return new Order(OrderNumber, CustomerID, branch, orderStatus, OrderDate, EstimatedDate, ActualDate,
					SupplayType, TotalPrice, DeliveryCost, CustomerName, DeliveryAddress, AllItems, GreetingCard);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static FullMessage addOrderToDb(FullMessage messageFromClient) throws SQLException, ParseException {
		Order order = (Order) messageFromClient.getObject();
		FullMessage returnMessageToClient = messageFromClient;
		String ordernumber = order.getOrderNumber();
		String customerID = order.getCustomerID();
		Branch branch = order.getBranch();
		OrderStatus orderstatus = order.getOrderstatus();
		Timestamp orderdate = null;
		Timestamp estimated = order.getEstimatedDate();
		Timestamp actualdate = null;
		TypeOfSupply type = order.getSupplyType();
		double price = order.getTotalPrice();
		double deliveryCost = order.getDeliveryCost();
		String name = order.getCustomerName();
		String address = order.getDeliveryAddress();
		String items = order.getAllItems();
		String phone = order.getPhoneNumber();
		String greetingCard = order.getGreetingCard();
		mainQuery.InsertOneRowIntoOrderTable(ordernumber, customerID, branch, orderstatus, orderdate, estimated,
				actualdate, type, price, deliveryCost, name, address, items, phone, greetingCard);
		return returnMessageToClient;
	}

	public static FullMessage GetAmountAndUpdate(FullMessage messageFromClient) throws SQLException {

		FullMessage returnMessageToClient = messageFromClient;
		String id = (String) messageFromClient.getObject();
		String condition = "ID='" + id + "'";
		ResultSet rs = mainQuery.getTuple("catalog", condition);
		try {
			while (rs.next()) {
				int amount = rs.getInt(10);
				if (amount > 0) {
					amount--;
					String condition1 = "Amount" + "=" + amount; /// CHANGEEE EBRAHEM
					String condition2 = "ID=" + id;
					mainQuery.updateTuple("catalog", condition1, condition2);
					returnMessageToClient.setResponse(Response.AMOUNT_UPDATED);
				}

				else {

					returnMessageToClient.setResponse(Response.PRODUCT_NOT_IN_INVENTORY);
				}

			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// returnMessageToClient.setObject(customerFromDB);
		return returnMessageToClient;
	}

	public static void UpdateRefundStatus(FullMessage messageFromClient) throws SQLException {

		FullMessage returnMessageToClient = messageFromClient;
		String orderNumber = (String) returnMessageToClient.getObject();
		String condition = "OrderNumber='" + orderNumber + "'";
		mainQuery.updateTuple("orders", "RefundStatus='" + RefundStatus.WAITING + "'", condition);
	}

	public static FullMessage updateOrdersStatusOnDb(FullMessage messageFromClient) throws SQLException {
		ArrayList<Order> orders = new ArrayList<>();
		orders = new ArrayList<Order>((Collection<? extends Order>) messageFromClient.getObject());// copy the array
		FullMessage returnMessageToClient = messageFromClient; // that we got from
		// the controller
		String orderStatus = null;

		for (Order order : orders) {
			switch (order.getOrderstatus()) {
			case APPROVED:
				orderStatus = "APPROVED";
				break;
			case UN_APPROVED:
				orderStatus = "UN_APPROVED";
				break;
			case COMPLETED:
				orderStatus = "COMPLETED";
				break;
			case CANCEL:
				orderStatus = "CANCEL";
				break;
			case APPROVED_CANCEL:
				orderStatus = "APPROVED_CANCEL";
				break;

			default:
				break;
			}

			mainQuery.updateTuple("orders", "OrderStatus='" + orderStatus + "'",
					"orderNumber='" + order.getOrderNumber() + "'");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			switch (orderStatus) {
			case "APPROVED":
				mainQuery.updateTuple("orders", "ActualOrderDate='" + timestamp + "'",
						"orderNumber='" + order.getOrderNumber() + "'");
				returnMessageToClient.setResponse(Response.MANAGE_ORDER_APPROVED_SUCCEEDED_WRITING_INTO_DB);
				return returnMessageToClient;

			case "CANCEL":
				mainQuery.updateTuple("orders", "OrderDate='" + timestamp + "'",
						"orderNumber='" + order.getOrderNumber() + "'");
				returnMessageToClient.setResponse(Response.MANAGE_ORDER_CANCEL_SUCCEEDED_WRITING_INTO_DB);
				return returnMessageToClient;

			case "UN_APPROVED":
				mainQuery.updateTuple("orders", "OrderDate='" + timestamp + "'",
						"orderNumber='" + order.getOrderNumber() + "'");
				returnMessageToClient.setResponse(Response.MANAGE_ORDER_UN_APPROVED_SUCCEEDED_WRITING_INTO_DB);
				return returnMessageToClient;

			case "COMPLETED":
				mainQuery.updateTuple("orders", "OrderDate='" + timestamp + "'",
						"orderNumber='" + order.getOrderNumber() + "'");
				returnMessageToClient.setResponse(Response.MANAGE_ORDER_COMPLETED_SUCCEEDED_WRITING_INTO_DB);
				return returnMessageToClient;

			case "APPROVED_CANCEL":
				if (order.getActualDate() == null) {
					mainQuery.updateTuple("orders", "ActualOrderDate='" + timestamp + "'",
							"orderNumber='" + order.getOrderNumber() + "'");
				}
				mainQuery.updateTuple("orders", "OrderDate='" + timestamp + "'",
						"orderNumber='" + order.getOrderNumber() + "'");
				returnMessageToClient.setResponse(Response.MANAGE_ORDER_APPROVED_CANCEL_SUCCEEDED_WRITING_INTO_DB);
				return returnMessageToClient;
			}

		}
		return null;
	}

	public static FullMessage getTheDiffrenceTimeBetweenDates(FullMessage messageFromClient) throws SQLException {
		ArrayList<String> list = (ArrayList<String>) messageFromClient.getObject();

		Double TotalPrice = 0.0;
		Double Balance = 0.0;
		Double NewBalance = 0.0;
		OrderStatus orderstatus = null;
		String msg=null;

		String EstimatedString = list.get(0);
		Timestamp Estimated = Timestamp.valueOf(EstimatedString);
		String Actualstring = list.get(1);
		Timestamp Actual = Timestamp.valueOf(Actualstring);
		String OrderDateString = list.get(1);
		Timestamp OrderDate = Timestamp.valueOf(OrderDateString);
		String OrderNumber = list.get(2);
		String CustomerID = null;

		long diff = Actual.getTime() - Estimated.getTime();
		long Seconds = TimeUnit.MILLISECONDS.toSeconds(diff);

		long diff1 = OrderDate.getTime() - Estimated.getTime();
		long OrderDateSeconds = TimeUnit.MILLISECONDS.toSeconds(diff1);

		ResultSet rs = mainQuery.getRowFromDB("orders", "OrderNumber='" + OrderNumber + "'");
		if (rs.next()) {
			CustomerID = rs.getString(2);
		}
		rs.close();

		ResultSet rs1 = mainQuery.getRowFromDB("customer", "ID='" + CustomerID + "'");
		if (rs1.next()) {
			Balance = rs1.getDouble(10);
		}
		rs1.close();

		ResultSet rs2 = mainQuery.getRowFromDB("orders", "OrderNumber='" + OrderNumber + "'");
		if (rs2.next()) {
			orderstatus = OrderStatus.valueOf(rs2.getString(4));
			TotalPrice = rs2.getDouble(9);
		}
		rs2.close();

		if (orderstatus.equals(OrderStatus.CANCEL)) {
			if (Seconds > 10800 || Seconds < 0) {
				NewBalance = TotalPrice + Balance;
				mainQuery.updateTuple("customer", "Balance='" + NewBalance + "'", "ID='" + CustomerID + "'");
				msg="Customer Got Full Refund";
			} else if (Seconds < 10800 && Seconds > 5400) {
				TotalPrice = TotalPrice / 2;
				NewBalance = Balance + TotalPrice;
				mainQuery.updateTuple("customer", "Balance='" + NewBalance + "'", "ID='" + CustomerID + "'");
				msg="Customer Got 50% Refund";
			} else {
				msg="Customer Didn't Get Refund";
			}
		} else {
			// if(orderstatus.equals(OrderStatus.COMPLETED)) {
			if (OrderDateSeconds > 0) {
				NewBalance = TotalPrice + Balance;
				mainQuery.updateTuple("customer", "Balance='" + NewBalance + "'", "ID='" + CustomerID + "'");
				FullMessage messageToClient = new FullMessage(Request.THE_SUBRACTED_DATE_TIME_DONE,
						Response.GET_THE_SUBRACTED_DATE_TIME_SUCCEDED_FOR_DELIVERY, null);
				return messageToClient;
			} else {
				FullMessage messageToClient = new FullMessage(Request.THE_SUBRACTED_DATE_TIME_DONE,
						Response.GET_THE_SUBRACTED_DATE_TIME_SUCCEDED_FOR_DELIVERY, null);
				return messageToClient;
			}
		}
		// }
		// need to send the messageForUser in the object for the controller
		FullMessage messageToClient = new FullMessage(Request.THE_SUBRACTED_DATE_TIME_DONE,
				Response.GET_THE_SUBRACTED_DATE_TIME_SUCCEDED,msg);
		return messageToClient;

	}

}
