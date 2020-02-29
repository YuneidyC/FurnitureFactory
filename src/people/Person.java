package people;

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

	// @Override
	public String toString() {
		return id + ": " + name;
	}
}
