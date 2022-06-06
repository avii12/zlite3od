package queries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import AllUsers.ConfirmationStatus;
import AllUsers.Users;
import Orders.Branch;
import Orders.DominantColor;
import Orders.FlowerColor;
import Orders.ItemCategory;
import Orders.ItemType;
import Orders.OrderStatus;
import Orders.RefundStatus;
import Orders.TypeOfSupply;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Response;
import ServerGUIControllers.ServerGuiController;
import Survey.SurveyAnswers;

/**
 * @author Obied
 *
 */
public class mainQuery {
	/**
	 * The var of the connection to the main db
	 */
	private static Connection con;
	/**
	 * The var of the connection to external db
	 */
	private static Connection externaldbCon;
	/**
	 * Setter of the connection of  db
	 */
	public static void setConnectionFromServerToDB(Connection connection) {
		con = connection;
	}
	/**
	 * Setter of the connection of external db
	 */
	public static void setConnectionFromServerToExternalDB(Connection connection) {
		externaldbCon = connection;
	}

	/**
	 * This method will be called once to import the data of users management from the allusers DB.
	 * 
	 * @return rs
	 */
	public static ResultSet getExternalDBData() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM allusers.usermanagement";
		try {
			pstmt = externaldbCon.prepareStatement(query);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * Method for inserting Survey into DB
	 * @param answerANDid
	 * @throws ParseException
	 */
	public static void InsertOneRowIntosurveyAnswersTable(SurveyAnswers answerANDid) throws ParseException {

		String query = "INSERT INTO surveyanswers (SurveyID,CustomerID, QuestionNumber,QuestionAnswer) VALUES ("
				+ "?, ?, ?,?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, answerANDid.getSurveyID());
			pstmt.setString(2, answerANDid.getCustomerID());
			pstmt.setString(3, answerANDid.getQuestionNumber());
			pstmt.setString(4, answerANDid.getQuestionAnswer());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method For Inserting Row In login table
	 * @param userName
	 * @param Password
	 * @param userId
	 * @param userType
	 */
	public static void insertOneRowIntoLoginTable(String userName, String Password, String userId, String userType) {
		String query = "INSERT INTO zli_db.login ( Username, Password, UserID, UserType) VALUES( '" + userName + "' , '"
				+ Password + "' , '" + userId + "' , '" + userType + "' )";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
		}
	}


	/**
	 * Method For inserting into customer table
	 * @param user
	 * @throws ParseException
	 */
	public static void InsertOneRowIntoCustomerTable(Users user) throws ParseException {

		String query = "INSERT INTO customer (" + "ID," + "FirstName," + " LastName," + " Email," + " PhoneNumber,"
				+ " UserType," + " LogInStatus," + " ConfirmationStatus," + " CreditCard," + " Balance) VALUES ("
				+ "?, ?, ?, ?, ?, ?, ?, ?,?,?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, user.getUserID());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhoneNumber());
			pstmt.setString(6, user.getUserType());
			pstmt.setBoolean(7, user.isLogInStatus());

			pstmt.setString(8, String.valueOf(user.getConfirmationstatus()));
			pstmt.setString(9, "");

			pstmt.setString(8, String.valueOf(user.getConfirmationstatus()));
			pstmt.setString(9, "100");

			pstmt.setDouble(10, 0);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method For inserting into Worker table
	 * @param user
	 * @throws ParseException
	 */
	public static void InsertOneRowIntoWorkerTable(Users user) throws ParseException {

		String query = "INSERT INTO worker (" + "ID," + "FirstName," + " LastName," + " Email," + " PhoneNumber,"
				+ " UserType," + " LogInStatus," + " ConfirmationStatus," + " Sales) VALUES ("
				+ "?, ?, ?, ?, ?, ?, ?, ?,?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, user.getUserID());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhoneNumber());
			pstmt.setString(6, user.getUserType());
			pstmt.setBoolean(7, user.isLogInStatus());

			pstmt.setString(8, String.valueOf(user.getConfirmationstatus()));
			pstmt.setString(9, "");

			pstmt.setString(8, String.valueOf(user.getConfirmationstatus()));
			pstmt.setString(9, "0");

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method For inserting into service specialist table
	 * @param user
	 * @throws ParseException
	 */
	public static void InsertOneRowIntoServicesTable(Users user) throws ParseException {

		String query = "INSERT INTO servicespecialist (" + "ID," + "FirstName," + " LastName," + " Email,"
				+ " PhoneNumber," + " UserType," + " LogInStatus," + " ConfirmationStatus) VALUES ("
				+ "?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, user.getUserID());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhoneNumber());
			pstmt.setString(6, user.getUserType());
			pstmt.setBoolean(7, user.isLogInStatus());
			pstmt.setString(8, String.valueOf(user.getConfirmationstatus()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method For inserting into customer service worker  table
	 * @param user
	 * @throws ParseException
	 */
	public static void InsertOneRowIntoCostomerServicesTable(Users user) throws ParseException {

		String query = "INSERT INTO customerserviceworker (" + "ID," + "FirstName," + " LastName," + " Email,"
				+ " PhoneNumber," + " UserType," + " LogInStatus," + " ConfirmationStatus) VALUES ("
				+ "?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, user.getUserID());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhoneNumber());
			pstmt.setString(6, user.getUserType());
			pstmt.setBoolean(7, user.isLogInStatus());
			pstmt.setString(8, String.valueOf(user.getConfirmationstatus()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void InsertOneRowIntoDeliveryPersonTable(Users user) throws ParseException {

		String query = "INSERT INTO deliveryperson (" + "ID," + "FirstName," + " LastName," + " Email,"
				+ " PhoneNumber," + " UserType," + " LogInStatus," + " ConfirmationStatus," + " Branch) VALUES ("
				+ "?, ?, ?, ?, ?, ?, ?, ?,?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, user.getUserID());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhoneNumber());
			pstmt.setString(6, user.getUserType());
			pstmt.setBoolean(7, user.isLogInStatus());

			pstmt.setString(8, String.valueOf(user.getConfirmationstatus()));
			pstmt.setString(9, "");

			pstmt.setString(8, String.valueOf(user.getConfirmationstatus()));
			pstmt.setString(9, "TheSecretGarden");

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertOneRowIntoCustomerTable(String id, String FirstName, String LastName, String Email,
			String PhoneNumber, String UserType, String LogInStatus, ConfirmationStatus Status, String CreditCard,
			Double Balance) {
		String query = "INSERT INTO zli_db.customer (ID,FirstName,LastName,Email,PhoneNumber,UserType,LogInStatus,ConfirmationStatus,CreditCard,Balance) VALUES( '"
				+ id + "' , '" + FirstName + "' , '" + LastName + "' , '" + Email + "' , '" + PhoneNumber + "' ,'"
				+ UserType + "' , '" + LogInStatus + "' ,  '" + Status + "' , '" + CreditCard + "' , '" + Balance
				+ "')";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
		}
	}

	public static void insertOneRowIntoBranchManagerAndDeliveryTable(String tabelname, String id, String FirstName,
			String LastName, String Email, String PhoneNumber, String UserType, String LogInStatus,
			ConfirmationStatus Status, Branch branch) {
		String query = "INSERT INTO zli_db." + tabelname
				+ "(ID,FirstName,LastName,Email,PhoneNumber,UserType,LogInStatus,ConfirmationStatus,Branch) VALUES( '"
				+ id + "' , '" + FirstName + "' , '" + LastName + "' , '" + Email + "' , '" + PhoneNumber + "' ,'"
				+ UserType + "' , '" + LogInStatus + "' ,  '" + Status + "' , '" + branch + "')";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
		}
	}

	public static void insertOneRowIntoCeoZliAndServiceSpAndCustomerServiceTable(String tabelname, String id,
			String FirstName, String LastName, String Email, String PhoneNumber, String UserType, String LogInStatus,
			ConfirmationStatus Status) {
		String query = "INSERT INTO zli_db." + tabelname
				+ "(ID,FirstName,LastName,Email,PhoneNumber,UserType,LogInStatus,ConfirmationStatus) VALUES( '" + id
				+ "' , '" + FirstName + "' , '" + LastName + "' , '" + Email + "' , '" + PhoneNumber + "' ,'" + UserType
				+ "' , '" + LogInStatus + "' ,  '" + Status + "')";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
		}
	}

	public static void insertOneRowIntoWorkerTable(String id, String FirstName, String LastName, String Email,
			String PhoneNumber, String UserType, String LogInStatus, ConfirmationStatus Status, String Sales) {
		String query = "INSERT INTO zli_db.worker (ID,FirstName,LastName,Email,PhoneNumber,UserType,LogInStatus,ConfirmationStatus,Sales) VALUES( '"
				+ id + "' , '" + FirstName + "' , '" + LastName + "' , '" + Email + "' , '" + PhoneNumber + "' ,'"
				+ UserType + "' , '" + LogInStatus + "' ,  '" + Status + "' , '" + Sales + "')";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
		}
	}

	public static ResultSet getTuple(String tableName, String condition) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = con.prepareStatement("SELECT * FROM zli_db." + tableName + " WHERE " + condition);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet getTupleAccordingToPrice(String tableName, int minPrice, int maxPrice) {

		PreparedStatement statement = null;
		ResultSet rs = null;
		try {

			statement = con.prepareStatement("SELECT * FROM zli_db." + tableName + "  WHERE " + "Price between'"
					+ minPrice + "' AND '" + maxPrice + "'");

			rs = statement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet SelectAllProductsFromDB(String tableName) {/// select table
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String query = "SELECT * FROM zli_db." + tableName;
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet getColumnFromTableInDB(String tableName, String columnName, String condition) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT " + columnName + " FROM zli_db." + tableName + "WHERE" + condition;
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void updateTuple(String tableName, String columnSet, String condition) {
		PreparedStatement statement = null;
		try {
			String query = "UPDATE zli_db." + tableName + " SET " + columnSet + " WHERE " + "(" + condition + ")";
			statement = con.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void updateTupleWithNoCondition(String tableName, String columnSet) {
		PreparedStatement statement = null;
		try {
			String query = "UPDATE zli_db." + tableName + " SET " + columnSet + "";
			statement = con.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static ResultSet SelectAllFromDB(String tableName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM zli_db." + tableName + "";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	/*
	 * java.sql.Statement statement = conn.createStatement(); String sql =
	 * "SELECT MAX(GiaSP) from tbsanpham where manhom = '"+manhom+"'"; ResultSet rs
	 * = statement.executeQuery(sql); if (rs.next()) { max = rs.getInt(1);
	 */

	public static void UpdateDateForPDFInsert() throws SQLException {

		String updateSQL = "UPDATE summarizedpdf " + "SET Date = ? " + "WHERE surveyID=?";
		// String updateSQL = "UPDATE candidates " + "SET resume = ? " + "WHERE id=?";

		PreparedStatement pstmt = con.prepareStatement(updateSQL);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		Timestamp dateTimeStamp = Timestamp.valueOf(date);
		System.out.println(dateTimeStamp);
		pstmt.setTimestamp(1, dateTimeStamp);
		pstmt.setString(2, "1");
		pstmt.executeUpdate();

	}

	public static void InsertPDF(String path) throws SQLException, FileNotFoundException {

		String updateSQL = "UPDATE summarizedpdf " + "SET File = ? " + "WHERE surveyID=?";

		PreparedStatement pstmt = con.prepareStatement(updateSQL);

		File file = new File(path);
		FileInputStream input = new FileInputStream(file);

		pstmt.setBinaryStream(1, input);
		pstmt.setString(2, "1000");
		pstmt.executeUpdate();
		SetDateForPDFTable();

	}
	
	public static void SetDateForPDFTable() throws SQLException, FileNotFoundException {

		String updateSQL = "UPDATE summarizedpdf " + "SET Date = ? " + "WHERE surveyID=?";
		// String updateSQL = "UPDATE candidates " + "SET resume = ? " + "WHERE id=?";

		PreparedStatement pstmt = con.prepareStatement(updateSQL);

		Timestamp date = new Timestamp(System.currentTimeMillis());
		System.out.println(date);
		pstmt.setTimestamp(1, date);
		pstmt.setString(2, "1000");
		pstmt.executeUpdate();

	}

	@SuppressWarnings("unused")
	public static FullMessage ShowPDF(FullMessage message) throws SQLException, IOException {

		String selectSQL = "SELECT File FROM summarizedpdf WHERE surveyID=?";
		PreparedStatement pstmt = con.prepareStatement(selectSQL);

		pstmt.setString(1, "1000");
		ResultSet rs = pstmt.executeQuery();

		File file = new File("C:\\Reports\\PDF_from_db.pdf");

		if (rs.next()) {

			if (rs.getBlob(1) != null) {

				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream("File");
				message.setResponse(Response.PDF_FOUND);
				message.setObject(file.getAbsolutePath());
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}

			} else {
				message.setResponse(Response.PDF_NOT_FOUND);
			}
			return message;

		}
		message.setResponse(Response.PDF_NOT_FOUND);
		return message;
	}

	public static void InsertOneRowIntoOrderTable(String orderNumber, String customerID, Branch branch,
			OrderStatus orderstatus, Timestamp orderDate, Timestamp estimatedDate, Timestamp actualDate,
			TypeOfSupply supplyType, double totalPrice, double deliveryCost, String customerName,
			String deliveryAddress, String Item, String PhoneNumber, String greetingCard) throws ParseException {

		// String estimatedSupplyDateAndTime =
		// DateTimeHandler.convertMySqlDateTimeFormatToString(estimatedSupplyDateTime);
		String query = "INSERT INTO orders (" + " OrderNumber," + " CustomerID," + " Branch," + " OrderStatus,"
				+ " OrderDate," + " EstimatedOrderDate," + " ActualOrderDate," + "SupplyType," + "TotalPrice,"
				+ "DeliveryCost," + "CustomerName," + "DeliveryAddress," + "AllItems," + "PhoneNumber,"
				+ "GreetingCard) VALUES (" + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, orderNumber);
			pstmt.setString(2, customerID);
			pstmt.setString(3, String.valueOf(branch));
			pstmt.setString(4, String.valueOf(orderstatus));
			pstmt.setTimestamp(5, null);
			pstmt.setTimestamp(6, estimatedDate);
			pstmt.setTimestamp(7, null);
			pstmt.setString(8, String.valueOf(supplyType));
			pstmt.setString(9, String.valueOf(totalPrice));
			pstmt.setString(10, String.valueOf(deliveryCost));
			pstmt.setString(11, customerName);
			pstmt.setString(12, deliveryAddress);
			pstmt.setString(13, Item);
			pstmt.setString(14, PhoneNumber);
			pstmt.setString(15, greetingCard);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void InsertOneRowIntoComplaintTable(int complaintid, int complaintnumber, String customerid,
			int ordernumber, Timestamp date, Branch branch, String text) throws ParseException {

		String query = "INSERT INTO complaint (" + " ComplaintID," + " ComplaintNum," + " CustomerID," + " OrderID,"
				+ " Date," + " Branch," + " Text) VALUES (" + "?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, complaintid);
			pstmt.setInt(2, complaintnumber);
			pstmt.setString(3, customerid);
			pstmt.setInt(4, ordernumber);
			pstmt.setTimestamp(5, date);
			pstmt.setString(6, String.valueOf(branch));
			pstmt.setString(7, text);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void InsertOneRowIntoCatalogTable(String itemID, ItemCategory itemCategory, FlowerColor color,
			String itemName, double price, String picturePath, String greetingCard, ItemType type,
			DominantColor dominantColor, int amount) throws ParseException {

		String query = "INSERT INTO catalog (" + "ID," + "Category," + " Color," + " ItemName," + " Price,"
				+ " PicturePath," + " GreetingCard," + " Type," + "DominantColor," + "Amount) VALUES ("
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, 0);
			pstmt.setString(2, String.valueOf(itemCategory));
			pstmt.setString(3, String.valueOf(color));
			pstmt.setString(4, itemName);
			pstmt.setDouble(5, price);
			pstmt.setString(6, picturePath);
			pstmt.setString(7, greetingCard);
			pstmt.setString(8, String.valueOf(type));
			pstmt.setString(9, String.valueOf(dominantColor));
			pstmt.setInt(10, amount);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void DeleteRowFromDB(String condition, String tableName) {
		String query = "DELETE FROM zli_db." + tableName + " WHERE (" + condition + ")";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet getRowFromDB(String tableName, String condition) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM zli_db." + tableName + "  WHERE " + condition;
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet getDifferenceBetweenDate(String tableName, Timestamp FirstDate, Timestamp SecoundDate) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT TIMESTAMPDIFF('SECOND'" + "'" + FirstDate + "'" + "," + "'"
					+ SecoundDate + "'" + ")" + "time" + " FROM zli_db." + tableName + "");
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet getDifferenceBetweenDates(String tableName, Timestamp FirstDate, Timestamp SecoundDate) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT TIMEDIFF('" + FirstDate + "'" + "," + "'" + SecoundDate + "'" + ")"
					+ " FROM zli_db." + tableName + "");
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet getColumnFromTableInDB(String tableName, String columnName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT " + columnName + " FROM zli_db." + tableName;
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
