package Orders;

import java.io.Serializable;
/**
 *class description:
 *delivery details class contains the information that is required for the delivery option 
 * 
 * @author Ebrahem ,Enbtawe.

 */
public class DeliveryDetails implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * type of supply 
	 */
	public TypeOfSupply typeOfSupply;
	/**
	 *  the address for the delivery
	 */
	public String ReciverAddress;
	/**
	 * the delivery fee
	 */
	public double deliveryFee;
	
	/**
	 * @param typeOfSupply
	 * @param reciverAddress
	 * @param deliveryFee
	 */
	public DeliveryDetails(TypeOfSupply typeOfSupply, String reciverAddress, double deliveryFee) {
		super();
		this.typeOfSupply = typeOfSupply;
		ReciverAddress = reciverAddress;
		this.deliveryFee = deliveryFee;
	}
	
	/**
	 * 
	 * setters and getters
	 */

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
