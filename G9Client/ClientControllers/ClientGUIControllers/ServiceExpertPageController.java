package ClientGUIControllers;

import java.io.File;
import java.io.IOException;
import javafx.scene.layout.Pane;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *Class description:  
 *Controlling The UI Of Service Expert 
 *
 *
 *@author obied haddad
 *
 */
public class ServiceExpertPageController extends UsersController {
	public static FullMessage message;
	@FXML
	private Pane anchorid;
	@FXML
	private TextField TextFieldPath;
	@FXML
	private Button UploadButton;
	private String path;
	private File file;
	public static String extractedPath;

	public void LogoutButton(ActionEvent event) throws IOException {

		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		switch (message.getResponse()) {

		case Succeed:
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			scene.setOnMousePressed(pressEvent -> {
				scene.setOnMouseDragged(dragEvent -> {
					primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
					primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
				});
			});
			primaryStage.setScene(scene);
			primaryStage.show(); /* Show login page */
			break;
		default:
			break;
		}
	}

	public void ExitButton(MouseEvent event) throws IOException {

		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

	@FXML
	public void BrowseReport(ActionEvent event) {

		String parsedMsg[] = null;
		final FileChooser chooser = new FileChooser();
		Stage stage = (Stage) anchorid.getScene().getWindow();
		file = chooser.showOpenDialog(stage);

		if (file != null) {
			path = file.getAbsolutePath();
			System.out.println(path);
			parsedMsg = file.getAbsolutePath().split("C:\\\\Reports\\\\");
			String st1 = "\\\\" + parsedMsg[1];
			path = "C:\\\\Reports" + st1;
			TextFieldPath.setText(path);
			UploadButton.setDisable(false);

		}
	}

	@FXML
	public void UploadReport(ActionEvent event) {

		message = new FullMessage(Request.UPLOAD_PDF_TO_SYSTEM, Response.Wait, path);
		ZliClientUI.ZliClientController.accept(message);
		PopUpMsg.AlertForUser("**File has been uploaded to Database**");
		TextFieldPath.clear();
		file = null;
		UploadButton.setDisable(true);

	}

	@FXML
	public void ExtractPDF(ActionEvent event) {

		message = new FullMessage(Request.EXTRACT_PDF_FROM_DB, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);

		switch (message.getResponse()) {

		case PDF_FOUND:
			PopUpMsg.AlertForUser("PDF file was extracted to -->" + extractedPath);
			break;
		case PDF_NOT_FOUND:
			PopUpMsg.AlertForUser("PDF was not found in Database!!");
			break;

		}
	}
}
