public class DataAnalyst extends User {
	public DataAnalyst(String name, String surname, String username, String password) {
		super(name, surname, username, password);
	}
	
	public DataAnalyst(int idUser, String name, String surname, String username, String password) {
		super(idUser, name, surname, username, password);
	}
	
	public static void login(String username, String password) throws Exception {
		DataAnalyst da;
		for (User user : User.getUsers()) {
			if (user instanceof DataAnalyst) {
				if (username.equals(user.getName()) && password.equals(user.getPassword())) {
					da = (DataAnalyst)user;
					da.getMenu();
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