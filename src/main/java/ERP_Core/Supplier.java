package ERP_Core;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The class represents a supplier of the ERP system The class is responsible for creating a
 * supplier object and storing it in an ArrayList either by creating a completely new supplier or
 * loading it from a .csv file. It also contains methods for searching and editing the contents of
 * the suppliers list with specific criteria. There are also methods that contain menus in order to
 * use the above mentioned methods according to user input.
 *
 * @author Christina Kardami
 * @author George Liargovas
 * @version 1.0
 */
public class Supplier {
  /** The name of the supplier. */
  private String name;
  /** The unique id of the supplier. */
  private int id;
  /** The telephone of the supplier. */
  private int tel;
  /** The address of the supplier. */
  private String address;
  /** The counter that counts the unique suppliers created. */
  private static int idCounter = FileHandler.getSupplierCounterFromFile();
  /** The list where the suppliers are stored. */
  private static ArrayList<Supplier> suppliers = new ArrayList<Supplier>();

  /**
   * Constructor for loading objects from .csv file
   *
   * @param name The name of the supplier
   * @param address The address of the supplier
   * @param tel The telephone of the supplier. Must not be larger than 10 digits
   * @param id The unique id of the supplier when first registered
   */
  public Supplier(String name, String address, int tel, int id) {
    super();
    this.name = name;
    this.id = id;
    this.tel = tel;
    this.address = address;
    suppliers.add(this);
  }

  /**
   * Constructor for creating new Supplier objects.
   *
   * @param name The name of the supplier
   * @param tel The telephone of the supplier. Must not be larger than 10 digits
   * @param address The address of the supplier
   */
  public Supplier(String name, int tel, String address) {
    this.name = name;
    this.id = ++idCounter;
    this.tel = tel;
    this.address = address;
    suppliers.add(this);
  }

  /**
   * Returns the name of the Supplier.
   *
   * @return the name of supplier, type String
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the Supplier according to the parameter that is given.
   *
   * @param name the new name of the supplier
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the id of the supplier.
   *
   * @return Id of the supplier, type integer
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the telephone of the supplier.
   *
   * @return the Telephone, type integer
   */
  public int getTel() {
    return tel;
  }

  /**
   * Sets the number of the telephone according to the parameter that is given.
   *
   * @param tel the new telephone of the supplier
   */
  public void setTel(int tel) {
    this.tel = tel;
  }

  /**
   * Returns the Address of the supplier.
   *
   * @return the address, type String
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the Address of the supplier according to the parameter that is given.
   *
   * @param address the new address of the supplier
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Returns the supplier list.
   *
   * @return supplier list
   */
  public static ArrayList<Supplier> getsuppliers() {
    return suppliers;
  }

  public static int getIdCounter() {
    return idCounter;
  }

  /** All the suppliers from the list are appeared. */
  public static void printAllSuppliers() {
    for (Supplier k : suppliers) {
      System.out.println(k.toString());
    }
  }

  /**
   * Deletes the supplier with the given id.
   *
   * @param id the id of the supplier to delete
   * @return true if the supplier is removed or false if the id doesn't refer to a supplier
   */
  public static boolean deleteSupplier(int id) {
    for (Supplier i : suppliers) {
      if (i.getId() == id) {
        suppliers.remove(i);
        return true;
      }
    }
    return false;
  }

  /**
   * Searches for the supplier, who has the given id.
   *
   * @param id the id of the supplier
   * @return the supplier from the list according his id
   */
  public static Supplier searchById(int id) {
    for (Supplier i : suppliers) {
      if (i.getId() == id) {
        return i;
      }
    }
    System.out.println("There is no such element");
    return null;
  }

  /**
   * Creates all the suppliers from the list.
   *
   * @param Suppliers the list with the parsed contents of the supplier file
   */
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

  /**
   * Searches the supplier and prints him according to the name that is given
   *
   * @param name the name to match
   */
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

  /**
   * Searches the supplier and prints him according to the address that is given
   *
   * @param address the address to match
   */
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

  /**
   * Searches the Supplier and prints him according to the given id.
   *
   * @param id, integer type
   */
  public static void searchAndPrintSupplierByid(int id) {
    for (Supplier s : suppliers) {
      if (s.getId() == id) {
        System.out.println(s);
        return;
      }
    }
    System.out.println("No suppliers with such id");
  }

  /** Searches and prints the supplier according to the given telephone. */
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

  /**
   * Changes name of the supplier with the given id.
   * given.
   *
   * @param id The id of the supplier
   * @param name The new name
   * @throws NoSuchElementException The supplier does not exist
   */
  public static void changeName(int id, String name) throws NoSuchElementException {
    Supplier supplier = searchById(id);
    if (supplier == null) {
      throw new NoSuchElementException("Supplier with such id does not exist");
    } else {
      supplier.setName(name);
    }
  }

  /**
   * Changes the address of the supplier with the given id.
   *
   * @param id The id of the supplier
   * @param address The new address of the supplier
   * @throws NoSuchElementException The supplier does not exist
   */
  public static void changeAddress(int id, String address) throws NoSuchElementException {
    Supplier supplier = searchById(id);
    if (supplier == null) {
      throw new NoSuchElementException("Customer with such id does not exist");
    } else {
      supplier.setAddress(address);
    }
  }

  /**
   * Changes the telephone of the supplier with the given id.
   *
   * @param id The id of the supplier
   * @param tel The new telephone o the supplier
   * @throws NoSuchElementException
   */
  public static void changeTelephone(int id, int tel) throws NoSuchElementException {
    Supplier supplier = searchById(id);
    if (supplier == null) {
      throw new NoSuchElementException("Suppliers with such id do not exist");
    } else {
      supplier.setTel(tel);
    }
  }

  /** Creates the registration of the supplier according to the values given from the user. */
  public static void registerNewSupplierMenu() {
    String name;
    String address;
    int tel;
    Scanner in = new Scanner(System.in);
    System.out.print("Enter the supplier's name: ");
    name = in.nextLine();
    System.out.print("Enter the supplier's address: ");
    address = (in.nextLine().toLowerCase());
    for (; ; ) {
      try {
        // wait just enough so as to display the exception message in the correct order
        Thread.sleep(10);
        System.out.print("Enter supplier's telephone: ");
        tel = in.nextInt();
        break;
      } catch (InputMismatchException e) {
        System.err.println("Price must be an integer number. Try again...");
        in.nextLine();
      } catch (InterruptedException e) {
    	e.printStackTrace();
      }
    }
    new Supplier(name, tel, address);
    System.out.printf("Supplier %s registered successfully!\n", name);
  }

  /** Changes the name of the supplier at the menu according to the id that is given. */
  public static void changeCustomerNameMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    String input;
    String name;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the supplier you want to change\nTo cancel, press \"enter\": ");
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
        System.err.println("Suppliers with such id do not exist");
      }
    }
  }

  /** Changes the address of the supplier at the menu according to the id that is given. */
  public static void changeSupplierAddressMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    String input;
    String address;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the supplier you want to change\nTo cancel, press \"enter\": ");
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
        System.err.println("Suppliers with such id do not exist");
      }
    }
  }

  /** changes the telephone of the supplier at the menu according to the id that is given. */
  public static void changeSupplierTelephoneMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    int tel;
    String input;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the supplier you want to change\nTo cancel, press \"enter\": ");
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
        System.err.println("Suppliers with such id do not exist");
      }
    }
  }

  /** Deletes the supplier at the menu according to the id that is given. */
  public static void deleteSupplierMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    String input;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the supplier you want to delete\nTo cancel, press \"enter\": ");
        input = in.nextLine();
        if (input.equals(" ")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        id = Integer.parseInt(input);
        deleteSupplier(id);
        break;
      } catch (NoSuchElementException e) {
        System.err.println("Suppliers with such id do not exist");
      }
    }
  }

  /** Searches and prints the Supplier according to the name that is given. */
  public static void searchAndPrintSupplierBynameMenu() {
    Scanner in = new Scanner(System.in);
    String input;
    for (; ; ) {
      System.out.print(
          "Enter the name of the supplier you want to print\nTo cancel, press \"enter\": ");
      input = in.nextLine();
      if (input.equals(" ")) {
        System.out.println("Process cancelled. Returning to previous menu...");
        return;
      }
      searchAndPrintSupplierByname(input);
      break;
    }
  }

  /** Searches and prints the Supplier according to the address that is given. */
  public static void searchAndPrintSupplierByaddressMenu() {
    Scanner in = new Scanner(System.in);
    String input;
    for (; ; ) {
      System.out.print(
          "Enter the address of the supplier you want to print\nTo cancel, press \"enter\": ");
      input = in.nextLine();
      if (input.equals(" ")) {
        System.out.println("Process cancelled. Returning to previous menu...");
        return;
      }
      searchAndPrintSupplierByaddress(input);
      break;
    }
  }

  /** Searches and prints the Supplier according to the id that is given. */
  public static void searchAndPrintSupplierByidMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    String input;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the supplier you want to print\nTo cancel, press \"enter\": ");
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

  /** Searches and prints the Supplier according to the telephone that is given. */
  public static void searchAndPrintSupplierByTelephoneMenu() {
    Scanner in = new Scanner(System.in);
    int tel;
    String input;
    for (; ; ) {
      tel = 0;
      try {
        System.out.print(
            "Enter the telephone of the supplier you want to print\nTo cancel, press \"enter\": ");
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

  /** Returns the current Supplier object in String format. */
  @Override
  public String toString() {
    return "Supplier [name=" + name + ", id=" + id + ", tel=" + tel + ", address=" + address + "]";
  }
}
