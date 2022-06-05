package Report;

import Orders.Branch;

public class IncomeReport {

	public int idreport;
	public String date;
	public double TotalPrice;
	public int NumOfOrders;
	public Branch BranchName;
	
	public IncomeReport(IncomeReport income) {
		this.idreport=income.getIdreport();
		this.date=income.getDate();
		this.TotalPrice=income.getTotalPrice();
		this.NumOfOrders=income.getNumOfOrders();
		this.BranchName=income.getBranchName();
	}
	@Override
	public String toString() {
		return "IncomeReport [idreport=" + idreport + ", date=" + date + ", TotalPrice=" + TotalPrice + ", NumOfOrders="
				+ NumOfOrders + ", BranchName=" + BranchName + "]";
	}
	public IncomeReport(int idreport, String date, double totalPrice, int numOfOrders, Branch branchName) {
		super();
		this.idreport = idreport;
		this.date = date;
		TotalPrice = totalPrice;
		NumOfOrders = numOfOrders;
		BranchName = branchName;
	}
	public int getIdreport() {
		return idreport;
	}
	public void setIdreport(int idreport) {
		this.idreport = idreport;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		TotalPrice = totalPrice;
	}
	public int getNumOfOrders() {
		return NumOfOrders;
	}
	public void setNumOfOrders(int numOfOrders) {
		NumOfOrders = numOfOrders;
	}
	public Branch getBranchName() {
		return BranchName;
	}
	public void setBranchName(Branch branchName) {
		BranchName = branchName;
	}
}
