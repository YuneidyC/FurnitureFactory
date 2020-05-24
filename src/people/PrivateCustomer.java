package people;

import factory.Factory;

public class PrivateCustomer extends Client {

	public PrivateCustomer(Factory factory, String DNI, String name, int telephone) {
		super(factory, DNI, name, telephone);
	}

}
