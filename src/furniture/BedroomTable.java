package furniture;

public class BedroomTable extends Table {

	private int price;

	public BedroomTable(String id, String name) {
		super(id, name);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
