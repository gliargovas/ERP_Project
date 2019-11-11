import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FileHandler {

    //CSV separator used: ;
    private static final String CSV_SEPARATOR = ";";
    private static void writeToCSV(Product product)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Products.csv"), "UTF-8"));
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
    
    private static void writeToCSV(RegisteredCustomer customer)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("RegisteredCustomers.csv"), "UTF-8"));
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
    
    private static void writeToCSV(User user) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Users.csv"), "UTF-8")); {
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
    
    private static void writeToCSV(Order order) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Orders.csv"), "UTF-8")); {
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
    
    private static void writeToCSV(StorageOrder order) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Orders.csv"), "UTF-8")); {
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
    
    private static void writeToCSV(Supplier supplier) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Orders.csv"), "UTF-8")); {
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
    public static void ReadAllProducstFromCsv() {
    	public static void main(String[] args) throws FileNotFoundException 
    	{
            Scanner scanner = new Scanner(new File("SampleCSVFile.csv"));
             
            //Set the delimiter used in file
            scanner.useDelimiter(";");
            String line = new String[];
            ArrayList<String[]> data = new ArrayList<String[]>()
            while (scanner.hasNext()) 
            {
                System.out.print(scanner.next() + "|");
            }
            scanner.close();
        }
    }
    
    
    
    
    
}