package Report;

public enum ReportType {
	Income {
		@Override
		public String toString() {
			return "Income";
		}
	},
	Complaint {
		@Override
		public String toString() {
			return "Complaint";
		}
	},
	Orders {
		@Override
		public String toString() {
			return "Orders";
		}
	}
}
