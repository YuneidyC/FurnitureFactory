package people;

import java.util.LinkedList;
import java.util.List;

import factory.Factory;
import factory.Order;

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

	public void statusOrder() {
		boolean exist = false;
		Craftsman craftman = null;

		while (!exist) {
			craftman = Factory.getCraftman();
			if (craftman != null) {
				exist = true;
				break;
			}
			System.out.println("This craftman no exist");
		}
		for (int idOrder : assignedOrders) {
			Order o = Factory.getOrder(idOrder);
			if (o != null) {
				if (o.getStatus().equals("En espera")) {
//					o.setStatus(status);
				}
			}
		}
	}

	public void modifyData() {
		super.modifyData();
	}
}
