package furniture;

import factory.Factory;

public class Chair extends Furniture {

	public Chair(Factory factory, String name, int price, String features) {
		super(factory, name, price, features);
	}

	public String toString() {
		return super.toString();
	}
}
