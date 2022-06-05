package AllUsers;

import java.io.Serializable;

public abstract class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String FirstName;
	protected String LastName;
	protected String ID;
	protected String Email;
	protected String PhoneNumber;
	protected String Type; //The type of the user: costumer,CEO,BranchManger
	protected boolean LogInStatus;
	protected ConfirmationStatus Confirmationstatus;
	
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
