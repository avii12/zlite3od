package ClientGUIControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import AllUsers.Login;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.ZliClientUI;

/**
 * class description: this class is for the login screen functionality of the
 * login
 * 
 * @author Mario Rohana
 * 
 * @version 10/05/2022
 *
 */

public class LoginController extends UsersController{
	
	private static Login login;
	
	public static FullMessage message;
	/**
	 * this is the userName that the user type in
	 */

	@FXML
	private TextField username;

	/**
	 * this is the password that the user type in
	 */
	@FXML
	private PasswordField password;

	@FXML
	private Label errorLabel;

	public void LoginButton(ActionEvent event) throws IOException {

		 login = new Login(username.getText(), password.getText());
		 logOnToNeededPage(event);
		
	}

	public void logOnToNeededPage(ActionEvent event) throws IOException {
		
		if (notEmpty(login)) {
			// send message to server to check DB
			 message = new FullMessage(Request.LOGIN, Response.Wait, login);
			ZliClientUI.ZliClientController.accept(message);
			
			
				
			switch (message.getRequest()) {

			case OPEN_PORTAL:

				switch (message.getResponse()) {
				case Create_CUSTOMER_PORTAL:
					TransferToNeededPage(event,"/ClientFXMLFiles/CustomerPage.fxml");
					break;
				case Create_CEOZLI_PORTAL:
					TransferToNeededPage(event,"/ClientFXMLFiles/CEOPage.fxml");
					break;
				case Create_BRANCHMANAGER_PORTAL:
					TransferToNeededPage(event,"/ClientFXMLFiles/BranchManager.fxml");
					break;
				case Create_CUSTOMERSERVICEWORKER_PORTAL:
					TransferToNeededPage(event,"/ClientFXMLFiles/CustomerServiceDepartment.fxml");
					break;
				case Create_SERVICESPECIALIST_PORTAL:
					TransferToNeededPage(event,"/ClientFXMLFiles/ServiceExpertPage.fxml");
					break;
				case Create_WORKER_PORTAL:
					TransferToNeededPage(event,"/ClientFXMLFiles/NetWorkMarketingWorker.fxml");
					break;
				case Create_DELIVERYPERSON_PORTAL:
					TransferToNeededPage(event,"/ClientFXMLFiles/DeliveryPersonPage.fxml");
					break;
				
				}
				break;
				
			case POP_UP_ERROR:
				switch(message.getResponse()) {
				case NO_SUCH_USER:
					errorControl("User Doesnt Exist !!");
					break;
				case ALREADY_LOGGED_IN:
				    errorControl("User Already Logged In !!");
				    break;
				default:
					break;
				}
			default:
				break;
			}
		}
	}
	
	private boolean notEmpty(Login login) {

		if (login.getUsername().equals("") && !login.getPassword().equals("")) {
			username.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			password.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			errorControl("please type username");
			return false;

		}

		if (!login.getUsername().equals("") && login.getPassword().equals("")) {
			username.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			password.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			errorControl("please type password");
			return false;
		}

		if (login.getUsername().equals("") && login.getPassword().equals("")) {
			username.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			password.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			errorControl("please type username and password");
			return false;

		}

		return true;

	}

	private void errorControl(String message) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				errorLabel.setText(message);
				errorLabel.setVisible(true);
			}

		});
	}

	public void ExitButton(MouseEvent event) {
		System.out.println("exit the establish Connection to server Window");
		Request request = Request.Disconnect;
		Response response = Response.Wait;
		FullMessage message = new FullMessage(request, response, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

	public void BackButton(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ZRLI.fxml"));
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

	public void TransferToNeededPage(ActionEvent event, String path) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource(path));
		Scene scene = new Scene(root);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		scene.setOnMousePressed(pressEvent -> {
			scene.setOnMouseDragged(dragEvent -> {
				primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
				primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
			});
		});
		primaryStage.setScene(scene);
		primaryStage.show();/* show the new screen *SearchForOrder.fxml */
	}
	
	public static Login getUser() {
		
		return login;
	}
	

}