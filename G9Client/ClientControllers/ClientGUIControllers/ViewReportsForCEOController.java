package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Class description: 
 * This is a class for 
 * controlling Graph for CEO
 *  
 * @author Seren Hanany
 *
 */
public class ViewReportsForCEOController extends UsersController implements Initializable {
	/**
	 *static parameter message of full message
	 */
	public static FullMessage message;
	/**
	 *array list for income report
	 */
	public static ArrayList<String> IncomeReport = new ArrayList<>();
	/**
	 *array list for orders report
	 */
	public static ArrayList<String> OrdersReport = new ArrayList<>();
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
	@FXML
	/**
	 * ComboBox of branch name.
	 */
	private ComboBox<String> BranchName;
	/**
	 * ComboBox of quarterly.
	 */
	@FXML
	private ComboBox<String> Quarterly;
	/**
	 * ComboBox of quarterly year.
	 */
	@FXML
	private ComboBox<String> ReportYear1;
	/**
	 * Error label
	 */
	@FXML
	private Label ErrorLabel;
	/**
	 * Text area to show reports
	 */
	@FXML
	private TextArea TextAreaReport;
	/**
	 * After Clicking On find report Button 
	 * And We Can show the wanted report in area text
	 * @param event
	 * @throws IOException
	 */
	public void FindReportBtn(ActionEvent event) throws Exception {
		TextAreaReport.clear();
		DisplayMessageForUser("");
		if ((BranchName.getValue().equals("BranchName*") || (ReportType.getValue().equals("Type*"))))
	    {
			DisplayMessageForUser("Please fill all the required fields!");
		} 
		else {
			
			if ((!(Quarterly.getValue().equals("Quarterly"))) && (!(ReportYear1.getValue().equals("Year")))) {
				String[] quarterly = new String[3];
				quarterly[0]=Quarterly.getValue();
				quarterly[1]=BranchName.getValue();
				quarterly[2]=ReportYear1.getValue();
				
				message = new FullMessage(Request.GET_NUM_OF_COMPLAINT_FOR_CEO, Response.Wait,quarterly);
				ZliClientUI.ZliClientController.accept(message);
				
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/GraphForCEO1.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
				
				
				
			}
			else if ((!(ReportMonth.getValue().equals("Month"))) && (!(ReportYear.getValue().equals("Year")))) {
				String branchname = BranchName.getValue();
				String type = ReportType.getValue();
				String date = ReportYear.getValue() + "-" + ReportMonth.getValue() + "-01";
				String[] dateAndTypeAndBranch = new String[3];
				dateAndTypeAndBranch[0] = date;
				dateAndTypeAndBranch[1] = type;
				dateAndTypeAndBranch[2] = branchname;

				message = new FullMessage(Request.CHECK_REPORT_FROM_DB_FOR_CEO, Response.Wait, dateAndTypeAndBranch);
				ZliClientUI.ZliClientController.accept(message);
				if (message.getResponse().equals(Response.NO_REPORT)) {
					DisplayMessageToTextAreaIncome("No report founded");
				}

				else {

					if (message.getResponse().equals(Response.REPORT_FOUND)) {
						DisplayMessageToTextAreaIncome(ReportType.getValue().toString() + " report founded.");
						DisplayMessageToTextAreaIncome("******");

						switch (ReportType.getValue()) {
						case "Income":
							message = new FullMessage(Request.GET_REPORT_FROM_DB_FOR_CEO, Response.Wait,
									dateAndTypeAndBranch);
							ZliClientUI.ZliClientController.accept(message);
							if (!(message.getResponse().equals(Response.NO_REPORT))) {
								DisplayMessageToTextAreaIncome(" Branch Name: " + IncomeReport.get(4).toString());
								DisplayMessageToTextAreaIncome(" Report ID: " + IncomeReport.get(0).toString());
								DisplayMessageToTextAreaIncome(" Report Date: " + IncomeReport.get(1).toString());
								DisplayMessageToTextAreaIncome(" Total Price: " + IncomeReport.get(2).toString());
								DisplayMessageToTextAreaIncome(" Num Of Orders: " + IncomeReport.get(3).toString());
							}
							break;
						case "Orders":
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
							String[] DateAndBranch = new String[4];
							DateAndBranch[0] = date;
							DateAndBranch[1] = nextMonth;
							DateAndBranch[2] = branchname;
							message = new FullMessage(Request.GET_ORDER_REPORT_FROM_DB_FOR_CEO, Response.Wait, DateAndBranch);
							ZliClientUI.ZliClientController.accept(message);
							if ((message.getResponse().equals(Response.REPORT_FOUND))) {
								
								DisplayMessageToTextAreaIncome(" ID Report: " + OrdersReport.get(0).toString());
								DisplayMessageToTextAreaIncome(" Report Date: " + OrdersReport.get(1).toString());
								DisplayMessageToTextAreaIncome(" Num Of Orders: " + OrdersReport.get(2).toString());
								DisplayMessageToTextAreaIncome(" Num Of Late Orders: " + OrdersReport.get(3).toString());
								DisplayMessageToTextAreaIncome(".........................");
								
							}
							break;

						}
					}
				}
			}else {
				DisplayMessageForUser("Please fill all the required fields!");
			}
			
	
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
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> BranchList = FXCollections.observableArrayList("TheSecretGarden", "YourNeighborhoodFlorist", "BeautifulBlossoms");
		ObservableList<String> Types = FXCollections.observableArrayList("Income", "Complaint", "Orders");
		ReportType.setValue("Type*");
		BranchName.setValue("BranchName*");
		Quarterly.setValue("Quarterly");
		ReportYear1.setValue("Year");
		ReportYear.setValue("Year");
		ReportMonth.setValue("Month");
		BranchName.setItems(BranchList);
		ReportType.setItems(Types);
		ReportYear.setDisable(true);
		ReportMonth.setDisable(true);
		ReportYear1.setDisable(true);
		Quarterly.setDisable(true);
		ReportType.setEditable(true);
	}

	/**
	 * After Clicking On continue Button
	 * The Function get type value and show the relevant comboBox 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void continueBtn(ActionEvent event) throws Exception {
		
		ObservableList<String> quarterly = FXCollections.observableArrayList("1-3", "4-6", "7-9", "10-12");
		Year y = java.time.Year.now();
		if (ReportType.getValue().equals("Complaint")) {

			ReportYear1.setDisable(false);
			Quarterly.setDisable(false);
			ReportYear.setDisable(true);
			ReportMonth.setDisable(true);
			for (int i = y.getValue(); i > 2009; i--) {
				ReportYear1.getItems().add("" + i);
				Quarterly.setItems(quarterly);
			}
		} else if (ReportType.getValue().equals("Income") || ReportType.getValue().equals("Orders")) {
			ReportYear.setDisable(false);
			ReportMonth.setDisable(false);
			ReportYear1.setDisable(true);
			Quarterly.setDisable(true);
			for (int i = y.getValue(); i > 2009; i--) {
				ReportYear.getItems().add("" + i);
			}
			for (int i = 1; i < 13; i++) {
				if (i < 10)
					ReportMonth.getItems().add("0" + i);
				else
					ReportMonth.getItems().add("" + i);
			}
		}
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
}
