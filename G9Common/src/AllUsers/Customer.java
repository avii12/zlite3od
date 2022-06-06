package AllUsers;

import java.io.Serializable;


/**
 * class description:
 * the customer class derived from the user class
 * @author Obied, Haddad
 * @author Mario, Rohana
 * @author Ebrahem ,Enbtawe,
 *
 */
public class Customer extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * credit card of the customer
	 */
	public String creditcard;
	/**
	 * the bank balance of the customer
	 */
	public double balance;
	


	/**
	 * return string
	 */
	@Override
	public String toString() {
		return "Customer [creditcard=" + creditcard + ", balance=" + balance + ", FirstName=" + FirstName
				+ ", LastName=" + LastName + ", ID=" + ID + ", Email=" + Email + ", PhoneNumber=" + PhoneNumber
				+ ", Type=" + Type + ", LogInStatus=" + LogInStatus + ", Confirmationstatus=" + Confirmationstatus
				+  "]";
	}



	/**
	 * @param UserID
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param type
	 * @param logInStatus
	 * @param confirmationstatus
	 * @param creditcard
	 * @param balance
	 */
	public Customer(String UserID, String firstName, String lastName, String email, String phoneNumber, String type,
			boolean logInStatus, ConfirmationStatus confirmationstatus, String creditcard, double balance) {
		super(UserID, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
		this.creditcard = creditcard;
		this.balance = balance;
	}

	/**
	 * @param customer
	 */
	public Customer(Customer customer) {
		super(customer.getID(),customer.getFirstName(),customer.getLastName(),customer.getPhoneNumber(),customer.getType(),customer.getCreditcard(), customer.isLogInStatus(),customer.getConfirmationstatus());
	this.balance=customer.getBalance();
	}
	



	/**
	 * setters and getters
	 */
	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
