package ClientGUIControllers;

import java.io.IOException;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Orders.Branch;
import Orders.ItemsForTableView;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
import customerService.Complaint;
import customerService.ComplaintsForTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ComplaintHandelingController extends UsersController implements Initializable {

	public static Complaint complaint;

	@FXML
	private TableView<ComplaintsForTableView> complaintTable;
	@FXML
	private TableColumn<ComplaintsForTableView, Timestamp> complaintDateCol;
	@FXML
	private TableColumn<ComplaintsForTableView, Branch> branchNameCol;
	@FXML
	private TableColumn<ComplaintsForTableView, Integer> orderNumberCol;
	@FXML
	private TableColumn<ComplaintsForTableView, Integer> complaintNumberCol;
	@FXML
	private TableColumn<ComplaintsForTableView, String> customerIdCol;

	public static FullMessage message;

	public static ObservableList<ComplaintsForTableView> complaints;
	public static ObservableList<ComplaintsForTableView> selectedComplaint;

	@FXML
	private Text errorText;

	public boolean check = false;

	public static ArrayList<Complaint> complaintListFromDB = new ArrayList<>();

	public void BackButton(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CustomerServiceDepartment.fxml"));
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

	public void InsertComplaint(ActionEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/InsertComplaint.fxml"));
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

	public void chooseCustomer(ObservableList<ComplaintsForTableView> selectedComplaint) {

		Complaint complaint1 = selectedComplaint.get(0).getComplaint();
		complaint = complaint1;
		check = true;

	}

	@FXML
	public void ShowCustomerComplaint(ActionEvent event) throws Exception {

		selectedComplaint = complaintTable.getSelectionModel().getSelectedItems();
		if (selectedComplaint.isEmpty()) {
			errorText.setText("Please select a complaint");
			errorText.setFill(Color.RED);
		} else {
			chooseCustomer(selectedComplaint);

			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CustomerComplaint.fxml"));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		complaints = FXCollections.observableArrayList();
		message = new FullMessage(Request.GET_COMPLAINT_FROM_DB, Response.Wait, "complaint");
		ZliClientUI.ZliClientController.accept(message);

		if (message.getResponse().equals(Response.NO_COMPLAINTS)) {
			if (complaints.size() != 0)
				complaints.clear();
		}

		else {

			for (int i = 0; i < complaintListFromDB.size(); i++)
				complaints.add(new ComplaintsForTableView(complaintListFromDB.get(i)));

			complaintNumberCol
					.setCellValueFactory(new PropertyValueFactory<ComplaintsForTableView, Integer>("complaintNum"));
			orderNumberCol
					.setCellValueFactory(new PropertyValueFactory<ComplaintsForTableView, Integer>("OrderNumber"));
			customerIdCol.setCellValueFactory(new PropertyValueFactory<ComplaintsForTableView, String>("customerId"));
			complaintDateCol
					.setCellValueFactory(new PropertyValueFactory<ComplaintsForTableView, Timestamp>("complaintDate"));
			branchNameCol.setCellValueFactory(new PropertyValueFactory<ComplaintsForTableView, Branch>("branchName"));

			complaintTable.setItems(complaints);
			complaintTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			complaintTable.refresh();
		}

	}

	@FXML
	public void ExitButton(MouseEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}
}
