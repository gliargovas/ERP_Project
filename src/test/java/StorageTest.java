

public class StorageTest {

	public static void loadProducts() {
		new Product("Mack Book Air", "Computer", "Pear's macbook air computer", 800);
		new Product("Mack Book Pro", "Computer", "Pear's macbook pro computer", 2200);
		new Product("iMack", "Computer", "Pear's iMac computer", 2000);
		new Product("Lled spx", "Computer", "L", 3100);
		new Product("Lled computer", "Computr", "Lled iMac computer", 3000);
		new Product("Lled inspire", "Computer", "Lled computer", 1000);
		new Product("HP Spectrum", "Computer", "Hp High End PC", 1900);
		new Product("Table", "Furniture", "Table", 50);
		new Product("Chair", "Furniture", "Chair", 70);
	}
	

	public static void main(String[] args) {
		loadProducts();
		for (int i = 1; i <= 9; i++) {
		Storage.createProductQuantity(i);
		Storage.addProductQuantity(i, 2*i-1);
		}
		/*System.out.println("1\n\n\n");
		Storage.printAllProductsWithQuantities();
		Storage.addProductQuantity(2, 4);
		System.out.println("2\n\n\n");
		Storage.printAllProductsWithQuantities();
		try {
			Storage.removeProductQuantity(4, 5);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("3\n\n\n");
		Storage.printAllProductsWithQuantities();
		try {
			Storage.removeProductQuantity(2, 100);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("4\n\n\n");
		Storage.printAllProductsWithQuantities();
		try {
			Storage.removeProductQuantity(100, 100);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("5\n\n\n");*/
		Storage.printAllProductsWithQuantities();
		FileHandler.writeProductListToCSV(Storage.getProducts());
		FileHandler.writeProductQuantitiesListToCSV(Storage.getProductQuantities());
		
		Storage.products.removeAll(Storage.products);
		Storage.productQuantities.removeAll(Storage.productQuantities);
		
		Product.createProductsFromList(FileHandler.getProductsFromCsv());
		Storage.createProductQuantitiesFromList(FileHandler.getProductQuantityFromCsv());
		
		System.out.println("6\n\n\n");
		Storage.printAllProductsWithQuantities();
		Storage.searchAndPrintProductsByNameMenu();
		/*Storage.printAllProductsWithQuantities();
		Storage.changeProductPriceMenu();
		Storage.createNewProductMenu();
		Storage.searchAndPrintProductsByNameMenu();
		Storage.searchAndPrintProductByIdMenu();
		*/
	}

}
