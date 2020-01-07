package ERP_Core;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * The class contains methods for analyzing the orders that have been recorded by the 
 * ERP. There are methods for calculating frequencies of products by specific time periods 
 * as well as methods for predicting the sales by using linear regression.
 * 
 * @author George Liargovas
 * @version 1.0
 */

public class AnalyzeOrders {
  /** A final array that contains the month names of a year */
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

  /**
   * Returns an array with the total order value of each, ignoring the year.
   * @param orders the order list
   * @return total order value of each month
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
  
  /** Prints the total order value of each month with month labels. */
  public static void printTotalOrderValueByMonthWithLabel() {
    double[] monthlyValue = getTotalOrderValueByMonth(Order.getOrders());
    System.out.println("Total order value by month:\n");
    for (int i = 0; i < 12; i++) {
      System.out.printf("%s Sales: %.02f \n", MONTHS[i], monthlyValue[i]);
    }
  }
  
/**
 * Returns the order value by month for a specific year
 * @param orders the order list
 * @param year the year for which to print the orders
 * @return total order value each month for the given year
 */
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
 * Prints the order value for each month for specific year, with label.
 * @param year the year for which to print the orders
 */
  public static void printSpecificYearOrderValueByMonthWithLabel(int year) {
    double[] monthlyValue = getSpecificYearOrderValueByMonth(Order.getOrders(), year);
    System.out.printf("Total order value by month for the year %d:\n\n", year);
    for (int i = 0; i < 12; i++) {
      System.out.printf("%s Sales: %f \n", MONTHS[i], monthlyValue[i]);
    }
  }
  
/**
 * Prints the order value by month for a specific year according to user input.
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
 * Predicts the sales for the given year using simple linear regression
 * @param predictionYear the year of the prediction
 * @return the predicted sales for the given year
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
  
  /** Predicts the sales for the given year using simple linear regression, according to user input. */
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
 * Returns a predicted value according to a given simple linear regression equation.
 * @param equation the array with the linear regression equation coefficients
 * @param x the year of the prediction
 * @return the predicted value
 */
  public static double predictSalesAccordingToLinearRegressionEquation(
      double[] equation, double x) {
    double a = equation[0];
    double b = equation[1];
    double y = a * x + b;
    return y;
  }
  
/**
 * Calculated the linear regression slope and intercept of a given set of values
 * @param xValues the x axis arguments
 * @param yValues the y axis arguments
 * @return the linear regression equation slope and intercept
 */
  public static double[] calculateLinearRegressionEquationForTotalSales(
      double[] xValues, double[] yValues) {
    double slope = calculateLinearRegressionSlope(xValues, yValues);
    double intercept = calculateLinearRegressionIntercept(slope, xValues, yValues);
    double[] equation = {slope, intercept};
    return equation;
  }
  
/**
 * Calculates the linear regression equation slope.
 * @param xValues the x axis values
 * @param yValues the y axis values
 * @return the slope of the regression line
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
 * Calculates the linear regression equation intercept.
 * @param slope the slope of the linear regression line
 * @param xValues the x axis values
 * @param yValues the y axis values
 * @return the regression line intercept
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
 * Calculates the sum of the product of x and y value pairs.
 * @param xValues the x axis values
 * @param yValues the y axis values
 * @return the sum of products
 */
  public static double calculateSumOfProducts(double[] xValues, double[] yValues) {
    double sum = 0;
    for (int i = 0; i < xValues.length; i++) {
      sum += (xValues[i] * yValues[i]);
    }
    return sum;
  }
  
/**
 * Calculates the sum of squared values of an array.
 * @param values the values of the array
 * @return the sum of squared values
 */
  public static double calculateSumOfSquares(double[] values) {
    double sum = 0;
    for (double value : values) {
      sum += value * value;
    }
    return sum;
  }
  
/**
 * Calculates the sum of the values of an array.
 * @param values the values of the array
 * @return sum of values
 */
  public static double calclateSumOfArray(double[] values) {
    double sum = 0;
    for (double value : values) {
      sum += value;
    }
    return sum;
  }
  
/**
 * Returns the total order value for a year interval.
 * @param orders the order list
 * @param startYear the beginning of the interval
 * @param endYear the end of the interval
 * @return an array with the total order value of each year for the given interval
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
 * Prints the total order value for a year interval with label.
 * @param startYear the beginning of the interval
 * @param endYear the end of the interval
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
 * Prints the total order value with labels for a year interval, according to user input. 
 * If no interval is specified by the user, the sales of every year are displayed. */
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
 * Returns the year the earliest order has been made.
 * @return the earliest year
 */
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
 * Returns the year the latest order has been made.
 * @return the latest year
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
 * Prints total order value for each year, with label.
 */
  public static void printTotalOrderValueByYearWithLabel() {
    int startYear = findMinYearInOrders();
    int endYear = findMaxYearInOrders();
    printTotalOrderValueByYearIntervalWithLabel(startYear, endYear);
  }
  
/**
 * Returns the day of a given date.
 * @param date the order date
 * @return the day of the date
 */
  public static int getDateDay(String date) {
    String[] cutDate = date.split("-");
    return Integer.parseInt(cutDate[0]);
  }
  
/**
 * Returns the month of a given date.
 * @param date the order date
 * @return the month of the date
 */
  public static int getDateMonth(String date) {
    String[] cutDate = date.split("-");
    return Integer.parseInt(cutDate[1]);
  }
  
/**
 * Returns the year of a given date.
 * @param date the order date
 * @return the year of the date
 */
  public static int getDateYear(String date) {
    String[] cutDate = date.split("-");
    return Integer.parseInt(cutDate[2].substring(0, 4));
  }
}
