import java.util.Scanner;
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
				if (username.equals(user.getName()) && password.equals(user.getPassword())) {
					storekeeper = (Storekeeper)user;
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
		int yes=0;
		Scanner sc=new Scanner(System.in); 
		try{ 
			while(yes==0){ 
		  	printMenu();
	        System.out.print("Enter your choice:"); 
	        ch=sc.nextInt();
	        sc.nextLine();
	        switch(ch){ 
	        case 1: 
	        	getStorageMenu();
	        	break; 
	        case 2: 
	        	getSupplierMenu();
	        	break; 
	        case 3: 
	        	StorageOrder.makeStorageOrder();
	        	break; 
	        case 4:
	        	return;
	        default: System.out.println("Invalid choice!"); 
	        } 
	        System.out.print("Continue? Press 0 to continue:"); 
	        yes=sc.nextInt();
	        }
		}catch(Exception e) {
			return;
		}
	}		
}

