import java.util.ArrayList;
public class Supplier {
	private String Name;
	private int Id;
	private int Tel;
	private String Address;
	private static int idCounter;
	private static ArrayList<Supplier> Suppliers = new ArrayList<Supplier>();
	public Supplier(String name, int id, int tel, String address) {
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
	private 
	@Override
	public String toString() {
		return "Supplier [Name=" + Name + ", Id=" + Id + ", Tel=" + Tel + ", Address=" + Address + "]";
	}
	

}
