package furniture;

public class Furniture {

	private String id;
	private String furniture;
	private int price;

	public Furniture(String id, String furniture, int price) {
		this.id = id;
		this.furniture = furniture;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFurniture() {
		return furniture;
	}

	public void setFurniture(String furniture) {
		this.furniture = furniture;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// @Override
	public String toString() {
		return id + ": " + furniture + " " + price;
	}

}
