import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private final int OrderNo;
	private final int count;
	private final Date OrderDate;
	private final double TotalCost;
	private final Customer customer;
	private final Cashier cashier;
	protected ArrayList<Order> orders=new ArrayList<Order>();
	protected static List<int[]> basket=new ArrayList<int[]>();
 	
	//Create a new order
	public Order(int orderNo, Date orderDate, double totalCost, Customer customer, Cashier cashier) {
		OrderDate = orderDate;
		TotalCost = totalCost;
		this.customer = customer;
		this.cashier = cashier;
		count++;
		OrderNo=count;
		orders.add(this);
		FileHandler.writeToCSV(this);
	}
	
	//Getters and Setters
	public int getOrderNo() {
		return OrderNo;
	}
	
	public Date getOrderDate() {
		return OrderDate;
	}
	
	public double getTotalCost() {
		return TotalCost;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Cashier getCashier() {
		return cashier;
	}
	
	//Basket creation 
	public static void addProductToBasket(int Id, int quantity) {
		//Check if productId contains Id and throws an exception if it's necessary
		int[] prod= {Id, quantity};
		basket.add(prod);
	}
	
	//Calculate a product's cost 
	public static double calculateCost (int Id, int quantity) {
		//Check if productId contains Id and throws an exception if it's necessary
		Product prod; 
		double price = prod.getSalePrice(); 
		double cost = price * quantity;
		return cost; 
	}
	
	//Prints everything on the Basket
	public static void printProduct(int Id, int quantity) {
		//Check if productId contains Id and throws an exception if it's necessary
		Product prod;
		String name = prod.getName();
			double price = calculateCost (Id, quantity);
		    System.out.println(Id + "-" + name);
		    System.out.println("pieces:" + quantity );
		    System.out.println("price:" + price);   
	}
	
	//Prints a preview of the Order
	public void previewOrder () {
		int cashid = this.cashier.getIdUser();
		String custname = this.customer.getCompanyName;
		String custaddress = this.customer.getAddress;
		double totalcost = 0;
		System.out.println("Preview of Order");
		System.out.println();
		System.out.println("Cashier's Code: " + cashid);
		System.out.println("Customer's data");
		System.out.println("Name: " + custname);
		System.out.println("Address: " + custaddress);
		System.out.println("Products: ");
		for (int prod [] : basket) {
			printProduct(prod [1], prod [2]);
			totalcost = totalcost + calculateCost(prod [1], prod [2]);
		}
		System.out.println("Order's total cost: " + totalcost);		
	}
	
	//Prints final Order
	public void finalOrder () {
		Cashier cash;
		int cashid = cash.getIdUser();
		Customer cust;
		String custname = cust.getCompanyName;
		String custaddress = cust.getAddress;
		double totalcost = 0;
		System.out.println("Final Order");
		System.out.println();
		System.out.println("Date: " + this.OrderDate);
		//extra! Prints OrderNo
		System.out.println("OrderNo: " + this.OrderNo);
		System.out.println("Cashier's Code: " + cashid);
		System.out.println("Customer's data");
		System.out.println("Name: " + custname);
		System.out.println("Address: " + custaddress);
		System.out.println("Products: ");
		for (int prod [] : basket) {
			printProduct(prod [1], prod [2]);
			totalcost = totalcost + calculateCost(prod [1], prod [2]);
		}
		System.out.println("Order's total cost: " + totalcost);	
		
	}
}
