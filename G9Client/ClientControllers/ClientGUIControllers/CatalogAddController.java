package ClientGUIControllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import Orders.DominantColor;
import Orders.FlowerColor;
import Orders.Item;
import Orders.ItemCategory;
import Orders.ItemType;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *Class description:  
 *Controlling The adding to Catalog 
 *
 *@author obied haddad
 *
 */
public class CatalogAddController extends UsersController implements Initializable {
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	/**
	 * Pane id
	 */
	@FXML
	private Pane anchorid;
	/**
	 * Text field for path
	 */
	@FXML
	private TextField TextFieldPath;
	/**
	 * Text field for name
	 */
	@FXML
	private TextField TextFieldName;
	/**
	 * Text field for price
	 */
	@FXML
	private TextField TextFieldPrice;
	/**
	 * Text field for amount
	 */
	@FXML
	private TextField TextFieldAmount;
	/**
	 * ComboBox for category
	 */
	@FXML
	private ComboBox<ItemCategory> CategoryComboBox;
	/**
	 * ComboBox for color
	 */
	@FXML
	private ComboBox<FlowerColor> ColorComboBox;
	/**
	 * ComboBox for type
	 */
	@FXML
	private ComboBox<ItemType> TypeComboBox;
	/**
	 * ComboBox for Dominant
	 */
	@FXML
	private ComboBox<DominantColor> DominantComboBox;
	/**
	 * Error label
	 */
	@FXML
	private Label ErrorLabel;
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevent Data 
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		SetComboBoxes();
	}

	/**
	 * This function set valued in comboBoxes 
	 */
	public void SetComboBoxes() {
		
		ArrayList<ItemCategory> categoryList = new ArrayList<>();
		categoryList.add(ItemCategory.BOUQUET);
		categoryList.add(ItemCategory.FLOWER);

		ArrayList<FlowerColor> colorList = new ArrayList<>();
		colorList.add(FlowerColor.GREEN);
		colorList.add(FlowerColor.MIXED);
		colorList.add(FlowerColor.PINK);
		colorList.add(FlowerColor.PURPLE);
		colorList.add(FlowerColor.RED);
		colorList.add(FlowerColor.WHITE);
		colorList.add(FlowerColor.YELLOW);

		ArrayList<ItemType> typeList = new ArrayList<>();
		typeList.add(ItemType.FLORABLOOM);
		typeList.add(ItemType.LILLY);

		ArrayList<DominantColor> dominantColor = new ArrayList<>();
		dominantColor.add(DominantColor.GREEN);
		dominantColor.add(DominantColor.MIXED);
		dominantColor.add(DominantColor.PINK);
		dominantColor.add(DominantColor.PURPLE);
		dominantColor.add(DominantColor.RED);
		dominantColor.add(DominantColor.WHITE);
		dominantColor.add(DominantColor.YELLOW);

		DominantComboBox.getItems().addAll(dominantColor);
		TypeComboBox.getItems().addAll(typeList);
		ColorComboBox.getItems().addAll(colorList);
		CategoryComboBox.getItems().addAll(categoryList);

	}

	/**
	 * Boolean function text field is valid
	 * Check the value text fields 
	 * @return
	 */
	private boolean TextFieldsIsInvalid() {
		
		boolean check = false;
		if (TextFieldName.getText().equals("")) {

			TextFieldName.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldName.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}
		if (TextFieldPrice.getText().equals("")) {

			TextFieldPrice.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldPrice.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}
		if (TextFieldAmount.getText().equals("")) {

			TextFieldAmount.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldAmount.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}

		if (TextFieldPath.getText().equals("")) {

			TextFieldPath.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldPath.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}
		return check;

	}

	/**
	 * Boolean function comboBox is valid
	 * Check the value text fields  
	 * @return
	 */
	private boolean ComboBoxesIsInvalid() {
		
		boolean check = false;

		if (CategoryComboBox.getValue() == null) {

			CategoryComboBox.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			CategoryComboBox.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}

		if (ColorComboBox.getValue() == null) {

			ColorComboBox.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			ColorComboBox.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}

		if (TypeComboBox.getValue() == null) {

			TypeComboBox.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TypeComboBox.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}

		if (DominantComboBox.getValue() == null) {

			DominantComboBox.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			DominantComboBox.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}
		return check;
	}

	/**
	 * After Clicking On add Button 
	 * The Function Hide The Current Window and logout. 
	 * Load The Catalog Management Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void AddButton(ActionEvent event) throws IOException {

		String price = TextFieldPrice.getText();
		String amount = TextFieldAmount.getText();
		if (TextFieldsIsInvalid()) {
			errorControl("Please fill all Empty Details");
			if (ComboBoxesIsInvalid()) {
				errorControl("Please fill all Empty Details");
			}
		} else {
			char firstcharAmount = amount.charAt(0);
			char firstcharPrice = price.charAt(0);
			if (!(isNumeric(price)) || !(isNumeric(amount)))
				errorControl("input is Invalid for Amount or Price!!");
			else if (firstcharPrice == '0' || firstcharAmount == '0') {
				errorControl("Input Cannot Start with 0 for Price and amount");
			} else {

				Optional<ButtonType> Option = PopUpMsg.ConfirmationForUser("Are You Sure You want to Add this Item?!");
				if (Option.get() == ButtonType.OK) {
					ItemCategory category = CategoryComboBox.getValue();
					FlowerColor color = ColorComboBox.getValue();
					String Name = TextFieldName.getText();
					double Price = Double.parseDouble(TextFieldPrice.getText());
					String Path = TextFieldPath.getText();
					String card = "";
					ItemType type = TypeComboBox.getValue();
					DominantColor dominant = DominantComboBox.getValue();
					int Amount = Integer.parseInt(TextFieldAmount.getText());

					Item itemToAdd = new Item(null, category, color, Name, Price, Path, card, type, dominant, Amount);
					message = new FullMessage(Request.ADD_ITEM_TO_DB, Response.Wait, itemToAdd);
					ZliClientUI.ZliClientController.accept(message);
					PopUpMsg.AlertForUser("Item Added Successfully");
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
				else {
					
				}
			}
		}
	}

	/**
	 * This function gets string text
	 * Check if the text contain number 
	 * @param strNum
	 * @return
	 */
	public static boolean isNumeric(String strNum) {
		
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	/**
	 * After Clicking On Exit Button 
	 * The Function Send A Message To The Server The
	 * Function LogOut The Account And Disconnect From The Server
	 * @param event
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
	 * After Clicking on Browse Button
	 * Load The Browse Window 
	 * @param event
	 */
	@FXML
	public void BrowseButton(ActionEvent event) {

		String slash = "\\\\";
		String path;
		String[] parsedMsg;
		final FileChooser chooser = new FileChooser();
		Stage stage = (Stage) anchorid.getScene().getWindow();
		File file = chooser.showOpenDialog(stage);

		if (file != null) {
			parsedMsg = file.getAbsolutePath().split("C:");
			path = "File:C:" + slash + parsedMsg[1];
			TextFieldPath.setText(path);
		}
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
