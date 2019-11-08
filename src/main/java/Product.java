import java.util.ArrayList;

public class Product {
	// Counts the number of objects created
	private static int idCounter;
	// Instance variables
	private final int productId;
	private final String name;
	private final String category;
	private final String description;
	private double salePrice;
	private int quantity;
	// Product array list
	private static ArrayList <Product> products = new ArrayList <Product>();
	// Product constructors
	// New product order with specific quantity
	public Product(String name, String category, String description, double salePrice, int quantity) {
		this.productId = ++idCounter;
		this.name = name;
		this.category = category;
		this.description = description;
		this.salePrice = salePrice;
		this.quantity = quantity;
		products.add(this);
	}
	// New product with no quantity
	public Product(String name, String category, String description, double salePrice) {
		this.productId = ++idCounter;
		this.name = name;
		this.category = category;
		this.description = description;
		this.salePrice = salePrice;
		this.quantity = 0;
		products.add(this);
	}
	// Getters and setters
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getCategory() {
		return category;
	}
	public static ArrayList<Product> getProducts() {
		return products;
	}
	// toString
	@Override
	public String toString() {
		return String.format("Id: %d | Name: %15s | Category: %10s | Price: %5f\u20ac | Units Available: %d\nDescription: %s", this.getProductId(), this.getName(), this.getCategory(), this.getSalePrice(), this.getQuantity(), this.getDescription());
	}
	// Searches product by id
	public static Product searchProductById(int id) {
		for (Product product : getProducts()) {
			if (product.getProductId() == id) {
				return product;
			}
		}
		return null;
	}
	// Deletes a product from list
	public static void deleteProductFromList(int id) {
		products.remove(products.indexOf(searchProductById(id)));
	}
	// Prints all products
	public static void printAllProducts() {
		System.out.println("Product List");
		for (Product p : getProducts()) {
			System.out.println(p);
		}
	}
	
}
