package ClientGUIControllers;

import java.net.URL;
import java.util.ResourceBundle;
import RequestsAndResponses.FullMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
/**
 * Class description: 
 * This is a class for 
 * controlling Graph for CEO
 *  
 *@author Seren Hanany
 *
 */
public class GraphForCEO1Controller extends UsersController implements Initializable {
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	/**
	 * BarChart for complaint report
	 */
	@FXML
    private BarChart<?, ?> ComplaintReport;
	/**
	 * Category Axis for x
	 */
    @FXML
    private CategoryAxis x;
    /**
	 * Category Axis for y
	 */
    @FXML
    private NumberAxis y;
    /**
	 * Error label
	 */
    @FXML
    private Label ErrorLabel;
    /**
	 * Array string for quarterly
	 */
	public String[] quarterly = new String[3];
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		message=ViewReportsForCEOController.message;
		int[] NumOfComlaint=(int[]) message.getObject();
		
		if(NumOfComlaint[0]==0&&NumOfComlaint[1]==0&&NumOfComlaint[2]==0) {
			ErrorLabel.setText("No Complaint Report!");
		}
		XYChart.Series set1= new XYChart.Series<>();
		set1.getData().add(new XYChart.Data("month 1",NumOfComlaint[0]));
		set1.getData().add(new XYChart.Data("month 2",NumOfComlaint[1]));
		set1.getData().add(new XYChart.Data("month 3",NumOfComlaint[2]));
	    ComplaintReport.getData().addAll(set1);
		
	}

}
