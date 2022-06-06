// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package ZliClient;

import java.io.IOException;

import ChatIF.ChatIf;
import RequestsAndResponses.FullMessage;

/**
 * This class constructs the UI for a chat client. It implements the chat
 * interface in order to activate the display() method. Warning: Some of the
 * code here is cloned in ServerConsole
 *
 */
public class ZliClientController implements ChatIf {
	// Class variables *************************************************
	/**
	 * The default port to connect on.
	 */
	public static int DEFAULT_PORT;

	// Instance variables **********************************************

	/**
	 * The instance of the ZliClientChat with the server
	 */
	ZliClientChat client;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the ClientConsole UI.
	 *
	 * @param host The host to connect to.
	 * @param port The port to connect on.
	 */
	public ZliClientController(String host, int port) {
		try {
			client = new ZliClientChat(host, port, this);
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}

	// Instance methods ************************************************

	/**
	 * This method waits for input from the console. Once it is received, it sends
	 * it to the client's message handler.
	 */
	public void accept(FullMessage fullmessage) {
		client.handleMessageFromClientUI(fullmessage);
	}

	/**
	 * This method overrides the method in the ChatIF interface. It displays a
	 * message onto the screen.
	 *
	 * @param message The string to be displayed.
	 */
	public void display(String message) {

	}
}
//End of ConsoleChat class
