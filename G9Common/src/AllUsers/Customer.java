package AllUsers;

import java.io.Serializable;

public class Customer extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	public String creditcard;
	public double balance;
	


	@Override
	public String toString() {
		return "Customer [creditcard=" + creditcard + ", balance=" + balance + ", FirstName=" + FirstName
				+ ", LastName=" + LastName + ", ID=" + ID + ", Email=" + Email + ", PhoneNumber=" + PhoneNumber
				+ ", Type=" + Type + ", LogInStatus=" + LogInStatus + ", Confirmationstatus=" + Confirmationstatus
				+  "]";
	}



	public Customer(String UserID, String firstName, String lastName, String email, String phoneNumber, String type,
			boolean logInStatus, ConfirmationStatus confirmationstatus, String creditcard, double balance) {
		super(UserID, firstName, lastName, email, phoneNumber, type, logInStatus, confirmationstatus);
		this.creditcard = creditcard;
		this.balance = balance;
	}

	

	public Customer(Customer customer) {
		super(customer.getID(),customer.getFirstName(),customer.getLastName(),customer.getPhoneNumber(),customer.getType(),customer.getCreditcard(), customer.isLogInStatus(),customer.getConfirmationstatus());
	this.balance=customer.getBalance();
	}
	



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
