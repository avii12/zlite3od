package ClientGUIControllers;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.ZliClientUI;
import customerService.Complaint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ComplaintRefundController extends UsersController implements Initializable {

	@FXML
	private TextArea complaint_txt;

	@FXML
	private TextField reply_txt;

	@FXML
	private Text errorText;

	public static FullMessage message;

	public static Complaint complaint;

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

	public void refundButton(ActionEvent event) throws Exception {

		
		getReply();
		if (!refundCustomer()) {
			errorText.setText("Problem accured while refunding the customer!");
			errorText.setFill(Color.RED);
		} else {
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

	public void getReply() {
		String reply = reply_txt.getText();
		String customerID = complaint.getCustomerId();
		String OrderNumber = complaint.getOrderNumber();
	

		// new complaintReplay()
	}

	public boolean refundCustomer() {

		Object sendComplaint = complaint;
		System.out.println(sendComplaint.toString());
		 message = new FullMessage(Request.UPDATE_BALANCE_AFTER_COMPLAINT,
				Response.UPDATE_BALANCE_AFTER_COMPLAINT_SUCCEEDED, sendComplaint);
		ZliClientUI.ZliClientController.accept(message);

		if (message.getResponse() == Response.MANAGE_COMPLAINT_APPROVED_SUCCEEDED_WRITING_INTO_DB) {

			return true;
		}

		return false;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ComplaintRefundController.complaint = ComplaintHandelingController.complaint;
        
		complaint_txt.setWrapText(true);
		complaint_txt.setText(complaint.getText());

	}

	@FXML
	public void ExitButton(ActionEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

}
