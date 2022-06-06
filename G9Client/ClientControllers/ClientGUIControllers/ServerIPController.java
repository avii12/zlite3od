
package ClientGUIControllers;
import java.net.URL;
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
/**
 * Class description: 
 * This is a class for 
 * controlling the UI of Server IP 
 *  
 *@author Seren Hanany
 *
 */
public class ServerIPController implements Initializable {
/**
 * Error label
 */
	@FXML
	private Label ErrorLabel;
	/**
	 * Text Field for port
	 */
	@FXML
	private TextField TextFieldLocalHost;
	/**
	 * Confirm Button
	 */
	@FXML
	private Button BtnConfirm;
	/**
	 * Exit Button
	 */
	@FXML
	private Button BtnExit;

	/**
	 *  When clicked exit SeachIP frame */
	@FXML
	public void ExitBtn(MouseEvent event) throws Exception {
		System.out.println("exit the establish Connection to server Window");
		System.exit(0);
	}

	
	/**
	 *  When clicked establishes connection to the Server */
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
		}
	}

	/**
	 *  load the ServerIP form
	 * @param primaryStage
	 * @throws Exception
	 */
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
	/**
	 * The Function Display's The Message On The Label
	 * @param message
	 */
	public void DisplayMessageForUser(String message) {
		Platform.runLater(() -> {
			ErrorLabel.setText(message);
		});

	}
	/**
	 * This method sets the correct values.
	 * 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TextFieldLocalHost.setText("localhost");
		
	}
}