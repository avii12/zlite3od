package Report;

import java.io.Serializable;

import Orders.Branch;

/**
 * 
 * Class description:
 * this class is the reports class
 * contains all the report information
 * 
 * @author Maisalon, Safory
 * @author Seren ,Hanany.
 * @author Shoroq ,Heib.
 *
 */

public class Reports implements Serializable {
	
	
	/**
	 * report id number
	 */
	private int idreport;
	/**
	 *  report type
	 */
	private ReportType ReportType;
	/**
	 *  report date
	 */
	private String Date;
	/**
	 * branch id
	 */
	private Branch branchID;
	
	
	/**
	 * @param idreport
	 * @param reportType
	 * @param date
	 * @param branchID
	 */
	public Reports(int idreport, Report.ReportType reportType, String date, Branch branchID) {
		super();
		this.idreport = idreport;
		ReportType = reportType;
		Date = date;
		this.branchID = branchID;
	}
	
	
	
	/**
	 * setters and getters
	 */
	
	public Branch getBranch() {
		return branchID;
	}


	public void setBranch(Branch branch) {
		this.branchID = branch;
	}


	public int getIdreport() {
		return idreport;
	}
	public void setIdreport(int idreport) {
		this.idreport = idreport;
	}
	public ReportType getReportType() {
		return ReportType;
	}
	public void setReportType(ReportType reportType) {
		ReportType = reportType;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	/**
	 * return report info
	 */
	@Override
	public String toString() {
		return "Reports [idreport=" + idreport + ", ReportType=" + ReportType + ", Date=" + Date + ", branchID="
				+ branchID + "]";
	}
	

}
