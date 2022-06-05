package ZliClient;


import ClientGUIControllers.ServerIPController;
import javafx.application.Application;
import javafx.stage.Stage;



public class ZliClientUI extends Application{
	public static ZliClientController ZliClientController;
	public ServerIPController ipFormController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		ipFormController = new ServerIPController(); /* create controller for first Frame */
		ipFormController.start(primaryStage); /* go to start method in the IPController */
		
		
	}
	
	public static void main(String args[]) throws Exception {
		launch(args);
	} // end main

	
	public static void StartController(String ipTxt) {
		ZliClientController = new ZliClientController(ipTxt,5555); /* start running with the given IP from the IP frame entered by the client */
	}

}
