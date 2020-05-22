package furniture;

import factory.Factory;

public class CoffeeTable extends Table {

	public CoffeeTable(Factory factory, String name, int price, String features) {
		super(factory, name, price, features);
	}

	public String toString() {
		return super.toString();
	}
}
