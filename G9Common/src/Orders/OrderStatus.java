package Orders;

public enum OrderStatus {
	PENDING {
		@Override
		public String toString() {
			return "Pending";
		}
	},
	APPROVED {
		@Override
		public String toString() {
			return "Approved";
		}

	},
	UN_APPROVED {
		@Override
		public String toString() {
			return "Un_Approved";
		}
	},
	CANCEL {
		@Override
		public String toString() {
			return "Cancel";
		}
	},
	COMPLETED {
		@Override
		public String toString() {
			return "Completed";
		}
	},
	APPROVED_CANCEL {
		@Override
		public String toString() {
			return "Approved Cancel";
		}

	}

}
