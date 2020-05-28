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
		String dniPerson = DNI;
		System.out.println("Insert new DNI:");
		if (!factory.sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
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
			Printer.nothingHasBeenInserted();
			return;
		}
		str = factory.sc.nextLine();
		if (!str.isEmpty()) {
			name = str;
		}
	}

	public int chooseIdOrder() {
		int IDsOrder = -1;
		System.out.println("Choose the order ID: ");
		if (!factory.sc.hasNextLine()) {
			Printer.nothingHasBeenInserted();
			return -1;
		}
		String IDOrder = factory.sc.nextLine();
		try {
			IDsOrder = Integer.parseInt(IDOrder);
		} catch (NumberFormatException exception) {
			Printer.thisIsNotANumber();
			return -1;
		}
		return IDsOrder;
	}

	public String toString() {
		return "DNI/PASSPORT: " + DNI + " Name: " + name + "\n";
	}

}
