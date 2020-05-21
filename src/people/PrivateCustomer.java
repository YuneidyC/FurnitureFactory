package people;

public class PrivateCustomer extends Client {

	public PrivateCustomer(String DNI, String name, int telephone) {
		super(DNI, name, telephone);
	}

	public void modifyData() {
		super.modifyData();
	}

	public String toString() {
		return super.toString();
	}
}
