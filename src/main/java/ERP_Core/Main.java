package ERP_Core;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  /** The main method of the program. Starts the ERP */
  public static void main(String[] args) {
    printLogo();
    loadAllListsFromCsv();
    getMainMenu();
  }

  /** Prints the logo of the ERP */
  public static void printLogo() {
    System.out.println(
        "\n\n\n\n\n"
            + " ______   _______  _        _______  _______  _______                    _______  _______  _______ \n"
            + "(  __  \\ (  ___  )( (    /|(  ____ \\(  ____ \\(  ____ )       |\\     /|  (  ____ \\(  ____ )(  ____ )\n"
            + "| (  \\  )| (   ) ||  \\  ( || (    \\/| (    \\/| (    )|       ( \\   / )  | (    \\/| (    )|| (    )|\n"
            + "| |   ) || (___) ||   \\ | || |      | (__    | (____)| _____  \\ (_) /   | (__    | (____)|| (____)|\n"
            + "| |   | ||  ___  || (\\ \\) || | ____ |  __)   |     __)(_____)  ) _ (    |  __)   |     __)|  _____)\n"
            + "| |   ) || (   ) || | \\   || | \\_  )| (      | (\\ (           / ( ) \\   | (      | (\\ (   | (      \n"
            + "| (__/  )| )   ( || )  \\  || (___) || (____/\\| ) \\ \\__       ( /   \\ )  | (____/\\| ) \\ \\__| )      \n"
            + "(______/ |/     \\||/    )_)(_______)(_______/|/   \\__/       |/     \\|  (_______/|/   \\__/|/ \n\n\n");
  }

  /** Prints the main menu of the ERP system */
  public static void printMainMenu() {
    System.out.print(
        "****** MAIN MENU ******\n\n"
            + "1) Login as a Cashier\n"
            + "2  Login as a Storekeeper\n"
            + "3) Login as a Data Analyst\n"
            + "4) Login as Administrator\n"
            + "5) Exit Application\n\n"
            + "\tAnswer: ");
  }

  /** Select the type you want to connect to */
  public static void getMainMenu() {
    Scanner in = new Scanner(System.in);
    int ans;
    for (; ; ) {
      printMainMenu();
      try {
        ans = in.nextInt();
        in.nextLine();
        switch (ans) {
          case 1:
            cashierLogin();
            break;
          case 2:
            storekeeperLogin();
            break;
          case 3:
            dataAnalystLogin();
            break;
          case 4:
            administratorLogin();
            break;
          case 5:
            saveAllListsToCsv();
            FileHandler.writeAllCountersToFile();
            in.close();
            Thread.sleep(100);
            return;
          default:
            System.out.println("Prease enter a valid option. Try again...");
        }
      } catch (InputMismatchException e) {
        in.nextLine();
        System.out.println("Your option must be an integer number. Try again...");
      } catch (Exception e) {
        System.out.println("An error occured. Try again...");
        e.printStackTrace();
      }
    }
  }

  /** Login as a Cashier */
  public static void cashierLogin() {
    Scanner in = new Scanner(System.in);
    String username, password;
    try {
      System.out.print("Enter your username.\n" + "To exit press \"enter\": ");
      username = in.nextLine();
      if (username.equals("")) {
        System.out.println("Returning to main menu...");
        return;
      }
      System.out.print("Enter your password: ");
      password = in.nextLine();
      Cashier.login(username, password);
    } catch (Exception e) {
      System.out.println("Invalid credentials given. Try again...");
    }
  }

  /** Login as a Storekeeper */
  public static void storekeeperLogin() {
    Scanner in = new Scanner(System.in);
    String username, password;
    try {
      System.out.print("Enter your username.\n" + "To exit press \"enter\": ");
      username = in.nextLine();
      if (username.equals("")) {
        System.out.println("Returning to main menu...");
        return;
      }
      System.out.print("Enter your password: ");
      password = in.nextLine();
      Storekeeper.login(username, password);
    } catch (Exception e) {
      System.out.println("Invalid credentials given. Try again...");
    }
  }

  /** Login as a Data Analyst */
  public static void dataAnalystLogin() {
    Scanner in = new Scanner(System.in);
    String username, password;
    try {
      System.out.print("Enter your username.\n" + "To exit press \"enter\": ");
      username = in.nextLine();
      if (username.equals("")) {
        System.out.println("Returning to main menu...");
        return;
      }
      System.out.print("Enter your password: ");
      password = in.nextLine();
      DataAnalyst.login(username, password);
    } catch (Exception e) {
      System.out.println("Invalid credentials given. Try again...");
    }
  }

  /** Login as an Administrator */
  public static void administratorLogin() {
    Scanner in = new Scanner(System.in);
    String username, password;
    try {
      System.out.print("Enter your username.\n" + "To exit press \"enter\": ");
      username = in.nextLine();
      if (username.equals("")) {
        System.out.println("Returning to main menu...");
        return;
      }
      System.out.print("Enter your password: ");
      password = in.nextLine();
      Administrator.login(username, password);
    } catch (Exception e) {
      System.out.println("Invalid credentials given. Try again...");
    }
  }

  /** Saving all the lists to .csv files */
  public static void saveAllListsToCsv() {
    FileHandler.writeOrderListToCSV(Order.getOrders());
    FileHandler.writeProductListToCSV(Storage.getProducts());
    FileHandler.writeProductQuantitiesListToCSV(Storage.getProductQuantities());
    FileHandler.writeUserListToCSV(User.getUsers());
    FileHandler.writeStorageOrderToCSV(StorageOrder.getOrders());
    FileHandler.writeSupplierToCSV(Supplier.getsuppliers());
    FileHandler.writeCustomerListToCSV(RegisteredCustomer.getCustomers());
  }

  /** Loading all the lists from the .csv files */
  public static void loadAllListsFromCsv() {
	if (FileHandler.checkIfCountersFileExists() == false) {
		System.out.println("File Counters.txt not found, creating default file" ); 
		FileHandler.createDefaultCountersFile();
	}
    try {
      User.createUsersFromList(FileHandler.getUsersFromCsv());
    } catch (FileNotFoundException e) {
      // if no user file was found, the user list is initialized with the default administrator user
      new Administrator(0, "admin", "admin", "admin", "admin");
      System.out.println("File Users.csv not found, initializing default user list");
    }
    try {
      Product.createProductsFromList(FileHandler.getProductsFromCsv());
    } catch (FileNotFoundException e) {
      System.out.println("File Products.csv not found, initializing default product list");
    }
    try {
      Storage.createProductQuantitiesFromList(FileHandler.getProductQuantityFromCsv());
    } catch (FileNotFoundException e) {
      System.out.println("File ProductQuantities.csv not found, initializing default product quantity list");
    }
    try {
      RegisteredCustomer.createRegisteredCustomersFromList(
          FileHandler.getRegisteredCustomersFromCsv());
    } catch (FileNotFoundException e) {
      System.out.println("File RegisteredCustomers.csv not found, initializing default registered customer list");
    }
    try {
      Order.createOrdersFromList(FileHandler.getOrdersFromCsv());
    } catch (FileNotFoundException e) {
      System.out.println("File Orders.csv not found, initializing default order list");
    }
    try {
      Supplier.createSuppliersFromList(FileHandler.getSuppliersFromCsv());
    } catch (FileNotFoundException e) {
      System.out.println("File Suppliers.csv not found, initializing default supplier list");
    }
    try {
      StorageOrder.createStorageOrdersFromList(FileHandler.getStorageOrdersFromCsv());
    } catch (FileNotFoundException e) {
      System.out.println("File StorageOrders.csv not found, initializing default storage order list");
    }
  }
}
