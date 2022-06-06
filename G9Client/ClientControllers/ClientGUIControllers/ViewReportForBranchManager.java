package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import Report.OrderReport;
import Report.IncomeReport;
import Report.Reports;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Class description: 
 * This is a class for 
 * controlling Graph for branch manager 
 *  
 * @author Ebrahem enbtawe
 *
 */
public class ViewReportForBranchManager extends UsersController implements Initializable {
	/**
	 *static parameter message of full message
	 */
	public static FullMessage message;
	/**
	 *Array list for reports from DB
	 */
	public static ArrayList<Reports> ReportFromDB = new ArrayList<>();
	/**
	 *Array list for branch manager
	 */
	public static ArrayList<String> BranchForManager = new ArrayList<>();
	/**
	 *Array list for income report
	 */
	public static ArrayList<String> IncomeReport = new ArrayList<>();
	/**
	 *Array list for orders report
	 */
	public static ArrayList<String> OrdersReport = new ArrayList<>();
	/**
	 *Text area to show reports
	 */
	@FXML
	private TextArea TextAreaReport;
	/**
	 *Table view for income report
	 */
	@FXML
	private TableView<IncomeReport> DetailsTable;
	/**
	 *Table view for orders report
	 */
	@FXML
	private TableView<OrderReport> OrderReport;
	/**
	 *Error label
	 */
	@FXML
	private Label ErrorLabel;
	@FXML
	/**
	 * ComboBox of years.
	 */
	private ComboBox<String> ReportYear;
	@FXML
	/**
	 * ComboBox of Month.
	 */
	private ComboBox<String> ReportMonth;
	@FXML
	/**
	 * ComboBox of Type.
	 */
	private ComboBox<String> ReportType;
	
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
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> Types = FXCollections.observableArrayList("Income", "Orders");
		ReportYear.setValue("Year*");
		ReportMonth.setValue("Month*");
		ReportType.setValue("Type*");
		
		Year y = java.time.Year.now();
		for (int i = y.getValue(); i > 2009; i--) {
			ReportYear.getItems().add("" + i);
			
		}
			
		for (int i = 1; i < 13; i++) {
			if (i < 10)
				ReportMonth.getItems().add("0" + i);
			else
				ReportMonth.getItems().add("" + i);
		}
		ReportType.setItems(Types);
	}
	/**
	 * After Clicking On search Button 
	 * And We Can show the wanted report in area text
	 * @param event
	 * @throws IOException
	 */
	public void SearchBtn(ActionEvent event) throws IOException {
		if (Check()) {
			TextAreaReport.clear();
			String date = ReportYear.getValue() + "-" + ReportMonth.getValue() + "-01";

			String[] TypeAndDate = new String[4];
			String managerID = CurrentUser.getID();
			message = new FullMessage(Request.GET_MANAGER_ID, Response.Wait, managerID);
			ZliClientUI.ZliClientController.accept(message);
			TypeAndDate[0] = date;
			TypeAndDate[1] = ReportType.getValue();
			TypeAndDate[2] = BranchForManager.get(0);
			message = new FullMessage(Request.CHECK_REPORT_FROM_DB, Response.Wait, TypeAndDate);
			ZliClientUI.ZliClientController.accept(message);
			if (message.getResponse().equals(Response.NO_REPORT)) {
				DisplayMessageToTextAreaIncome("No report founded");
			}

			else {

				if (message.getResponse().equals(Response.REPORT_FOUND)) {
					DisplayMessageToTextAreaIncome(ReportType.getValue().toString() + " report founded.");
					DisplayMessageToTextAreaIncome("******");

					if (ReportType.getValue().equals("Income")) {
						message = new FullMessage(Request.GET_REPORT_FROM_DB, Response.Wait, TypeAndDate);
						ZliClientUI.ZliClientController.accept(message);
						if (!(message.getResponse().equals(Response.NO_REPORT))) {
							DisplayMessageToTextAreaIncome(" Report ID: " + IncomeReport.get(0).toString());
							DisplayMessageToTextAreaIncome(" Report Date: " + IncomeReport.get(1).toString());
							DisplayMessageToTextAreaIncome(" Total Price: " + IncomeReport.get(2).toString());
							DisplayMessageToTextAreaIncome(" Num Of Orders: " + IncomeReport.get(3).toString());
							DisplayMessageToTextAreaIncome(" Branch Name: " + IncomeReport.get(4).toString());
						}
					}
					if (ReportType.getValue().equals("Orders")) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal = Calendar.getInstance();
						try {
							cal.setTime(dateFormat.parse(date));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						cal.add(Calendar.MONTH, 1);
						String nextMonth = dateFormat.format(cal.getTime());
						String[] DateAndBranch = new String[3];
						DateAndBranch[0] = date;
						DateAndBranch[1] = nextMonth;
						DateAndBranch[2] = BranchForManager.get(0);
						message = new FullMessage(Request.GET_ORDER_REPORT_FROM_DB, Response.Wait, DateAndBranch);
						ZliClientUI.ZliClientController.accept(message);
						if ((message.getResponse().equals(Response.REPORT_FOUND))) {
							
								DisplayMessageToTextAreaIncome(" ID Report: " + OrdersReport.get(0).toString());
								DisplayMessageToTextAreaIncome(" Report Date: " + OrdersReport.get(1).toString());
								DisplayMessageToTextAreaIncome(" Num Of Orders: " + OrdersReport.get(2).toString());
								DisplayMessageToTextAreaIncome(" Num Of Late Orders: " + OrdersReport.get(3).toString());
								DisplayMessageToTextAreaIncome(".........................");
								
						}

					}

				}
			}

		}
	}

	/**
	 * This function gets String message
	 * put the message on text area
	 * @param msg
	 */
	public void DisplayMessageToTextAreaIncome(String msg) {
		
		Platform.runLater(() -> {
			String Message = TextAreaReport.getText();
			this.TextAreaReport.setText(Message + "\n" + msg);
		});
	}

	/**
	 * Boolean function 
	 * Check all the values in comboBox
	 * @return
	 */
	public boolean Check() {
		
		ReportYear.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");
		ReportMonth.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");
		ReportType.setStyle("-fx-border-color: black;-fx-background-radius: 15; -fx-border-radius: 15");

		if ((ReportYear.getValue().equals("Year*")) && (ReportMonth.getValue().equals("Month*"))
				&& (ReportType.getValue().equals("Type*"))) {
			ReportYear.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ReportMonth.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ReportType.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ErrorLabel.setText("Please fill all the required fields (*)!");
			return false;
		}

		else if ((ReportYear.getValue().equals("Year*")) && (ReportMonth.getValue().equals("Month*"))
				&& (!(ReportType.getValue().equals("Type*")))) {
			ReportYear.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ReportMonth.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ErrorLabel.setText("Please fill all the required fields (*)!");
			return false;
		} else if ((ReportYear.getValue().equals("Year*")) && (!(ReportMonth.getValue().equals("Month*")))
				&& (!(ReportType.getValue().equals("Type*")))) {
			ReportYear.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ErrorLabel.setText("Please fill all the required fields (*)!");
			return false;
		} else if ((!(ReportYear.getValue().equals("Year*"))) && (ReportMonth.getValue().equals("Month*"))
				&& (ReportType.getValue().equals("Type*"))) {
			ReportType.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ReportMonth.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ErrorLabel.setText("Please fill all the required fields (*)!");
			return false;
		} else if ((ReportYear.getValue().equals("Year*")) && (!(ReportMonth.getValue().equals("Month*")))
				&& (ReportType.getValue().equals("Type*"))) {
			ReportYear.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ReportType.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ErrorLabel.setText("Please fill all the required fields (*)!");
			return false;
		} else if (!(ReportYear.getValue().equals("Year*")) && (ReportMonth.getValue().equals("Month*"))
				&& (!(ReportType.getValue().equals("Type*")))) {
			ReportMonth.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ErrorLabel.setText("Please fill all the required fields (*)!");
			return false;
		} else if ((!(ReportYear.getValue().equals("Year*"))) && (!(ReportMonth.getValue().equals("Month*")))
				&& (ReportType.getValue().equals("Type*"))) {
			ReportType.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			ErrorLabel.setText("Please fill all the required fields (*)!");
			return false;
		}
		return true;
	}

}
