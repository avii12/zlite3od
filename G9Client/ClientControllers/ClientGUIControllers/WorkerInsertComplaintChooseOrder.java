package ClientGUIControllers;

import java.io.IOException;


import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Orders.Branch;
import Orders.Order;
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

/**
 * Class description: 
 * This is a class for 
 * controlling the  Worker Insert Complaint Choose Order 
 *  
 *@author maisalon safory
 *
 */
public class WorkerInsertComplaintChooseOrder extends UsersController implements Initializable {
	
	
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	/**
	 * variable of complaint
	 */
	
	public static Complaint complaint;
	/**
	 * Array list for order
	 */
	public static ArrayList<Order> OrderFromDB = new ArrayList<>();
	/**
	 * Observable List for customers
	 */
	public ObservableList<Order> customers = FXCollections.observableArrayList();
	/**
	 * Observable List for Order
	 */
	public static ObservableList<Order> selectedorder;
	@FXML
	/**
	 *  Table Displays The Orders 
	 */
	private TableView<Order> OrderTable;
	@FXML
	/**
	 * Column That Contains The Order Number
	 */
	private TableColumn<Order, String> OrderNumberCol;
	@FXML
	/**
	 * Column That Contains The Order Date
	 */
	private TableColumn<Order, Timestamp> DateCol;
	@FXML
	/**
	 * Column That Contains The Order Branch
	 */
	private TableColumn<Order, Branch> BranchCol;
	@FXML
	/**
	 * Column That Contains The Order All Items 
	 */
	private TableColumn<Order, String> AllItemsCol;
	@FXML
	/**
	 * Label For Message For The User 
	 */
	private Label ErrorLabel;

	@Override
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 */
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
	/**
	 * After Clicking On Exit Button
	 * The Function Send A Message To The Server 
	 * The Function LogOut The Account 
	 * And Disconnect From The Server  
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
     * 
	 * The Function Choose Order To Insert Complaint
     * @param event
     * @throws IOException
     */
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
	/**
	 * After Clicking On Back Button 
	 * The Function Hide The Current Window 
	 * And Load The previous Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
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
	/**
	 * The Function Display's The Message On The Label
	 * @param message
	 */
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
