package Worker;

import java.io.Serializable;



/**
 * class descritpion:
 * this class is for sale column details in the  DB
 * 
 * @author Ebrahem, Enbtawe
 *
 */
public class SaleColumn implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * checks if there is a sale
	 */
	private String SaleOn;
	/**
	 * the sale percent, how many is off the price
	 */
	private String Percent;
	/**
	 * the branch we do the sale in 
	 */
	private String branch;
	
	
	/**
	 * @param saleOn
	 * @param percent
	 * @param branch
	 */
	public SaleColumn(String saleOn, String percent, String branch) {
		super();
		SaleOn = saleOn;
		Percent = percent;
		this.branch = branch;
	}
	
	/*
	 * 
	 * the setters and getters of the sale column
	 */
	public String getSaleOn() {
		return SaleOn;
	}
	public void setSaleOn(String saleOn) {
		SaleOn = saleOn;
	}
	public String getPercent() {
		return Percent;
	}
	public void setPercent(String percent) {
		Percent = percent;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	
	/**
	 * return the details as a string 
	 */
	@Override
	public String toString() {
		return "SaleColumn [SaleOn=" + SaleOn + ", Percent=" + Percent + ", branch=" + branch + "]";
	}
	
	
	
	

}
