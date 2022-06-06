package ClientGUIControllers;

import java.io.IOException;

import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import AllUsers.Customer;
import Orders.Order;
import Orders.TypeOfSupply;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Class description: 
 * This is a class for 
 * controlling the Start Sales 
 *  
 *@author Obied haddad
 *
 */
public class PaymentController extends UsersController implements Initializable {
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;

	@FXML
	/**
	 * Text For error Text For The User 
	 */
	private Text errorText;
	@FXML
	/**
	 * Text Field For Credit Card of the user 
	 */
	private TextField TextFieldCreditCard;
	/**
	 * variable of Order
	 */
	
	public static Order order;

	@Override
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 */
	public void initialize(URL location, ResourceBundle resources) {

		if (OrderDetailsController.order.getSupplyType().equals(TypeOfSupply.DELIVERY)) {
			PaymentController.order = DeliveryDetailsController.order;

		} else
			PaymentController.order = OrderDetailsController.order;

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

	private boolean isInvalid() {
		boolean check = false;

		if (TextFieldCreditCard.getText().equals("")) {

			TextFieldCreditCard.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldCreditCard.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}
		return check;
	}

	@FXML
	/**
	 * After Clicking On Done Button 
	 * The Function Hide The Current Window 
	 * And Load The Customer Page
	 * @param event
	 * @throws IOException
	 */
	public void DoneButton(ActionEvent event) throws IOException, ParseException {

		int OrderId = 0;

		Optional<ButtonType> Option = PopUpMsg
				.ConfirmationForUser("After clicking okay the Payment will procceed, Are you Sure?!");
		if (Option.get() == ButtonType.OK) {

			message = new FullMessage(Request.GET_CUSTOMER_DETAILS, Response.Wait, CurrentUser);
			ZliClientUI.ZliClientController.accept(message);
			Customer objectFromServer = (Customer) message.getObject();

			String creditcard = objectFromServer.getCreditcard();
			double balance = objectFromServer.getBalance();

			switch (message.getResponse()) {

			case CUSTOMER_FOUND:

				if (!(TextFieldCreditCard.getText().equals(creditcard))) {

					errorText.setText("Credit Card Number is Invalid!!");
					errorText.setFill(Color.RED);

				}

				else {

					if (balance < order.TotalPrice) {

						errorText.setText("You Dont Have Enough Money In Your Account!!");
						errorText.setFill(Color.RED);
					}

					else {
						errorText.setText("Credit Card Approved" + "\n" + "You order is being Proccesed");
						errorText.setFill(Color.BLUE);
						double NewBalance = balance - order.TotalPrice;

						message = new FullMessage(Request.GET_LAST_ORDER_ID, Response.Wait, CurrentUser);
						ZliClientUI.ZliClientController.accept(message);

						switch (message.getResponse()) {

						case NO_ORDER_FOUND:

							OrderId = 1;
							double price = order.getTotalPrice() * 80 / 100;
							order.setTotalPrice(price);
							order.setOrderNumber(Integer.toString(OrderId));
							message = new FullMessage(Request.INSERT_ORDER_TO_DB, Response.Wait, order);
							ZliClientUI.ZliClientController.accept(message);
							message = new FullMessage(Request.UPDATE_CUSTOMER_BALANCE, Response.Wait,
									NewBalance + " " + CurrentUser.getID());
							ZliClientUI.ZliClientController.accept(message);
							PopUpMsg.AlertForUser("Congratulations you are the fist one to order from ZerLi!! \n"
									+ "You get 20% discount \n" + "The final price is: " + order.getTotalPrice());
							CatalogController.ItemsIncart.clear();
							break;

						case NOT_FIRST_ORDER:

							OrderId = (int) message.getObject();
							OrderId++;
							order.setOrderNumber(Integer.toString(OrderId));
							message = new FullMessage(Request.INSERT_ORDER_TO_DB, Response.Wait, order);
							ZliClientUI.ZliClientController.accept(message);
							message = new FullMessage(Request.UPDATE_CUSTOMER_BALANCE, Response.Wait,
									NewBalance + " " + CurrentUser.getID());
							ZliClientUI.ZliClientController.accept(message);
							CatalogController.ItemsIncart.clear();
							// CatalogController.TextPrice.setText("");
							break;

						}

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
						CatalogController.TotalPrice = 0.0;
						PopUpMsg.AlertForUser("Order is Finished!!");

					}

				}
			}
		} else {

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
	public void BackBtn(MouseEvent event) throws Exception {
		
		String path = null;
		if (order.getSupplyType().equals(TypeOfSupply.DELIVERY)) {
			path = "/ClientFXMLFiles/DeliveryDetails.fxml";
		}

		else {
			path = "/ClientFXMLFiles/OrderDetails.fxml";
		}
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource(path));
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
