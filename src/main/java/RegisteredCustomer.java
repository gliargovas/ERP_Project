import java.util.ArrayList;
import java.util.NoSuchElementException;

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
	public static RegisteredCustomer searchById(int id) {
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
		for (RegisteredCustomer c : customers) {
			System.out.println(c);
		}
	}
	public static void searchAndPrintRegisteredCustomersByName(String name) {
		boolean found = false;
		for (RegisteredCustomer c : customers) {
			if (c.getCompanyName().toLowerCase().contains(name.toLowerCase())) {
				found = true;
				System.out.println(c);
			}
		}
		if (found == false) {
			System.out.println("No customers with such name");
		}
	}
	// Deletes a customer from list
		public static void deleteRegisteredCustomer(int id) throws NoSuchElementException {
			RegisteredCustomer customer = searchById(id);
			if (customer == null) {
				throw new NoSuchElementException("Product with such id does not exist");
			}
				customers.remove(customers.indexOf(customer));
		}
		//checks if a Registered Customer already exists and if it does, increases his points
		public static void addPoints(int id, int quantity) throws NoSuchElementException{
			for (RegisteredCustomer i : customers) {
				if(i.getId() == id) {
					i.setPoints(i.getPoints() + quantity);
					return;
				}
			}
			throw new NoSuchElementException("Product with such id does not exist");
		}
}