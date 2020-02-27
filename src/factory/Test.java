package factory;

import java.util.LinkedList;
import java.util.List;

import furniture.WoodenCoffeeTable;
import people.PrivateCustomer;

public class Test {

	public static void createPerson() {

		PrivateCustomer p = new PrivateCustomer("ID1", "Julian");
		WoodenCoffeeTable t = new WoodenCoffeeTable("Order1");
		List<String> order = new LinkedList<String>();
		order.add("Order1");
		System.out.println(t.getId());
		System.out.println(p.getDni() + " " + p.getName());
	}

	public void createAndModifyPerson() {

	}
}
