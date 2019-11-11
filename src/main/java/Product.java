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
	// Product array list
	// Product constructors
	// New product
	public Product(String name, String category, String description, double salePrice) {
		this.productId = ++idCounter;
		this.name = name;
		this.category = category;
		this.description = description;
		this.salePrice = salePrice;
		FileHandler.writeToCSV(this);
	}
	//Constructor for loading products read from .csv file
	//The id is already associated the product
	public Product(String name, String category, String description, double salePrice, int id) {
		this.productId = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.salePrice = salePrice;
	}
	// Getters and setters
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
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
	// toString
	@Override
	public String toString() {
		return String.format("Id: %d | Name: %15s | Category: %10s | Price: %5f\u20ac \nDescription: %s", this.getProductId(), this.getName(), this.getCategory(), this.getSalePrice(), this.getDescription());
	}
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
