package furniture;

import factory.Factory;

public class BedroomTable extends Table {

	public BedroomTable(Factory factory, String model, int price, String features) {
		super(factory, model, price, features);
	}

	public boolean modifyData() {
		return super.modifyData();
	}

	public String toString() {
		return super.toString();
	}
}
