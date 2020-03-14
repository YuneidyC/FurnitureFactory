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

	public static void peopleFunction() {
		System.out.println("People Functions");
		System.out.println("1. Boss");
		System.out.println("2. Salesman");
		System.out.println("3. Craftsman");
		System.out.println("4. Exit");
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
		System.out.println("4. Exit");
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
		System.out.println("furniture types: ");
		System.out.println("1. Chair");
		System.out.println("2. Table");
		System.out.println("Insert the number: ");
	}

	public static void printTypesOfChairs() {
		System.out.println("Types of chairs: ");
		System.out.println("1. Folding Chair");
		System.out.println("2. Kitchen Chair");
		System.out.println("3. Office Chair");
		System.out.println("Insert the number: ");
	}

	public static void printTypesOfOfficeChair() {
		System.out.println("Types of office chair: ");
		System.out.println("1. Chair with wheels");
		System.out.println("2. Chair without wheels");
		System.out.println("Insert the number: ");
	}

	public static void printTypesOfTables() {
		System.out.println("Types of tables: ");
		System.out.println("1. Bedroom");
		System.out.println("2. Coffee");
		System.out.println("3. Dining");
		System.out.println("Insert the number: ");
	}

	public static void printTypesOfCoffeeTable() {
		System.out.println("Types of coffee table: ");
		System.out.println("1. Cristal");
		System.out.println("2. Wooden");
		System.out.println("Insert the number: ");
	}

	public static void printTypesOfClient() {
		System.out.println("Types of Cliente");
		System.out.println("1. Private");
		System.out.println("2. Company");
		System.out.println("Insert the number: ");
	}
}
