package ERP_Core;

/**
 * The class represents a supplier of the ERP system The class is responsible
 * for creating a supplier object and storing it in an ArrayList either by
 * creating a completely new supplier or loading it from a .csv file. It also
 * contains methods for searching and editing the contents of the suppliers list
 * with specific criteria. There are also methods that contain menus in order to
 * use the above mentioned methods according to user input.
 * 
 * @author chris
 *
 */
public class Supplier {
	private String Name;
	private int Id;
	private int Tel;
	private String Address;
	private static int idCounter;
	private static ArrayList<Supplier> Suppliers = new ArrayList<Supplier>();

	/**
	 * Constructor for loading objects from .csv file
	 * 
	 * @param name
	 * @param address
	 * @param tel
	 * @param id
	 */
	public Supplier(String name, String address, int tel, int id) {
		super();
		Name = name;
		Id = id;
		Tel = tel;
		Address = address;
		Suppliers.add(this);
	}

	/**
	 * Constructor for creating new Supplier objects
	 * 
	 * @param name
	 * @param tel
	 * @param address
	 */
	public Supplier(String name, int tel, String address) {
		Name = name;
		Id = ++idCounter;
		Tel = tel;
		Address = address;
		Suppliers.add(this);
		idCounter++;
	}

	/**
	 * Returns the name of the Supplier
	 * 
	 * @return the Name of supplier , type String
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Sets the name of the Supplier according to the parameter that is given
	 * 
	 * @param name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Returns the id of the supplier
	 * 
	 * @return Id of the supplier, type integer
	 */
	public int getId() {
		return Id;
	}

	/**
	 * Sets the id of the Supplier according to the parameter that is given
	 * 
	 * @param id
	 */
	public void setId(int id) {
		Id = id;
	}

	/**
	 * Returns the telephone of the supplier
	 * 
	 * @return the Telephone, type integer
	 */
	public int getTel() {
		return Tel;
	}

	/**
	 * Sets the number of the telephone according to the parameter that is given
	 * 
	 * @param tel
	 */
	public void setTel(int tel) {
		Tel = tel;
	}

	/**
	 * Returns the Address of the supplier
	 * 
	 * @return the Address, type String
	 */
	public String getAddress() {
		return Address;
	}

	/**
	 * Sets the Address of the supplier according to the parameter that is given
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		Address = address;
	}

	/**
	 * Returns all the suppliers from the List Suppliers
	 * 
	 * @return alla the Suppliers from the list
	 */
	public static ArrayList<Supplier> getSuppliers() {
		return Suppliers;
	}

	/**
	 * All the suppliers from the list are appeared
	 */
	public static void alltheSuppliers() {
		for (Supplier k : Suppliers) {
			k.toString();
		}
	}

	/**
	 * Deletes the supplier that the id ,that is given, refers to
	 * 
	 * @param id
	 * @return true if the supplier is removed or false if the id doesn't refer to a
	 *         supplier
	 */
	public static boolean deleteSupplier(int id) {
		for (Supplier i : Suppliers) {
			if (i.getId() == id) {
				Suppliers.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Searches the supplier, which has the id that is given
	 * 
	 * @param id
	 * @return the supplier from the list according its id
	 */
	public static Supplier searchbyId(int id) {
		for (Supplier i : Suppliers) {
			if (i.getId() == id) {
				return i;
			}
		}
		System.out.println("There is no such element");
		return null;
	}

	/**
	 * Creates all the suppliers from the list
	 * 
	 * @param Suppliers
	 */
	public static void createSuppliersFromList(ArrayList<ArrayList<String>> Suppliers) {
		int Id, Tel;
		String Name, Address;
		for (ArrayList<String> supplier : Suppliers) {
			Id = Integer.parseInt(supplier.get(0));
			Name = supplier.get(1);
			Address = supplier.get(2);
			Tel = Integer.parseInt(supplier.get(3));
			new Supplier(Name, Address, Tel, Id);
		}
	}

	/**
	 * Searches the supplier and prints him according to the name that is given
	 * 
	 * @param name, String type
	 */
	public static void searchAndPrintSupplierByName(String name) {
		boolean found = false;
		for (Supplier s : Suppliers) {
			if (s.getName().toLowerCase().contains(name.toLowerCase())) {
				found = true;
				System.out.println(s);
			}
			if (found == false) {
				System.out.println("No suppliers with such name");
			}
		}
	}

	/**
	 * Searches the supplier and prints him according to the address that is given
	 * 
	 * @param address, String type
	 */
	public static void searchAndPrintSupplierByAddress(String address) {
		boolean found = false;
		for (Supplier s : Suppliers) {
			if (s.getAddress().toLowerCase().contains(address.toLowerCase())) {
				found = true;
				System.out.println(s);
			}
		}
		if (found == false) {
			System.out.println("No suppliers with such address");
		}
	}

	/**
	 * Searches the Supplier and prints him according to the id that is given
	 * 
	 * @param id, integer type
	 */
	public static void searchAndPrintSupplierById(int id) {
		for (Supplier s : Suppliers) {
			if (s.getId() == id) {
				System.out.println(s);
				return;
			}
		}
		System.out.println("No suppliers with such Id");
	}

	/**
	 * Searches and prints the supplier according to the telephone that is given
	 */
	public static void searchAndPrintSupplierByTelephone(int tel) {
		boolean found = false;
		for (Supplier s : Suppliers) {
			if (s.getTel() == tel) {
				found = true;
				System.out.println(s);
			}
		}
		if (found == false) {
			System.out.println("No suppliers with such telephone");
		}
	}

	/**
	 * Changes the Name of the Name of the supplier from the list according to the
	 * id and name that is given
	 * 
	 * @param id
	 * @param name
	 * @throws NoSuchElementException
	 */
	public static void changeName(int id, String name) throws NoSuchElementException {
		Supplier supplier = searchbyId(id);
		if (supplier == null) {
			throw new NoSuchElementException("Supplier with such id does not exist");
		} else {
			supplier.setName(name);
		}
	}

	/**
	 * Changes the address of the supplier according to the id and the address that
	 * is given
	 * 
	 * @param id
	 * @param address
	 * @throws NoSuchElementException
	 */
	public static void changeAddress(int id, String address) throws NoSuchElementException {
		Supplier supplier = searchbyId(id);
		if (supplier == null) {
			throw new NoSuchElementException("Customer with such id does not exist");
		} else {
			supplier.setAddress(address);
		}
	}

	/**
	 * Changes the telephone of the supplier according to th id and telephone that
	 * is given
	 * 
	 * @param id
	 * @param tel
	 * @throws NoSuchElementException
	 */
	public static void changeTelephone(int id, int tel) throws NoSuchElementException {
		Supplier supplier = searchbyId(id);
		if (supplier == null) {
			throw new NoSuchElementException("Suppliers with such id does not exist");
		} else {
			supplier.setTel(tel);
		}
	}

	/**
	 * Creates the registration of the supplier according to the values given from
	 * the user
	 */
	public static void registerSupplierMenu() {
		String name, address;
		int tel;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the supplier's name: ");
		name = in.nextLine();
		System.out.print("Enter the supplier's address: ");
		address = (in.nextLine().toLowerCase());
		System.out.print("Enter supplier's telephone: ");
		tel = in.nextInt();
		new Supplier(name, tel, address);
		System.out.printf("Supplier %s registered successfully!\n", name);
	}

	/**
	 * Changes the name of the supplier at the menu according to the id that is
	 * given
	 */
	public static void changeCustomerNameMenu() {
		Scanner in = new Scanner(System.in);
		int id;
		String input, name;
		for (;;) {
			id = 0;
			try {
				System.out.print("Enter the id of the supplier you want to change\nTo cancel, press \"enter\": ");
				input = in.nextLine();
				if (input.equals("")) {
					System.out.println("Process cancelled. Returning to previous menu...");
					return;
				}
				id = Integer.parseInt(input);
				System.out.print("Enter the new name: ");
				name = in.nextLine();
				changeName(id, name);
				break;
			} catch (NumberFormatException e) {
				System.err.println("Invalid input given. Id must be an integer, larger than 0");
			} catch (NoSuchElementException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * Changes the address of the supplier at the menu according to the id that is
	 * given
	 * 
	 */
	public static void changeSupplierAddressMenu() {
		Scanner in = new Scanner(System.in);
		int id;
		String input, address;
		for (;;) {
			id = 0;
			try {
				System.out.print("Enter the id of the supplier you want to change\nTo cancel, press \"enter\": ");
				input = in.nextLine();
				if (input.equals("")) {
					System.out.println("Process cancelled. Returning to previous menu...");
					return;
				}
				id = Integer.parseInt(input);
				System.out.print("Enter the new adress: ");
				address = in.nextLine();
				changeAddress(id, address);
				break;
			} catch (NumberFormatException e) {
				System.err.println("Invalid input given. Id must be an integer, larger than 0");
			} catch (NoSuchElementException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * changes the telephone of the supplier at the menu according to the id that is
	 * given
	 */
	// contains customer telephone change menu
	public static void changeSupplierTelephoneMenu() {
		Scanner in = new Scanner(System.in);
		int id, tel;
		String input;
		for (;;) {
			id = 0;
			try {
				System.out.print("Enter the id of the supplier you want to change\nTo cancel, press \"enter\": ");
				input = in.nextLine();
				if (input.equals("")) {
					System.out.println("Process cancelled. Returning to previous menu...");
					return;
				}
				id = Integer.parseInt(input);
				System.out.print("Enter new telephone: ");
				tel = in.nextInt();
				changeTelephone(id, tel);
				break;
			} catch (NumberFormatException e) {
				System.err.println("Invalid input given. Id must be an integer, larger than 0");
			} catch (InputMismatchException e) {
				System.err.println("Invalid input given. Telephone must be a number");
			} catch (NoSuchElementException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * Deletes the supplier at the menu according to the id that is given
	 * 
	 */
	public static void deleteSupplierMenu() {
		Scanner in = new Scanner(System.in);
		int id;
		String input;
		for (;;) {
			id = 0;
			try {
				System.out.print("Enter the id of the supplier you want to delete\nTo cancel, press \"enter\": ");
				input = in.nextLine();
				if (input.equals(" ")) {
					System.out.println("Process cancelled. Returning to previous menu...");
					return;
				}
				id = Integer.parseInt(input);
				deleteSupplier(id);
				break;
			} catch (NoSuchElementException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * Searches and prints the Supplier according to the name that is given
	 */
	public static void searchAndPrintSupplierByNameMenu() {
		Scanner in = new Scanner(System.in);
		String input;
		for (;;) {
			System.out.print("Enter the name of the supplier you want to print\nTo cancel, press \"enter\": ");
			input = in.nextLine();
			if (input.equals(" ")) {
				System.out.println("Process cancelled. Returning to previous menu...");
				return;
			}
			searchAndPrintSupplierByName(input);
			break;
		}
	}

	/**
	 * Searches and prints the Supplier according to the address that is given
	 */
	public static void searchAndPrintSupplierByAddressMenu() {
		Scanner in = new Scanner(System.in);
		String input;
		for (;;) {
			System.out.print("Enter the address of the supplier you want to print\nTo cancel, press \"enter\": ");
			input = in.nextLine();
			if (input.equals(" ")) {
				System.out.println("Process cancelled. Returning to previous menu...");
				return;
			}
			searchAndPrintSupplierByAddress(input);
			break;
		}
	}

	/**
	 * Searches and prints the Supplier according to the id that is given
	 */
	public static void searchAndPrintSupplierByIdMenu() {
		Scanner in = new Scanner(System.in);
		int id;
		String input;
		for (;;) {
			id = 0;
			try {
				System.out.print("Enter the id of the supplier you want to print\nTo cancel, press \"enter\": ");
				input = in.nextLine();
				if (input.equals(" ")) {
					System.out.println("Process cancelled. Returning to previous menu...");
					return;
				}
				id = Integer.parseInt(input);
				searchAndPrintSupplierById(id);
				break;
			} catch (NumberFormatException e) {
				System.err.println("Invalid input given. Id must be an integer");
			}
		}
	}

/**
 *Searches and prints the Supplier according to the telephone that is given
 */
	public static void searchAndPrintSupplierByTelephoneMenu() {
		Scanner in = new Scanner(System.in);
		int tel;
		String input;
		for (;;) {
			tel = 0;
			try {
				System.out.print("Enter the telephone of the supplier you want to print\nTo cancel, press \"enter\": ");
				input = in.nextLine();
				if (input.equals(" ")) {
					System.out.println("Process cancelled. Returning to previous menu...");
					return;
				}
				tel = Integer.parseInt(input);
				searchAndPrintSupplierByTelephone(tel);
				break;
			} catch (NumberFormatException e) {
				System.err.println("Invalid input given. Telephone must be an integer");
			}
		}
	}

/**
 * Returns the current Supplier object in String format
 */
	@Override
	public String toString() {
		return "Supplier [Name=" + Name + ", Id=" + Id + ", Tel=" + Tel + ", Address=" + Address + "]";
	}
}
