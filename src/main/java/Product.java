import java.util.ArrayList;
/**
 * This is the class with product constructors , setters , getters and toString methods
 * @version 1.0
 * @author TODO
 *  
 */
public class Product {
	/** idCounter counts the number of objects created */
	private static int idCounter;
	/** productId product's id */
	private final int productId;
	/** name product's name */
	private final String name;
	/** category product's category */
	private final String category;
	/** description product's description */
	private final String description;
	/** salePrice product's price */
	private double salePrice;
	/**
	 * The constructor for creating a new Product object
	 * @param name product's name
	 * @param category product's category
	 * @param description product's description
	 * @param salePrice product's price
	 */
	
	public Product(String name, String category, String description, double salePrice) {
		this.productId = ++idCounter;
		this.name = name;
		this.category = category;
		this.description = description;
		this.salePrice = salePrice;
		Storage.addProductToList(this);
		Storage.createProductQuantity(this.getProductId());
	}
	/**
	 * The constructor for loading a previously created Product object to the ERP system.
	 * @param name name product's name
	 * @param category  category product's category
	 * @param description product's description
	 * @param salePrice product's price
	 * @param id the id given to the Product the first time he was created
	 */
	public Product(String name, String category, String description, double salePrice, int id) {
		this.productId = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.salePrice = salePrice;
		Storage.addProductToList(this);
	}
	/**
	 * salePrice getter 
	 * @return salePrice product's price
	 */
	public double getSalePrice() {
		return salePrice;
	}
	/**
	 * salePrice setter
	 * @param salePrice product's price
	 */
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * ProductId getter
	 * @return ProductId product id
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * Name getter
	 * @return getName product's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Description getter
	 * @return description product's description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Product's category getter
	 * @return category product's category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * idCounter getter
	 * @return idCounter the number of objects created
	 */
	public static int getIdCounter() {
		return idCounter;
	}
	/**
	 * Returns a String with product's id, name, category, price and description 
	 */
	@Override
	public String toString() {
		return String.format("Id: %d | Name: %15s | Category: %10s | Price: %5f\u20ac \nDescription: %s", this.getProductId(), this.getName(), this.getCategory(), this.getSalePrice(), this.getDescription());
	}
	/**
	 * Returns a string with product's id, name, category and price 
	 * @return String product info without description 
	 */
	public String toShortString() {
		return String.format("Id: %3d | Name: %15s | Category: %10s | Price: %5f\u20ac", this.getProductId(), this.getName(), this.getCategory(), this.getSalePrice());
	}
	/**
	 * Returns a string with product's id, name, category, price and quantity.
	 * @return String product info with quantity
	 */
	public String toStringWithQuantity() {
		return String.format("Id: %3d | Name: %15s | Category: %10s | Price: %5f\u20ac | Units Available: %3d", this.getProductId(), this.getName(), this.getCategory(), this.getSalePrice(), Storage.getProductQuantity(this.getProductId()));
	}
	/**
	 * Create Product from a list
	 * @param products
	 */
	public static void createProductsFromList(ArrayList<ArrayList<String>> products) {
		int id;
		String name, category, description;
		double salePrice;
		for (ArrayList<String> product: products) {
			id = Integer.parseInt(product.get(0));
			name = product.get(1);
			category = product.get(2);
			description = product.get(3);
			salePrice = Double.parseDouble(product.get(4));
			new Product(name, category, description, salePrice, id);
		}
	}
	
}
