package people;

import java.util.LinkedList;
import java.util.List;

import factory.Factory;
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

	public static void confirmOrder() {
		Client client = null;
		boolean exist = false;
		while (!exist) {
			client = Factory.getClient();
			if (client != null) {
				exist = true;
				break;
			}
			System.out.println("This client no exist");
		}
		Order confirmOrder = Factory.confirmStatusOrder(client);
		if (confirmOrder != null) {
			int answer = Factory.askConfirmOrder();
			if (answer == 1) {
				confirmOrder.setPendingCustomerConfirmation(true);
			} else {
				confirmOrder.setPendingCustomerConfirmation(false);
			}
		}
	}
}
