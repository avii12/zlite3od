package Orders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemsForTableView {

	private Item item;
	private ImageView Picture;
	public String ItemName;
	public FlowerColor Color;
	public double Price;
	public String PicturePath;
	public ItemType type;
	public DominantColor dominantColor;
	public int amount;
	public String ID;
	public ItemCategory Category;

	
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
