package ERP_Core;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Plots.*;

public class DataAnalyst extends User {
	public DataAnalyst(String name, String surname, String username, String password) {
		super(name, surname, username, password);
	}
	
	public DataAnalyst(int idUser, String name, String surname, String username, String password) {
		super(idUser, name, surname, username, password);
	}
	
	public static void login(String username, String password) throws Exception {
		DataAnalyst da;
		for (User user : User.getUsers()) {
			if (user instanceof DataAnalyst) {
				if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					da = (DataAnalyst)user;
					System.out.printf("Welcome %s %s!\n", da.getName(), da.getSurname());
					da.getMenu();
					return;
				}
			}
		}
		throw new Exception("Invalid Credentials");
	}
	
	
	public void printDescriptivesMenu() {
		System.out.print("\n\n***Analysis Menu***\n\n"
		    + "1) Display sales by year\n"
		    + "2) Display sales by year for specific year interval\n"
		    + "3) Display cumulative sales by month\n"
		    + "4) Display sales by month for specific year\n"
		    + "5) Return to the previous menu\n"
		    + "Option: "); 
	}
	
	public void printMenu() {
		System.out.print("\n\n***Data Analysis Menu***\n\n"
		    + "1) Print Descriptives\n"
		    + "2) Plots\n"
		    + "3) Predict sales for a given year\n"
		    + "4) Return to the previous menu\n"
		    + "Option: " ); 
	}
	
	public void printPlotsMenu() {
		System.out.print("\n\n*** Plots ***\n\n"
		    + "1) Plot sales by year (barchart)\n"
		    + "2) Plot sales by year (line chart)\n"
		    + "3) Plot sales by month for specific year interval (line chart)\n"
		    + "4) Plot cumulative sales by month (barchart)\n"
		    + "5) Plot sales by month for specific year (barchart)\n"
		    + "6) Return to previous menu\n"
		    + "Options: " ); 
	}
	
	public void getDescriptivesMenu() {
		Scanner in = new Scanner(System.in);
		int ans;
		for(;;) {
			printDescriptivesMenu();
			try {
				ans = in.nextInt();
				switch (ans) {
				case 1:
					AnalyzeOrders.printTotalOrderValueByYearWithLabel();
					break;
				case 2:
					AnalyzeOrders.printTotalOrderValueByYearIntervalWithLabelMenu();
					break;
				case 3:
					AnalyzeOrders.printTotalOrderValueByMonthWithLabel();
					break;
				case 4:
					AnalyzeOrders.printTotalOrderValueByMonthWithLabellMenu();
					break;
				case 5:
					return;
				}
			} catch (InputMismatchException e) {
				System.err.println("Your option must be an integer number. Try again...");
				// clear scanner buffer
				in.nextLine();
			}
		}
	}
	
	public void getPlotsMenu() {
		Scanner in = new Scanner(System.in);
		int ans;
		for(;;) {
			printPlotsMenu();
			try {
				ans = in.nextInt();
				switch (ans) {
				case 1:
					PlotSalesByYear.main(null);
					break;
				case 2:
					LinePlotSalesByYear.main(null);
					break;
				case 3:
					LinePlotSalesByMonth.main(null);
					break;
				case 4:
					PlotTotalOrderValueByMonth.main(null);
					break;
				case 5:
					PlotSpecificYearOrderValueByMonth.main(null);
					break;
				case 6:
					return;
				}
			} catch (InputMismatchException e) {
				System.err.println("Your option must be an integer number. Try again...");
				// clear scanner buffer
				in.nextLine();
			}
		}
	}
	
	@Override
	public void getMenu() {
		Scanner in = new Scanner(System.in);
		int ans;
		for(;;) {
			printMenu();
			try {
				ans = in.nextInt();
				switch (ans) {
				case 1:
					getDescriptivesMenu();
					break;
				case 2:
					try {
						getPlotsMenu();
					} catch (IllegalStateException e) {
						System.out.println("You must restart the ERP in order to make another plot..." ); 
					}
					break;
				case 3:
					AnalyzeOrders.predictSalesAccordingToLinearRegressionEquationMenu();
					break;
				case 4:
					return;
				}
			} catch (InputMismatchException e) {
				System.err.println("Your option must be an integer number. Try again...");
				// clear scanner buffer
				in.nextLine();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
      Order.createOrdersFromList(FileHandler.getOrdersFromCsv());
    } catch (FileNotFoundException e) {
    e.printStackTrace();
    }
		DataAnalyst d = new DataAnalyst(0, "", "","","");
		d.getMenu();
	}
}