import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StorageOrder {
  private static int counter = 0;
  private final int storageOrderNumber;
  private final String storageOrderDate;
  private final double totalCost;
  private final Supplier supplier;
  private final Storekeeper storekeeper;
  public ArrayList<int[]> supplies = new ArrayList<int[]>();
  public static ArrayList<StorageOrder> orders = new ArrayList<StorageOrder>();

  /**
   * Constructor for creating a new order.
   *
   * @param storageOrderDate
   * @param totalCost
   * @param supplier
   * @param storekeeper
   */
  public StorageOrder(
      String storageOrderDate, Supplier supplier, Storekeeper storekeeper, ArrayList<int[]> supplies) {
    this.storageOrderNumber = ++counter;
    this.storageOrderDate = storageOrderDate;
    this.supplies = supplies;
    this.totalCost = calculateBasketCost(supplies);
    this.supplier = supplier;
    this.storekeeper = storekeeper;
  }

  /**
   * Constructor for already created products read from .csv file. There is non need to calculate
   * the total cost again as it has been created.
   *
   * @param StorageOrderNumber
   * @param storageOrderDate
   * @param totalCost
   * @param supplier
   * @param storekeeper
   */
  public StorageOrder(
      int StorageOrderNumber,
      String storageOrderDate,
      double totalCost,
      Supplier supplier,
      Storekeeper storekeeper,
      ArrayList<int[]> supplies) {
    this.storageOrderNumber = StorageOrderNumber;
    this.storageOrderDate = storageOrderDate;
    this.totalCost = totalCost;
    this.supplier = supplier;
    this.storekeeper = storekeeper;
  }
/**
 * Returns the Date of the order
 * @return storageOrderDate, type String
 */
  public String getStorageOrderDate() {
    return storageOrderDate;
  }
/**
 * Returns the total cost
 * @return TotalCost, type double
 */
  public double getTotalCost() {
    return totalCost;
  }
/**
 * Returns the object supplier
 * @return the supplier
 */
  public Supplier getSupplier() {
    return supplier;
  }
/**
 * Returns the object Storekeeper
 * @return storekeeper
 */
  public Storekeeper getStorekeeper() {
    return storekeeper;
  }
/**
 * Returns the Number of the Order
 * @return storageOrderNumber, type int
 */
  public int getStorageOrderNumber() {
    return storageOrderNumber;
  }
/**
 * Returns the supplies from the list
 * @return supplies
 */
  public ArrayList<int[]> getSupplies() {
    return supplies;
  }
/**
 * Returns the orders from the list
 * @return orders
 */
  public static ArrayList<StorageOrder> getOrders() {
    return orders;
  }

  /** Prints all the storage orders */
  public static void printStorageOrderHistory() {
    int counter = 1;
    System.out.println("***Order History***\n");
    for (StorageOrder order : orders) {
      System.out.printf("-> Storage Order #%d\n", counter++);
      order.printStorageOrderFormatted();
    }
  }

  /** 
   * Contains the menu for printing the order history from a specific supplier 
   * according to user input. 
   */
  public static void printOrderHistoryMenu() {
    Scanner in = new Scanner(System.in);
    int input;
    for (; ; ) {
      try {
        System.out.print(
            "Enter the id of the supplier to get order history: ");
        input = in.nextInt();
        printOrderHistory(input);
        return;
      } catch (InputMismatchException e) {
        System.err.println("Invalid input given. Id must be an integer. Try again...");
      } catch (Exception e) {
        e.printStackTrace();
        System.err.println("Something went wrong. Returning to previous menu");
        return;
      }
    }
  }

  /**
   * Prints the orders of a customer with a specific given id.
   *
   * @param id
   */
  public static void printOrderHistory(int id) {
    int counter = 0;
    for (StorageOrder order : orders) {
      if (order.getSupplier() != null && order.getSupplier().getId() == id) {
        if (counter == 0) {
          System.out.printf("Printing orders made from supplier with id %d\n\n:", id);
        }
        System.out.printf("-> Order #%d\n", ++counter);
        order.printStorageOrderFormatted();
      }
    }
    if (counter == 0) {
      System.out.printf("Customer with id %d has not made any orders\n", id);
    }
  }
  
  /**
   * Calculates the cost of a product and quantity combination.
   *
   * @param id
   * @param quantity
   * @return
   */
  public static double calculateCost(int id, int quantity) {
    Product prod = Storage.searchById(id);
    double price = prod.getSalePrice();
    double cost = price * quantity;
    return cost;
  }

  /**
   * Calculates the total cost of a storage order supplies.
   *
   * @param basket
   * @return
   */
  public static double calculateBasketCost(ArrayList<int[]> basket) {
    double totalCost = 0;
    double productCost;
    Product prod;
    for (int[] product : basket) {
      prod = Storage.searchById(product[0]);
      productCost = prod.getSalePrice() * product[1];
      totalCost += productCost;
    }
    return totalCost;
  }

  /**
   * Prints a product's information with a given id, in the format used to print the storage order
   * information.
   *
   * @param id
   * @param quantity
   */
  public static void printProduct(int id, int quantity) {
    Product prod = Storage.searchById(id);
    String name = prod.getName();
    double price = calculateCost(id, quantity);
    System.out.printf(
        "Product id: %d | Product name: %s | Quantity: %d | Price: %.02f",
        prod.getProductId(), prod.getName(), quantity, price);
  }

  /**
   * Prints a preview of the order, in the appropriate format.
   *
   * @param storekeeper
   * @param supplier
   * @param supplies
   */
  public static void previewOrder(
      Storekeeper storekeeper, Supplier supplier, ArrayList<int[]> supplies) {
    int storekeeperId = storekeeper.getIdUser();
    double totalCost = 0;
    System.out.println("***Preview of Storage Order***");
    System.out.println();
    System.out.println("---Storekeeper's Code: " + storekeeperId);
    System.out.println();
    System.out.println("---Supplier data");
    if (supplier != null) {
      System.out.println("Name: " + supplier.getName());
      System.out.println("Address: " + supplier.getAddress());
    } else {
      System.out.println("Supplier Deleted");
    }
    System.out.println("\n---Supplies List: ");
    for (int prod[] : supplies) {
      printProduct(prod[0], prod[1]);
      System.out.println();
    }
    System.out.println();
    totalCost = calculateBasketCost(supplies);
    System.out.println("---Storage order's total cost: " + totalCost);
  }

  /** Prints the storage order after it has been confirmed, in the appropriate format. */
  public void printFinalOrder() {
    System.out.println("\n***Final Order Report***");
    this.printStorageOrderFormatted();
    System.out.println("***End of report***\n");
  }

  /** Prints the storage order in the appropriate format. */
  public void printStorageOrderFormatted() {
    int storekeeperId = storekeeper.getIdUser();
    System.out.println();
    System.out.println();
    System.out.println("---Storage Order id: " + this.getStorageOrderNumber());
    System.out.println("---Storage Order Date: " + this.getStorageOrderDate());
    System.out.println("---Storekeeper's Code: " + storekeeperId);
    System.out.println("---Customer's data");
    if (supplier != null) {
      System.out.println("Name: " + this.supplier.getName());
      System.out.println("Address: " + this.supplier.getAddress());
    } else {
      System.out.println("Supplier Deleted");
    }
    System.out.println("\n---Product Basket: ");
    for (int prod[] : this.getSupplies()) {
      try {
        printProduct(prod[0], prod[1]);
      } catch (NoSuchElementException e) {
        System.out.println("This product has been removed from the storage.");
      }
      System.out.println();
    }
    System.out.println("---Order's total cost: " + totalCost);
  }

  /**
   * Contains the prompts in order to make a storage order.
   *
   * @param storekeeper
   * @param isRegistered
   */
  public static void makeOrder(Storekeeper storekeeper, boolean isRegistered) {
    Scanner in = new Scanner(System.in);
    String ans;
    Supplier supplier;
    ArrayList<int[]> supplies;
    int id;
    for (; ; ) {
      try {
        if (isRegistered) {
          System.out.println("*** Storage Order Menu ***");
          System.out.print(
              "Enter the id of the supplier the order is about. " + "To cancel, press \"enter\": ");
          ans = in.nextLine();
          if (ans.equals("")) {
            System.out.println("Process cancelled, returning to previous menu...");
            return;
          }
          id = Integer.parseInt(ans);
          supplier = Supplier.searchById(id);
        } else {
          System.out.println("*** Guest Customer Order Menu ***");
          supplier = null;
        }
        supplies = fillBasket();
        StorageOrder.previewOrder(storekeeper, supplier, supplies);

        for (; ; ) {
          System.out.print("Confirm storge order? (Y/N): ");
          ans = in.nextLine();
          if (ans.toLowerCase().equals("y") || ans.toLowerCase().equals("yes")) {
            StorageOrder.confirmOrder(storekeeper, supplier, supplies);
            return;
          } else if (ans.toLowerCase().equals("n") || ans.toLowerCase().equals("no")) {
            System.out.println("Process cancelled. Returning to previous menu...");
            return;
          }
          System.out.println(
              "Please enter either \"Yes\"(\"Y\") or \"No\"(\"N\").\n" + "Try again...");
        }
      } catch (NoSuchElementException e) {
        System.out.println("Customer with such id does not exist. Try again...");
        continue;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input given. Try again...");
        continue;
      }
    }
  }
  
  /**
   * Contains the prompts in order for the user to fill the order basket
   * @return tempBasket 
   */
  public static ArrayList<int[]> fillBasket() {
	    Scanner in = new Scanner(System.in);
	    String input;
	    ArrayList<int[]> tempBasket = new ArrayList<int[]>();
	    int[] tempProduct = new int[2];
	    boolean alreadyExists;
	    for (; ; ) {
	      try {
	        tempProduct = new int[2];
	        System.out.print(
	            "Enter the id of the product you want to get.\n"
	                + "Press only \"enter\" when you do not want to add any products: ");
	        input = in.nextLine();
	        if (input.equals("")) {
	          return tempBasket;
	        }
	        tempProduct[0] = Integer.parseInt(input);
	        System.out.println("Enter the quantity of the product you want to shop");
	        tempProduct[1] = in.nextInt();
	        in.nextLine();
	        alreadyExists = checkIfProductAlreadyExistsAndAddQuantity(tempProduct[0], tempProduct[1], tempBasket);
	        if (alreadyExists == false) {
	          tempBasket.add(tempProduct);
	        }
	      } catch (NumberFormatException e) {
	        System.out.println("Product id must be an integer larger than 0. Try again...");
	        in.nextLine();
	      } catch (InputMismatchException e) {
	        System.out.println("The quantity must be an integer larger than 0. Try again...");
	        in.nextLine();
	      } catch (NoSuchElementException e) {
	        System.out.println("Product with such id does not exist. Try again...");
	      }
	    }
	  }
  /**
   * Checks if the Product exists and adds the quantity that is needed
   * @param id
   * @param quantity
   * @param supplies
   * @return false or true
   */
  public static boolean checkIfProductAlreadyExistsAndAddQuantity(int id, int quantity, ArrayList<int[]> supplies) {
	    for (int[] line : supplies) {
	      if (line[0] == id) {
	    	line[1] += quantity; 
	        return true;
	      }
	    }
	    return false;
  }
  
  /**
   * Makes the needed changes in the storage product quantities, and creates the order object with
   * the system's current date and time.
   *
   * @param cashier
   * @param customer
   * @param basket
   */
  public static void confirmOrder(
      Storekeeper storekeeper, Supplier supplier, ArrayList<int[]> supplies) {
    for (int[] product : supplies) {
      try {
        // add products to storage
        Storage.addProductQuantity(product[0], product[1]);
      } catch (NoSuchElementException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    String orderDate = getCurrentDate();
    StorageOrder newOrder = new StorageOrder(orderDate, supplier, storekeeper, supplies);
    newOrder.printFinalOrder();
  }

  /**
   * Utility method that returns the system's date and time as a String.
   *
   * @return date
   */
  public static String getCurrentDate() {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
    Date date = new Date();
    return dateFormatter.format(date);
  }
  
  /**
   * Creates a new Storage Order from an Arraylist of strings by parsing its contents.
   *
   * @param orders
   */
  public static void createStorageOrdersFromList(ArrayList<ArrayList<String>> orders) {
    int supplierId, storekeeperId, orderNo;
    double totalCost;
    ArrayList<int[]> supplies;
    String orderDate;
    Supplier supplier = null;
    Storekeeper storekeeper;
    int[] temp = null;
    for (ArrayList<String> order : orders) {
      supplies = new ArrayList<int[]>();
      orderNo = Integer.parseInt(order.get(0));
      orderDate = order.get(1);
      totalCost = Double.parseDouble(order.get(2));
      supplierId = Integer.parseInt(order.get(3));
      storekeeperId = Integer.parseInt(order.get(4));
      try {
          supplier = Supplier.searchById(supplierId);
      } catch (NoSuchElementException e) {
          supplier = null;
        }
      try {
         storekeeper = (Storekeeper) User.searchUserById(storekeeperId);
      } catch (NoSuchElementException e) {
        storekeeper = null;
      }
      for (int i = 5; i < order.size(); i += 2) {
        temp = new int[2];
        temp[0] = Integer.parseInt(order.get(i));
        temp[1] = Integer.parseInt(order.get(i + 1));
        supplies.add(temp);
      }
      new StorageOrder(orderNo, orderDate, totalCost, supplier, storekeeper, supplies);
    }
  }
}

