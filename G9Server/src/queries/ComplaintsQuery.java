package queries;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Orders.Branch;
import Orders.Order;
import Orders.OrderStatus;
import Orders.TypeOfSupply;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Response;
import customerService.Complaint;

public class ComplaintsQuery {

	public static FullMessage showComplaintsForUser(FullMessage messageFromClient) throws SQLException {

		FullMessage returnMessageToClient = messageFromClient;
		List<Complaint> complaintList = new ArrayList<Complaint>();
		ResultSet rs = mainQuery.SelectAllFromDB("complaints");
		try {

			if (!rs.isBeforeFirst()) {
				returnMessageToClient.setResponse(Response.NO_COMPLAINTS);
				returnMessageToClient.setObject(null);
				return returnMessageToClient;
			}
			while (rs.next()) {
				Complaint complaintFromDB = convertToComplaint(rs);
				if (complaintFromDB != null) {
					complaintList.add(complaintFromDB);

				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		returnMessageToClient.setObject(complaintList);
		returnMessageToClient.setResponse(Response.COMPLAINTS_FOUND);
		System.out.println((returnMessageToClient.getRequest().toString()));

		return returnMessageToClient;
	}

	private static Complaint convertToComplaint(ResultSet rs) {
		try {
			int complaintNumber = rs.getInt(1);
			String customerId = rs.getString(2);
			String orderNumber = rs.getString(3);
			String firstName = rs.getString(4);
			String lastName = rs.getString(5);
			Double totalPrice = rs.getDouble(6);
			String text = rs.getString(7);

			return new Complaint(complaintNumber, customerId, orderNumber, firstName, lastName, totalPrice, text);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static FullMessage AddComplaintToDB(FullMessage messageFromClient) throws SQLException, ParseException {
		Complaint complaint = (Complaint) messageFromClient.getObject();
		FullMessage returnMessageToClient = messageFromClient;
		int complaintNumber = complaint.getComplaintNumber();
		String customerId = complaint.getCustomerId();
		String complaintDate = complaint.getComplaintDate();
		String branchName = complaint.getBranchName();
		System.out.println(branchName);
		String orderNumber = complaint.getOrderNumber();
		double totalPrice = complaint.getTotalPrice();
		String text = complaint.getText();

		mainQuery.InsertOneRowIntoComplaintTable(complaintNumber, customerId, complaintDate, branchName, orderNumber,
				totalPrice, text);
		return returnMessageToClient;

	}
}
