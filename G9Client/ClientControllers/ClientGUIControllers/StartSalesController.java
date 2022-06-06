package ClientGUIControllers;

import java.io.IOException;



import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Orders.Branch;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class description: 
 * This is a class for 
 * controlling the Start Sales 
 *  
 *@author maisalon safory
 *
 */
public class StartSalesController extends UsersController implements Initializable {
	
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	@FXML
	/**
	 * End Sales Button
	 */

	private Button EndSalesBtn;
	@FXML
	/**
	 * Start Sales Button
	 */
	private Button StartSalesBtn;
	@FXML
	/**
	 * Label For Message For The User 
	 */
	private Label ErrorLabel;
	@FXML
	/**
	 * ComboBox for Sales percentage
	 */
	private ComboBox<String> SaleComboBox;
	@FXML
	/**
	 * ComboBox for Branches
	 */
	private ComboBox<Branch> BranchComboBox;
	@FXML
	/**
	 * Start Sales Branch button
	 */
	private Button StartSalesBranch;
	@FXML
	/**
	 * Boolean of Start Sales with Branch
	 */
	private boolean StartSalesWithBranchClicked = true;
	@FXML
	/**
	 * variable of Branch
	 */
	
	private Branch branch;

	@Override
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 */
	public void initialize(URL location, ResourceBundle resources) {
		

		message = new FullMessage(Request.GET_WORKER_BRANCH, Response.Wait, CurrentUser.getID());
		ZliClientUI.ZliClientController.accept(message);

		branch = (Branch) message.getObject();

		message = new FullMessage(Request.CHECK_IF_SALES_ARE_ON, Response.Wait, branch);
		ZliClientUI.ZliClientController.accept(message);
		String[] parsedMsgFromServer = (String[]) message.getObject();
		String sale = parsedMsgFromServer[0];

		if (sale.equals("1") || sale.equals("2")) {
			StartSalesBranch.setDisable(true);
			StartSalesBtn.setDisable(true);
			EndSalesBtn.setDisable(false);
		}

		if (sale.equals("1"))
			StartSalesWithBranchClicked = false;
		else
			StartSalesWithBranchClicked = true;
		ArrayList<Branch> branches = new ArrayList<>();
		branches.add(branch);
		ArrayList<String> sales = new ArrayList<>();
		sales.add("20");
		sales.add("50");
		sales.add("70");
		SaleComboBox.getItems().addAll(sales);
		BranchComboBox.getItems().addAll(branches);

	}

	@FXML
	/**
	 * after clicking on EndSales button disable sales  
	 * @param event
	 */
	public void EndSales(ActionEvent event) {
		

		message = new FullMessage(Request.CHECK_IF_CATALOG_EXIST_TO_START_SALES, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);

		switch (message.getResponse()) {

		case NO_CATALOG:
			errorControl("No Catalog Was Found to End Sales");
			StartSalesBtn.setDisable(false);
			EndSalesBtn.setDisable(true);
			break;

		case CATALOG_FOUND:

			Optional<ButtonType> Option = PopUpMsg.ConfirmationForUser("Are You sure you want to Start the Sales!?");
			if (Option.get() == ButtonType.OK) {

				StartSalesBranch.setDisable(true);
				StartSalesBtn.setDisable(false);
				EndSalesBtn.setDisable(true);
				errorControl("");

				message = new FullMessage(Request.GET_THE_SALE_PERCENTAGE_FROM_WORKER, Response.Wait, branch);
				ZliClientUI.ZliClientController.accept(message);
				String percent = (String) message.getObject();
				message = new FullMessage(Request.CHECK_IF_SALES_ARE_ON, Response.Wait, branch);
				ZliClientUI.ZliClientController.accept(message);
				String[] parsedMsgFromServer = (String[]) message.getObject();

				String length = String.valueOf(parsedMsgFromServer.length);
				message = new FullMessage(Request.UPDATE_WORKER_AFTER_END_SALE, Response.Wait,
						branch.toString() + " " + length);
				ZliClientUI.ZliClientController.accept(message);
				if (!StartSalesWithBranchClicked) {
					message = new FullMessage(Request.UPDATE_PRICES_AFTER_END_SALES, Response.Wait, percent);
					ZliClientUI.ZliClientController.accept(message);
				}

			}

		}

	}

	@FXML
	/**
	 * when choosing value from StartSalesComboAction ComboBox is enable
	 * @param event
	 */
	public void StartSalesComboAction(ActionEvent event) {
		

		StartSalesBranch.setDisable(false);
	}

	@FXML
	/**
	 * after clicking on StartSalesWithSpecificBranch button starting sales for the branch
	 * @param event
	 */
	private void StartSalesWithSpecificBranch(ActionEvent event) {

		StartSalesWithBranchClicked = true;

		message = new FullMessage(Request.CHECK_IF_CATALOG_EXIST_TO_START_SALES, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);

		switch (message.getResponse()) {

		case NO_CATALOG:
			errorControl("No Catalog Was Found to Start Sales");
			break;

		case CATALOG_FOUND:
			errorControl("");

			if (SaleComboBox.getValue() == null || BranchComboBox.getValue() == null) {

				errorControl("Please Select Values from the ComboBoxes!!");
			} else {

				Optional<ButtonType> Option = PopUpMsg
						.ConfirmationForUser("Are You sure you want to Start the Sales For The "
								+ BranchComboBox.getValue().toString() + "Branch");
				if (Option.get() == ButtonType.OK) {

					StartSalesBranch.setDisable(true);
					StartSalesBtn.setDisable(true);
					EndSalesBtn.setDisable(false);
					errorControl("");

					String percent = SaleComboBox.getValue();

					message = new FullMessage(Request.UPDATE_WORKER_SALE_FOR_SPECIFIC_BRANCH, Response.Wait,
							CurrentUser.getID() + " " + percent + " " + BranchComboBox.getValue().toString());
					System.out
							.println(CurrentUser.getID() + " " + percent + " " + BranchComboBox.getValue().toString());
					ZliClientUI.ZliClientController.accept(message);

				}
			}

		}

	}

	@FXML
	/**
	 * after clicking on StartSales button starting sales for all branches
	 * @param event
	 */
	public void StartSales(ActionEvent event) {

		StartSalesWithBranchClicked = false;
		message = new FullMessage(Request.CHECK_IF_CATALOG_EXIST_TO_START_SALES, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);

		switch (message.getResponse()) {

		case NO_CATALOG:
			errorControl("No Catalog Was Found to Start Sales");
			break;

		case CATALOG_FOUND:
			errorControl("");

			if (SaleComboBox.getValue() == null) {

				errorControl("No Percent Selected!!");
			} else {

				Optional<ButtonType> Option = PopUpMsg
						.ConfirmationForUser("Are You sure you want to Start the Sales!?");
				if (Option.get() == ButtonType.OK) {

					StartSalesBranch.setDisable(true);
					StartSalesBtn.setDisable(true);
					EndSalesBtn.setDisable(false);
					errorControl("");

					String percent = SaleComboBox.getValue();

					message = new FullMessage(Request.UPDATE_WORKER_SALE, Response.Wait,
							CurrentUser.getID() + " " + percent);
					ZliClientUI.ZliClientController.accept(message);

					message = new FullMessage(Request.UPDATE_PRICES_AFTER_SALES, Response.Wait, percent);
					ZliClientUI.ZliClientController.accept(message);

				}
			}

		}

	}

	
	@FXML
	/**
	 * After Clicking On Exit Button
	 * The Function Send A Message To The Server 
	 * The Function LogOut The Account 
	 * And Disconnect From The Server  
	 * @param event
	 * @throws IOException
	 */
	public void ExitButton(MouseEvent event) throws IOException {
		

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
