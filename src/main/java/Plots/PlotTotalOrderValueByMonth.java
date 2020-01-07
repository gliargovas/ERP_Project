package Plots;

import ERP_Core.AnalyzeOrders;
import ERP_Core.FileHandler;
import ERP_Core.Order;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The class contains an javafx application for creating a bar chart for the total monthly order
 * value for every year.
 *
 * @author George Liargovas
 * @version 1.0
 */
public class PlotTotalOrderValueByMonth extends Application {

  /** Main method for launching the application */
  public static void main(String args[]) {
    Application.launch(args);
  }

  /** Create the plot */
  public void start(Stage stage) {
    stage.setTitle("Total Sales by Month");
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Month");
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Total Order Value");
    BarChart barChart = new BarChart(xAxis, yAxis);

    XYChart.Series dataSeries = new XYChart.Series();
    double[] monthlyValue = AnalyzeOrders.getTotalOrderValueByMonth(Order.getOrders());
    dataSeries.setName("Value");
    for (int i = 0; i < 12; i++) {
      dataSeries.getData().add(new XYChart.Data(AnalyzeOrders.MONTHS[i], monthlyValue[i]));
    }
    barChart.getData().add(dataSeries);
    VBox vbox = new VBox(barChart);
    Scene scene = new Scene(vbox, 400, 200);
    stage.setScene(scene);
    stage.setHeight(300);
    stage.setWidth(1200);
    stage.show();
  }
}
