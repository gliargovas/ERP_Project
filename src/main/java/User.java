import java.util.ArrayList;
import java.util.NoSuchElementException;
public abstract class User {
	
	private final int idUser;
	private final String name;
	private final String surname;
	private final String username;
	private String password;
	
	private static ArrayList <User> users = new ArrayList<User>();
	
	private static int userIdCounter = FileHandler.getUserCounterFromFile();
	
	//constructor for new users
	public User(String name, String surname, String username, String password) {
		this.idUser = ++userIdCounter;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		users.add(this);
	}
	
	//constructor for user from .csv file
	public User(int idUser, String name, String surname, String username, String password) {
		this.idUser = idUser;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		users.add(this);
	}
	
	/**
	 * Returns the Id counter of the User
	 * @return id
	 */
	public static int getUserIdCounter() {
		return userIdCounter;
	}
	
	/**
	 * Returns the Id of the User
	 * @return id
	 */
	public int getIdUser() {
		return idUser;
	}
	
	/**
	 * Returns the name of the User
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the Surname of the User
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Returns the Username of the User
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Returns the Password of the User
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Returns all the users from the List users
	 * @return users
	 */
	public static ArrayList<User> getUsers() {
		return users;
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d  Name: %s  Surname: %s  Username: %s", getIdUser(), getName(), getSurname(), getUsername());
	}
	public abstract void getMenu();
	
	/**
	 * Creates the users from the list according to the type
	 * @param users
	 */
	public static void createUsersFromList(ArrayList<ArrayList<String>> users) {
		//TODO: Change fileHandler writeToCsv(User) administrator names and add username & password
		int id;
		String name, surname, username, password, type;
		double salePrice;
		for (ArrayList<String> user : users) {
			type = user.get(0);
			id = Integer.parseInt(user.get(1));
			name = user.get(2);
			surname = user.get(3);
			username = user.get(4);
			password = user.get(5);
			if (type.equals("Cashier")) {
				new Cashier(id, name, surname, username, password);
			} else if (type.equals("Storekeeper")) {
				new Storekeeper(id, name, surname, username, password);
			} else if (type.equals("DataAnalyst")) {
				new DataAnalyst(id, name, surname, username, password);
			} else if (type.equals("Admin")) {
				new Administrator(id, name, surname, username, password);
			}
		}
	}
	
	/**
	 * Searches the user according to the id that is given
	 * @param id
	 * @return User
	 * @throws NoSuchElementException
	 */
	public static User searchUserById(int id) throws NoSuchElementException {
		for (User user : users) {
			if (user.getIdUser() == id) {
				return user;
			}
		}
		throw new NoSuchElementException("User with such id does not exist");
	}
}
