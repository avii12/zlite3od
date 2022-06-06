package RequestsAndResponses;

import java.io.Serializable;

/**
 * 
 * Class description:
 * Full message class contains the message we want to send to the server
 * and the response we get from the server
 * contains parameter of type object 
 * 
 * 
 * @author Obied, Haddad
 * @author Ebrahem, Enbtawe
 * @author Shoroq ,Heib.
 *
 */

public class FullMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * the object we send to the server
	 */
	private Object object;
	
	/**
	 *  the response we get from the server
	 */
	private Response response;

	/**
	 * the request we send to the server
	 */
	private Request request;


	/**
	 * @param request
	 * @param object
	 */
	
	/**
	 * @param request
	 * @param response
	 * @param object
	 */
	public FullMessage( Request request, Response response, Object object) {
		super();
		this.request = request;
		this.response = response;
		this.object = object;
	}

	
	/**
	 * setters and getters of the full message class
	 * 
	 */
	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	
	
	
}
