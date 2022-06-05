package ClientGUIControllers;

import java.awt.Rectangle;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ComplaintReportController extends UsersController implements Initializable {

	public static FullMessage message;
	@FXML
    private ComboBox<String> ReportYear;
	@FXML
	private Label ErrorLabel;
	@FXML
	private ComboBox<String> Quarterly;

	public static String[] quarterly=new String[2];

	public void ExitButton(MouseEvent event) throws IOException {

		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

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

	public void ViewQuarterlyComplaint(MouseEvent event) throws IOException {
		quarterly[0] = Quarterly.getValue().toString();
		quarterly[1]=ReportYear.getValue().toString();
		if (quarterly.equals("Quarterly*")||ReportYear.getValue().toString().equals("Year*")) {
			 ErrorLabel.setText("Fill the wanted quarterly");
		} else {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/Graph.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}

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
