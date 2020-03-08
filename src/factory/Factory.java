package factory;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import people.Person;

public class Factory {

	private static void mainFunction() {
		System.out.println("Artisan Factory");
		System.out.println("1.People");
		System.out.println("2.Furniture");
		System.out.println("3.Orders");
		System.out.println("4.Exit");
		System.out.println("Enter the number: ");
	}

	private List<Person> people = new LinkedList<Person>();
	private List<Order> orders = new LinkedList<Order>();

	public static void main(String[] args) {

		boolean mustExit = false;
		while (!mustExit) {
			Factory.mainFunction();
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			int integer = Integer.parseInt(str);
			try {
				switch (integer) {
				case 0:
					System.out.println("People");
					break;
				case 1:
					System.out.println("Furniture");
					break;
				case 4:
					mustExit = true;
					break;
				default:
					break;
				}
			} catch (NumberFormatException exception) {
				System.out.println("this is not a number");
			}

		}
	}
}
