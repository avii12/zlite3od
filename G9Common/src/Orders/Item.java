package Orders;

import java.io.Serializable;

/**
 * class description;
 * the item class contains all the attributes of the item
 * 
 * @author Obied ,Haddad.
 * @author Ebrahem ,Enbtawe.
 * @author Mario, Rohana.

 */
public class Item implements Serializable {

	
	private static final long serialVersionUID = 1L;
	/**
	 *  item id 
	 */
	public String ItemID;
	/**
	 * item category
	 */
	public ItemCategory itemCategory;
	/**
	 * the item color
	 */
	public FlowerColor Color;
	/**
	 * item name
	 */
	public String ItemName;
	/**
	 * item price
	 */
	public double price;
	/**
	 *the path of the item picture
	 */
	public String PicturePath;
	/**
	 * item greeting card
	 */
	public String GreetingCard;
	/**
	 * item type
	 */
	public ItemType type;
	/**
	 * item dominant color
	 */
	public DominantColor dominantColor;
	/**
	 * item amount
	 */
	public int amount;
	
	/**
	 * @param itemID
	 * @param itemCategory
	 * @param color
	 * @param itemName
	 * @param price
	 * @param picturePath
	 * @param greetingCard
	 * @param type
	 * @param dominantColor
	 * @param amount
	 */
	public Item(String itemID, ItemCategory itemCategory, FlowerColor color, String itemName, double price,
			String picturePath, String greetingCard,ItemType type,DominantColor dominantColor,int amount) {
		super();
		this.ItemID = itemID;
		this.itemCategory = itemCategory;
		this.Color = color;
		this.ItemName = itemName;
		this.price = price;
		this.PicturePath = picturePath;
		this.GreetingCard = greetingCard;
		this.type=type;
		this.dominantColor=dominantColor;
		this.amount=amount;
	}
	
	/**
	 * @param item
	 */
	public Item(Item item) {
		this.ItemID= item.getItemID();
		this.ItemName = item.getItemName();
		this.itemCategory = item.getItemCategory();
		this.Color = item.getColor();
		this.PicturePath = item.getPicturePath();
		this.price = item.getPrice();
		this.type=item.getType();
		this.dominantColor=item.getDominantColor();
		this.amount=item.getAmount();
		this.itemCategory=item.getItemCategory();

	}
	
	/**
	 * 
	 * setters and getters 
	 */
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public DominantColor getDominantColor() {
		return dominantColor;
	}

	public void setDominantColor(DominantColor dominantColor) {
		this.dominantColor = dominantColor;
	}

	public String getItemID() {
		return ItemID;
		
	}
	public void setItemID(String itemID) {
		ItemID = itemID;
	}
	public ItemCategory getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}
	public FlowerColor getColor() {
		return Color;
	}
	public void setColor(FlowerColor color) {
		Color = color;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;

	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPicturePath() {
		return PicturePath;
	}
	public void setPicturePath(String picturePath) {
		PicturePath = picturePath;
	}
	public String getGreetingCard() {
		return GreetingCard;
	}
	public void setGreetingCard(String greetingCard) {
		GreetingCard = greetingCard;
	}

	@Override
	public String toString() {
		return "Item [ItemID=" + ItemID + ", itemCategory=" + itemCategory + ", Color=" + Color + ", ItemName="
				+ ItemName + ", price=" + price + ", PicturePath=" + PicturePath + ", GreetingCard=" + GreetingCard
				+ ", type=" + type + ", dominantColor=" + dominantColor + "]";
	}
	
	
}
	
	
