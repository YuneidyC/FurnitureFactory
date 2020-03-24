package people;

import java.util.LinkedList;
import java.util.List;

public class Craftsman extends Employee {

//	TODO: Change order by string
	private List<Integer> assignedOrders = new LinkedList<Integer>();

	public Craftsman(String id, String name) {
		super(id, name);
	}

	public List<Integer> getAssignedOrders() {
		return assignedOrders;
	}

	public void assignOrder(int order) {
		assignedOrders.add(order);
	}

	// TODO
//	public void statusOrder() {
//		for (Order order : assignedOrders) {
//			if (order.getStatus() != "Done") {
//
//			}
//		}
//	}

	public void modifyData() {
		super.modifyData();
	}
}
