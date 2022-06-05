package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import AllUsers.Users;
import Orders.ItemsForTableView;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
import customerService.Complaint;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WorkerInsertComplaint extends UsersController implements Initializable {

	public static FullMessage message;
	public static Complaint complaint = new Complaint(0, 0, null, 0, null, null, null, 0);
	public static ArrayList<Users> ArrayForCustomers = new ArrayList<>();
	public ObservableList<Users> customers = FXCollections.observableArrayList();
	public static ObservableList<Users> selectedcustomer;
	@FXML
	private Label ErrorLabel;
	@FXML
	private TableView<Users> CustomerTable;
	@FXML
	private TableColumn<Users, String> UserIDCol;
	@FXML
	private TableColumn<Users, String> FirstNameCol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		int complaintnum = 0;

		message = new FullMessage(Request.GET_USERS_FROM_DB_FOR_WORKER, Response.Wait, "customer");
		ZliClientUI.ZliClientController.accept(message);

		switch (message.getResponse()) {

		case NO_CUSTOMER:

			errorControl("No Customers Found!!");
			break;

		case USER_FOUND:

			for (int i = 0; i < ArrayForCustomers.size(); i++) {
				customers.add(new Users(ArrayForCustomers.get(i)));
			}

			UserIDCol.setCellValueFactory(new PropertyValueFactory<Users, String>("UserID"));
			FirstNameCol.setCellValueFactory(new PropertyValueFactory<Users, String>("firstName"));

			CustomerTable.setItems(customers);
			CustomerTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			message = new FullMessage(Request.GET_LAST_COMPLAINT_NUMBER, Response.Wait, null);
			ZliClientUI.ZliClientController.accept(message);

			switch (message.getResponse()) {

			case NO_COMPLAINT_FOUND:

				complaintnum = 1;
				complaint.setComplaintNum(complaintnum);
				break;

			case NOT_FIRST_COMPLAINT:

				complaintnum = (int) message.getObject();
				complaintnum++;
				complaint.setComplaintNum(complaintnum);
				break;

			}
			break;

		}

	}

	@FXML
	public void BackButton(MouseEvent event) throws Exception {
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

	@FXML
	public void ExitButton(MouseEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

	@FXML
	public void ChooseCustomer(ActionEvent event) throws IOException {

		selectedcustomer = CustomerTable.getSelectionModel().getSelectedItems();
		if (selectedcustomer.size() == 0) {
			PopUpMsg.AlertForUser("Please Select a user to continue");
		} else {

			String id = String.valueOf(selectedcustomer.get(0).getUserID());
			complaint.setCustomerId(id);
			PopUpMsg.AlertForUser(selectedcustomer.get(0).getFirstName() + " is Selected");
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/InsertComplaintChooseOrder.fxml"));
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
