// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package ZliClient;
////hey
import java.io.IOException;

import ChatIF.ChatIf;

import ReadMessage.ReadMessageFromServer;
import RequestsAndResponses.FullMessage;
import ocsf.client.AbstractClient;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 */
public class ZliClientChat extends AbstractClient {
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIf clientUI;

	/* Create New Order **This will be the order from the DataBase** */


	public static boolean awaitResponse = false; // wait for response from server

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public ZliClientChat(String host, int port, ChatIf clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		openConnection(); // create socket and open connection

	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */

	public void print() {

		System.out.println("no");
	}

	public void handleMessageFromServer(Object msg) {
		if(msg instanceof FullMessage ) {
			awaitResponse = false;
			try {
				ReadMessageFromServer.readMessageFromServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void handleMessageFromClientUI(FullMessage fullmessage) {

		try {
			awaitResponse = true;
			openConnection();// in order to send more than one message
			sendToServer(fullmessage); // send the msg to the server
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}
//End of ZliChatClient class
