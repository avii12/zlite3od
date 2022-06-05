package Orders;

import java.io.Serializable;

public class AbstractSupplyType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int SupplyID;
	public int OrderNumber;
	public String ReceiverFirstName;
	public String ReceiverLasttName;
	public String ReceiverPhoneNumber;

	public AbstractSupplyType(int supplyID, int orderNumber, String receiverFirstName, String receiverLasttName,
			String receiverPhoneNumber) {
		super();
		SupplyID = supplyID;
		OrderNumber = orderNumber;
		ReceiverFirstName = receiverFirstName;
		ReceiverLasttName = receiverLasttName;
		ReceiverPhoneNumber = receiverPhoneNumber;
	}

	public int getSupplyID() {
		return SupplyID;
	}

	public void setSupplyID(int supplyID) {
		SupplyID = supplyID;
	}

	public int getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		OrderNumber = orderNumber;
	}

	public String getReceiverFirstName() {
		return ReceiverFirstName;
	}

	public void setReceiverFirstName(String receiverFirstName) {
		ReceiverFirstName = receiverFirstName;
	}

	public String getReceiverLasttName() {
		return ReceiverLasttName;
	}

	public void setReceiverLasttName(String receiverLasttName) {
		ReceiverLasttName = receiverLasttName;
	}

	public String getReceiverPhoneNumber() {
		return ReceiverPhoneNumber;
	}

	public void setReceiverPhoneNumber(String receiverPhoneNumber) {
		ReceiverPhoneNumber = receiverPhoneNumber;
	}

}
