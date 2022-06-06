package ClientGUIControllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.ZliClientUI;
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
 * controlling Graph for branch manager 
 *  
 *@author shorok heib
 *
 */
public class GraphController extends UsersController implements Initializable {
	/**
	 * Array list for branch manager
	 */
	public static ArrayList<String> BranchForManager = new ArrayList<>();
	/**
	 *static parameter message of full message
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
	 * Array String for quarterly
	 */
	public String[] quarterly = new String[3];
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String managerID=CurrentUser.getID();
		message = new FullMessage(Request.GET_MANAGER_ID, Response.Wait,managerID);
		ZliClientUI.ZliClientController.accept(message);
		quarterly[0]=ComplaintReportController.quarterly[0];///quarterly
		quarterly[1]=BranchForManager.get(0);//barnachname
		quarterly[2]=ComplaintReportController.quarterly[1];//year
		message = new FullMessage(Request.GET_NUM_OF_COMPLAINT, Response.Wait,quarterly);
		ZliClientUI.ZliClientController.accept(message);
		int[] NumOfComlaint=(int[]) message.getObject();
		if(NumOfComlaint[0]==0 && NumOfComlaint[1]==0&&NumOfComlaint[2]==0) {
			ErrorLabel.setText("No Complaint Report!");
		}
		else{
		XYChart.Series set1= new XYChart.Series<>();
		set1.getData().add(new XYChart.Data("month 1",NumOfComlaint[0]));
		set1.getData().add(new XYChart.Data("month 2",NumOfComlaint[1]));
		set1.getData().add(new XYChart.Data("month 3",NumOfComlaint[2]));
	    ComplaintReport.getData().addAll(set1);
	}
	}
	
	}

