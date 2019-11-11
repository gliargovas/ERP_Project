public class Customer {
	//instance variables
	private String companyName;
	private String adress;
	private int telephone;
	//constructor
	public Customer(String name, String adress, int telephone) {
		this.companyName = name;
		this.adress = adress;
		this.telephone = telephone;
	}
	//getters and setters
	public String getcompanyName() {
		return companyName;
	}
	public void setName(String name) {
		this.companyName = name;
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
		return "Customer [compunyName =" + companyName + ", adress=" + adress + ", telephone=" + telephone
				+ "]";
	}
	
}
