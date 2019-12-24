import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//loadAllListsFromCsv();
		getMainMenu();
	}
	
	/**
	 * Prints the main menu of the ERP SYSTEM
	 */
	public static void printMainMenu() {
		System.out.print("ERP SYSTEM\n\n"
				+ "1) Login as a Cashier\n"
				+ "2  Login as a Storekeeper\n"
				+ "3) Login as a Data Analyst\n"
				+ "4) Login as Administrator\n"
				+ "5) Exit Application\n\n"
				+ "\tAnswer: ");
	}
	
	/**
	 * Select the type you want to connect to 
	 */
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
					FileHandler.writeAllCountersToFile();
					in.close();
					Thread.sleep(100);
					return;
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
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Login as a Cashier
	 */
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
	
	/**
	 * Login as a Storekeeper
	 */
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
	
	/**
	 * Login as a Data Analyst
	 */
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
	
	/**
	 * Login as a Administrator
	 */
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
	
	/**
	 * Saving all the lists to .csv files
	 */
	public static void saveAllListsToCsv() {
		FileHandler.writeOrderListToCSV(Order.getOrders());
		FileHandler.writeProductListToCSV(Storage.getProducts());
		FileHandler.writeProductQuantitiesListToCSV(Storage.getProductQuantities());
		FileHandler.writeUserListToCSV(User.getUsers());
		FileHandler.writeStorageOrderToCSV(StorageOrder.getOrders());
		FileHandler.writeSupplierToCSV(Supplier.getsuppliers());
		FileHandler.writeCustomerListToCSV(RegisteredCustomer.getCustomers());
	}
	
	/**
	 * Loading all the list from the .csv files
	 */
	public static void loadAllListsFromCsv() {
		User.createUsersFromList(FileHandler.getUsersFromCsv());
		Product.createProductsFromList(FileHandler.getProductsFromCsv());
		Storage.createProductQuantitiesFromList(FileHandler.getProductQuantityFromCsv());
		RegisteredCustomer.createRegisteredCustomersFromList(FileHandler.getRegisteredCustomersFromCsv());
		Order.createOrdersFromList(FileHandler.getOrdersFromCsv());
		Supplier.createSuppliersFromList(FileHandler.getSuppliersFromCsv());
		StorageOrder.createStorageOrdersFromList(FileHandler.getStorageOrdersFromCsv());
	}

}
