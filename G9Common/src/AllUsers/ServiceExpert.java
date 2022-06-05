package AllUsers;

import java.io.Serializable;

public class ServiceExpert extends User implements Serializable {

	public ServiceExpert(String iD, String firstName, String lastName, String email, String phoneNumber,
			String type, boolean logInStatus, ConfirmationStatus confirmationstatus) {
		super(iD, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Override
	public String toString() {
		return "ServiceSpecialist: [FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}

}
