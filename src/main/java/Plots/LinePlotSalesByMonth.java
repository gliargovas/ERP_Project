package Plots;

import ERP_Core.AnalyzeOrders;
import ERP_Core.FileHandler;
import ERP_Core.Order;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * The class contains an javafx application for creating a line chart for the monthly order value
 * for a specific year interval.
 *
 * @author George Liargovas
 * @version 1.0
 */
public class LinePlotSalesByMonth extends Application {

  /** Start the application thread. */
  @Override
  public void start(Stage stage) {
    int[] years = getPlotParameters();
    int startYear = years[0];
    int endYear = years[1];

    stage.setTitle("Total Sales");
    NumberAxis xAxis = new NumberAxis(1, 12, 1);
    xAxis.setLabel("Month");
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Sales");
    LineChart<Number, Number> lc = new LineChart<Number, Number>(xAxis, yAxis);
    lc.setTitle("Total monthly sales for each year");

    XYChart.Series data = null;
    for (int year = startYear; year <= endYear; year++) {
      data = new XYChart.Series();
      data.setName(year + "Sales");
      double[] monthlyValue =
          AnalyzeOrders.getSpecificYearOrderValueByMonth(Order.getOrders(), year);
      for (int i = 0; i < 12; i++) {
        data.getData().add(new XYChart.Data(i + 1, monthlyValue[i]));
      }
      lc.getData().add(data);
    }
    Scene scene = new Scene(lc, 500, 400);
    stage.setScene(scene);
    stage.show();
  }

  /** 
   * Launch the application.
   * @param args the arguments given 
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Contains the prompts in order for the user to specify the plot parameters. If the year interval
   * is not specified by the user the default interval is used.
   *
   * @return the first and last year of the interval
   */
  public int[] getPlotParameters() {
    Scanner in = new Scanner(System.in);
    int startYear;
    int endYear;
    String ans;
    for (; ; ) {
      try {
        System.out.print(
            "Enter the starting year for which you want to plot the monthly sales.\n"
                + "To plot sales for all the years press enter: ");
        ans = in.nextLine();
        if (ans.equals("")) {
          startYear = AnalyzeOrders.findMinYearInOrders();
          endYear = AnalyzeOrders.findMaxYearInOrders();
          break;
        } else {
          startYear = Integer.parseInt(ans);
        }
        System.out.print("Enter the year upto which you want to plot the monthly sales: ");
        endYear = in.nextInt();
        break;
      } catch (InputMismatchException e) {
        in.nextLine();
        System.out.println("Please enter a valid number. Try again...");
      } catch (NumberFormatException e) {
        System.out.println("The year must be a number. Try again...");
      }
    }
    int[] years = new int[2];
    years[0] = startYear;
    years[1] = endYear;
    return years;
  }
}
