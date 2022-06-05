package ReadMessage;

import java.sql.SQLException;
import java.text.ParseException;

import Orders.Item;
import Orders.Order;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ServerGUIControllers.ServerGuiController;
import ocsf.server.ConnectionToClient;
import queries.ComplaintsQuery;
import queries.ItemsAndProductsQuery;
import queries.LogInQuery;
import queries.OrderQuery;
import queries.ReportQuery;
import queries.SurveyQuery;
import queries.UserQuery;

public class ReadMessageFromClient {

	public static ConnectionToClient CurrentClient;

	public static ServerGuiController HandleClientStatus = ServerGuiController.getLoader().getController();

	public static FullMessage ReadMessage(Object message, ConnectionToClient client) throws SQLException {
		CurrentClient = client;

		if (!(message instanceof FullMessage)) {

			return null;
		}

		else {

			FullMessage messageFromClient = (FullMessage) message;
			Request requestFromClient = messageFromClient.getRequest();
			switch (requestFromClient) {
			case Connect:
				boolean AddClient = HandleClientStatus
						.CheckIfClientIsInListAndSetStatusToConnect(CurrentClient.getInetAddress().getHostAddress());

				if (AddClient) {

					HandleClientStatus.AddNewClient(CurrentClient.getInetAddress().getHostAddress(),
							CurrentClient.getInetAddress().getCanonicalHostName(), "Connected");

					messageFromClient.setResponse(Response.Succeed);
				}
			default:
				break;

			case Disconnect:

				HandleClientStatus.SetClientStatusToDisconnect(CurrentClient.getInetAddress().getHostAddress(),
						"Disconnected");

				messageFromClient.setResponse(Response.Succeed);
				break;

			case LOGIN:

				try {

					messageFromClient = LogInQuery.serverLogIn(messageFromClient);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case LOGOUT:

				LogInQuery.LogOut(messageFromClient);
				messageFromClient.setResponse(Response.Succeed);
				break;

			case GET_ITEMS_FROM_DB:
				messageFromClient = ItemsAndProductsQuery.CreateCatalogForUser(messageFromClient);
				break;

			case GET_ORDER_FROM_DB:
				messageFromClient = OrderQuery.AcceptOrder(messageFromClient);
				break;

			case GET_ORDER_FROM_DB_FOR_CANCELORDER:
				messageFromClient = OrderQuery.CancelOrder(messageFromClient);
				break;

			case GET_ORDER_FROM_DB_FOR_MANAGER_CANCEL_ORDER:
				messageFromClient = OrderQuery.ManagerCancelOrder(messageFromClient);
				break;

			case GET_ORDER_FROM_DB_FOR_CUSTOMER:
				messageFromClient = OrderQuery.GetOrderForCustomer(messageFromClient);
				break;

			case MANAGE_ORDER_FINISHED:
				messageFromClient = OrderQuery.updateOrdersStatusOnDb(messageFromClient);
				break;

			case UPDATE_BALANCE_ORDER_UNAPPROVED:
				messageFromClient = UserQuery.restoreTheOldBalanceBeforePurchasing(messageFromClient);
				break;

			case GET_THE_SUBRACTED_DATE_TIME:
				messageFromClient = OrderQuery.getTheDiffrenceTimeBetweenDates(messageFromClient);
				break;

			case CANCEL_ORDER_FINISHED:
				messageFromClient = OrderQuery.updateOrdersStatusOnDb(messageFromClient);
				break;
			case COMPLETED_ORDER_FINISHED:
				messageFromClient = OrderQuery.updateOrdersStatusOnDb(messageFromClient);
				break;
				
			case GET_SURVEY_FROM_DB:
				messageFromClient=SurveyQuery.GetSurveyFromDB(messageFromClient);
				break;
				
			case SET_SURVEY_ANSWER:
				messageFromClient=SurveyQuery.SetAnswersToDB(messageFromClient);
				break;

			case GET_CUSTOMER_DETAILS:
				messageFromClient = UserQuery.GetUserBalanceAndCreditCard(messageFromClient);
				break;

			case GET_LAST_ORDER_ID:

				messageFromClient = OrderQuery.CheckIfFirstOrder(messageFromClient);
				break;
				
			case GET_LAST_COMPLAINT_NUMBER:
				messageFromClient = OrderQuery.CheckIfFirstComplaint(messageFromClient);
				break;
			
			case INSERT_ORDER_TO_DB:

				try {

					messageFromClient = OrderQuery.addOrderToDb(messageFromClient);
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case UPDATE_CUSTOMER_BALANCE:
				messageFromClient = UserQuery.UpdateCustomerBalance(messageFromClient);
				break;

			case CHECK_AMOUNT:

				messageFromClient = OrderQuery.GetAmountAndUpdate(messageFromClient);
				break;

			case GET_ITEMS_ACCORDING_TO_COLOR:
				messageFromClient = ItemsAndProductsQuery.CreateCatalogAccordingToColor(messageFromClient);
				break;

			case GET_ITEMS_ACCORDING_TO_TYPE:
				messageFromClient = ItemsAndProductsQuery.CreateCatalogAccordingToType(messageFromClient);
				break;
			case GET_ITEMS_ACCORDING_TO_PRICE:
				messageFromClient = ItemsAndProductsQuery.CreateCatalogAccordingToPrice(messageFromClient);
				break;

			case RESTORE_AMOUNT_FOR_ITEM:
				messageFromClient = ItemsAndProductsQuery.RestoreAmountForItem(messageFromClient);
				break;
			case GET_ALL_ITEMS_FOR_WORKER:
				messageFromClient = ItemsAndProductsQuery.CreateCatalogForUser(messageFromClient);
				break;

			case REMOVE_ITEM_FROM_CATALOG:
				ItemsAndProductsQuery.RemoveItemFromCatalog(messageFromClient);
				break;

			case UPDATE_ITEM_IN_CATALOG:
				ItemsAndProductsQuery.UpdateItemInCatalog(messageFromClient);
				break;
			case ADD_ITEM_TO_DB:
				try {
					ItemsAndProductsQuery.addItemToDB(messageFromClient);
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case UPDATE_WORKER_SALE:
				ItemsAndProductsQuery.UpdateSaleForWorker(messageFromClient);
				break;
			case UPDATE_WORKER_SALE_FOR_SPECIFIC_BRANCH:
				ItemsAndProductsQuery.UpdateSaleForWorkerForSpecificBranch(messageFromClient);
				break;

			case UPDATE_WORKER_AFTER_END_SALE:
				ItemsAndProductsQuery.UpdateSaleForWorkerToEndSale(messageFromClient);
				break;

			case UPDATE_PRICES_AFTER_SALES:

				ItemsAndProductsQuery.ChangeItemsPrices(messageFromClient);
				break;

			case CHECK_IF_CATALOG_EXIST_TO_START_SALES:
				messageFromClient = ItemsAndProductsQuery.CreateCatalogForUser(messageFromClient);
				break;

			case GET_THE_SALE_PERCENTAGE_FROM_WORKER:
				messageFromClient = ItemsAndProductsQuery.GetPercentageOfSales(messageFromClient);
				break;
			case UPDATE_PRICES_AFTER_END_SALES:
				ItemsAndProductsQuery.ChangeItemsPricesToBeforeSales(messageFromClient);
				break;
			case CHECK_IF_SALES_ARE_ON:
				messageFromClient = ItemsAndProductsQuery.CheckIfSalesAreOn(messageFromClient);
				break;

			case CHECK_IF_SALES_ARE_ON_FOR_CATALOG:
				messageFromClient = ItemsAndProductsQuery.CheckIfSalesAreOnForCatalog(messageFromClient);
				break;

			case CHECK_REPORT_FROM_DB:
				messageFromClient = ReportQuery.GetReportFromDB(messageFromClient);
				break;
			case CHECK_REPORT_FROM_DB_FOR_CEO:
				messageFromClient = ReportQuery.GetReportFromDB(messageFromClient);
				break;

			case GET_MANAGER_ID:
				messageFromClient = ReportQuery.GetManagerID(messageFromClient);
				break;
			case GET_REPORT_FROM_DB:
				messageFromClient = ReportQuery.GetIncomeReportForManager(messageFromClient);
				break;
			case GET_REPORT_FROM_DB_FOR_CEO:
				messageFromClient = ReportQuery.GetIncomeReportForManager(messageFromClient);
				break;
			case GET_ORDER_REPORT_FROM_DB:
				messageFromClient = ReportQuery.GetOrderReport(messageFromClient);
				break;
			case GET_NUM_OF_COMPLAINT:
				messageFromClient = ReportQuery.GetNumOfComplaintByQuarterly(messageFromClient);
				break;

			case GET_REPORT_FOR_TWO_QUARTERLY:
				messageFromClient = ReportQuery.GetReportForTwoQuarterly(messageFromClient);
				break;
			case GET_REPORT_FOR_TWO_BRANCHES:
				messageFromClient = ReportQuery.GetReportForTwoBranches(messageFromClient);
				break;
			case GET_ORDER_FROM_DB_FOR_DELIVERY:
				messageFromClient = OrderQuery.DeliveryOrder(messageFromClient);
				break;
			case GET_CUSTOMER_FROM_DB:
				messageFromClient = UserQuery.GetCustomerFromDB(messageFromClient);
				break;
			case UPDATE_STATUS_CUSTOMER:
				messageFromClient = UserQuery.UpdateCustomerStatus(messageFromClient);
				break;
			case GET_USER_STATUS:
				messageFromClient = UserQuery.GetUserStatus(messageFromClient);
				break;

			case GET_COMPLAINT_FROM_DB:
				messageFromClient = ComplaintsQuery.showComplaintsForUser(messageFromClient);
				break;
			case GET_NUM_OF_COMPLAINT_FOR_CEO:
				messageFromClient = ReportQuery.GetNumOfComplaintByQuarterly(messageFromClient);
				break;
			case GET_ORDER_REPORT_FROM_DB_FOR_CEO:
				messageFromClient = ReportQuery.GetOrderReport(messageFromClient);
				break;
			case UPDATE_BALANCE_AFTER_COMPLAINT:
				messageFromClient = UserQuery.restoreOldBalanceAfterComplaint(messageFromClient);
				break;
				
			case GET_USERS_FROM_DB:
				messageFromClient=UserQuery.GetUsersFromDB(messageFromClient);
				break;	

			case GET_WORKER_BRANCH:
				messageFromClient = UserQuery.GetBranchFromWorker(messageFromClient);
				break;
				
			case UPDATE_TYPE_USER:
				messageFromClient=UserQuery.ChangeUsersFromDB(messageFromClient);
				break;	

			case ADD_COMPLAINT_FROM_USER_TO_DB:
				try {
					ComplaintsQuery.AddComplaintToDB(messageFromClient);
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case UPDATE_REFUND_STATUS:
				OrderQuery.UpdateRefundStatus(messageFromClient);
				break;
				
			case GET_USERS_FROM_DB_FOR_WORKER:
				messageFromClient=UserQuery.GetUsersFromDB(messageFromClient);
				break;	

			}

			return messageFromClient;

		}

	}

}