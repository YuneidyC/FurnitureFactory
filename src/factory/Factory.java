package factory;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import furniture.Furniture;
import people.Boss;
import people.CompanyCustomer;
import people.Craftsman;
import people.Person;
import people.PrivateCustomer;
import people.Salesman;

public class Factory {

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

	public void searchTypesOfChair() {
		Scanner sc = new Scanner(System.in);
		Printer.printTypesOfChairs();
		String str = sc.nextLine();
		int typeChair = Integer.parseInt(str);
		switch (typeChair) {
		case 1:

			break;
		case 2:

			break;
		case 3:
			searchTypesOfficeChair();
		}
		sc.close();
	}

	public void searchTypesOfficeChair() {
		Scanner sc = new Scanner(System.in);
		Printer.printTypesOfOfficeChair();
		String str = sc.nextLine();
		int officeChair = Integer.parseInt(str);
		switch (officeChair) {
		case 1:

			break;

		case 2:
			break;
		}
		sc.close();
	}

	public void searchTypesOfTables() {
		Scanner sc = new Scanner(System.in);
		Printer.printTypesOfTables();
		String str = sc.nextLine();
		int typeTable = Integer.parseInt(str);
		switch (typeTable) {
		case 1:

			break;
		case 2:

			break;
		case 3:
			searchTypesCoffeeTable();
			break;
		}
		sc.close();
	}

	public void searchTypesCoffeeTable() {
		Scanner sc = new Scanner(System.in);
		Printer.printTypesOfCoffeeTable();
		String str = sc.nextLine();
		int coffeeTable = Integer.parseInt(str);
		switch (coffeeTable) {
		case 1:

			break;
		case 2:

			break;
		}
		sc.close();
	}

	public void addBoss() {
		System.out.println("Insert name: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println("Insert DNI/PASSPORT: ");
		String dni = sc.nextLine();
		Boss b = new Boss(name, dni);
		addPerson((Person) b);
		sc.close();
	}

	public void modifyBossData() {
		System.out.println("Insert DNI/PASSPORT: ");
		Scanner scDni = new Scanner(System.in);
		String dni = scDni.nextLine();
		for (Boss boss : bosses) {
			if (boss.getId() == dni) {
				boss.modifyData();
			}
		}
		scDni.close();
	}

	// switch with class
	public void addNewFurniture() {

	}

	public void modifyFurnitureData() {
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
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String name = str;
		Person p = null;
		try {
			System.out.println("Choose: 1. Private or 2. Company");
			System.out.println("Insert: ");
			int integer = Integer.parseInt(str);
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
		sc.close();
		return p;
	}

	// TODO
	public void addOrder() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert DNI/CIF: ");
		String str = sc.nextLine();
		createOrGetClient(str);
		try {
			Printer.furnitureTypes();
			int integer = Integer.parseInt(str);
			switch (integer) {
			case 1:
				searchTypesOfChair();
			case 2:
				searchTypesOfTables();
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
		sc.close();
	}

	public static void main(String[] args) {

		Factory factory = new Factory();
		boolean mustExit = false;
		while (!mustExit) {
			Printer.mainFunction();
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
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
			sc.close();
		}
	}

	public void peopleSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.peopleFunction();
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			try {
				int integer = Integer.parseInt(str);
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
					System.out.println("Exit");
					mustExit = true;
					break;
				default:
					break;
				}
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			sc.close();
		}
	}

	public void bossSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.bossFunction();
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			try {
				int integer = Integer.parseInt(str);
				switch (integer) {
				case 1:
					addBoss();
					break;
				case 2:
					System.out.println("2. Modify boss data");
					break;
				case 3:
					System.out.println("3. Assign Craftsman");
					break;
				case 4:
					System.out.println("4. Exit");
					mustExit = true;
					break;
				default:
					break;
				}
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			sc.close();
		}
	}

	public void salesmanSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.salesmanFunction();
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			try {
				int integer = Integer.parseInt(str);
				switch (integer) {
				case 1:
					System.out.println("1. Add new salesman");
					break;
				case 2:
					System.out.println("2. Modify commercial data");
					break;
				case 3:
					System.out.println("3. Add new order");
					break;
				case 4:
					System.out.println("4. Exit");
					mustExit = true;
					break;
				default:
					break;
				}
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			sc.close();
		}
	}

	public void craftmanSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.craftmanFunction();
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			try {
				int integer = Integer.parseInt(str);
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
					System.out.println("4. Exit");
					mustExit = true;
					break;
				default:
					break;
				}
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			sc.close();
		}
	}

	public void furnitureSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.furnitureFunction();
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			try {
				int integer = Integer.parseInt(str);
				switch (integer) {
				case 1:
					System.out.println("1. Add new Furniture");
					break;
				case 2:
					System.out.println("2. Modify furniture data");
					break;
				case 3:
					System.out.println("Exit");
					mustExit = true;
					break;
				default:
					break;
				}
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			sc.close();
		}
	}

	public void orderSwitch() {
		boolean mustExit = false;
		while (!mustExit) {
			Printer.orderFunction();
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			try {
				int integer = Integer.parseInt(str);
				switch (integer) {
				case 1:
					System.out.println("1. Add new order");
					break;
				case 2:
					System.out.println("2. Modify order data");
					break;
				case 3:
					System.out.println("Exit");
					mustExit = true;
					break;
				default:
					break;
				}
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}
			sc.close();
		}
	}
}
