package AllUsers;

import java.io.Serializable;

public class CreditCard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String CreditCardnumber;
	private String CreditCardHolderName;
	private String CreditCardDateOfExpiration;
	private String CreditCardCvv;
	
	public CreditCard(String creditCardnumber, String creditCardHolderName, String creditCardDateOfExpiration,
			String creditCardCvv) {
		super();
		CreditCardnumber = creditCardnumber;
		CreditCardHolderName = creditCardHolderName;
		CreditCardDateOfExpiration = creditCardDateOfExpiration;
		CreditCardCvv = creditCardCvv;
	}

	public String getCreditCardnumber() {
		return CreditCardnumber;
	}

	public void setCreditCardnumber(String creditCardnumber) {
		CreditCardnumber = creditCardnumber;
	}

	public String getCreditCardHolderName() {
		return CreditCardHolderName;
	}

	public void setCreditCardHolderName(String creditCardHolderName) {
		CreditCardHolderName = creditCardHolderName;
	}

	public String getCreditCardDateOfExpiration() {
		return CreditCardDateOfExpiration;
	}

	public void setCreditCardDateOfExpiration(String creditCardDateOfExpiration) {
		CreditCardDateOfExpiration = creditCardDateOfExpiration;
	}

	public String getCreditCardCvv() {
		return CreditCardCvv;
	}

	public void setCreditCardCvv(String creditCardCvv) {
		CreditCardCvv = creditCardCvv;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
