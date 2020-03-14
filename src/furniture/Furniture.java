package furniture;

public class Furniture {

	private static int nextId = 0;
	private int id;
	private String furniture;
	private int price;

	public Furniture(String furniture) {
		this.id = nextId++;
		this.furniture = furniture;
	}

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

	public String getFurniture() {
		return furniture;
	}

	public void setFurniture(String furniture) {
		this.furniture = furniture;
	}

	// @Override
	public String toString() {
		return id + ": " + furniture + " " + price;
	}

}
