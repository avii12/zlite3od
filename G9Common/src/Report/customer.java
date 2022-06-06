package Report;

import java.io.Serializable;

import AllUsers.ConfirmationStatus;

/**
 * 
 * Class description:
 * 
 * customer class for the reports
 * 
 * @author Maisalon, Safory
 * @author Seren ,Hanany.
 * @author Shoroq ,Heib.
 *
 */
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git
public class customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
=======
public class customer  implements Serializable {

	private static final long serialVersionUID = 1L;
	/*
>>>>>>> 6e74f03 qq
	 * customer id
	 */
	public String CustomerID;
	/**
	 * customer first name
	 */
	public String FirstName;
	/**
	 * last name
	 */
	public String LastName;
	/**
	 * customer email
	 */
	public String Email;
	/**
	 * customer status
	 */
	public ConfirmationStatus Status;

	/**
	 * @param cu
	 */
	public customer(customer cu) {
		this.CustomerID = cu.getCustomerID();
		this.FirstName = cu.getFirstName();
		this.LastName = cu.getLastName();
		this.Email = cu.getEmail();
		this.Status = cu.getStatus();
	}

	@Override
	public String toString() {
		return "customer [CustomerID=" + CustomerID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Email="
				+ Email + ", Status=" + Status + "]";
	}

	/**
	 * @param customerID
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param status
	 */
	public customer(String customerID, String firstName, String lastName, String email, ConfirmationStatus status) {
		super();
		CustomerID = customerID;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		Status = status;
	}

	/**
	 * setters and getters
	 */
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
