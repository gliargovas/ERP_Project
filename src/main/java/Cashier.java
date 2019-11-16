
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
	
	@Override
	public void getMenu() {
		
	}
}