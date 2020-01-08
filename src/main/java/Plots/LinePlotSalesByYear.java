package Plots;

import ERP_Core.AnalyzeOrders;
import ERP_Core.FileHandler;
import ERP_Core.Order;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * The class contains an javafx application for creating a line chart for the yearly order value for
 * every year.
 *
 * @author George Liargovas
 * @version 1.0
 */
public class LinePlotSalesByYear extends Application {

  /** Launch the application. */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  /** Start the application thread. */
  public void start(Stage stage) {
    int startYear = AnalyzeOrders.findMinYearInOrders();
    int endYear = AnalyzeOrders.findMaxYearInOrders();

    stage.setTitle("Total Sales");

    NumberAxis xAxis = new NumberAxis(startYear - 1, endYear + 1, 1);
    xAxis.setLabel("Year");
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Sales");
    ScatterChart<Number, Number> sc = new ScatterChart<Number, Number>(xAxis, yAxis);
    sc.setTitle("Total sales by year");
    XYChart.Series data = new XYChart.Series();
    data.setName("Equities");

    double[] yearlyValue =
        AnalyzeOrders.getTotalOrderValueByYearInterval(Order.getOrders(), startYear, endYear);
    int index = 0;
    for (int i = startYear; i <= endYear; i++) {
      data.getData().add(new XYChart.Data(i, yearlyValue[index]));
      index++;
    }
    sc.getData().add(data);
    Scene scene = new Scene(sc, 500, 400);
    stage.setScene(scene);
    stage.show();
  }
}
