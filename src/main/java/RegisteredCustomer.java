import java.util.ArrayList;
public class RegisteredCustomer extends Customer {
	//instance variables
	private int id;
	private int points;
	// counts the number of Registered Customers created
	private static int idcounter;
	private static ArrayList<RegisteredCustomer> customers = new ArrayList<RegisteredCustomer>();
	// new registered customer
	public RegisteredCustomer(String name, String adress, int telephone, int points) {
			super(name, adress, telephone);
			this.points = points;
			this.id = ++idcounter;
			//adding registered customer to arraylist
			customers.add(this);
			idcounter++;
			//adding customer to csv file
			FileHandler.writeToCSV(this);
		}
	//getters and setters
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getId() {
		return id;
	}
	//Constructor for loading customers read from .csv file
	//The id is already associated the customer
	public RegisteredCustomer(String name, String adress, int telephone, int id, int points) {
		super(name, adress, telephone);
		this.id = id;
		this.points = points;
		customers.add(this);
	}
	@Override
	public String toString() {
		return super.toString()+" id=" + id + ", points=" + points + "]";
	}
	//returns a customer from the Arraylist with a specific id
	public static RegisteredCustomer searchbyId(int id) {
		for (RegisteredCustomer i : customers) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
}
}