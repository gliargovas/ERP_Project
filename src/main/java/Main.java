import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		loadAllListsFromCsv();
		getMainMenu();
	}

	public static void printMainMenu() {
		System.out.print("ERP SYSTEM\n\n"
				+ "1) Login as a Cashier\n"
				+ "2  Login as a Storekeeper"
				+ "3) Login as a Data Analyst "
				+ "4) Login as Administrator\n"
				+ "5) Exit Application\n\n"
				+ "\tAnswer: )");
	}
	
	public static void getMainMenu() {
		Scanner in = new Scanner(System.in);
		int ans;
		for(;;) {
			printMainMenu();
			try {
				ans = in.nextInt();
				in.nextLine();
				switch (ans) {
				case 1: 
					cashierLogin();
					break;
				case 2:
					storekeeperLogin();
					break;
				case 3:
					dataAnalystLogin();
					break;
				case 4:
					administratorLogin();
					break;
				case 5:
					saveAllListsToCsv();
					System.exit(0);
					break;
				default:
					System.out.println("Prease enter a valid option. Try again...");
				}
			}
			catch (InputMismatchException e) {
				in.nextLine();
				System.out.println("Your option must be an integer number. Try again...");
			}
			catch (Exception e) {
				System.out.println("An error occured. Try again...");
			}
		}
	}
	
	public static void cashierLogin() {
		Scanner in = new Scanner(System.in);
		String username, password;
		try {
			System.out.print("Enter your username.\n"
					+ "To exit press \"enter\": ");
			username = in.nextLine();
			if (username.equals("")) {
				System.out.println("Returning to main menu...");
				return;
			}
			System.out.print("Enter your password: ");
			password = in.nextLine();
			Cashier.login(username, password);
		} catch (Exception e) {
			System.out.println("Invalid credentials given. Try again...");
		}
	}
	
	public static void storekeeperLogin() {
		Scanner in = new Scanner(System.in);
		String username, password;
		try {
			System.out.print("Enter your username.\n"
					+ "To exit press \"enter\": ");
			username = in.nextLine();
			if (username.equals("")) {
				System.out.println("Returning to main menu...");
				return;
			}
			System.out.print("Enter your password: ");
			password = in.nextLine();
			Storekeeper.login(username, password);
		} catch (Exception e) {
			System.out.println("Invalid credentials given. Try again...");
		}
	}
	
	public static void dataAnalystLogin() {
		Scanner in = new Scanner(System.in);
		String username, password;
		try {
			System.out.print("Enter your username.\n"
					+ "To exit press \"enter\": ");
			username = in.nextLine();
			if (username.equals("")) {
				System.out.println("Returning to main menu...");
				return;
			}
			System.out.print("Enter your password: ");
			password = in.nextLine();
			DataAnalyst.login(username, password);
		} catch (Exception e) {
			System.out.println("Invalid credentials given. Try again...");
		}
	}
	
	public static void administratorLogin() {
		Scanner in = new Scanner(System.in);
		String username, password;
		try {
			System.out.print("Enter your username.\n"
					+ "To exit press \"enter\": ");
			username = in.nextLine();
			if (username.equals("")) {
				System.out.println("Returning to main menu...");
				return;
			}
			System.out.print("Enter your password: ");
			password = in.nextLine();
			Administrator.login(username, password);
		} catch (Exception e) {
			System.out.println("Invalid credentials given. Try again...");
		}
	}
	
	private static void saveAllListsToCsv() {
		//TODO Complete the method when all the classes have been connected
		FileHandler.writeToCSV(Order.getOrders());
		FileHandler.writeToCSV(Storage.getProducts());
		FileHandler.writeToCSV(Storage.getIdQuantities());
		FileHandler.writeToCSV(User.getUsers());
		FileHandler.writeToCSV(StorageOrder.getStorageOrders());
		FileHandler.writeToCSV(Supplier.getSuppliers());
		FileHandler.writeToCSV(RegisteredCustomer.getRegisteredCustomers());
	}
	
	private static void loadAllListsFromCsv() {
		// TODO Complete the method when all the classes have been connected
		User.createUsersFromList(FileHandler.getUsersFromCsv());
		Product.createProductsFromList(FileHandler.getProductsFromCsv());
		Order.createOrdersFromList(FileHandler.getOrdersFromCsv());
		StorageOrder.createOrdersFromList(FileHandler.getStorageOrdersFromCsv());
		Supplier.createStorageOrdersFromList(FileHandler.getSuppliersFromList());
		RegisteredCustomer.createRegisteredCustomersFromList(FileHandler.getRegisteredCustomersFromCsv());
		Storage.createProductQuantitiesFromList(FileHandler.getProductQuantityFromCsv);
	}

}
