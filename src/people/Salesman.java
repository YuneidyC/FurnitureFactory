package people;

import factory.Factory;

public class Salesman extends Employee {

	public Salesman(Factory factory, String DNI, String name) {
		super(factory, DNI, name);
	}

	public void notifyTheCustomer() {
		int idOrder = -1;
		boolean allFine = getFactory().printIdsOrderHasNotBeenNotified();
		if (allFine == false) {
			return;
		}
		idOrder = super.chooseIdOrder();
		if (idOrder == -1) {
			return;
		}
		getFactory().notifyCustomer(idOrder);
		return;
	}

	public void modifyData() {
		super.modifyData();
	}
}
