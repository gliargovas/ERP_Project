import java.util.ArrayList;
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
	public Supplier(String name,int tel, String address) {
		Name = name;
		Id = ++idCounter;
		Tel = tel;
		Address = address;
		Suppliers.add(this);
		idCounter++;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getTel() {
		return Tel;
	}
	public void setTel(int tel) {
		Tel = tel;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public static ArrayList<Supplier> getSuppliers() {
		return Suppliers;
	}
	public static void alltheSuppliers() {
		for (Supplier k : Suppliers) {
			k.toString();
		}
	}
	public static boolean deleteSupplier(int id) {
		for(Supplier i : Suppliers) {
			if(i.getId() == id) {
				Suppliers.remove(i);
				return true;
			}
		}
		return false;
	}
	public static Supplier searchbyId(int id) {
		for (Supplier i : Suppliers) {
			if (i.getId() == id) {
				return i;
			}
		}
		System.out.println("There is no such element");
		return null;
	}
	public static void createSuppliersFromList(ArrayList<ArrayList<String>> Suppliers) {
		int Id, Tel;
		String Name, Address;
		for (ArrayList<String> supplier: Suppliers) {
			Id = Integer.parseInt(supplier.get(0));
			Name = supplier.get(1);
			Address = supplier.get(2);
			Tel = Integer.parseInt(supplier.get(3));
			new Supplier(Name, Address,Tel, Id);
		}
	}
	public static void searchAndPrintRegisteredSupplierByName(String name) {
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
	public static void searchAndPrintSupplierrByAddress(String address) {
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
	public static void searchAndPrintSupplierrById(int id) {
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
	public static void changeName(int id,String name) throws NoSuchElementException{
		Supplier supplier = searchbyId(id);
		if (supplier == null) {
			throw new NoSuchElementException("Supplier with such id does not exist");
		} else {
			supplier.setName(name);
		}
	}
	public static void changeAddress(int id,String address) throws NoSuchElementException{
		Supplier supplier = searchbyId(id);
		if (supplier == null) {
			throw new NoSuchElementException("Customer with such id does not exist");
		} else {
			supplier.setAddress(address);
		}
	}
	public static void changeTelephone(int id,int tel) throws NoSuchElementException{
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
		new Supplier(name,tel,address);
		System.out.printf("Supplier %s registered successfully!\n", name);
	}
	@Override
	public String toString() {
		return "Supplier [Name=" + Name + ", Id=" + Id + ", Tel=" + Tel + ", Address=" + Address + "]";
	}
	 
	
	

}
