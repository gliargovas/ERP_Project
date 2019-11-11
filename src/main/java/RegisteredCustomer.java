import java.util.ArrayList;
public class RegisteredCustomer extends Customer {
	//instance variables
	private int id;
	private int points;
	// counts the number of Registered Customers created
	private static int idcounter;
	private static ArrayList<RegisteredCustomer> customers = new ArrayList<RegisteredCustomer>();
	// new registered customer
	public RegisteredCustomer(String name, String surname, String adress, int telephone, int points) {
			super(name, surname, adress, telephone);
			this.points = points;
			this.id = ++idcounter;
			//adding registered customer to arraylist
			customers.add(this);
			idcounter++;
			//adding customer to csv file
			FileHandler.writeToCSV(this);
		}
	//Constructor for loading customers read from .csv file
	//The id is already associated the customer
	public RegisteredCustomer(String name, String surname, String adress, int telephone, int id, int points) {
		super(name, surname, adress, telephone);
		this.id = id;
		this.points = points;
	}
	@Override
	public String toString() {
		return super.toString()+" id=" + id + ", points=" + points + "]";
	}
}