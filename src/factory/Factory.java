package factory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import furniture.BedroomTable;
import furniture.CristalCoffeeTable;
import furniture.DiningTable;
import furniture.FoldingChair;
import furniture.Furniture;
import furniture.KitchenChair;
import furniture.OfficeChairWithWheels;
import furniture.OfficeChairWithoutWheels;
import furniture.WoodenCoffeeTable;
import javafx.util.Pair;
import people.Boss;
import people.CompanyCustomer;
import people.ContractInStaff;
import people.Craftsman;
import people.HourlyContract;
import people.Person;
import people.PrivateCustomer;
import people.Salesman;

public class Factory {

	public static Scanner sc = new Scanner(System.in);
	private List<Person> people = new LinkedList<Person>();
	private static List<Craftsman> craftsmans = new LinkedList<Craftsman>();
	private List<Boss> bosses = new LinkedList<Boss>();
	private List<Salesman> salesmans = new LinkedList<Salesman>();
	private List<PrivateCustomer> privateCustomer = new LinkedList<PrivateCustomer>();
	private List<CompanyCustomer> companyCustomer = new LinkedList<CompanyCustomer>();
	private List<Furniture> furnitures = new LinkedList<Furniture>();
	private static List<Order> OrderList = new LinkedList<Order>();

	public void addPerson(Person person) {
		if (person instanceof Craftsman) {
			people.add(person);
			craftsmans.add((Craftsman) person);
		} else if (person instanceof Boss) {
			people.add(person);
			bosses.add((Boss) person);
		} else if (person instanceof Salesman) {
			people.add(person);
			salesmans.add((Salesman) person);
		} else if (person instanceof PrivateCustomer) {
			people.add(person);
			privateCustomer.add((PrivateCustomer) person);
		} else if (person instanceof CompanyCustomer) {
			people.add(person);
			companyCustomer.add((CompanyCustomer) person);
		} else {
			people.add(person);
		}
	}

	public static void confirmStatusOrder() {

	}

	public static Order getOrder(int idOrder) {
		for (Order order : OrderList) {
			if (order.getId() == idOrder) {
				return order;
			}
		}
		return null;
	}

	public static Craftsman getCraftman() {
		String id;
		System.out.println("Insert DNI/PASSPORT: ");
		id = sc.nextLine();
		for (Craftsman craftman : craftsmans) {
			if (craftman.getId() == id) {
				return craftman;
			}
		}
		return null;
	}

	public int askHowManyItems() {
		int totalFurniture = 0;
		boolean mustExit = false;
		while (!mustExit) {
			System.out.println("how many do you want?: ");
			String str = sc.nextLine();
			try {
				totalFurniture = Integer.parseInt(str);
				mustExit = true;
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
				mustExit = false;
			}
		}
		return totalFurniture;
	}

	public int calculateTotalPrice(HashMap<Integer, Integer> order) {
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

	private Pair<String, Integer> newFurniture() {
		Pair<String, Integer> newFurniture = null;
		System.out.println("Insert furniture model: ");
		String furnitureModel = sc.nextLine();
		System.out.println("Insert price: ");
		String str = sc.nextLine();
		try {
			int price = Integer.parseInt(str);
			Pair<String, Integer> ans = new Pair<String, Integer>(furnitureModel, price);
			newFurniture = ans;
		} catch (Exception e) {
			System.out.println("This is not a number");
		}
		return newFurniture;
	}

	/**
	 * Create a new chair and adds it to the internal list
	 */
	public int createChair() {
		int typeChair = -1;
		int furnitureID = -1;
		boolean mustExit = false;
		Pair<String, Integer> chair = null;
		while (!mustExit) {
			Printer.menuChairs();
			String str = sc.nextLine();
			String modelOfChair;
			String features;
			int price;
			typeChair = Integer.parseInt(str);
			switch (typeChair) {
			case 1:
				chair = newFurniture();
				modelOfChair = chair.getKey();
				price = chair.getValue();
				features = addOrNotFeatures();
				Furniture foldingChair = new FoldingChair(modelOfChair, price, features);
				furnitures.add(foldingChair);
				furnitureID = foldingChair.getId();
				mustExit = true;
				break;
			case 2:
				chair = newFurniture();
				modelOfChair = chair.getKey();
				price = chair.getValue();
				features = addOrNotFeatures();
				Furniture kitchenChair = new KitchenChair(modelOfChair, price, features);
				furnitures.add(kitchenChair);
				furnitureID = kitchenChair.getId();
				mustExit = true;
				break;
			case 3:
				furnitureID = createOfficeChair();
				mustExit = true;
				break;
			case 4:
				mustExit = true;
				break;
			default:
				System.out.println("This number is not valide\n");
			case -1:
				continue;
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
		Pair<String, Integer> furniture = null;
		String modelOfficeChair;
		String features;
		int price;
		while (!mustExit) {
			Printer.menuOfficeChair();
			String str = sc.nextLine();
			typeOfficeChair = Integer.parseInt(str);
			switch (typeOfficeChair) {
			case 1:
				furniture = newFurniture();
				modelOfficeChair = furniture.getKey();
				price = furniture.getValue();
				features = addOrNotFeatures();
				Furniture chairWithWheels = new OfficeChairWithWheels(modelOfficeChair, price, features);
				furnitures.add(chairWithWheels);
				furnitureID = chairWithWheels.getId();
				mustExit = true;
				break;
			case 2:
				furniture = newFurniture();
				modelOfficeChair = furniture.getKey();
				price = furniture.getValue();
				features = addOrNotFeatures();
				Furniture chairWithoutWheels = new OfficeChairWithoutWheels(modelOfficeChair, price, features);
				furnitures.add(chairWithoutWheels);
				furnitureID = chairWithoutWheels.getId();
				mustExit = true;
				break;
			}
		}
		return furnitureID;
	}

	public int createTables() {
		Pair<String, Integer> furniture = null;
		Printer.menuTables();
		String model;
		String features;
		String str = sc.nextLine();
		int price;
		int furnitureID = -1;
		int typeTable = Integer.parseInt(str);
		switch (typeTable) {
		case 1:
			furniture = newFurniture();
			model = furniture.getKey();
			price = furniture.getValue();
			features = addOrNotFeatures();
			Furniture bedroomTable = new BedroomTable(model, price, features);
			furnitures.add(bedroomTable);
			furnitureID = bedroomTable.getId();
			break;
		case 2:
			furnitureID = createCoffeeTable();
			break;
		case 3:
			furniture = newFurniture();
			model = furniture.getKey();
			price = furniture.getValue();
			features = addOrNotFeatures();
			Furniture diningTable = new DiningTable(model, price, features);
			furnitures.add(diningTable);
			furnitureID = diningTable.getId();
			break;
		}
		return furnitureID;
	}

	public int createCoffeeTable() {
		Pair<String, Integer> furniture = null;
		Printer.menuCoffeeTable();
		String model;
		String features;
		String str = sc.nextLine();
		int furnitureID = -1;
		int price;
		int coffeeTable = Integer.parseInt(str);
		switch (coffeeTable) {
		case 1:
			furniture = newFurniture();
			model = furniture.getKey();
			price = furniture.getValue();
			features = addOrNotFeatures();
			Furniture cristalCoffeeTable = new CristalCoffeeTable(model, price, features);
			furnitures.add(cristalCoffeeTable);
			furnitureID = cristalCoffeeTable.getId();
			break;
		case 2:
			furniture = newFurniture();
			model = furniture.getKey();
			price = furniture.getValue();
			features = addOrNotFeatures();
			furnitures.add(new WoodenCoffeeTable(model, price, features));
			break;
		}
		return furnitureID;
	}

	public void addBoss() {
		System.out.println("Insert name: ");
		String name = sc.nextLine();
		System.out.println("Insert DNI/PASSPORT: ");
		String dni = sc.nextLine();
		Boss b = new Boss(dni, name);
		addPerson(b);
	}

	// HERE MAKE SURE
	public void modifyPeopleData() {
		System.out.println("Insert DNI/PASSPORT: ");
		String dni = sc.nextLine();
		for (Person p : people) {
			if (p.getId().equals(dni)) {
				p.modifyData();
			}
		}
	}

	public void addSalesman() {
		System.out.println("Insert name: ");
		String name = sc.nextLine();
		System.out.println("Insert DNI/PASSPORT: ");
		String dni = sc.nextLine();
		Salesman s = new Salesman(name, dni);
		addPerson(s);
	}

	public void addCraftman() {
		System.out.println("Insert name: ");
		String name = sc.nextLine();
		System.out.println("Insert DNI/PASSPORT: ");
		String dni = sc.nextLine();
		Printer.menuContractCraftsman();
		String str = sc.nextLine();
		try {
			int contract = Integer.parseInt(str);
			switch (contract) {
			case 1:
				Craftsman c1 = new ContractInStaff(name, dni);
				addPerson(c1);
				break;
			case 2:
				Craftsman c2 = new HourlyContract(name, dni);
				addPerson(c2);
				break;
			case 3:
				break;
			}
		} catch (NumberFormatException exception) {
			System.out.println("This is nor a number");
		}
	}

	public int createNewFurniture() {
		Printer.furnitureTypes();
		int furnitureID = -1;
		int integer = -1;
		String str = sc.nextLine();
		try {
			integer = Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			System.out.println("This is not a number");
		}
		switch (integer) {
		case 1:
			furnitureID = createChair();
			break;
		case 2:
			furnitureID = createTables();
			break;
		}
		return furnitureID;
	}

	public void modifyFurnitureData() {
		System.out.println("Insert ID: ");
		String id = sc.nextLine();
		int integer = Integer.parseInt(id);
		for (Furniture f : furnitures) {
			if (f.getId() == integer) {
				f.modifyData();
			}
		}
	}

	public void addNewClient() {
		System.out.println("Insert DNI/PASSPORT: ");
		String dni = sc.nextLine();
		createOrGetClient(dni);
	}

	public Person createOrGetClient(String personId) {
		if (personId.isEmpty()) {
			return null;
		}
		for (Person p : people) {
			if (p.getId().equals(personId)) {
				return p;
			}
		}
		System.out.println("Insert Name: ");
		String str = sc.nextLine();
		String name = str;
		Person p = null;
		Printer.menuClient();
		String type = sc.nextLine();
		try {
			int integer = Integer.parseInt(type);
			switch (integer) {
			case 1:
				PrivateCustomer privateCustomer = new PrivateCustomer(personId, name);
				p = privateCustomer;
				addPerson(privateCustomer);
				break;
			case 2:
				CompanyCustomer companyCustomer = new CompanyCustomer(personId, name);
				p = companyCustomer;
				addPerson(companyCustomer);
				break;
			default:
				System.out.println("This number is not valide");
				break;
			}
		} catch (NumberFormatException exception) {
			System.out.println("this is not a number");
		}
		return p;
	}

	// TODO
	public void createAnOrder() {
		String id = null;
		int moreFurniture;
		int idFurniture;
		int items;
		int totalPrice;
		boolean mustExit = false;
		id = askID();
		if (id == null || id.isEmpty()) {
			return;
		}
		Order order = new Order(id);
		// creamos un mueble primera para que no este en el bucle
		idFurniture = createNewFurniture();
		items = askHowManyItems();
		order.addFurniturePiece(idFurniture, items);
		while (!mustExit) {
			// TODO change addMoreFurniture to return boolean
			moreFurniture = addMoreFurnitureInTheOrder();
			switch (moreFurniture) {
			case 1:
				idFurniture = createNewFurniture();
				items = askHowManyItems();
				order.addFurniturePiece(idFurniture, items);
				break;
			case 2:
				mustExit = true;
				break;
			}
		}
		totalPrice = calculateTotalPrice(order.getIdsAndPriceFurniture());
		order.setTotalPrice(totalPrice);
		OrderList.add(order);
	}

	public int addMoreFurnitureInTheOrder() {
		int integer = -1;
		int moreFurniture = -1;
		boolean mustExit = false;
		while (!mustExit) {
			Printer.askMoreFurnitureInTheOrder();
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
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
				System.out.println("This is not a number");
			case -1:
				continue;
			}
		}
		return moreFurniture;
	}

	public String addOrNotFeatures() {
		String features = null;
		Printer.askFeatures();
		String str = sc.nextLine();
		int answer = -1;
		try {
			answer = Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			System.out.println("this is not a number");
		}
		switch (answer) {
		case 1:
			System.out.println("Insert features: ");
			features = sc.nextLine();
			break;
		case 2:
			features = "Without features";
			break;
		}
		return features;
	}

	public String askID() {
		boolean mustExit = false;
		int integer = -1;
		String id = null;
		while (!mustExit) {
			Printer.idMenu();
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				System.out.println("This is not a number");
			}
			switch (integer) {
			case 1:
				id = getID();
				mustExit = true;
				break;
			case 2:
				mustExit = true;
				break;
			default:
				System.out.println("This number is nor valid\n");
			case -1:
				continue;
			}
		}
		return id;
	}

	public String getID() {
		String dniOrCif = null;
		boolean mustExit = false;
		while (!mustExit) {
			System.out.println("DNI/CIF: ");
			String str = sc.nextLine();
			if (str != null && !str.isEmpty()) {
				Person person = createOrGetClient(str);
				dniOrCif = person.getId();
				mustExit = true;
				break;
			} else {
				mustExit = false;
			}
		}
		return dniOrCif;
	}

	public int getPrice(int furnitureID) {
		int price = -1;
		for (Furniture f : furnitures) {
			if (furnitureID == f.getId()) {
				price = f.getPrice();
			}
		}
		return price;
	}

//	// TODO recibir el objeto mejor
//	public void addOrder(String dni, int idFurniture, int price, int totalItems, int totalPrice) {
//		String features = null;
//		int integer = -1;
//		Printer.askFeatures();
//		String str = sc.nextLine();
//		try {
//			integer = Integer.parseInt(str);
//		} catch (NumberFormatException exception) {
//			System.out.println("this is not a number");
//		}
//		switch (integer) {
//		case 1:
//			features = sc.nextLine();
//			break;
//		case 2:
//			features = "Without features";
//			break;
//		}
////		TODO añadirle la lista id de muebles
//		// OrderList.add(new Order(dni, idFurniture, price, totalItems, totalPrice,
//		// features));
//	}

	public Order getOrder(String orderId) {
// TODO: Implement
		return null;
	}

	public static void main(String[] args) {
		Factory factory = new Factory();
		Factory.sc = new Scanner(System.in);
		boolean mustExit = false;
		while (!mustExit) {
			Printer.mainFunction();
			String str = Factory.sc.nextLine();
			try {
				int integer = Integer.parseInt(str);
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
					System.out.println("Exit");
					mustExit = true;
					break;
				default:
					System.out.println("This number is not valid\n");
					break;
				}
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
		}
		Factory.sc.close();
	}

	public void peopleSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.peopleFunction();
			String str = sc.nextLine();
			int integer = -1;
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
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
			case 5:
				mustExit = true;
				break;
			default:
				System.out.println("this number is not valid\n");
			case -1:
				continue;
			}
		}
	}

	public void bossSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.bossFunction();
			String str = sc.nextLine();
			int integer = -1;
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			switch (integer) {
			case 1:
				addBoss();
				break;
			case 2:
				modifyPeopleData();
				break;
			case 3:
				System.out.println("3. Assign Craftsman");
				break;
			case 4:
				mustExit = true;
				break;
			default:
				System.out.println("this number is not valid\n");
			case -1:
				continue;
			}
		}
	}

	public void salesmanSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.salesmanFunction();
			String str = sc.nextLine();
			int integer = -1;
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			switch (integer) {
			case 1:
				addSalesman();
				break;
			case 2:
				modifyPeopleData();
				break;
			case 3:
				mustExit = true;
				break;
			default:
				System.out.println("this number is not valid\n");
			case -1:
				continue;
			}
		}
	}

	public void craftmanSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.craftmanFunction();
			String str = sc.nextLine();
			int integer = -1;
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			switch (integer) {
			case 1:
				System.out.println("1. Add new craftman");
				break;
			case 2:
				System.out.println("2. Modify craftman data");
				break;
			case 3:
				System.out.println("3. Add a new status of the order");
				break;
			case 4:
				mustExit = true;
				break;
			default:
				System.out.println("this number is not valid\n");
			case -1:
				continue;
			}
		}
	}

	public void clientSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.clientFunction();
			String str = sc.nextLine();
			int integer = -1;
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			switch (integer) {
			case 1:
				addNewClient();
				break;
			case 2:
				System.out.println("2. Modify client data");
				modifyPeopleData();
				break;
			case 3:
				mustExit = true;
				break;
			default:
				System.out.println("this number is not valid\n");
			case -1:
				continue;
			}
		}
	}

	public void furnitureSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.furnitureFunction();
			String str = sc.nextLine();
			int furnitureType = -1;
			try {
				furnitureType = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number\n");
			}
			switch (furnitureType) {
			case 1:
				// createNewFurniture();
				break;
			case 2:
				modifyFurnitureData();
				break;
			case 3:
				mustExit = true;
				break;
			default:
				System.out.println("this number is not valid\n");
			case -1:
				continue;
			}
		}
	}

	public void orderSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.orderFunction();
			String str = sc.nextLine();
			int integer = -1;
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			switch (integer) {
			case 1:
				createAnOrder();
				break;
			case 2:
				System.out.println("2. Modify order data");
				break;
			case 3:
				mustExit = true;
				break;
			default:
				System.out.println("this number is not valid\n");
			case -1:
				continue;
			}
		}
	}
}
