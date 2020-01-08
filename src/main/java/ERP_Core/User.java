package ERP_Core;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/** An abstract class that represents a user of the ERP. Each user
 *  type, that extends this class is able to make a set of actions by
 *  inheriting the getMenu() method. All the different users are stored 
 *  in an arrayList.
 *  
 *  @author Dimitris Tsaousis
 *  @author George Liargovas
 *  @version 1.0
 */

public abstract class User {

  /** The user's unique id assigned at first creation of the user. */
  private final int idUser;
  /** The user's name. */
  private final String name;
  /** The user's surname. */
  private final String surname;
  /** The user's unique username. */
  private final String username;
  /** The user's password. */
  private String password;
  /** The arrayList for storing the created users. */
  private static ArrayList<User> users = new ArrayList<User>();
  /** 
   *  The counter that counts the unique users that have been created and is
   *  used for assigning their ids.
   */
  private static int userIdCounter = FileHandler.getUserCounterFromFile();

  /**
   * Constructor for new users.
   * 
   * @param name the name of the user
   * @param surname the surname of the user
   * @param username the user's username
   * @param password the user's password
   */
  public User(String name, String surname, String username, String password) {
    this.idUser = ++userIdCounter;
    this.name = name;
    this.surname = surname;
    this.username = username;
    this.password = password;
    users.add(this);
  }

  /**
   * Constructor for user from .csv file.
   * 
   * @param idUser the id of an existing user
   * @param name the name of the user
   * @param surname the surname of the user
   * @param username the user's username
   * @param password the user's password
   */
  public User(int idUser, String name, String surname, String username, String password) {
    this.idUser = idUser;
    this.name = name;
    this.surname = surname;
    this.username = username;
    this.password = password;
    users.add(this);
  }

  /**
   * Returns the id counter of the Users.
   *
   * @return the user counter
   */
  public static int getUserIdCounter() {
    return userIdCounter;
  }

  /**
   * Returns the id of the User.
   *
   * @return the id of the user
   */
  public int getIdUser() {
    return idUser;
  }

  /**
   * Returns the name of the User.
   *
   * @return name the name of the user
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the username of the User.
   *
   * @return the surname of the user
   */
  public String getSurname() {
    return surname;
  }

  /**
   * Returns the username of the User.
   *
   * @return the username of the user
   */
  public String getUsername() {
    return username;
  }

  /**
   * Returns the password of the User.
   *
   * @return the password of the user
   */
  public String getPassword() {
    return password;
  }

  /**
   * Changes the value of the user's password.
   *
   * @param password the new password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Returns the user list.
   *
   * @return Users list
   */
  public static ArrayList<User> getUsers() {
    return users;
  }

  /** toString method for printing the user in a formatted way. */
  @Override
  public String toString() {
    return String.format(
        "ID: %d | Name: %s | Surname: %s | Username: %s",
        getIdUser(), getName(), getSurname(), getUsername());
  }

  /** The abstract method that returns the main menu of each user according to their type. */
  public abstract void getMenu();

  /**
   * Creates the users from the list according to the type.
   *
   * @param users The ArrayList of strings that contains the parsed contents of the user file
   */
  public static void createUsersFromList(ArrayList<ArrayList<String>> users) {
    int id;
    String name;
    String surname;
    String username;
    String password;
    String type;
    double salePrice;
    for (ArrayList<String> user : users) {
      type = user.get(0);
      id = Integer.parseInt(user.get(1));
      name = user.get(2);
      surname = user.get(3);
      username = user.get(4);
      password = user.get(5);
      if (type.equals("Cashier")) {
        new Cashier(id, name, surname, username, password);
      } else if (type.equals("Storekeeper")) {
        new Storekeeper(id, name, surname, username, password);
      } else if (type.equals("DataAnalyst")) {
        new DataAnalyst(id, name, surname, username, password);
      } else if (type.equals("Admin")) {
        new Administrator(id, name, surname, username, password);
      }
    }
  }

  /**
   * Searches the user according to the id that is given.
   *
   * @param id The id of the user
   * @return The user with the given id
   * @throws NoSuchElementException The user does not exist
   */
  public static User searchUserById(int id) throws NoSuchElementException {
    for (User user : users) {
      if (user.getIdUser() == id) {
        return user;
      }
    }
    throw new NoSuchElementException("User with such id does not exist");
  }
}
