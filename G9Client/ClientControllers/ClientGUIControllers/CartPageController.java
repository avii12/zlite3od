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

public class CartPageController extends UsersController implements Initializable {

	private ObservableList<ItemsForCartView> ItemsIncart = FXCollections.observableArrayList();

	public static FullMessage message;

	@FXML
	private double TotalPrice = 0;

	@FXML
	private Text TextPrice;
	@FXML
	private Label ErrorLabel;
	@FXML
	private TableView<ItemsForCartView> CartTable;
	@FXML
	public TableColumn<ItemsForCartView, String> CartNameCol;
	@FXML
	public TableColumn<ItemsForCartView, ItemType> CartTypeCol;
	@FXML
	public TableColumn<ItemsForCartView, String> CartPriceCol;
	@FXML
	private TableColumn<ItemsForCartView, Integer> CartBouqueCol;
	@FXML
	public static Order order;

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

	@FXML
	public void PaymentButton(MouseEvent event) throws IOException {
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
