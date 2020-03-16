package furniture;

import java.util.Scanner;

public class Furniture {

	private static int nextId = 0;
	private int id;
	private String furnitureModel;
	private int price;

	public Furniture(String furnitureModel, int price) {
		this.id = nextId++;
		this.furnitureModel = furnitureModel;
		this.price = price;
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
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert Furniture model: ");
		String str = sc.nextLine();
		if (!str.isEmpty()) {
			furnitureModel = str;
		}
		System.out.println("Insert price: ");
		str = sc.nextLine();
		int priceFurniture = Integer.parseInt(str);
		if (!str.isEmpty()) {
			price = priceFurniture;
		}
		sc.close();
	}

	// @Override
	public String toString() {
		return id + ": " + furnitureModel + " " + price;
	}

}
