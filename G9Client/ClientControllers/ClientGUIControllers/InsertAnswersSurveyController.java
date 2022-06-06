package ClientGUIControllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Orders.ItemsForTableView;
import Report.customer;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import Survey.SurveyAnswers;
import ZliClient.PopUpMessage;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InsertAnswersSurveyController extends UsersController implements Initializable {

	public static FullMessage message;

	@FXML
	private Label errorLabel;

	@FXML
	private TableView<SurveyAnswers> AnswersTable;

	@FXML
	private TableColumn<SurveyAnswers, String> SurveyIDcol;

	@FXML
	private TableColumn<SurveyAnswers, String> CustomerIDcol;

	@FXML
	private TableColumn<SurveyAnswers, String> QuestionNymbercol;

	@FXML
	private TableColumn<SurveyAnswers, String> Answercol;

	public static ArrayList<SurveyAnswers> ArrayForSurveyAnswers = new ArrayList<>();

	@FXML
	public void ExitButton(MouseEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<SurveyAnswers> SurveyAnswer = FXCollections.observableArrayList();

		if (ArrayForSurveyAnswers.size() != 0) {

			for (int i = 0; i < ArrayForSurveyAnswers.size(); i++) {
				SurveyAnswer.add(new SurveyAnswers(ArrayForSurveyAnswers.get(i)));
			}

			SurveyIDcol.setCellValueFactory(new PropertyValueFactory<SurveyAnswers, String>("SurveyID"));
			CustomerIDcol.setCellValueFactory(new PropertyValueFactory<SurveyAnswers, String>("CustomerID"));
			QuestionNymbercol.setCellValueFactory(new PropertyValueFactory<SurveyAnswers, String>("QuestionNumber"));
			Answercol.setCellValueFactory(new PropertyValueFactory<SurveyAnswers, String>("QuestionAnswer"));
			AnswersTable.setItems(SurveyAnswer);
		} else {

			errorLabel.setText("No Answers founded!!");
		}

	}

}
