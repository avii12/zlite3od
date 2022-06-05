package queries;

import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import AllUsers.ConfirmationStatus;
import AllUsers.Customer;
import AllUsers.User;
import Report.customer;
import Orders.Branch;
import Orders.DominantColor;
import Orders.FlowerColor;
import Orders.Item;
import Orders.ItemCategory;
import Orders.ItemType;
import Report.IncomeReport;
import Report.OrderReport;
import Report.ReportType;
import Report.Reports;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;

public class ReportQuery {

	public static FullMessage GetOrderReport(FullMessage messageFromClient) throws SQLException {
		String[] DateAndBranch = (String[]) messageFromClient.getObject();

		ArrayList<String> OrderReport = new ArrayList<String>();
		String orderDate = "";
		String date = DateAndBranch[0];
		String condition = "Branch='" + DateAndBranch[2] + "' AND ReportDate='"+date+"'";
		ResultSet rs = mainQuery.getTuple("reportorder", condition);
	
		try {
			if (!rs.isBeforeFirst()) {
				messageFromClient.setResponse(Response.NO_REPORT);
				messageFromClient.setObject(null);
				return messageFromClient;
			}

			while (rs.next()) {
				String idReport = rs.getString(1);
				OrderReport.add(idReport);
				String ReportDate = rs.getString(2);
				OrderReport.add(ReportDate);
				String NumOfOrders = rs.getString(3);
				OrderReport.add(NumOfOrders);
				String NumOfLateOrders = rs.getString(4);
				OrderReport.add(NumOfLateOrders);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageFromClient.setResponse(Response.REPORT_FOUND);
		messageFromClient.setObject(OrderReport);
		return messageFromClient;

	}

	public static FullMessage GetIncomeReportForManager(FullMessage messageFromClient) throws SQLException {
		String[] TypeAndDate = (String[]) messageFromClient.getObject();
		String date = TypeAndDate[0];
		String condition = "BranchName='" + TypeAndDate[2] + "' AND (Date='" + date + "')";

		ArrayList<String> IncomeReport = new ArrayList<String>();
		ResultSet rs = mainQuery.getTuple("reportdetails", condition);
		try {
			if (!rs.isBeforeFirst()) {
				messageFromClient.setResponse(Response.NO_REPORT);
				messageFromClient.setObject(null);
				return messageFromClient;
			}
			while (rs.next()) {
				String idReport = rs.getString(1);
				IncomeReport.add(idReport);
				String Reportdate = rs.getString(2);
				IncomeReport.add(Reportdate);
				String TotalPrice = rs.getString(3);
				IncomeReport.add(TotalPrice);
				String NumOfOrders = rs.getString(4);
				IncomeReport.add(NumOfOrders);
				String BranchName = rs.getString(5);
				IncomeReport.add(BranchName);

			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageFromClient.setObject(IncomeReport);
		return messageFromClient;
	}

	public static FullMessage GetManagerID(FullMessage messageFromClient) throws SQLException {

		String BranchID = (String) messageFromClient.getObject();
		String condition = "ID= " + BranchID;
		ArrayList<String> branch1 = new ArrayList<String>();
		ResultSet rs = mainQuery.getTuple("branchmanager", condition);
		try {
			if (!rs.isBeforeFirst()) {
				messageFromClient.setResponse(Response.NO_REPORT);
				messageFromClient.setObject(null);
				return messageFromClient;
			}
			while (rs.next()) {
				String branch = rs.getString(9);
				branch1.add(branch);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageFromClient.setObject(branch1);
		messageFromClient.setResponse(Response.REPORT_FOUND);
		return messageFromClient;

	}

	public static FullMessage GetReportFromDB(FullMessage messageFromClient) throws SQLException {

		FullMessage returnMessageToClient = messageFromClient;
		ArrayList<Reports> Report = new ArrayList<Reports>();
		int flag = 0;
		String[] TypeAndDate = (String[]) messageFromClient.getObject();
		String date = TypeAndDate[0];
		String condition = "Branch='" + TypeAndDate[2] + "' AND (Date='" + date + "')";
		ResultSet rs = mainQuery.getTuple("reports", condition);
		try {
			// If the row doesn't exist in login Table
			if (!rs.isBeforeFirst()) {
				returnMessageToClient.setResponse(Response.NO_REPORT);
				returnMessageToClient.setObject(null);
				return returnMessageToClient;
			}

			while (rs.next()) {
				Reports reports = convertToReport(rs);
				if (reports != null) {
					Report.add(reports);
				}
				if (TypeAndDate[1].equals(reports.getReportType().toString())) {
					flag = 1;
					returnMessageToClient.setResponse(Response.REPORT_FOUND);
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag == 0)
			returnMessageToClient.setResponse(Response.NO_REPORT);
		returnMessageToClient.setObject(Report);
		return returnMessageToClient;

	}

	private static Reports convertToReport(ResultSet rs) {
		try {
			int ReportID = rs.getInt(1);
			ReportType Type = ReportType.valueOf(rs.getString(2));
			String Date = rs.getString(3);
			Branch branch = Branch.valueOf(rs.getString(4));
			return new Reports(ReportID, Type, Date, branch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static FullMessage GetNumOfComplaintByQuarterly(FullMessage messageFromClient) throws SQLException {
		FullMessage returnMessageToClient = messageFromClient;
		String[] quarterly = (String[]) messageFromClient.getObject();
		int[] NumOfComlaint = new int[3];
		NumOfComlaint[0] = 0;
		NumOfComlaint[1] = 0;
		NumOfComlaint[2] = 0;
		String fromDate, toDate, condition;
		switch (quarterly[0]) {
		case "1-3":
			fromDate = quarterly[2] + "-01-01 00:00:00";
			toDate = quarterly[2] + "-01-31 23:59:59";
			NumOfComlaint[0] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);

			fromDate = quarterly[2] + "-02-01 00:00:00";
			toDate = quarterly[2] + "-02-28 23:59:59";
			NumOfComlaint[1] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);

			fromDate = quarterly[2] + "-03-01 00:00:00";
			toDate = quarterly[2] + "-03-31 23:59:59";
			NumOfComlaint[2] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);
			
			break;

		case "4-6":
			fromDate = quarterly[2] + "-04-01 00:00:00";
			toDate = quarterly[2] + "-04-30 23:59:59";
			NumOfComlaint[0] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);

			fromDate = quarterly[2] + "-05-01 00:00:00";
			toDate = quarterly[2] + "-05-31 23:59:59";
			NumOfComlaint[1] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);

			fromDate = quarterly[2] + "-06-01 00:00:00";
			toDate = quarterly[2] + "-06-30 23:59:59";
			NumOfComlaint[2] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);
			
			break;
		case "7-9":
			fromDate = quarterly[2] + "-07-01 00:00:00";
			toDate = quarterly[2] + "-07-31 23:59:59";
			NumOfComlaint[0] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);

			fromDate = quarterly[2] + "-08-01 00:00:00";
			toDate = quarterly[2] + "-08-31 23:59:59";
			NumOfComlaint[1] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);

			fromDate = quarterly[2] + "-09-01 00:00:00";
			toDate = quarterly[2] + "-09-30 23:59:59";
			NumOfComlaint[2] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);
			
			break;
		case "10-12":
			fromDate = quarterly[2] + "-10-01 00:00:00";
			toDate = quarterly[2] + "-10-31 23:59:59";
			NumOfComlaint[0] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);

			fromDate = quarterly[2] + "-11-01 00:00:00";
			toDate = quarterly[2] + "-11-30 23:59:59";
			NumOfComlaint[1] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);

			fromDate = quarterly[2] + "-12-01 00:00:00";
			toDate = quarterly[2] + "-12-31 23:59:59";
			NumOfComlaint[2] = GetNumOfComplaint(fromDate, toDate, quarterly[1], messageFromClient);
			
			break;

		}
		returnMessageToClient.setObject(NumOfComlaint);
		return returnMessageToClient;
	}

	public static int GetNumOfComplaint(String fromDate, String ToDate, String BranchName,
			FullMessage messageFromClient) throws SQLException {
		int NumOfComlpaint = 0;
		String condition = "Branch='" + BranchName + "' AND (Date between '" + fromDate + "' AND '" + ToDate + "')";
		ResultSet rs = mainQuery.getTuple("complaint", condition);
		try {
			// If the row doesn't exist in login Table
			if (!rs.isBeforeFirst()) {
				messageFromClient.setResponse(Response.NO_REPORT);
				messageFromClient.setObject(null);
				return 0;
			}

			while (rs.next()) {
				NumOfComlpaint += rs.getInt(2);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageFromClient.setResponse(Response.REPORT_FOUND);
		return NumOfComlpaint;

	}

	public static double GetTotalPriceOfQuarterly(String fromDate, String ToDate, String BranchName,
			FullMessage messageFromClient) throws SQLException {
		double TotalPrice = 0;

		String condition = "Branch='" + BranchName + "' AND (EstimatedOrderDate between '" + fromDate + "' AND '"
				+ ToDate + "')";
		ResultSet rs = mainQuery.getTuple("orders", condition);
		try {
			// If the row doesn't exist in login Table
			if (!rs.isBeforeFirst()) {
				messageFromClient.setResponse(Response.NO_REPORT);
				messageFromClient.setObject(null);
				return 0;
			}

			while (rs.next()) {
				TotalPrice += rs.getDouble(9);
				String bb=rs.getString(2);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TotalPrice;

	}

	public static FullMessage GetReportForTwoQuarterly(FullMessage messageFromClient) throws SQLException {
		String[] infoForQuarterly = (String[]) messageFromClient.getObject();
		double[] Numforquarterly = new double[7];
		Numforquarterly[0] = 0;
		Numforquarterly[1] = 0;
		Numforquarterly[2] = 0;
		Numforquarterly[3] = 0;
		Numforquarterly[4] = 0;
		Numforquarterly[5] = 0;
		String fromDate, toDate;

		switch (infoForQuarterly[1]) {
		case "1-3":
			fromDate = infoForQuarterly[3] + "-01-01 00:00:00";
			toDate = infoForQuarterly[3] + "-01-31 23:59:59";
			Numforquarterly[0] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-02-01 00:00:00";
			toDate = infoForQuarterly[3] + "-02-28 23:59:59";
			Numforquarterly[1] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-03-01 00:00:00";
			toDate = infoForQuarterly[3] + "-03-31 23:59:59";
			Numforquarterly[2] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);

			break;
		case "4-6":
			fromDate = infoForQuarterly[3] + "-04-01 00:00:00";
			toDate = infoForQuarterly[3] + "-04-30 23:59:59";
			Numforquarterly[0] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-05-01 00:00:00";
			toDate = infoForQuarterly[3] + "-05-31 23:59:59";
			Numforquarterly[1] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-06-01 00:00:00";
			toDate = infoForQuarterly[3] + "-06-30 23:59:59";
			Numforquarterly[2] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);

			break;
		case "7-9":
			fromDate = infoForQuarterly[3] + "-07-01 00:00:00";
			toDate = infoForQuarterly[3] + "-07-30 23:59:59";
			Numforquarterly[0] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-08-01 00:00:00";
			toDate = infoForQuarterly[3] + "-08-31 23:59:59";
			Numforquarterly[1] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-09-01 00:00:00";
			toDate = infoForQuarterly[3] + "-09-30 23:59:59";
			Numforquarterly[2] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);

			break;
		case "10-12":
			fromDate = infoForQuarterly[3] + "-10-01 00:00:00";
			toDate = infoForQuarterly[3] + "-10-31 23:59:59";
			Numforquarterly[0] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-11-01 00:00:00";
			toDate = infoForQuarterly[3] + "-11-30 23:59:59";
			Numforquarterly[1] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-12-01 00:00:00";
			toDate = infoForQuarterly[3] + "-12-31 23:59:59";
			Numforquarterly[2] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);

			break;

		}// switch1

		switch (infoForQuarterly[2]) {
		case "1-3":
			fromDate = infoForQuarterly[3] + "-01-01 00:00:00";
			toDate = infoForQuarterly[3] + "-01-31 23:59:59";
			Numforquarterly[3] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-02-01 00:00:00";
			toDate = infoForQuarterly[3] + "-02-28 23:59:59";
			Numforquarterly[4] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-03-01 00:00:00";
			toDate = infoForQuarterly[3] + "-03-31 23:59:59";
			Numforquarterly[5] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);

			break;
		case "4-6":
			fromDate = infoForQuarterly[3] + "-04-01 00:00:00";
			toDate = infoForQuarterly[3] + "-04-30 23:59:59";
			Numforquarterly[3] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-05-01 00:00:00";
			toDate = infoForQuarterly[3] + "-05-31 23:59:59";
			Numforquarterly[4] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-06-01 00:00:00";
			toDate = infoForQuarterly[3] + "-06-30 23:59:59";
			Numforquarterly[5] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);

			break;
		case "7-9":
			fromDate = infoForQuarterly[3] + "-07-01 00:00:00";
			toDate = infoForQuarterly[3] + "-07-30 23:59:59";
			Numforquarterly[3] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-08-01 00:00:00";
			toDate = infoForQuarterly[3] + "-08-31 23:59:59";
			Numforquarterly[4] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-09-01 00:00:00";
			toDate = infoForQuarterly[3] + "-09-30 23:59:59";
			Numforquarterly[5] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);

			break;
		case "10-12":
			fromDate = infoForQuarterly[3] + "-10-01 00:00:00";
			toDate = infoForQuarterly[3] + "-10-31 23:59:59";
			Numforquarterly[3] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-11-01 00:00:00";
			toDate = infoForQuarterly[3] + "-11-30 23:59:59";
			Numforquarterly[4] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			fromDate = infoForQuarterly[3] + "-12-01 00:00:00";
			toDate = infoForQuarterly[3] + "-12-31 23:59:59";
			Numforquarterly[5] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);

			break;
		}// switch2

		messageFromClient.setResponse(Response.REPORT_FOUND);
		messageFromClient.setObject(Numforquarterly);
		return messageFromClient;
	}

	public static FullMessage GetReportForTwoBranches(FullMessage messageFromClient) throws SQLException {
		String[] infoForQuarterly = (String[]) messageFromClient.getObject();
		double[] TotalPrice = new double[7];
		TotalPrice[0] = 0;
		TotalPrice[1] = 0;
		TotalPrice[2] = 0;
		TotalPrice[3] = 0;
		TotalPrice[4] = 0;
		TotalPrice[5] = 0;
		String fromDate, toDate;

		switch (infoForQuarterly[2]) {
		case "1-3":
			fromDate = infoForQuarterly[3] + "-01-01 00:00:00";
			toDate = infoForQuarterly[3] + "-01-31 23:59:59";
			TotalPrice[0] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[3] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			fromDate = infoForQuarterly[3] + "-02-01 00:00:00";
			toDate = infoForQuarterly[3] + "-02-28 23:59:59";
			TotalPrice[1] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[4] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			fromDate = infoForQuarterly[3] + "-03-01 00:00:00";
			toDate = infoForQuarterly[3] + "-03-31 23:59:59";
			TotalPrice[2] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[5] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			break;
		case "4-6":
			fromDate = infoForQuarterly[3] + "-04-01 00:00:00";
			toDate = infoForQuarterly[3] + "-04-30 23:59:59";
			TotalPrice[0] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[3] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			fromDate = infoForQuarterly[3] + "-05-01 00:00:00";
			toDate = infoForQuarterly[3] + "-05-31 23:59:59";
			TotalPrice[1] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[4] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			fromDate = infoForQuarterly[3] + "-06-01 00:00:00";
			toDate = infoForQuarterly[3] + "-06-30 23:59:59";
			TotalPrice[2] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[5] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			break;

		case "7-9":
			fromDate = infoForQuarterly[3] + "-07-01 00:00:00";
			toDate = infoForQuarterly[3] + "-07-31 23:59:59";
			TotalPrice[0] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[3] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			fromDate = infoForQuarterly[3] + "-08-01 00:00:00";
			toDate = infoForQuarterly[3] + "-08-31 23:59:59";
			TotalPrice[1] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[4] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			fromDate = infoForQuarterly[3] + "-09-01 00:00:00";
			toDate = infoForQuarterly[3] + "-09-30 23:59:59";
			TotalPrice[2] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[5] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			break;

		case "10-12":
			fromDate = infoForQuarterly[3] + "-10-01 00:00:00";
			toDate = infoForQuarterly[3] + "-10-31 23:59:59";
			TotalPrice[0] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[3] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			fromDate = infoForQuarterly[3] + "-11-01 00:00:00";
			toDate = infoForQuarterly[3] + "-11-30 23:59:59";
			TotalPrice[1] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[4] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			fromDate = infoForQuarterly[3] + "-12-01 00:00:00";
			toDate = infoForQuarterly[3] + "-12-31 23:59:59";
			TotalPrice[2] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[0], messageFromClient);
			TotalPrice[5] = GetTotalPriceOfQuarterly(fromDate, toDate, infoForQuarterly[1], messageFromClient);

			break;

		}

		messageFromClient.setResponse(Response.REPORT_FOUND);
		messageFromClient.setObject(TotalPrice);
		return messageFromClient;
	}


}
