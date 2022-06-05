package Orders;

public enum ItemCategory {
	FLOWER {
		@Override
		public String toString() {
			return "FLOWER";
		}
	},
	BOUQUET {
		@Override
		public String toString() {
			return "BOUQUET";
		}
	}
}
