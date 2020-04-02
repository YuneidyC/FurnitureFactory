package people;

import java.util.LinkedList;
import java.util.List;

import factory.Order;

public class CompanyCustomer extends Client {

	private List<Order> orders = new LinkedList<Order>();

	public CompanyCustomer(String id, String name) {
		super(id, name);
	}

	public void addOrder(Order o) {
		orders.add(o);
	}

	public void modifyData() {
		super.modifyData();
	}

	public boolean confirmOrder() {
		for (Order o : orders) {
			if (o.getPendingCustomerConfirmation()) {

			}
		}
		return true;
	}

}
