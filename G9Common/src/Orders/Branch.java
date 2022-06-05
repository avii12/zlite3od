package Orders;

public enum Branch {
	TheSecretGarden {
		@Override
		public String toString() {
			return "TheSecretGarden";
		}
	},
	YourNeighborhoodFlorist {
		@Override
		public String toString() {
			return "YourNeighborhoodFlorist";
		}
	},
	BeautifulBlossoms {
		@Override
		public String toString() {
			return "BeautifulBlossoms";
		}
	}

}
