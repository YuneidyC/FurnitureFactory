package people;

import java.util.List;
import java.util.Random;

import factory.Order;

public class Boss extends Employee {

	public Boss(String id, String name) {
		super(id, name);
	}

	public boolean assignCraftsman(Order order, List<Craftsman> craftsmans) {
		if (craftsmans == null || craftsmans.isEmpty()) {
			return false;
		}

		// We don't want to always assign orders to the same artisan.
		int sizeCraftsmanList = craftsmans.size();
		Random randomNumbers = new Random();
		int randomIndex = randomNumbers.nextInt(sizeCraftsmanList);
		Craftsman craftsman = craftsmans.get(randomIndex);
		assignCraftsman(order, craftsman);
		return true;
	}

	public boolean assignCraftsman(Order order, Craftsman craftsman) {
		if (order == null || craftsman == null) {
			return false;
		}
		if (order.getEmployeeAssigned() != null) {
			return false;
		}
		order.setEmployeeAssigned(craftsman.getId());
		craftsman.assignOrder(order);
		return true;
	}
}
