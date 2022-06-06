package AllUsers;

import java.io.Serializable;


/**
 * class description:
 * This is the Worker class
 * This class is derived class from User class
	 * this class has the user class attributes 
 * @author Mario, Rohana
 *
 */
public class Worker extends User implements Serializable{

	
	private static final long serialVersionUID = 1L;

	/**
	 * @param firstName
	 * @param lastName
	 * @param iD
	 * @param email
	 * @param phoneNumber
	 * @param type
	 * @param logInStatus
	 * @param confirmationstatus
	 */
	public Worker(String firstName, String lastName, String iD, String email, String phoneNumber, String type,
			boolean logInStatus, ConfirmationStatus confirmationstatus) {
		super(firstName, lastName, iD, email, phoneNumber, type, logInStatus, confirmationstatus);
	}

	/**
	 * return the worker info
	 */
	@Override
	public String toString() {
		return "Worker: [FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}
	

}
