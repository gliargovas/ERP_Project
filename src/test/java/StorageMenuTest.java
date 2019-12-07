
public class StorageMenuTest {

	public static void loadObjects() {
		new Product("Mack Book Air", "Computer", "Pear's macbook air computer", 800);
		new Product("Mack Book Pro", "Computer", "Pear's macbook pro computer", 2200);
		new Product("iMack", "Computer", "Pear's iMac computer", 2000);
		new Product("Lled spx", "Computer", "L", 3100);
		new Product("Lled computer", "Computr", "Lled iMac computer", 3000);
		new Product("Lled inspire", "Computer", "Lled computer", 1000);
		new Product("HP Spectrum", "Computer", "Hp High End PC", 1900);
		new Product("Table", "Furniture", "Table", 50);
		new Product("Chair", "Furniture", "Chair", 70);
		for (int i = 1; i <= 9; i++) {
			Storage.createProductQuantity(i);
			Storage.addProductQuantity(i, 2*i-1);
		}
	}


	public static void main(String[] args) {
		loadObjects();
		// All the possible exceptions are getting handled in the methods below,
		// normally if everything is as expected, no exception should occur
		try {
			System.out.println("\n\nCreate new product test\n");
			Storage.createNewProductMenu();
			System.out.println("\n\nDelete product test\n");
			Storage.deleteProductMenu();
			System.out.println("\n\nSearch and Print Product by Id test\n");
			Storage.searchAndPrintProductByIdMenu();
			System.out.println("\n\nSearch and Print Product by Name test\n");
			Storage.searchAndPrintProductsByNameMenu();
			System.out.println("\n\nChange product price test\n");
			Storage.changeProductPriceMenu();
			System.out.println("\n\nPrinting all products\n");
			Storage.printAllProducts();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Exception occured test failed");
			System.exit(1);
		}
	}

}
