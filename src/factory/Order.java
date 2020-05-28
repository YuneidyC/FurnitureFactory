package factory;

import java.util.HashMap;

import people.Craftsman;

public class Order {

	private static int nextID = 0;
	private int ID;
	private HashMap<Integer, Integer> IDsAndItemsFurniture = new HashMap<Integer, Integer>();
	private String DNIClient;
	private String employeeAssigned = null;
	private boolean pendingCustomerConfirmation = false;
	private int totalPrice;

	public Order(String DNIClient) {
		this.ID = nextID++;
		this.DNIClient = DNIClient;
	}

	public void addFurnitureIDAndItems(int IDFurniture, int items) {
		IDsAndItemsFurniture.put(IDFurniture, items);
	}

	public int getId() {
		return ID;
	}

	public String getDNIClient() {
		return DNIClient;
	}

	public void setDNIClient(String DNIClient) {
		this.DNIClient = DNIClient;
	}

	public HashMap<Integer, Integer> getIdsAndItemsFurniture() {
		return IDsAndItemsFurniture;
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

	public boolean isAssignedToCraftsman(Craftsman craftsman) {
		if (getEmployeeAssigned().equals(craftsman.getDNI())) {
			return true;
		}
		return false;
	}

	public String toString() {
		return "ID: " + ID;
	}
}
