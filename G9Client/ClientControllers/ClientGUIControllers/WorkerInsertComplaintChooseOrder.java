package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import AllUsers.Users;
import Orders.Branch;
import Orders.Order;
import Orders.RefundStatus;
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

public class WorkerInsertComplaintChooseOrder extends UsersController implements Initializable {

	public static FullMessage message;
	public static Complaint complaint;
	public static ArrayList<Order> OrderFromDB = new ArrayList<>();
	public ObservableList<Order> customers = FXCollections.observableArrayList();
	public static ObservableList<Order> selectedorder;
	@FXML
	private TableView<Order> OrderTable;
	@FXML
	private TableColumn<Order, String> OrderNumberCol;
	@FXML
	private TableColumn<Order, Timestamp> DateCol;
	@FXML
	private TableColumn<Order, Branch> BranchCol;
	@FXML
	private TableColumn<Order, String> AllItemsCol;
	@FXML
	private Label ErrorLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		complaint = WorkerInsertComplaint.complaint;
		ObservableList<Order> Orders = FXCollections.observableArrayList();
		message = new FullMessage(Request.GET_ORDER_FROM_DB_FOR_CUSTOMER, Response.Wait, complaint.getCustomerId());
		ZliClientUI.ZliClientController.accept(message);

		switch (message.getResponse()) {

		case NO_ORDER_FOUND:
			errorControl("No Orders Found");
			break;

		case ORDER_FOUND_FOR_CUSTOMER:
			for (int i = 0; i < OrderFromDB.size(); i++) {
				Orders.add(new Order(OrderFromDB.get(i)));

			}
			System.out.println(Orders.toString());
			OrderNumberCol.setCellValueFactory(new PropertyValueFactory<Order, String>("OrderNumber"));
			DateCol.setCellValueFactory(new PropertyValueFactory<Order, Timestamp>("ActualDate"));
			BranchCol.setCellValueFactory(new PropertyValueFactory<Order, Branch>("branch"));
			AllItemsCol.setCellValueFactory(new PropertyValueFactory<Order, String>("allItems"));
			OrderTable.setItems(Orders);
			OrderTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			break;
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
	
	@FXML
	public void ChooseOrder(ActionEvent event) throws IOException {

		selectedorder = OrderTable.getSelectionModel().getSelectedItems();
		if (selectedorder.size() == 0) {
			PopUpMsg.AlertForUser("Please Select an order to continue!!");
		} else {

			String ordernumber = String.valueOf(selectedorder.get(0).getOrderNumber());
			int ordernumberParsed = Integer.parseInt(ordernumber);
			complaint.setOrderNumber(ordernumberParsed);
			complaint.setBranchName(selectedorder.get(0).getBranch());
			PopUpMsg.AlertForUser("Order Number " + selectedorder.get(0).getOrderNumber() + " is Selected");
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/WriteComplaintForCustomer.fxml"));
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

	@FXML
	public void BackButton(MouseEvent event) throws Exception {
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
