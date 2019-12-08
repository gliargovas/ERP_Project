import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The class represents a registered corporate customer of the ERP system
 * The class is responsible for creating a registered customer object and
 * storing it in an @ArrayList either by creating a completely new registered
 * customer or loading it from a @.csv file. It also contains methods for searching
 * and editing the contents of the customer list with specific criteria. There are also
 * methods that contain menus in order to use the above mentioned methods according
 * to user input.
 * 
 * @version     1.0
 * @author      George Drosos
 * @author      George Liargovas
 */

public class RegisteredCustomer extends Customer {
  private int id;
  /** points each customer earns depending on his purchases */
  private int points;
  // counts the number of registered customers created
  private static int idCounter;
  // an @Arraylist in which Customer information is temporarily saved
  protected static ArrayList<RegisteredCustomer> customers = new ArrayList<RegisteredCustomer>();
  
  /**
   * Constructor for creating a new registered customer.
   * @param name
   * @param address
   * @param telephone
   * @param points
   */
  public RegisteredCustomer(String name, String address, int telephone, int points) {
    super(name, address, telephone);
    this.points = points;
    this.id = ++idCounter;
    // adding registered customer to @Arraylist
    customers.add(this);
  }
  
  /** 
   * Constructor for loading customers read from @.csv file
   * The id is already associated with the customer, used when loading information from the database
   * to the program 
   * 
   * @param name
   * @param address
   * @param telephone
   * @param id
   * @param points
   */
  public RegisteredCustomer(String name, String address, int telephone, int id, int points) {
    super(name, address, telephone);
    this.id = id;
    this.points = points;
    customers.add(this);
  }
  
  // getters and setters
  public int getPoints() {
    return points;
  }

  public static int getIdCounter() {
    return idCounter;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public int getId() {
    return id;
  }

  public static ArrayList<RegisteredCustomer> getCustomers() {
    return customers;
  }

  @Override
  public String toString() {
    return super.toString() + " id=" + id + ", points=" + points + "]";
  }
  
  /** Returns a customer with a given id.
   * @param id
   * @return RegisteredCustomer
   * @throws NoSuchElementException
   */
  public static RegisteredCustomer searchById(int id) throws NoSuchElementException {
    for (RegisteredCustomer i : customers) {
      if (i.getId() == id) {
        return i;
      }
    }
    throw new NoSuchElementException();
  }
  
  /** Creates a new registered customer from an @Arraylist. of strings by parsing its contents.
   * @param customers
   */
  public static void createRegisteredCustomersFromList(ArrayList<ArrayList<String>> customers) {
    int id, points, telephone;
    String name, address;
    for (ArrayList<String> customer : customers) {
      id = Integer.parseInt(customer.get(0));
      name = customer.get(1);
      address = customer.get(2);
      telephone = Integer.parseInt(customer.get(3));
      points = Integer.parseInt(customer.get(4));
      new RegisteredCustomer(name, address, telephone, id, points);
    }
  }
  
  /** Prints the customer list as a whole.*/
  public static void printAllCustomers() {
    for (RegisteredCustomer c : customers) {
      System.out.println(c);
    }
  }
  
  /**
   * Searches the customer list and prints all the registered customers with 
   * a name that contains the character sequence given.
   * @param name
   */
  public static void searchAndPrintRegisteredCustomerByName(String name) {
    boolean found = false;
    for (RegisteredCustomer c : customers) {
      if (c.getCompanyName().toLowerCase().contains(name.toLowerCase())) {
        found = true;
        System.out.println(c);
      }
    }
    if (found == false) {
      System.out.println("No customers with such name");
    }
  }

  /**
   * Searches the customer list and prints the registered customer with
   * the same address as a given string.
   * @param address
   */
  public static void searchAndPrintRegisteredCustomerByAddress(String address) {
    boolean found = false;
    for (RegisteredCustomer c : customers) {
      if (c.getAddress().toLowerCase().contains(address.toLowerCase())) {
        found = true;
        System.out.println(c);
      }
    }
    if (found == false) {
      System.out.println("No customers with such address");
    }
  }

  /**
   * Searches the customer list and prints the registered customer with
   * the same id number as a given integer.
   * @param id
   */
  public static void searchAndPrintRegisteredCustomerById(int id) {
    for (RegisteredCustomer c : customers) {
      if (c.getId() == id) {
        System.out.println(c);
        return;
      }
    }
    System.out.println("No customer with such Id");
  }

  /**
   * Searches the customer list and prints the registered customer with
   * the same telephone number as a given number.
   * @param telephone
   */
  public static void searchAndPrintRegisteredCustomerByTelephone(int telephone) {
    boolean found = false;
    for (RegisteredCustomer c : customers) {
      if (c.getTelephone() == telephone) {
        found = true;
        System.out.println(c);
      }
    }
    if (found == false) {
      System.out.println("No customer with such telephone");
    }
  }

  /**
   * Searches the customer list and prints all the registered customer with
   * the same amount of points as the user input.
   * @param points
   */
  public static void searchAndPrintRegisteredCustomerByPoints(int points) {
    boolean found = false;
    for (RegisteredCustomer c : customers) {
      if (c.getTelephone() == points) {
        found = true;
        System.out.println(c);
      }
    }
    if (found == false) {
      System.out.println("No customer with such points");
    }
  }
  
  /**
   * Deletes a customer from list with given id
   * @param id
   * @throws NoSuchElementException
   */
  public static void deleteRegisteredCustomer(int id) throws NoSuchElementException {
    RegisteredCustomer customer = searchById(id);
    if (customer == null) {
      throw new NoSuchElementException("Customer with such id does not exist");
    }
    customers.remove(customers.indexOf(customer));
  }
  
  /**
   * Checks if a Registered Customer already exists and if it does, changes his points
   * @param id
   * @param points
   * @throws NoSuchElementException
   * @throws NumberFormatException
   */
  public static void changePoints(int id, int points)
      throws NoSuchElementException, NumberFormatException {
    if (points <= 0) {
      throw new NumberFormatException("Points must be more or equal to 0");
    }
    RegisteredCustomer customer = searchById(id);
    if (customer == null) {
      throw new NoSuchElementException("Customer with such id does not exist");
    } else {
      customer.setPoints(points);
    }
  }
  
  /**
   * checks if a Registered Customer already exists and if it does, changes his name
   * @param id
   * @param name
   * @throws NoSuchElementException
   */
  public static void changeName(int id, String name) throws NoSuchElementException {
    RegisteredCustomer customer = searchById(id);
    if (customer == null) {
      throw new NoSuchElementException("Customer with such id does not exist");
    } else {
      customer.setName(name);
    }
  }
  
  /**
   * Checks if a Registered Customer already exists and if it does, changes his address
   * @param id
   * @param address
   * @throws NoSuchElementException
   */
  public static void changeAddress(int id, String address) throws NoSuchElementException {
    RegisteredCustomer customer = searchById(id);
    if (customer == null) {
      throw new NoSuchElementException("Customer with such id does not exist");
    } else {
      customer.setAddress(address);
    }
  }
  
  /**
   * Checks if a Registered Customer already exists and if it does, changes his telephone
   * @param id
   * @param telephone
   * @throws NoSuchElementException
   */
  public static void changeTelephone(int id, int telephone) 
		  throws NoSuchElementException {
    RegisteredCustomer customer = searchById(id);
    if (customer == null) {
      throw new NoSuchElementException("Customer with such id does not exist");
    } else {
      customer.setTelephone(telephone);
    }
  }
  
  /** 
   * Contains the registered customer creation menu
   */
  public static void registerNewCustomerMenu() {
    String name;
    String address;
    int telephone;
    int points;
    Scanner in = new Scanner(System.in);
    for (; ; ) {
      System.out.print("Enter the customer's name: ");
      name = in.nextLine();
      System.out.print("Enter the customer's address: ");
      address = (in.nextLine().toLowerCase());
      for (; ; ) {
        try {
          System.out.print("Enter customer's telephone: ");
          telephone = in.nextInt();
          break;
        } catch (InputMismatchException e) {
          System.err.println("Invalid input given. Telephone must be a number. Try again...");
          in.nextLine();
        }
      }
      for (; ; ) {
        try {
          System.out.print("Enter customer's initial points: ");
          points = in.nextInt();
          if (points <= 0) {
            System.err.println("Points must be larger than 0. Try again...");
            continue;
          }
          break;
        } catch (InputMismatchException e) {
          System.err.println("Invalid input given. Points must be a number." + "Try again...");
          in.nextLine();
        }
      }
      break;
    }
    new RegisteredCustomer(name, address, telephone, points);
    System.out.printf("Customer %s registered successfully!\n", name);
  }
  
  /**
   * Contains the menu for changing a RegisteredCustomer's points
   * according to user input.
   */
  public static void changeCustomerPointsMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    String points;
    String input;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the registered customer you want to change\n"
            + "To cancel, press \"enter\": ");
        input = in.nextLine();
        if (input.equals("")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        id = Integer.parseInt(input);
        System.out.print("Enter new points: ");
        points = in.nextInt();
        changePoints(id, points);
        break;
      } catch (NumberFormatException e) {
        System.err.println("Invalid input given. Id must be an integer, larger than 0");
      } catch (InputMismatchException e) {
        System.err.println("Invalid input given. Points must be a number");
      } catch (NoSuchElementException e) {
        System.err.println(e.getMessage());
      }
    }
  }
  
  /**
   * Contains the menu for changing a RegisteredCustomer's name
   * according to user input.
   */
  public static void changeCustomerNameMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    String input;
    String name;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the registered customer you want to change\n"
            + "To cancel, press \"enter\": ");
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
  /** 
   * Contains the menu for changing a RegisteredCustomer's address
   * according to user input.
   */
  public static void changeCustomerAddressMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    String input;
    String address;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the registered customer you want to change\n"
            + "To cancel, press \"enter\": ");
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
  /**
   * Contains the menu for changing a RegisteredCustomer's telephone
   * according to user input.
   */
  public static void changeCustomerTelephoneMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    int telephone;
    String input;
    for (;;) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the registered customer you want to change\n"
            + "To cancel, press \"enter\": ");
        input = in.nextLine();
        if (input.equals("")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        id = Integer.parseInt(input);
        System.out.print("Enter new telephone: ");
        telephone = in.nextInt();
        changeTelephone(id, telephone);
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
  
  /**
   * Contains the menu for deleting a registered customer with a specific id
   * given by the user.
   */
  public static void deleteCustomerMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    String input;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the registered customer you want to delete\n"
            + "To cancel, press \"enter\": ");
        input = in.nextLine();
        if (input.equals(" ")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        id = Integer.parseInt(input);
        deleteRegisteredCustomer(id);
        break;
      } catch (NoSuchElementException e) {
        System.err.println(e.getMessage());
      }
    }
  }
  
  /**
   * Contains the menu for printing registered customers with a specific name
   * given by the user.
   */
  public static void searchAndPrintCustomerByNameMenu() {
    Scanner in = new Scanner(System.in);
    String input;
    for (; ; ) {
      System.out.print(
          "Enter the name of the registered customer you want to print\nTo cancel, press \"enter\": ");
      input = in.nextLine();
      if (input.equals(" ")) {
        System.out.println("Process cancelled. Returning to previous menu...");
        return;
      }
      searchAndPrintRegisteredCustomerByName(input);
      break;
    }
  }

  /**
   * Contains the menu for printing a registered customer with a specific address
   * given by the user.
   */
  public static void searchAndPrintCustomerByAddressMenu() {
    Scanner in = new Scanner(System.in);
    String input;
    for (; ; ) {
      System.out.print(
          "Enter the address of the registered customer you want to print\n"
          + "To cancel, press \"enter\": ");
      input = in.nextLine();
      if (input.equals(" ")) {
        System.out.println("Process cancelled. Returning to previous menu...");
        return;
      }
      searchAndPrintRegisteredCustomerByAddress(input);
      break;
    }
  }

  /**
   * Contains the menu for printing a registered customer with a specific id
   * given by the user.
   */
  public static void searchAndPrintCustomerByIdMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    String input;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the registered customer you want to print\n"
            + "To cancel, press \"enter\": ");
        input = in.nextLine();
        if (input.equals(" ")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        id = Integer.parseInt(input);
        searchAndPrintRegisteredCustomerById(id);
        break;
      } catch (NumberFormatException e) {
        System.err.println("Invalid input given. Id must be an integer");
      }
    }
  }

  /**
   * Contains the menu for printing a registered customer with a telephone number
   * given by the user.
   */
  public static void searchAndPrintCustomerByTelephoneMenu() {
    Scanner in = new Scanner(System.in);
    int telephone;
    String input;
    for (; ; ) {
      telephone = 0;
      try {
        System.out.print(
            "Enter the telephone of the registered customer you want to print\n"
            + "To cancel, press \"enter\": ");
        input = in.nextLine();
        if (input.equals(" ")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        telephone = Integer.parseInt(input);
        searchAndPrintRegisteredCustomerByTelephone(telephone);
        break;
      } catch (NumberFormatException e) {
        System.err.println("Invalid input given. Telephone must be an integer");
      }
    }
  }
  
  /**
   * Contains the menu for printing the registered customers with a specific point
   * amount given by the user.
   */
  public static void searchAndPrintCustomerByPointsMenu() {
    Scanner in = new Scanner(System.in);
    int points;
    String input;
    for (; ; ) {
      points = 0;
      try {
        System.out.print(
            "Enter the points of the registered customers you want to print\n"
            + "To cancel, press \"enter\": ");
        input = in.nextLine();
        if (input.equals(" ")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        points = Integer.parseInt(input);
        searchAndPrintRegisteredCustomerByPoints(points);
        break;
      } catch (NumberFormatException e) {
        System.err.println("Invalid input given. Points must be integers");
      }
    }
  }
}
