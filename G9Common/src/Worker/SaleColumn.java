package Worker;

import java.io.Serializable;

public class SaleColumn implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String SaleOn;
	private String Percent;
	private String branch;
	public SaleColumn(String saleOn, String percent, String branch) {
		super();
		SaleOn = saleOn;
		Percent = percent;
		this.branch = branch;
	}
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
	@Override
	public String toString() {
		return "SaleColumn [SaleOn=" + SaleOn + ", Percent=" + Percent + ", branch=" + branch + "]";
	}
	
	
	
	

}
