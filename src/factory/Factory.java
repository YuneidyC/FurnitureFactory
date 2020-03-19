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
	public void createChair() {
		Pair<String, Integer> chair = null;
		Printer.printTypesOfChairs();
		String str = sc.nextLine();
		String modelOfChair;
		int price;
		int typeChair = Integer.parseInt(str);
		switch (typeChair) {
		case 1:
			chair = newFurniture();
			modelOfChair = chair.getKey();
			price = chair.getValue();
			furnitures.add(new FoldingChair(modelOfChair, price));
			break;
		case 2:
			chair = newFurniture();
			modelOfChair = chair.getKey();
			price = chair.getValue();
			furnitures.add(new KitchenChair(modelOfChair, price));
			break;
		case 3:
			createOfficeChair();
		}
	}

	/**
	 * Create a new OfficeChair and adds it to the internal list
	 */

	public void createOfficeChair() {
		Pair<String, Integer> furniture = null;
		String model;
		int price;
		Printer.printTypesOfOfficeChair();
		String str = sc.nextLine();
		int officeChair = Integer.parseInt(str);
		switch (officeChair) {
		case 1:
			furniture = newFurniture();
			model = furniture.getKey();
			price = furniture.getValue();
			furnitures.add(new OfficeChairWithWheels(model, price));
			break;
		case 2:
			furniture = newFurniture();
			model = furniture.getKey();
			price = furniture.getValue();
			furnitures.add(new OfficeChairWithoutWheels(model, price));
			break;
		}
	}

	public void typesOfTables() {
		Pair<String, Integer> furniture = null;
		String model;
		int price;
		sc = new Scanner(System.in);
		Printer.printTypesOfTables();
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
			typesCoffeeTable();
			break;
		case 3:
			furniture = newFurniture();
			model = furniture.getKey();
			price = furniture.getValue();
			furnitures.add(new DiningTable(model, price));
			break;
		}
	}

	public void typesCoffeeTable() {
		Pair<String, Integer> furniture = null;
		String model;
		int price;
		Printer.printTypesOfCoffeeTable();
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
		Boss b = new Boss(name, dni);
		addPerson((Person) b);
	}

	// HERE MAKE SURE
	public void modifyPeopleData() {
		System.out.println("Insert DNI/PASSPORT: ");
		String dni = sc.nextLine();
		for (Person p : people) {
			if (p.getId() == dni) {
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

	public void createNewFurniture() {
		Printer.furnitureTypes();
		String str = sc.nextLine();
		try {
			int integer = Integer.parseInt(str);
			switch (integer) {
			case 1:
				createChair();
				break;
			case 2:
				typesOfTables();
				break;
			}
		} catch (NumberFormatException exception) {
			System.out.println("This is not a number");
		}
	}

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

	public Person createOrGetClient(String personId) {
		if (personId.isEmpty()) {
			return null;
		}
		for (Person p : people) {
			if (p.getId() == personId) {
				return p;
			}
		}
		System.out.println("Insert Name: ");
		String str = sc.nextLine();
		String name = str;
		Person p = null;
		Printer.printTypesOfClient();
		int integer = Integer.parseInt(str);
		try {
			switch (integer) {
			case 1:
				PrivateCustomer privateCustomer = new PrivateCustomer(personId, name);
				addPerson(privateCustomer);
				p = privateCustomer;
				break;
			case 2:
				CompanyCustomer company = new CompanyCustomer(personId, name);
				addPerson(company);
				p = company;
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
	public void addOrder() {
		System.out.println("Insert DNI/CIF: ");
		String str = sc.nextLine();
		createOrGetClient(str);
		try {
			Printer.furnitureTypes();
			int integer = Integer.parseInt(str);
			switch (integer) {
			case 1:
				createChair();
			case 2:
				typesOfTables();
				break;
			default:
				System.out.println("This number is not valid");
				break;
			}
		} catch (

		NumberFormatException exception) {
			System.out.println("this is not a number");
		}
		// Order newOrder = new Order();
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
					System.out.println("This number is not valide");
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
				System.out.println("1. Add new client");
				break;
			case 2:
				System.out.println("2. Modify client data");
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
				createNewFurniture();
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
