package factory;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import furniture.BedroomTable;
import furniture.CristalCoffeeTable;
import furniture.DiningTable;
import furniture.FoldingChair;
import furniture.Furniture;
import furniture.KitchenChair;
import furniture.OfficeChairWithWheels;
import furniture.OfficeChairWithoutWheels;
import furniture.WoodenCoffeeTable;
import people.Boss;
import people.Client;
import people.CompanyCustomer;
import people.ContractInStaff;
import people.Craftsman;
import people.HourlyContract;
import people.Person;
import people.PrivateCustomer;
import people.Salesman;

public class Factory {

	public Scanner sc;
	public Boss boss = null;
	private List<Person> people = new LinkedList<Person>();
	private List<String> ListDNICraftsman = new LinkedList<String>();
	private List<Furniture> furnitures = new LinkedList<Furniture>();
	private List<Order> orderList = new LinkedList<Order>();
	private Map<Boolean, ArrayList<Order>> finishedOrders = new HashMap<Boolean, ArrayList<Order>>();

	public List<Person> getPeople() {
		return people;
	}

	public List<String> getCraftsmans() {
		return ListDNICraftsman;
	}

	public List<Furniture> getFurnitures() {
		return furnitures;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public Map<Boolean, ArrayList<Order>> getFinishedOrders() {
		return finishedOrders;
	}

	/**
	 * This method takes care of adding people to the list, in case in case the
	 * person is a craftsman, the ID is also added to the listIDCraftsman list.
	 */
	public void addPerson(Person person) {
		if (person instanceof Craftsman) {
			people.add(person);
			ListDNICraftsman.add(person.getDNI());
		} else {
			people.add(person);
		}
	}

	public String instanceOf(Craftsman craftsman) {
		if (craftsman instanceof ContractInStaff) {
			return "In staff";
		}
		if (craftsman instanceof HourlyContract) {
			return "Hourly";
		}
		return null;
	}

	private void missingPieces() {
		String missing = null;
		if (orderList.isEmpty()) {
			System.out.println("There are no factory orders.");
			return;
		}
		for (Order order : orderList) {
			if (order.getIdsAndItemsFurniture().isEmpty()) {
				System.out.println("This order does not have furniture.");
				continue;
			}
			for (Integer IDFurniture : order.getIdsAndItemsFurniture().keySet()) {
				Furniture furniture = getFurniture(IDFurniture);
				if (furniture == null) {
					continue;
				}
				if (furniture.getMissingPieces().isEmpty()) {
					System.out.println("This furniture does not have missing pieces.");
					continue;
				}
				System.out.println("Missing pieces for this furniture " + furniture.getId() + ": ");
				for (Piece pieces : furniture.getMissingPieces()) {
					missing += pieces.toString() + ", ";
				}
				System.out.println(missing.substring(0, missing.length() - 2));
			}
		}
	}

	private void printOrdersProcessedByEachCraftsman() {
		if (people.isEmpty()) {
			System.out.println("There is no craftsman in the factory.");
			return;
		}
		for (String craftsmanDNI : ListDNICraftsman) {
			for (Person person : people) {
				if (person.getDNI().equals(craftsmanDNI)) {
					Craftsman craftsman = (Craftsman) person;
					List<Integer> assignedOrders = craftsman.getAssignedOrders();
					int sizeAssignedOrders = assignedOrders.size();
					if (craftsman.getAssignedOrders().isEmpty()) {
						System.out.println("This craftsman does not have any furniture in process.");
						continue;
					}
					String str = "Order";
					if (sizeAssignedOrders > 1) {
						str += "s";
					}
					str += " processed by this craftsman: ";
					System.out.println(str);
					for (Integer idOrder : craftsman.getAssignedOrders()) {
						Order order = getOrder(idOrder);
						if (order == null) {
							continue;
						}
						for (Integer idFurniture : order.getIdsAndItemsFurniture().keySet()) {
							Furniture furniture = getFurniture(idFurniture);
							if (furniture == null) {
								continue;
							}
							int lastStatus = furniture.getStatusHistory().size() - 1;
							if ((furniture.getStatusHistory().get(lastStatus).equals("In process."))) {
								System.out.println(order.getId() + " " + furniture.getId());
							}
						}
					}
				}
			}
		}
	}

	public void onClientDNIUpdate(String DNI, String DNIAct) {
		for (Order order : orderList) {
			if (order.getDNIClient().equals(DNI)) {
				order.setDNIClient(DNIAct);
			}
		}
	}

	private void requestCompanyCustomerToConfirm(Client client) {
		List<Integer> orders = ((CompanyCustomer) client).getOrders();
		if (orders.isEmpty()) {
			System.out.println("This client does not have orders.");
			return;
		}
		System.out.println("Request confirm order to this client: ");
		for (Integer IDOrder : orders) {
			Order order = getOrder(IDOrder);
			if (order == null) {
				continue;
			}
			if (order.getPendingCustomerConfirmation() == false) {
				System.out.println(((CompanyCustomer) client).toString());
			}
		}
	}

	private void requestPrivateCustomerToConfirm(Client client) {
		List<Integer> orders = ((PrivateCustomer) client).getOrders();
		if (orders.isEmpty()) {
			System.out.println("This client does not have orders.");
			return;
		}
		System.out.println("Request confirm order to this client: ");
		for (Integer IDOrder : orders) {
			Order order = getOrder(IDOrder);
			if (order == null) {
				continue;
			}
			if (order.getPendingCustomerConfirmation() == false) {
				System.out.println(((PrivateCustomer) client).toString());
			}
		}
	}

	private void requestClientToConfirm() {
		for (Person person : people) {
			if (person instanceof CompanyCustomer) {
				requestCompanyCustomerToConfirm((Client) person);
			} else if (person instanceof PrivateCustomer) {
				requestPrivateCustomerToConfirm((Client) person);
			}
		}
	}

	private void reportInProcess() {
		/*
		 * We could stop iterating when we find the one that interests us, but in this
		 * way we would allow an artisan to have several orders in process.
		 */
		if (ListDNICraftsman.isEmpty()) {
			System.out.println("There are not craftsman in the factory.");
			return;
		}
		for (String craftsmanDNI : ListDNICraftsman) {
			Craftsman craftsman = getCraftsman(craftsmanDNI);
			if (craftsman == null) {
				System.out.println("This craftsman does not exist.");
				return;
			}
			for (Integer idOrder : craftsman.getAssignedOrders()) {
				Order order = getOrder(idOrder);
				if (order != null) {
					for (Integer idFurniture : order.getIdsAndItemsFurniture().keySet()) {
						Furniture furniture = getFurniture(idFurniture);
						if (furniture != null) {
							int sizeStatus = (furniture.getStatusHistory()).size();
							String statusFurniture = furniture.getStatusHistory().get(sizeStatus - 1);
							if (statusFurniture == "In process.") {
								System.out.println("The order in process for this craftsman: ");
								System.out.println(idFurniture + " " + statusFurniture);
							}
						}
						continue;
					}
				}
				continue;
			}
		}
	}

	public Craftsman getCraftsman(String DNI) {
		Craftsman craftsman = null;
		for (Person person : people) {
			if (person.getDNI().equals(DNI)) {
				craftsman = (Craftsman) person;
				return craftsman;
			}
		}
		return craftsman;
	}

	private void switchCraftsmanHistory() {
		boolean mustExit = false;
		int integer = -1;
		while (!mustExit) {
			Printer.craftsmanHistory();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (integer) {
			case 1:
				showAndGetCraftsman();
				break;
			case 2:
				allCraftsmanHistory();
				break;
			case 3: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
	}

	private void showAndGetCraftsman() {
		Craftsman craftsman = null;
		if (ListDNICraftsman.isEmpty()) {
			System.out.println("There are no craftsmans in the factory.");
			return;
		}
		for (String craftsmanDNI : ListDNICraftsman) {
			craftsman = getCraftsman(craftsmanDNI);
			if (craftsman == null) {
				return;
			}
			System.out.println(craftsman.toString());
		}
		craftsman.craftsmanHistory();
	}

	private void allCraftsmanHistory() {
		boolean exist = false;
		Craftsman craftsman = null;
		if (people.isEmpty()) {
			System.out.println("There are no people in the factory.");
			return;
		}
		for (String craftsmanDNI : ListDNICraftsman) {
			craftsman = getCraftsman(craftsmanDNI);
			if (craftsman == null) {
				System.out.println("This craftsman does not exist.");
				continue;
			}
			craftsman.craftsmanHistory();
		}
		if (exist == false) {
			System.out.println("There are no craftsman in the factory.");
		}
	}

	private void switchFurnitureHistory() {
		boolean mustExit = false;
		int integer = -1;
		while (!mustExit) {
			Printer.furnitureHistory();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (integer) {
			case 1:
				showAndGetFurniture();
				break;
			case 2:
				allFurnitureHistory();
				break;
			case 3: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
	}

	private void showAndGetFurniture() {
		int idFurniture = -1;
		for (Furniture furniture : furnitures) {
			System.out.println(furniture.getId());
		}
		System.out.println("Insert id furniture: ");
		if (!sc.hasNextLine()) {
			System.out.println("Nothing has been inserted.");
			return;
		}
		String str = sc.nextLine();
		try {
			idFurniture = Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return;
		}
		if (idFurniture == -1) {
			System.out.println("This id furniture does not exist.");
			return;
		}
		Furniture furniture = getFurniture(idFurniture);
		if (furniture == null) {
			System.out.println("This furniture does not exist.");
			return;
		}
		furniture.furnitureHistory();
	}

	private void allFurnitureHistory() {
		for (Furniture furniture : furnitures) {
			furniture.furnitureHistory();
		}
	}

	private boolean allFurnitureDone(Order order) {
		int counter = 0;
		int furnituresOrder = order.getIdsAndItemsFurniture().size();
		for (Integer idFurniture : order.getIdsAndItemsFurniture().keySet()) {
			for (Furniture furniture : furnitures) {
				if (furniture.getId() == idFurniture) {
					List<String> statusHistory = furniture.getStatusHistory();
					String lastStatus = statusHistory.get(statusHistory.size() - 1);
					if (lastStatus.equals("Finished.")) {
						counter++;
					}
				}
			}
		}
		if (counter == furnituresOrder) {
			return true;
		}
		return false;
	}

	public void printFinishedOrder(int idOrder) {
		boolean furnitureFinished = false;
		System.out.println("Order finished: ");
		Order order = getOrder(idOrder);
		if (order == null) {
			return;
		}
		furnitureFinished = allFurnitureDone(order);
		if (furnitureFinished == true) {
			System.out.println(order.toString());
		}
	}

	public boolean printIdsOrderHasNotBeenNotified() {
		if (finishedOrders.get(false).isEmpty()) {
			System.out.println("There is no order without notifying.");
			return false;
		}
		for (Order order : finishedOrders.get(false)) {
			System.out.println(order.toString() + " " + order.getDNIClient() + " Not notified.");
		}
		return true;
	}

	public int getIDsFurnitureOrder(Order order) {
		if (orderList.contains(order)) {
			for (Integer ID : order.getIdsAndItemsFurniture().keySet()) {
				if (ID == null) {
					continue;
				}
				Furniture furniture = getFurniture(ID);
				if (furniture == null) {
					continue;
				}
				List<String> status = furniture.getStatusHistory();
				if (status.isEmpty()) {
					continue;
				}
				if (!status.get(status.size() - 1).equals("Finished.")) {
					return furniture.getId();
				}
			}
		}
		return -1;
	}

	public void notifyCustomer(int idOrder) {
		for (Order o : finishedOrders.get(false)) {
			if (idOrder == o.getId()) {
				finishedOrders.get(true).add(o);
				finishedOrders.get(false).remove(o);
				System.out.println("The customer has been notified.");
				return;
			}
		}
		if (!finishedOrders.get(true).isEmpty()) {
			for (Order o : finishedOrders.get(true)) {
				if (idOrder == o.getId()) {
					System.out.println("This order has already been notified.");
					return;
				}
			}
		}
		Order order = getOrder(idOrder);
		if (order == null) {
			return;
		}
		System.out.println("This order has not finished.");
	}

	public int orderFinished(int idOrder, Craftsman craftsman) {
		Order toDelete = null;
		int allFine = 0;
		boolean finished = false;
		Order order = getOrder(idOrder);
		if (order == null) {
			return -1;
		}
		if (!order.getEmployeeAssigned().equals(craftsman.getDNI())) {
			System.out.println("The order you have selected is not from this craftsman.");
			return -1;
		}
		finished = allFurnitureDone(order);
		if (finished == false) {
			System.out.println("There is unfinished furniture.");
			return -1;
		}
		finishedOrders.get(false).add(order);
		toDelete = order;
		if (toDelete != null) {
			orderList.remove(toDelete);
		}
		return allFine;
	}

	public Furniture showAndGetFurnitureOfThisOrder(Order order) {
		int idFurniture = -1;
		Furniture furniture = null;
		System.out.println("ID furniture: ");
		for (Integer idFurni : order.getIdsAndItemsFurniture().keySet()) {
			for (Furniture furnitureSelec : furnitures) {
				if (idFurni == furnitureSelec.getId()) {
					System.out.println(furnitureSelec.toString());
					break;
				}
			}
		}
		System.out.println("Choose the furniture ID: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		String str = sc.nextLine();
		try {
			idFurniture = Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return null;
		}
		furniture = getFurniture(idFurniture);
		if (furniture == null) {
			return null;
		}
		return furniture;
	}

	public void changeStatusToOrder(Furniture furniture) {
		int furnitureStatus = -1;
		boolean exit = false;
		List<String> statusHistory = furniture.getStatusHistory();
		String lastStatus = statusHistory.get(statusHistory.size() - 1);
		if (lastStatus.equals("Finished.")) {
			System.out.println("You cannot change the status of a finished furniture.");
			return;
		}
		while (!exit) {
			Printer.statusFurnitureOrder();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			try {
				furnitureStatus = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (furnitureStatus) {
			case 1:
				furniture.addStatus(1, "Pending");
				exit = true;
				break;
			case 2:
				furniture.addStatus(2, "In process");
				exit = true;
				break;
			case 3:
				furniture.addStatus(3, "Stopped due to missing part");
				exit = true;
				break;
			case 4:
				furniture.addStatus(4, "Stopped to customer confirmation");
				exit = true;
				break;
			case 5:
				furniture.addStatus(5, "Test phase");
				exit = true;
				break;
			case 6:
				furniture.addStatus(6, "Finished");
				exit = true;
				break;
			case 7: // Exit
				exit = true;
				return;
			default:
				Printer.isNotValidOption();
			}
		}
	}

	public Piece askForPieceDetails() {
		Piece piece = null;
		System.out.println("Insert part reference: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		String reference = sc.nextLine();
		if (reference.isEmpty()) {
			return null;
		}
		piece = new Piece(reference);
		return piece;
	}

	public int unassignedOrders() {
		int id = -1;
		String str = null;
		if (orderList.isEmpty()) {
			System.out.println("There are no orders in the factory.");
			return -1;
		}
		for (Order orderList : orderList) {
			if (orderList.getEmployeeAssigned() == null) {
				System.out.println(orderList.toString() + " unassigned");
			}
		}
		System.out.println("Insert id order: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return -1;
		}
		str = sc.nextLine();
		try {
			id = Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return -1;
		}
		return id;
	}

	public Boss getBoss() {
		Boss boss = null;
		for (Person person : people) {
			if (person instanceof Boss) {
				boss = (Boss) person;
			}
		}
		return boss;
	}

	public Craftsman assignOrderWithIDOrRandom() {
		boolean mustExit = false;
		Craftsman craftsman = null;
		int assign = -1;
		Printer.assignOrderWithDNIOrRandom();
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		String str = sc.nextLine();
		try {
			assign = Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return null;
		}
		while (!mustExit) {
			switch (assign) {
			case 1:
				craftsman = assignCraftsman();
				if (craftsman == null) {
					return null;
				}
				mustExit = true;
				break;
			case 2:
				craftsman = assignRandomCraftsman();
				if (craftsman == null) {
					return null;
				}
				mustExit = true;
				break;
			case 3:
				craftsman = listAndCatchCraftsman();
				if (craftsman == null) {
					return null;
				}
				mustExit = true;
				break;
			case 4: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
		return craftsman;
	}

	private Craftsman assignRandomCraftsman() {
		if (ListDNICraftsman == null || ListDNICraftsman.isEmpty()) {
			System.out.println("There are no people in the factory.");
			return null;
		}
		// We don't want to always assign orders to the same artisan.
		int sizeCraftsmanList = ListDNICraftsman.size();
		Random randomNumbers = new Random();
		int randomIndex = randomNumbers.nextInt(sizeCraftsmanList);
		String craftsmanDNI = ListDNICraftsman.get(randomIndex);
		Craftsman craftsman = getCraftsman(craftsmanDNI);
		if (craftsman == null) {
			return null;
		}
		return craftsman;
	}

	private Craftsman listAndCatchCraftsman() {
		String DNI = null;
		if (ListDNICraftsman.isEmpty()) {
			System.out.println("There are no craftsman in the factory.");
			return null;
		}
		System.out.println("List of craftsman: ");
		for (String craftsmanDNI : ListDNICraftsman) {
			DNI += craftsmanDNI + "\n";
		}
		System.out.println(DNI.substring(0, DNI.length() - 1));
		System.out.println("Insert craftsman DNI: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		DNI = sc.nextLine();
		if (DNI.isEmpty()) {
			System.out.println("The DNI craftsman has not been inserted.");
			return null;
		}
		Craftsman craftsman = getCraftsman(DNI);
		if (craftsman != null) {
			return craftsman;
		}
		return null;
	}

	private Craftsman assignCraftsman() {
		Craftsman craftsman = getCraftsman();
		if (craftsman == null) {
			System.out.println("This craftsman does not exist or the DNI was not inserted.");
			return null;
		}
		return craftsman;
	}

	private ArrayList<Integer> printerPendingClientOrder(String DNIClient) {
		boolean anyClientOrder = false;
		ArrayList<Integer> idsOrder = new ArrayList<Integer>();
		if (orderList.isEmpty()) {
			System.out.println("There is no registered order.");
			return null;
		}
		for (Order order : orderList) {
			if (order.getDNIClient().equals(DNIClient)) {
				if (order.getPendingCustomerConfirmation() == false) {
					System.out.println(order.toString());
					idsOrder.add(order.getId());
					anyClientOrder = true;
				}
			}
		}
		if (!anyClientOrder) {
			System.out.println("You don't have any order to confirm.");
			return null;
		}
		return idsOrder;
	}

	public void confirmOrder(String DNIClient) {
		int selectIDOrder = -1;
		ArrayList<Integer> idsOrder = printerPendingClientOrder(DNIClient);
		if (idsOrder == null) {
			return;
		}
		System.out.println("Insert ID of the order you want to confirm: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return;
		}
		String str = sc.nextLine();
		try {
			selectIDOrder = Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return;
		}
		for (int idOrder : idsOrder) {
			if (idOrder == selectIDOrder) {
				int confirm = confirmOrderClient(DNIClient, selectIDOrder);
				if (confirm == -1) {
					return;
				}
				System.out.println("Order " + idOrder + " has been confirmed");
				return;
			}
		}
		System.out.println("The order you have selected is not in the list of your orders.");
	}

	private int confirmOrderClient(String DNIClient, int idOrder) {
		int IDOrder = -1;
		if (orderList.isEmpty()) {
			System.out.println("There is no registered order.");
			return -1;
		}
		for (Order order : orderList) {
			if (order.getId() == idOrder) {
				if (order.getDNIClient().equals(DNIClient)) {
					Printer.confirmOrder();
					if (!sc.hasNextLine()) {
						Printer.nothingHasBeenInserted();
						return -1;
					}
					String str = sc.nextLine();
					int answer = 0;
					try {
						answer = Integer.parseInt(str);
					} catch (NumberFormatException exception) {
						Printer.thisIsNotANumber();
						return -1;
					}
					if (answer != 1) {
						System.out.println("Order " + IDOrder + " has not been confirmed.");
						return -1;
					}
					order.setPendingCustomerConfirmation(true);
					break;
				}
			}
		}
		return 0;
	}

	private void ShowOrderPrice() {
		int idOrder = -1;
		System.out.println("Insert ID order: ");
		String id = sc.nextLine();
		try {
			idOrder = Integer.parseInt(id);
		} catch (Exception e) {
			Printer.thisIsNotANumber();
			return;
		}
		if (idOrder == -1) {
			System.out.println("You did not insert ID order.");
			return;
		}
		Order order = getOrder(idOrder);
		if (order == null) {
			return;
		}
		System.out.println(order.toString() + "Total price: " + order.getTotalPrice());
	}

	public Client getClient(String DNI) {
		for (Person person : people) {
			if ((person instanceof Client) && person.getDNI().equals(DNI)) {
				return (Client) person;
			}
		}
		System.out.println("This client does not exist.");
		return null;
	}

	private Client getClient() {
		String DNI;
		System.out.println("Insert client DNI/PASSPORT: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		DNI = sc.nextLine();
		for (Person person : people) {
			if ((person instanceof Client) && person.getDNI().equals(DNI)) {
				return (Client) person;
			}
		}
		System.out.println("This client does not exist.");
		return null;
	}

	public Order getOrder(int idOrder) {
		if (orderList.isEmpty()) {
			System.out.println("There is no order in the factory.");
			return null;
		}
		for (Order order : orderList) {
			if (order.getId() == idOrder) {
				return order;
			}
		}
		System.out.println("This order does not exist.");
		return null;
	}

	public Set<Integer> getIdFurniture(Order order) {
		return order.getIdsAndItemsFurniture().keySet();
	}

	public Furniture getFurniture(int idFurniture) {
		for (Furniture furniture : furnitures) {
			if (furniture.getId() == idFurniture) {
				return furniture;
			}
		}
		System.out.println("This furniture does not exist.");
		return null;
	}

	private Craftsman getCraftsman() {
		Craftsman craftsman = null;
		System.out.println("Insert craftsman DNI/PASSPORT: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		String DNI = sc.nextLine();
		if (DNI.isEmpty()) {
			System.out.println("The DNI craftsman has not been inserted.");
			return null;
		}
		if (!ListDNICraftsman.contains(DNI)) {
			System.out.println("This craftsman does not exist.");
			return null;
		}
		for (String craftsmanDNI : ListDNICraftsman) {
			if (craftsmanDNI.equals(DNI)) {
				craftsman = getCraftsman(craftsmanDNI);
				break;
			}
		}
		return craftsman;
	}

	private Salesman getSalesman() {
		String DNI;
		System.out.println("Insert salesman DNI/PASSPORT: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		DNI = sc.nextLine();
		for (Person person : people) {
			if ((person instanceof Salesman) && person.getDNI().equals(DNI)) {
				return (Salesman) person;
			}
		}
		System.out.println("This salesman does not exist.");
		return null;
	}

	public int askHowManyItems() {
		int totalFurniture = 0;
		System.out.println("How many do you want?: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return -1;
		}
		String str = sc.nextLine();
		try {
			totalFurniture = Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return -1;
		}
		return totalFurniture;
	}

	private int calculateTotalPrice(HashMap<Integer, Integer> order) {
		int priceFurniture;
		int items;
		int total = 0;
		for (Map.Entry<Integer, Integer> pair : order.entrySet()) {
			for (Furniture furnitureId : furnitures) {
				if (pair.getKey() == furnitureId.getId()) {
					priceFurniture = furnitureId.getPrice();
					items = pair.getValue();
					total += (priceFurniture * items);
				}
			}
		}
		return total;
	}

	/**
	 * We take the model and the price of the furniture
	 */
	private Entry<String, Integer> modelAndPriceFurniture() {
		int price = -1;
		Entry<String, Integer> newFurniture = null;
		System.out.println("Insert furniture model: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		String furnitureModel = sc.nextLine();
		System.out.println("Insert price: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		String str = sc.nextLine();
		try {
			price = Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return null;
		}
		if (price == 0) {
			System.out.println("This price is not valid for a piece of furniture.");
			return null;
		}
		Entry<String, Integer> newFurnitureWithModelAndPrice = new SimpleEntry<String, Integer>(furnitureModel, price);
		newFurniture = newFurnitureWithModelAndPrice;
		return newFurniture;
	}

	/**
	 * Create a new chair and adds it to the internal list
	 */
	public int createChairs() {
		int typeChair = -1;
		int furnitureID = -1;
		boolean mustExit = false;
		Entry<String, Integer> chair = null;
		String modelOfChair;
		String features;
		int price;
		while (!mustExit) {
			Printer.chairsTypes();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return -1;
			}
			String str = sc.nextLine();
			try {
				typeChair = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return -1;
			}
			switch (typeChair) {
			case 1:
				chair = modelAndPriceFurniture();
				if (chair == null) {
					System.out.println("Could not create a chair.");
					return -1;
				}
				modelOfChair = chair.getKey();
				price = chair.getValue();
				features = addOrNotFeatures();
				if (features == null) {
					System.out.println("This is not a features.");
					return -1;
				}
				Furniture foldingChair = new FoldingChair(this, modelOfChair, price, features);
				furnitureID = foldingChair.getId();
				furnitures.add(foldingChair);
				mustExit = true;
				break;
			case 2:
				chair = modelAndPriceFurniture();
				if (chair == null) {
					System.out.println("Could not create a chair.");
					return -1;
				}
				modelOfChair = chair.getKey();
				price = chair.getValue();
				features = addOrNotFeatures();
				if (features == null) {
					System.out.println("This is not a features.");
					return -1;
				}
				Furniture kitchenChair = new KitchenChair(this, modelOfChair, price, features);
				furnitureID = kitchenChair.getId();
				furnitures.add(kitchenChair);
				mustExit = true;
				break;
			case 3:
				furnitureID = createOfficeChair();
				mustExit = true;
				break;
			case 4: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
		return furnitureID;
	}

	/**
	 * Create a new OfficeChair and adds it to the internal list
	 */
	public int createOfficeChair() {
		int typeOfficeChair = -1;
		int furnitureID = -1;
		boolean mustExit = false;
		Entry<String, Integer> chair = null;
		String model;
		String features;
		int price;
		while (!mustExit) {
			Printer.officeChairTypes();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return -1;
			}
			String str = sc.nextLine();
			try {
				typeOfficeChair = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return -1;
			}
			switch (typeOfficeChair) {
			case 1:
				chair = modelAndPriceFurniture();
				if (chair == null) {
					System.out.println("Could not create a chair.");
					return -1;
				}
				model = chair.getKey();
				price = chair.getValue();
				features = addOrNotFeatures();
				if (features == null) {
					System.out.println("This is not a features.");
					return -1;
				}
				Furniture officeChairWithWheels = new OfficeChairWithWheels(this, model, price, features);
				furnitureID = officeChairWithWheels.getId();
				furnitures.add(officeChairWithWheels);
				mustExit = true;
				break;
			case 2:
				chair = modelAndPriceFurniture();
				if (chair == null) {
					System.out.println("Could not create a chair.");
					return -1;
				}
				model = chair.getKey();
				price = chair.getValue();
				features = addOrNotFeatures();
				if (features == null) {
					System.out.println("This is not a features.");
					return -1;
				}
				Furniture officeChairWithoutWheels = new OfficeChairWithoutWheels(this, model, price, features);
				furnitureID = officeChairWithoutWheels.getId();
				furnitures.add(officeChairWithoutWheels);
				mustExit = true;
				break;
			case 3: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
		return furnitureID;
	}

	/**
	 * Create a new Tables and adds it to the internal list
	 */
	public int createTables() {
		Entry<String, Integer> table = null;
		String model;
		String features;
		int furnitureID = -1;
		int price;
		int typeTable = -1;
		boolean mustExit = false;
		while (!mustExit) {
			Printer.tableTypes();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return -1;
			}
			String str = sc.nextLine();
			try {
				typeTable = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return -1;
			}
			typeTable = Integer.parseInt(str);
			switch (typeTable) {
			case 1:
				table = modelAndPriceFurniture();
				if (table == null) {
					System.out.println("Could not create a table.");
					return -1;
				}
				model = table.getKey();
				price = table.getValue();
				features = addOrNotFeatures();
				if (features == null) {
					System.out.println("This is not a features.");
					return -1;
				}
				Furniture bedroomTable = new BedroomTable(this, model, price, features);
				furnitureID = bedroomTable.getId();
				furnitures.add(bedroomTable);
				mustExit = true;
				break;
			case 2:
				furnitureID = createCoffeeTable();
				mustExit = true;
				break;
			case 3:
				table = modelAndPriceFurniture();
				if (table == null) {
					System.out.println("Could not create a table.");
					return -1;
				}
				model = table.getKey();
				price = table.getValue();
				features = addOrNotFeatures();
				if (features == null) {
					System.out.println("This is not a features.");
					return -1;
				}
				Furniture diningTable = new DiningTable(this, model, price, features);
				furnitureID = diningTable.getId();
				furnitures.add(diningTable);
				mustExit = true;
				break;
			case 4: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
		return furnitureID;
	}

	/**
	 * Create a new CoffeeTable and adds it to the internal list
	 */
	public int createCoffeeTable() {
		Entry<String, Integer> table = null;
		String model;
		String features;
		int price;
		int furnitureID = -1;
		int coffeeTable = -1;
		boolean mustExit = false;
		while (!mustExit) {
			Printer.coffeeTableTypes();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return -1;
			}
			String str = sc.nextLine();
			try {
				coffeeTable = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return -1;
			}
			switch (coffeeTable) {
			case 1:
				table = modelAndPriceFurniture();
				if (table == null) {
					System.out.println("Could not create a table.");
					return -1;
				}
				model = table.getKey();
				price = table.getValue();
				features = addOrNotFeatures();
				if (features == null) {
					System.out.println("This is not a features.");
					return -1;
				}
				Furniture cristalCoffeeTable = new CristalCoffeeTable(this, model, price, features);
				furnitureID = cristalCoffeeTable.getId();
				furnitures.add(cristalCoffeeTable);
				mustExit = true;
				break;
			case 2:
				table = modelAndPriceFurniture();
				if (table == null) {
					System.out.println("Could not create a table.");
					return -1;
				}
				model = table.getKey();
				price = table.getValue();
				features = addOrNotFeatures();
				if (features == null) {
					System.out.println("This is not a features.");
					return -1;
				}
				Furniture woodenCoffeeTable = new WoodenCoffeeTable(this, model, price, features);
				furnitureID = woodenCoffeeTable.getId();
				furnitures.add(woodenCoffeeTable);
				mustExit = true;
				break;
			case 3: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
		return furnitureID;
	}

	public boolean addBoss() {
		String name = "";
		String DNI = "";
		System.out.println("Insert boss data: ");
		System.out.println("Name: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return false;
		}
		name = sc.nextLine();
		if (name.isEmpty()) {
			System.out.println("Boss name not inserted.");
			return false;
		}
		char[] nameC = name.toCharArray();
		for (char nameChar : nameC) {
			if (nameChar >= '0' && nameChar <= '9') {
				System.out.println("The name cannot have numbers.");
				return false;
			}
		}
		System.out.println("DNI/PASSPORT: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return false;
		}
		DNI = sc.nextLine();
		if (DNI.isEmpty()) {
			System.out.println("Boss DNI/PASSPORT not inserted.");
			return false;
		}
		boss = new Boss(this, DNI, name);
		addPerson(boss);
		return true;
	}

	public void modifyPeopleData() {
		boolean exist = false;
		System.out.println("Insert DNI/PASSPORT: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return;
		}
		String DNI = sc.nextLine();
		if (DNI.isEmpty()) {
			System.out.println("The DNI has not been inserted.");
			return;
		}
		for (Person person : people) {
			if (person.getDNI().equals(DNI)) {
				person.modifyData();
				exist = true;
				break;
			}
		}
		if (!exist) {
			System.out.println("This DNI is not registered.");
		}
	}

	public void addSalesman() {
		System.out.println("Insert name: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return;
		}
		String name = sc.nextLine();
		if (name.isEmpty()) {
			System.out.println("Salesman name not inserted.");
			return;
		}
		char[] nameC = name.toCharArray();
		for (char nameChar : nameC) {
			if (nameChar >= '0' && nameChar <= '9') {
				System.out.println("The name cannot have numbers.");
				return;
			}
		}
		System.out.println("Insert DNI/PASSPORT: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return;
		}
		String dni = sc.nextLine();
		if (dni.isEmpty()) {
			System.out.println("Salesman DNI/PASSPORT not inserted.");
			return;
		}
		for (Person person : people) {
			if (person.getDNI().equals(dni)) {
				System.out.println("This person already exists.");
				return;
			}
		}
		Salesman salesman = new Salesman(this, dni, name);
		addPerson(salesman);
	}

	public void addCraftman() {
		int contract = -1;
		boolean mustExit = false;
		System.out.println("Insert name: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return;
		}
		String name = sc.nextLine();
		if (name.isEmpty()) {
			System.out.println("Craftman name not inserted.");
			return;
		}
		char[] nameC = name.toCharArray();
		for (char nameChar : nameC) {
			if (nameChar >= '0' && nameChar <= '9') {
				System.out.println("The name cannot have numbers.");
				return;
			}
		}
		System.out.println("Insert DNI/PASSPORT: ");
		if (!sc.hasNextLine()) {
			System.out.println("Nothing has been inserted.");
			return;
		}
		String dni = sc.nextLine();
		if (dni.isEmpty()) {
			System.out.println("Craftman DNI/PASSPORT not inserted.");
			return;
		}
		for (Person person : people) {
			if (person.getDNI().equals(dni)) {
				System.out.println("This person already exists.");
				return;
			}
		}
		while (!mustExit) {
			Printer.menuContractCraftsman();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			try {
				contract = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (contract) {
			case 1:
				Craftsman craftsmanStaff = new ContractInStaff(this, dni, name);
				addPerson(craftsmanStaff);
				mustExit = true;
				break;
			case 2:
				Craftsman craftsmanHourlyContract = new HourlyContract(this, dni, name);
				addPerson(craftsmanHourlyContract);
				mustExit = true;
				break;
			case 3: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
	}

	public int createNewFurniture() {
		boolean mustExit = false;
		int furnitureID = -1;
		int integer = -1;
		while (!mustExit) {
			Printer.furnitureTypes();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return -1;
			}
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return furnitureID;
			}
			switch (integer) {
			case 1:
				furnitureID = createChairs();
				mustExit = true;
				break;
			case 2:
				furnitureID = createTables();
				mustExit = true;
				break;
			case 3: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
				break;
			}
		}
		return furnitureID;
	}

	public void modifyFurnitureData() {
		Furniture furniture = null;
		boolean modifyPrice;
		int integer = -1;
		int price = 0;
		System.out.println("Insert ID furniture: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return;
		}
		String ID = sc.nextLine();
		try {
			integer = Integer.parseInt(ID);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return;
		}
		furniture = getFurniture(integer);
		if (furniture == null) {
			return;
		}
		// Get current price
		for (Order order : orderList) {
			for (Integer furnitureID : order.getIdsAndItemsFurniture().keySet()) {
				if (furnitureID == furniture.getId()) {
					int items = order.getIdsAndItemsFurniture().get(furnitureID);
					price = furniture.getPrice() * items;
				}
			}
		}
		modifyPrice = furniture.modifyData();
		if (modifyPrice) {
			// Recalculate total price
			for (Order order : orderList) {
				for (Integer furnitureID : order.getIdsAndItemsFurniture().keySet()) {
					if (furnitureID == furniture.getId()) {
						int totalPrice = order.getTotalPrice() - price;
						totalPrice += furniture.getPrice() * order.getIdsAndItemsFurniture().get(furnitureID);
						order.setTotalPrice(totalPrice);
					}
				}
			}
		}
	}

	private boolean existClient(String DNI) {
		for (Person person : people) {
			if (person instanceof Client && person.getDNI().equals(DNI)) {
				return true;
			}
		}
		return false;
	}

	private String getName() {
		System.out.println("Insert Name: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		String name = sc.nextLine();
		if (name.isEmpty()) {
			System.out.println("Client name not inserted.");
			return null;
		}
		char[] nameC = name.toCharArray();
		for (char nameChar : nameC) {
			if (nameChar >= '0' && nameChar <= '9') {
				System.out.println("The name cannot have numbers.");
				return null;
			}
		}
		return name;
	}

	public Client createOrGetClient() {
		int phoneNumber = -1;
		int integer = -1;
		boolean existClient = false;
		boolean mustExit = false;
		Client client = null;
		String name = null;
		String DNI = getDNI();
		if (DNI == null) {
			return null;
		}
		existClient = existClient(DNI);
		if (!existClient) {
			name = getName();
			if (name == null) {
				return null;
			}
			phoneNumber = getPhoneNumber();
			if (phoneNumber == -1) {
				phoneNumber = getPhoneNumber();
			}
		} else {
			client = getClient(DNI);
			return client;
		}
		while (!mustExit) {
			Printer.clientTypes();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return null;
			}
			String type = sc.nextLine();
			try {
				integer = Integer.parseInt(type);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return null;
			}
			switch (integer) {
			case 1:
				PrivateCustomer privateCustomer = new PrivateCustomer(this, DNI, name, phoneNumber);
				client = privateCustomer;
				addPerson(privateCustomer);
				mustExit = true;
				break;
			case 2:
				CompanyCustomer companyCustomer = new CompanyCustomer(this, DNI, name, phoneNumber);
				client = companyCustomer;
				addPerson(companyCustomer);
				mustExit = true;
				break;
			case 3: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
				break;
			}
		}
		return client;
	}

	public int getPhoneNumber() {
		int phoneNumber = 0;
		System.out.println("Insert Phone number (7 digit): ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return -1;
		}
		String str = sc.nextLine();
		try {
			phoneNumber = Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return -1;
		}
		if (str.length() != 7) {
			System.out.println("This phone number does not have.");
			return -1;
		}
		for (Person person : people) {
			if (person instanceof Client) {
				if (((Client) person).getTelephone() == phoneNumber) {
					System.out.println("This number already exists.");
					return -1;
				}
			}
		}
		return phoneNumber;
	}

	public void createAnOrder() {
		Client client = null;
		int moreFurniture;
		int idFurniture;
		int items;
		int totalPrice;
		boolean mustExit = false;
		client = createOrGetClient();
		if (client == null) {
			return;
		}
		Order order = new Order(client.getDNI());
		// Do not ask for more before the first one.
		idFurniture = createNewFurniture();
		if (idFurniture == -1) {
			return;
		}
		items = askHowManyItems();
		if (items == -1) {
			return;
		}
		order.addFurnitureIDAndItems(idFurniture, items);
		while (!mustExit) {
			moreFurniture = addMoreFurnitureInTheOrder();
			switch (moreFurniture) {
			case 1:
				idFurniture = createNewFurniture();
				if (idFurniture == -1) {
					return;
				}
				items = askHowManyItems();
				if (items == -1) {
					return;
				}
				order.addFurnitureIDAndItems(idFurniture, items);
				break;
			case 2:
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
		totalPrice = calculateTotalPrice(order.getIdsAndItemsFurniture());
		if (totalPrice == 0) {
			System.out.println("Unable to register an order with price 0.");
			return;
		}
		order.setTotalPrice(totalPrice);
		client.addOrder(order.getId());
		orderList.add(order);
	}

	public int addMoreFurnitureInTheOrder() {
		int integer = -1;
		int moreFurniture = -1;
		boolean mustExit = false;
		while (!mustExit) {
			Printer.askMoreFurnitureInTheOrder();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return -1;
			}
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return moreFurniture;
			}
			switch (integer) {
			case 1:
				moreFurniture = 1;
				mustExit = true;
				break;
			case 2:
				moreFurniture = 2;
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
		return moreFurniture;
	}

	public String addOrNotFeatures() {
		String features = null;
		Printer.askFeatures();
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		String str = sc.nextLine();
		boolean exit = false;
		int answer = -1;
		try {
			answer = Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return null;
		}
		while (!exit) {
			switch (answer) {
			case 1:
				System.out.println("Insert features: ");
				if (!sc.hasNextLine()) {
					Printer.nothingHasBeenInserted();
					return null;
				}
				features = sc.nextLine();
				exit = true;
				break;
			case 2:
				features = "Without features.";
				exit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
		return features;
	}

	private String getDNI() {
		System.out.println("Insert DNI/CIF: ");
		if (!sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return null;
		}
		String DNI = sc.nextLine();
		if (DNI.isEmpty() || DNI == null) {
			System.out.println("The DNI has not been inserted.");
			return null;
		}
		return DNI;
	}

	public static void main(String[] args) {
		Factory factory = new Factory();
		factory.finishedOrders.put(false, new ArrayList<Order>());
		factory.finishedOrders.put(true, new ArrayList<Order>());
		factory.sc = new Scanner(System.in);
		boolean bossInserted = false;
		bossInserted = factory.addBoss();
		if (!bossInserted) {
			return;
		}
		int integer = -1;
		boolean mustExit = false;
		while (!mustExit) {
			Printer.mainFunction();
			if (!factory.sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = factory.sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (integer) {
			case 1:
				factory.peopleSwitch();
				break;
			case 2:
				factory.furnitureSwitch();
				break;
			case 3:
				factory.orderSwitch();
				break;
			case 4:
				factory.factorySwitch();
				break;
			case 5: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
		factory.sc.close();
	}

	private void peopleSwitch() {
		boolean mustExit = false;
		int integer = -1;
		while (!mustExit) {
			Printer.peopleFunction();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (integer) {
			case 1:
				bossSwitch();
				break;
			case 2:
				salesmanSwitch();
				break;
			case 3:
				craftmanSwitch();
				break;
			case 4:
				clientSwitch();
				break;
			case 5: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
	}

	private void bossSwitch() {
		boolean mustExit = false;
		int integer = -1;
		while (!mustExit) {
			Printer.bossFunction();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (integer) {
			case 1:
				if (boss == null) {
					System.out.println("There is no boss.");
					break;
				}
				boss.assignCraftsman();
				break;
			case 2: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
	}

	private void salesmanSwitch() {
		boolean mustExit = false;
		int integer = -1;
		while (!mustExit) {
			Printer.salesmanFunction();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (integer) {
			case 1:
				addSalesman();
				break;
			case 2:
				modifyPeopleData();
				break;
			case 3:
				Salesman salesman = getSalesman();
				if (salesman == null) {
					break;
				}
				salesman.notifyTheCustomer();
				break;
			case 4:
				ShowOrderPrice();
			case 5: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
	}

	private void craftmanSwitch() {
		boolean mustExit = false;
		Craftsman craftsman = null;
		int integer = -1;
		while (!mustExit) {
			Printer.craftmanFunction();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (integer) {
			case 1:
				addCraftman();
				break;
			case 2:
				modifyPeopleData();
				break;
			case 3:
				craftsman = getCraftsman();
				if (craftsman == null) {
					System.out.println("This craftsman does not exist.");
					break;
				}
				craftsman.modifyFurnitureStatus();
				break;
			case 4:
				craftsman = getCraftsman();
				if (craftsman == null) {
					System.out.println("This craftsman does not exist.");
					break;
				}
				craftsman.finishedOrder();
				break;
			case 5: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
	}

	private void clientSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.clientFunction();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			Client client = null;
			int integer = -1;
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (integer) {
			case 1:
				modifyPeopleData();
				break;
			case 2:
				client = getClient();
				if (client == null) {
					break;
				}
				client.confirmOrder();
				break;
			case 3: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
	}

	private void furnitureSwitch() {
		boolean mustExit = false;
		int furnitureType = -1;
		while (!mustExit) {
			Printer.furnitureFunction();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			try {
				furnitureType = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (furnitureType) {
			case 1:
				modifyFurnitureData();
				break;
			case 2: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
	}

	private void orderSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.orderFunction();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			int integer = -1;
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (integer) {
			case 1:
				createAnOrder();
				break;
			case 2: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
	}

	private void factorySwitch() {
		boolean mustExit = false;
		int integer = -1;
		while (!mustExit) {
			Printer.factorySwitch();
			if (!sc.hasNextLine()) {
				Printer.nothingHasBeenInserted();
				return;
			}
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (integer) {
			case 1:
				missingPieces();
				break;
			case 2:
				printOrdersProcessedByEachCraftsman();
				break;
			case 3:
				requestClientToConfirm();
				break;
			case 4:
				reportInProcess();
				break;
			case 5:
				switchCraftsmanHistory();
				break;
			case 6:
				switchFurnitureHistory();
				break;
			case 7: // Exit
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
			}
		}
	}
}
