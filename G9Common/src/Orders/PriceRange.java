package Orders;

public enum PriceRange {
	RANGE1 {
		@Override
		public String toString() {
			return "10->50";
		}
	},
	RANGE2 {
		@Override
		public String toString() {
			return "50->100";
		}
	},
	RANGE3 {
		@Override
		public String toString() {
			return "100->150";
		}
	},
	RANGE4 {
		@Override
		public String toString() {
			return "150->200";
		}
	}

}