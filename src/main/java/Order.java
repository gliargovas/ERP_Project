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
	protected List<int[]> basket=new ArrayList<int[]>();
 	
	public Order(int orderNo, Date orderDate, double totalCost, Customer customer, Cashier cashier) {
		super();
		OrderDate = orderDate;
		TotalCost = totalCost;
		this.customer = customer;
		this.cashier = cashier;
		count++;
		OrderNo=count;
		orders.add(this);
		FileHandler.writeToCSV(this);
	}
	
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
	
	public void addProductToBasket(int Id, int quantity) {
		//Check if productId containts Id
		int[] prod= {Id, quantity};
		basket.add(prod);
	}
}
