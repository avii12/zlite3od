package AllUsers;

import java.io.Serializable;

public class CustomerServiceWorker extends User implements Serializable{

	public CustomerServiceWorker(String iD, String firstName, String lastName, String email, String phoneNumber,
			String type, boolean logInStatus, ConfirmationStatus confirmationstatus) {
		super(iD, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public String toString() {
		return "CustomerServiceWorker: [FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}


}
