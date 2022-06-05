package Orders;

import java.io.Serializable;

public class DeliveryDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TypeOfSupply typeOfSupply;
	public String ReciverAddress;
	public double deliveryFee;
	
	public DeliveryDetails(TypeOfSupply typeOfSupply, String reciverAddress, double deliveryFee) {
		super();
		this.typeOfSupply = typeOfSupply;
		ReciverAddress = reciverAddress;
		this.deliveryFee = deliveryFee;
	}

	public TypeOfSupply getTypeOfSupply() {
		return typeOfSupply;
	}

	public void setTypeOfSupply(TypeOfSupply typeOfSupply) {
		this.typeOfSupply = typeOfSupply;
	}

	public String getReciverAddress() {
		return ReciverAddress;
	}

	public void setReciverAddress(String reciverAddress) {
		ReciverAddress = reciverAddress;
	}

	public double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
