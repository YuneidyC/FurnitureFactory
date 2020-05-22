package furniture;

import factory.Factory;

public class Table extends Furniture {

	public Table(Factory factory, String name, int price, String features) {
		super(factory, name, price, features);
	}

	public String toString() {
		return super.toString();
	}

}
