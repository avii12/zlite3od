package AllUsers;
/**
 
*  Enum description:
*  We use this enum to clarify the user status
*  The user needs to be confired first
*  
*  @version Ebrahem, Enbtawe.
*/

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
