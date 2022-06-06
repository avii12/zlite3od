package ReadMessage;

import java.io.IOException;
import java.util.ArrayList;
import AllUsers.BranchManager;
import AllUsers.CEOZli;
import AllUsers.Customer;
import AllUsers.CustomerServiceWorker;
import AllUsers.DeliveryPerson;
import AllUsers.ServiceExpert;
import AllUsers.Users;
import AllUsers.Worker;
import ClientGUIControllers.AcceptCancelOrderController;
import ClientGUIControllers.AcceptOrderController;
import ClientGUIControllers.BranchManagerPageController;
import ClientGUIControllers.CEOZliPageController;
import ClientGUIControllers.CancelOrderController;
import ClientGUIControllers.CatalogController;
import ClientGUIControllers.CatalogManagement;
import ClientGUIControllers.ChangeCustomerStatusController;
import ClientGUIControllers.ChangeUserPermissionController;
import ClientGUIControllers.ComplaintHandelingController;
import ClientGUIControllers.ComplaintRefundController;
import ClientGUIControllers.CustomerPageController;
import ClientGUIControllers.CustomerServiceWorkerPageController;
import ClientGUIControllers.DeliveryPersonPageController;
import ClientGUIControllers.FillSurveyController;
import ClientGUIControllers.GraphController;
import ClientGUIControllers.InsertAnswersSurveyController;
import ClientGUIControllers.LoginController;
import ClientGUIControllers.PaymentController;
import ClientGUIControllers.ServiceExpertPageController;
import ClientGUIControllers.StartSalesController;
import ClientGUIControllers.UsersController;
import ClientGUIControllers.ViewReportForBranchManager;
import ClientGUIControllers.ViewReportsForCEOController;
import ClientGUIControllers.ViewTwoReportsForOneBranchController;
import ClientGUIControllers.ViewTwoReportsForTwoBranchesController;
import ClientGUIControllers.WorkerInsertComplaint;
import ClientGUIControllers.WorkerInsertComplaintChooseOrder;
import ClientGUIControllers.WorkerPageController;
import Orders.Item;

import Orders.Order;

import Report.OrderReport;
import Report.Reports;
import Report.customer;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import Survey.survey;
import customerService.Complaint;

public class ReadMessageFromServer {

	@SuppressWarnings({ "incomplete-switch", "unchecked" })
	public static void readMessageFromServer(Object message) throws IOException {

		if (!(message instanceof FullMessage)) {
			return;
		} else {

			FullMessage MessageFromServer = (FullMessage) message;
			Request RequestFromClient = MessageFromServer.getRequest();
			Response ResponseFromServer = MessageFromServer.getResponse();

			Object ReturnedObjectFromDB = MessageFromServer.getObject();

			switch (RequestFromClient) {

			case OPEN_PORTAL:
				LoginController.message.setRequest(Request.OPEN_PORTAL);
				UsersController.CurrentUser = (AllUsers.User) MessageFromServer.getObject();

				switch (ResponseFromServer) {

				case Create_CUSTOMER_PORTAL:
					LoginController.message.setResponse(Response.Create_CUSTOMER_PORTAL);
					break;
				case Create_BRANCHMANAGER_PORTAL:
					LoginController.message.setResponse(Response.Create_BRANCHMANAGER_PORTAL);
					break;
				case Create_CEOZLI_PORTAL:
					LoginController.message.setResponse(Response.Create_CEOZLI_PORTAL);
					break;
				case Create_SERVICESPECIALIST_PORTAL:
					LoginController.message.setResponse(Response.Create_SERVICESPECIALIST_PORTAL);
					break;
				case Create_CUSTOMERSERVICEWORKER_PORTAL:
					LoginController.message.setResponse(Response.Create_CUSTOMERSERVICEWORKER_PORTAL);
					break;
				case Create_WORKER_PORTAL:
					LoginController.message.setResponse(Response.Create_WORKER_PORTAL);
					break;
				case Create_DELIVERYPERSON_PORTAL:
					LoginController.message.setResponse(Response.Create_DELIVERYPERSON_PORTAL);
					break;
				}

				break;

			case POP_UP_ERROR:
				LoginController.message.setRequest(Request.POP_UP_ERROR);

				switch (ResponseFromServer) {

				case NO_SUCH_USER:
					LoginController.message.setResponse(Response.NO_SUCH_USER);
					break;
				case ALREADY_LOGGED_IN:
					LoginController.message.setResponse(Response.ALREADY_LOGGED_IN);
					break;
				}
				break;

			case LOGOUT:
				if (MessageFromServer.getObject() instanceof Customer)
					CustomerPageController.message.setResponse(Response.Succeed);
				else if (MessageFromServer.getObject() instanceof CEOZli)
					CEOZliPageController.message.setResponse(Response.Succeed);
				else if (MessageFromServer.getObject() instanceof BranchManager)
					BranchManagerPageController.message.setResponse(Response.Succeed);
				else if (MessageFromServer.getObject() instanceof Worker)
					WorkerPageController.message.setResponse(Response.Succeed);
				else if (MessageFromServer.getObject() instanceof ServiceExpert)
					ServiceExpertPageController.message.setResponse(Response.Succeed);
				else if (MessageFromServer.getObject() instanceof CustomerServiceWorker)
					CustomerServiceWorkerPageController.message.setResponse(Response.Succeed);
				else if (MessageFromServer.getObject() instanceof DeliveryPerson)
					DeliveryPersonPageController.message.setResponse(Response.Succeed);

				break;

			case GET_ITEMS_FROM_DB:

				switch (ResponseFromServer) {

				case NO_CATALOG:
					break;

				case CATALOG_FOUND:

					CatalogController.ItemListFromDB = (ArrayList<Item>) MessageFromServer.getObject();
					break;

				}

			case GET_ORDER_FROM_DB:

				switch (ResponseFromServer) {

				case NO_ORDER_FOUND:
					AcceptOrderController.message.setResponse(Response.NO_ORDER_FOUND);
					break;

				case ORDER_FOUND:
					AcceptOrderController.message.setResponse(Response.ORDER_FOUND);

					AcceptOrderController.OrderFromDB = (ArrayList<Order>) MessageFromServer.getObject();
					break;
				}

			case GET_ORDER_FROM_DB_FOR_DELIVERY:

				switch (ResponseFromServer) {

				case NO_ORDER_FOUND:
					DeliveryPersonPageController.message.setResponse(Response.NO_ORDER_FOUND);
					break;
				case ORDER_FOUND:
					DeliveryPersonPageController.message.setResponse(Response.ORDER_FOUND);
					DeliveryPersonPageController.OrderFromDB = (ArrayList<Order>) MessageFromServer.getObject();
					break;
				}

			case GET_CUSTOMER_DETAILS:
				PaymentController.message.setResponse(Response.CUSTOMER_FOUND);
				PaymentController.message.setObject(MessageFromServer.getObject());
				break;

			case GET_LAST_ORDER_ID:

				switch (ResponseFromServer) {

				case NO_ORDER_FOUND:
					PaymentController.message.setResponse(Response.NO_ORDER_FOUND);
					PaymentController.message.setObject("1");
					break;

				case NOT_FIRST_ORDER:
					PaymentController.message.setResponse(Response.NOT_FIRST_ORDER);
					PaymentController.message.setObject(MessageFromServer.getObject());
					break;
				}

			case GET_LAST_COMPLAINT_NUMBER:

				switch (ResponseFromServer) {

				case NO_COMPLAINT_FOUND:
					WorkerInsertComplaint.message.setResponse(Response.NO_COMPLAINT_FOUND);
					WorkerInsertComplaint.message.setObject("1");
					break;

				case NOT_FIRST_COMPLAINT:
					WorkerInsertComplaint.message.setResponse(Response.NOT_FIRST_COMPLAINT);
					WorkerInsertComplaint.message.setObject(MessageFromServer.getObject());
					break;
				}

			case GET_SURVEY_FROM_DB:
				FillSurveyController.message.setResponse(MessageFromServer.getResponse());
				FillSurveyController.ArrayForSurvey = (ArrayList<survey>) MessageFromServer.getObject();
				break;
			case SET_SURVEY_ANSWER:
				InsertAnswersSurveyController.message.setResponse(MessageFromServer.getResponse());
				break;

			case CHECK_AMOUNT:

				switch (ResponseFromServer) {

				case AMOUNT_UPDATED:

					CatalogController.message.setResponse(Response.AMOUNT_UPDATED);
					break;
				case PRODUCT_NOT_IN_INVENTORY:

					CatalogController.message.setResponse(Response.PRODUCT_NOT_IN_INVENTORY);
					break;

				}
				break;

			case INSER_ORDER_TO_DB:
				PaymentController.message.setRequest(RequestFromClient);
				break;

			case UPDATE_CUSTOMER_BALANCE:
				break;

			case GET_ITEMS_ACCORDING_TO_COLOR:

				switch (ResponseFromServer) {

				case NO_CATALOG:
					break;

				case CATALOG_FOUND:

					CatalogController.ItemListFromDB = (ArrayList<Item>) MessageFromServer.getObject();
					AcceptOrderController.OrderFromDB = (ArrayList<Order>) MessageFromServer.getObject();
					break;

				}

			case GET_ITEMS_ACCORDING_TO_TYPE:

				switch (ResponseFromServer) {

				case NO_CATALOG:
					break;

				case CATALOG_FOUND:

					CatalogController.ItemListFromDB = (ArrayList<Item>) MessageFromServer.getObject();
					break;
				}
				break;

			case GET_ITEMS_ACCORDING_TO_PRICE:

				switch (ResponseFromServer) {

				case NO_CATALOG:
					break;

				case CATALOG_FOUND:

					CatalogController.ItemListFromDB = (ArrayList<Item>) MessageFromServer.getObject();
					break;

				case AMOUNT_RESTORED:
					break;
				}

			case GET_ALL_ITEMS_FOR_WORKER:

				switch (ResponseFromServer) {

				case NO_CATALOG:
					CatalogManagement.message.setResponse(Response.NO_CATALOG);
					break;

				case CATALOG_FOUND:
					CatalogManagement.ItemListFromDB = (ArrayList<Item>) MessageFromServer.getObject();
					CatalogManagement.message.setResponse(Response.CATALOG_FOUND);
					break;

				}
				break;

			case GET_ORDER_FROM_DB_FOR_CANCELORDER:
				switch (ResponseFromServer) {
				case NO_ORDER_FOUND:
					CancelOrderController.message.setResponse(Response.NO_ORDER_FOUND);
					break;

				case ORDER_FOUND:
					CancelOrderController.message.setResponse(Response.ORDER_FOUND);
					CancelOrderController.OrderFromDB = (ArrayList<Order>) MessageFromServer.getObject();
					break;
				}
				break;

			case MANAGE_ORDER_FINISHED:
				switch (ResponseFromServer) {

				case MANAGE_ORDER_APPROVED_SUCCEEDED_WRITING_INTO_DB:
					AcceptOrderController.message.setResponse(Response.MANAGE_ORDER_APPROVED_SUCCEEDED_WRITING_INTO_DB);
					break;
				case MANAGE_ORDER_COMPLETED_SUCCEEDED_WRITING_INTO_DB:
					CancelOrderController.message
							.setResponse(Response.MANAGE_ORDER_COMPLETED_SUCCEEDED_WRITING_INTO_DB);
					break;
				case MANAGE_ORDER_UN_APPROVED_SUCCEEDED_WRITING_INTO_DB:
					AcceptOrderController.message
							.setResponse(Response.MANAGE_ORDER_UN_APPROVED_SUCCEEDED_WRITING_INTO_DB);
					break;
				case MANAGE_ORDER_APPROVED_CANCEL_SUCCEEDED_WRITING_INTO_DB:
					AcceptCancelOrderController.message
							.setResponse(Response.MANAGE_ORDER_APPROVED_CANCEL_SUCCEEDED_WRITING_INTO_DB);
					break;
				case MANAGE_ORDER_CANCEL_SUCCEEDED_WRITING_INTO_DB:
					CancelOrderController.message.setResponse(Response.MANAGE_ORDER_CANCEL_SUCCEEDED_WRITING_INTO_DB);
					break;

				default:
					break;
				}

			case UPDATE_BALANCE_ORDER_UNAPPROVED:
				switch (ResponseFromServer) {
				case UPDATE_UNAPPROVED_ORDER_BALANCE_SUCCEEDED:
					AcceptOrderController.message.setResponse(Response.MANAGE_ORDER_APPROVED_SUCCEEDED_WRITING_INTO_DB);
					break;
				}
				break;

			case THE_SUBRACTED_DATE_TIME_DONE:
				switch (ResponseFromServer) {
				case GET_THE_SUBRACTED_DATE_TIME_SUCCEDED:
					AcceptCancelOrderController.message.setResponse(Response.TIME_FOUND);
					AcceptCancelOrderController.message.setObject(ReturnedObjectFromDB);
					break;
				case GET_THE_SUBRACTED_DATE_TIME_SUCCEDED_FOR_DELIVERY:
					DeliveryPersonPageController.message.setResponse(Response.TIME_FOUND);
					break;
				}
				break;

			case GET_ORDER_FROM_DB_FOR_MANAGER_CANCEL_ORDER:
				switch (ResponseFromServer) {

				case NO_ORDER_FOUND:
					AcceptCancelOrderController.message.setResponse(Response.NO_ORDER_FOUND);
					break;

				case ORDER_FOUND_FOR_MANAGER:
					AcceptCancelOrderController.message.setResponse(Response.ORDER_FOUND_FOR_MANAGER);
					AcceptCancelOrderController.OrderFromDB = (ArrayList<Order>) MessageFromServer.getObject();
					break;

				}
				break;

			case GET_ORDER_FROM_DB_FOR_CUSTOMER:
				switch (ResponseFromServer) {

				case NO_ORDER_FOUND:
					WorkerInsertComplaintChooseOrder.message.setResponse(Response.NO_ORDER_FOUND);
					break;

				case ORDER_FOUND_FOR_MANAGER:
					WorkerInsertComplaintChooseOrder.message.setResponse(Response.ORDER_FOUND_FOR_CUSTOMER);
					WorkerInsertComplaintChooseOrder.OrderFromDB = (ArrayList<Order>) MessageFromServer.getObject();
					break;

				}
				break;

			case CHECK_IF_CATALOG_EXIST_TO_START_SALES:
				switch (ResponseFromServer) {

				case CATALOG_FOUND:
					StartSalesController.message.setResponse(Response.CATALOG_FOUND);
					break;

				case NO_CATALOG:
					StartSalesController.message.setResponse(Response.NO_CATALOG);
					break;

				}
				break;

			case GET_THE_SALE_PERCENTAGE_FROM_WORKER:

				StartSalesController.message.setObject(ReturnedObjectFromDB);
				break;

			case CHECK_IF_SALES_ARE_ON:
				StartSalesController.message.setObject(ReturnedObjectFromDB);
				break;
			case GET_WORKER_BRANCH:
				StartSalesController.message.setObject(ReturnedObjectFromDB);
				break;

			case CHECK_IF_SALES_ARE_ON_FOR_CATALOG:
				CatalogController.message.setObject(ReturnedObjectFromDB);
				break;

			case GET_REPORT_FROM_DB:

				ViewReportForBranchManager.message.setResponse(MessageFromServer.getResponse());
				ViewReportForBranchManager.IncomeReport = (ArrayList<String>) MessageFromServer.getObject();
				break;
			case GET_REPORT_FROM_DB_FOR_CEO:
				ViewReportsForCEOController.message.setResponse(MessageFromServer.getResponse());
				ViewReportsForCEOController.IncomeReport = (ArrayList<String>) MessageFromServer.getObject();
				break;

			case CHECK_REPORT_FROM_DB:
				switch (ResponseFromServer) {

				case NO_REPORT:
					ViewReportForBranchManager.message.setResponse(MessageFromServer.getResponse());
					break;

				case REPORT_FOUND:
					ViewReportForBranchManager.message.setResponse(MessageFromServer.getResponse());
					break;
				}
				break;
			case CHECK_REPORT_FROM_DB_FOR_CEO:
				switch (ResponseFromServer) {

				case NO_REPORT:

					ViewReportsForCEOController.message.setResponse(MessageFromServer.getResponse());
					break;

				case REPORT_FOUND:

					ViewReportsForCEOController.message.setResponse(MessageFromServer.getResponse());
					break;
				}

				break;
			case GET_MANAGER_ID:
				switch (ResponseFromServer) {

				case NO_REPORT:
					ViewReportForBranchManager.message.setResponse(MessageFromServer.getResponse());
					GraphController.message.setResponse(MessageFromServer.getResponse());
					ViewReportForBranchManager.message.setResponse(MessageFromServer.getResponse());
					break;

				case REPORT_FOUND:
					GraphController.BranchForManager = (ArrayList<String>) MessageFromServer.getObject();
					ViewReportForBranchManager.message.setResponse(MessageFromServer.getResponse());
					ViewReportForBranchManager.BranchForManager = (ArrayList<String>) MessageFromServer.getObject();
					break;
				}

				break;

			case GET_ORDER_REPORT_FROM_DB:
				ViewReportForBranchManager.message.setResponse(MessageFromServer.getResponse());
				ViewReportForBranchManager.OrdersReport = (ArrayList<String>) MessageFromServer.getObject();
				break;

			case GET_NUM_OF_COMPLAINT:
				GraphController.message.setObject(MessageFromServer.getObject());
				break;

			case GET_REPORT_FOR_TWO_QUARTERLY:
				ViewTwoReportsForOneBranchController.message.setResponse(MessageFromServer.getResponse());
				ViewTwoReportsForOneBranchController.message.setObject(MessageFromServer.getObject());
				break;
			case GET_REPORT_FOR_TWO_BRANCHES:

				ViewTwoReportsForTwoBranchesController.message.setObject(MessageFromServer.getObject());
				ViewTwoReportsForTwoBranchesController.message.setResponse(MessageFromServer.getResponse());
				break;
			case GET_CUSTOMER_FROM_DB:
				ChangeCustomerStatusController.ArrayForChangedStatus = (ArrayList<customer>) (MessageFromServer
						.getObject());
				break;
			case UPDATE_STATUS_CUSTOMER:

				ChangeCustomerStatusController.message.setResponse(MessageFromServer.getResponse());
				break;
			case GET_USER_STATUS:
				CustomerPageController.message.setObject(ReturnedObjectFromDB);
				break;

			case GET_COMPLAINT_FROM_DB:

				switch (ResponseFromServer) {

				case NO_COMPLAINTS:
					ComplaintHandelingController.complaintListFromDB = null;
					ComplaintHandelingController.message.setResponse(Response.NO_COMPLAINTS);
					break;

				case COMPLAINTS_FOUND:

					ComplaintHandelingController.complaintListFromDB = (ArrayList<Complaint>) MessageFromServer
							.getObject();
					break;
				}
			case GET_NUM_OF_COMPLAINT_FOR_CEO:
				ViewReportsForCEOController.message.setObject(MessageFromServer.getObject());
				break;

			case GET_ORDER_REPORT_FROM_DB_FOR_CEO:
				ViewReportsForCEOController.message.setResponse(MessageFromServer.getResponse());
				ViewReportsForCEOController.OrdersReport = (ArrayList<String>) MessageFromServer.getObject();
				break;

			case GET_USERS_FROM_DB:
				ChangeUserPermissionController.message.setResponse(MessageFromServer.getResponse());
				ChangeUserPermissionController.ArrayForChangedPermission = (ArrayList<Users>) MessageFromServer
						.getObject();
				break;

			case GET_USERS_FROM_DB_FOR_WORKER:
				WorkerInsertComplaint.message.setResponse(MessageFromServer.getResponse());
				WorkerInsertComplaint.ArrayForCustomers = (ArrayList<Users>) MessageFromServer.getObject();
				break;

			case UPDATE_TYPE_USER:
				ChangeUserPermissionController.message.setResponse(MessageFromServer.getResponse());
				break;

			case UPDATE_BALANCE_AFTER_COMPLAINT:
				switch (ResponseFromServer) {

				case UPDATE_BALANCE_AFTER_COMPLAINT_SUCCEEDED:
					ComplaintRefundController.message.setResponse(ResponseFromServer);
					ComplaintRefundController.message.setObject(ReturnedObjectFromDB);
					break;
				}
				break;
<<<<<<< HEAD

			case EXTRACT_PDF_FROM_DB:
				switch (ResponseFromServer) {

				case PDF_FOUND:
					ServiceExpertPageController.message.setResponse(Response.PDF_FOUND);
					ServiceExpertPageController.extractedPath = (String) ReturnedObjectFromDB;
					break;

				case PDF_NOT_FOUND:
					ServiceExpertPageController.message.setResponse(Response.PDF_NOT_FOUND);
					break;

				}
				break;

=======
			case GET_SURVEYID:
				FillSurveyController.ArrayForSurvey = (ArrayList<survey>) MessageFromServer.getObject();
				break;
>>>>>>> branch 'master' of https://github.com/avii12/zlite3od.git
			}

		}
	}

}
