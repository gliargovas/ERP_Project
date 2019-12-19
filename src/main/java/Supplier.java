import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Supplier {
	private String Name;
	private int Id;
	private int Tel;
	private String Address;
	private static int idCounter;
	private static ArrayList<Supplier> Suppliers = new ArrayList<Supplier>();

	public Supplier(String name, String address, int tel, int id) {
		super();
		Name = name;
		Id = id;
		Tel = tel;
		Address = address;
		Suppliers.add(this);
	}

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

	public static void searchAndPrintSupplierById(int id) {
		for (Supplier s : Suppliers) {
			if (s.getId() == id) {
				System.out.println(s);
				return;
			}
		}
		System.out.println("No suppliers with such Id");
	}

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

	public static void changeName(int id, String name) throws NoSuchElementException {
		Supplier supplier = searchbyId(id);
		if (supplier == null) {
			throw new NoSuchElementException("Supplier with such id does not exist");
		} else {
			supplier.setName(name);
		}
	}

	public static void changeAddress(int id, String address) throws NoSuchElementException {
		Supplier supplier = searchbyId(id);
		if (supplier == null) {
			throw new NoSuchElementException("Customer with such id does not exist");
		} else {
			supplier.setAddress(address);
		}
	}

	public static void changeTelephone(int id, int tel) throws NoSuchElementException {
		Supplier supplier = searchbyId(id);
		if (supplier == null) {
			throw new NoSuchElementException("Suppliers with such id does not exist");
		} else {
			supplier.setTel(tel);
		}
	}

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

	@Override
	public String toString() {
		return "Supplier [Name=" + Name + ", Id=" + Id + ", Tel=" + Tel + ", Address=" + Address + "]";
	}

}
