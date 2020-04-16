package people;

import java.util.LinkedList;
import java.util.List;

import factory.Factory;
import factory.Order;
import furniture.Furniture;

public class Craftsman extends Employee {

//	TODO: Change order by string
	private List<Integer> listOfAssignedOrders = new LinkedList<Integer>();
	private List<Integer> listFinishedOrder = new LinkedList<Integer>();

	public Craftsman(String id, String name) {
		super(id, name);
	}

	public List<Integer> getAssignedOrders() {
		return listOfAssignedOrders;
	}

	public void assignOrder(int order) {
		listOfAssignedOrders.add(order);
	}

	public void modifyFurnitureStatus() {
		boolean exist = false;
		boolean sameCraftsman = false;
		Order order = null;
		int idOrder = -1;
		Furniture furniture = null;
		idOrder = Factory.chooseIdOrder(this);
		order = Factory.checkExistOrder(idOrder);
		while (!exist) {
			sameCraftsman = Factory.sameCraftsmanOrder(order, this);
			if (sameCraftsman != false) {
				exist = true;
				break;
			}
			return;
		}
		furniture = Factory.showAndGetFurnitureOfThisOrder(order);
		Factory.changeStatusToOrder(furniture);
	}

	public void modifyData() {
		super.modifyData();
	}
}
