package ERP_Core;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The class represents the storage of the ERP system. It is responsible for handling the products
 * and their quantities.
 *
 * @version 1.0
 * @author George Liargovas
 */
public class Storage {
  /** ArrayList where the products available in the storage are stored. */
  protected static ArrayList<Product> products = new ArrayList<Product>();
  /** ArrayList where the quantities of the products available in the storage are stored. */
  protected static ArrayList<int[]> productQuantities = new ArrayList<int[]>();

  /**
   * Returns the product list.
   *
   * @return the product list
   */
  public static ArrayList<Product> getProducts() {
    return products;
  }

  /**
   * Returns a list with the combination of product id and available quantity.
   *
   * @return the product - quantities list
   */
  public static ArrayList<int[]> getProductQuantities() {
    return productQuantities;
  }

  /**
   * Returns a Product object from the product list with a specific id.
   *
   * @param id  the id of the product
   * @return the product with the specific id
   */
  public static Product searchById(int id) {
    for (Product product : getProducts()) {
      if (product.getProductId() == id) {
        return product;
      }
    }
    return null;
  }

  /** Prints all the products with their available quantities in storage. */
  public static void printAllProductsWithQuantities() {
    for (Product product : products) {
      System.out.println(product.toStringWithQuantity());
    }
  }

  /**
   * Adds a new product to the product list.
   *
   * @param product the product to add
   */
  public static void addProductToList(Product product) {
    products.add(product);

  }
  
  /**
   * Searches and prints all products that contain a specific string in their name.
   *
   * @param name the name of the product
   */
  public static void searchAndPrintProductsByName(String name) {
    boolean found = false;
    for (Product p : products) {
      if (p.getName().toLowerCase().contains(name.toLowerCase())) {
        found = true;
        System.out.println(p);
      }
    }
    if (found == false) {
      System.out.println("No products with such name");
    }
  }

  /**
   * Removes the product with the given id from the product list.
   *
   * @param id the id of the product
   * @throws NoSuchElementException the product does not exist
   */
  private static void deleteProduct(int id) throws NoSuchElementException {
    Product product = searchById(id);
    if (product == null) {
      throw new NoSuchElementException("Product with such id does not exist");
    }
    products.remove(products.indexOf(product));
  }

  /** Prints all the products in the product list. */
  public static void printAllProducts() {
    System.out.println("Product List");
    for (Product p : getProducts()) {
      System.out.println(p);
    }
  }

  /**
   * Makes a new association between a product and a quantity with the default quantity as 0.
   *
   * @param id the id of the product
   */
  public static void createProductQuantity(int id) {
    int[] productQuantity = {id, 0};
    productQuantities.add(productQuantity);
  }
  
  /**
   * Makes a new association between a product and a quantity, with the given starting quantity.
   *
   * @param id the id of the product
   * @param quantity the quantity of the product
   */
  public static void createProductQuantity(int id, int quantity) {
    int[] productQuantity = {id, quantity};
    productQuantities.add(productQuantity);
  }

  /**
   * Returns the quantity of a specific product in the storage with a given id.
   *
   * @param id the id of the product
   * @return  the quantity of the product with the given id
   * @throws NoSuchElementException the product does not exist
   */
  public static int getProductQuantity(int id) throws NoSuchElementException {
    for (int[] i : productQuantities) {
      if (i[0] == id) {
        return i[1];
      }
    }
    throw new NoSuchElementException("Product with such id does not exist");
  }

  /**
   * Checks if a product with a given quantity already exists and if it does, increases its
   * quantity.
   *
   * @param id the id of the product
   * @param quantity the quantity of the product to increase
   * @throws NoSuchElementException the product does not exist
   */
  public static void addProductQuantity(int id, int quantity) throws NoSuchElementException {
    for (int[] i : productQuantities) {
      if (i[0] == id) {
        i[1] += quantity;
        return;
      }
    }
    throw new NoSuchElementException("Product with such id does not exist");
  }

  /**
   * Checks if a product with a given quantity already exists and if it does, decreases its
   * quantity.
   *
   * @param id the id of the product
   * @param quantity the quantity of the product
   * @throws Exception not enough quantity to decrease
   * @throws NoSuchElementException product does not exist
   */
  public static void removeProductQuantity(int id, int quantity)
      throws Exception, NoSuchElementException {
    for (int[] i : productQuantities) {
      if (i[0] == id) {
        if (i[1] - quantity < 0) {
          throw new Exception("Not enough quantity");
        }
        i[1] -= quantity;
        return;
      }
    }
    throw new NoSuchElementException("Product with such id does not exist");
  }

  /** Contains the user prompts for creating a new product. */
  public static void createNewProductMenu() {
    String name;
    String category;
    String description;
    double price;
    Scanner in = new Scanner(System.in);
    System.out.print("Enter the product's name: ");
    name = in.nextLine();
    System.out.print("Enter the product's catgory: ");
    category = (in.nextLine().toLowerCase());
    System.out.print("Enter product's description: ");
    description = in.nextLine();
    for (; ; ) {
      System.out.print("Enter the product's price: ");
      try {
        price = in.nextDouble();
        in.nextLine();
        if (price <= 0) {
          System.out.println("Price must be larger than 0. Try again...");
          continue;
        }
        break;
      } catch (InputMismatchException e) {
        System.err.println("Invalid input given. Price must be a number");
        in.nextLine();
      }
    }
    Product prod = new Product(name, category, description, price);
    createProductQuantity(prod.getProductId());
    System.out.printf("Product %s created successfully!\n", name);
  }

  /** Contains the user prompts for changing a product's price. */
  public static void changeProductPriceMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    double price;
    String input;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the product you want to change\nTo cancel, press \"enter\": ");
        input = in.nextLine();
        if (input.equals("")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        id = Integer.parseInt(input);
        System.out.print("Enter the new price: ");
        price = in.nextDouble();
        in.nextLine();
        changeProductPrice(id, price);
        break;
      } catch (NumberFormatException e) {
        System.err.println("Invalid input given. Id must be an integer, larger than 0");
      } catch (InputMismatchException e) {
        System.err.println("Invalid input given. Price must be a number");
      } catch (NoSuchElementException e) {
        System.err.println(e.getMessage());
      }
    }
  }

  /**
   * Sets a specific product's price, with the given id to the given price.
   *
   * @param id the id of the product
   * @param price the new price
   * @throws NoSuchElementException product with such id does not exist
   * @throws NumberFormatException invalid price
   */
  private static void changeProductPrice(int id, double price)
      throws NoSuchElementException, NumberFormatException {
    if (price <= 0) {
      throw new NumberFormatException("Price must be larger than 0");
    }
    Product product = searchById(id);
    if (product == null) {
      throw new NoSuchElementException("Product with such id does not exist");
    } else product.setSalePrice(price);
  }

  /** Contains the user prompts for deleting a product from the product list. */
  public static void deleteProductMenu() {
    Scanner in = new Scanner(System.in);
    int id;
    String input;
    for (; ; ) {
      id = 0;
      try {
        System.out.print(
            "Enter the id of the product you want to delete\nTo cancel, press \"enter\": ");
        input = in.nextLine();
        if (input.equals("0")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        id = Integer.parseInt(input);
        Storage.deleteProduct(id);
        break;
      } catch (NumberFormatException e) {
        System.err.println("Invalid input given. Price must be an integer");
      } catch (NoSuchElementException e) {
        System.err.println(e.getMessage());
      } catch (Exception e) {
        System.err.println("An error occured. Returning to previous menu");
      }
    }
  }

  /**
   * Creates productQuantity elements and adds them to the productQuantities list by parsing the
   * contents of an ArrayList.
   *
   * @param idQuantities an ArrayList of strings with the parsed contents of the product id-quantities file. 
   */
  public static void createProductQuantitiesFromList(ArrayList<ArrayList<String>> idQuantities) {
    int id, quantity;
    for (ArrayList<String> idQuantity : idQuantities) {
      id = Integer.parseInt(idQuantity.get(0));
      quantity = Integer.parseInt(idQuantity.get(1));
      Storage.createProductQuantity(id, quantity);
    }
  }

  /**
   * Contains the user prompts for searching and printing a product from the product list by its id.
   */
  public static void searchAndPrintProductByIdMenu() {
    Scanner in = new Scanner(System.in);
    String input;
    int id;
    Product p;
    for (; ; ) {
      try {
        System.out.print(
            "Enter the id of the product you want to print\nTo cancel, press \"enter\": ");
        input = in.nextLine();
        if (input.equals("")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        id = Integer.parseInt(input);
        System.out.print(searchById(id).toStringWithQuantity());
        break;
      } catch (NumberFormatException e) {
        System.out.println("Id must be a non-negative integer. Try again...");
      } catch (NullPointerException e) {
        System.out.println("Customer with such id does not exist. Try again...");
      } catch (Exception e) {
        System.out.println("An error has occured. Try again...");
      }
    }
  }

  /**
   * Checks if a product with a given id, has at least the given amount of units available in the
   * storage. Returns true in case the quantity is enough, otherwise, returns false.
   *
   * @param id the id of the product
   * @param quantity the quantity of the product
   * @return true if the quantity is enough, else false
   * @throws NoSuchElementException product does not exist
   */
  public static boolean checkIfQuantityIsEnough(int id, int quantity)
      throws NoSuchElementException {
    int productQuantity;
    productQuantity = Storage.getProductQuantity(id);
    if (productQuantity >= quantity) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Contains the user prompts for searching and printing a product from the product list by its
   * name.
   */
  public static void searchAndPrintProductsByNameMenu() {
    Scanner in = new Scanner(System.in);
    String input;
    int id;
    Product p;
    for (; ; ) {
      try {
        System.out.print(
            "Enter the name of the product you want to print\nTo cancel, press \"enter\": ");
        input = in.nextLine();
        if (input.equals("")) {
          System.out.println("Process cancelled. Returning to previous menu...");
          return;
        }
        Storage.searchAndPrintProductsByName(input);
        break;
      } catch (NumberFormatException e) {
        System.out.println("Id must be a non-negative integer. Try again...");
      }
    }
  }
}
