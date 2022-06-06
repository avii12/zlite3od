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
 * Class description: 
 * This is a class for 
 * controlling the Start Sales 
 *  
 *@author ibrahem enbtawe
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

	/**
	 * Label For Message For The User 
	 */
	@FXML
	private Label errorLabel;

	/**
	 * After Clicking On LogIn Button 
	 * The Function Get The Text Field Contents
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void LoginButton(ActionEvent event) throws IOException {

		 login = new Login(username.getText(), password.getText());
		 logOnToNeededPage(event);
		
	}

	/**
	 * Load The Correct Page 
	 * From the Text Fields Contents
	 * @param event
	 * @throws IOException
	 */
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
	
	
	/**
	 * 
	 * If Any Of The Fields Is Empty
	 * Display To The User Message
	 * @param login
	 * @return
	 */
	private boolean notEmpty(Login login) {
		username.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");
		password.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");

		if (login.getUsername().equals("") && !login.getPassword().equals("")) {
			username.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			password.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");
			errorControl("please type username");
			return false;

		}

		if (!login.getUsername().equals("") && login.getPassword().equals("")) {
			username.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");
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
	
	/**
	 * The Function Display's The Message On The Label
	 * @param message
	 */
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
	
	/**
	 * After Clicking On Exit Button
	 * The Function Send A Message To The Server  
	 * Disconnect From The Server  
	 * @param event
	 */
	public void ExitButton(MouseEvent event) {
		System.out.println("exit the establish Connection to server Window");
		Request request = Request.Disconnect;
		Response response = Response.Wait;
		FullMessage message = new FullMessage(request, response, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}
	
	/**
	 * After Clicking On Back Button 
	 * The Function Hide The Current Window 
	 * And Load The previous Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
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

	/**
	 * Hide The Current Window 
	 * Transfer To The Needed Page 
	 * 
	 * @param event
	 * @param path
	 * @throws IOException
	 */
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