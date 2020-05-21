package furniture;

import factory.Factory;

public class WoodenCoffeeTable extends CoffeeTable {

	public WoodenCoffeeTable(Factory factory, String name, int price, String features) {
		super(factory, name, price, features);
	}

	public boolean modifyData() {
		return super.modifyData();
	}

	public String toString() {
		return super.toString();
	}
}
