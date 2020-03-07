package people;

import java.util.List;
import java.util.Random;

import factory.Order;

public class Boss extends Employee {

	public Boss(String id, String name) {
		super(id, name);
	}

	public boolean assignCraftsman(Order order, List<Craftsman> artisanList) {
		if (order == null || artisanList == null || artisanList.isEmpty()) {
			return false;
		}

		if (order.getEmployeeAssigned() != null) {
			return false;
		}

		// We don't want to always assign orders to the same artisan.
		int sizeCraftsmanList = artisanList.size();
		Random randomNumbers = new Random();
		int randomIndex = randomNumbers.nextInt(sizeCraftsmanList);
		Craftsman craftsman = artisanList.get(randomIndex);
		order.setEmployeeAssigned(craftsman.getId());
		craftsman.assignOrder(order);
		return true;
	}
}
