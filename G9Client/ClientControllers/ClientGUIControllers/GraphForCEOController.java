package ClientGUIControllers;

import java.net.URL;
import java.util.ArrayList;
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
public class GraphForCEOController extends UsersController implements Initializable{
	/**
	 * Array list for branch manager
	 */
	public static ArrayList<String> BranchForManager = new ArrayList<>();
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	/**
	 * BarChart for total income
	 */
	@FXML
    private BarChart<?, ?> TotalIncome;
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
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		double[] TotalPriceForIncome=(double[]) ViewTwoReportsForOneBranchController.message.getObject();
		if(TotalPriceForIncome[0]==0&&TotalPriceForIncome[1]==0) {
			ErrorLabel.setText("No Income Report!");
		}
		XYChart.Series set1= new XYChart.Series<>();
		set1.setName("Month 1");
		set1.getData().add(new XYChart.Data("Quarterly 1",TotalPriceForIncome[0]));
		set1.getData().add(new XYChart.Data("Quarterly 2",TotalPriceForIncome[3]));
		
		XYChart.Series set2= new XYChart.Series<>();
		set2.setName("Month 2");
		set2.getData().add(new XYChart.Data("Quarterly 1",TotalPriceForIncome[1]));
		set2.getData().add(new XYChart.Data("Quarterly 2",TotalPriceForIncome[4]));
		
		XYChart.Series set3= new XYChart.Series<>();
		set3.setName("Month 3");
		set3.getData().add(new XYChart.Data("Quarterly 1",TotalPriceForIncome[2]));
		set3.getData().add(new XYChart.Data("Quarterly 2",TotalPriceForIncome[5]));
		
		TotalIncome.getData().addAll(set1,set2,set3);
	}
	
	
}
