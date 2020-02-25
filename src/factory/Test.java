package factory;

import java.util.LinkedList;
import java.util.List;

import furniture.WoodenCoffeeTable;
import people.PrivateCustomer;

public class Test {

	private boolean testResult = true;

	public boolean getTestResult() {
		return testResult;
	}

	public static void createPerson() {
		// list<order> l;
		// l.add(

		PrivateCustomer p = new PrivateCustomer("ID1", "Order1");
		WoodenCoffeeTable t = new WoodenCoffeeTable("Order1");
		List<String> order = new LinkedList<String>();
		order.add("Order1");

	}

	public void createAndModifyPerson() {

	}
}
