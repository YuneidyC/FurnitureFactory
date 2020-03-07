package factory;

import java.util.LinkedList;
import java.util.List;

import people.Person;

public class Factory {

	private List<Person> people = new LinkedList<Person>();
	private List<Order> orders = new LinkedList<Order>();

	public static void main(String[] args) {
		Factory factory = new Factory();
		Test.fillEmployeesList(factory.people);
		Test.createOrder(factory.orders);
	}
}
