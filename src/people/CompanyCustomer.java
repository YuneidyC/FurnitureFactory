package people;

import factory.Factory;

public class CompanyCustomer extends Client {

	public CompanyCustomer(Factory factory, String DNI, String name, int telephone) {
		super(factory, DNI, name, telephone);
	}

	public void modifyData() {
		super.modifyData();
	}

}
