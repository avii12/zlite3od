package Orders;

public enum TypeOfSupply {
	TAKE_AWAY {
		@Override
		public String toString() {
			return "TAKE_AWAY";
		}
	},
	DELIVERY {
		@Override
		public String toString() {
			return "DELIVERY";
		}
	}
}
