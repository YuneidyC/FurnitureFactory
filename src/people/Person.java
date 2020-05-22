package people;

import factory.Factory;
import factory.Printer;

public abstract class Person {
	private Factory factory;
	private String DNI;
	private String name;

	public Person(Factory factory, String dni, String name) {
		this.factory = factory;
		this.DNI = dni;
		this.name = name;
	}

	protected Factory getFactory() {
		return factory;
	}

	public String getDNI() {
		return DNI;
	}

	public String getName() {
		return name;
	}

//	If an attribute to be modify is empty, it'll be left unchanged 
	public void modifyData() {
		String dniPerson = this.DNI;
		System.out.println("Insert new DNI:");
		if (!factory.sc.hasNextLine()) {
			System.out.println("Nothing has been inserted.");
			return;
		}
		String str = factory.sc.nextLine();
		if (!str.isEmpty()) {
			DNI = str;
			if ((this instanceof Client)) {
				factory.onClientDNIUpdate(dniPerson, str);
			}
		}
		System.out.println("Insert new NAME");
		if (!factory.sc.hasNextLine()) {
			System.out.println("Nothing has been inserted.");
			return;
		}
		str = factory.sc.nextLine();
		if (!str.isEmpty()) {
			name = str;
		}
	}

	// TODO BUCLE
	public int chooseIdOrder() {
		int idOrder = -1;
		System.out.println("Choose the order ID: ");
		if (!factory.sc.hasNextLine()) {
			System.out.println("Nothing has been inserted.");
			return -1;
		}
		String IDorder = factory.sc.nextLine();
		try {
			idOrder = Integer.parseInt(IDorder);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return -1;
		}
		return idOrder;
	}

	// ARREGLAR ESTE TOSTRING QUE AL FINAL NO ME SALGA UN \N CUANDO LLEGUE AL ULTIMO
	public String toString() {
		return "DNI/PASSPORT: " + DNI + " Name: " + name + "\n";
	}

}
