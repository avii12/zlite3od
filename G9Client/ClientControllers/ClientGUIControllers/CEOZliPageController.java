package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.ZliClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Class description: 
 * This is a class for 
 * controlling the UI of CEO form
 *  
 *@author Seren Hanany
 *
 */
public class CEOZliPageController extends UsersController implements Initializable {
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git

	/**
	 * Static Parimter Message Of FullMessage
=======
	/**
	 * message type of FullMessage
>>>>>>> 7a300d4 dd
	 */
	public static FullMessage message;
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git
	
	/**
	 * Label For Welcome For The User
=======
	/**
	 * Welcome label
>>>>>>> 7a300d4 dd
	 */
	@FXML
	private Label WelcomeLabel;
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git
	
	/**
	 * Label For The User Status
=======
	/**
	 * Status label
>>>>>>> 7a300d4 dd
	 */
	@FXML
	private Label StatusLabel;
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git
	
	/**
	 * Label For The User Type
=======
	/**
	 * Type label
>>>>>>> 7a300d4 dd
	 */
	@FXML 
	private Label TypeLabel;
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git
	
	/**
	 * After Clicking On LogOut Button 
	 * The Function Hide The Current Window 
	 * And Load The Login window
	 * We Can Drag the Window How Ever We Want
	 * 
=======
	/**
	 * After Clicking On Logout Button 
	 * The Function Hide The Current Window and logout the account. 
	 * Load The Login Window 
	 * And We Can Drag the Window How Ever We Want
>>>>>>> 7a300d4 dd
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
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git
	 * After Clicking On Exit Button 
	 * The Function Send A Message To The Server The
	 * Function LogOut The Account And Disconnect From The Server
	 * 
	 * @param event
	 */
	@FXML
	public void exitButton(MouseEvent event) throws IOException {

		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}
	
	/**
	 * After Clicking On View Report
	 * The Function Hide The Current Window And
	 * Load The View Reports For CEO Window 
	 * We Can Drag the Window How Ever We Want
	 * 
=======
	 * After Clicking On View Report Button 
	 * The Function Hide The Current Window  
	 * And Load The View Report Window that the CEO can view all the reports  
	 * And We Can Drag the Window How Ever We Want
>>>>>>> 7a300d4 dd
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void ViewReportBtn(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ViewReportsForCEO.fxml"));
		Scene scene = new Scene(root);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		scene.setOnMousePressed(pressEvent -> {
			scene.setOnMouseDragged(dragEvent -> {
				primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
				primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
			});
		});
		primaryStage.setScene(scene);
		primaryStage.show();
	}
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git

	/**
	 * After Clicking On View Report For One Branch
	 * The Function Hide The Current Window And
	 * Load The View Report For One Branch Window 
	 * We Can Drag the Window How Ever We Want
	 * 
=======
	/**
	 * After Clicking On View Reports For One Branch Button 
	 * The Function Hide The Current Window  
	 * And Load The View Reports For One Branch Window 
	 * And We Can Drag the Window How Ever We Want
>>>>>>> 7a300d4 dd
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void ViewReportsForOneBranchBtn(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ViewTwoReportsForOneBranch.fxml"));
		Scene scene = new Scene(root);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		scene.setOnMousePressed(pressEvent -> {
			scene.setOnMouseDragged(dragEvent -> {
				primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
				primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
			});
		});
		primaryStage.setScene(scene);
		primaryStage.show();
	}
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git
	
	/**
	 * After Clicking On View Report For Two Branch
	 * The Function Hide The Current Window And
	 * Load The View Report For Two Branch Window 
	 * We Can Drag the Window How Ever We Want
	 * 
=======
	/**
	 * After Clicking On View Reports For Two Branches Button 
	 * The Function Hide The Current Window  
	 * And Load The View Reports For Two Branches Window 
	 * And We Can Drag the Window How Ever We Want
>>>>>>> 7a300d4 dd
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void ViewReportsForTwoBranchesBtn(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ViewTwoReportsForTwoBranches.fxml"));
		Scene scene = new Scene(root);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		scene.setOnMousePressed(pressEvent -> {
			scene.setOnMouseDragged(dragEvent -> {
				primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
				primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
			});
		});
		primaryStage.setScene(scene);
		primaryStage.show();
	}
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git

	/**
	 * This method sets the correct values of the CEO Zli in portal.
	 * 
=======
	/**
	 *
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 *
>>>>>>> 7a300d4 dd
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		WelcomeLabel.setText("Welcome" + "-" + CurrentUser.getFirstName().toUpperCase());
		TypeLabel.setText(CurrentUser.getType().toUpperCase());
		StatusLabel.setText("Status" + " " +CurrentUser.getConfirmationstatus().toString().toUpperCase());
		
		
	}
}
