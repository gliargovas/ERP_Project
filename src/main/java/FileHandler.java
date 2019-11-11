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
    public static void writeToCSV(Product product)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Products.csv"), true));
            {
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
    
    public static void writeToCSV(RegisteredCustomer customer)
    {
        try
        {
        	BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Customers.csv"), true));
        	{
                StringBuffer line = new StringBuffer();
                line.append(customer.getId());
                line.append(CSV_SEPARATOR);
                line.append(customer.getName());
                line.append(CSV_SEPARATOR);
                line.append(customer.getAddress());
                line.append(CSV_SEPARATOR);
                line.append(customer.getTel());
                line.append(CSV_SEPARATOR);
                line.append(customer.getPoints());
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
    
    public static void writeToCSV(User user) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Users.csv"), true)); {
            	StringBuffer line = new StringBuffer();
            	if (user instanceof Cashier) {
            		line.append("Cashier");
            	} else if (user instanceof Storekeeper) {
            		line.append("Storekeeper");
            	} else if (user instanceof DataAnalyst) {
                		line.append("DataAnalyst");
            	} else if (user instanceof Admin) {
                    		line.append("Admin");
            	}
            	line.append(CSV_SEPARATOR);
            	line.append(user.getId());
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
    
    public static void writeToCSV(Order order) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Orders.csv"), true)); {
            	line.append(order.getOrderNumber());
                line.append(CSV_SEPARATOR);
                line.append(order.getOrderDate());
                line.append(CSV_SEPARATOR);
                line.append(order.getPrice());
                line.append(CSV_SEPARATOR);
                line.append(order.getCustomer.getId());
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
    
    public static void writeToCSV(StorageOrder order) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("StorageOrders.csv"), true)); {
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
    
    public static void writeToCSV(Supplier supplier) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Suppliers.csv"), true)); {
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