import java.util.Date;
import java.util.ArrayList;

public class StorageOrder {
	private static int counter=1;
	private final int storageOrderNumber;
	private final Date storageOrderDate;
	private final double totalCost;
	private final Supplier supplier;
	private final Storekeeper storekeeper;
	private static ArrayList<int[]> supplies =new ArrayList<int[]>();
	private static ArrayList<StorageOrder> orders = new ArrayList<StorageOrder>();
 	
	public StorageOrder(int StorageOrderNumber, Date storageOrderDate, double totalCost, Storekkeper storekeeper, Supplier supplier) {
		storageOrderDate = storageOrderDate;
		totalCost = totalCost;
		this.Storekeeper = storekeeper;
		this.Supplier = supplier;
		storageOrderNumber = counter++;
		orders.add(this);
		
	}
	
	public Date getStorageOrderDate() {
		return storageOrderDate;
	}
	
	public double getTotalCost() {
		return totalCost;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public Storekeeper getStorekeeper() {
		return storekeeper;
	}
	
	
	public int getStorageOrderNumber() {
		return storageOrderNumber;
	}

	public ArrayList<int[]> getSupplies() {
		return supplies;
	}

	public static ArrayList<StorageOrder> getOrders() {
		return orders;
	}

	public static void addSupplies(int Id, int quantity) {
		//Check if productId contains Id and throws an exception if it's necessary
		int[] supplie = {Id, quantity};
		supplies.add(supplie);
	}
}
