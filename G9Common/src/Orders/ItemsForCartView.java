package Orders;

import javafx.scene.image.ImageView;

public class ItemsForCartView {

	private Item item;
	private ImageView Picture;
	public String ItemName;
	public FlowerColor Color;
	public double Price;
	public String PicturePath;
	public ItemType type;
	public DominantColor dominantColor;
	public int Bouque;
	private String ID;

	public ItemsForCartView(Item item,int Bouque,String ItemName) {
		this.item = item;
		this.type=item.getType();
	    this.Price = item.getPrice();
	    this.Bouque=Bouque;
	    this.ItemName = ItemName;
	    this.ID=item.getItemID();
	    
	}

	
	
	public String getID() {
		return ID;
	}



	public void setID(String iD) {
		ID = iD;
	}



	public Item getItem() {
		return item;
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

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
	//	ItemName = itemName;
		item.setItemName(itemName);
	}

	public FlowerColor getColor() {
		return Color;
	}

	public void setColor(FlowerColor color) {
		Color = color;
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

	public int getBouque() {
		return Bouque;
	}

	public void setBouque(int bouque) {
		Bouque = bouque;
	}

	@Override
	public String toString() {
		return "ItemsForCartView [item=" + item + ", Picture=" + Picture + ", ItemName=" + ItemName + ", Color=" + Color
				+ ", Price=" + Price + ", PicturePath=" + PicturePath + ", type=" + type + ", dominantColor="
				+ dominantColor + ", Bouque=" + Bouque + "]" +"\n";
	}


}
