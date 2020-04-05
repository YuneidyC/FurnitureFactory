package people;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import factory.Factory;
import factory.Order;
import factory.Printer;
import furniture.Furniture;

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

	public static void modifyFurnitureStatus() {
		boolean exist = false;
		Craftsman craftsman = null;
		Order o = null;
		while (!exist) {
			craftsman = Factory.getCraftman();
			if (craftsman != null) {
				exist = true;
				break;
			} else {
				System.out.println("This Craftsman does not exist \n");
				continue;
			}
		}
		for (int idOrder : craftsman.assignedOrders) {
			exist = false;
			int answerConfirmOrder = -1;
			// TODO CHECK NULLS & CHANGE VARIABLE NAME
			while (!exist) {
				o = Factory.getOrder(idOrder);
				if (o != null) {
					exist = true;
					break;
				} else {
					System.out.println("This order does not exist \n");
					continue;
				}
			}
			// TODO BUCLE
			exist = false;
			Set<Integer> listIdFurniture = Factory.getIdFurniture(o);
			for (Integer idFurniture : listIdFurniture) {
				Furniture furnitureStatus = Factory.getFurniture(idFurniture);
				while (!exist) {
					Printer.statusFurnitureOrder();
					String str = Factory.sc.nextLine();
					try {
						answerConfirmOrder = Integer.parseInt(str);
					} catch (NumberFormatException exception) {
						System.out.println("This is not a number");
					}
					switch (answerConfirmOrder) {
					case 1:
						furnitureStatus.setStatus("Pending");
						exist = true;
						break;
					case 2:
						furnitureStatus.setStatus("In process");
						exist = true;
						break;
					case 3:
						furnitureStatus.setStatus("Stopped due to missing part");
						exist = true;
						break;
					case 4:
						furnitureStatus.setStatus("Stopped to customer confirmation");
						exist = true;
						break;
					case 5:
						furnitureStatus.setStatus("Test phase");
						exist = true;
						break;
					case 6:
						furnitureStatus.setStatus("Finished");
						exist = true;
						break;
					case 7:
						exist = true;
						break;
					default:
						System.out.println("This number is not in the range");
					case -1:
						continue;
					}
				}
			}
		}
	}

	public void modifyData() {
		super.modifyData();
	}
}
