package people;

import java.util.LinkedList;
import java.util.List;

import factory.Factory;

public class Client extends Person {
	private int telephone;
	private List<Integer> orders = new LinkedList<Integer>();

	public Client(String DNI, String name, int telephone) {
		super(DNI, name);
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
		int phoneNumber = Factory.getPhoneNumber();
		if (phoneNumber == -1) {
			System.out.println("The phone could not be modified.");
			return;
		}
		telephone = phoneNumber;
	}

	// TODO EL LA LISTA DE IDSORDERS LA TIENE CADA TIPO
	public void confirmOrder() {
		Factory.confirmOrder(this.getDNI());
	}

	public String toString() {
		return super.toString() + " Phone: " + telephone;
	}
}
