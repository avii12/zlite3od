package Report;

import java.io.Serializable;

public class OrderReport implements Serializable {
  public int ReportID;
  public String ReportDate;
  public String OrderID;
  public String OrderDate;
  public double OrderPrice;
@Override
public String toString() {
	return "OrderReport [ReportID=" + ReportID + ", ReportDate=" + ReportDate + ", OrderID=" + OrderID + ", OrderDate="
			+ OrderDate + ", OrderPrice=" + OrderPrice + "]";
}
public OrderReport(int reportID, String reportDate, String orderID, String orderDate, double orderPrice) {
	super();
	ReportID = reportID;
	ReportDate = reportDate;
	OrderID = orderID;
	OrderDate = orderDate;
	OrderPrice = orderPrice;
}
public OrderReport(OrderReport orderReport) {
	this.ReportID=orderReport.getReportID();
	this.ReportDate=orderReport.getReportDate();
	this.OrderID=orderReport.getOrderID();
	this.OrderDate=orderReport.getOrderDate();
	this.OrderPrice=orderReport.getOrderPrice();
	
}
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

}
