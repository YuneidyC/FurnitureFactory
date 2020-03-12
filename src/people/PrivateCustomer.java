package people;

public class PrivateCustomer extends Client {

	private String dni;
	private String name;

	public PrivateCustomer(String dni, String name) {
		super(dni, name);
		this.dni = dni;
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void modifyData() {
		super.modifyData();
	}
}
