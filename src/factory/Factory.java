package factory;

import java.util.LinkedList;
import java.util.List;
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
	private List<Craftsman> craftsmans = new LinkedList<Craftsman>();
	private List<Boss> bosses = new LinkedList<Boss>();
	private List<Salesman> salesmans = new LinkedList<Salesman>();
	private List<PrivateCustomer> privateCustomer = new LinkedList<PrivateCustomer>();
	private List<CompanyCustomer> companyCustomer = new LinkedList<CompanyCustomer>();
	private List<Furniture> furnitures = new LinkedList<Furniture>();
	private List<Order> OrderList = new LinkedList<Order>();

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

	public int askHowManyItems() {
		int totalFurniture = 0;
		boolean mustExit = false;
		while (!mustExit) {
			System.out.println("how many do you want?: ");
			String str = sc.nextLine();
			try {
				totalFurniture = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
				mustExit = false;
			}
		}
		return totalFurniture;
	}

	public int calculateTotalPrice(int price, int totalFurniture) {
		int total = price * totalFurniture;
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
	public void createChair(String dni) {
		int typeChair = -1;
		boolean mustExit = false;
		Pair<String, Integer> chair = null;
		while (!mustExit) {
			Printer.typesOfChairs();
			String str = sc.nextLine();
			String modelOfChair;
			int price;
			typeChair = Integer.parseInt(str);
			switch (typeChair) {
			case 1:
				chair = newFurniture();
				modelOfChair = chair.getKey();
				price = chair.getValue();
				Furniture foldingChair = new FoldingChair(modelOfChair, price);
				furnitures.add(foldingChair);
				int getId = foldingChair.getId();
				int totalItems = askHowManyItems();
				int totalPrice = calculateTotalPrice(price, totalItems);
				addOrder(getId, totalItems, totalPrice);
				break;
			case 2:
				chair = newFurniture();
				modelOfChair = chair.getKey();
				price = chair.getValue();
				furnitures.add(new KitchenChair(modelOfChair, price));
				break;
			case 3:
				createOfficeChair();
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
	}

	/**
	 * Create a new OfficeChair and adds it to the internal list
	 */
	public void createOfficeChair() {
		int typeOfficeChair = -1;
		boolean mustExit = false;
		Pair<String, Integer> furniture = null;
		String modelOfficeChair;
		int price;
		while (!mustExit) {
			Printer.typesOfOfficeChair();
			String str = sc.nextLine();
			typeOfficeChair = Integer.parseInt(str);
			switch (typeOfficeChair) {
			case 1:
				furniture = newFurniture();
				modelOfficeChair = furniture.getKey();
				price = furniture.getValue();
				furnitures.add(new OfficeChairWithWheels(modelOfficeChair, price));
				break;
			case 2:
				furniture = newFurniture();
				modelOfficeChair = furniture.getKey();
				price = furniture.getValue();
				furnitures.add(new OfficeChairWithoutWheels(modelOfficeChair, price));
				break;
			}
		}
	}

	public void createTables() {
		Pair<String, Integer> furniture = null;
		String model;
		int price;
		Printer.typesOfTables();
		String str = sc.nextLine();
		int typeTable = Integer.parseInt(str);
		switch (typeTable) {
		case 1:
			furniture = newFurniture();
			model = furniture.getKey();
			price = furniture.getValue();
			furnitures.add(new BedroomTable(model, price));
			break;
		case 2:
			createCoffeeTable();
			break;
		case 3:
			furniture = newFurniture();
			model = furniture.getKey();
			price = furniture.getValue();
			furnitures.add(new DiningTable(model, price));
			break;
		}
	}

	public void createCoffeeTable() {
		Pair<String, Integer> furniture = null;
		String model;
		int price;
		Printer.typesOfCoffeeTable();
		String str = sc.nextLine();
		int coffeeTable = Integer.parseInt(str);
		switch (coffeeTable) {
		case 1:
			furniture = newFurniture();
			model = furniture.getKey();
			price = furniture.getValue();
			furnitures.add(new CristalCoffeeTable(model, price));
			break;
		case 2:
			furniture = newFurniture();
			model = furniture.getKey();
			price = furniture.getValue();
			furnitures.add(new WoodenCoffeeTable(model, price));
			break;
		}
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
		Printer.typeOfContractCraftsman();
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

//	public void createNewFurniture() {
//		Printer.furnitureTypes();
//		String str = sc.nextLine();
//		try {
//			int integer = Integer.parseInt(str);
//			switch (integer) {
//			case 1:
//				createChair();
//				break;
//			case 2:
//				createTables();
//				break;
//			}
//		} catch (NumberFormatException exception) {
//			System.out.println("This is not a number");
//		}
//	}

//	TODO
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
		Printer.typesOfClient();
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
		int integer = -1;
		boolean mustExit = false;
		id = askID();
		if (id == null || id.isEmpty()) {
			return;
		}

		while (!mustExit) {
			Printer.furnitureTypes();
			String str = sc.nextLine();
			try {
				integer = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			switch (integer) {
			case 1:
				createChair(id);
				break;
			case 2:
				createTables();
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

	// TODO
	public void addOrder(int id, int totalItems, int totalPrice) {

	}

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
				System.out.println("1. Add new order");
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
