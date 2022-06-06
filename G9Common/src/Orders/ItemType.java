package Orders;

/**

 * Item type enum 
 * the flowers type in the item category
 * 
 * @author Obied ,Haddad.

 */

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