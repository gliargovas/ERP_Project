package ERP_Core;
/**
 * The class represents a non-registered corporate customer of the ERP system The class is
 * responsible for creating a non-registered customer object and is used when a customer does not
 * want to be registered and only his address and telephone are needed in order to complete the
 * order and the shipping.
 *
 * @version 1.0
 * @author George Drosos
 */
public class Customer {

  /** The name of the customer. */
  private String companyName;
  /** The customer's address. */
  private String address;
  /** The customer's telephone. */
  private int telephone;
  
  /**
   * Constructor for creating a new non-registered customer.
   *
   * @param name the customer's name
   * @param address the customer's address
   * @param telephone the customer's telephone, must be less than 10 digits
   */
  public Customer(String name, String address, int telephone) {
    this.companyName = name;
    this.address = address;
    this.telephone = telephone;
  }

  /**
   * Returns customer's name.
   *
   * @return
   */
  public String getCompanyName() {
    return companyName;
  }

  /**
   * Sets the customer name to the value of the variable received.
   *
   * @param name the name of the customer
   */
  public void setName(String name) {
    this.companyName = name;
  }

  /**
   * Returns customer's address.
   *
   * @return the customer address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the customer address to the value of the variable received.
   *
   * @param adress the customer's address
   */
  public void setAddress(String adress) {
    this.address = adress;
  }

  /**
   * Returns customer's telephone.
   *
   * @return
   */
  public int getTelephone() {
    return telephone;
  }

  /**
   * Sets the customer telephone to the value of the variable received.
   *
   * @param telephone the telephone of the customer
   */
  public void setTelephone(int telephone) {
    this.telephone = telephone;
  }

  /** Returns the current customer object in String format. */
  @Override
  public String toString() {
    return String.format(
        "Name: %s | Address: %s | Telephone: %d ",
        this.getCompanyName(), this.getAddress(), this.getTelephone());
  }
}
