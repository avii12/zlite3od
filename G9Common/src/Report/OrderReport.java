package Report;

import java.io.Serializable;

/**
 * 
 * Class description:

 * Order reports class contains all the information we get when we order the report
 * 
 * @author Maisalon, Safory
 * @author Seren ,Hanany.
 * @author Shoroq ,Heib.
 *
 */

public class OrderReport implements Serializable {
	
	/**
	 * report id
	 */
	public int ReportID;
	/**
	 * date of the report
	 */
	public String ReportDate;
	/**
	 * order id 
	 */
	public String OrderID;
	/**
	 * the date of the order
	 */
	public String OrderDate;
	/**
	 * the price of the order
	 */
	public double OrderPrice;

	/**
	 * @param reportID
	 * @param reportDate
	 * @param orderID
	 * @param orderDate
	 * @param orderPrice
	 */
	public OrderReport(int reportID, String reportDate, String orderID, String orderDate, double orderPrice) {
		super();
		ReportID = reportID;
		ReportDate = reportDate;
		OrderID = orderID;
		OrderDate = orderDate;
		OrderPrice = orderPrice;
	}

	/**
	 * @param orderReport
	 */
	public OrderReport(OrderReport orderReport) {
		this.ReportID = orderReport.getReportID();
		this.ReportDate = orderReport.getReportDate();
		this.OrderID = orderReport.getOrderID();
		this.OrderDate = orderReport.getOrderDate();
		this.OrderPrice = orderReport.getOrderPrice();

	}

	
	/**
	 * 
	 * setters and getters
	 */
	public int getReportID() {
		return ReportID;
	}

	public void setReportID(int reportID) {
		ReportID = reportID;
	}

	public String getReportDate() {
		return ReportDate;
	}

	public void setReportDate(String reportDate) {
		ReportDate = reportDate;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}

	public double getOrderPrice() {
		return OrderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		OrderPrice = orderPrice;
	}

	@Override
	public String toString() {
		return "OrderReport [ReportID=" + ReportID + ", ReportDate=" + ReportDate + ", OrderID=" + OrderID
				+ ", OrderDate=" + OrderDate + ", OrderPrice=" + OrderPrice + "]";
	}

}
