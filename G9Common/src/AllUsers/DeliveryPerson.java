package AllUsers;

import java.io.Serializable;

import Orders.Branch;

public class DeliveryPerson extends User implements Serializable  {
	private Branch BranchID;
	public DeliveryPerson(String iD, String firstName, String lastName, String email, String phoneNumber, String type,
			boolean logInStatus, ConfirmationStatus confirmationstatus,Branch branch) {
		super(iD, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;


	@Override
	public String toString() {
		return "BranchManger: [FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}
	
}
