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
	// New product order with specific quantity
	public Product(String name, String category, String description, double salePrice) {
		this.productId = ++idCounter;
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
	
}
