package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import Orders.DateTimeConvert;
import Orders.Order;
import Orders.OrderStatus;
import Orders.TypeOfSupply;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import Util.OrganizeDate;
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

public class CancelOrderController extends UsersController implements Initializable {

	@FXML
	private ImageView Backbtn;
	@FXML
	private ImageView Exitbtn;

	@FXML
	private TableView<Order> CancelTable;
	@FXML
	private TableColumn<Order, String> NameColCancel;
	@FXML
	private TableColumn<Order, Double> PriceColCancel;
	@FXML
	private TableColumn<Order, Timestamp> DateColCancel;
	@FXML
	private TableColumn<Order, OrderStatus> StatusColCancel;
	@FXML
	private Label ErrorLabel;

	ArrayList<Order> ArrayForChangedOrderStatus = new ArrayList<>();
	public static ArrayList<Order> OrderFromDB = new ArrayList<>();
	public static FullMessage message;

	public void BackButton(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		// Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<Order> Orders = FXCollections.observableArrayList();
		message = new FullMessage(Request.GET_ORDER_FROM_DB_FOR_CANCELORDER, Response.Wait, CurrentUser.getID());
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

			PriceColCancel.setCellValueFactory(new PropertyValueFactory<Order, Double>("TotalPrice"));
			NameColCancel.setCellValueFactory(new PropertyValueFactory<Order, String>("AllItems"));
			DateColCancel.setCellValueFactory(new PropertyValueFactory<Order, Timestamp>("EstimatedDate"));
			StatusColCancel.setCellValueFactory(new PropertyValueFactory<Order, OrderStatus>("orderstatus"));

			OrderStatus[] orderStatusArray = { OrderStatus.CANCEL };
			StatusColCancel.setCellFactory((param) -> new ComboBoxTableCell<>(new StringConverter<OrderStatus>() {
				@Override
				public String toString(OrderStatus object) {
					return object.toString();
				}

				@Override
				public OrderStatus fromString(String string) {
					return OrderStatus.valueOf(string);
				}

			}, orderStatusArray));

			CancelTable.setItems(Orders);
			CancelTable.setEditable(true);

			StatusColCancel.setOnEditCommit(event -> {
				Order order = event.getRowValue();
				order.setOrderstatus(event.getNewValue());
				Optional<ButtonType> Option = PopUpMessage.ConfirmationForUser("Are You Sure You Want To Continue");
				if (Option.get() == ButtonType.OK) {
					if (event.getNewValue() == OrderStatus.CANCEL) {
						errorControl("Please Wait Until The Approvel Of The BranchManger");
						order.setOrderstatus(OrderStatus.CANCEL);
						Orders.remove(order);		
						ArrayForChangedOrderStatus.add(order);
						message = new FullMessage(Request.CANCEL_ORDER_FINISHED, Response.WAIT_RESPONSE,
								ArrayForChangedOrderStatus);
						ZliClientUI.ZliClientController.accept(message);
						ArrayForChangedOrderStatus.remove(order);
					}
				} else {

				}
			});

			CancelTable.refresh();
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

	public void ExitButton(MouseEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}
}
