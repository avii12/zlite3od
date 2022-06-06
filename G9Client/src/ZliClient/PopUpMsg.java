package ZliClient;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUpMsg {
	
	/**
	 * This method displays a confirmation box with the string (message)
	 * user has option to choose 'ok' or 'cancel' 
	 * @param message
	 * @return
	 */
	public static Optional<ButtonType> ConfirmationForUser(String message ) {
		
		Alert.AlertType type = Alert.AlertType.CONFIRMATION;
		Alert alert = new Alert (type,"");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.getDialogPane().setHeaderText(message);
		centerButtons(alert.getDialogPane());
		return alert.showAndWait();
	}
	
	/**
	 * this box appears with 2 buttons OK and Cancel
	 * the method returns Optional<ButtonType> , we have to check if its equals to OK to continue , other wise we do nothing.
	 * example after calling this method : if(confirmationMessage(message) == ButtonType.OK)
	 * @param dialogPane
	 */
	public static void centerButtons(DialogPane dialogPane) {
		
        Region spacer = new Region();
        ButtonBar.setButtonData(spacer, ButtonBar.ButtonData.BIG_GAP);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        dialogPane.applyCss();
        HBox hboxDialogPane = (HBox) dialogPane.lookup(".container");
        hboxDialogPane.getChildren().add(spacer);
     }
	
	/**
	 * This method simulation Message for user
	 * @param title
	 * @param Headermessage
	 * @param bodyMessage
	 */
	public static void simulationMessage(String title ,String Headermessage, String bodyMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(Headermessage);
		alert.setContentText(bodyMessage);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		DialogPane dialogPane = alert.getDialogPane();
		centerButtons(dialogPane);
		alert.showAndWait();
	}
	
	/**
	 * This is a message for the user to that
	 * informs him about something (such as finishing the order
	 * process).
	 * @param message
	 * @return
	 */
	public static Optional<ButtonType> AlertForUser(String message ){
		
		Alert.AlertType type = Alert.AlertType.INFORMATION;
		Alert alert = new Alert (type,"");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.getDialogPane().setHeaderText(message);
		centerButtons(alert.getDialogPane());
		return alert.showAndWait();
	}

}
