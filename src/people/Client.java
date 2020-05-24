package people;

import java.util.LinkedList;
import java.util.List;

import factory.Factory;

public class Client extends Person {
	private int telephone;
	private List<Integer> orders = new LinkedList<Integer>();

	public Client(Factory factory, String DNI, String name, int telephone) {
		super(factory, DNI, name);
		this.telephone = telephone;
	}

	public int getTelephone() {
		return telephone;
	}

	public List<Integer> getOrders() {
		return orders;
	}

	public void addOrder(int IDOrder) {
		orders.add(IDOrder);
	}

	public void modifyData() {
		super.modifyData();
		System.out.println("Insert new telephone:");
		if (!getFactory().sc.hasNextLine()) {
			System.out.println("Nothing has been inserted.");
			return;
		}
		int phone = getFactory().sc.nextInt();
		if (phone != -1) {
			this.telephone = phone;
		}
	}

	public void confirmOrder() {
		getFactory().confirmOrder(this.getDNI());
	}

	public String toString() {
		return super.toString() + " Phone: " + telephone;
	}
}
