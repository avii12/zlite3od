package ReadMessage;

public interface ClientListenerInterface {
	/**
	 * The listener that will work if the connection to the given IP succeed.
	 */
	default void connectedToServer() {
		return;
	}

	/**
	 * The listener that will work if the connection didn't succeed.
	 */
	default void failedConnectToServer() {
		return;
	}

}
