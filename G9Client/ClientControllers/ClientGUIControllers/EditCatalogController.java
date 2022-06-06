package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Class description: Controlling The Customer Service worker page
 *
 * @author obied haddad
 *
 */
public class EditCatalogController extends UsersController implements Initializable {
	/**
	 * Error label
	 */
	@FXML
	private Label ErrorLabel;
	/**
	 * Image View for product picture
	 */
	@FXML
	private ImageView Picture;
	/**
	 * Text Field for name
	 */
	@FXML
	private TextField TextFieldName;
	/**
	 * Text Field for category
	 */
	@FXML
	private TextField TextFieldCategory;
	/**
	 * Text Field for type
	 */
	@FXML
	private TextField TextFieldType;
	/**
	 * Text Field for price
	 */
	@FXML
	private TextField TextFieldPrice;
	/**
	 * Text Field for amount
	 */
	@FXML
	private TextField TextFieldAmount;
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	/**
	 *
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		String category = String.valueOf(CatalogManagement.ItemCategory);
		String ItemType = String.valueOf(CatalogManagement.ItemType);
		Image image = new Image(CatalogManagement.PicturePath);
		Picture.setImage(image);
		TextFieldName.setText(CatalogManagement.ItemName);
		TextFieldCategory.setText(category);
		TextFieldType.setText(ItemType);
		TextFieldPrice.setText(Double.toString(CatalogManagement.ItemPrice));
		TextFieldAmount.setText(Integer.toString(CatalogManagement.Amount));

	}

	/**
	 * After clicking on save Button
	 * The Function send all the information to server
	 * @param event
	 */
	@FXML
	public void SaveButton(ActionEvent event) {

		String price = TextFieldPrice.getText();
		String amount = TextFieldAmount.getText();
		String ID = CatalogManagement.ID;
		char firstcharAmount = amount.charAt(0);
		char firstcharPrice = price.charAt(0);
		if (!(isNumeric(price)) || !(isNumeric(amount)))
			errorControl("input is Invalid!!");
		else if (firstcharPrice == '0' || firstcharAmount == '0')
			errorControl("Input Cannot Start with 0");
		else {

			Optional<ButtonType> Option = PopUpMsg
					.ConfirmationForUser("Are You Sure You want to save the changes you made?!");
			if (Option.get() == ButtonType.OK) {
				errorControl("");
				message = new FullMessage(Request.UPDATE_ITEM_IN_CATALOG, Response.Wait, //send new details to server
						ID + " " + price + " " + amount);
				ZliClientUI.ZliClientController.accept(message);
				PopUpMsg.AlertForUser("Changes have been Saved!");

			}

		}
	}
	/**
	 * After Clicking On Exit Button
	 * The Function Send A Message To The Server 
	 * The Function LogOut The Account 
	 * And Disconnect From The Server  
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void ExitButton(MouseEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}
	/**
	 * After Clicking On Back Button 
	 * The Function Hide The Current Window 
	 * And Load The previous Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void BackButton(MouseEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CatalogManagement.fxml"));
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
	 * Boolean function to check if the text contain numbers
	 * @param strNum
	 * @return
	 */
	public static boolean isNumeric(String strNum) {
		
		if (strNum == null) {
			return false;
		}
		try {
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	/**
	 * This function gets String message
	 * put the message on error label
	 * @param msg
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
