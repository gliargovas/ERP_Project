import java.sql.Date;

public class Order {
	private int OrderNo;
	private Date OrderDate;
	private double TotalCost;
	private Customer customer;
	private Cashier cashier;
	public Order(int orderNo, Date orderDate, double totalCost, Customer customer, Cashier cashier) {
		super();
		OrderNo = orderNo;
		OrderDate = orderDate;
		TotalCost = totalCost;
		this.customer = customer;
		this.cashier = cashier;
	}
	public int getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(int orderNo) {
		OrderNo = orderNo;
	}
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	public double getTotalCost() {
		return TotalCost;
	}
	public void setTotalCost(double totalCost) {
		TotalCost = totalCost;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Cashier getCashier() {
		return cashier;
	}
	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}
}
