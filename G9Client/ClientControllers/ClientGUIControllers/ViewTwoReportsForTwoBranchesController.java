package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Class description: 
 * This is a class for 
 * controlling Graph for CEO
 *  
 * @author mario rohana
 *
 */
public class ViewTwoReportsForTwoBranchesController extends UsersController implements Initializable {
	/**
	 *static parameter message of full message
	 */
	public static FullMessage message;
	/**
	 *ComboBox for years
	 */
	@FXML
	private ComboBox<String> Year;
	/**
	 *Error label
	 */
	@FXML
	private Label ErrorLabel;
	@FXML
	/**
	 * ComboBox of First Branch Name.
	 */
	private ComboBox<String> FirstBranchName;
	@FXML
	/**
	 * ComboBox of Second Branch Name.
	 */
	private ComboBox<String> SecondBranchName;
	@FXML
	/**
	 * ComboBox of Quarterly.
	 */
	private ComboBox<String> Quarterly;
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> BranchList = FXCollections.observableArrayList("TheSecretGarden", "YourNeighborhoodFlorist", "BeautifulBlossoms");
		ObservableList<String> Quarterlies = FXCollections.observableArrayList("1-3", "4-6", "7-12");
		FirstBranchName.setValue("FirstBranchName");
		SecondBranchName.setValue("SecondBranchName");
		Quarterly.setValue("Quarterly*");
		Year.setValue("Year*");
		Quarterly.setItems(Quarterlies);
		FirstBranchName.setItems(BranchList);
		SecondBranchName.setItems(BranchList);
		Year y=java.time.Year.now();
		for (int i = y.getValue(); i > 2009; i--) {
			Year.getItems().add("" + i);
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
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CEOPage.fxml"));
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
	 * @throws IOException
	 */
	@FXML
	public void exitButton(MouseEvent event) throws IOException {

		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}
	/**
	 * after clicking on View Reports Distribution button
	 * The function Load a graph window
	 * @param event
	 * @throws IOException
	 */
	public void ViewReportsDistributionBtn(ActionEvent event) throws IOException {
		if ((FirstBranchName.getValue().equals("FirstBranchName*")||
				(SecondBranchName.getValue().equals("SecondBranchName*")) || (Quarterly.getValue().equals("Quarterly*"))
				|| (Year.getValue().equals("Year*"))))
			{
			DisplayMessageForUser("Please fill all the required fields!");
			}
		else {
			String[] information=new String[4];
			information[0]=FirstBranchName.getValue();
			information[1]=SecondBranchName.getValue();
			information[2]=Quarterly.getValue();
			information[3]=Year.getValue();
			message = new FullMessage(Request.GET_REPORT_FOR_TWO_BRANCHES, Response.Wait,information);
			ZliClientUI.ZliClientController.accept(message);
			if(message.getResponse().equals(Response.REPORT_FOUND)) {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/GraphForCEO2.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			}
			else {
				DisplayMessageForUser("No Report Founded!");
			}
		}//else1

	}

	/**
	 * This function gets String message
	 * put the message on error label
	 * @param msg
	 */

	public void DisplayMessageForUser(String message) {
		Platform.runLater(() -> {
			ErrorLabel.setText(message);
		});

	}

}
