package AllUsers;

import java.io.Serializable;

public class CEOZli extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CEOZli(String iD, String firstName, String lastName, String email, String phoneNumber, String type,
			boolean logInStatus, ConfirmationStatus confirmationstatus) {
		super(iD, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
		
	}

	@Override
	public String toString() {
		return "CEOZli: [FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}

}
