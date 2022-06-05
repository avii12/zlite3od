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

	public static FullMessage message;
	@FXML
    private ComboBox<String> Quarterly1;

    @FXML
    private ComboBox<String> Year;

    @FXML
    private ComboBox<String> Quarterly2;

    @FXML
    private ComboBox<String> BranchName;

    @FXML
    private Label ErrorLabel;
    static double[] TotalPriceForIncome=new double[2];
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
	@FXML
	public void exitButton(MouseEvent event) throws IOException {

		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}
	
	
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
		
	
	 
	 public void DisplayMessageForUser(String message) {
			Platform.runLater(() -> {
				ErrorLabel.setText(message);
			});

		}
	
	}




