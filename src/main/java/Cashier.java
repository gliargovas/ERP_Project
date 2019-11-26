import java.util.InputMismatchException;
import java.util.Scanner;

public class Cashier extends User {
	
	public Cashier(String name, String surname, String username, String password) {
		super(name, surname, username, password);
	}
	
	public Cashier(int idUser, String name, String surname, String username, String password) {
		super(idUser, name, surname, username, password);
	}
	
	public static void login(String username, String password) throws Exception {
		Cashier cashier;
		for (User user : User.getUsers()) {
			if (user instanceof Cashier) {
				if (username.equals(user.getName()) && password.equals(user.getPassword())) {
					cashier = (Cashier)user;
					cashier.getMenu();
					return;
				}
			}
		}
		throw new Exception("Invalid Credentials");
	}
	
	public static void printProductMenu() {
		System.out.print("--- Product Menu ---\n"
				+ "1) View All Products\n"
				+ "2) Search for Specific Products (by product name)\n"
				+ "3) Search for Specific Products (by product id)\n"
				+ "4) Return to Previous Menu\n"
				+ "Option: ");
	}
	
	public static void printCustomerMenu() {
		System.out.print("--- Customer Menu ---\n"
				+ "1) View All Customers\n"
				+ "2) Search for Specific Customers (by customer name)\n"
				+ "3) Search for Specific Customers (by customer id)\n"
				+ "4) Search for Specific Customers (by telephone number)\n"
				+ "5) Add a new Customer\n"
				+ "6) Edit Customer Telephone\n"
				+ "7) Edit Customer Address\n"
				+ "8) Delete a Customer\n"
				+ "9) View Order History"
				+ "10) View Specific Customer Order History"
				+ "11) Return to Previous Menu\n"
				+ "Option: ");
	}
	
	public static void printMenu() {
		System.out.println("--- Cashier Menu ---\n"
				+ "1) Products\n"
				+ "2) Customers\n"
				+ "3) Make a New Customer Order\n"
				+ "4) Logout and Return to Main Menu\n"
				+ "Option: ");
	}
	
	public void getProductMenu() {
		Scanner in = new Scanner(System.in);
		int ans;
		for(;;) {
			printProductMenu();
			try {
				ans = in.nextInt();
				switch (ans) {
				case 1:
					Storage.printAllProductsWithQuantities();
					break;
				case 2:
					Storage.searchAndPrintProductByIdMenu();
					break;
				case 3:
					Storage.searchAndPrintProductsByNameMenu();
					break;
				case 4:
					return;
				}
			} catch (InputMismatchException e) {
				System.err.println("Your option must be an integer number. Try again...");
				// clear scanner buffer
				in.nextLine();
			}
		}
	}
	
	public void getCustomerMenu() {
		Scanner in = new Scanner(System.in);
		int ans;
		for(;;) {
			printCustomerMenu();
			try {
				ans = in.nextInt();
				switch (ans) {
				case 1:
					RegisteredCustomer.printAllCustomers();
					break;
				case 2:
					RegisteredCustomer.searchAndPrintCustomerByIdMenu();
					break;
				case 3:
					RegisteredCustomer.searchAndPrintRegisteredCustomerByNameMenu();
					break;
				case 4:
					RegisteredCustomer.searchAndPrintCustomersByTelephoneMenu();
					break;
				case 5:
					RegisteredCustomer.registerNewCustomerMenu();
					break;
				case 6:
					RegisteredCustomer.changeCustomerTelephoneMenu();
					break;
				case 7:
					RegisteredCustomer.changeCustomerAddressMenu();
					break;
				case 8:
					RegisteredCustomer.deleteCustomerMenu();
					break;
				case 9:
					//TODO method that displays order history
					break;
				case 10:
					//TODO method that displays specific customer order history
					break;
				case 11:
					return;
				}
			} catch (InputMismatchException e) {
				System.err.println("Your option must be an integer number. Try again...");
				// clear scanner buffer
				in.nextLine();
			}
		}
	}
	
	@Override
	public void getMenu() {
		Scanner in = new Scanner(System.in);
		int ans;
		for(;;) {
			printMenu();
			try {
				ans = in.nextInt();
				switch (ans) {
				case 1:
					getProductMenu();
					break;
				case 2:
					getCustomerMenu();
					break;
				case 3:
					Order.makeOrder();
				case 4:
					return;
				}
			} catch (InputMismatchException e) {
				System.err.println("Your option must be an integer number. Try again...");
				// clear scanner buffer
				in.nextLine();
			}
		}
	}
}