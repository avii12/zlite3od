package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import Orders.Order;
import Orders.OrderStatus;
import Orders.TypeOfSupply;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMessage;
import ZliClient.ZliClientUI;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

public class AcceptOrderController extends UsersController implements Initializable {

	@FXML
	private ImageView Backbtn;
	@FXML
	private ImageView Exitbtn;

	@FXML
	private TableView<Order> AcceptTable;
	@FXML
	private TableColumn<Order, String> OrderNamecol;
	@FXML
	private TableColumn<Order, Double> PriceCol;
	@FXML
	private TableColumn<Order, TypeOfSupply> OrderTypeCol;
	@FXML
	private TableColumn<Order, Timestamp> DateCol;
	@FXML
	private TableColumn<Order, OrderStatus> Status;
	@FXML
	private Label errorLabel;

	ArrayList<Order> ArrayForChangedOrderStatus = new ArrayList<>();

	public static ArrayList<Order> OrderFromDB = new ArrayList<>();

	public static FullMessage message;

	public void BackButton(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/BranchManager.fxml"));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<Order> Orders = FXCollections.observableArrayList();
		message = new FullMessage(Request.GET_ORDER_FROM_DB, Response.Wait, CurrentUser.getID());
		ZliClientUI.ZliClientController.accept(message);
		ArrayForChangedOrderStatus = new ArrayList<Order>();
		switch (message.getResponse()) {

		case NO_ORDER_FOUND:
			errorControl("No Orders Found");
			break;

		case ORDER_FOUND:
			for (int i = 0; i < OrderFromDB.size(); i++) {
				Orders.add(new Order(OrderFromDB.get(i)));
			}

			PriceCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("TotalPrice"));
			OrderTypeCol.setCellValueFactory(new PropertyValueFactory<Order, TypeOfSupply>("SupplyType"));
			DateCol.setCellValueFactory(new PropertyValueFactory<Order, Timestamp>("EstimatedDate"));
			Status.setCellValueFactory(new PropertyValueFactory<Order, OrderStatus>("orderstatus"));
			OrderNamecol.setCellValueFactory(new PropertyValueFactory<Order, String>("AllItems"));

			OrderStatus[] orderStatusArray = { OrderStatus.APPROVED, OrderStatus.UN_APPROVED };
			Status.setCellFactory((param) -> new ComboBoxTableCell<>(new StringConverter<OrderStatus>() {
				@Override
				public String toString(OrderStatus object) {
					return object.toString();
				}

				@Override
				public OrderStatus fromString(String string) {
					return OrderStatus.valueOf(string);
				}

			}, orderStatusArray));

			AcceptTable.setItems(Orders);
			AcceptTable.setEditable(true);

			Status.setOnEditCommit(event -> {

				Optional<ButtonType> Option = PopUpMessage.ConfirmationForUser("Are You Sure You Want To Continue");
				if (Option.get() == ButtonType.OK) {
					Order order = event.getRowValue();
					order.setOrderstatus(event.getNewValue());
					if (event.getNewValue() == OrderStatus.APPROVED) {

						Orders.remove(order);
					} else if (event.getNewValue() == OrderStatus.UN_APPROVED) {
						ArrayList<String> UpdateBalance = new ArrayList<>();
						UpdateBalance.add(order.getOrderNumber());
						UpdateBalance.add(order.getCustomerID());

						message = new FullMessage(Request.UPDATE_BALANCE_ORDER_UNAPPROVED, Response.WAIT_RESPONSE,
								UpdateBalance);
						ZliClientUI.ZliClientController.accept(message);
						Orders.remove(order);
					}

					ArrayForChangedOrderStatus.add(order);
					message = new FullMessage(Request.MANAGE_ORDER_FINISHED, Response.WAIT_RESPONSE,
							ArrayForChangedOrderStatus);
					ZliClientUI.ZliClientController.accept(message);
					ArrayForChangedOrderStatus.remove(order);
				} else {
				}
			});

			AcceptTable.refresh();
		}
	}

	private void errorControl(String message) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				errorLabel.setText(message);
				errorLabel.setVisible(true);
			}

		});
	}

	public void ExitButton(MouseEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

}
