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

public class GraphforCEO2Controller extends UsersController implements Initializable{
	public static ArrayList<String> BranchForManager = new ArrayList<>();
	public static FullMessage message;
	@FXML
    private BarChart<?, ?> TotalIncome;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    @FXML
    private Label ErrorLabel;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		double[] TotalPriceForIncome=(double[]) ViewTwoReportsForTwoBranchesController.message.getObject();
		if(TotalPriceForIncome[0]==0&&TotalPriceForIncome[1]==0) {
			ErrorLabel.setText("No Income Report!");
		}
		
		XYChart.Series set1= new XYChart.Series<>();
		set1.setName("Month 1");
		set1.getData().add(new XYChart.Data("Branch 1",TotalPriceForIncome[0]));
		set1.getData().add(new XYChart.Data("Branch 2",TotalPriceForIncome[3]));
		
		XYChart.Series set2= new XYChart.Series<>();
		set2.setName("Month 2");
		set2.getData().add(new XYChart.Data("Branch 1",TotalPriceForIncome[1]));
		set2.getData().add(new XYChart.Data("Branch 2",TotalPriceForIncome[4]));
		
		XYChart.Series set3= new XYChart.Series<>();
		set3.setName("Month 3");
		set3.getData().add(new XYChart.Data("Branch 1",TotalPriceForIncome[2]));
		set3.getData().add(new XYChart.Data("Branch 2",TotalPriceForIncome[5]));
		
		TotalIncome.getData().addAll(set1,set2,set3);
	}

}
