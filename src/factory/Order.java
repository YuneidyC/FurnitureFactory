package factory;

import java.util.LinkedList;
import java.util.List;

public class Order {

	private String id;
	private List<Order> orders = new LinkedList<Order>();
	private String furniture;
	private String employeeAssigned = null;
	private String status;

	private double price;

	public Order(String id, String furniture, double price) {
		this.id = id;
		this.furniture = furniture;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFurniture() {
		return furniture;
	}

	public void setFurniture(String furniture) {
		this.furniture = furniture;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getEmployeeAssigned() {
		return employeeAssigned;
	}

	public void setEmployeeAssigned(String employeeAssigned) {
		this.employeeAssigned = employeeAssigned;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
