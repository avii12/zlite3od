package queries;

import java.sql.ResultSet;
import java.sql.SQLException;

import AllUsers.ConfirmationStatus;
import Orders.Branch;
import ServerGUIControllers.ServerGuiController;

public class ImportQuery {
	private static ServerGuiController serverGuiController = ServerGuiController.getLoader().getController();
	public static void getFromExternalDB() {
		ResultSet rs=mainQuery.getExternalDBData();
		
			try {
				while(rs.next()) {
						mainQuery.insertOneRowIntoLoginTable(rs.getString(13), rs.getString(14), rs.getString(1), rs.getString(6));
						switch(rs.getString(6)) {
						case "customer":
							mainQuery.insertOneRowIntoCustomerTable(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), ConfirmationStatus.valueOf(rs.getString(8)), rs.getString(10), rs.getDouble(11));
							break;
						case"branchmanager":
							mainQuery.insertOneRowIntoBranchManagerAndDeliveryTable("branchmanager",rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), ConfirmationStatus.valueOf(rs.getString(8)), Branch.valueOf(rs.getString(9)));
							break;
						case"ceozli":
							mainQuery.insertOneRowIntoCeoZliAndServiceSpAndCustomerServiceTable("ceozli",rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), ConfirmationStatus.valueOf(rs.getString(8)));
							break;
						case"servicespecialist":
							mainQuery.insertOneRowIntoCeoZliAndServiceSpAndCustomerServiceTable("servicespecialist",rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), ConfirmationStatus.valueOf(rs.getString(8)));
							break;
						case"customerserviceworker":
							mainQuery.insertOneRowIntoCeoZliAndServiceSpAndCustomerServiceTable("customerserviceworker",rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), ConfirmationStatus.valueOf(rs.getString(8)));
							break;
						case"worker":
							mainQuery.insertOneRowIntoWorkerTable(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), ConfirmationStatus.valueOf(rs.getString(8)),rs.getString(12));
							break;
						case"deliveryperson":
							mainQuery.insertOneRowIntoBranchManagerAndDeliveryTable("deliveryperson",rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), ConfirmationStatus.valueOf(rs.getString(8)), Branch.valueOf(rs.getString(9)));
							break;
						default:
							break;
						}	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				serverGuiController.DisplayMessageToTextAreaConsole("There Are No Data Found");
			}

		
	}
	

}
