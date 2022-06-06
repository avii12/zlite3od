package ClientGUIControllers;

import java.io.IOException;


import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.ZliClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
 * controlling the Worker Page 
 *  
 *@author Obied haddad
 *
 */
public class WorkerPageController extends UsersController {
	

	/**
	 * Parimter Message Of FullMessage
	 */
	public static FullMessage message;
	

	
	
	/**
	 * After Clicking On LogOut Button The Function Hide The Current Window And Load
	 * The Login window We Can Drag the Window How Ever We Want
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void LogoutButton(ActionEvent event) throws IOException {
		

		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);

		switch (message.getResponse()) {

		case Succeed:
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
			primaryStage.setScene(scene);
			primaryStage.show(); /* Show login page */
			break;
		default:
			break;
		}
	}
	
    /**
     * 
     * After Clicking On Start Sales Button 
	 * The Function Hide The Current Window
	 * And Load The Start Sales window
     * @param event
     * @throws IOException
     */
    @FXML
	public void StartSalesButton(ActionEvent event) throws IOException {
    	

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/StartSales.fxml"));
		Scene scene = new Scene(root);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		scene.setOnMousePressed(pressEvent -> {
			scene.setOnMouseDragged(dragEvent -> {
				primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
				primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
			});
		});
		primaryStage.setScene(scene);
		primaryStage.show(); /* Show login page */

	}

	/**
	 * After Clicking On Exit Button 
	 * The Function Send A Message To The Server The
	 * Function LogOut The Account 
	 * And Disconnect From The Server
	 * 
	 * @param event
	 * @throws IOException
	 */

	@FXML
	public void ExitButton(MouseEvent event) throws IOException {
		

		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

	/**
	 * 
	 * After Clicking On Catalog Management Button 
	 * The Function Hide The Current Window
	 * And Load The Catalog Management window
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void CatalogManageButton(ActionEvent event) throws IOException {
		

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CatalogManagement.fxml"));
		Scene scene = new Scene(root);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		scene.setOnMousePressed(pressEvent -> {
			scene.setOnMouseDragged(dragEvent -> {
				primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
				primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
			});
		});
		primaryStage.setScene(scene);
		primaryStage.show(); /* Show login page */

	}

}
