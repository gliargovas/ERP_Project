package Plots;

import ERP_Core.AnalyzeOrders;
import ERP_Core.FileHandler;
import ERP_Core.Order;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The class contains an javafx application for creating a bar chart for the yearly order value for
 * every year for a specified year interval.
 *
 * @author George Liargovas
 * @version 1.0
 */
public class PlotSalesByYear extends Application {

  /**
   * Launch the application.
   *
   * @param args the arguments given
   */
  public static void main(String[] args) {
    Application.launch(args);
  }

  /** The application's thread for creating the plot. */
  public void start(Stage stage) {
    int[] years = this.getPlotParameters();
    int startYear = years[0];
    int endYear = years[1];

    stage.setTitle(String.format("Total Sales for years %d to %d", startYear, endYear));
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Month");
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Total Order Value");
    BarChart barChart = new BarChart(xAxis, yAxis);
    barChart.setTitle(String.format("Total Sales for years %d to %d", startYear, endYear));

    XYChart.Series dataSeries = new XYChart.Series();
    double[] yearlyValue =
        AnalyzeOrders.getTotalOrderValueByYearInterval(Order.getOrders(), startYear, endYear);
    dataSeries.setName("Value in euros");
    int index = 0;
    for (int i = startYear; i <= endYear; i++) {
      dataSeries.getData().add(new XYChart.Data(String.valueOf(i), yearlyValue[index]));
      index++;
    }
    barChart.getData().add(dataSeries);
    VBox vbox = new VBox(barChart);
    Scene scene = new Scene(vbox, 400, 200);
    stage.setScene(scene);
    stage.setHeight(300);
    stage.setWidth(1200);
    stage.show();
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
