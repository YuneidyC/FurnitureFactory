package people;

import java.util.LinkedList;
import java.util.List;

import factory.Order;

public class PrivateCustomer extends Client {

	private List<Order> orders = new LinkedList<Order>();

	public PrivateCustomer(String dni, String name) {
		super(dni, name);
	}

	public void addOrder(Order o) {
		orders.add(o);
	}

	public void modifyData() {
		super.modifyData();
	}
}
