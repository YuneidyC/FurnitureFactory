package people;

import factory.Factory;
import factory.Printer;

public abstract class Person {
	private String dni;
	private String name;

	public Person(String dni, String name) {
		super();
		this.dni = dni;
		this.name = name;
	}

	public String getDNI() {
		return dni;
	}

	public String getName() {
		return name;
	}

//	If an attribute to be modify is empty, it'll be left unchanged 
	public void modifyData() {
		String dniPerson = this.dni;
		System.out.println("Insert new DNI:");
		String str = Factory.sc.nextLine();
		if (!str.isEmpty()) {
			dni = str;
			if ((this instanceof Client)) {
				Factory.onClientDNIUpdate(dniPerson, str);
			}
		}
		System.out.println("Insert new NAME");
		str = Factory.sc.nextLine();
		if (!str.isEmpty()) {
			name = str;
		}
	}

	// TODO BUCLE
	public int chooseIdOrder() {
		int idOrder = -1;
		System.out.println("Choose the order ID: ");
		String IDorder = Factory.sc.nextLine();
		try {
			idOrder = Integer.parseInt(IDorder);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return -1;
		}
		return idOrder;
	}

	public String toString() {
		return "DNI/PASSPORT: " + dni + " Name: " + name;
	}
}
