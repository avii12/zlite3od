package AllUsers;

import java.io.Serializable;


import Orders.Branch;

/**
 * Class description: 
 * Delivry person class 
 * This class is derived class from User class
 * this class has the user class attributes 
 * 
 * @author Mario, Rohana.
 * @author Ebrahem, Enbtawe.
 * 
 */public class DeliveryPerson extends User implements Serializable  {
	private Branch BranchID;
	/**
	 * @param iD
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param type
	 * @param logInStatus
	 * @param confirmationstatus
	 * @param branch
	 */
	public DeliveryPerson(String iD, String firstName, String lastName, String email, String phoneNumber, String type,
			boolean logInStatus, ConfirmationStatus confirmationstatus,Branch branch) {
		super(iD, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;


	/**
	 * return the delivery person information
	 */
	@Override
	public String toString() {
		return "BranchManger: [FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}
	
}
