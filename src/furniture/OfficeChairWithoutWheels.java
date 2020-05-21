package furniture;

import factory.Factory;

public class OfficeChairWithoutWheels extends OfficeChair {

	public OfficeChairWithoutWheels(Factory factory, String name, int price, String features) {
		super(factory, name, price, features);
	}

	public boolean modifyData() {
		return super.modifyData();
	}

	public String toString() {
		return super.toString();
	}
}
