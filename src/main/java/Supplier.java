import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Supplier {
	private String name;
	private int id;
	private int tel;
	private String address;
	private static int idCounter;
	private static ArrayList<Supplier> suppliers = new ArrayList<Supplier>();

	public Supplier(String name, String address, int tel, int id) {
		super();
		this.name = name;
		this.id = id;
		this.tel = tel;
		this.address = address;
		suppliers.add(this);
	}

	public Supplier(String name, int tel, String address) {
		this.name = name;
		this.id = ++idCounter;
		this.tel = tel;
		this.address = address;
		suppliers.add(this);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static ArrayList<Supplier> getsuppliers() {
		return suppliers;
	}

	public static void printAllSuppliers() {
		for (Supplier k : suppliers) {
			System.out.println(k.toString());
		}
	}

	public static boolean deleteSupplier(int id) {
		for (Supplier i : suppliers) {
			if (i.getId() == id) {
				suppliers.remove(i);
				return true;
			}
		}
		return false;
	}

	public static Supplier searchById(int id) {
		for (Supplier i : suppliers) {
			if (i.getId() == id) {
				return i;
			}
		}
		System.out.println("There is no such element");
		return null;
	}

	public static void createSuppliersFromList(ArrayList<ArrayList<String>> suppliers) {
		int id, tel;
		String name, address;
		for (ArrayList<String> supplier : suppliers) {
			id = Integer.parseInt(supplier.get(0));
			name = supplier.get(1);
			address = supplier.get(2);
			tel = Integer.parseInt(supplier.get(3));
			new Supplier(name, address, tel, id);
		}
	}
	
	public static void searchAndPrintSupplierByname(String name) {
		boolean found = false;
		for (Supplier s : suppliers) {
			if (s.getName().toLowerCase().contains(name.toLowerCase())) {
				found = true;
				System.out.println(s);
			}
			if (found == false) {
				System.out.println("No suppliers with such name");
			}
		}
	}

	public static void searchAndPrintSupplierByaddress(String address) {
		boolean found = false;
		for (Supplier s : suppliers) {
			if (s.getAddress().toLowerCase().contains(address.toLowerCase())) {
				found = true;
				System.out.println(s);
			}
		}
		if (found == false) {
			System.out.println("No suppliers with such address");
		}
	}

	public static void searchAndPrintSupplierByid(int id) {
		for (Supplier s : suppliers) {
			if (s.getId() == id) {
				System.out.println(s);
				return;
			}
		}
		System.out.println("No suppliers with such id");
	}

	public static void searchAndPrintSupplierBytelephone(int tel) {
		boolean found = false;
		for (Supplier s : suppliers) {
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
		Supplier supplier = searchById(id);
		if (supplier == null) {
			throw new NoSuchElementException("Supplier with such id does not exist");
		} else {
			supplier.setName(name);
		}
	}

	public static void changeAddress(int id, String address) throws NoSuchElementException {
		Supplier supplier = searchById(id);
		if (supplier == null) {
			throw new NoSuchElementException("Customer with such id does not exist");
		} else {
			supplier.setAddress(address);
		}
	}

	public static void changeTelephone(int id, int tel) throws NoSuchElementException {
		Supplier supplier = searchById(id);
		if (supplier == null) {
			throw new NoSuchElementException("suppliers with such id does not exist");
		} else {
			supplier.setTel(tel);
		}
	}

	public static void registerNewSupplierMenu() {
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
				System.err.println("Invalid input given. id must be an integer, larger than 0");
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
				System.err.println("Invalid input given. id must be an integer, larger than 0");
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
				System.err.println("Invalid input given. id must be an integer, larger than 0");
			} catch (InputMismatchException e) {
				System.err.println("Invalid input given. telephone must be a number");
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

	public static void searchAndPrintSupplierBynameMenu() {
		Scanner in = new Scanner(System.in);
		String input;
		for (;;) {
			System.out.print("Enter the name of the supplier you want to print\nTo cancel, press \"enter\": ");
			input = in.nextLine();
			if (input.equals(" ")) {
				System.out.println("Process cancelled. Returning to previous menu...");
				return;
			}
			searchAndPrintSupplierByname(input);
			break;
		}
	}

	public static void searchAndPrintSupplierByaddressMenu() {
		Scanner in = new Scanner(System.in);
		String input;
		for (;;) {
			System.out.print("Enter the address of the supplier you want to print\nTo cancel, press \"enter\": ");
			input = in.nextLine();
			if (input.equals(" ")) {
				System.out.println("Process cancelled. Returning to previous menu...");
				return;
			}
			searchAndPrintSupplierByaddress(input);
			break;
		}
	}

	public static void searchAndPrintSupplierByidMenu() {
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
				searchAndPrintSupplierByid(id);
				break;
			} catch (NumberFormatException e) {
				System.err.println("Invalid input given. id must be an integer");
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
				searchAndPrintSupplierBytelephone(tel);
				break;
			} catch (NumberFormatException e) {
				System.err.println("Invalid input given. telephone must be an integer");
			}
		}
	}

	@Override
	public String toString() {
		return "Supplier [name=" + name + ", id=" + id + ", tel=" + tel + ", address=" + address + "]";
	}

}
