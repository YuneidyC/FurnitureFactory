package people;

import factory.Factory;
import factory.Printer;

public abstract class Person {
	private Factory factory;
	private String dni;
	private String name;

	public Person(Factory factory, String dni, String name) {
		this.factory = factory;
		this.dni = dni;
		this.name = name;
	}

	protected Factory getFactory() {
		return factory;
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
		String str = factory.sc.nextLine();
		if (!str.isEmpty()) {
			dni = str;
			if ((this instanceof Client)) {
				factory.onClientDNIUpdate(dniPerson, str);
			}
		}
		System.out.println("Insert new NAME");
		str = factory.sc.nextLine();
		if (!str.isEmpty()) {
			name = str;
		}
	}

	// TODO BUCLE
	public int chooseIdOrder() {
		int idOrder = -1;
		System.out.println("Choose the order ID: ");
		String IDorder = factory.sc.nextLine();
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
