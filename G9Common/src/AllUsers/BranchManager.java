package AllUsers;

import java.io.Serializable;



import Orders.Branch;

/**
 *  Class description: This class is derived class from
 *  User class which defines the branch manager attributes
 * 
 * @author obied, haddad.
 *  
 */
public class BranchManager extends User implements Serializable {
	
	/**
	 * branch id for the branch manager to check the branch ID
	 */
	private String BranchID;
	/**
	 * @param iD
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param type
	 * @param logInStatus
	 * @param confirmationstatus
	 * @param BranchId
	 */
	public BranchManager(String iD, String firstName, String lastName, String email, String phoneNumber, String type,
			boolean logInStatus, ConfirmationStatus confirmationstatus,String BranchId) {
		super(iD, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
		this.BranchID=BranchId;
	}

	/**
	 * setters and getters
	 */
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
