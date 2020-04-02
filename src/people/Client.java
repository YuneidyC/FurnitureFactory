package people;

import factory.Factory;
import factory.Order;

public class Client extends Person {

	public Client(String id, String name) {
		super(id, name);
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
