package furniture;

public class KitchenChair extends Chair {

	private int price;

	public KitchenChair(String id, String name) {
		super(id, name);
	}

	public int getPrice() {
		return price;
	}
}
