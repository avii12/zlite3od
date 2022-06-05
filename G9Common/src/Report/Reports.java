package Report;

import java.io.Serializable;

import Orders.Branch;

public class Reports implements Serializable {
	
	@Override
	public String toString() {
		return "Reports [idreport=" + idreport + ", ReportType=" + ReportType + ", Date=" + Date + ", branchID="
				+ branchID + "]";
	}
	private int idreport;
	private ReportType ReportType;
	private String Date;
	private Branch branchID;

	public Branch getBranch() {
		return branchID;
	}


	public void setBranch(Branch branch) {
		this.branchID = branch;
	}

	

	public Reports(int idreport, Report.ReportType reportType, String date, Branch branchID) {
		super();
		this.idreport = idreport;
		ReportType = reportType;
		Date = date;
		this.branchID = branchID;
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
	

}
