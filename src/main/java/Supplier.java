import java.util.ArrayList;
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
	@Override
	public String toString() {
		return "Supplier [Name=" + Name + ", Id=" + Id + ", Tel=" + Tel + ", Address=" + Address + "]";
	}
	 
	
	

}
