package Orders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * class description:
 * class to insert the items in the item table 
 *
 * 
 * @author Ebrahem ,Enbtawe
 */

public class ItemsForTableView {

	/**
	 * the item
	 */
	private Item item;
	/**
	 * the picture of the item
	 */
	private ImageView Picture;
	/**
	 * the item name
	 */
	public String ItemName;
	/**
	 * the color of the flower
	 */
	public FlowerColor Color;
	/**
	 * the price of the item 
	 */
	public double Price;
	/**
	 * the picture path
	 */
	public String PicturePath;
	/**
	 * the type of the item
	 */
	public ItemType type;
	/**
	 * the dominant color of the item
	 */
	public DominantColor dominantColor;
	/**
	 * the amount 
	 */
	public int amount;
	/**
	 * the Id of the item
	 */
	public String ID;
	/**
	 * item category
	 */
	public ItemCategory Category;

	
	/**
	 * @param item
	 */
	public ItemsForTableView(Item item) {
		this.item = item;
	    this.Picture = new ImageView(new Image(item.getPicturePath(),64,64,false,true));
		this.ItemName = item.getItemName();
	    this.Price = item.getPrice();
	    this.PicturePath = item.getPicturePath();
		this.Color=item.getColor();
		this.type=item.getType();
		this.dominantColor=item.getDominantColor();
		this.amount=item.getAmount();
		this.ID=item.getItemID();
		this.Category=item.getItemCategory();
		
	}
	
	/*
	 * getters and setters
	 */

	public ItemCategory getCategory() {
		return Category;
	}



	public void setCategory(ItemCategory category) {
		Category = category;
	}



	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}




	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}




	public String getItemName() {
		return ItemName;
	}




	public void setColor(FlowerColor color) {
		Color = color;
	}




	public ItemType getType() {
		return type;
	}



	public void setType(ItemType type) {
		this.type = type;
		item.setType(type);
	}



	public DominantColor getDominantColor() {
		return dominantColor;
	}



	/**
	 * @param dominantColor
	 */
	public void setDominantColor(DominantColor dominantColor) {
		this.dominantColor = dominantColor;
		item.setDominantColor(dominantColor);
	}



	public Item getItem() {
		return item;
	}
	
	public FlowerColor getColor() {
		
		return Color;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ImageView getPicture() {
		return Picture;
	}

	public void setPicture(ImageView picture) {
		Picture = picture;
	}

	public String getName() {
		
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
		item.setPrice(price);
	}

	public String getPicturePath() {
		return PicturePath;
	}

	/**
	 * @param picturePath
	 */
	public void setPicturePath(String picturePath) {
		PicturePath = picturePath;
		item.setPicturePath(PicturePath);
	}

	
	public boolean equals(Object obj) {
		ItemsForTableView o = (ItemsForTableView) obj;
		return item.equals(o.getItem());
	}
	
	public void SetColor(FlowerColor color) {
		this.Color = color;
		item.setColor(color);
	}

}
