import java.util.ArrayList;
public class RegisteredCustomer extends Customer {
	private int id;
	//points each customer earns depending on his purchases TODO points algorithm
	private int points;
	// counts the number of registered customers created
	private static int idCounter;
	//an @Arraylist in which Customer information is temporarily saved
	private static ArrayList<RegisteredCustomer> customers = new ArrayList<RegisteredCustomer>();
	// new registered customer creation
	public RegisteredCustomer(String name, String address, int telephone, int points) {
			super(name, address, telephone);
			this.points = points;
			this.id = ++idCounter;
			//adding registered customer to @Arraylist
			customers.add(this);
			idCounter++;
	}
	//Constructor for loading customers read from @.csv file
	//The id is already associated with the customer, used when loading information from the database to the program
	public RegisteredCustomer(String name, String address, int telephone, int id, int points) {
		super(name, address, telephone);
		this.id = id;
		this.points = points;
		customers.add(this);
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
	public static ArrayList<RegisteredCustomer> getCustomers() {
		return customers;
	}
	@Override
	public String toString() {
		return super.toString()+" id=" + id + ", points=" + points + "]";
	}
	//returns a customer by Id
	public static RegisteredCustomer searchbyId(int id) {
		for (RegisteredCustomer i : customers) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}
	//creates a new registered customer from an @Arraylist
	public static void createRegisteredCustomersFromList(ArrayList<ArrayList<String>> customers) {
		int id, points, telephone;
		String name, address;
		for (ArrayList<String> customer: customers) {
			id = Integer.parseInt(customer.get(0));
			name = customer.get(1);
			address = customer.get(2);
			telephone = Integer.parseInt(customer.get(3));
			points = Integer.parseInt(customer.get(4));
			new RegisteredCustomer(name, address, telephone, id, points);
		}
	}
	public static void printAllCustomers() {
		for (Customer c : customers) {
			System.out.println(c);
		}
	}
}