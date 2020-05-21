package factory;

import java.util.HashMap;

public class Order {

	private static int nextID = 0;
	private int id;
	private HashMap<Integer, Integer> idsAndItemsFurniture = new HashMap<Integer, Integer>();
	private String DNIClient;
	private String employeeAssigned = null;
	private boolean pendingCustomerConfirmation = false;
	private int totalPrice;

	public Order(String DNIClient) {
		this.id = nextID++;
		this.DNIClient = DNIClient;
	}

	public void addFurnitureIDAndItems(int idFurniture, int items) {
		idsAndItemsFurniture.put(idFurniture, items);
	}

	public int getId() {
		return id;
	}

	public String getDNIClient() {
		return DNIClient;
	}

	public void setDNIClient(String DNIClient) {
		this.DNIClient = DNIClient;
	}

	public HashMap<Integer, Integer> getIdsAndItemsFurniture() {
		return idsAndItemsFurniture;
	}

	public String getEmployeeAssigned() {
		return employeeAssigned;
	}

	public void setEmployeeAssigned(String employeeAssigned) {
		this.employeeAssigned = employeeAssigned;

	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean getPendingCustomerConfirmation() {
		return pendingCustomerConfirmation;
	}

	public void setPendingCustomerConfirmation(boolean pendingCustomerConfirmation) {
		this.pendingCustomerConfirmation = pendingCustomerConfirmation;
	}

	public String toString() {
		return "ID: " + id;
	}
}
