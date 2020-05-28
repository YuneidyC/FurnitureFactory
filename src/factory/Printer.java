package factory;

public class Printer {

	public static void mainFunction() {
		System.out.println("**Artisan Factory**");
		System.out.println("1. People.");
		System.out.println("2. Furniture.");
		System.out.println("3. Orders.");
		System.out.println("4. Factory.");
		exitOption(5);
	}

	public static void peopleFunction() {
		System.out.println("--People Functions--");
		System.out.println("1. Boss.");
		System.out.println("2. Salesman.");
		System.out.println("3. Craftsman.");
		System.out.println("4. Client.");
		exitOption(5);
	}

	public static void bossFunction() {
		System.out.println("--Boss Functions--");
		System.out.println("1. Assign Crafstman.");
		exitOption(2);
	}

	public static void salesmanFunction() {
		System.out.println("--Salesman Function--");
		System.out.println("1. Add new salesman.");
		System.out.println("2. Modify salesman data.");
		System.out.println("3. Notify the customer.");
		// Show the price to salesman -> notify client.
		System.out.println("4. Show order's price.");
		exitOption(5);
	}

	public static void craftmanFunction() {
		System.out.println("--Craftman Function--");
		System.out.println("1. Add new Craftman.");
		System.out.println("2. Modify craftman data.");
		System.out.println("3. New status of furniture.");
		System.out.println("4. Finished order.");
		exitOption(5);
	}

	public static void menuContractCraftsman() {
		System.out.println("--Type of contract--");
		System.out.println("1. Contract in staff.");
		System.out.println("2. Hourly Contract.");
		exitOption(3);
	}

	public static void clientFunction() {
		System.out.println("--Client Function--");
		System.out.println("1. Modify client data.");
		System.out.println("2. Confirm order.");
		exitOption(3);
	}

	public static void furnitureFunction() {
		System.out.println("--Furniture Function--");
		System.out.println("1. Modify furniture data.");
		exitOption(2);
	}

	public static void orderFunction() {
		System.out.println("--Order Function--");
		System.out.println("1. Add new order.");
		exitOption(2);
	}

	public static void furnitureTypes() {
		System.out.println("--Furniture types--");
		System.out.println("1. Chair.");
		System.out.println("2. Table.");
		exitOption(3);
	}

	public static void chairsTypes() {
		System.out.println("Types of chairs");
		System.out.println("1. Folding Chair.");
		System.out.println("2. Kitchen Chair.");
		System.out.println("3. Office Chair.");
		exitOption(4);
	}

	public static void officeChairTypes() {
		System.out.println("--Types of office chair--");
		System.out.println("1. Chair with wheels.");
		System.out.println("2. Chair without wheels.");
		exitOption(3);
	}

	public static void tableTypes() {
		System.out.println("--Types of tables--");
		System.out.println("1. Bedroom.");
		System.out.println("2. Coffee.");
		System.out.println("3. Dining.");
		exitOption(4);
	}

	public static void coffeeTableTypes() {
		System.out.println("--Types of coffee table--");
		System.out.println("1. Cristal.");
		System.out.println("2. Wooden.");
		exitOption(3);
	}

	public static void clientTypes() {
		System.out.println("--Types of Client--");
		System.out.println("1. Private.");
		System.out.println("2. Company.");
		insertOption();
	}

	public static void DNIMenu() {
		System.out.println("1. Insert.");
		exitOption(2);
	}

	public static void askFeatures() {
		System.out.println("--Furniture features--");
		System.out.println("1. add features.");
		System.out.println("2. do not add features.");
		insertOption();
	}

	public static void askMoreFurnitureInTheOrder() {
		System.out.println("Do you want more furniture?");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		insertOption();
	}

	public static void confirmOrder() {
		System.out.println("Do you want to confirm this order?");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		insertOption();
	}

	public static void assignOrderWithDNIOrRandom() {
		System.out.println("Assign order: ");
		System.out.println("1. With DNI/PASSPORT.");
		System.out.println("2. Random.");
		System.out.println("3. List of Craftsman.");
		exitOption(4);
	}

	public static void statusFurnitureOrder() {
		System.out.println("1. Pending.");
		System.out.println("2. In process.");
		System.out.println("3. Stopped due to missing part.");
		System.out.println("4. Stopped due to customer confirmation.");
		System.out.println("5. Test phase.");
		System.out.println("6. Finished.");
		exitOption(7);
	}

	public static void orderParts() {
		System.out.println("1. Add new piece.");
		exitOption(2);
	}

	public static void factorySwitch() {
		System.out.println("List of: ");
		System.out.println("1. Parts missing for manufacturing.");
		System.out.println("2. Chip processed by each craftsman.");
		System.out.println("3. Confirmation to request the client.");
		System.out.println("4. Report in process.");
		System.out.println("5. Craftsman history.");
		System.out.println("6. Furniture history.");
		exitOption(7);
	}

	public static void craftsmanHistory() {
		System.out.println("1. An craftsman.");
		System.out.println("2. All craftsmans.");
		exitOption(3);
	}

	public static void furnitureHistory() {
		System.out.println("1. An furniture.");
		System.out.println("2. All furnitures.");
		exitOption(3);
	}

	/**
	 * @brief Avoid repeating this code at the end of each menu
	 */
	public static void exitOption(int option) {
		System.out.println(option + "." + " Exit.");
		insertOption();
	}

	public static void insertOption() {
		System.out.println("Insert option: ");
	}

	public static void isNotValidOption() {
		System.out.println("This is not a valid option.");
	}

	public static void thisIsNotANumber() {
		System.out.println("This is not a number.");
	}

	public static void nothingHasBeenInserted() {
		System.out.println("Nothing has been inserted.");
	}
}
