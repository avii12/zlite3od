package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import AllUsers.ConfirmationStatus;
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

/**
 * Class description: Controlling The Customer page
 *
 * @author ibrahem enbtawe
 *
 */
public class CustomerPageController extends UsersController implements Initializable {
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	/**
	 * variable for status
	 */
	@FXML
	public static ConfirmationStatus status;
	/**
	 * label for welcome
	 */
	@FXML
	private Label WelcomeLabel;
	/**
	 * label for welcome
	 */
	@FXML
	private Label StatusLabel;
	/**
	 * label for type
	 */
	@FXML
	private Label TypeLabel;
	/**
	 * Cancel order button
	 */
	@FXML
	private Button CancelOrderBtn;
	/**
	 * Show Survey button
	 */
	@FXML
	private Button ShowSurveyBtn;

	/**
	 * After Clicking On Logout Button The Function Hide The Current Window and
	 * logout. Load The Login Window And We Can Drag the Window How Ever We Want
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
	 * After Clicking On Exit Button The Function Send A Message To The Server The
	 * Function LogOut The Account And Disconnect From The Server
	 * 
	 * @param event
	 * @throws IOException
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
	 * After clicking on show survey button The Function Hide The Current Window And
	 * Load The Survey Window
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void ShowSurveyBtn(ActionEvent event) throws Exception {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/Survey.fxml"));
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

	/**
	 * After clicking on show survey button The Function Hide The Current Window And
	 * Load The Catalog Window
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void ViewCatalogBtn(ActionEvent event) throws Exception {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CatalogPage.fxml"));
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

	/**
	 *  After clicking on show survey button The Function Hide The Current Window And
	 * Load The Cancel Order Window
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void CancelOrderBtn(ActionEvent event) throws Exception {
		
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CancelOrderPage.fxml"));
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

	/**
	 * After clicking on show survey button The Function Hide The Current Window And
	 * Load The Make Complaint For Customer Window
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void MakeComplaint(ActionEvent event) throws Exception {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/MakeComplaintForCustomer.fxml"));
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
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		message = new FullMessage(Request.GET_USER_STATUS, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		status = (ConfirmationStatus) message.getObject();

		if (status.equals(ConfirmationStatus.FROZEN)) {

			CancelOrderBtn.setDisable(true);
			ShowSurveyBtn.setDisable(true);

		} else {
			CancelOrderBtn.setDisable(false);
			ShowSurveyBtn.setDisable(false);
		}

		WelcomeLabel.setText("Welcome" + "-" + CurrentUser.getFirstName().toUpperCase());
		TypeLabel.setText(CurrentUser.getType().toUpperCase());
		StatusLabel.setText("Status" + " " + CurrentUser.getConfirmationstatus().toString().toUpperCase());

	}

}