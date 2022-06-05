package RequestsAndResponses;

public enum Response {

	Wait, Succeed, NO_SUCH_USER, ALREADY_LOGGED_IN, Create_CUSTOMER_PORTAL, Create_BRANCHMANAGER_PORTAL,
	Create_CEOZLI_PORTAL, Create_CUSTOMERSERVICEWORKER_PORTAL, Create_SERVICESPECIALIST_PORTAL, Create_WORKER_PORTAL,
	NO_CATALOG, PRODUCTS_IN_CATALOG_FOUND, ITEMS_IN_CATALOG_FOUND,

	CATALOG_FOUND, NO_ORDER_FOUND, ORDER_FOUND, CUSTOMER_FOUND, NOT_FIRST_ORDER, AMOUNT_UPDATED,
	PRODUCT_NOT_IN_INVENTORY, AMOUNT_RESTORED, WAIT_RESPONSE, UPDATE_UNAPPROVED_ORDER_BALANCE_SUCCEEDED,
	ORDER_FOUND_FOR_CANCELORDER, NO_DATE_FOUND, GET_THE_SUBRACTED_DATE_TIME_SUCCEDED, TIME_FOUND,
	MANAGE_ORDER_APPROVED_SUCCEEDED_WRITING_INTO_DB, MANAGE_ORDER_COMPLETED_SUCCEEDED_WRITING_INTO_DB,
	ORDER_FOUND_FOR_MANAGER, MANAGE_ORDER_PENDING_SUCCEEDED_WRITING_INTO_DB,
	MANAGE_ORDER_APPROVED_CANCEL_SUCCEEDED_WRITING_INTO_DB, MANAGE_ORDER_UN_APPROVED_SUCCEEDED_WRITING_INTO_DB,
	MANAGE_ORDER_CANCEL_SUCCEEDED_WRITING_INTO_DB,

	NO_REPORT, REPORT_FOUND, NO_BRANCHID, Create_DELIVERYPERSON_PORTAL,

	NO_CUSTOMER, EDIT_SUCCEED,

	WAIT_RESPONSE_FOR_DELIVERY, GET_THE_SUBRACTED_DATE_TIME_SUCCEDED_FOR_DELIVERY, NO_COMPLAINTS, COMPLAINTS_FOUND,
	UPDATE_BALANCE_AFTER_COMPLAINT_SUCCEEDED, MANAGE_COMPLAINT_APPROVED_SUCCEEDED_WRITING_INTO_DB,
	ORDER_FOUND_FOR_CUSTOMER, USER_FOUND, USER_UPDATED, NO_SURVEY, SURVEY_FOUND, SET_ANSWER_DONE,

}
