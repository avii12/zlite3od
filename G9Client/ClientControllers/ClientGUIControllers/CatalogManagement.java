package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import Orders.Item;
import Orders.ItemCategory;
import Orders.ItemsForTableView;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CatalogManagement extends UsersController implements Initializable {

	public static FullMessage message;

	public static ArrayList<Item> ItemListFromDB = new ArrayList<>();

	@FXML
	private TableView<ItemsForTableView> CatalogTable;
	@FXML
	private TableColumn<ItemsForTableView, ImageView> CatalogPictureCol;
	@FXML
	private TableColumn<ItemsForTableView, String> CatalogNameCol;
	@FXML
	private TableColumn<ItemsForTableView, ItemCategory> CatalogCategoryCol;

	public static String PicturePath;
	public static String ItemName;
	public static ItemCategory ItemCategory;
	public static Orders.ItemType ItemType;
	public static double ItemPrice;
	public static int Amount;
	public static String ID;

	@FXML
	private Label ErrorLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		GetCatalogForUser();

	}

	public void GetCatalogForUser() {

		CatalogPictureCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, ImageView>("Picture"));
		CatalogNameCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, String>("Name"));
		CatalogCategoryCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, ItemCategory>("Category"));

		ObservableList<ItemsForTableView> Allitems = FXCollections.observableArrayList();
		message = new FullMessage(Request.GET_ALL_ITEMS_FOR_WORKER, Response.Wait, "catalog");
		ZliClientUI.ZliClientController.accept(message);

		switch (message.getResponse()) {

		case CATALOG_FOUND:

			for (int i = 0; i < ItemListFromDB.size(); i++) {
				Allitems.add(new ItemsForTableView(ItemListFromDB.get(i)));
			}

			CatalogTable.setItems(Allitems);
			CatalogTable.refresh();
			break;

		case NO_CATALOG:
			errorControl("THE CATALOG IS EMPTY!!");
			break;

		}
	}

	@FXML
	public void RemoveButton(ActionEvent event) {

		ItemsForTableView SelectedItem = CatalogTable.getSelectionModel().getSelectedItem();

		if (CatalogTable.getSelectionModel().getSelectedItem() == null) {

			errorControl("Please Select an Item First!!");
		}

		else {

			Optional<ButtonType> Option = PopUpMsg.ConfirmationForUser("Are You Sure You want to remove this Item?!");
			String ID = SelectedItem.getID();
			String tablename = "catalog";
			if (Option.get() == ButtonType.OK) {
				CatalogTable.getSelectionModel().clearSelection();
				errorControl("");
				message = new FullMessage(Request.REMOVE_ITEM_FROM_CATALOG, Response.Wait, ID + " " + tablename);
				ZliClientUI.ZliClientController.accept(message);

				PopUpMsg.AlertForUser("Item Has Been Removed From Catalog!!");
				GetCatalogForUser();
				CatalogTable.refresh();

			}

			else {
				CatalogTable.getSelectionModel().clearSelection();
				errorControl("");

			}
		}

	}

	@FXML
	public void AddButton(ActionEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CatalogAddPage.fxml"));
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
	public void EditButton(ActionEvent event) throws IOException {

		ItemsForTableView SelectedItem = CatalogTable.getSelectionModel().getSelectedItem();

		if (CatalogTable.getSelectionModel().getSelectedItem() == null) {

			errorControl("Please Select an Item First!!");
		} else {

			PicturePath = SelectedItem.getPicturePath();
			ItemName = SelectedItem.getItemName();
			ItemCategory = SelectedItem.getCategory();
			ItemType = SelectedItem.getType();
			ItemPrice = SelectedItem.getPrice();
			Amount = SelectedItem.getAmount();
			ID = SelectedItem.getID();

			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CatalogEditPage.fxml"));
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
	public void ExitButton(MouseEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

	@FXML
	public void BackButton(MouseEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/NetWorkMarketingWorker.fxml"));
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

}
