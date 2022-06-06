package AllUsers;

/**
 * class description:
 * this class is for the server table
 * 
 * @author maisalon ,safory.
 *
 */
public class ClientStatus {

	private String Host;
	private String IP;
	private String Status;


	/**
	 * @param host
	 * @param iP
	 * @param status
	 */
	public ClientStatus(String host, String iP, String status) {
		super();
		Host = host;
		IP = iP;
		Status = status;
	}
	
	
	/**
	 * setters and getters
	 */
	public String getHost() {
		return Host;
	}


	public void setHost(String host) {
		Host = host;
	}


	public String getIP() {
		return IP;
	}


	public void setIP(String iP) {
		IP = iP;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}


	
}
