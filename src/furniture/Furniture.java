package furniture;

import java.util.LinkedList;
import java.util.List;

import factory.Factory;
import factory.Piece;
import factory.Printer;

public class Furniture {

	private static int nextId = 0;
	private int ID;
	private String furnitureModel;
	private int price;
	private Factory factory;
	private String features;
	private List<String> statusHistory = new LinkedList<String>();
	private List<Piece> missingPieces = new LinkedList<Piece>();

	public Furniture(Factory factory, String furnitureModel, int price, String features) {
		this.ID = nextId++;
		this.factory = factory;
		this.furnitureModel = furnitureModel;
		this.price = price;
		this.features = features;
		statusHistory.add("On hold");
	}

	public int getPrice() {
		return price;
	}

	public int getId() {
		return ID;
	}

	public String getFurniture() {
		return furnitureModel;
	}

	protected Factory getFactory() {
		return factory;
	}

	public String getFeatures() {
		return features;
	}

	public List<String> getStatusHistory() {
		return statusHistory;
	}

	public List<Piece> getMissingPieces() {
		return missingPieces;
	}

//	If an attribute to be modify is empty, it'll be left unchanged 
	public boolean modifyData() {
		boolean modifyPrice = false;
		int priceFurniture = -1;
		System.out.println("Insert Furniture model: ");
		if (!factory.sc.hasNextLine()) {
			System.out.println("Nothing has been inserted.");
			return false;
		}
		String str = getFactory().sc.nextLine();
		if (!str.isEmpty()) {
			furnitureModel = str;
		}
		System.out.println("Insert price: ");
		if (!factory.sc.hasNextLine()) {
			System.out.println("Nothing has been inserted.");
			return false;
		}
		str = getFactory().sc.nextLine();
		if (!str.isEmpty()) {
			try {
				priceFurniture = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return false;
			}
			price = priceFurniture;
			modifyPrice = true;
		}
		System.out.println("Insert features: ");
		if (!factory.sc.hasNextLine()) {
			System.out.println("Nothing has been inserted.");
			return false;
		}
		str = getFactory().addOrNotFeatures();
		if (!str.isEmpty()) {
			features = str;
		}
		return modifyPrice;
	}

	public void addStatus(int option, String status) {
		if (option == 3) {
			if (missingPieces.isEmpty()) {
				fillMissingPieces(status);
			}
			// The list must be filled, or there are not missing pieces.
			if (missingPieces.isEmpty()) {
				System.out.println("Status has not been updated.");
				return;
			}
			statusHistory.add(status);
			System.out.println("Status has been updated.");
			return;
		}
		if (missingPieces.isEmpty()) {
			statusHistory.add(status);
			System.out.println("Status has been updated.");
		}
	}

	public void fillMissingPieces(String status) {
		boolean mustExit = false;
		Piece piece = null;
		int insert = -1;
		while (!mustExit) {
			Printer.orderParts();
			if (!factory.sc.hasNextLine()) {
				System.out.println("Nothing has been inserted.");
				return;
			}
			String str = getFactory().sc.nextLine();
			try {
				insert = Integer.parseInt(str);
			} catch (NumberFormatException exception) {
				Printer.thisIsNotANumber();
				return;
			}
			switch (insert) {
			case 1:
				piece = getFactory().askForPieceDetails();
				if (piece == null) {
					System.out.println("The part was not inserted.");
					mustExit = true;
					break;
				}
				addToList(piece);
				break;
			case 2:
				mustExit = true;
				break;
			default:
				Printer.isNotValidOption();
				continue;
			}
		}
	}

	public void furnitureHistory() {
		System.out.println(toString());
		if (statusHistory.size() == 0) {
			System.out.println("This furniture only has the status On Hold.");
			return;
		}
		System.out.println("History status of this furniture: ");
		this.printHistory();
	}

	public void addToList(Piece piece) {
		for (Piece reference : missingPieces) {
			if (reference.equals(piece)) {
				System.out.println("This piece is already on the list.");
				return;
			}
		}
		missingPieces.add(piece);
		System.out.println("Added part.");
	}

	public void printHistory() {
		String history = null;
		for (String status : statusHistory) {
			history += status + ", ";
		}
		System.out.println(history.substring(0, history.length() - 2));
	}

	public String toString() {
		return "ID: " + ID + " \nModel: " + furnitureModel;
	}

}
