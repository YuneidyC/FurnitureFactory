package furniture;

import factory.Factory;

public class Furniture {

	private static int nextId = 0;
	private int id;
	private String furnitureModel;
	private int price;
	private String features;
	private String status = "En espera";

	public Furniture(String furnitureModel, int price, String features) {
		this.id = nextId++;
		this.furnitureModel = furnitureModel;
		this.price = price;
		this.features = features;
	}

	public int getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}

	public String getFurniture() {
		return furnitureModel;
	}

	public void setFurniture(String furniture) {
		this.furnitureModel = furniture;
	}

//	If an attribute to be modify is empty, it'll be left unchanged 
	public void modifyData() {
		System.out.println("Insert Furniture model: ");
		String str = Factory.sc.nextLine();
		if (!str.isEmpty()) {
			furnitureModel = str;
		}
		System.out.println("Insert price: ");
		str = Factory.sc.nextLine();
		if (!str.isEmpty()) {
			int priceFurniture = Integer.parseInt(str);
			price = priceFurniture;
		}
	}

	// @Override
	public String toString() {
		return id + ": " + furnitureModel + " " + price;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
