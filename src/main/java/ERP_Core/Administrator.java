package ERP_Core;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents the administrator of the ERP system. The administrator is responsible for
 * creating, editing and deleting users. There is also the ability to view additional information
 * about the state of the ERP system. The system contains one administrator by default and it is not
 * possible to create an other one.
 *
 * @version 1.0
 * @author George Liargovas
 */
public class Administrator extends User {
  /**
   * The constructor for creating a new Administrator object.
   *
   * @param name the name of the Administrator
   * @param surname the surname of the Administrator
   * @param username the username of the Administrator
   * @param password the password of the Administrator
   */
  public Administrator(String name, String surname, String username, String password) {
    super(name, surname, username, password);
  }

  /**
   * The constructor for loading a previously created administrator object to the ERP system.
   *
   * @param idUser the id given to the Administrator the first time he was created.
   * @param name the name of the Administrator
   * @param surname the surname of the Administrator
   * @param username the username of the Administrator
   * @param password the password of the Administrator
   */
  public Administrator(int idUser, String name, String surname, String username, String password) {
    super(idUser, name, surname, username, password);
  }

  /**
   * Checks if the given user credentials exist in the list and if they do, the user is logged in
   * and the specific user menu is called.
   *
   * @param username the user's username as a String
   * @param password the user's password as a String
   * @throws Exception in case the credentials do not match for any administrator in the list
   */
  public static void login(String username, String password) throws Exception {
    Administrator admin;
    for (User user : User.getUsers()) {
      if (user instanceof Administrator) {
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
          admin = (Administrator) user;
          System.out.printf("Welcome %s %s!\n", admin.getName(), admin.getSurname());
          admin.getMenu();
          return;
        }
      }
    }
    throw new Exception("Invalid Credentials");
  }

  /** Prints the administrator's menu */
  public void printMenu() {
    System.out.print(
        "\n\n--- Administrator Menu ---\n\n"
            + "1)  Create a new User\n"
            + "2)  Change an existing's users password\n"
            + "3)  Delete an existing User\n"
            + "4)  View all users\n"
            + "5)  View all products\n"
            + "6)  View all customers\n"
            + "7)  View all suppliers\n"
            + "8)  View all orders\n"
            + "9)  View specific orders\n"
            + "10) View all storage orders\n"
            + "11) View specific storage orders\n"
            + "12) Save all changes\n"
            + "13) Return to previous menu\n"
            + "Option: ");
  }

  /**
   * The administrator's menu. Several options are available. When the return option is selected,
   * the user is logged out and returned to the main menu
   */
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
            this.createNewUserMenu();
            break;
          case 2:
            this.changeUserPasswordMenu();
            break;
          case 3:
            this.deleteUserMenu();
            break;
          case 4:
            Administrator.viewAllUsers();
            break;
          case 5:
            Storage.printAllProductsWithQuantities();
            break;
          case 6:
            RegisteredCustomer.printAllCustomers();
            break;
          case 7:
            Supplier.printAllSuppliers();
            break;
          case 8:
            Order.printOrderHistory();
            break;
          case 9:
            Order.printOrderHistoryMenu();
            break;
          case 10:
            StorageOrder.printStorageOrderHistory();
            break;
          case 11:
            StorageOrder.printOrderHistoryMenu();
            break;
          case 12:
        	Main.saveAllListsToCsv();
        	System.out.println("Changes saved!" ); 
          case 13:
            System.out.println("Logging off...Returning to previous menu");
            return;
          default:
            System.out.println("Please enter a valid option. Try again...");
            break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Your option must be an integer number. Try again...");
        in.nextLine();
      }
    }
  }

  /** Displays all the users registered in the system in a specific format. */
  private static void viewAllUsers() {
    System.out.println("\n--- Users ---");
    for (User user : User.getUsers()) {
      System.out.println(user);
    }
  }
  
  /** Contains the user prompts for deleting a user from the system. */
  private void deleteUserMenu() {
    Scanner in = new Scanner(System.in);
    String ans = null;
    int id = 0;
    for (; ; ) {
      try {
        System.out.print(
            "Enter the id of the user you want to delete. To cancel press \"enter\": ");
        ans = in.nextLine();
        if (ans.equals("")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        id = Integer.parseInt(ans);
        if (id == this.getIdUser()) {
          System.out.println("You cannot delete yourself! Try again...");
        }
        System.out.print(
            "Deleting a user is permanent. Are you sure you want to delete a user?\n"
                + "Type \"Yes\" if you understand the risk and wish to proceed. Typing anything else will cancel the process: ");
        ans = in.nextLine();
        if (ans.toLowerCase().equals(("Yes").toLowerCase())) {
          this.deleteUser(id);
          System.out.println("User deleted successfully.");
        } else {
          System.out.println("Process cancelled. Returning to previous menu...");
        }

        return;
      } catch (NumberFormatException e) {
        System.out.println("User id must be an integer number. Try again...");
      } catch (NoSuchElementException e) {
        System.out.println("User with such id does not exist. Try again...");
      }
    }
  }
  
  /**
   * Deletes a user with a given id from the system.
   *
   * @param id the user's to delete id
   * @throws NoSuchElementException in case the id does not match to an existing user
   */
  private void deleteUser(int id) throws NoSuchElementException {
    User toDelete = User.searchUserById(id);
    User.getUsers().remove(toDelete);
  }

  /** Contains the user prompts for changing a user's password */
  private void changeUserPasswordMenu() {
    Scanner in = new Scanner(System.in);
    String ans = null;
    int id = 0;
    for (; ; ) {
      try {
        System.out.print(
            "Enter the id of the user you want to change. To cancel press \"enter\": ");
        ans = in.nextLine();
        if (ans.equals("")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        id = Integer.parseInt(ans);
        for (; ; ) {
          System.out.print(
              "Enter the user's password." + "The password must be at least 8 characters long: ");
          ans = in.nextLine();
          if (ans.length() < 8) {
            System.out.println("The password did not match the requirements. Try again...");
          } else {
            break;
          }
        }
        changeUserPassword(id, ans);
        System.out.println("Password changed successfully.");
        return;
      } catch (NumberFormatException e) {
        System.out.println("User id must be an integer number. Try again...");
      } catch (NoSuchElementException e) {
        System.out.println("User with such id does not exist. Try again...");
      }
    }
  }

  public void changeUserPassword(int id, String newPassword) throws NoSuchElementException {
    User user = User.searchUserById(id);
    System.out.println(user);
    user.setPassword(newPassword);
  }

  /** Creates a new user according to user input */
  private void createNewUserMenu() {
    Scanner in = new Scanner(System.in);
    String username;
    String password;
    String name;
    String surname;
    int ans;
    for (; ; ) {
      System.out.print("Enter the user's name: ");
      name = in.nextLine();
      System.out.print("Enter the user's surname:");
      surname = in.nextLine();
      for (; ; ) {
        System.out.print("Enter the user's username:");
        username = in.nextLine();
        if (usernameAlreadyExists(username) == false) {
          break;
        } else {
          System.out.println(
              "User with such username already exists. Try again with an other username...");
        }
      }
      for (; ; ) {
        System.out.print(
            "Enter the user's password." + "The password must be at least 8 characters long: ");
        password = in.nextLine();
        if (password.length() < 8) {
          System.out.println("The password did not match the requirements. Try again...");
        } else {
          break;
        }
      }
      for (; ; ) {
        try {
          System.out.print(
              "Choose the type of the new user: \n"
                  + "1) Cashier\n"
                  + "2) Storekeeper\n"
                  + "3) Data Analyst\n"
                  + "4) Administrator\n"
                  + "5) Cancel\n"
                  + "Option: ");
          ans = in.nextInt();
          switch (ans) {
            case 1:
              new Cashier(name, surname, username, password);
              System.out.println("Cashier created successfully");
              return;
            case 2:
              new Storekeeper(name, surname, username, password);
              System.out.println("Storekeeper created successfully");
              return;
            case 3:
              new DataAnalyst(name, surname, username, password);
              System.out.println("Data analyst created successfully");
              return;
            case 4:
              new Administrator(name, surname, username, password);
              System.out.println("Data analyst created successfully");
              return;
            case 5:
              System.out.println("Process cancelled. Returning to previous menu...");
              return;
            default:
              System.out.println("Please enter a valid option. Try again...");
          }
        } catch (InputMismatchException e) {
          in.nextLine();
          System.out.println("Please enter an integer number. Try again...");
        }
      }
    }
  }

  /**
   * Checks if the given username matches to a user already in the list.
   *
   * @param username the username to check
   * @return true if the username is matched to an existing user, otherwise false
   */
  public static boolean usernameAlreadyExists(String username) {
    for (User user : User.getUsers()) {
      if (user.getUsername().equals(username)) {
        return true;
      }
    }
    return false;
  }
}
