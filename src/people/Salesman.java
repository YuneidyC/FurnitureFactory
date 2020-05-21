package people;

import factory.Factory;

public class Salesman extends Employee {

	public Salesman(String DNI, String name) {
		super(DNI, name);
	}

	public void notifyTheCustomer() {
		int idOrder = -1;
		boolean allFine = Factory.printIdsOrderHasNotBeenNotified();
		if (allFine == false) {
			return;
		}
		idOrder = super.chooseIdOrder();
		if (idOrder == -1) {
			return;
		}
		Factory.notifyCustomer(idOrder);
		return;
	}

	public void modifyData() {
		super.modifyData();
	}
}
