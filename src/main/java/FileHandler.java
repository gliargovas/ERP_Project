import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    //CSV separator used: ;
    private static final String CSV_SEPARATOR = ";";
    public static void writeProductListToCSV(ArrayList<Product> products)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Products.csv"), "UTF-8"));
            for (Product product : products) {
            	StringBuffer line = new StringBuffer();
            	line.append(product.getProductId());
                line.append(CSV_SEPARATOR);
                line.append(product.getName());
                line.append(CSV_SEPARATOR);
                line.append(product.getCategory());
                line.append(CSV_SEPARATOR);
                line.append(product.getDescription());
                line.append(CSV_SEPARATOR);
                line.append(product.getSalePrice());
                line.append(CSV_SEPARATOR);                 
                bw.write(line.toString());
                bw.newLine();
                }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
    
    public static void writeProductQuantitiesListToCSV(ArrayList<int[]> productQuantities)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ProductQuantities.csv"), "UTF-8"));
            for (int[] productQuantity : productQuantities) {
            	StringBuffer line = new StringBuffer();
            	line.append(productQuantity[0]);
                line.append(CSV_SEPARATOR);
                line.append(productQuantity[1]);
                line.append(CSV_SEPARATOR);               
                bw.write(line.toString());
                bw.newLine();
                }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
    
    public static void writeCustomerListToCsv(ArrayList<RegisteredCustomer> customers) {
        try
        {
        	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Customers.csv"), "UTF-8"));
            for (RegisteredCustomer registeredCustomer : customers) {
            	StringBuffer line = new StringBuffer();
            	line.append(registeredCustomer.getId());
                line.append(CSV_SEPARATOR);
                line.append(registeredCustomer.getCompanyName());
                line.append(CSV_SEPARATOR);
                line.append(registeredCustomer.getAddress());
                line.append(CSV_SEPARATOR);
                line.append(registeredCustomer.getTelephone());
                line.append(CSV_SEPARATOR);
                line.append(registeredCustomer.getPoints());
                bw.write(line.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
    
    public static void writeUserListToCSV(ArrayList<User> users) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Users.csv"), "UTF-8"));
            for (User user : users) {
            	StringBuffer line = new StringBuffer();
            	if (user instanceof Cashier) {
           			line.append("Cashier");
           		} else if (user instanceof Storekeeper) {
           			line.append("Storekeeper");
           		} else if (user instanceof DataAnalyst) {
               		line.append("DataAnalyst");
           		} else if (user instanceof Administrator) {
               		line.append("Admin");
           		}
           		line.append(CSV_SEPARATOR);
           		line.append(user.getIdUser());
           		line.append(CSV_SEPARATOR);
           		line.append(user.getName());
           		line.append(CSV_SEPARATOR);
           		line.append(user.getSurname());
           		line.append(CSV_SEPARATOR);
           		bw.write(line.toString());
               	bw.newLine();
           	}
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
    }
    
    public static void writeOrderListToCSV(ArrayList<Order> orders) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Orders.csv"), "UTF-8"));
            for (Order order : orders) {
            	StringBuffer line = new StringBuffer();
           		line.append(order.getOrderNo());
                line.append(CSV_SEPARATOR);
                line.append(order.getOrderDate());
                line.append(CSV_SEPARATOR);
                line.append(order.getTotalCost());
                line.append(CSV_SEPARATOR);
                // the unregistered customers are saved to the Orders.csv with the id value of 0
                if (order.getCustomer() instanceof RegisteredCustomer)
                	line.append(((RegisteredCustomer)order.getCustomer()).getId());
                else
                	line.append("0");
                line.append(CSV_SEPARATOR);
                for (int[] i : order.getBasket()) {
                 	line.append(CSV_SEPARATOR);
                    line.append(i[0]);
                    line.append(CSV_SEPARATOR);
                    line.append(i[1]);
                }
                bw.write(line.toString());
                bw.newLine();
          	}
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
    }
    
    public static void writeStorageOrderToCSV(ArrayList<StorageOrder> orders) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("StorageOrders.csv"), "UTF-8"));
    		for (StorageOrder order : orders) {
    			StringBuffer line = new StringBuffer();
        		line.append(order.getOrderNumber);
                line.append(CSV_SEPARATOR);
                line.append(order.getOrderDate());
                line.append(CSV_SEPARATOR);
                line.append(order.getTotalCost());
                line.append(CSV_SEPARATOR);
                line.append(order.getSupplier.getId());
                line.append(CSV_SEPARATOR);
                line.append(order.getStorekeeper.getId());
                for (int[] i : order.getSupplies()) {
                	line.append(CSV_SEPARATOR);
                    line.append(i[0]);
                    line.append(CSV_SEPARATOR);
                    line.append(i[1]);
                bw.write(line.toString());
                bw.newLine();
                }	
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
    }
    
    public static void writeSupplierToCSV(ArrayList<Supplier> suppliers) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Suppliers.csv"), "UTF-8"));
    		for (Supplier supplier : suppliers) {
    			StringBuffer line = new StringBuffer();
    			line.append(supplier.getId());
                line.append(CSV_SEPARATOR);
                line.append(supplier.getAddress());
                line.append(CSV_SEPARATOR);
                line.append(supplier.getTel());
                line.append(CSV_SEPARATOR);
                bw.write(line.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
    }
    
    //File Reader
    public static ArrayList<ArrayList<String>> getProductsFromCsv() {
		Scanner scanner=null;
		ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    	try {
			scanner = new Scanner(new File("./Products.csv"));
		} catch (FileNotFoundException e) {}
        scanner.useDelimiter(CSV_SEPARATOR);
        while (scanner.hasNext()) {
        	records.add(getRecordFromLine(scanner.nextLine()));
        }
        scanner.close();
        return records;
    }
    
    public static ArrayList<ArrayList<String>> getProductQuantityFromCsv() {
		Scanner scanner=null;
		ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    	try {
			scanner = new Scanner(new File("./ProductQuantities.csv"));
		} catch (FileNotFoundException e) {}
        scanner.useDelimiter(CSV_SEPARATOR);
        while (scanner.hasNext()) {
        	records.add(getRecordFromLine(scanner.nextLine()));
        }
        scanner.close();
        return records;
    }
    
    public static ArrayList<ArrayList<String>> getUsersFromCsv() {
		Scanner scanner=null;
		ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    	try {
			scanner = new Scanner(new File("./Users.csv"));
		} catch (FileNotFoundException e) {}
        scanner.useDelimiter(CSV_SEPARATOR);
        while (scanner.hasNext()) {
        	records.add(getRecordFromLine(scanner.nextLine()));
        }
        scanner.close();
        return records;
    }
    
    public static ArrayList<ArrayList<String>> getRegisteredCustomersFromCsv() {
		Scanner scanner=null;
		ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    	try {
			scanner = new Scanner(new File("./Customers.csv"));
		} catch (FileNotFoundException e) {}
        scanner.useDelimiter(CSV_SEPARATOR);
        while (scanner.hasNext()) {
        	records.add(getRecordFromLine(scanner.nextLine()));
        }
        scanner.close();
        return records;
    }
    public static ArrayList<ArrayList<String>> getOrdersFromCsv() {
		Scanner scanner=null;
		ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    	try {
			scanner = new Scanner(new File("./Orders.csv"));
		} catch (FileNotFoundException e) {}
        scanner.useDelimiter(CSV_SEPARATOR);
        while (scanner.hasNext()) {
        	records.add(getRecordFromLine(scanner.nextLine()));
        }
        scanner.close();
        return records;
    }
    public static ArrayList<ArrayList<String>> getStorageOrdersFromCsv() {
		Scanner scanner=null;
		ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    	try {
			scanner = new Scanner(new File("./StorageOrders.csv"));
		} catch (FileNotFoundException e) {}
        scanner.useDelimiter(CSV_SEPARATOR);
        while (scanner.hasNext()) {
        	records.add(getRecordFromLine(scanner.nextLine()));
        }
        scanner.close();
        return records;
    }
    public static ArrayList<ArrayList<String>> getSuppliersFromCsv() {
		Scanner scanner=null;
		ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
    	try {
			scanner = new Scanner(new File("./Suppliers.csv"));
		} catch (FileNotFoundException e) {}
        scanner.useDelimiter(CSV_SEPARATOR);
        while (scanner.hasNext()) {
        	records.add(getRecordFromLine(scanner.nextLine()));
        }
        scanner.close();
        return records;
    }
    private static ArrayList<String> getRecordFromLine(String line) {
        ArrayList<String> values = new ArrayList<String>();
        Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(CSV_SEPARATOR);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
        }
        return values;
    }
    
}