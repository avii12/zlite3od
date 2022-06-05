package AllUsers;

import java.io.Serializable;


/**
 *  Class description:
 *  This is the login class, it contains:
 *  userName, password, user type(customer,CEO,...), ID
 * 
 * @author Mario Rohana.
 *  
 *  @version 10/05/2022
 */

public class Login implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the user name of the user
	 */
	private String userName;
	
	/**	  
	 * This is the password of the user
	 */
	
	private String password;
	
	/**
	 * the constructor of the login
	 * we get the userName and the password and create an object and send it to the server  
	 * 
	 *the other parameters we get from the data base 
	 */
	
	public Login(String userName, String password) {
		this.userName = userName;
		this.password = password;
		
	}
	
	/**
	 * get functions 
	 * get the userName and the password
	 */
	
	public String getUsername() {
		return userName;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	/**
	 * set functions 
	 * set the userName and the password
	 */
	
	public void setUsername(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password =password;
	}
	
	
	@Override
	public String toString() {
		String message;
		message = "UserName: " + userName +"\nPassword: " + password ;
		return message;
	}
	
	

}
