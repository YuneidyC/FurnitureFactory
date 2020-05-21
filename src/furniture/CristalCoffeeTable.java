package furniture;

import factory.Factory;

public class CristalCoffeeTable extends CoffeeTable {

	public CristalCoffeeTable(Factory factory, String name, int price, String features) {
		super(factory, name, price, features);
	}

	public boolean modifyData() {
		return super.modifyData();
	}

	public String toString() {
		return super.toString();
	}
}
