package AllUsers;

import java.io.Serializable;
/**
 * Class description: 
 * Users class 
 * This class is derived class from User class
 * this class has the user class attributes 
 * 
 * @Author Seren, Hanany.
 * 
 */

public class Users implements Serializable{
	/**
	 * the Pk of the user is the id 
	 */
	public int UserID;
	/**
	 *first name
	 */
	
	public String FirstName;
	/**
	 * user last name
	 */
	public String LastName;
	/**
	 * email of the user
	 */
	public String Email;
	/**
	 * the phone number of the user
	 */
	public String PhoneNumber;
	/**
	 * the user type in the system: manager, customer, customer service worker....
	 * 
	 */
	public String UserType;
	/**
	 * check if the user is currently logged in 
	 * 
	 */
	public boolean LogInStatus;
	
	/**
	 * 
	 * this is the state of the user if confirmed he can use the system
	 */
	public ConfirmationStatus Confirmationstatus;
	
	
	/**
	 * @param userID
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param userType
	 * @param logInStatus
	 * @param confirmationstatus
	 */
	public Users(int userID, String firstName, String lastName, String email, String phoneNumber, String userType,
			boolean logInStatus, ConfirmationStatus confirmationstatus) {
		super();
		UserID = userID;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		PhoneNumber = phoneNumber;
		UserType = userType;
		LogInStatus = logInStatus;
		Confirmationstatus = confirmationstatus;
	}
	/**
	 * This section is for the 
	 * Setters and Getters of the 
	 * Class User
	 */
	
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
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
	public Users(int userID, String firstName, String lastName, String email, String userType) {
		super();
		UserID = userID;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		UserType = userType;
	}
	public Users(Users users) {
		this.UserID = users.getUserID();
		this.FirstName = users.getFirstName();
		this.LastName = users.getLastName();
		this.Email = users.getEmail();
		this.UserType = users.getUserType();
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
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
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
	
	/**
	 * return the user info
	 */
	@Override
	public String toString() {
		return "Users [UserID=" + UserID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Email=" + Email
				+ ", UserType=" + UserType + "]";
	}
	

}
