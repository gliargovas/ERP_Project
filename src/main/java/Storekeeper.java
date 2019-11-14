
public class Storekeeper extends User{
	public Storekeeper(int idUser, String name, String surname, String username, String password) {
		super(idUser, name, surname, username, password);
	}
	private void showMenu() {
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
		   
		  
		}
		
	}

