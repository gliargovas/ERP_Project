/**
 * The class represents a non-registered corporate customer of the ERP system
 * The class is responsible for creating a non-registered customer object and is used
 * when a customer does not want to be registered and only his address and telephone
 * are needed in order to complete the order and the shipping.
 * @version     1.0
 * @author      George Drosos
 */

public class Customer {
	/** the name of the customer */
	private String companyName;
	/** the customer's address */
	private String address;
	/** the customer's telephone */
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
	
	/**
	 * returns customer's name
	 * @return
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	/**
	 * sets the customer name to the value of the variable received
	 * @param name
	 */
	public void setName(String name) {
		this.companyName = name;
	}
	
	/**
	 * returns customer's address
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * sets the customer address to the value of the variable received
	 * @param adress
	 */
	public void setAddress(String adress) {
		this.address = adress;
	}
	
	/**
	 * returns customer's telephone
	 * @return
	 */
	public int getTelephone() {
		return telephone;
	}
	
	/**
	 * sets the customer telephone to the value of the variable received
	 * @param telephone
	 */
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * returns the current customer object in String format
	 */
	@Override
	public String toString() {
		return String.format("Name: %s | Address: %s | Telephone: %d ", this.getCompanyName(), this.getAddress(), this.getTelephone());
	}
}
