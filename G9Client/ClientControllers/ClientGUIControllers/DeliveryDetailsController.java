package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Orders.Order;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.ZliClientUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Class description: Controlling The Delivery Details
 *
 * @author obied haddad
 *
 */
public class DeliveryDetailsController extends UsersController implements Initializable {
	
	/**
	 * Static Parameter Message Of FullMessage
	 */
	public static FullMessage message;
	/**
	 * Error label
	 */
	@FXML
	private Label ErrorLabel;
	/**
	 * Text Field for address
	 */
	@FXML
	private TextField TextFieldAddress;
	/**
	 * Text Field for customer name
	 */
	@FXML
	private TextField TextFieldCustomerName;
	/**
	 * Text Field for Phone number
	 */
	
	@FXML
	private TextField TextFieldPhoneNumber;
	/**
	 * variable for order
	 */
	public static Order order;

	/**
	 * After Clicking on submit Button
	 * The Function Hide The Current Window 
	 * And Load The Payment Window
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void SubmitButton(ActionEvent event) throws IOException {
		
		TextFieldAddress.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");
		TextFieldCustomerName.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");
		TextFieldPhoneNumber.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");

		if (isInvalid()) {
			ErrorLabel.setText("Please fill the empty fields!");
		}

		else if (!(isNumeric(TextFieldPhoneNumber.getText()))) {
			ErrorLabel.setText("Phone Number can only contain Numbers!!");
		} else {

			addDetails();
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/Payment.fxml"));
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
	}

	/**
	 * After Clicking On Back Button 
	 * The Function Hide The Current Window 
	 * And Load The previous Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void BackButton(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/OrderDetails.fxml"));
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
	 * This function gets String
	 * and check if the value is numbers
	 * @param strNum
	 * @return 
	 */
	public static boolean isNumeric(String strNum) {
		
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	/**
	 * After Clicking On Exit Button 
	 * The Function Send A Message To The Server The
	 * Function LogOut The Account And Disconnect From The Server
	 * @param event
	 */
	public void ExitButton(MouseEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}
	/**
	 * This function gets String message
	 * put the message on error label
	 * @param msg
	 */
	public void DisplayMessageForUser(String message) {
		Platform.runLater(() -> {
			ErrorLabel.setText(message);
		});

	}

	/**
	 * This function check the value in text field address 
	 * @return
	 */
	private boolean isInvalid() {
		
		boolean check = false;

		if (TextFieldAddress.getText().equals("")) {

			TextFieldAddress.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldAddress.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}

		if (TextFieldCustomerName.getText().equals("")) {

			TextFieldCustomerName.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldCustomerName.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}

		if (TextFieldPhoneNumber.getText().equals("")) {

			TextFieldPhoneNumber.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldPhoneNumber.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}
		return check;
	}

	/**
	 * This function add the details of delivery to order 
	 */
	private void addDetails() {

		String address = TextFieldAddress.getText();
		String name = TextFieldCustomerName.getText();
		String phone = TextFieldPhoneNumber.getText();

		order.setDeliveryAddress(address);
		order.setCustomerName(name);
		order.setPhoneNumber(phone);

	}

	/**
	 *
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		DeliveryDetailsController.order = OrderDetailsController.order;

	}

}
