package ZliServer;

import ServerGUIControllers.ServerGuiController;
import javafx.application.Application;
import javafx.stage.Stage;
import jdbc.mysqlConnection;

public class ZliServerUI extends Application {

	public static ZliServerChat Server;
	public static ServerGuiController serverGuiController;
	public static mysqlConnection connectionToDB;

	public static void main(String args[]) throws Exception {
		launch(args);
	} // end main

	@Override
	public void start(Stage primaryStage) throws Exception {
		serverGuiController= new ServerGuiController(); // create server controller
		serverGuiController.start(primaryStage);
	}

	/*
	 * This function goes to the ZliServer method of stop listening when button
	 * Disconnect is clicked in the Server Controller
	 */
	public static void DisconnectServer() {
		System.out.println("The server is Disconnected");
		Server.stopListening();

	}

	/*
	 * This function Creates new Instance of ZliServerChat with the specific Port to
	 * Connect to. This function Connects to the DB Using dbName,User,Password
	 */
	public static String runServer(String Port, String dbName, String dbUserTxt, String password) {
		String returnMsgFromServer = "Error! Connection failed.";
		int port = 0; // Port to listen on
		try {
			port = Integer.parseInt(Port); // Set port to 5555

		} catch (Throwable t) {

		}
		if (Server == null)
			Server = new ZliServerChat(port);
		connectionToDB = new mysqlConnection();
		if (mysqlConnection.connectToDB(dbName, dbUserTxt, password))
			returnMsgFromServer = "Connection to server succeded";
		try {
			Server.listen(); // Start listening for connections
		} catch (Exception ex) {
			returnMsgFromServer = "Error! Could't listen for clients!";
		}
		return returnMsgFromServer;
	}

}
