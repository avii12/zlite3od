package Orders;

public enum RefundStatus {
	
	FULL_REFUND {
		@Override
		public String toString() {
			return "FULL_REFUND";
		}
	},
	NO_REFUND {
		@Override
		public String toString() {
			return "NO_REFUND";
		}
	},
	NULL {
		@Override
		public String toString() {
			return "NULL";
		}
	},
	WAITING {
		@Override
		public String toString() {
			return "WAITING";
		}
	}

}
