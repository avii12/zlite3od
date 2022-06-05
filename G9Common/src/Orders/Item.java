package Orders;

import java.io.Serializable;


public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String ItemID;
	public ItemCategory itemCategory;
	public FlowerColor Color;
	public String ItemName;
	public double price;
	public String PicturePath;
	public String GreetingCard;
	public ItemType type;
	public DominantColor dominantColor;
	public int amount;
	
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
	
	
