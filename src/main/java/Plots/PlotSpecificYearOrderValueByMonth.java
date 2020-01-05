package Plots;

import java.util.InputMismatchException;
import java.util.Scanner;
import ERP_Core.AnalyzeOrders;
import ERP_Core.FileHandler;
import ERP_Core.Order;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlotSpecificYearOrderValueByMonth extends Application {

  public static void main(String args[]) {
    Application.launch(args);
  }

  public void start(Stage stage) {
    int year = this.getPlotArguments();
    stage.setTitle("Total Sales by Month for year " + year);
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Month");
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Total Order Value");
    BarChart barChart = new BarChart(xAxis, yAxis);
    
    XYChart.Series dataSeries = new XYChart.Series();
    double[] monthlyValue = AnalyzeOrders.getSpecificYearOrderValueByMonth(Order.getOrders(), year);
    dataSeries.setName("Value in euros");
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

  public int getPlotArguments() {
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
    return year;
  }
}
