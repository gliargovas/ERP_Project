import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Class represents an order made in the ERP system.
 *
 * @version 1.0
 * @author George Liargovas
 * @author Eirini Piperou
 */
public class Order {
  /** The unique id of the order */
  private final int orderNo;
  /** Date and time the order was made */
  private final String orderDate;
  /** Total cost of the order in euro currency */
  private double totalCost;
  /** The customer that made the order */
  private final Customer customer;
  /** The user that made the order */
  private final Cashier cashier;
  /** Contains the combination of the product id and quantity of all the products */
  private ArrayList<int[]> basket = new ArrayList<int[]>();
  /** The counter of the created orders, used for generating new order ids */
  private static int count = FileHandler.getOrderCounterFromFile();
  /** The order list that contains all the orders made */
  protected static ArrayList<Order> orders = new ArrayList<Order>();

  /**
   * Constructor for creating a new order.
   *
   * @param orderDate
   * @param customer
   * @param cashier
   * @param basket
   */
  public Order(String orderDate, Customer customer, Cashier cashier, ArrayList<int[]> basket) {
    this.orderNo = count++;
    this.orderDate = orderDate;
    this.totalCost = calculateBasketCost(basket);
    this.customer = customer;
    this.cashier = cashier;
    this.basket = basket;
    orders.add(this);
  }

  /**
   * Constructor for already created products read from .csv file. There is non need to calculate
   * the total cost again as it has been created.
   *
   * @param orderNo
   * @param orderDate
   * @param totalCost
   * @param customer
   * @param cashier
   * @param basket
   */
  public Order(
      int orderNo,
      String orderDate,
      double totalCost,
      Customer customer,
      Cashier cashier,
      ArrayList<int[]> basket) {
    this.orderNo = orderNo;
    this.orderDate = orderDate;
    this.totalCost = totalCost;
    this.customer = customer;
    this.cashier = cashier;
    this.basket = basket;
    orders.add(this);
  }

  /**
   * Returns the order number.
   *
   * @return
   */
  public int getOrderNo() {
    return orderNo;
  }

  /**
   * Returns the order date.
   *
   * @return
   */
  public String getOrderDate() {
    return orderDate;
  }

  /**
   * Returns the order total cost.
   *
   * @return
   */
  public double getTotalCost() {
    return totalCost;
  }
  
  public void setTotalCost(double totalCost) {
	  this.totalCost = totalCost;
  }

  /**
   * Returns the order's Customer object.
   *
   * @return
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * Returns the order's Cashier object.
   *
   * @return
   */
  public Cashier getCashier() {
    return cashier;
  }

  /**
   * Returns the ArrayList that represents the order's basket.
   *
   * @return
   */
  public ArrayList<int[]> getBasket() {
    return basket;
  }

  /**
   * Returns the ArrayList that the order objects are stored.
   *
   * @return
   */
  public static ArrayList<Order> getOrders() {
    return orders;
  }

  /**
   * Returns the id counter
   *
   * @return
   */
  public static int getIdCounter() {
    return count;
  }

  /** Prints all the orders */
  public static void printOrderHistory() {
    int counter = 1;
    System.out.println("***Order History***\n");
    for (Order order : orders) {
      System.out.printf("-> Order #%d\n", counter++);
      order.printOrderFormatted();
    }
  }

  /** Contains the menu for printing a specific customer's order history according to user input. */
  public static void printOrderHistoryMenu() {
    Scanner in = new Scanner(System.in);
    int input;
    for (; ; ) {
      try {
        System.out.print(
            "Enter the id of the customer for whom you want to print the order history: ");
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
    for (Order order : orders) {
      if (order.getCustomer() != null && ((RegisteredCustomer) order.getCustomer()).getId() == id) {
        if (counter == 0) {
          System.out.printf("Printing orders of the customer with id %d\n\n:", id);
        }
        System.out.printf("-> Order #%d\n", ++counter);
        order.printOrderFormatted();
      }
    }
    if (counter == 0) {
      System.out.printf("Customer with id %d has not made any orders\n", id);
    }
  }

  /**
   * Increases the quantity of a product with a given id in the storage by a specific given
   * quantity.
   *
   * @param id
   * @param quantity
   */
  public void addProductToBasket(int id, int quantity) {
    int[] products = {id, quantity};
    basket.add(products);
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
   * Calculates the total cost of an order's basket.
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
   * Prints a product's information with a given id, in the format used to print the order
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
   * @param cashier
   * @param customer
   * @param basket
   */
  public static void previewOrder(Cashier cashier, Customer customer, ArrayList<int[]> basket) {
    int cashierId = cashier.getIdUser();
    double totalCost = 0;
    System.out.println("***Preview of Order***");
    System.out.println();
    System.out.println("---Cashier's Code: " + cashierId);
    System.out.println();
    System.out.println("---Customer's data");
    if (customer instanceof RegisteredCustomer) {
      String customerName = customer.getCompanyName();
      System.out.println("Name: " + customerName);
      System.out.println("Address: " + customer.getAddress());
    } else {
      System.out.println("Guest or Deleted");
    }
    System.out.println("\n---Product Basket: ");
    for (int prod[] : basket) {
      printProduct(prod[0], prod[1]);
      System.out.println();
    }
    System.out.println();
    totalCost = calculateBasketCost(basket);
    System.out.println("---Order's total cost: " + totalCost);
  }

  /** Prints the order after it has been confirmed, in the appropriate format. */
  public void printFinalOrder() {
    System.out.println("\n***Final Order Report***");
    this.printOrderFormatted();
    System.out.println("***End of report***\n");
  }

  /** Prints the order in the appropriate format. */
  public void printOrderFormatted() {
    int cashierId = cashier.getIdUser();
    System.out.println();
    System.out.println();
    System.out.println("---Order id: " + this.getOrderNo());
    System.out.println("---Order Date: " + this.getOrderDate());
    System.out.println("---Cashier's Code: " + cashierId);
    System.out.println("---Customer's data");
    if (customer instanceof RegisteredCustomer) {
      String customerName = customer.getCompanyName();
      System.out.println("Name: " + customerName);
      System.out.println("Address: " + this.getCustomer().getAddress());
    } else {
      System.out.println("Guest or Deleted");
    }
    System.out.println("\n---Product Basket: ");
    for (int prod[] : basket) {
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
   * Contains the prompts in order to make a customer order.
   *
   * @param cashier
   * @param isRegistered
   */
  public static void makeOrder(Cashier cashier, boolean isRegistered) {
    Scanner in = new Scanner(System.in);
    String ans;
    Customer customer;
    ArrayList<int[]> basket;
    int id;
    for (; ; ) {
      try {
        if (isRegistered) {
          System.out.println("*** Registered Customer Order Menu ***");
          for (;;) {
            System.out.print("Enter the id of the registered cutomer the order is about. "
              + "To cancel, press \"enter\": ");
            ans = in.nextLine();
            if (ans.equals("")) {
              System.out.println("Process cancelled, returning to previous menu...");
                return;
            }
            id = Integer.parseInt(ans);
            customer = RegisteredCustomer.searchById(id);
            if (customer == null) {
              System.out.println("Registered customer with such id does not exist. Try again..." ); 
              continue;
            }
            break;
          } 
        } else {
        System.out.println("*** Guest Customer Order Menu ***");
        customer = null;
        }
        basket = fillBasket();
        Order.previewOrder(cashier, customer, basket);

        for (; ; ) {
          System.out.print("Confirm order? (Y/N): ");
          ans = in.nextLine();
          if (ans.toLowerCase().equals("y") || ans.toLowerCase().equals("yes")) {
            Order.confirmOrder(cashier, customer, basket);
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
   * Makes the needed changes in the storage product quantities, and creates the order object with
   * the system's current date and time.
   *
   * @param cashier
   * @param customer
   * @param basket
   */
  public static void confirmOrder(Cashier cashier, Customer customer, ArrayList<int[]> basket) {
    for (int[] product : basket) {
      try {
        // remove the products from storage
        Storage.removeProductQuantity(product[0], product[1]);
      } catch (NoSuchElementException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    String orderDate = getCurrentDate();
    Order newOrder = new Order(orderDate, customer, cashier, basket);
    if (customer instanceof RegisteredCustomer && newOrder.checkPointDiscount((RegisteredCustomer)customer) == true) {
    	newOrder.setTotalCost(0.9 * newOrder.getTotalCost());
    }
    newOrder.printFinalOrder();
  }
  
  public boolean checkPointDiscount(RegisteredCustomer c) {
	  ((RegisteredCustomer)(this.getCustomer())).setPoints((int) Math.round(((this.getTotalCost() * 5))));
	  System.out.println("With this purchase " + Math.round(this.getTotalCost() * 5) +" points were earned!");
	  if (c.getPoints() >= 10000) {
		  System.out.println("Order has been discounted by 10% by redeeming 10000 points!");
		  c.setPoints(c.getPoints() - 10000);
		  return true;
	  }
	  return false;
  }

  /**
   * Utility method that returns the system's date and time as a String.
   *
   * @return
   */
  public static String getCurrentDate() {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    Date date = new Date();
    return dateFormatter.format(date);
  }

  /**
   * Contains the prompts in order for the user to fill the order basket.
   *
   * @return
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
            "Enter the id of the product you want to buy.\n"
                + "Press only \"enter\" when you do not want to add any products: ");
        input = in.nextLine();
        if (input.equals("")) {
          return tempBasket;
        }
        tempProduct[0] = Integer.parseInt(input);
        System.out.println("Enter the quantity of the product you want to shop");
        tempProduct[1] = in.nextInt();
        in.nextLine();
        alreadyExists =
            checkIfProductAlreadyExistsAndAddToBasket(tempProduct[0], tempProduct[1], tempBasket);
        if (alreadyExists == false
            && Storage.checkIfQuantityIsEnough(tempProduct[0], tempProduct[1]) == true) {
          tempBasket.add(tempProduct);
        } else {
          System.out.println("There are not enough product units available.\n" + "Try Again...");
        }
      } catch (NumberFormatException e) {
        System.out.println("Product id must be an integer larger than 0. Try again...");
        in.nextLine();
      } catch (InputMismatchException e) {
        System.out.println("The quantity must be an integer larger than 0. Try again...");
        in.nextLine();
      } catch (NoSuchElementException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Checks if a product already exists in the temporary basket. If it does, the method checks if
   * there are enough units available in the storage and either updates the basket or rejects the
   * product.
   *
   * @param id
   * @param quantity
   * @param basket
   * @return
   */
  public static boolean checkIfProductAlreadyExistsAndAddToBasket(
      int id, int quantity, ArrayList<int[]> basket) {
    int combinedQuantity;
    for (int[] line : basket) {
      if (line[0] == id) {
        combinedQuantity = line[1] + quantity;
        if (Storage.checkIfQuantityIsEnough(id, combinedQuantity) == true) {
          line[1] = combinedQuantity;

        } else {
          System.out.println(
              "There are not enough product units available.\n"
                  + "Change the quantity or order more. Try Again...");
        }
        return true;
      }
    }
    return false;
  }

  /**
   * Creates a new order from an Arraylist of strings by parsing its contents.
   *
   * @param orders
   */
  public static void createOrdersFromList(ArrayList<ArrayList<String>> orders) {
    int customerId, cashierId, orderNo;
    double totalCost;
    ArrayList<int[]> basket;
    String orderDate;
    Customer customer = null;
    Cashier cashier;
    int[] temp = null;
    for (ArrayList<String> order : orders) {
      basket = new ArrayList<int[]>();
      orderNo = Integer.parseInt(order.get(0));
      orderDate = order.get(1);
      totalCost = Double.parseDouble(order.get(2));
      customerId = Integer.parseInt(order.get(3));
      basket = new ArrayList<int[]>();
      if (customerId == 0) {
        customer = null;
      } else {
        try {
          customer = RegisteredCustomer.searchById(customerId);
        } catch (NoSuchElementException e) {
          customer = null;
        }
      }
      cashierId = Integer.parseInt(order.get(4));
      try {
        cashier = (Cashier) User.searchUserById(cashierId);
      } catch (NoSuchElementException e) {
        cashier = null;
      }
      for (int i = 5; i < order.size(); i += 2) {
        temp = new int[2];
        temp[0] = Integer.parseInt(order.get(i));
        temp[1] = Integer.parseInt(order.get(i + 1));
        basket.add(temp);
      }
      new Order(orderNo, orderDate, totalCost, customer, cashier, basket);
    }
  }
}
