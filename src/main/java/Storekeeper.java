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
		System.out.println("Storekeeper's Menu");
		System.out.println("press:");
		System.out.println("      1.Storage info");
		System.out.println("	  2.Find an item");
		System.out.println("	  3.Add product");
		System.out.println("	  4.Delete product");
		System.out.println("	  5.Exit");
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
	        	//Method that shows storage info
	        	break; 
	        case 2: 
	        	//Search method 
	        	break; 
	        case 3: 
	        	//Method that add products 
	        	break; 
	        case 4:
	        	//Method that deletes products
	        	break; 
	        case 5:
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

