package Report;

import java.io.Serializable;

import AllUsers.ConfirmationStatus;
import Survey.SurveyAnswers;

public class customer  implements Serializable {

	public String CustomerID;
	public String FirstName;
	public String LastName;
	public String Email;
	public ConfirmationStatus Status;
	public customer (customer cu) {
		this.CustomerID=cu.getCustomerID();
		this.FirstName=cu.getFirstName();
		this.LastName=cu.getLastName();
		this.Email=cu.getEmail();
		this.Status=cu.getStatus();
	}
	@Override
	public String toString() {
		return "customer [CustomerID=" + CustomerID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Email="
				+ Email + ", Status=" + Status + "]";
	}
	public customer(String customerID, String firstName, String lastName, String email, ConfirmationStatus status) {
		super();
		CustomerID = customerID;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		Status = status;
	}
	
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
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
	public ConfirmationStatus getStatus() {
		return Status;
	}
	public void setStatus(ConfirmationStatus status) {
		Status = status;
	}
}
