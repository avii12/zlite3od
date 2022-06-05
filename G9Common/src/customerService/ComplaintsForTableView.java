package customerService;

public class ComplaintsForTableView {
	
	private Complaint complaint;
	private int complaintNumber;
	private String customerId;
	private String complaintDate;
	private String branchName;
	private String OrderNumber;
	
	public ComplaintsForTableView(Complaint complaint) {
		this.complaint = complaint;
		this.complaintNumber = complaint.getComplaintNumber();
		this.customerId = complaint.getCustomerId();
		this.complaintDate = complaint.getComplaintDate();
		this.branchName = complaint.getBranchName();
		this.OrderNumber = complaint.getOrderNumber();
		
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
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
	
	

}
