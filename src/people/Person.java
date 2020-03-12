package people;

import java.util.Scanner;

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
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert DNI");
		String str = sc.nextLine();
		if (!str.isEmpty()) {
			id = str;
		}
		System.out.println("Insert NAME");
		str = sc.nextLine();
		if (!str.isEmpty()) {
			name = str;
		}
		sc.close();
	}

//	@Override
	public String toString() {
		return id + ": " + name;
	}
}
