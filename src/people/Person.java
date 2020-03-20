package people;

import factory.Factory;

public abstract class Person {
	private String id;
	private String name;

	public Person(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

//	If an attribute to be modify is empty, it'll be left unchanged 
	public void modifyData() {
		System.out.println("Insert new DNI:");
		String str = Factory.sc.nextLine();
		if (!str.isEmpty()) {
			id = str;
		}
		System.out.println("Insert new NAME");
		str = Factory.sc.nextLine();
		if (!str.isEmpty()) {
			name = str;
		}
	}

//	@Override
	public String toString() {
		return id + ": " + name;
	}
}
