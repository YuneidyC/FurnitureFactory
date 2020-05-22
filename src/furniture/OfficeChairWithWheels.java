package furniture;

import factory.Factory;

public class OfficeChairWithWheels extends OfficeChair {

	public OfficeChairWithWheels(Factory factory, String name, int price, String features) {
		super(factory, name, price, features);
	}

	public String toString() {
		return super.toString();
	}
}
