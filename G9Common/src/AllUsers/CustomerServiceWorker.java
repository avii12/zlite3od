package AllUsers;

import java.io.Serializable;
	/**
	 * Class description: 
	 * This class is derived class from User class
	 * this class has the user class attributes 
	 * 
	 * @author Maisalon, safory.
	 * 
	 */

public class CustomerServiceWorker extends User implements Serializable{

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
	public CustomerServiceWorker(String iD, String firstName, String lastName, String email, String phoneNumber,
			String type, boolean logInStatus, ConfirmationStatus confirmationstatus) {
		super(iD, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
		// TODO Auto-generated constructor stub
	}

	
	private static final long serialVersionUID = 1L;


	/**
	 *  return the name of the user
	 */
	@Override
	public String toString() {
		return "CustomerServiceWorker: [FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}


}
