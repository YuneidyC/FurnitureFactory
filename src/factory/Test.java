package factory;

import java.util.LinkedList;
import java.util.List;

import furniture.CristalCoffeeTable;
import furniture.Furniture;
import furniture.OfficeChairWithoutWheels;
import furniture.WoodenCoffeeTable;
import people.Boss;
import people.ContractInStaff;
import people.HourlyContract;
import people.Person;
import people.PrivateCustomer;
import people.Salesman;

public class Test {

	public static void createPerson() {
		System.out.println("createPerson");
		List<String> artisan = new LinkedList<String>();
		List<String> customer = new LinkedList<String>();
		ContractInStaff c = new ContractInStaff("ID2", "Juan Jose");
		PrivateCustomer p = new PrivateCustomer("ID1", "Julian");
		Boss m = new Boss("ID3", "Yrene");
		Salesman s = new Salesman("ID4", "Rubby");
		artisan.add(c.getId());
		customer.add(p.getDni());
		System.out.println("------");
	}

	public void createAndModifyPerson() {

	}

	public static void fillEmployeesList() {
		System.out.println("fillEmployeesList");
		List<Person> people = new LinkedList<Person>();
		people.add(new ContractInStaff("ID3", "Julian"));
		people.add(new HourlyContract("ID5", "Bae"));
		for (Person p : people) {
			System.out.println(p.toString());
		}

		System.out.println(people.get(0).getName());

		System.out.println("------");
	}

	public static void createOrder() {

		List<Furniture> order = new LinkedList<Furniture>();
		{
			order.add(new WoodenCoffeeTable("WoodenCoffeeTable", 6000));
			order.add(new CristalCoffeeTable("CristalCoffeeTable", 5000));
			order.add(new OfficeChairWithoutWheels("OfficeChairWithoutWheels", 10000));
		}
		for (Furniture furniture : order) {
			System.out.println(furniture.toString());
		}

	}

}
