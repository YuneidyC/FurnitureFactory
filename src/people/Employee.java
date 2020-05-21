package people;

import factory.Factory;

public class Employee extends Person {

	public Employee(Factory factory, String DNI, String name) {
		super(factory, DNI, name);
	}

	public void modifyData() {
		super.modifyData();
	}

	public String toString() {
		return super.toString();
	}
}
