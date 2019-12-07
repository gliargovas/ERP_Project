
public class Administrator extends User {
	public Administrator(String name, String surname, String username, String password) {
		super(name, surname, username, password);
	}
	
	public Administrator(int idUser, String name, String surname, String username, String password) {
		super(idUser, name, surname, username, password);
	}
	
	public static void login(String username, String password) throws Exception {
		Administrator admin;
		for (User user : User.getUsers()) {
			if (user instanceof Administrator) {
				if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					admin = (Administrator)user;
					System.out.printf("Welcome %s %s!\n", admin.getName(), admin.getSurname());
					admin.getMenu();
					return;
				}
			}
		}
		throw new Exception("Invalid Credentials");
	}

	@Override
	public void getMenu() {
		//TODO Complete the method
		System.out.println("Admin Menu");
	}

}
