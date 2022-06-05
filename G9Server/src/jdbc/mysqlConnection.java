package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mysqlConnection {
	public static Connection conn;


	/* Connect to DB */
	public static boolean connectToDB(String DBName, String DBUserName, String DBPassword) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception ex) {
			/* handle the error */
			System.out.println("Driver definition failed");

			return false;
		}

		try {
			conn = DriverManager.getConnection(DBName, DBUserName, DBPassword);

			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println(""
					+ "+SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;

		}
		return true;
	}

	/* Return the Connection */
	public Connection getConnection() {
		return conn;
	}

}
