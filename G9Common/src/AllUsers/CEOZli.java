package AllUsers;

import java.io.Serializable;

public class CEOZli extends User implements Serializable {

	/**
	 * class description:
	 * this is the class of the CEO 
	 * the Ceo deals with the reports
	 * 
	 *  @author Shoroq,Hieb.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param iD
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param type
	 * @param logInStatus
	 * @param confirmationstatus
	 */
	public CEOZli(String iD, String firstName, String lastName, String email, String phoneNumber, String type,
			boolean logInStatus, ConfirmationStatus confirmationstatus) {
		super(iD, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
		
	}

	/**
	 * return the CEO info in string
	 */
	@Override
	public String toString() {
		return "CEOZli: [FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}

}
