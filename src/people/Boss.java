package people;

import factory.Factory;
import factory.Order;

public class Boss extends Employee {

	public Boss(Factory factory, String DNI, String name) {
		super(factory, DNI, name);
	}

	public void assignCraftsman() {
		boolean assignCraftsman = false;
		Craftsman craftsman = getFactory().assignOrderWithIDOrRandom();
		if (craftsman == null) {
			return;
		}
		int ID = getFactory().unassignedOrders();
		Order order = getFactory().getOrder(ID);
		if (order == null) {
			System.out.println("This order does not exist");
			return;
		}
		assignCraftsman = assignCraftsman(order, craftsman);
		if (assignCraftsman == true) {
			System.out.println("This craftsman has been assigned.");
			return;
		}
		System.out.println("This craftsman has not been assigned.");
		return;
	}

	public boolean assignCraftsman(Order order, Craftsman craftsman) {
		if (order == null || craftsman == null) {
			return false;
		}
		if (order.getEmployeeAssigned() != null) {
			return false;
		}
		order.setEmployeeAssigned(craftsman.getDNI());
		craftsman.assignOrder(order.getId());
		return true;
	}

}
