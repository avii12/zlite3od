package ClientGUIControllers;

import java.io.IOException;



import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.ZliClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
/**
 * Class description: 
 * This is a class for 
 * controlling the Zli Description 
 *  
 *@author Obied haddad
 *
 */
public class ZliDescriptionController {

	/**
	 * After Clicking On Back Button 
	 * The Function Hide The Current Window 
	 * And Load The previous Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
	public void BackButton(MouseEvent event) throws IOException {
		
		
		Request request = Request.Disconnect;
		Response response = Response.Wait;
		FullMessage message = new FullMessage(request, response,null);
		ZliClientUI.ZliClientController.accept(message);
		
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/IPAddress.fxml"));
		Scene scene = new Scene(root);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		scene.setOnMousePressed(pressEvent -> {
			scene.setOnMouseDragged(dragEvent -> {
				primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
				primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
			});
		});
		primaryStage.setTitle("ZerLi Description");
		primaryStage.setScene(scene);
		primaryStage.show();/* show the new screen *SearchForOrder.fxml */
	}
	
	/**
	 * After Clicking On Exit Button
	 * The Function Send A Message To The Server 
	 * The Function LogOut The Account 
	 * And Disconnect From The Server  
	 * @param event
	 * @throws IOException
	 */
	public void ExitBtn(MouseEvent event) throws Exception {
		
		Request request = Request.Disconnect;
		Response response = Response.Wait;
		FullMessage message = new FullMessage(request, response,null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}
	
	/**
	 * Hide The Current Window 
	 * Transfer To The Needed Page 
	 * 
	 * @param event
	 * @param path
	 * @throws IOException
	 */
	
	public void TransferToLoginPageButton (ActionEvent event) throws Exception{
		
		
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/Login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		scene.setOnMousePressed(pressEvent -> {
			scene.setOnMouseDragged(dragEvent -> {
				primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
				primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
			});
		});
		primaryStage.setTitle("Login Page");
		primaryStage.setScene(scene);
		primaryStage.show();/* show the new screen *SearchForOrder.fxml */
	}
}
