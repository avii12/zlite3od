package customerService;

import java.io.Serializable;
import java.sql.Timestamp;

import Orders.Branch;

public class Complaint implements Serializable {

	private int complaintID;
	private int complaintNum;
	private String customerId;
	private int OrderNumber;
	private Timestamp complaintDate;
	private Branch branchName;
	private String text;
	private double totalPrice;

	public Complaint(int complaintID, int complaintNum, String customerId, int OrderNumber, Timestamp complaintDate,
			Branch branchName, String text, double totalprice) {
		super();
		this.complaintID = complaintID;
		this.complaintNum = complaintNum;
		this.customerId = customerId;
		this.OrderNumber = OrderNumber;
		this.complaintDate = complaintDate;
		this.branchName = branchName;
		this.text = text;
		this.totalPrice = totalprice;

	}

	public int getComplaintID() {
		return complaintID;
	}

	public void setComplaintID(int complaintID) {
		this.complaintID = complaintID;
	}

	public int getComplaintNum() {
		return complaintNum;
	}

	public void setComplaintNum(int complaintNum) {
		this.complaintNum = complaintNum;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		OrderNumber = orderNumber;
	}

	public Timestamp getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Timestamp complaintDate) {
		this.complaintDate = complaintDate;
	}

	public Branch getBranchName() {
		return branchName;
	}

	public void setBranchName(Branch branchName) {
		this.branchName = branchName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Complaint [complaintID=" + complaintID + ", complaintNum=" + complaintNum + ", customerId=" + customerId
				+ ", OrderNumber=" + OrderNumber + ", complaintDate=" + complaintDate + ", branchName=" + branchName
				+ ", text=" + text + ", totalPrice=" + totalPrice + "]";
	}
	
	

}
