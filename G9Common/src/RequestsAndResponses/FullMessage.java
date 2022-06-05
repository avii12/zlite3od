package RequestsAndResponses;

import java.io.Serializable;

public class FullMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Object object;
	
	private Response response;

	private Request request;


	public FullMessage(Request request, Object object) {
		super();
		this.request = request;
		this.object = object;
	}
	
	public FullMessage( Request request, Response response, Object object) {
		super();
		this.request = request;
		this.response = response;
		this.object = object;
	}

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
