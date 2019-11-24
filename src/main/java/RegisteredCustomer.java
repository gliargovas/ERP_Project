import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
			throw new NoSuchElementException("Registered Customer with such id does not exist");
		}
		// contains the registered customer creation menu TODO Customer menu at superclass
		public static void registerNewCustomerMenu() {
			String name, address;
			int telephone,points;
			Scanner in = new Scanner(System.in);
			System.out.print("Enter the customer's name: ");
			name = in.nextLine();
			System.out.print("Enter the customer's address: ");
			address = (in.nextLine().toLowerCase());
			System.out.print("Enter customer's telephone: ");
			telephone = in.nextInt();
			for (;;) {	
				System.out.print("Enter customer's points: ");
				try {
					points = in.nextInt();
					if (points <= 0) {
						System.out.println("Points must be larger than 0. Try again...");
						continue;
					}
					break;
				} catch(InputMismatchException e) {
					System.err.println("Invalid input given. Points must be a number");
					in.nextLine();
				}
			}
			new RegisteredCustomer(name,address,telephone,points);
			System.out.printf("Customer %s registered successfully!\n", name);
		}
		// contains the customer points change menu
		public static void changeCustomerPointsMenu() {
			Scanner in = new Scanner(System.in);
			int id, points;
			String input;
			for(;;) {
				id = 0;
				try {
					System.out.print("Enter the id of the registered customer you want to change\nTo cancel, press \"enter\": ");
					input = in.nextLine();
					if (input.equals("")) {
						System.out.println("Process cancelled. Returning to previous menu...");
						return;
					}
					id = Integer.parseInt(input);
					System.out.print("Enter new points: ");
					points = in.nextInt();
					searchById(id).setPoints(points);
					break;
				} catch (NumberFormatException e){
					System.err.println("Invalid input given. Id must be an integer, larger than 0");
				} catch (InputMismatchException e) {
					System.err.println("Invalid input given. Points must be a number");
				} catch (NoSuchElementException e) {
					System.err.println(e.getMessage());
					}
				}
		}
		// contains the customer name change menu
		public static void changeCustomerNameMenu() {
			Scanner in = new Scanner(System.in);
			int id;
			String input, name;
			for(;;) {
				id = 0;
				try {
					System.out.print("Enter the id of the registered customer you want to change\nTo cancel, press \"enter\": ");
					input = in.nextLine();
					if (input.equals("")) {
						System.out.println("Process cancelled. Returning to previous menu...");
						return;
					}
					id = Integer.parseInt(input);
					System.out.print("Enter the new name: ");
					name = in.nextLine();
					searchById(id).setName(name);
					break;
				} catch (NumberFormatException e){
					System.err.println("Invalid input given. Id must be an integer, larger than 0");
				} catch (NoSuchElementException e) {
					System.err.println(e.getMessage());
					}
				}
		}
		// contains the customer address change menu
		public static void changeCustomerAddressMenu() {
			Scanner in = new Scanner(System.in);
			int id;
			String input, address;
			for(;;) {
				id = 0;
				try {
					System.out.print("Enter the id of the registered customer you want to change\nTo cancel, press \"enter\": ");
					input = in.nextLine();
					if (input.equals("")) {
						System.out.println("Process cancelled. Returning to previous menu...");
						return;
					}
					id = Integer.parseInt(input);
					System.out.print("Enter the new adress: ");
					address = in.nextLine();
					searchById(id).setAddress(address);
					break;
				} catch (NumberFormatException e){
					System.err.println("Invalid input given. Id must be an integer, larger than 0");
				} catch (NoSuchElementException e) {
					System.err.println(e.getMessage());
					}
				}
		}
}