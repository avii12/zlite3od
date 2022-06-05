package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Orders.ItemsForTableView;
import Orders.Order;
import Orders.OrderStatus;
import Orders.RefundStatus;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MakeComplaintForCustomerController extends UsersController implements Initializable {
	public static FullMessage message;

	@FXML
	private TableView<Order> OrderTable;
	@FXML
	private TableColumn<Order, Double> PriceCol;
	@FXML
	private TableColumn<Order, Timestamp> DateCol;
	@FXML
	private TableColumn<Order, String> ItemsCol;
	@FXML
	private TableColumn<Order, RefundStatus> refundStatusCol;
	@FXML
	private Label ErrorLabel;
	@FXML
	private Button ComplaintButton;
	public static ObservableList<Order> selectedOrder;

	public static ArrayList<Order> OrderFromDB = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<Order> Orders = FXCollections.observableArrayList();
		message = new FullMessage(Request.GET_ORDER_FROM_DB_FOR_CUSTOMER, Response.Wait, CurrentUser.getID());
		ZliClientUI.ZliClientController.accept(message);

		switch (message.getResponse()) {

		case NO_ORDER_FOUND:
			ComplaintButton.setDisable(true);
			errorControl("No Orders Found");
			break;

		case ORDER_FOUND_FOR_CUSTOMER:
			ComplaintButton.setDisable(false);
			for (int i = 0; i < OrderFromDB.size(); i++) {
				Orders.add(new Order(OrderFromDB.get(i)));

			}
			PriceCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("TotalPrice"));
			DateCol.setCellValueFactory(new PropertyValueFactory<Order, Timestamp>("ActualDate"));
			ItemsCol.setCellValueFactory(new PropertyValueFactory<Order, String>("AllItems"));
			refundStatusCol.setCellValueFactory(new PropertyValueFactory<Order, RefundStatus>("refundStatus"));
			OrderTable.setItems(Orders);
			OrderTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		}
	}

	@FXML
	public void BackButton(MouseEvent event) throws Exception {
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

	@FXML
	public void MakeComplaintButton(ActionEvent event) throws IOException {

		selectedOrder = OrderTable.getSelectionModel().getSelectedItems();
		if (selectedOrder.size() != 0) {

			if (selectedOrder.get(0).getRefundStatus().equals(RefundStatus.NULL)) {
				((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader
						.load(getClass().getResource("/ClientFXMLFiles/WriteComplaintForCustomer.fxml"));
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
			} else {
				PopUpMsg.AlertForUser("You have already made a complaint to this Order");
			}

		} else {
			PopUpMsg.AlertForUser("Please Select Order to Make Complaint");
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
