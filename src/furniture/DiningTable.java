package furniture;

import factory.Factory;

public class DiningTable extends Table {

	public DiningTable(Factory factory, String name, int price, String features) {
		super(factory, name, price, features);
	}

	public boolean modifyData() {
		return super.modifyData();
	}

	public String toString() {
		return super.toString();
	}
}
