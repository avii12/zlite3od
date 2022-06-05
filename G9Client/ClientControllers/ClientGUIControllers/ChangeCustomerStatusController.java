package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import AllUsers.ConfirmationStatus;
import Report.customer;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMessage;
import ZliClient.ZliClientUI;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
/**
 * Class description: 
 * This is a class for 
 * controlling the change customer status by branch manager
 *  
 *@author Seren Hanany
 *
 */
public class ChangeCustomerStatusController extends UsersController implements Initializable {
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	
	/**
	 * Customer Table
	 */
	@FXML
	private TableView<customer> CustomerTable;
	/**
	 * Customer column
	 */
	@FXML
	private TableColumn<customer, String> CustomerIDcol;
	/**
	 * First name column
	 */
	@FXML
	private TableColumn<customer, String> FirstNamecol;
	/**
	 * Last name column
	 */
	@FXML
	private TableColumn<customer, String> lastNamecol;
	/**
	 * Email column
	 */
	@FXML
	private TableColumn<customer, String> Emailcol;
	/**
	 * Status column
	 */
	@FXML
	private TableColumn<customer, ConfirmationStatus> Statuscol;
	/**
	 * Error label
	 */
	@FXML
	private Label ErrorLabel;
	/**
	 * Array List for changed status
	 */
	public static ArrayList<customer> ArrayForChangedStatus = new ArrayList<>();
	/**
	 * After Clicking On Back Button 
	 * The Function Hide The Current Window 
	 * And Load The previous Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
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
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<customer> customer1 = FXCollections.observableArrayList();

		message = new FullMessage(Request.GET_CUSTOMER_FROM_DB, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		for (int i = 0; i < ArrayForChangedStatus.size(); i++) {
			customer1.add(new customer(ArrayForChangedStatus.get(i)));
		}
		CustomerIDcol.setCellValueFactory(new PropertyValueFactory<customer, String>("CustomerID"));
		FirstNamecol.setCellValueFactory(new PropertyValueFactory<customer, String>("FirstName"));
		lastNamecol.setCellValueFactory(new PropertyValueFactory<customer, String>("LastName"));
		Emailcol.setCellValueFactory(new PropertyValueFactory<customer, String>("Email"));
		Statuscol.setCellValueFactory(new PropertyValueFactory<customer, ConfirmationStatus>("Status"));

		ConfirmationStatus[] CustomerStatusArray = { ConfirmationStatus.CONFIRMED, ConfirmationStatus.FROZEN };
		Statuscol.setCellFactory((param) -> new ComboBoxTableCell<>(new StringConverter<ConfirmationStatus>() {
			@Override
			public String toString(ConfirmationStatus object) {
				return object.toString();
			}

			@Override
			public ConfirmationStatus fromString(String string) {
				return ConfirmationStatus.valueOf(string);
			}

		}, CustomerStatusArray));

		CustomerTable.setItems(customer1);
		CustomerTable.setEditable(true);
		Statuscol.setOnEditCommit(event -> {
			customer selectedUser = CustomerTable.getSelectionModel().getSelectedItem();
			Optional<ButtonType> Option=PopUpMessage.ConfirmationForUser("Edit this customer?");
			if(Option.get()== ButtonType.OK) {
			customer msgToServer = event.getRowValue();
			msgToServer.setStatus(event.getNewValue());
			msgToServer.setCustomerID(selectedUser.getCustomerID());
			message = new FullMessage(Request.UPDATE_STATUS_CUSTOMER, Response.Wait, msgToServer);
			ZliClientUI.ZliClientController.accept(message);
			if (message.getResponse().equals(Response.EDIT_SUCCEED)) {
				ErrorLabel.setText("Customer ID:" + selectedUser.getCustomerID() + " edited.");
			} else {
				ErrorLabel.setText("Try again!!");
			}
			}
		});
	}

	

}
