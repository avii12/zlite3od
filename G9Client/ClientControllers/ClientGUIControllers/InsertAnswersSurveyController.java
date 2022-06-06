package ClientGUIControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import Survey.SurveyAnswers;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Class description: 
 * This is a class for 
 * controlling Graph for branch manager 
 *  
 *@author Seren Hanany
 *
 */
public class InsertAnswersSurveyController extends UsersController implements Initializable {
	/**
	 *static parameter message of full message
	 */
	public static FullMessage message;
	/**
	 * Error label
	 */
	@FXML
	private Label errorLabel;
	/**
	 * Table view for answer 
	 */
	@FXML
	private TableView<SurveyAnswers> AnswersTable;
	/**
	 * Survey ID column
	 */
	@FXML
	private TableColumn<SurveyAnswers, String> SurveyIDcol;
	/**
	 * Customer ID column
	 */
	@FXML
	private TableColumn<SurveyAnswers, String> CustomerIDcol;
	/**
	 * Question number column 
	 */
	@FXML
	private TableColumn<SurveyAnswers, String> QuestionNumbercol;
	/**
	 * Answer column 
	 */
	@FXML
	private TableColumn<SurveyAnswers, String> Answercol;
	/**
	 * Array list for survey answers
	 */
	public static ArrayList<SurveyAnswers> ArrayForSurveyAnswers = new ArrayList<>();
	/**
	 * After Clicking On Exit Button
	 * The Function Send A Message To The Server 
	 * The Function LogOut The Account 
	 * And Disconnect From The Server  
	 * @param event
	 * @throws IOException
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
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CustomerServiceDepartment.fxml"));
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
	 * After Clicking On add Button 
	 * The Function Hide The Current Window  
	 * And send the answers to DB
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void AddBtn(ActionEvent event) {
		SurveyAnswers selectedUser = AnswersTable.getSelectionModel().getSelectedItem();
		if (AnswersTable.getSelectionModel().getSelectedItem()==null) {
			PopUpMsg.AlertForUser("Please Select row to save!");
		}
		else {
			
		message = new FullMessage(Request.SET_SURVEY_ANSWER, Response.Wait, selectedUser);
		ZliClientUI.ZliClientController.accept(message);
		AnswersTable.getItems().remove(selectedUser);

		}
	}
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<SurveyAnswers> SurveyAnswer = FXCollections.observableArrayList();

		if (ArrayForSurveyAnswers.size() != 0) {

			for (int i = 0; i < ArrayForSurveyAnswers.size(); i++) {
				SurveyAnswer.add(new SurveyAnswers(ArrayForSurveyAnswers.get(i)));
			}

			SurveyIDcol.setCellValueFactory(new PropertyValueFactory<SurveyAnswers, String>("SurveyID"));
			CustomerIDcol.setCellValueFactory(new PropertyValueFactory<SurveyAnswers, String>("CustomerID"));
			QuestionNumbercol.setCellValueFactory(new PropertyValueFactory<SurveyAnswers, String>("QuestionNumber"));
			Answercol.setCellValueFactory(new PropertyValueFactory<SurveyAnswers, String>("QuestionAnswer"));
			AnswersTable.setItems(SurveyAnswer);
		} else {

			errorLabel.setText("No Answers founded!!");
		}

	}

}
