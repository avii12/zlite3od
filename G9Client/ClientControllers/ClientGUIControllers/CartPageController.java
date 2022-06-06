package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Orders.ItemType;
import Orders.ItemsForCartView;
import Orders.Order;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *Class description:  
 *Controlling The Cart page for customer
 *For The Orders That Belongs To His Branch 
 *
 *@author shorok heib
 *
 */
public class CartPageController extends UsersController implements Initializable {
	/**
	 *Observable List for items on cart
	 */
	private ObservableList<ItemsForCartView> ItemsIncart = FXCollections.observableArrayList();
	/**
	 *static parameter message of full message
	 */
	public static FullMessage message;
	/**
	 *double total price
	 */
	@FXML
	private double TotalPrice = 0;
	/**
	 * Text 'text price' 
	 */
	@FXML
	private Text TextPrice;
	/**
	 *Error label
	 */
	@FXML
	private Label ErrorLabel;
	/**
	 *Table view for cart
	 */
	@FXML
	private TableView<ItemsForCartView> CartTable;
	/**
	 *Table column for name
	 */
	@FXML
	public TableColumn<ItemsForCartView, String> CartNameCol;
	/**
	 *Table column for product type.
	 */
	@FXML
	public TableColumn<ItemsForCartView, ItemType> CartTypeCol;
	/**
	 *Table column for Cart price
	 */
	@FXML
	public TableColumn<ItemsForCartView, String> CartPriceCol;
	/**
	 *Table column for Cart Bouquet.
	 */
	@FXML
	private TableColumn<ItemsForCartView, Integer> CartBouqueCol;
	/**
	 *variable for order
	 */
	@FXML
	public static Order order;
	/**
	 *
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevent Data 
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ItemsIncart = CatalogController.ItemsIncart;
		TotalPrice = CatalogController.TotalPrice;
		order = CatalogController.order;

		CartNameCol.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
		CartTypeCol.setCellValueFactory(new PropertyValueFactory<ItemsForCartView, ItemType>("Type"));
		CartPriceCol.setCellValueFactory(new PropertyValueFactory<ItemsForCartView, String>("Price"));
		CartBouqueCol.setCellValueFactory(new PropertyValueFactory<ItemsForCartView, Integer>("Bouque"));
		CartTable.setItems(ItemsIncart);
		TextPrice.setText(String.valueOf(TotalPrice));

	}
	/**
	 * After Clicking On Remove Button 
	 * And We Can show the table without the selected item
	 * @param event
	 */
	@FXML
	public void RemoveButton(ActionEvent event) {

		if (CatalogController.ItemsIncart.size() == 0) {

			errorControl("Cart Is Empty!!");

		} else {
			int IndexOfDeleted = CartTable.getSelectionModel().getSelectedIndex();
			if (IndexOfDeleted == -1) {

				errorControl("Please select an item to delete");

			} else {
				errorControl("");
				CatalogController.TotalPrice -= CatalogController.ItemsIncart.get(IndexOfDeleted).getPrice();
				String ID = CatalogController.ItemsIncart.get(IndexOfDeleted).getID();
				message = new FullMessage(Request.RESTORE_AMOUNT_FOR_ITEM, Response.Wait, ID);
				ZliClientUI.ZliClientController.accept(message);
				CatalogController.ItemsIncart.remove(IndexOfDeleted);
				TextPrice.setText(String.valueOf(CatalogController.TotalPrice));
				CartTable.refresh();
			}
		}
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
	public void BackButton(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CatalogPage.fxml"));
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
	/**
	 * After Clicking On Order Details Button 
	 * The Function Hide The Current Window . 
	 * Load The Order Details Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 */
	@FXML
	public void OrderDetailsButton(MouseEvent event) throws IOException {
		if (ItemsIncart.size() == 0) {
			errorControl("Please choose items, Cart Is Empty!!");
		} else {

			String name = ConvertToBouquets();
			order.setAllItems(name);
			order.setTotalPrice(TotalPrice);
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/OrderDetails.fxml"));
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

	/**
	 * This function Sorting the products into two types
	 * return name
	 * @return
	 */
	public String ConvertToBouquets() {

		int bouqueNumber = 1;
		String name = "Bouque: ";

		for (int i = 0; i < ItemsIncart.size(); i++) {

			if (ItemsIncart.get(i).getBouque() == bouqueNumber) {
				name = name + "" + ItemsIncart.get(i).getItemName() + ", ";
			}

			else if (ItemsIncart.get(i).getBouque() != bouqueNumber) {

				name = name + "  " + "Bouque " + ":";
				name = name + ItemsIncart.get(i).getItemName() + ", ";
				bouqueNumber++;
			}

		}

		return name;
	}

}
