package AllUsers;

public enum ConfirmationStatus {

	CONFIRMED {
		@Override
		public String toString() {
			return "CONFIRMED";
		}
	},
	PENDING_APPROVAL {
		@Override
		public String toString() {
			return "PENDING_APPROVAL";
		}
	},
	FROZEN {
		@Override
		public String toString() {
			return "FROZEN";
		}

	}
}
