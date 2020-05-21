package furniture;

public class OfficeChairWithoutWheels extends OfficeChair {

	public OfficeChairWithoutWheels(String name, int price, String features) {
		super(name, price, features);
	}

	public boolean modifyData() {
		return super.modifyData();
	}

	public String toString() {
		return super.toString();
	}
}
