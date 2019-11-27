import java.util.Date;
import java.util.ArrayList;

public class Order {
	private final int orderNo;
	private final Date orderDate;
	private final double totalCost;
	private final Customer customer;
	private final Cashier cashier;
	private ArrayList<int[]> basket=new ArrayList<int[]>();
	
	private static int count = 1;
	private  static ArrayList<Order> orders=new ArrayList<Order>();
 	
	//Create a new order
	public Order(Date orderDate, Customer customer, Cashier cashier, ArrayList<int[]> basket) {
		this.orderNo = count++;
		this.orderDate = orderDate;
		this.totalCost = this.calculateBasketCost(basket);
		this.customer = customer;
		this.cashier = cashier;
		this.basket = basket;
		orders.add(this);
	}
	
	/*Constructor for already created products read from .csv file
	There is non need to calculate the total cost again as it has been created*/
	public Order(int orderNo, Date orderDate, double totalCost, Customer customer, Cashier cashier, ArrayList<int[]> basket) {
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.customer = customer;
		this.cashier = cashier;
		this.basket = basket;
		orders.add(this);
	}
	
	//Getters and Setters
	public int getOrderNo() {
		return orderNo;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	
	public double getTotalCost() {
		return totalCost;
	}

	public Customer CustomerId() {
		return customer;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public ArrayList<int[]> getBasket() {
		return basket;
	}
	
	public static ArrayList<Order> getOrders() {
		return orders;
	}
	
	//Basket creation 
	public void addProductToBasket(int Id, int quantity) {
		//Check if productId contains Id and throws an exception if it's necessary
		int[] prod= {Id, quantity};
		basket.add(prod);
	}
	
	//Calculate a product's cost 
	public static double calculateCost (int id, int quantity) {
		Product prod = Storage.searchById(id);
		double price = prod.getSalePrice(); 
		double cost = price * quantity;
		return cost; 
	}
	
	//Calculates the total cost of an order's basket
	public static double calculateBasketCost (ArrayList<int[]> basket) {
		double totalCost = 0;
		Product prod;
		for (int[] product : basket) {
			prod = Storage.searchById(product[0]);
			totalCost += prod.getSalePrice();
		}
	}
	
	//Prints everything on the Basket
	public static void printProduct(int id, int quantity) {
		Product prod = Storage.searchById(id);
		String name = prod.getName();
			double price = calculateCost (id, quantity);
		    System.out.println(id + " - " + name);
		    System.out.println("pieces: " + quantity );
		    System.out.println("price: " + price);   
	}
	
	//Prints a preview of the Order
	public void previewOrder () {
		int cashId = cashier.getIdUser();
		String custName = customer.getCompanyName;
		String custAddress = customer.getAddress;
		double totalCost = 0;
		System.out.println("Preview of Order");
		System.out.println();
		System.out.println("Cashier's Code: " + cashId);
		System.out.println("Customer's data");
		System.out.println("Name: " + custName);
		System.out.println("Address: " + custAddress);
		System.out.println("Products: ");
		for (int prod [] : basket) {
			printProduct(prod [1], prod [2]);
			totalCost = totalCost + calculateCost(prod [1], prod [2]);
		}
		System.out.println("Order's total cost: " + totalCost);		
	}
	
	//Prints final Order
	public void finalOrder () {
		String customerName;
		if (customerId == 0)
			customerName = "Guest";
		else
			customerName = customer.getCompanyName();
		String
		double totalcost = 0;
		System.out.println("Final Order");
		System.out.println();
		System.out.println("Date: " + this.orderDate);
		//extra! Prints OrderNo
		System.out.println("OrderNo: " + this.orderNo);
		System.out.println("Cashier's Code: " + cashId);
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
	
	public static void createOrdersFromList(ArrayList<ArrayList<String>> orders) {
		int customerId, cashierId, 
		ArrayList<int[]> basket;
		Date orderDate;
		String name, address;
		for (ArrayList<String> order: orders) {
			id = Integer.parseInt(customer.get(0));
			name = customer.get(1);
			address = customer.get(2);
			telephone = Integer.parseInt(customer.get(3));
			points = Integer.parseInt(customer.get(4));
			new RegisteredCustomer(name, address, telephone, id, points);
		}
	}
}
