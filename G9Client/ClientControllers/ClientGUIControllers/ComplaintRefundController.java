package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
import customerService.Complaint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *Class description:  
 *Controlling The Complaint Refund   
 *
 *@author maisalon safory
 *
 */
public class ComplaintRefundController extends UsersController implements Initializable {
	/**
	 * Text area for complaint text 
	 */
	@FXML
	private TextArea complaint_txt;
	/**
	 * Text area for replay text 
	 */
	@FXML
	private TextField reply_txt;
	/**
	 * Text for error text
	 */
	@FXML
	private Text errorText;
	/**
	 * Text for error text
	 */
	public static FullMessage message;
	/**
	 * variable for Complaint
	 */
	public static Complaint complaint;

	/**
	 * After Clicking On Back Button 
	 * The Function Hide The Current Window 
	 * And Load The previous Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
	public void BackButton(ActionEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ComplaintHandling.fxml"));
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
	 * After clicking on refund button
	 * The Function Hide The Current Window  
	 * And Load The Complaint Handling Window  
	 * @param event
	 * @throws Exception
	 */
	public void refundButton(ActionEvent event) throws Exception {
		if (!refundCustomer()) {
			errorText.setText("Problem accured while refunding the customer!");
			errorText.setFill(Color.RED);
		} else {
			double refund = (double) message.getObject();
			String convertedRefund = String.valueOf(refund);

			PopUpMsg.AlertForUser(
					"User has been refunded with " + convertedRefund + "\n" + "**An E-mail has been sent to user!**");
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ComplaintHandling.fxml"));
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
	 * This function updated the balance in DB 
	 * @return
	 */
	public boolean refundCustomer() {

		Complaint sendComplaint = complaint;
		message = new FullMessage(Request.UPDATE_BALANCE_AFTER_COMPLAINT, Response.Wait, sendComplaint);
		ZliClientUI.ZliClientController.accept(message);

		if (message.getResponse() == Response.UPDATE_BALANCE_AFTER_COMPLAINT_SUCCEEDED) {

			return true;
		}

		return false;

	}
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ComplaintRefundController.complaint = ComplaintHandelingController.complaint;

		complaint_txt.setWrapText(true);
		complaint_txt.setText(complaint.getText());

	}
	/**
	 * After Clicking On Exit Button
	 * The Function Send A Message To The Server 
	 * The Function LogOut The Account 
	 * And Disconnect From The Server  
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void ExitButton(ActionEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

}
