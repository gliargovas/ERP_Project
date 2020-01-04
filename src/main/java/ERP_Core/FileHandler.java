package ERP_Core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class contains the methods for writing and reading the data of the ERP system to and from
 * files. The files that the data is written are predefined. All the data except the object counters
 * are stored in .csv format. The counters are stored in .txt format.
 *
 * @version 1.0
 * @author George Liargkovas
 */
public class FileHandler {

  /**
   * CSV file separator used. In European countries the ";" symbol is usually used instead of a
   * comma (,)
   */
  private static final String CSV_SEPARATOR = ";";

  /**
   * Writes all the object counters of the other classes of the ERP system to a .txt file named
   * "Counters.txt".
   */
  public static void writeAllCountersToFile() {
    try {
      BufferedWriter bw =
          new BufferedWriter(
              new OutputStreamWriter(new FileOutputStream("./Data/Counters.txt"), "UTF-8"));
      bw.write("User counter: " + User.getUserIdCounter());
      bw.newLine();
      bw.write("Product counter: " + Product.getIdCounter());
      bw.newLine();
      bw.write("Registered customer counter: " + RegisteredCustomer.getIdCounter());
      bw.newLine();
      bw.write("Order counter: " + Order.getIdCounter());
      bw.newLine();
      bw.write("Supplier counter: " + Supplier.getIdCounter());
      bw.newLine();
      bw.write("Storage order counter: " + StorageOrder.getIdCounter());
      bw.newLine();
      bw.flush();
      bw.close();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Retrieves the user counter stored in "Counters.txt".
   *
   * @return userCounter as int
   */
  public static int getUserCounterFromFile() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader("./Data/Counters.txt"));
      String line = reader.readLine();
      while (line != null) {;
        if (line.contains("User counter:")) {
          reader.close();
          return Integer.parseInt(line.substring(14));
        }
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * Retrieves the product counter stored in "Counters.txt".
   *
   * @return productCounter as int
   */
  public static int getProductCounterFromFile() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader("./Data/Counters.txt"));
      String line = reader.readLine();
      while (line != null) {;
        if (line.contains("Product counter:")) {
          reader.close();
          return Integer.parseInt(line.substring(17));
        }
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * Retrieves the registered customer counter stored in "Counters.txt".
   *
   * @return registeredCustomerCounter as int
   */
  public static int getRegisteredCustomerCounterFromFile() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader("./Data/Counters.txt"));
      String line = reader.readLine();
      while (line != null) {
        if (line.contains("Registered customer counter:")) {
          reader.close();
          return Integer.parseInt(line.substring(29));
        }
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * Retrieves the order counter stored in "Counters.txt".
   *
   * @return orderCounter as int
   */
  public static int getOrderCounterFromFile() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader("./Data/Counters.txt"));
      String line = reader.readLine();
      while (line != null) {;
        if (line.contains("Order counter:")) {
          reader.close();
          return Integer.parseInt(line.substring(15));
        }
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * Retrieves the supplier counter stored in "Counters.txt".
   *
   * @return supplierCounter as int
   */
  public static int getSupplierCounterFromFile() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader("./Data/Counters.txt"));
      String line = reader.readLine();
      while (line != null) {;
        if (line.contains("Supplier counter:")) {
          reader.close();
          return Integer.parseInt(line.substring(18));
        }
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * Retrieves the storage order counter stored in "Counters.txt".
   *
   * @return storageOrderCounter as int
   */
  public static int getStorageOrderCounterFromFile() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader("./Data/Counters.txt"));
      String line = reader.readLine();
      while (line != null) {;
        if (line.contains("Storage order counter:")) {
          reader.close();
          return Integer.parseInt(line.substring(23));
        }
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * Writes the product list stored in the memory, to a file named "Products.csv", in .csv format
   * and UTF-8 encoding.
   *
   * @param products ArrayList<product> that contains the products created by the ERP
   */
  public static void writeProductListToCSV(ArrayList<Product> products) {
    try {
      BufferedWriter bw =
          new BufferedWriter(
              new OutputStreamWriter(new FileOutputStream("./Data/Products.csv"), "UTF-8"));
      for (Product product : products) {
        StringBuffer line = new StringBuffer();
        line.append(product.getProductId());
        line.append(CSV_SEPARATOR);
        line.append(product.getName());
        line.append(CSV_SEPARATOR);
        line.append(product.getCategory());
        line.append(CSV_SEPARATOR);
        line.append(product.getDescription());
        line.append(CSV_SEPARATOR);
        line.append(product.getSalePrice());
        line.append(CSV_SEPARATOR);
        bw.write(line.toString());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes the ProductQuantities list stored in the memory, to a file named
   * "ProductQuantities.csv", in .csv format and UTF-8 encoding.
   *
   * @param productQuantities ArrayList<int[]> that contains the ids and the quantities of the
   *     products in the storage
   */
  public static void writeProductQuantitiesListToCSV(ArrayList<int[]> productQuantities) {
    try {
      BufferedWriter bw =
          new BufferedWriter(
              new OutputStreamWriter(
                  new FileOutputStream("./Data/ProductQuantities.csv"), "UTF-8"));
      for (int[] productQuantity : productQuantities) {
        StringBuffer line = new StringBuffer();
        line.append(productQuantity[0]);
        line.append(CSV_SEPARATOR);
        line.append(productQuantity[1]);
        line.append(CSV_SEPARATOR);
        bw.write(line.toString());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    } catch (UnsupportedEncodingException e) {
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
    }
  }

  /**
   * Writes the Registered Customer list stored in the memory, to a file named "Customers.csv", in
   * .csv format and UTF-8 encoding.
   *
   * @param customers ArrayList that contains the registered customer objects created by the ERP
   */
  public static void writeCustomerListToCSV(ArrayList<RegisteredCustomer> customers) {
    try {
      BufferedWriter bw =
          new BufferedWriter(
              new OutputStreamWriter(new FileOutputStream("./Data/Customers.csv"), "UTF-8"));
      for (RegisteredCustomer registeredCustomer : customers) {
        StringBuffer line = new StringBuffer();
        line.append(registeredCustomer.getId());
        line.append(CSV_SEPARATOR);
        line.append(registeredCustomer.getCompanyName());
        line.append(CSV_SEPARATOR);
        line.append(registeredCustomer.getAddress());
        line.append(CSV_SEPARATOR);
        line.append(registeredCustomer.getTelephone());
        line.append(CSV_SEPARATOR);
        line.append(registeredCustomer.getPoints());
        bw.write(line.toString());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    } catch (UnsupportedEncodingException e) {
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
    }
  }

  /**
   * Writes the User list stored in the memory, to a file named "Users.csv", in .csv format and
   * UTF-8 encoding.
   *
   * @param users ArrayList that contains the user objects created by the ERP
   */
  public static void writeUserListToCSV(ArrayList<User> users) {
    try {
      BufferedWriter bw =
          new BufferedWriter(
              new OutputStreamWriter(new FileOutputStream("./Data/Users.csv"), "UTF-8"));
      for (User user : users) {
        StringBuffer line = new StringBuffer();
        if (user instanceof Cashier) {
          line.append("Cashier");
        } else if (user instanceof Storekeeper) {
          line.append("Storekeeper");
        } else if (user instanceof DataAnalyst) {
          line.append("DataAnalyst");
        } else if (user instanceof Administrator) {
          line.append("Admin");
        }
        line.append(CSV_SEPARATOR);
        line.append(user.getIdUser());
        line.append(CSV_SEPARATOR);
        line.append(user.getName());
        line.append(CSV_SEPARATOR);
        line.append(user.getSurname());
        line.append(CSV_SEPARATOR);
        line.append(user.getUsername());
        line.append(CSV_SEPARATOR);
        line.append(user.getPassword());
        bw.write(line.toString());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    } catch (UnsupportedEncodingException e) {
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
    }
  }

  /**
   * Writes the order list stored in the memory, to a file named "Orders.csv", in .csv format and
   * UTF-8 encoding.
   *
   * @param orders ArrayList that contains the user order objects created by the ERP
   */
  public static void writeOrderListToCSV(ArrayList<Order> orders) {
    try {
      BufferedWriter bw =
          new BufferedWriter(
              new OutputStreamWriter(new FileOutputStream("./Data/Orders.csv"), "UTF-8"));
      for (Order order : orders) {
        StringBuffer line = new StringBuffer();
        line.append(order.getOrderNo());
        line.append(CSV_SEPARATOR);
        line.append(order.getOrderDate());
        line.append(CSV_SEPARATOR);
        line.append(order.getTotalCost());
        line.append(CSV_SEPARATOR);
        // the unregistered customers are saved to the Orders.csv with the id value of 0
        if (order.getCustomer() instanceof RegisteredCustomer)
          line.append(((RegisteredCustomer) order.getCustomer()).getId());
        else line.append("0");
        line.append(CSV_SEPARATOR);
        line.append(order.getCashier().getIdUser());
        for (int[] i : order.getBasket()) {
          line.append(CSV_SEPARATOR);
          line.append(i[0]);
          line.append(CSV_SEPARATOR);
          line.append(i[1]);
        }
        bw.write(line.toString());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    } catch (UnsupportedEncodingException e) {
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
    }
  }

  /**
   * Writes the storage order list stored in the memory, to a file named "StorageOrders.csv", in
   * .csv format and UTF-8 encoding.
   *
   * @param orders ArrayList that contains the storage order objects created by the ERP
   */
  public static void writeStorageOrderToCSV(ArrayList<StorageOrder> orders) {
    try {
      BufferedWriter bw =
          new BufferedWriter(
              new OutputStreamWriter(new FileOutputStream("./Data/StorageOrders.csv"), "UTF-8"));
      for (StorageOrder order : orders) {
        StringBuffer line = new StringBuffer();
        line.append(order.getStorageOrderNumber());
        line.append(CSV_SEPARATOR);
        line.append(order.getStorageOrderDate());
        line.append(CSV_SEPARATOR);
        line.append(order.getTotalCost());
        line.append(CSV_SEPARATOR);
        line.append(order.getSupplier().getId());
        line.append(CSV_SEPARATOR);
        line.append(order.getStorekeeper().getIdUser());
        for (int[] i : order.getSupplies()) {
          line.append(CSV_SEPARATOR);
          line.append(i[0]);
          line.append(CSV_SEPARATOR);
          line.append(i[1]);
        }
        bw.write(line.toString());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    } catch (UnsupportedEncodingException e) {
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
    }
  }

  /**
   * Writes the supplier list stored in the memory, to a file named "Suppliers.csv", in .csv format
   * and UTF-8 encoding.
   *
   * @param suppliers ArrayList that contains the supplier objects created by the ERP file
   */
  public static void writeSupplierToCSV(ArrayList<Supplier> suppliers) {
    try {
      BufferedWriter bw =
          new BufferedWriter(
              new OutputStreamWriter(new FileOutputStream("./Data/Suppliers.csv"), "UTF-8"));
      for (Supplier supplier : suppliers) {
        StringBuffer line = new StringBuffer();
        line.append(supplier.getId());
        line.append(CSV_SEPARATOR);
        line.append(supplier.getName());
        line.append(CSV_SEPARATOR);
        line.append(supplier.getAddress());
        line.append(CSV_SEPARATOR);
        line.append(supplier.getTel());
        line.append(CSV_SEPARATOR);
        bw.write(line.toString());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    } catch (UnsupportedEncodingException e) {
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
    }
  }

  /**
   * .csv file format reader for loading the contents of the "Products.csv" file to the main memory
   * as an ArrayList of strings.
   *
   * @return an ArrayList<ArrayList<String>> that contains each element read from the Products.csv
   *     file
   */
  public static ArrayList<ArrayList<String>> getProductsFromCsv() throws FileNotFoundException {
    Scanner scanner = null;
    ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    scanner = new Scanner(new File("./Data/Products.csv"));
    scanner.useDelimiter(CSV_SEPARATOR);
    while (scanner.hasNext()) {
      records.add(getRecordFromLine(scanner.nextLine()));
    }
    scanner.close();
    return records;
  }

  /**
   * .csv file format reader for loading the contents of the "ProductQuantities.csv" file to the
   * main memory as an ArrayList of strings.
   *
   * @return an ArrayList<ArrayList<String>> that contains each element read from the
   *     ProductQuantities.csv file
   */
  public static ArrayList<ArrayList<String>> getProductQuantityFromCsv()
      throws FileNotFoundException {
    Scanner scanner = null;
    ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    scanner = new Scanner(new File("./Data/ProductQuantities.csv"));
    scanner.useDelimiter(CSV_SEPARATOR);
    while (scanner.hasNext()) {
      records.add(getRecordFromLine(scanner.nextLine()));
    }
    scanner.close();
    return records;
  }

  /**
   * .csv file format reader for loading the contents of the "Users.csv" file to the main memory as
   * an ArrayList of strings.
   *
   * @return an ArrayList<ArrayList<String>> that contains each element read from the Users.csv file
   */
  public static ArrayList<ArrayList<String>> getUsersFromCsv() throws FileNotFoundException {
    Scanner scanner = null;
    ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    scanner = new Scanner(new File("./Data/Users.csv"));
    scanner.useDelimiter(CSV_SEPARATOR);
    while (scanner.hasNext()) {
      records.add(getRecordFromLine(scanner.nextLine()));
    }
    scanner.close();
    return records;
  }

  /**
   * .csv file format reader for loading the contents of the "Customers.csv" file to the main memory
   * as an ArrayList of strings.
   *
   * @return an ArrayList<ArrayList<String>> that contains each element read from the Customers.csv
   *     file
   */
  public static ArrayList<ArrayList<String>> getRegisteredCustomersFromCsv()
      throws FileNotFoundException {
    Scanner scanner = null;
    ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    scanner = new Scanner(new File("./Data/Customers.csv"));
    scanner.useDelimiter(CSV_SEPARATOR);
    while (scanner.hasNext()) {
      records.add(getRecordFromLine(scanner.nextLine()));
    }
    scanner.close();
    return records;
  }

  /**
   * .csv file format reader for loading the contents of the "Orders.csv" file to the main memory as
   * an ArrayList of strings.
   *
   * @return an ArrayList<ArrayList<String>> that contains each element read from the Order.csv file
   */
  public static ArrayList<ArrayList<String>> getOrdersFromCsv() throws FileNotFoundException {
    Scanner scanner = null;
    ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    scanner = new Scanner(new File("./Data/Orders.csv"));
    scanner.useDelimiter(CSV_SEPARATOR);
    while (scanner.hasNext()) {
      records.add(getRecordFromLine(scanner.nextLine()));
    }
    scanner.close();
    return records;
  }

  /**
   * .csv file format reader for loading the contents of the "StorageOrders.csv" file to the main
   * memory as an ArrayList of strings.
   *
   * @return an ArrayList<ArrayList<String>> that contains each element read from the
   *     StorageOrders.csv
   */
  public static ArrayList<ArrayList<String>> getStorageOrdersFromCsv()
      throws FileNotFoundException {
    Scanner scanner = null;
    ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    scanner = new Scanner(new File("./Data/StorageOrders.csv"));
    scanner.useDelimiter(CSV_SEPARATOR);
    while (scanner.hasNext()) {
      records.add(getRecordFromLine(scanner.nextLine()));
    }
    scanner.close();
    return records;
  }

  /**
   * .csv file format reader for loading the contents of the "Suppliers.csv" file to the main memory
   * as an ArrayList of strings.
   *
   * @return an ArrayList<ArrayList<String>> that contains each element read from the Suppliers.csv
   *     file
   */
  public static ArrayList<ArrayList<String>> getSuppliersFromCsv() throws FileNotFoundException {
    Scanner scanner = null;
    ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    scanner = new Scanner(new File("./Data/Suppliers.csv"));
    scanner.useDelimiter(CSV_SEPARATOR);
    while (scanner.hasNext()) {
      records.add(getRecordFromLine(scanner.nextLine()));
    }
    scanner.close();
    return records;
  }

  /**
   * A String parser used for reading a single line of a .csv file using the ";" delimiter.
   *
   * @param line of the .csv file that contains separators
   * @return ArrayList<String> that contains the split line according to the separators
   */
  private static ArrayList<String> getRecordFromLine(String line) {
    ArrayList<String> values = new ArrayList<String>();
    Scanner rowScanner = new Scanner(line);
    rowScanner.useDelimiter(CSV_SEPARATOR);
    while (rowScanner.hasNext()) {
      values.add(rowScanner.next());
    }
    return values;
  }
}
