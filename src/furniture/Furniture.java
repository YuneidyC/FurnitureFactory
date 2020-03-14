package furniture;

import java.util.LinkedList;
import java.util.List;

public class Furniture {

	private String id;
	private String furniture;
	private int price;
	private List<Furniture> furnitures = new LinkedList<Furniture>();

	public Furniture(String id, String furniture) {
		this.id = id;
		this.furniture = furniture;
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

//	ADD NEW FURNITURE 
	public void addNewFurniture(Furniture furniture) {
		furnitures.add(furniture);
	}

	// @Override
	public String toString() {
		return id + ": " + furniture + " " + price;
	}

}
