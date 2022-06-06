package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import AllUsers.ConfirmationStatus;
import AllUsers.User;
import AllUsers.Users;
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
 * controlling the change user permission by branch manager
 *  
 *@author maisalon safory
 *
 */
public class ChangeUserPermissionController extends UsersController implements Initializable {
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	/**
	 * Users table view
	 */
	@FXML
	private TableView<Users> UsersTable;
	/**
	 * user ID column
	 */
	@FXML
	private TableColumn<Users, String> UserIDcol;
	/**
	 * First name column
	 */
	@FXML
	private TableColumn<Users, String> FirstNamecol;
	/**
	 * Last name column
	 */
	@FXML
	private TableColumn<Users, String> lastNamecol;
	/**
	 * Email column
	 */
	@FXML
	private TableColumn<Users, String> Emailcol;
	/**
	 * Type column
	 */
	@FXML
	private TableColumn<Users, String> Typecol;
	/**
	 * Error label
	 */
	@FXML
	private Label ErrorLabel;
	/**
	 * Array list for change permission
	 */
	public static ArrayList<Users> ArrayForChangedPermission = new ArrayList<>();
	/**
	 * Observable List for users
	 */
	public ObservableList<Users> users = FXCollections.observableArrayList();
	/**
	 *
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the users in data base 
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		message = new FullMessage(Request.GET_USERS_FROM_DB, Response.Wait, "worker");
		ZliClientUI.ZliClientController.accept(message);
		if (!(message.getResponse().equals(Response.USER_FOUND))) {
			ErrorLabel.setText("No Users");
		} else {
			ViewUsers();
		}
		message = new FullMessage(Request.GET_USERS_FROM_DB, Response.Wait, "deliveryperson");
		ZliClientUI.ZliClientController.accept(message);
		if (!(message.getResponse().equals(Response.USER_FOUND))) {
			ErrorLabel.setText("No Users");
		} else {
			ViewUsers();
		}
		message = new FullMessage(Request.GET_USERS_FROM_DB, Response.Wait, "customer");
		ZliClientUI.ZliClientController.accept(message);
		if (!(message.getResponse().equals(Response.USER_FOUND))) {
			ErrorLabel.setText("No Users");
		} else {
			ViewUsers();
		}
		message = new FullMessage(Request.GET_USERS_FROM_DB, Response.Wait, "customerserviceworker");
		ZliClientUI.ZliClientController.accept(message);
		if (!(message.getResponse().equals(Response.USER_FOUND))) {
			ErrorLabel.setText("No Users");
		} else {
			ViewUsers();
		}
		message = new FullMessage(Request.GET_USERS_FROM_DB, Response.Wait, "servicespecialist");
		ZliClientUI.ZliClientController.accept(message);
		if (!(message.getResponse().equals(Response.USER_FOUND))) {
			ErrorLabel.setText("No Users");
		} else {
			ViewUsers();
		}

		UsersTable.setItems(users);
		UsersTable.setEditable(true);
		
		Typecol.setOnEditCommit(event -> {
			Users selectedUser = UsersTable.getSelectionModel().getSelectedItem();
			Optional<ButtonType> Option=PopUpMessage.ConfirmationForUser("Edit this User?");
			if(Option.get()== ButtonType.OK) {
			String[] msgServer=new String[3];
			Users user=event.getRowValue();
			user.setEmail(user.getUserType());//old type
			user.setUserType(event.getNewValue());


		message = new FullMessage(Request.UPDATE_TYPE_USER, Response.Wait, user);
			ZliClientUI.ZliClientController.accept(message);
			if ((message.getResponse().equals(Response.USER_UPDATED))) {
				Option=PopUpMessage.ConfirmationForUser("The Permission is updated");
			}
			else {
				Option=PopUpMessage.ConfirmationForUser("Try again!!");
			}
			}
		});
		

	}
	/**
	 *
	 *This function prevents code duplication 
	 *And put the user in the table 
	 *And add comboBox in type column
	 */
	public void ViewUsers() {

		for (int i = 0; i < ArrayForChangedPermission.size(); i++) {
			users.add(new Users(ArrayForChangedPermission.get(i)));
		}
		UserIDcol.setCellValueFactory(new PropertyValueFactory<Users, String>("UserID"));
		FirstNamecol.setCellValueFactory(new PropertyValueFactory<Users, String>("FirstName"));
		lastNamecol.setCellValueFactory(new PropertyValueFactory<Users, String>("LastName"));
		Emailcol.setCellValueFactory(new PropertyValueFactory<Users, String>("Email"));
		Typecol.setCellValueFactory(new PropertyValueFactory<Users, String>("UserType"));
		String[] UserTypeArray = { "worker", "customer", "customerserviceworker", "servicespecialist","deliveryperson" };
		Typecol.setCellFactory((param) -> new ComboBoxTableCell<>(new StringConverter<String>() {
			@Override
			public String toString(String object) {
				return object.toString();
			}

			@Override
			public String fromString(String string) {
				return String.valueOf(string);
			}

		}, UserTypeArray));

	}
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

}
