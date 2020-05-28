package people;

import factory.Factory;

public class Salesman extends Employee {

	public Salesman(Factory factory, String DNI, String name) {
		super(factory, DNI, name);
	}

	public void notifyTheCustomer() {
		int IDOrder = -1;
		boolean allFine = getFactory().printIdsOrderHasNotBeenNotified();
		if (allFine == false) {
			return;
		}
		IDOrder = super.chooseIdOrder();
		if (IDOrder == -1) {
			return;
		}
		getFactory().notifyCustomer(IDOrder);
		return;
	}
}
