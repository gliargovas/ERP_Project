public class Customer {
	//instance variables
	private String name;
	private String surname;
	private String adress;
	private int telephone;
	//constructor
	public Customer(String name, String surname, String adress, int telephone) {
		this.name = name;
		this.surname = surname;
		this.adress = adress;
		this.telephone = telephone;
	}
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	//toString Method
	@Override
	public String toString() {
		return "Customer [name=" + name + ", surname=" + surname + ", adress=" + adress + ", telephone=" + telephone
				+ "]";
	}
	
}
