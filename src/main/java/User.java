import java.util.ArrayList;
public abstract class User {
	
	private final int idUser;
	private final String name;
	private final String surname;
	private final String username;
	private final String password;
	
	private static ArrayList <User> users = new ArrayList<User>();
	
	public User(int idUser, String name, String surname, String username, String password) {
		this.idUser = idUser;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		users.add(this);
	}
	public int getIdUser() {
		return idUser;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d  Name: %s  Surname: %s  Username: %s",getIdUser(),getName(),getSurname(),getUsername());
	}
	public abstract void getMenu();
}
