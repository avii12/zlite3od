package Orders;


/**

 * Type of supply enum 
 * two options take away, delivery
 * 
 * @author Obied ,Haddad.

 */
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
