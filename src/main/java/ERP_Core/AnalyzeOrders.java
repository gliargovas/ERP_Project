package ERP_Core;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * 
 * @author user
 *
 */

public class AnalyzeOrders {
  public static final String[] MONTHS = {
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
  };
  public static ArrayList<int[]> ordersByMonth = new ArrayList<int[]>();
  public static int[] monthlyTotalSales = new int[12];
  public static int[] weeklyTotalSales = new int[52];

  // total value by month
  /**
   * 
   * @param orders
   * @return
   */
  public static double[] getTotalOrderValueByMonth(ArrayList<Order> orders) {
    double[] monthlySales = new double[12];
    int month;
    for (Order order : orders) {
      month = getDateMonth(order.getOrderDate());
      monthlySales[month - 1] += order.getTotalCost();
    }
    return monthlySales;
  }
/**
 * 
 */
  public static void printTotalOrderValueByMonthWithLabel() {
    double[] monthlyValue = getTotalOrderValueByMonth(Order.getOrders());
    System.out.println("Total order value by month:\n");
    for (int i = 0; i < 12; i++) {
      System.out.printf("%s Sales: %.02f \n", MONTHS[i], monthlyValue[i]);
    }
  }
/**
 * 
 * @param orders
 * @param year
 * @return
 */
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
/**
 * 
 * @param year
 */
  public static void printSpecificYearOrderValueByMonthWithLabel(int year) {
    double[] monthlyValue = getSpecificYearOrderValueByMonth(Order.getOrders(), year);
    System.out.printf("Total order value by month for the year %d:\n\n", year);
    for (int i = 0; i < 12; i++) {
      System.out.printf("%s Sales: %f \n", MONTHS[i], monthlyValue[i]);
    }
  }
/**
 * 
 */
  public static void printTotalOrderValueByMonthWithLabellMenu() {
    Scanner in = new Scanner(System.in);
    int year;
    for (; ; ) {
      try {
        System.out.print("Enter the year for which you want to plot the monthly sales: ");
        year = in.nextInt();
        break;
      } catch (InputMismatchException e) {
        in.nextLine();
        System.out.println("Please enter a valid number. Try again...");
      }
    }
    printSpecificYearOrderValueByMonthWithLabel(year);
  }
/**
 * 
 * @param predictionYear
 * @return
 */
  public static double predictSalesForYear(int predictionYear) {
    ArrayList<Double> xValuesArrayList = new ArrayList<Double>();
    ArrayList<Double> yValuesArrayList = new ArrayList<Double>();
    for (Order order : Order.getOrders()) {
      xValuesArrayList.add((double) getDateYear(order.getOrderDate()));
      yValuesArrayList.add(order.getTotalCost());
    }
    int size = xValuesArrayList.size();
    double[] xValues = new double[size];
    double[] yValues = new double[size];
    for (int i = 0; i < size; i++) {
      xValues[i] = xValuesArrayList.get(i);
      yValues[i] = yValuesArrayList.get(i);
    }
    double[] regressionSlopeAndIntercept =
        calculateLinearRegressionEquationForTotalSales(xValues, yValues);
    return predictSalesAccordingToLinearRegressionEquation(
        regressionSlopeAndIntercept, predictionYear);
  }
/**
 * 
 */
  public static void predictSalesAccordingToLinearRegressionEquationMenu() {
    Scanner in = new Scanner(System.in);
    double predictionYear;
    for (; ; ) {
      try {
        System.out.print("Enter the year of the sales prediction: ");
        predictionYear = in.nextDouble();
        break;
      } catch (InputMismatchException e) {
        System.out.println("Please enter a valid year...");
      }
    }
    int size = Order.getOrders().size();
    double[] years = new double[size];
    double[] sales = new double[size];
    int index = 0;
    for (Order order : Order.getOrders()) {
      years[index] = getDateYear(order.getOrderDate());
      sales[index] = order.getTotalCost();
      index++;
    }
    double[] i = calculateLinearRegressionEquationForTotalSales(years, sales);
    double prediction =
        predictSalesAccordingToLinearRegressionEquation(
            calculateLinearRegressionEquationForTotalSales(years, sales), predictionYear);
    System.out.printf(
        "The sales for the year %d are predicted to be %.02f euros\n",
        (int) predictionYear, prediction);
  }
/**
 * 
 * @param equation
 * @param x
 * @return
 */
  public static double predictSalesAccordingToLinearRegressionEquation(
      double[] equation, double x) {
    double a = equation[0];
    double b = equation[1];
    double y = a * x + b;
    return y;
  }
/**
 * 
 * @param xValues
 * @param yValues
 * @return
 */
  public static double[] calculateLinearRegressionEquationForTotalSales(
      double[] xValues, double[] yValues) {
    double slope = calculateLinearRegressionSlope(xValues, yValues);
    double intercept = calculateLinearRegressionIntercept(slope, xValues, yValues);
    double[] equation = {slope, intercept};
    return equation;
  }
/**
 * 
 * @param xValues
 * @param yValues
 * @return
 */
  public static double calculateLinearRegressionSlope(double[] xValues, double[] yValues) {
    double sumXY = calculateSumOfProducts(xValues, yValues);
    double sumX = calclateSumOfArray(xValues);
    double sumY = calclateSumOfArray(yValues);
    double sumXSq = calculateSumOfSquares(xValues);
    int n = xValues.length;
    double slope = (n * sumXY - sumX * sumY) / (n * sumXSq - sumX * sumX);
    return slope;
  }
/**
 * 
 * @param slope
 * @param xValues
 * @param yValues
 * @return
 */
  public static double calculateLinearRegressionIntercept(
      double slope, double[] xValues, double[] yValues) {
    int n = xValues.length;
    double sumX = calclateSumOfArray(xValues);
    double sumY = calclateSumOfArray(yValues);
    double intercept = ((sumY - slope * sumX) / n);
    return intercept;
  }
/**
 * 
 * @param xValues
 * @param yValues
 * @return
 */
  public static double calculateSumOfProducts(double[] xValues, double[] yValues) {
    double sum = 0;
    for (int i = 0; i < xValues.length; i++) {
      sum += (xValues[i] * yValues[i]);
    }
    return sum;
  }
/**
 * 
 * @param values
 * @return
 */
  public static double calculateSumOfSquares(double[] values) {
    double sum = 0;
    for (double value : values) {
      sum += value * value;
    }
    return sum;
  }
/**
 * 
 * @param values
 * @return
 */
  public static double calclateSumOfArray(double[] values) {
    double sum = 0;
    for (double value : values) {
      sum += value;
    }
    return sum;
  }
/**
 * 
 * @param orders
 * @param startYear
 * @param endYear
 * @return
 */
  // value by year interval
  public static double[] getTotalOrderValueByYearInterval(
      ArrayList<Order> orders, int startYear, int endYear) {
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
/**
 * 
 * @param startYear
 * @param endYear
 */
  public static void printTotalOrderValueByYearIntervalWithLabel(int startYear, int endYear) {
    double[] yearlyValue = getTotalOrderValueByYearInterval(Order.getOrders(), startYear, endYear);
    int index = 0;
    System.out.printf("Total order value from the year %d to %d:\n\n", startYear, endYear);
    for (int i = startYear; i <= endYear; i++) {
      System.out.printf("%d Sales: %.02f \n", i, yearlyValue[index]);
      index++;
    }
  }
/**
 * 
 */
  public static void printTotalOrderValueByYearIntervalWithLabelMenu() {
    Scanner in = new Scanner(System.in);
    int startYear;
    int endYear;
    for (; ; ) {
      try {
        System.out.print("Enter the starting year for which you want to plot the monthly sales: ");
        startYear = in.nextInt();
        System.out.print("Enter the year upto which you want to plot the monthly sales: ");
        endYear = in.nextInt();
        break;
      } catch (InputMismatchException e) {
        in.nextLine();
        System.out.println("Please enter a valid number. Try again...");
      }
    }
    printTotalOrderValueByYearIntervalWithLabel(startYear, endYear);
  }
/**
 * 
 * @return
 */
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
/**
 * 
 * @return
 */
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
/**
 * 
 */
  public static void printTotalOrderValueByYearWithLabel() {
    int startYear = findMinYearInOrders();
    int endYear = findMaxYearInOrders();
    printTotalOrderValueByYearIntervalWithLabel(startYear, endYear);
  }
/**
 * 
 * @param date
 * @return
 */
  public static int getDateDay(String date) {
    String[] cutDate = date.split("-");
    return Integer.parseInt(cutDate[0]);
  }
/**
 * 
 * @param date
 * @return
 */
  public static int getDateMonth(String date) {
    String[] cutDate = date.split("-");
    return Integer.parseInt(cutDate[1]);
  }
/**
 * 
 * @param date
 * @return
 */
  public static int getDateYear(String date) {
    String[] cutDate = date.split("-");
    return Integer.parseInt(cutDate[2].substring(0, 4));
  }
}
