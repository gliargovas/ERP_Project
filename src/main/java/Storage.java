import java.util.ArrayList;

public class Storage {
	protected static ArrayList<Product> products = new ArrayList <Product>();
	protected static double totalVolume;
	protected static ArrayList<Integer[]> productQuantities= new ArrayList <Integer[]> ();
	public static double getTotalVolume() {
		return totalVolume;
	}
	public static void setTotalVolume(double totalVolume) {
		Storage.totalVolume = totalVolume;
	}
	public static ArrayList<Product> getProducts() {
		return products;
	}
	public static ArrayList<Integer[]> getProductQuantities() {
		return productQuantities;
	}
	// Searches product by id
	public static Product searchById(int id) {
		for (Product product : getProducts()) {
			if (product.getProductId() == id) {
				return product;
			}
		}
		return null;
	}
	// Deletes a product from list
	public static void deleteProduct(int id) {
		products.remove(products.indexOf(searchById(id)));
	}
	// Prints all products
	public static void printAllProducts() {
		System.out.println("Product List");
		for (Product p : getProducts()) {
			System.out.println(p);
		}
	}
}
