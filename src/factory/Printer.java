package factory;

public class Printer {

	public static void mainFunction() {
		System.out.println("Artisan Factory");
		System.out.println("1. People");
		System.out.println("2. Furniture");
		System.out.println("3. Orders");
		System.out.println("4. Exit");
		System.out.println("Enter the number: ");
	}

	// TODO switch add client
	public static void peopleFunction() {
		System.out.println("People Functions");
		System.out.println("1. Boss");
		System.out.println("2. Salesman");
		System.out.println("3. Craftsman");
		System.out.println("4. Client");
		System.out.println("5. Exit");
		System.out.println("Enter the number: ");
	}

	public static void bossFunction() {
		System.out.println("Boss Functions");
		System.out.println("1. Add new boss");
		System.out.println("2. Modify Boss data");
		System.out.println("3. Assign Crafstman");
		System.out.println("4. Exit");
		System.out.println("Enter the number: ");
	}

	public static void salesmanFunction() {
		System.out.println("Salesman Function");
		System.out.println("1. Add new Salesman");
		System.out.println("2. Modify commercial data");
		System.out.println("3. Exit");
		System.out.println("Enter the number: ");
	}

	public static void craftmanFunction() {
		System.out.println("Craftman Function");
		System.out.println("1. Add new Craftman");
		System.out.println("2. Modify craftman data");
		System.out.println("3. Add a new status of the order");
		System.out.println("4. Exit");
		System.out.println("Enter the number: ");
	}

	public static void menuContractCraftsman() {
		System.out.println("Type of contract");
		System.out.println("1. Contract in staff");
		System.out.println("2. Hourly Contract");
		System.out.println("3. Exit");
		System.out.println("Enter the number: ");
	}

	// TODO add switch client
	public static void clientFunction() {
		System.out.println("Client Function");
		System.out.println("1. Add new client");
		System.out.println("2. Modify client data");
		System.out.println("3. Confirm order");
		System.out.println("4. Exit");
		System.out.println("Enter the number: ");
	}

	public static void furnitureFunction() {
		System.out.println("Furniture Function");
		System.out.println("1. Add new furniture");
		System.out.println("2. Modify furniture data");
		System.out.println("3. Exit");
		System.out.println("Enter the number: ");
	}

	public static void orderFunction() {
		System.out.println("Order Function");
		System.out.println("1. Add new order");
		System.out.println("2. Modify order data");
		System.out.println("3. Exit");
		System.out.println("Enter the number: ");
	}

	public static void furnitureTypes() {
		System.out.println("furniture types");
		System.out.println("1. Chair");
		System.out.println("2. Table");
		System.out.println("3. Exit");
		System.out.println("Insert the number: ");
	}

	// TODO case exit
	public static void menuChairs() {
		System.out.println("Types of chairs");
		System.out.println("1. Folding Chair");
		System.out.println("2. Kitchen Chair");
		System.out.println("3. Office Chair");
		System.out.println("4. Exit");
		System.out.println("Insert the number: ");
	}

	public static void menuOfficeChair() {
		System.out.println("Types of office chair");
		System.out.println("1. Chair with wheels");
		System.out.println("2. Chair without wheels");
		System.out.println("3. Exit");
		System.out.println("Insert the number: ");
	}

	public static void menuTables() {
		System.out.println("Types of tables");
		System.out.println("1. Bedroom");
		System.out.println("2. Coffee");
		System.out.println("3. Dining");
		System.out.println("Insert the number: ");
	}

	public static void menuCoffeeTable() {
		System.out.println("Types of coffee table");
		System.out.println("1. Cristal");
		System.out.println("2. Wooden");
		System.out.println("Insert the number: ");
	}

	public static void menuClient() {
		System.out.println("Types of Client");
		System.out.println("1. Private");
		System.out.println("2. Company");
		System.out.println("Insert the number: ");
	}

	public static void idMenu() {
		System.out.println("Menu ID/CIF");
		System.out.println("1. Insert");
		System.out.println("2. Exit");
		System.out.println("Insert the number: ");
	}

	public static void askFeatures() {
		System.out.println("Furniture features");
		System.out.println("1. add features");
		System.out.println("2. do not add features");
		System.out.println("Insert the number: ");
	}

	public static void askMoreFurnitureInTheOrder() {
		System.out.println("Do you want more furniture?: ");
		System.out.println("1. Yes");
		System.out.println("2. No");
	}

	public static void confirmOrder() {
		System.out.println("Do you want to confirm this order?: ");
		System.out.println("1. Yes");
		System.out.println("2. No");
	}
}
