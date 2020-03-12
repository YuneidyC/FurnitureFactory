package factory;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import people.Boss;
import people.Craftsman;
import people.Person;
import people.Salesman;

public class Factory {

	private List<Order> orders = new LinkedList<Order>();
	private List<Person> people = new LinkedList<Person>();
	private List<Craftsman> craftsmans = new LinkedList<Craftsman>();
	private List<Boss> bosses = new LinkedList<Boss>();
	private List<Salesman> salesmans = new LinkedList<Salesman>();

	private static void mainFunction() {
		System.out.println("Artisan Factory");
		System.out.println("1. People");
		System.out.println("2. Furniture");
		System.out.println("3. Orders");
		System.out.println("4. Exit");
		System.out.println("Enter the number: ");
	}

	private static void peopleFunction() {
		System.out.println("People Functions");
		System.out.println("1. Boss");
		System.out.println("2. Salesman");
		System.out.println("3. Craftsman");
		System.out.println("4. Exit");
		System.out.println("Enter the number: ");
	}

	private static void bossFunction() {
		System.out.println("Boss Functions");
		System.out.println("1. Add new boss");
		System.out.println("2. Modify Boss data");
		System.out.println("3. Assign Crafstman");
		System.out.println("4. Exit");
		System.out.println("Enter the number: ");
	}

	private static void salesmanFunction() {
		System.out.println("Salesman Function");
		System.out.println("1. Add new Salesman");
		System.out.println("2. Modify commercial data");
		System.out.println("3. Add new order");
		System.out.println("4. Exit");
		System.out.println("Enter the number: ");
	}

	private static void craftmanFunction() {
		System.out.println("Craftman Function");
		System.out.println("1. Add new Craftman");
		System.out.println("2. Modify craftman data");
		System.out.println("3. Add a new status of the order");
		System.out.println("4. Exit");
		System.out.println("Enter the number: ");
	}

	private static void furnitureFunction() {
		System.out.println("Furniture Function");
		System.out.println("1. Add new furniture");
		System.out.println("2. Modify furniture data");
		System.out.println("3. Exit");
		System.out.println("Enter the number: ");
	}

	private static void orderFunction() {
		System.out.println("Order Function");
		System.out.println("1. Add new order");
		System.out.println("2. Modify order data");
		System.out.println("3. Exit");
		System.out.println("Enter the number: ");
	}

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
		} else {
			people.add(person);
		}
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

	public void addNewFurniture() {

	}

	public static void main(String[] args) {

		Factory factory = new Factory();
		boolean mustExit = false;
		while (!mustExit) {
			Factory.mainFunction();
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
					System.out.println("Orders");
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
			Factory.peopleFunction();
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
			Factory.bossFunction();
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
			Factory.salesmanFunction();
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
			Factory.craftmanFunction();
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
			Factory.furnitureFunction();
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

	public static void orderSwitch() {

		boolean mustExit = false;
		while (!mustExit) {
			Factory.orderFunction();
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
