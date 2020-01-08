package ERP_Core;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class represents a cashier made in the ERP system. The class is responsible for creating a new
 * Cashier printing products' menu, customer's menu, cashier's menu and new order's menu. It
 * contains methods that implement the product, customer and order's menu. It also has a method that
 * creates order's menu.
 *
 * @version 1.0
 * @author George Liargovas
 */
public class Cashier extends User {

  public Cashier(String name, String surname, String username, String password) {
    super(name, surname, username, password);
  }

  public Cashier(int idUser, String name, String surname, String username, String password) {
    super(idUser, name, surname, username, password);
  }
  /**
   * Login Method for Cashiers.
   *
   * @param username the username of the Cashier
   * @param password the password of the Cashier
   * @throws Exception invalid credentials given
   */
  public static void login(String username, String password) throws Exception {
    Cashier cashier;
    for (User user : User.getUsers()) {
      if (user instanceof Cashier) {
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
          cashier = (Cashier) user;
          System.out.printf("Welcome %s %s!\n", cashier.getName(), cashier.getSurname());
          cashier.getMenu();
          return;
        }
      }
    }
    throw new Exception("Invalid Credentials");
  }

  /**
   * Prints product's Menu. Several options are available. When the return option is selected, the
   * user is returned to the main menu.
   */
  public static void printProductMenu() {
    System.out.print(
        "--- Product Menu ---\n"
            + "1) View All Products\n"
            + "2) Search for Specific Products (by product id)\n"
            + "3) Search for Specific Products (by product name)\n"
            + "4) Return to Previous Menu\n"
            + "Option: ");
  }

  /**
   * The customer's menu. Several options are available. When the return option is selected, the
   * user is returned to the main menu.
   */
  public static void printCustomerMenu() {
    System.out.print(
        "--- Customer Menu ---\n"
            + "1) View All Customers\n"
            + "2) Search for Specific Customers (by customer name)\n"
            + "3) Search for Specific Customer (by customer id)\n"
            + "4) Search for Specific Customer (by telephone number)\n"
            + "5) Add a new Customer\n"
            + "6) Edit Customer Telephone\n"
            + "7) Edit Customer Address\n"
            + "8) Delete a Customer\n"
            + "9) View Order History\n"
            + "10) View Specific Customer Order History\n"
            + "11) Return to Previous Menu\n"
            + "Option: ");
  }

  /**
   * The cashier's menu. Several options are available. When the return option is selected, the user
   * is logged out and returned to the main menu.
   */
  public static void printMenu() {
    System.out.print(
        "--- Cashier Menu ---\n"
            + "1) Products\n"
            + "2) Customers\n"
            + "3) Make a New Customer Order\n"
            + "4) Save changes\n"
            + "5) Logout and Return to Main Menu\n"
            + "Option: ");
  }

  /**
   * The order's menu. Several options are available. When the return option is selected, the user
   * is returned to the main menu.
   */
  public static void printNewOrderMenu() {
    System.out.print(
        "--- New Order Menu ---\n"
            + "Who does the order concern?\n"
            + "1) Already Registered Customer\n"
            + "2) Guest Customer (not registered)\n"
            + "3) Return To Previous Menu\n"
            + "Option: ");
  }

  /** Implementation of the product menu. */
  public void getProductMenu() {
    Scanner in = new Scanner(System.in);
    int ans;
    for (; ; ) {
      printProductMenu();
      try {
        ans = in.nextInt();
        switch (ans) {
          case 1:
            Storage.printAllProductsWithQuantities();
            break;
          case 2:
            Storage.searchAndPrintProductByIdMenu();
            break;
          case 3:
            Storage.searchAndPrintProductsByNameMenu();
            break;
          case 4:
            return;
        }
      } catch (InputMismatchException e) {
        System.err.println("Your option must be an integer number. Try again...");
        // clear scanner buffer
        in.nextLine();
      }
    }
  }

  /** Implementation of the customer menu. */
  public void getCustomerMenu() {
    Scanner in = new Scanner(System.in);
    int ans;
    for (; ; ) {
      printCustomerMenu();
      try {
        ans = in.nextInt();
        switch (ans) {
          case 1:
            RegisteredCustomer.printAllCustomers();
            break;
          case 2:
            RegisteredCustomer.searchAndPrintCustomerByNameMenu();
            break;
          case 3:
            RegisteredCustomer.searchAndPrintCustomerByIdMenu();
            break;
          case 4:
            RegisteredCustomer.searchAndPrintCustomerByTelephoneMenu();
            break;
          case 5:
            RegisteredCustomer.registerNewCustomerMenu();
            break;
          case 6:
            RegisteredCustomer.changeCustomerTelephoneMenu();
            break;
          case 7:
            RegisteredCustomer.changeCustomerAddressMenu();
            break;
          case 8:
            RegisteredCustomer.deleteCustomerMenu();
            break;
          case 9:
            Order.printOrderHistory();
            break;
          case 10:
            Order.printOrderHistoryMenu();
            break;
          case 11:
            return;
        }
      } catch (InputMismatchException e) {
        System.err.println("Your option must be an integer number. Try again...");
        // clear scanner buffer
        in.nextLine();
      }
    }
  }

  /** Create order menu. */
  public void makeOrderMenu() {
    Scanner in = new Scanner(System.in);
    int ans;
    for (; ; ) {
      printNewOrderMenu();
      try {
        ans = in.nextInt();
        switch (ans) {
          case 1:
            Order.makeOrder(this, true);
            break;
          case 2:
            Order.makeOrder(this, false);
            break;
          case 3:
            return;
        }
      } catch (InputMismatchException e) {
        System.err.println("Your option must be an integer number. Try again...");
        // clear scanner buffer
        in.nextLine();
      }
    }
  }

  /** The main menu of the cashier. */
  @Override
  public void getMenu() {
    Scanner in = new Scanner(System.in);
    int ans;
    for (; ; ) {
      printMenu();
      try {
        ans = in.nextInt();
        switch (ans) {
          case 1:
            getProductMenu();
            break;
          case 2:
            getCustomerMenu();
            break;
          case 3:
            makeOrderMenu();
            break;
          case 4:
        	Main.saveAllListsToCsv();
          	System.out.println("Changes saved!" );
          	break;
          case 5:
            return;
        }
      } catch (InputMismatchException e) {
        System.err.println("Your option must be an integer number. Try again...");
        // clear scanner buffer
        in.nextLine();
      }
    }
  }
}
