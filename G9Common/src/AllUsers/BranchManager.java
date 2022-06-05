package AllUsers;

import java.io.Serializable;

import Orders.Branch;

public class BranchManager extends User implements Serializable {
	private String BranchID;
	public BranchManager(String iD, String firstName, String lastName, String email, String phoneNumber, String type,
			boolean logInStatus, ConfirmationStatus confirmationstatus,String BranchId) {
		super(iD, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
		this.BranchID=BranchId;
	}

	public String getBranchID() {
		return BranchID;
	}


	public void setBranchID(String branchID) {
		BranchID = branchID;
	}

	private static final long serialVersionUID = 1L;


	@Override
	public String toString() {
		return "BranchManger: [FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}
	

}
