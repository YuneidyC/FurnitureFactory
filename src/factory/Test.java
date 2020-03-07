package factory;

import java.util.List;

import furniture.CristalCoffeeTable;
import furniture.Furniture;
import furniture.OfficeChairWithoutWheels;
import furniture.WoodenCoffeeTable;
import people.ContractInStaff;
import people.HourlyContract;
import people.Person;

public class Test {

	public static void fillEmployeesList(List<Person> artisan) {
		System.out.println("fillEmployeesList");
		artisan.add(new ContractInStaff("ID3", "Julian"));
		artisan.add(new HourlyContract("ID5", "Bae"));
		for (Person p : artisan) {
			System.out.println(p.toString());
		}

		System.out.println(artisan.get(0).getName());

		System.out.println("------");
	}

	public static void createListFurniture(List<Furniture> furnitures) {

		{
			furnitures.add(new WoodenCoffeeTable("ID1", "WoodenCoffeeTable", 6000));
			furnitures.add(new CristalCoffeeTable("ID2", "CristalCoffeeTable", 5000));
			furnitures.add(new OfficeChairWithoutWheels("ID3", "OfficeChairWithoutWheels", 10000));
		}
		for (Furniture furniture : furnitures) {
			System.out.println(furniture.toString());
		}

	}

	public static void createOrder(List<Order> orders) {

		{
			orders.add(new Order("ID1", "WoodenCoffeeTable", 6000));
			orders.add(new Order("ID2", "CristalCoffeeTable", 5000));
			orders.add(new Order("ID3", "OfficeChairWithoutWheels", 10000));
		}

	}

}
