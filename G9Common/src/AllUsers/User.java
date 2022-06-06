package AllUsers;

import java.io.Serializable;


/**
 * Class description: 
 * the User class contains the user info.
 * 
 * @author Mario, Rohana.
 * 
 */
public abstract class User implements Serializable {

	
	private static final long serialVersionUID = 1L;

	/**
	 * the user first name
	 */
	protected String FirstName;
	
	/**
	 * user last name
	 */
	protected String LastName;
	
	/**
	 * the user id PK
	 */
	protected String ID;
	
	/**
	 * the user email
	 */
	protected String Email;
	/**
	 * user Phone number
	 */
	protected String PhoneNumber;
	/**
	 * user type: manager, customer...
	 */
	protected String Type; 
	
	/**
	 * the status of the user, logged in or not
	 */
	protected boolean LogInStatus;
	
	/**
	 * user confirmation
	 */
	protected ConfirmationStatus Confirmationstatus;
	
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
	public User(String iD, String firstName, String lastName, String email, String phoneNumber, String type,
			boolean logInStatus, ConfirmationStatus confirmationstatus) {
		super();
		FirstName = firstName;
		LastName = lastName;
		ID = iD;
		Email = email;
		PhoneNumber = phoneNumber;
		Type = type;
		LogInStatus = logInStatus;
		Confirmationstatus = confirmationstatus;
	}

	/**
	 * setters and getters of the class
	 */
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public boolean isLogInStatus() {
		return LogInStatus;
	}

	public void setLogInStatus(boolean logInStatus) {
		LogInStatus = logInStatus;
	}

	public ConfirmationStatus getConfirmationstatus() {
		return Confirmationstatus;
	}

	public void setConfirmationstatus(ConfirmationStatus confirmationstatus) {
		Confirmationstatus = confirmationstatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
