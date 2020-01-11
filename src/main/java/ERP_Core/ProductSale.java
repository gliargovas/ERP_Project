package ERP_Core;
import java.util.ArrayList;

/**
 * This class represents a sale of a product.
 * The class is responsible for converting an order into product sales.
 *
 * @author Rania Pilioura
 * @version 1.0
 */
public class ProductSale {
    /**
     * The id of the product sold
     */
    private int productId;
    /**
     * Date and time the sale was made
     */
    private String date;
    /**
     * Quantity of sold pieces of certain product
     */
    private int quantity;
    /**
     * Selling price per unit
     */
    private double price;
    /**
     * ArrayList of all ProductSale objects created
     */
    private static ArrayList<ProductSale> allSales = new ArrayList<>();

    /**
     * Constructor for creating a new ProductSale
     *
     * @param productId the id of the product sold
     * @param quantity  the sold quantity of the product
     * @param price     the price of the product
     * @param date      the date of the sale
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
     * Returns the sold quantity of the sale
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
        int day = Integer.parseInt(date.substring(0, 2));
        return day;
    }

    /**
     * Returns the month of the date of the sale.
     *
     * @return the month of the sale, type integer
     */
    public int getMonth() {
        String date = getDate();
        String monthstr = date.substring(3, 6);
        switch (monthstr) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 0;
            default:
                return -1;
        }
    }

    /**
     * Returns the year of the date of the sale.
     *
     * @return the year of the sale, type integer
     */
    public int getYear() {
        String date = getDate();
        int year = Integer.parseInt(date.substring(7, 11));
        return year;
    }

    /**
     * Creates ProductSale objects for every sale in a certain order.
     * Adds sales in the ArrayList of all sales
     *
     * @param order the order that is converted to product sales
     */
    //Μήπως πρέπει να κάνει έχει try-catch και στο catch να κάνει
    //throw exception που θα το διαχειρίζεται η μέθοδος confirmOrder;
    //Αν και δεν μπορώ να σκεφτώ κάποιο exception που να πετάγεται εδώ,
    //από τη στιγμή που η παραγγελία έχει γίνει σωστά.
    //Όταν, δηλαδή, υπάρχει το προιόν που έχει διαλέξει ο χρήστης, άρα δεν
    //θα είναι ποτέ null κλπ.
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
     * toString Method for a product sale
     *
     * @return Product sale data, type String
     */
    @Override
    public String toString() {
        return ("ProductSale: [Product id: " + getProductId() + " Sold Quantity: "
                + getQuantity() + "Selling Price: " + getPrice() + " Date: " + getDate() + "]");
    }
}