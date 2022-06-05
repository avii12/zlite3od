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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git

/**
 * @author Obied
 *
 */
public class BranchManagerPageController extends UsersController implements Initializable {

	/**
	 * Static Parimter Message Of FullMessage
=======
/**
 * Class description: 
 * This is a class for 
 * controlling the UI of Branch Manager form
 *  
 *@author Seren Hanany
 *
 */
public class BranchManagerPageController extends UsersController implements Initializable{
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
	/**
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git
	 * Label For The User Status
=======
	 * Status label
>>>>>>> 7a300d4 dd
	 */
	@FXML
	private Label StatusLabel;
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git

	/**
	 * Label For The User Type
	 */
	@FXML
=======
	/**
	 * Type label
	 */
	@FXML 
>>>>>>> 7a300d4 dd
	private Label TypeLabel;

	/**
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git
	 * After Clicking On LogOut Button The Function Hide The Current Window And Load
	 * The Login window We Can Drag the Window How Ever We Want
	 * 
=======
	 * After Clicking On Logout Button 
	 * The Function Hide The Current Window and logout. 
	 * Load The Login Window 
	 * And We Can Drag the Window How Ever We Want
>>>>>>> 7a300d4 dd
	 * @param event
	 * @throws IOException
	 */
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
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git

	/**
	 * After Clicking On Exit Button 
	 * The Function Send A Message To The Server The
	 * Function LogOut The Account And Disconnect From The Server
	 * 
	 * @param event
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
	 * After Clicking On Change User Permission Button
	 * The Function Hide The Current Window And
	 * Load The ChangeUserPermission Window 
	 * We Can Drag the Window How Ever We Want
	 * 
	 * @param event
	 * @throws IOException
	 */
=======
	/**
	 * After Clicking On Change User Permission Button 
	 * The Function Hide The Current Window  
	 * And Load The Change User Permission Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
	
>>>>>>> 7a300d4 dd
	@FXML
	void ChangeUserPermission(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ChangeUserPermission.fxml"));
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
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git
	}

	/**
	 * After Clicking On Change Customer Status 
	 * The Function Hide The Current Window And
	 * Load The Change Customer Status Window 
	 * We Can Drag the Window How Ever We Want
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void ChangeCustomerStatusButton(ActionEvent event) throws IOException {
=======
    }
	/**
	 * After Clicking On Change Customer Status Button
	 * The Function Hide The Current Window  
	 * And Load The Change Customer Status Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
	public void ChangeCustomerStatusButton(ActionEvent event) throws IOException{
>>>>>>> 7a300d4 dd
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ChangeCustomerStatus.fxml"));
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
	 * After Clicking On Approve Order 
	 * The Function Hide The Current Window And
	 * Load The Accept Order Window 
	 * We Can Drag the Window How Ever We Want
	 * 
=======
	/**
	 * After Clicking On Accept Order Button
	 * The Function Hide The Current Window  
	 * And Load The Accept Order Window 
	 * And We Can Drag the Window How Ever We Want
>>>>>>> 7a300d4 dd
	 * @param event
	 * @throws IOException
	 */
	public void AcceptOrderButton(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/AcceptOrder'sPage.fxml"));
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
	 * After Clicking On Aprrove Cancel Order 
	 * The Function Hide The Current Window And
	 * Load The Accept Cancel Order Window 
	 * We Can Drag the Window How Ever We Want
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
=======
	/**
	 * After Clicking On Accept Cancel Order Button
	 * The Function Hide The Current Window  
	 * And Load The Accept Cancel Order Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
>>>>>>> 7a300d4 dd
	public void AcceptCancelOrderButton(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/AcceptCancelOrder's.fxml"));
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
	 * After Clicking On View income/Orders Report 
	 * The Function Hide The Current Window And
	 * Load The View Reports For Branch  Manager Window 
	 * We Can Drag the Window How Ever We Want
	 * 
	 * @param event
	 * @throws IOException
	 */
=======
	/**
	 * After Clicking on View Branch Report Button
	 * The Function Hide The Current Window  
	 * And Load The View Branch Report Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */

>>>>>>> 7a300d4 dd
	@FXML
	public void ViewBranchReportBtn(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ViewReportsForBranchManager.fxml"));
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
	 * After Clicking On View Complaint Reports 
	 * The Function Hide The Current Window And
	 * Load The Complaint Report Window 
	 * We Can Drag the Window How Ever We Want
	 * 
=======
	/**
	 * After Clicking on View Complaint Report Button
	 * The Function Hide The Current Window  
	 * And Load The View Complaint Report Window 
	 * And We Can Drag the Window How Ever We Want
>>>>>>> 7a300d4 dd
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void ViewComplaintReportBtn(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ComplaintReport.fxml"));
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
	 * This method sets the correct values of the Branch Manager in portal.
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
	public void initialize(URL location, ResourceBundle resources) {

		WelcomeLabel.setText("Welcome" + "-" + CurrentUser.getFirstName().toUpperCase());
		TypeLabel.setText(CurrentUser.getType().toUpperCase());
		StatusLabel.setText("Status" + " " + CurrentUser.getConfirmationstatus().toString().toUpperCase());

	}

}
