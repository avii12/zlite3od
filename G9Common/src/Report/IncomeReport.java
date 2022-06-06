package Report;

import Orders.Branch;

/**
 * 
 * Class description:
 *
 *this class contains the income report information
 * 
 * @author Maisalon, Safory
 * @author Seren ,Hanany.
 * @author Shoroq ,Heib.
 *
 */

public class IncomeReport {

	/**
	 * report id
	 */
	public int idreport;
	/**
	 * income report date
	 */
	public String date;
	/**
	 * total price of the income report
	 */
	public double TotalPrice;
	/**
	 * number of orders
	 */
	public int NumOfOrders;
	/**
	 * name of the branch
	 */
	public Branch BranchName;
	
	/**
	 * @param income
	 */
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
	/**
	 * @param idreport
	 * @param date
	 * @param totalPrice
	 * @param numOfOrders
	 * @param branchName
	 */
	public IncomeReport(int idreport, String date, double totalPrice, int numOfOrders, Branch branchName) {
		super();
		this.idreport = idreport;
		this.date = date;
		TotalPrice = totalPrice;
		NumOfOrders = numOfOrders;
		BranchName = branchName;
	}
	
	/**
	 * setters and getters
	 * 
	 */
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
