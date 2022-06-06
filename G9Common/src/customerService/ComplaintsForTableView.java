package customerService;

import java.sql.Timestamp;

import Orders.Branch;

public class ComplaintsForTableView {

	private Complaint complaint;
	private int complaintID;
	private int complaintNum;
	private String customerId;
	private int OrderNumber;
	private Timestamp complaintDate;
	private Branch branchName;
	private String text;
	private double totalPrice;

	public ComplaintsForTableView(Complaint complaint) {
		this.complaint = complaint;
		this.complaintID = complaint.getComplaintID();
		this.customerId = complaint.getCustomerId();
		this.OrderNumber = complaint.getOrderNumber();
		this.complaintDate = complaint.getComplaintDate();
		this.branchName = complaint.getBranchName();
		this.text = complaint.getText();
		this.totalPrice = complaint.getTotalPrice();
		this.complaintNum = complaint.getComplaintNum();

	}
	
	

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
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
		return "ComplaintsForTableView [complaint=" + complaint + ", complaintID=" + complaintID + ", complaintNum="
				+ complaintNum + ", customerId=" + customerId + ", OrderNumber=" + OrderNumber + ", complaintDate="
				+ complaintDate + ", branchName=" + branchName + ", text=" + text + ", totalPrice=" + totalPrice + "]";
	}

}
