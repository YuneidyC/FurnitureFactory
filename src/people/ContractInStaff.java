package people;

import factory.Factory;

public class ContractInStaff extends Craftsman {

	public ContractInStaff(Factory factory, String DNI, String name) {
		super(factory, DNI, name);
	}

	public String toString() {
		return super.toString();
	}

	public void modifyData() {
		super.modifyData();
	}
}
