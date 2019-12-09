import java.util.InputMismatchException;
import java.util.Scanner;

public class Storekeeper extends User{
	public Storekeeper(String name, String surname, String username, String password) {
		super(name, surname, username, password);
	}
	
	public Storekeeper(int idUser, String name, String surname, String username, String password) {
		super(idUser, name, surname, username, password);
	}
	
	public static void login(String username, String password) throws Exception {
		Storekeeper storekeeper;
		for (User user : User.getUsers()) {
			if (user instanceof Storekeeper) {
				if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					storekeeper = (Storekeeper)user;
					System.out.printf("Welcome %s %s!\n", storekeeper.getName(), storekeeper.getSurname());
					storekeeper.getMenu();
					return;
				}
			}
		}
		throw new Exception("Invalid Credentials");
	}
	private void printMenu() {
		System.out.println("--- Storekeeper Menu ---\n"
				+ "1) Storage\n"
				+ "2) Suppliers\n"
				+ "3) Make a new Storage Ressuply Order\n"
				+ "Option: ");
	}
	
	public static void printStorageMenu() {
		System.out.print("--- Product Menu ---\n"
				+ "1) View All Products\n"
				+ "2) Search for Specific Products (by product name)\n"
				+ "3) Search for Specific Products (by product id)\n"
				+ "4) Return to Previous Menu\n"
				+ "Option: ");
	}
	
	public static void printCustomerMenu() {
		System.out.print("--- Supplier Menu ---\n"
				+ "1) View All Customers\n"
				+ "2) Search for Specific Suppliers (by name)\n"
				+ "3) Search for Specific Suppliers (by Supplier id)\n"
				+ "4) Search for Specific Suppliers (by telephone number)\n"
				+ "5) Add a new Supplier\n"
				+ "6) Edit Supplier Telephone\n"
				+ "7) Edit Supplier Address\n"
				+ "8) View Storage Ressuply Order History\n"
				+ "9) View Specific Storage Ressuply Order History from Specific Supplier\n"
				+ "10) Return to Previous Menu\n"
				+ "Option: ");
	}
	@Override
	public void getMenu() {
		int ch;
		Scanner sc=new Scanner(System.in); 
		for(;;) {
		  	printMenu();
		  	try { 
		  		ch=sc.nextInt();
		  		switch(ch){ 
		  		case 1: 
		  			getStorageMenu();
		  			break; 
		  		case 2: 
		  			getSupplierMenu();
		  			break; 
		  		case 3: 
		  			//StorageOrder.makeStorageOrder();
		  			break; 
		  		case 4:
		  			return;
		  		default: System.out.println("Invalid choice!"); 
		  		} 
			}catch(Exception e) {
				System.err.println("Your option must be an integer number. Try again...");
				// clear scanner buffer
				sc.nextLine();
			}
		}
	}
	public void getStorageMenu() {
		Scanner in = new Scanner(System.in);
		int ans;
		for(;;) {
			printStorageMenu();
			try {
				ans = in.nextInt();
				switch (ans) {
				case 1:
					Storage.printAllProductsWithQuantities();
					break;
				case 2:
					Storage.searchAndPrintProductsByNameMenu();
					break;
				case 3:
					Storage.searchAndPrintProductByIdMenu();
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
	public void getSupplierMenu() {
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
					RegisteredCustomer.searchAndPrintCustomerByNameMenu();
					break;
				case 3:
					RegisteredCustomer.searchAndPrintCustomerByIdMenu();
					break;
				case 4:
					RegisteredCustomer.searchAndPrintCustomerByTelephoneMenu();
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
					//TODO method that displays order history
					break;
				case 9:
					//TODO method that displays specific customer order history
					break;
				case 10:
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

