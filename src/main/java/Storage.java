import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {
	protected static ArrayList<Product> products = new ArrayList <Product>();
	protected static ArrayList<Integer[]> productQuantities = new ArrayList <Integer[]> ();
	private static ArrayList<Product> getProducts() {
		return products;
	}
	private static ArrayList<Integer[]> getProductQuantities() {
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
	public static void printAllProductsWithQuantities() {
		for (Product product : products) {
			System.out.println(product.toStringWithQuantity());
		}
	}
	public static void addProductToList(Product product) {
		products.add(product);
	}
	// Searches and prints all products that contain a specific string in their name 
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
	// Deletes a product from list
	private static void deleteProduct(int id) throws NoSuchElementException {
		Product product = searchById(id);
		if (product == null) {
			throw new NoSuchElementException("Product with such id does not exist");
		}
		products.remove(products.indexOf(product));
	}
	// Prints all products
	public static void printAllProducts() {
		System.out.println("Product List");
		for (Product p : getProducts()) {
			System.out.println(p);
		}
	}
	
	//makes a new association between a product and a quantity
	public static void createProductQuantity(int id) {
		Integer[] productQuantity = {id, 0};
		productQuantities.add(productQuantity);
	}
	public static void createProductQuantity(int id, int quantity) {
		Integer[] productQuantity = {id, quantity};
		productQuantities.add(productQuantity);
	}
	//returns the quantity of a specific product id
	public static int getProductQuantity(int id) {
		for (Integer[] i : productQuantities) {
			if (i[0] == id) {
				return i[1];
			}
		}
		throw new NoSuchElementException("Product with such id does not exist");
	}
	
	//checks if a product already exists and if it does, increases its quantity
	public static void addProductQuantity(int id, int quantity) throws NoSuchElementException{
		for (Integer[] i : productQuantities) {
			if (i[0] == id) {
				i[1] += quantity;
				return;
			}
		}
		throw new NoSuchElementException("Product with such id does not exist");
	}
	//checks if a product already exists and if it does, decreases its quantity
	public static void removeProductQuantity(int id, int quantity) throws Exception, NoSuchElementException {
		for (Integer[] i : productQuantities) {
			if (i[0] == id) {
				if (i[1] - quantity < 0) {
					throw new Exception("Not enough quantity");
				}
				i[1] -= quantity;
				return;
			}
			throw new NoSuchElementException("Product with such id does not exist");
		}
	}
	// contains the product creation menu
	public static void createNewProductMenu() {
		String name, category, description;
		double price;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the product's name: ");
		name = in.nextLine();
		System.out.print("Enter the product's catgory: ");
		category = (in.nextLine().toLowerCase());
		System.out.print("Enter product's description: ");
		description = in.nextLine();
		for (;;) {	
			System.out.print("Enter the product's price: ");
			try {
				price = in.nextDouble();
				in.nextLine();
				if (price <= 0) {
					System.out.println("Price must be larger than 0. Try again...");
					continue;
				}
				break;
			} catch(InputMismatchException e) {
				System.err.println("Invalid input given. Price must be a number");
				in.nextLine();
			}
		}
		new Product(name, category, description, price);
		System.out.printf("Product %s created successfully!\n", name);
	}
	// contains the product price change menu
	public static void changeProductPriceMenu() {
		Scanner in = new Scanner(System.in);
		int id;
		double price;
		String input;
		for(;;) {
			id = 0;
			try {
				System.out.print("Enter the id of the product you want to change\nTo cancel, press \"enter\": ");
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
			} catch (NumberFormatException e){
				System.err.println("Invalid input given. Id must be an integer, larger than 0");
			} catch (InputMismatchException e) {
				System.err.println("Invalid input given. Price must be a number");
			} catch (NoSuchElementException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	// changes a specific product's price 
	private static void changeProductPrice(int id, double price) throws NoSuchElementException, NumberFormatException{
		if (price <= 0) {
			throw new NumberFormatException("Price must be larger than 0");
		}
		Product product = searchById(id);
		if (product == null) {
			throw new NoSuchElementException("Product with such id does not exist");
		} else product.setSalePrice(price);
	}
	
	public static void deleteProductMenu() {
		Scanner in = new Scanner(System.in);
		int id;
		String input;
		for(;;) {
			id = 0;
			try {
				System.out.print("Enter the id of the product you want to delete\nTo cancel, press \"enter\": ");
				input = in.nextLine();
				if (input.equals("0")) {
					System.out.println("Process cancelled. Returning to previous menu...");
					return;
				}
				id = Integer.parseInt(input);
				Storage.deleteProduct(id);
				break;
			} catch (NumberFormatException e){
				System.err.println("Invalid input given. Price must be an integer");
			} catch (NoSuchElementException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println("An error occured. Returning to previous menu");
			}
		}
	}
	//Adds productQuantity elements in productQuantities list that have been read from a .csv file
	public static void createProductQuantitiesFromList(ArrayList<ArrayList<String>> idQuantities) {
		int id, quantity;
		for (ArrayList<String> idQuantity: idQuantities) {
			id = Integer.parseInt(idQuantity.get(0));
			quantity = Integer.parseInt(idQuantity.get(1));
			Storage.createProductQuantity(id, quantity);
		}
	}
	
}
