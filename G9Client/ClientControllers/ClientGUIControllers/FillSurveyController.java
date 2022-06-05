package ClientGUIControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import AllUsers.ConfirmationStatus;
import Report.customer;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import Survey.SurveyAnswers;
import Survey.survey;
import ZliClient.PopUpMessage;
import ZliClient.ZliClientUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class FillSurveyController extends UsersController implements Initializable {
	public static FullMessage message;
	public static ArrayList<survey> updateItems = new ArrayList<>();
	@FXML
	/**
	 * ComboBox of answers.
	 */
	private ComboBox<String> answer1;
	@FXML
	/**
	 * ComboBox of answers.
	 */
	private ComboBox<String> answer2;
	@FXML
	/**
	 * ComboBox of answers.
	 */
	private ComboBox<String> answer3;
	@FXML
	/**
	 * ComboBox of answers.
	 */
	private ComboBox<String> answer4;
	@FXML
	/**
	 * ComboBox of answers.
	 */
	private ComboBox<String> answer5;
	@FXML
	/**
	 * ComboBox of answers.
	 */
	private ComboBox<String> answer6;

	@FXML
	private Label Question1;

	@FXML
	private Label Question2;

	@FXML
	private Label Question3;

	@FXML
	private Label Question4;

	@FXML
	private Label Question5;

	@FXML
	private Label Question6;

	@FXML
	private Label errorLabel;
	public static ArrayList<SurveyAnswers> ArrayForSurveyAnswers = new ArrayList<>();
	public String[] answer = new String[7];
	// public static ArrayList<SurveyAnswers> ArrayForSurveyAnswers = new
	// ArrayList<>();
	public static ArrayList<survey> ArrayForSurvey = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<survey> Survey = FXCollections.observableArrayList();
		answer1.setValue("answer1*");
		answer2.setValue("answer2*");
		answer3.setValue("answer3*");
		answer4.setValue("answer4*");
		answer5.setValue("answer5*");
		answer6.setValue("answer6*");
		for (int i = 1; i < 11; i++) {
			answer1.getItems().add("" + i);
			answer2.getItems().add("" + i);
			answer3.getItems().add("" + i);
			answer4.getItems().add("" + i);
			answer5.getItems().add("" + i);
			answer6.getItems().add("" + i);
		}

		message = new FullMessage(Request.GET_SURVEY_FROM_DB, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);

		if (ArrayForSurvey == null) {
			errorLabel.setVisible(true);
			errorLabel.setText("There are no Questions in this survey!");
		} else {
			for (int i = 0; i < ArrayForSurvey.size(); i++) {
				Survey.add(new survey(ArrayForSurvey.get(i)));
			}

			Question1.setText(Survey.get(0).getQuestionForm());
			Question2.setText(Survey.get(1).getQuestionForm());
			Question3.setText(Survey.get(2).getQuestionForm());
			Question4.setText(Survey.get(3).getQuestionForm());
			Question5.setText(Survey.get(4).getQuestionForm());
			Question6.setText(Survey.get(5).getQuestionForm());

		}
	}

	@FXML
	public void BackBtn(MouseEvent event) throws IOException {

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

	}

	@FXML
	public void ExitButton(MouseEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

	@FXML
	void SendBtn(ActionEvent event) throws IOException {
		if (answer1.getValue().equals("answer1*") || answer2.getValue().equals("answer2*")
				|| answer3.getValue().equals("answer3*") || answer4.getValue().equals("answer4*")
				|| answer5.getValue().equals("answer5*") || answer6.getValue().equals("answer6*")) {
			errorLabel.setText("Fill all the answers!!");
		} else {
			String SurveyID = "1000";
			String CustomerID = CurrentUser.getID();

			////////////////////
			String QuestionNumber = "1";
			String Qanswer = answer1.getValue();
			SurveyAnswers answer = new SurveyAnswers(SurveyID, CustomerID, QuestionNumber, Qanswer);
			ArrayForSurveyAnswers.add(answer);
			///////////////////
			QuestionNumber = "2";
			Qanswer = answer2.getValue();
			answer = new SurveyAnswers(SurveyID, CustomerID, QuestionNumber, Qanswer);
			ArrayForSurveyAnswers.add(answer);
			///////////////////
			QuestionNumber = "3";
			Qanswer = answer3.getValue();
			answer = new SurveyAnswers(SurveyID, CustomerID, QuestionNumber, Qanswer);
			ArrayForSurveyAnswers.add(answer);
			///////////////////
			QuestionNumber = "4";
			Qanswer = answer4.getValue();
			answer = new SurveyAnswers(SurveyID, CustomerID, QuestionNumber, Qanswer);
			ArrayForSurveyAnswers.add(answer);
			///////////////////
			QuestionNumber = "5";
			Qanswer = answer5.getValue();
			answer = new SurveyAnswers(SurveyID, CustomerID, QuestionNumber, Qanswer);
			ArrayForSurveyAnswers.add(answer);
			///////////////////
			QuestionNumber = "6";
			Qanswer = answer6.getValue();
			answer = new SurveyAnswers(SurveyID, CustomerID, QuestionNumber, Qanswer);
			ArrayForSurveyAnswers.add(answer);

			InsertAnswersSurveyController.ArrayForSurveyAnswers = ArrayForSurveyAnswers;
			
			Optional<ButtonType> Option=PopUpMessage.ConfirmationForUser("Thank you, have a nice day.");
			if(Option.get()== ButtonType.OK) {
				answer1.setValue("answer1*");
				answer2.setValue("answer2*");
				answer3.setValue("answer3*");
				answer4.setValue("answer4*");
				answer5.setValue("answer5*");
				answer6.setValue("answer6*");
			}
		
		}

	}

}
