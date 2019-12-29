package ERP_Core;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AnalyzeOrders {
	public static final String[] MONTHS = {"January", "February", "March", "April", "May",  
			"June", "July", "August", "September", "October", "November", "December"};
	public static ArrayList<int[]> ordersByMonth = new ArrayList<int[]>();
	public static int[] monthlyTotalSales = new int[12];
	public static int[] weeklyTotalSales = new int[52];
	
	// total value by month
	public static double[] getTotalOrderValueByMonth(ArrayList<Order> orders) {
		double[] monthlySales = new double[12];
		int month;
		for (Order order : orders) {
			month = getDateMonth(order.getOrderDate());
			monthlySales[month - 1] += order.getTotalCost();  
		}
		return monthlySales;
	}
	public static void printTotalOrderValueByMonthWithLabel() {
		double [] monthlyValue = getTotalOrderValueByMonth(Order.getOrders());
		System.out.println("Total order value by month:\n");
		for (int i = 0; i < 12; i++) {
			System.out.printf("%s Sales: %.02f \n", MONTHS[i], monthlyValue[i]); 
		}
	}
	
	
	// value by month for specific year
	public static double[] getSpecificYearOrderValueByMonth(ArrayList<Order> orders, int year) {
		double[] monthlySales = new double[12];
		int month;
		String date;
		for (Order order : orders) {
			date = order.getOrderDate();
			if (getDateYear(date) == year) {
				month = getDateMonth(date);
				monthlySales[month - 1] += order.getTotalCost();
			}
		}
		return monthlySales;
	}
	public static void printSpecificYearOrderValueByMonthWithLabel(int year) {
		double [] monthlyValue = getSpecificYearOrderValueByMonth(Order.getOrders(), year);
		System.out.printf("Total order value by month for the year %d:\n\n", year);
		for (int i = 0; i < 12; i++) {
			System.out.printf("%s Sales: %f \n", MONTHS[i], monthlyValue[i]); 
		}
	}
	public static void printTotalOrderValueByMonthWithLabellMenu() {
		Scanner in = new Scanner(System.in);
		int year;
		for (;;) {
			try {
				System.out.print("Enter the year for which you want to plot the monthly sales: " );
				year = in.nextInt();
				break;
			} catch (InputMismatchException e) {
				in.nextLine();
				System.out.println("Please enter a valid number. Try again..." ); 
			}
		}
		printSpecificYearOrderValueByMonthWithLabel(year);
	}
	
	
	
	// value by year interval
	public static double[] getTotalOrderValueByYearInterval(ArrayList<Order> orders, int startYear, int endYear) {
		double[] yearlySales = new double[(endYear - startYear) + 1];
		int orderYear;
		for (Order order : orders) {
			orderYear = getDateYear(order.getOrderDate());
			if (orderYear >= startYear && orderYear <= endYear) {
				yearlySales[orderYear - startYear] += order.getTotalCost();
			}
		}
		return yearlySales;
	}
	public static void printTotalOrderValueByYearIntervalWithLabel(int startYear, int endYear) {
		double [] yearlyValue = getTotalOrderValueByYearInterval(Order.getOrders(), startYear, endYear);
		int index = 0;
		System.out.printf("Total order value from the year %d to %d:\n\n", startYear, endYear);
		for (int i = startYear; i <= endYear; i++) {
			System.out.printf("%d Sales: %.02f \n", i, yearlyValue[index]);
			index++;
		}
	}
	public static void printTotalOrderValueByYearIntervalWithLabelMenu() {
		Scanner in = new Scanner(System.in);
		int startYear;
		int endYear;
		for (;;) {
			try {
				System.out.print("Enter the starting year for which you want to plot the monthly sales: " );
				startYear = in.nextInt();
				System.out.print("Enter the year upto which you want to plot the monthly sales: " );
				endYear = in.nextInt();
				break;
			} catch (InputMismatchException e) {
				in.nextLine();
				System.out.println("Please enter a valid number. Try again..." ); 
			}
		}
		printTotalOrderValueByYearIntervalWithLabel(startYear, endYear);
	}
	
	
	// value by year for all years
	public static int findMinYearInOrders() {
		int min = Integer.MAX_VALUE;
		int year;
		for (Order order : Order.getOrders()) {
			year = getDateYear(order.getOrderDate());
			if (year < min) {
				min = year; 
			}
		}
		return min;
	}
	public static int findMaxYearInOrders() {
		int max = Integer.MIN_VALUE;
		int year;
		for (Order order : Order.getOrders()) {
			year = getDateYear(order.getOrderDate());
			if (year > max) {
				max = year; 
			}
		}
		return max;
	}
	
	public static void printTotalOrderValueByYearWithLabel() {
		int startYear = findMinYearInOrders();
		int endYear = findMaxYearInOrders();
		printTotalOrderValueByYearIntervalWithLabel(startYear, endYear);
	}
	
	
	public static int getDateDay(String date) {
		String[] cutDate = date.split("-");
		return Integer.parseInt(cutDate[0]) ;
	}
	
	public static int getDateMonth(String date) {
		String[] cutDate = date.split("-");
		return Integer.parseInt(cutDate[1]) ;
	}
	
	public static int getDateYear(String date) { 
		String[] cutDate = date.split("-");
		return Integer.parseInt(cutDate[2].substring(0,4)) ;
	}
}
