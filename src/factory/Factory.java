package factory;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import people.Craftsman;
import people.Person;

public class Factory {

	private List<Order> orders = new LinkedList<Order>();
	private List<Person> people = new LinkedList<Person>();
	private List<Craftsman> craftsmans = new LinkedList<Craftsman>();

	public void addPerson(Person person) {
		if (person instanceof Craftsman) {
			people.add(person);
			craftsmans.add((Craftsman) person);
		} else {
			people.add(person);
		}

	}

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
		System.out.println("1. Add boss");
		System.out.println("2. Assign Crafstman");
		System.out.println("3. Exit");
		// bossSwitch();
	}

	public static void main(String[] args) {

		boolean mustExit = false;
		while (!mustExit) {
			Factory.mainFunction();
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			try {
				int integer = Integer.parseInt(str);
				switch (integer) {
				case 1:
					System.out.println("People");
					peopleFunction();
					break;
				case 2:
					System.out.println("Furniture");
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

	public static void peopleSwitch() {

		boolean mustExit = false;
		while (!mustExit) {
			Factory.peopleFunction();
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			try {
				int integer = Integer.parseInt(str);
				switch (integer) {
				case 1:
					System.out.println("Boss");
					bossFunction();
					break;
				case 2:
					System.out.println("Salesman");
					break;
				case 3:
					System.out.println("Craftsman");
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

}
