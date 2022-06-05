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
		ResultSet rs = mainQuery.SelectAllFromDB("complaint");
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
			System.out.println(complaintList);
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
			int complaintID = rs.getInt(1);
			int complaintnum = rs.getInt(2);
			String customerID = rs.getString(3);
			int ordernum = rs.getInt(4);
			Timestamp complaintdate = rs.getTimestamp(5);
			Branch branchname = Branch.valueOf(rs.getString(6));
			String text = rs.getString(7);

			return new Complaint(complaintID, complaintnum, customerID, ordernum, complaintdate, branchname, text, 0.0);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static FullMessage AddComplaintToDB(FullMessage messageFromClient) throws SQLException, ParseException {
		return messageFromClient;
		/*
		 * Complaint complaint = (Complaint) messageFromClient.getObject(); FullMessage
		 * returnMessageToClient = messageFromClient; int complaintNumber =
		 * complaint.getComplaintNumber(); String customerId =
		 * complaint.getCustomerId(); String complaintDate =
		 * complaint.getComplaintDate(); String branchName = complaint.getBranchName();
		 * System.out.println(branchName); String orderNumber =
		 * complaint.getOrderNumber(); double totalPrice = complaint.getTotalPrice();
		 * String text = complaint.getText();
		 * 
		 * mainQuery.InsertOneRowIntoComplaintTable(complaintNumber, customerId,
		 * complaintDate, branchName, orderNumber, totalPrice, text);
		 */

	}
}
