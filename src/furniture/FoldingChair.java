package furniture;

import factory.Factory;

public class FoldingChair extends Chair {

	public FoldingChair(Factory factory, String name, int price, String features) {
		super(factory, name, price, features);
	}

	public String toString() {
		return super.toString();
	}
}
