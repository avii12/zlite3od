package AllUsers;

import java.io.Serializable;

public class Worker extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Worker(String firstName, String lastName, String iD, String email, String phoneNumber, String type,
			boolean logInStatus, ConfirmationStatus confirmationstatus) {
		super(firstName, lastName, iD, email, phoneNumber, type, logInStatus, confirmationstatus);
	}

	@Override
	public String toString() {
		return "Worker: [FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}
	

}
