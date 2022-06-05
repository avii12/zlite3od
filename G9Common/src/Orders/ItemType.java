package Orders;

public enum ItemType {
	FLORABLOOM {
		@Override
		public String toString() {
			return "FloraBloom";
		}
	},
	LILLY {
		@Override
		public String toString() {
			return "Lilly";
		}
	},

}