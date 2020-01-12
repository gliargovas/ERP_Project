package ERP_Core;

import java.util.ArrayList;

/**
 * This class represents a sale of a product. The class is responsible for converting an order into
 * product sales.
 *
 * @author Rania Pilioura
 * @version 1.0
 */
public class ProductSale {
  /** The id of the product sold. */
  private int productId;
  /** Date and time the sale was made. */
  private String date;
  /** Quantity of sold pieces of certain product. */
  private int quantity;
  /** Selling price per unit. */
  private double price;
  /** ArrayList of all ProductSale objects created. */
  private static ArrayList<ProductSale> allSales = new ArrayList<>();

  /**
   * Constructor for creating a new ProductSale.
   *
   * @param productId the id of the product sold
   * @param quantity the sold quantity of the product
   * @param price the price of the product
   * @param date the date of the sale
   */
  public ProductSale(int productId, int quantity, double price, String date) {
    this.productId = productId;
    this.date = date;
    this.quantity = quantity;
    this.price = price;
  }

  /**
   * Returns the product id of the sold product.
   *
   * @return product id, type integer
   */
  public int getProductId() {
    return productId;
  }

  /**
   * Returns the date of the sale.
   *
   * @return date of sale, type String
   */
  public String getDate() {
    return date;
  }

  /**
   * Returns the sold quantity of the sale.
   *
   * @return sold quantity of product, type integer
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Returns the price per unit of the sold product.
   *
   * @return price of product, type double
   */
  public double getPrice() {
    return price;
  }

  /**
   * Returns the ArrayList that contains all the sales created.
   *
   * @return an ArrayList of all sales, type ProductSale
   */
  public static ArrayList<ProductSale> getAllSales() {
    return allSales;
  }

  /**
   * Returns the Day (number) of the date of the sale.
   *
   * @return the day of the sale, type integer
   */
  public int getDay() {
    String date = getDate();
    String[] cutDate = date.split("-");
    return Integer.parseInt(cutDate[0]);
  }

  /**
   * Returns the month of the date of the sale.
   *
   * @return the month of the sale, type integer
   */
  public int getMonth() {
    String date = getDate();
    String[] cutDate = date.split("-");
    int intDate = Integer.parseInt(cutDate[1]);
    if (intDate == 12) {
      return 0;
    } else {
      return intDate;
    }
  }

  /**
   * Returns the year of the date of the sale.
   *
   * @return the year of the sale, type integer
   */
  public int getYear() {
    String date = getDate();
    String[] cutDate = date.split("-");
    return Integer.parseInt(cutDate[2].substring(0, 4));
  }

  /**
   * Creates ProductSale objects for every sale in a certain order. Adds sales in the ArrayList of
   * all sales.
   *
   * @param order the order that is converted to product sales
   */
  public static void orderToSale(Order order) {
    try {
      String date = order.getOrderDate();
      ArrayList<int[]> basket = order.getBasket();
      int[] prodSale = new int[2];
      for (int i = 0; i < basket.size(); i++) {
        prodSale = basket.get(i);
        Product product = Storage.searchById(prodSale[0]);
        assert product != null;
        ProductSale sale = new ProductSale(prodSale[0], prodSale[1], product.getSalePrice(), date);
        product.getSales().add(sale);
        allSales.add(sale);
      }
    } catch (Exception e) {
      System.out.println("Something went wrong whe trying to convert order to sale.");
    }
  }

  /**
   * toString Method for a product sale.
   *
   * @return Product sale data, type String
   */
  @Override
  public String toString() {
    return ("ProductSale: [Product id: "
        + getProductId()
        + " Sold Quantity: "
        + getQuantity()
        + "Selling Price: "
        + getPrice()
        + " Date: "
        + getDate()
        + "]");
  }
}
