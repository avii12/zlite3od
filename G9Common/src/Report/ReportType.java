package Report;
/**
 * 
 * Class description:
 * this enum id for the report type 
 * contains income 
 * complaint 
 * orders
 * 
 * @author Maisalon, Safory
 * @author Seren ,Hanany.
 * @author Shoroq ,Heib.
 *
 */
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
