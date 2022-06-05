package customerService;

import java.io.Serializable;

public class Complaint implements Serializable {

	private int complaintNumber;
	private String customerId;
	private String complaintDate;
	private String branchName;
	private String OrderNumber;
	private double totalPrice;
	private String text;

	public Complaint(int complaintNumber, String customerId, String complaintDate, String branchName,
			String orderNumber, double totalPrice, String text) {
		super();
		this.complaintNumber = complaintNumber;
		this.customerId = customerId;
		this.complaintDate = complaintDate;
		this.branchName = branchName;
		this.OrderNumber = orderNumber;
		this.totalPrice = totalPrice;
		this.text = text;

	}

	public int getComplaintNumber() {
		return complaintNumber;
	}

	public void setComplaintNumber(int complaintNumber) {
		this.complaintNumber = complaintNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(String complaintDate) {
		this.complaintDate = complaintDate;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Complaint [complaintNumber=" + complaintNumber + ", customerId=" + customerId + ", complaintDate="
				+ complaintDate + ", branchName=" + branchName + ", OrderNumber=" + OrderNumber + ", totalPrice="
				+ totalPrice + ", text=" + text + "]";
	}

}
