package AllUsers;

import java.io.Serializable;

public class Users implements Serializable{
	public int UserID;
	public String FirstName;
	public String LastName;
	public String Email;
	public String PhoneNumber;
	public String UserType;
	public boolean LogInStatus;
	public ConfirmationStatus Confirmationstatus;
	
	
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
	@Override
	public String toString() {
		return "Users [UserID=" + UserID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Email=" + Email
				+ ", UserType=" + UserType + "]";
	}
	

}
