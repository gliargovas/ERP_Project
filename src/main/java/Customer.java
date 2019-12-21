/**
 * The class represents a non-registered corporate customer of the ERP system
 * The class is responsible for creating a non-registered customer object and is used
 * when a customer does not want to be registered and only his address and telephone
 * are needed in order to complete the order and the shipping.
 * @version     1.0
 * @author      George Drosos
 */

public class Customer {
	private String companyName;
	private String address;
	private int telephone;
	/**
	   * Constructor for creating a new non-registered customer.
	   * @param name
	   * @param address
	   * @param telephone
	   */
	public Customer(String name, String address, int telephone) {
		this.companyName = name;
		this.address = address;
		this.telephone = telephone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setName(String name) {
		this.companyName = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String adress) {
		this.address = adress;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	/**
	 * toString Method
	 */
	@Override
	public String toString() {
		return String.format("Name: %s | Address: %s | Telephone: %d ", this.getCompanyName(), this.getAddress(), this.getTelephone());
	}
}
