package ClientGUIControllers;

import java.awt.TextArea;
import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

import Orders.Branch;
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

public class ViewTwoReportsForOneBranchController extends UsersController implements Initializable {
	/**
	 *static parameter message of full message
	 */
	public static FullMessage message;
	/**
	 *ComboBox for quarterly1
	 */
	@FXML
    private ComboBox<String> Quarterly1;
	/**
	 *ComboBox for year
	 */
    @FXML
    private ComboBox<String> Year;
    /**
	 *ComboBox for quarterly2
	 */
    @FXML
    private ComboBox<String> Quarterly2;
    /**
	 *ComboBox for branch name
	 */
    @FXML
    private ComboBox<String> BranchName;
    /**
	 *Error label
	 */
    @FXML
    private Label ErrorLabel;
    /**
	 *double array for total price for income
	 */
    static double[] TotalPriceForIncome=new double[2];
    /**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> Quarterlies = FXCollections.observableArrayList("1-3","4-6", "7-12");
		ObservableList<String> BranchList = FXCollections.observableArrayList("TheSecretGarden", "YourNeighborhoodFlorist", "BeautifulBlossoms");
		Quarterly1.setValue("Quarterly1*");
		Quarterly2.setValue("Quarterly2*");
		BranchName.setValue("BranchName*");
		Year.setValue("Year*");
		Quarterly1.setItems(Quarterlies);
		Quarterly2.setItems(Quarterlies);
		BranchName.setItems(BranchList);
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
	@FXML
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
public void ViewReportsDistribution(ActionEvent event) throws IOException {
		
	if ((BranchName.getValue().equals("BranchName*")||
			(Quarterly1.getValue().equals("Quarterly1*")) || (Quarterly2.getValue().equals("Quarterly2*"))
			|| (Year.getValue().equals("Year*"))))
		{
		DisplayMessageForUser("Please fill all the required fields!");
		}
	else {
		String[] information=new String[4];
		information[0]=BranchName.getValue();
		information[1]=Quarterly1.getValue();
		information[2]=Quarterly2.getValue();
		information[3]=Year.getValue();
		message = new FullMessage(Request.GET_REPORT_FOR_TWO_QUARTERLY, Response.Wait,information);
		ZliClientUI.ZliClientController.accept(message);
		if(message.getResponse().equals(Response.REPORT_FOUND)) {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/GraphForCEO.fxml"));
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




