package furniture;

import factory.Factory;

public class OfficeChairWithoutWheels extends OfficeChair {

	public OfficeChairWithoutWheels(Factory factory, String name, int price, String features) {
		super(factory, name, price, features);
	}

	public String toString() {
		return super.toString();
	}
}
