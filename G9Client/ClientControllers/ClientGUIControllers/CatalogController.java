package ClientGUIControllers;

/**
 * class description:
 * this class is for the Catalog screen 
 * functionality of the Catalog
 * 
 * @author Seren Hanany
 * 
 * @version 11/05/2022
 *
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import AllUsers.ConfirmationStatus;
import Orders.DominantColor;
import Orders.FlowerColor;
import Orders.Item;
import Orders.ItemCategory;
import Orders.ItemType;
import Orders.ItemsForCartView;
import Orders.ItemsForTableView;
import Orders.Order;
import Orders.OrderStatus;
import Orders.PriceRange;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import Worker.SaleColumn;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class description: Controlling The Catalog
 *
 * @author obied haddad
 *
 */
public class CatalogController extends UsersController implements Initializable {
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	/**
	 * variable for Price Of Items In Cart
	 */
	private static double PriceOfItemsInCart = 0;
	/**
	 * Array list for ID
	 */
	public static ArrayList<String> ListOfID = new ArrayList<>();
	/**
	 * variable status -for save status
	 */
	private ConfirmationStatus status = CustomerPageController.status;
	/**
	 * Image view for cart
	 */
	@FXML
	private ImageView CartButton;
	/**
	 * Button for add
	 */
	@FXML
	private Button AddButton;
	/**
	 * Image view for cart
	 */
	@FXML
	private Button Refresh;
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
	 * ComboBox for price
	 */
	@FXML
	private ComboBox<PriceRange> PriceComboBox;
	/**
	 * Error label
	 */
	@FXML
	private Label ErrorLabel;
	/**
	 * Sales label
	 */
	@FXML
	private Label SalesLabel;
	/**
	 * Table View for products
	 */
	@FXML
	private TableView<ItemsForTableView> ProductsTable;
	/**
	 * Table Column for products
	 */
	@FXML
	private TableColumn<ItemsForTableView, ImageView> ProductPicCol;
	/**
	 * Table Column for products name
	 */
	@FXML
	private TableColumn<ItemsForTableView, String> ProductNameCol;
	/**
	 * Table Column for products price
	 */
	@FXML
	private TableColumn<ItemsForTableView, Double> ProductPriceCol;
	/**
	 * Table Column for products type
	 */
	@FXML
	private TableColumn<ItemsForTableView, String> ProductTypeCol;
	/**
	 * Table Column for Product Dominant Color
	 */
	@FXML
	private TableColumn<ItemsForTableView, String> ProductDominantColorCol;
	/**
	 * Table View for Items
	 */
	@FXML
	private TableView<ItemsForTableView> ItemTable;
	/**
	 * Table Column for Items picture
	 */
	@FXML
	private TableColumn<ItemsForTableView, ImageView> ItemPicCol;
	/**
	 * Table Column for Items name
	 */
	@FXML
	private TableColumn<ItemsForTableView, String> ItemNameCol;
	/**
	 * Table Column for Items price
	 */
	@FXML
	private TableColumn<ItemsForTableView, Double> ItemPriceCol;
	/**
	 * Table Column for Items type
	 */
	@FXML
	private TableColumn<ItemsForTableView, String> ItemTypeCol;
	/**
	 * Table Column for Item Dominant Color
	 */
	@FXML
	private TableColumn<ItemsForTableView, String> ItemDominantColorCol;
	/**
	 * Error frozen label
	 */
	@FXML
	private Label ErrorFrozenLabel;
	/**
	 * static variable for sale on
	 */
	public static String IsSaleOn;
	/**
	 * static variable for percent
	 */
	public static String percent;
	/**
	 * static variable for branch
	 */
	public static String branch;
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git

	private int selectedIndex;
=======
>>>>>>> bff3c74 ssff
	/**
	 * Observable List Item in cart
	 */
<<<<<<< Upstream, based on branch 'master' of https://github.com/avii12/zlite3od.git

=======
>>>>>>> bff3c74 ssff
	public static ObservableList<ItemsForCartView> ItemsIncart = FXCollections.observableArrayList();
	/**
	 * static variable for Total Price
	 */
	public static double TotalPrice = 0;
	/**
	 * Button Compose
	 */
	@FXML
	private Button ComposeButton;

	/**
	 * Image View
	 */
	@FXML
	private ImageView Back;
	/**
	 * Button for ShowItem.
	 */
	@FXML
	private Button ShowItem;
	/**
	 * Button for Show Products.
	 */
	@FXML
	private Button ShowProducts;
	/**
	 * Array list for items from DB
	 */
	public static ArrayList<Item> ItemListFromDB = new ArrayList<>();
	/**
	 * variable for Bouquet
	 */
	public static int Bouque = 1;

	public static Order order = new Order(null, CurrentUser.getID(), null, OrderStatus.PENDING, null, null, null, null,
			PriceOfItemsInCart, null, null, 0.0, null, null, null);
	/**
	 * Array list for Is Sale On
	 */
	public static ArrayList<String> IsSaleOn1 = new ArrayList<>();
	/**
	 * Array list for percent
	 */
	public static ArrayList<String> percent1 = new ArrayList<>();
	/**
	 * Array list for branch
	 */
	public static ArrayList<String> branch1 = new ArrayList<>();

	/**
	 *
	 * Initializing The List After Getting All The Relevant Data Send To The Server
	 * Message That Contains All the Relevent Data
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ClearLists();

		if (status.equals(ConfirmationStatus.FROZEN)) {

			CartButton.setDisable(true);
			AddButton.setDisable(true);
			ComposeButton.setDisable(true);
			ErrorFrozenLabel.setText("Your Account is FROZEN!");
		}

		else {
			ErrorFrozenLabel.setText("");
			AddButton.setDisable(false);
			ComposeButton.setDisable(false);
			CartButton.setDisable(false);
		}

		ObservableList<ItemsForTableView> items = FXCollections.observableArrayList();
		ObservableList<ItemsForTableView> products = FXCollections.observableArrayList();
		message = new FullMessage(Request.GET_ITEMS_FROM_DB, Response.Wait, "catalog");
		ZliClientUI.ZliClientController.accept(message);

		for (int i = 0; i < ItemListFromDB.size(); i++) {
			if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.BOUQUET)
				products.add(new ItemsForTableView(ItemListFromDB.get(i)));
			else
				items.add(new ItemsForTableView(ItemListFromDB.get(i)));
		}

		message = new FullMessage(Request.CHECK_IF_SALES_ARE_ON_FOR_CATALOG, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		ArrayList<SaleColumn> saleColumn = (ArrayList<SaleColumn>) message.getObject();
		for (int i = 0; i < saleColumn.size(); i++) {
			IsSaleOn1.add(saleColumn.get(i).getSaleOn());
			percent1.add(saleColumn.get(i).getPercent());
			branch1.add(saleColumn.get(i).getBranch());
		}
		if (IsSaleOn1.contains("2"))
			IsSaleOn = "2";
		else if (IsSaleOn1.contains("1"))
			IsSaleOn = "1";
		else if (IsSaleOn1.contains("0"))
			IsSaleOn = "0";

		switch (IsSaleOn) {

		case "1":
			// percent = parsedMsgFromClient[1];
			LabelControl("All Items in Catalog for all branches are on Sale with " + percent1 + "%");
			break;

		case "2":
			String percent = "";
			for (int i = 0; i < percent1.size(); i++) {
				if (percent1.get(i).equals("0")) {

				} else
					percent = percent + " " + percent1.get(i) + "%";
			}
			percent = RemoveDuplicate(percent);

			String name = "";
			;

			for (int i = 0; i < branch1.size(); i++) {
				if (branch1.get(i).equals("0")) {

				} else
					name = name + " " + branch1.get(i);
			}
			name = RemoveDuplicate(name);
			LabelControl("All Items in Catalog for " + name + " " + "are on sale with " + percent + " %");
			break;
		case "0":
			LabelControl("");
			break;

		}
		if (IsSaleOn.equals("1")) {
			LabelControl("All Items in Catalog for all branches are on Sale with " + percent1.get(0) + "%");
		} else {

		}

		ArrayList<PriceRange> pricerange = new ArrayList<>();
		pricerange.add(PriceRange.RANGE1);
		pricerange.add(PriceRange.RANGE2);
		pricerange.add(PriceRange.RANGE3);
		pricerange.add(PriceRange.RANGE4);

		ArrayList<FlowerColor> colorlist = new ArrayList<>();
		colorlist.add(FlowerColor.PURPLE);
		colorlist.add(FlowerColor.RED);
		colorlist.add(FlowerColor.WHITE);
		colorlist.add(FlowerColor.YELLOW);

		ArrayList<ItemType> flowertypelist = new ArrayList<>();
		flowertypelist.add(ItemType.FLORABLOOM);
		flowertypelist.add(ItemType.LILLY);

		TypeComboBox.getItems().addAll(flowertypelist);
		ColorComboBox.getItems().addAll(colorlist);
		PriceComboBox.getItems().addAll(pricerange);

		ProductPicCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, ImageView>("Picture"));
		ProductNameCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, String>("Name"));
		ProductPriceCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, Double>("Price"));
		ProductTypeCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, String>("Type"));
		ProductDominantColorCol
				.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, String>("DominantColor"));

		ItemPicCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, ImageView>("Picture"));
		ItemNameCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, String>("Name"));
		ItemPriceCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, Double>("Price"));
		ItemTypeCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, String>("Type"));
		ItemDominantColorCol.setCellValueFactory(new PropertyValueFactory<ItemsForTableView, String>("DominantColor"));

		ProductsTable.setItems(products);
		ItemTable.setItems(items);
		ItemTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		ProductsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		ProductsTable.refresh();
		ItemTable.refresh();
	}

	/**
	 * Function for clear lists
	 */
	public void ClearLists() {

		if (branch1.size() != 0)
			branch1.clear();
		if (IsSaleOn1.size() != 0)
			IsSaleOn1.clear();
		if (percent1.size() != 0)
			percent1.clear();
	}

	/**
	 * Function gets String Remove Duplicate
	 * 
	 * @param name
	 * @return
	 */
	public String RemoveDuplicate(String name) {

		String[] words = name.split("\\W+");
		StringBuilder stringBuilder = new StringBuilder();
		Set<String> wordsHashSet = new HashSet<>();

		for (String word : words) {
			if (wordsHashSet.contains(word.toLowerCase()))
				continue;
			wordsHashSet.add(word.toLowerCase());
			stringBuilder.append(word).append(" ");
		}
		String nonDuplicateString = stringBuilder.toString().trim();
		return nonDuplicateString;
	}

	/**
	 * Function that add item to list
	 * 
	 * @param items
	 */
	public void AddItemToListForCart(ObservableList<ItemsForTableView> items) {

		String name = null;
		errorControl("");

		for (int i = 0; i < items.size(); i++) {

			String Id = items.get(i).getItem().getItemID();
			name = items.get(i).getItem().getItemName();
			ItemType type = items.get(i).getType();
			String picturePath = items.get(i).getPicturePath();
			FlowerColor color = items.get(i).getColor();
			DominantColor dominantcol = items.get(i).getDominantColor();
			int amount = items.get(i).getAmount();

			message = new FullMessage(Request.CHECK_AMOUNT, Response.Wait, Id);
			ZliClientUI.ZliClientController.accept(message);

			if (message.getResponse() == Response.PRODUCT_NOT_IN_INVENTORY)
				errorControl(name + " not in inventory!!");
			else {

				double price = items.get(i).getPrice();
				TotalPrice += price;
				ItemsIncart.add(new ItemsForCartView(
						new Item(Id, null, color, name, price, picturePath, null, type, dominantcol, amount), Bouque,
						name));

			}
		}
		Bouque++;
	}

	/**
	 * After clicking add button the function gets the selection item and add to
	 * list for cart
	 * 
	 * @param event
	 */
	@FXML
	public void AddButton(ActionEvent event) {

		ObservableList<ItemsForTableView> selectedProducts = ProductsTable.getSelectionModel().getSelectedItems();
		if (selectedProducts.size() != 0)
			PopUpMsg.AlertForUser("Items have been added to Cart");
		else
			PopUpMsg.AlertForUser("Please Select Items to Add to Cart");
		AddItemToListForCart(selectedProducts);

	}

	/**
	 * After Clicking Compose Button gets the selection items and add to list for
	 * cart with adding number for bouquet
	 * 
	 * @param event
	 */
	@FXML
	public void ComposeButton(ActionEvent event) {

		ObservableList<ItemsForTableView> selectedProducts = ItemTable.getSelectionModel().getSelectedItems();
		if (selectedProducts.size() != 0)
			PopUpMsg.AlertForUser("Items have been added to Cart");
		else
			PopUpMsg.AlertForUser("Please Select Items to Add to Cart");
		AddItemToListForCart(selectedProducts);
	}

	/**
	 * After Clicking On Back Button The Function Hide The Current Window And Load
	 * The previous Window And We Can Drag the Window How Ever We Want
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void BackBtn(MouseEvent event) throws Exception {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
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

	/**
	 * After Clicking Cart Button The Function Hide The Current Window And Load The
	 * Cart Window
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void CartButton(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CartPage.fxml"));
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
	 * This function reload the catalog from DB
	 */
	@FXML
	public void ReloadCatalog() {

		ObservableList<ItemsForTableView> items = FXCollections.observableArrayList();
		ObservableList<ItemsForTableView> products = FXCollections.observableArrayList();
		message = new FullMessage(Request.GET_ITEMS_FROM_DB, Response.Wait, "catalog");
		ZliClientUI.ZliClientController.accept(message);

		for (int i = 0; i < ItemListFromDB.size(); i++) {
			if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.BOUQUET)
				products.add(new ItemsForTableView(ItemListFromDB.get(i)));
			else
				items.add(new ItemsForTableView(ItemListFromDB.get(i)));
		}

		ProductsTable.setItems(products);
		ItemTable.setItems(items);

	}

	/**
	 * After Clicking On Exit Button The Function Send A Message To The Server The
	 * Function LogOut The Account And Disconnect From The Server
	 * 
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
	 * After Clicking on get price Function show the products that in the wanted
	 * range
	 * 
	 * @param event
	 */
	@FXML
	public void GetPrice(ActionEvent event) {

		ObservableList<ItemsForTableView> items = FXCollections.observableArrayList();

		if (PriceComboBox.getValue().equals(PriceRange.RANGE1)) {

			message = new FullMessage(Request.GET_ITEMS_ACCORDING_TO_PRICE, Response.Wait, PriceRange.RANGE1);
			ZliClientUI.ZliClientController.accept(message);

			for (int i = 0; i < ItemListFromDB.size(); i++) {
				if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.FLOWER)
					items.add(new ItemsForTableView(ItemListFromDB.get(i)));
			}
			ItemTable.setItems(items);
		}

		else if (PriceComboBox.getValue().equals(PriceRange.RANGE2)) {

			message = new FullMessage(Request.GET_ITEMS_ACCORDING_TO_PRICE, Response.Wait, PriceRange.RANGE2);
			ZliClientUI.ZliClientController.accept(message);

			for (int i = 0; i < ItemListFromDB.size(); i++) {
				if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.FLOWER)
					items.add(new ItemsForTableView(ItemListFromDB.get(i)));
			}
			ItemTable.setItems(items);
		} else if (PriceComboBox.getValue().equals(PriceRange.RANGE3)) {

			message = new FullMessage(Request.GET_ITEMS_ACCORDING_TO_PRICE, Response.Wait, PriceRange.RANGE3);
			ZliClientUI.ZliClientController.accept(message);

			for (int i = 0; i < ItemListFromDB.size(); i++) {
				if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.FLOWER)
					items.add(new ItemsForTableView(ItemListFromDB.get(i)));
			}
			ItemTable.setItems(items);
		}

		else if (PriceComboBox.getValue().equals(PriceRange.RANGE4)) {

			message = new FullMessage(Request.GET_ITEMS_ACCORDING_TO_PRICE, Response.Wait, PriceRange.RANGE4);
			ZliClientUI.ZliClientController.accept(message);

			for (int i = 0; i < ItemListFromDB.size(); i++) {
				if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.FLOWER)
					items.add(new ItemsForTableView(ItemListFromDB.get(i)));
			}
			ItemTable.setItems(items);
		}

	}

	/**
	 * After Clicking on get price Function show the products that in the wanted
	 * type
	 * 
	 * @param event
	 */
	@FXML
	public void GetType(ActionEvent event) {

		ObservableList<ItemsForTableView> items = FXCollections.observableArrayList();

		if (TypeComboBox.getValue().equals(ItemType.FLORABLOOM)) {

			message = new FullMessage(Request.GET_ITEMS_ACCORDING_TO_TYPE, Response.Wait, ItemType.FLORABLOOM);
			ZliClientUI.ZliClientController.accept(message);

			for (int i = 0; i < ItemListFromDB.size(); i++) {
				if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.FLOWER)
					items.add(new ItemsForTableView(ItemListFromDB.get(i)));
			}
			ItemTable.setItems(items);
		}

		else if (TypeComboBox.getValue().equals(ItemType.LILLY)) {

			message = new FullMessage(Request.GET_ITEMS_ACCORDING_TO_TYPE, Response.Wait, ItemType.LILLY);
			ZliClientUI.ZliClientController.accept(message);

			for (int i = 0; i < ItemListFromDB.size(); i++) {
				if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.FLOWER)
					items.add(new ItemsForTableView(ItemListFromDB.get(i)));
			}
			ItemTable.setItems(items);
		}

	}

	/**
	 * After Clicking on get price Function show the products that in the wanted
	 * color
	 * 
	 * @param event
	 */
	@FXML
	public void GetColor(ActionEvent event) {

		ObservableList<ItemsForTableView> items = FXCollections.observableArrayList();

		if (ColorComboBox.getValue().equals(FlowerColor.RED)) {

			message = new FullMessage(Request.GET_ITEMS_ACCORDING_TO_COLOR, Response.Wait, FlowerColor.RED);
			ZliClientUI.ZliClientController.accept(message);

			for (int i = 0; i < ItemListFromDB.size(); i++) {
				if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.FLOWER)
					items.add(new ItemsForTableView(ItemListFromDB.get(i)));
			}
			ItemTable.setItems(items);
		}

		else if (ColorComboBox.getValue().equals(FlowerColor.PURPLE)) {

			message = new FullMessage(Request.GET_ITEMS_ACCORDING_TO_COLOR, Response.Wait, FlowerColor.PURPLE);
			ZliClientUI.ZliClientController.accept(message);

			for (int i = 0; i < ItemListFromDB.size(); i++) {
				if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.FLOWER)
					items.add(new ItemsForTableView(ItemListFromDB.get(i)));
			}
			ItemTable.setItems(items);
		} else if (ColorComboBox.getValue().equals(FlowerColor.WHITE)) {

			message = new FullMessage(Request.GET_ITEMS_ACCORDING_TO_COLOR, Response.Wait, FlowerColor.WHITE);
			ZliClientUI.ZliClientController.accept(message);

			for (int i = 0; i < ItemListFromDB.size(); i++) {
				if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.FLOWER)
					items.add(new ItemsForTableView(ItemListFromDB.get(i)));
			}
			ItemTable.setItems(items);
		}

		else if (ColorComboBox.getValue().equals(FlowerColor.YELLOW)) {

			message = new FullMessage(Request.GET_ITEMS_ACCORDING_TO_COLOR, Response.Wait, FlowerColor.YELLOW);
			ZliClientUI.ZliClientController.accept(message);

			for (int i = 0; i < ItemListFromDB.size(); i++) {
				if (ItemListFromDB.get(i).getItemCategory() == ItemCategory.FLOWER)
					items.add(new ItemsForTableView(ItemListFromDB.get(i)));
			}
			ItemTable.setItems(items);
		}
	}

	/**
	 * This function gets String message put the message on error label
	 * 
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
	 * This function gets String message put the message on error label
	 * 
	 * @param msg
	 */
	private void LabelControl(String message) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SalesLabel.setText(message);
				SalesLabel.setVisible(true);
			}

		});
	}
}