package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Orders.Order;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
import customerService.Complaint;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WriteComplaintForCustomerController extends UsersController implements Initializable {

	@FXML
	private TextArea TextAreaField;
	public static FullMessage message;
	private ObservableList<Order> order;
	@FXML
	private Label ErrorLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		order = MakeComplaintForCustomerController.selectedOrder;
	}

	@FXML
	public void BackButton(MouseEvent event) throws Exception {
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

	@FXML
	public void ExitButton(MouseEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

	@FXML
	/*public void FinishButton(ActionEvent event) throws IOException {

		if (TextAreaField.getText().equals("")) {
			PopUpMsg.AlertForUser("Please enter text before submitting Complaint");
		} else {
			PopUpMsg.AlertForUser("You complaint was proccesed into the System");
			Complaint complaint = new Complaint(0, CurrentUser.getID(), order.get(0).getOrderNumber(),
					order.get(0).getActualDate().toString(), order.get(0).getBranch().toString(),
					order.get(0).getTotalPrice(), TextAreaField.getText());
			message = new FullMessage(Request.ADD_COMPLAINT_FROM_USER_TO_DB, Response.Wait, complaint);
			ZliClientUI.ZliClientController.accept(message);
			message = new FullMessage(Request.UPDATE_REFUND_STATUS, Response.Wait, order.get(0).getOrderNumber());
			ZliClientUI.ZliClientController.accept(message);
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CustomerPage.fxml"));
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
	}*/

	private void errorControl(String message) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ErrorLabel.setText(message);
				ErrorLabel.setVisible(true);
			}

		});
	}

}
