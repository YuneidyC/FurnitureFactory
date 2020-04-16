package factory;

import java.util.HashMap;

public class Order {

	private static int nextID = 0;
	private int id;
	private int idFurniture;
	private HashMap<Integer, Integer> idsAndPriceFurniture = new HashMap<Integer, Integer>();
	private String dni;
	private String employeeAssigned = null;
	private boolean pendingCustomerConfirmation = false;
	private int items;
	private int totalPrice;

	public Order(String dni) {
		this.id = nextID++;
		this.dni = dni;
	}

	public void addFurniturePiece(int idFurniture, int items) {
		idsAndPriceFurniture.put(idFurniture, items);
	}

	public int getId() {
		return id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public HashMap<Integer, Integer> getIdsAndPriceFurniture() {
		return idsAndPriceFurniture;
	}

	public String getEmployeeAssigned() {
		return employeeAssigned;
	}

	public void setEmployeeAssigned(String employeeAssigned) {
		this.employeeAssigned = employeeAssigned;

	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getIdFurniture() {
		return idFurniture;
	}

	public void setIdFurniture(int idFurniture) {
		this.idFurniture = idFurniture;
	}

	public boolean getPendingCustomerConfirmation() {
		return pendingCustomerConfirmation;
	}

	public void setPendingCustomerConfirmation(boolean pendingCustomerConfirmation) {
		this.pendingCustomerConfirmation = pendingCustomerConfirmation;
	}
}
