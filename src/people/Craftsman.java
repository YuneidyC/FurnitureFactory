package people;

import java.util.LinkedList;
import java.util.List;

import factory.Order;

public class Craftsman extends Employee {

	private List<Order> assignedOrders = new LinkedList<Order>();

	public Craftsman(String id, String name) {
		super(id, name);
	}

	public List<Order> getAssignedOrders() {
		return assignedOrders;
	}

	public void assignOrder(Order order) {
		assignedOrders.add(order);
	}

	// TODO
	public void statusOrder() {
		for (Order order : assignedOrders) {
			if (order.getStatus() != "Done") {

			}
		}
	}

	public void modifyData() {
		super.modifyData();
	}
}
