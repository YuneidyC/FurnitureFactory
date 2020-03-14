package furniture;

public class DiningTable extends Table {

	private int price;

	public DiningTable(String id, String name) {
		super(id, name);
	}

	public int getPrice() {
		return price;
	}
}
