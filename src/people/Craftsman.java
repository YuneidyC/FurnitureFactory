package people;

import java.util.LinkedList;
import java.util.List;

import factory.Factory;
import factory.Order;
import furniture.Furniture;

public class Craftsman extends Employee {

	private List<Integer> listOfAssignedOrders = new LinkedList<Integer>();
	private List<Integer> finishedOrders = new LinkedList<Integer>();

	public Craftsman(Factory factory, String DNI, String name) {
		super(factory, DNI, name);
	}

	public List<Integer> getAssignedOrders() {
		return listOfAssignedOrders;
	}

	public void assignOrder(int order) {
		listOfAssignedOrders.add(order);
	}

	public List<Integer> getFinishesOrders() {
		return finishedOrders;
	}

	public void modifyFurnitureStatus() {
		boolean sameCraftsman = false;
		int IDOrder = -1;
		Furniture furniture = null;
		Order order = null;
		if (listOfAssignedOrders.isEmpty()) {
			System.out.println("You don't have assigned order.");
			return;
		}
		for (Integer IDOrders : listOfAssignedOrders) {
			System.out.println(getFactory().getOrder(IDOrders).toString());
		}
		IDOrder = super.chooseIdOrder();
		if (IDOrder == -1) {
			return;
		}
		order = getFactory().getOrder(IDOrder);
		if (order == null) {
			return;
		}
		sameCraftsman = getFactory().sameCraftsmanOrder(order, this);
		if (sameCraftsman == false) {
			return;
		}
		furniture = getFactory().showAndGetFurnitureOfThisOrder(order);
		if (furniture == null) {
			return;
		}
		getFactory().changeStatusToOrder(furniture);
	}

	public void finishedOrder() {
		int ID = -1;
		for (int idOrder : listOfAssignedOrders) {
			getFactory().printFinishedOrder(idOrder);
		}

		ID = super.chooseIdOrder();
		if (ID == -1) {
			return;
		}
		int allFine = getFactory().orderFinished(ID, this);
		if (allFine == -1) {
			return;
		}
		finishedOrders.add(ID);
		listOfAssignedOrders.remove(ID);
	}

	public void craftsmanHistory() {
		super.toString();
		String history = null;
		String contract = getFactory().instanceOf(this);
		System.out.println("Contract: " + contract + ".");
		if (listOfAssignedOrders.isEmpty()) {
			System.out.println("List orders assigned: ");
			for (Integer idOrder : listOfAssignedOrders) {
				Order order = getFactory().getOrder(idOrder);
				if (order != null) {
					history += order.getId() + ", ";
				}
			}
			System.out.println(history.substring(0, history.length() - 2));
		} else {
			System.out.println("This craftsman has no orders assigned.");
		}
		history = null;
		if (!finishedOrders.isEmpty()) {
			System.out.println("List orders finished: ");
			for (Integer idOrder : finishedOrders) {
				Order order = getFactory().getOrder(idOrder);
				if (order != null) {
					history += order.getId() + ", ";
				}
			}
			System.out.println(history.substring(0, history.length() - 2));
		} else {
			System.out.println("This craftsman has no completed orders.");
		}
	}

}
