
package ClientGUIControllers;



import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.ZliClientUI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.time.LocalDate;
public class ServerIPController implements Initializable {

	@FXML
	private Label ErrorLabel;
	@FXML
	private TextField TextFieldLocalHost;
	@FXML
	private Button BtnConfirm;
	@FXML
	private Button BtnExit;

	/* When clicked exit SeachIP frame */
	@FXML
	public void ExitBtn(MouseEvent event) throws Exception {
		System.out.println("exit the establish Connection to server Window");
		System.exit(0);
	}

	
	/* When clicked establishes connection to the Server */
	public void ConnectBtn(MouseEvent event) throws Exception {
		
		if (TextFieldLocalHost.getText().isEmpty()) {
			TextFieldLocalHost.setStyle("-fx-border-color: red;-fx-background-radius: 15; -fx-border-radius: 15");
			DisplayMessageForUser("Enter Server IP, please!!");
		}

		else {
			Request request = Request.Connect;
			Response response = Response.Wait;
			FullMessage message = new FullMessage(request, response, null);
			ZliClientUI.StartController(TextFieldLocalHost.getText()); /* get the entered IP by the client */
			ZliClientUI.ZliClientController.accept(message); // send connect message to the server after button
		// replace main screen after connecting to server using IP
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ZRLI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			scene.setOnMousePressed(pressEvent -> {
				scene.setOnMouseDragged(dragEvent -> {
					primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
					primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
				});
			});
			primaryStage.setScene(scene);

			primaryStage.show();/* show the new screen *SearchForOrder.fxml */

			primaryStage.show();/* show the new screen *ZRLI.fxml* */
// branch 'master' of https://github.com/avii12/ZliProj.git
		}
	}

	// load the ServerIP form
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/IPAddress.fxml"));
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

	public void DisplayMessageForUser(String message) {
		Platform.runLater(() -> {
			ErrorLabel.setText(message);
		});

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TextFieldLocalHost.setText("localhost");
		
	}
}