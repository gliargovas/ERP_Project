
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Scanner;

public class Charts extends Application  {

    public static void main(String[] args) throws Exception {
        LoadObjects.loadObjects();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        System.out.println("Chart Menu:");
        System.out.println("    1) to show Best-Sellers");
        System.out.println("    2) to show Best-Revenue");
        System.out.println("    3) to show Best-Seller Product of each month");
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                chartBestSellers(primaryStage);
                break;
            case 2:
                chartBestRevenue(primaryStage);
                break;
            case 3:
                chartBestPerMonthQuant(primaryStage);
                break;
            default: System.out.print("bye");
                break;
        }
    }

    public static void chartBestSellers (Stage primaryStage) {
        //Menu
        Scanner sc = new Scanner(System.in);
        System.out.println("Press: ");
        System.out.println("    1) to show Best Sellers of all time");
        System.out.println("    2) to show Best Sellers of certain year");
        System.out.println("    3) to show Best Sellers of certain year and month");
        int choice = sc.nextInt();
        //Chart Basics
        primaryStage.setTitle("Data View: Best-Sellers");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis, yAxis);
        xAxis.setLabel("Product");
        yAxis.setLabel("Sold Quantity");

        switch (choice) {
            case 1:
                bc.setTitle("Best-Sellers of All-Time");
                fillData_BestSellers(ProductSale.getAllSales(), bc);
                break;
            case 2:
                System.out.print("Choose Year: ");
                int year = sc.nextInt();
                bc.setTitle("Best-Sellers of " + year);
                fillData_BestSellers(AnalyzeData.getSalesOfYear(ProductSale.getAllSales(), year), bc);
                break;
            case 3:
                System.out.print("Choose Year: ");
                int year1 = sc.nextInt();
                System.out.print("Choose Month (0-11): ");
                int month = sc.nextInt();
                String monthName = AnalyzeData.getMonth(month);
                bc.setTitle("Best-Sellers of " + monthName + " " + year1);
                fillData_BestSellers((AnalyzeData.sortSalesPerMonth(
                        AnalyzeData.getSalesOfYear(ProductSale.getAllSales(), year1))).get(month), bc);
                break;
            default:
                break;
        }
        Scene scene = new Scene(bc, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void chartBestRevenue(Stage primaryStage) {
        //Menu
        Scanner sc = new Scanner(System.in);
        System.out.println("Press: ");
        System.out.println("    1) to show Best Revenue of all time");
        System.out.println("    2) to show Best Revenue of certain year");
        System.out.println("    3) to show Best Revenue of certain year and month");
        int choice = sc.nextInt();
        //Chart Basics
        primaryStage.setTitle("Data View: Best-Revenue");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis, yAxis);
        xAxis.setLabel("Product");
        yAxis.setLabel("Revenue (€)");

        switch (choice) {
            case 1:
                bc.setTitle("Best-Revenue of All-Time");
                fillData_BestRevenue(ProductSale.getAllSales(), bc);
                break;
            case 2:
                System.out.print("Choose Year: ");
                int year = sc.nextInt();
                bc.setTitle("Best-Revenue of " + year);
                fillData_BestRevenue(AnalyzeData.getSalesOfYear(ProductSale.getAllSales(), year), bc);
                break;
            case 3:
                System.out.print("Choose Year: ");
                int year1 = sc.nextInt();
                System.out.print("Choose Month (0-11): ");
                int month = sc.nextInt();
                String monthName = AnalyzeData.getMonth(month);
                bc.setTitle("Best-Revenue of " + monthName + " " + year1);
                fillData_BestRevenue((AnalyzeData.sortSalesPerMonth(
                        AnalyzeData.getSalesOfYear(ProductSale.getAllSales(), year1))).get(month), bc);
                break;
            default:
                break;
        }
        Scene scene = new Scene(bc, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void fillData_BestSellers(ArrayList<ProductSale> sales, BarChart bc) {
        int[][] bestSellersQuants = AnalyzeData.getTop10_BestSellers(sales);
        String[] bestSellersNames = new String[bestSellersQuants.length];
        for (int i = 0; i< bestSellersQuants.length; i++) {
            bestSellersNames[i] = Storage.searchById(bestSellersQuants[i][0]).getName();
        }
        for (int i = 0; i <bestSellersQuants.length; i++) {
            XYChart.Series<String, Number> seriesi = new XYChart.Series<>();
            seriesi.setName(bestSellersNames[i]);
            System.out.println(bestSellersNames[i] + " " + bestSellersQuants[i][1]);
            System.out.println();
            seriesi.getData().add(new XYChart.Data<>("SoldQuantity", bestSellersQuants[i][1]));
            bc.getData().addAll(seriesi);
        }
    }

    public static void fillData_BestRevenue(ArrayList<ProductSale> sales, BarChart bc) {
        double[][] bestRevenueValues = AnalyzeData.getTop10_BestRevenue(sales);
        String[] bestRevenueNames = new String[bestRevenueValues.length];
        for (int i = 0; i< bestRevenueValues.length; i++) {
            bestRevenueNames[i] = Storage.searchById((int) bestRevenueValues[i][0]).getName();
        }
        for (int i = 0; i <bestRevenueValues.length; i++) {
            XYChart.Series<String, Number> seriesi = new XYChart.Series<>();
            seriesi.setName(bestRevenueNames[i]);
            System.out.println(bestRevenueNames[i] + " " + bestRevenueValues[i][1]);
            System.out.println();
            seriesi.getData().add(new XYChart.Data<>("Revenue (€)", bestRevenueValues[i][1]));
            bc.getData().addAll(seriesi);
        }
    }


    public static void chartBestPerMonthQuant (Stage primaryStage) {
        primaryStage.setTitle("Top Product per month");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis, yAxis);
        xAxis.setLabel("Month");
        yAxis.setLabel("Sold Quantity");


        ArrayList<ArrayList<ProductSale>> salesOfEachMonth = AnalyzeData.sortSalesPerMonth(ProductSale.getAllSales());
        int [][] bestProductOfMonth = new int [12][2];
        String[] names = new String [12];
        for (int i = 0; i < 12; i++) {
            int[][] top10BestSellersQuants = AnalyzeData.sortProductsBasedOnSoldQuantities(salesOfEachMonth.get(i));
            bestProductOfMonth[i][0] = top10BestSellersQuants[top10BestSellersQuants.length - 1][0];
            bestProductOfMonth[i][1] = top10BestSellersQuants[top10BestSellersQuants.length - 1][1];
            names[i] = Storage.searchById(top10BestSellersQuants[i][0]).getName();
            System.out.println("Best of " + AnalyzeData.getMonth(i) + " " + names[i] + " " + bestProductOfMonth[i][1]);
            //System.out.println("Second best of " + AnalyzeData.getMonth(i) + " "
            //        + Storage.searchById(top10BestSellersQuants[top10BestSellersQuants.length - 2][0]).getName()
            //        + " " + top10BestSellersQuants[top10BestSellersQuants.length - 2][1]);
        }
        Scene scene = new Scene(bc ,1200, 900);
        for (int i = 0; i <12; i++) {
            XYChart.Series<String, Number> seriesi = new XYChart.Series<>();
            seriesi.setName(AnalyzeData.getMonth(i));
            seriesi.getData().add(new XYChart.Data<>( names[i], bestProductOfMonth[i][1]));
            System.out.println(names[i] + " " + bestProductOfMonth[i][1]);
            bc.getData().addAll(seriesi);
        }

        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
