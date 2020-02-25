package people;

public class PrivateCustomer extends Client {

	private String dni;
	private String order;

	public PrivateCustomer(String dni, String order) {
		this.dni = dni;
		this.order = order;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
