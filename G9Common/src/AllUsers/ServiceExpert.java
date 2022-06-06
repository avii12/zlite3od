package AllUsers;

import java.io.Serializable;


/**
 * Class description: 
 * Service Expert class
 * This class is derived class from User class
 * this class has the user class attributes 
 * 
 * @Author Seren, Hanany.
 * 
 */public class ServiceExpert extends User implements Serializable {

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
	public ServiceExpert(String iD, String firstName, String lastName, String email, String phoneNumber,
			String type, boolean logInStatus, ConfirmationStatus confirmationstatus) {
		super(iD, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	/**
	 *  return the information of the service expert 
	 */
	@Override
	public String toString() {
		return "ServiceSpecialist: [FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}

}
