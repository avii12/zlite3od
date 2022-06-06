package customerService;

import java.sql.Timestamp;

import Orders.Branch;


/**
 * class description:
 * this class is for inserting the complaint in for in the complaint table
 * 
 * @author Ebrahem, Enbtawe
 * @author Mario ,Rohana.
 * @authoe sere, hanany.
 *
 */
public class ComplaintsForTableView {

	/**
	 * the complaint from the complaint class 
	 */
	private Complaint complaint;
	/**
	 * complaint ID
	 */
	private int complaintID;
	/**
	 *  the complaint number   
	 */
	private int complaintNum;
	/**
	 * customer ID
	 */
	private String customerId;
	/**
	 * order number
	 */
	private int OrderNumber;
	/**
	 * complaint date
	 */
	private Timestamp complaintDate;
	/**
	 * branch name
	 */
	private Branch branchName;
	/**
	 * text of the complaint that the user customer type
	 */
	private String text;
	/**
	 * the price of the order of the complaint
	 */
	private double totalPrice;

	/**
	 * @param complaint
	 */
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
<<<<<<< HEAD
    
	/*
	 * these are the setters and getters of the complaint table
	 */
=======
	
	

>>>>>>> branch 'master' of https://github.com/avii12/zlite3od.git
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
