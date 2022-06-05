package ClientGUIControllers;


import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.ZliClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Class description: 
 * This is a class for 
 * controlling the complaint report 
 *  
 *@author Seren Hanany
 *
 */
public class ComplaintReportController extends UsersController implements Initializable {
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	/**
	 * ComboBox for report year
	 */
	@FXML
    private ComboBox<String> ReportYear;
	/**
	 * Error label
	 */
	@FXML
	private Label ErrorLabel;
	@FXML
	/**
	 * ComboBox for quarterly
	 */
	private ComboBox<String> Quarterly;
	/**
	 * Array String for save the quarterly
	 */
	public static String[] quarterly=new String[2];
	/**
	 * After Clicking On Exit Button
	 * The Function Send A Message To The Server 
	 * The Function LogOut The Account 
	 * And Disconnect From The Server  
	 * @param event
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
	public void BackBtn(MouseEvent event) throws Exception {
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
	 * After Clicking On View Quarterly Complaint Button 
	 * The Function Hide The Current Window  
	 * And Load The View Quarterly Complaint Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
	public void ViewQuarterlyComplaint(MouseEvent event) throws IOException {
		quarterly[0] = Quarterly.getValue().toString();
		quarterly[1]=ReportYear.getValue().toString();
		if (Quarterly.getValue().equals("Quarterly*") && ReportYear.getValue().toString().equals("Year*")) {
			Quarterly.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ReportYear.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			 ErrorLabel.setText("Fill the two combobox");
		} else if (Quarterly.getValue().equals("Quarterly*") && !(ReportYear.getValue().toString().equals("Year*"))){
			Quarterly.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ReportYear.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");
			 ErrorLabel.setText("Fill the wanted quarterly");
		}else if (!(Quarterly.getValue().equals("Quarterly*")) && ReportYear.getValue().toString().equals("Year*")){
			Quarterly.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");
			ReportYear.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			 ErrorLabel.setText("Fill the wanted year");
		}else {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/Graph.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}

	/**
	 *
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 *
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> quarterly = FXCollections.observableArrayList("1-3", "4-6", "7-9", "10-12");
		Quarterly.setValue("Quarterly*");
		ReportYear.setValue("Year*");
		Quarterly.setItems(quarterly);
		Year y=java.time.Year.now();
		for (int i = y.getValue(); i > 2009; i--) {
			ReportYear.getItems().add("" + i);
			}

	}

}
