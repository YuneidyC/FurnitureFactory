package furniture;

import factory.Factory;

public class KitchenChair extends Chair {

	public KitchenChair(Factory factory, String name, int price, String features) {
		super(factory, name, price, features);
	}

	public String toString() {
		return super.toString();
	}
}
