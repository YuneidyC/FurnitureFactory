package furniture;

import factory.Factory;

public class BedroomTable extends Table {

	public BedroomTable(Factory factory, String model, int price, String features) {
		super(factory, model, price, features);
	}

	public String toString() {
		return super.toString();
	}
}
