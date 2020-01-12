package ERP_Core;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains methods that create charts based on data of sales. It contains menus for the
 * user to choose from. Charts can show best-seller products or products with the greatest revenue
 * of certain periods of time.
 *
 * @author Rania Pilioura
 * @version 1.0
 */
public class Charts {

  /**
   * Contains main Chart Menu and calls chart-creator methods.
   */
  public static void showChart() {
    int choice = 0;
    do {
      try {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chart Menu:");
        System.out.println("    1) Best-Sellers Chart");
        System.out.println("    2) Best-Revenue Chart");
        System.out.println("    3) Best-Seller Product of each month Chart");
        System.out.println("    4) Return to previous menu");
        System.out.print("Option: ");
        choice = sc.nextInt();
        switch (choice) {
          case 1:
            chartBestSellers();
            break;
          case 2:
            chartBestRevenue();
            break;
          case 3:
            chartBestPerMonthQuant();
            break;
          case 4:
            break;
          default:
            System.out.println("Input must be 1, 2, 3 or 4.");
            break;
        }
      } catch (java.util.InputMismatchException e) {
        System.out.println("Your option must be an integer number. Try again...");
        choice = 0;
      }
    } while (choice != 4);
    System.out.println("Exit");
  }

  /** Creates chart of Best-Selling products. */
  public static void chartBestSellers() {
    int choice = 0;
    Scanner sc = new Scanner(System.in);
    do {
      try {
        // Menu
        System.out.println("Press: ");
        System.out.println("    1) to show Best Sellers of all time");
        System.out.println("    2) to show Best Sellers of certain year");
        System.out.println("    3) to show Best Sellers of certain year and month");
        System.out.println("    4) Return to previous menu");
        System.out.print("Option: ");
        choice = sc.nextInt();
        switch (choice) {
          case 1:
            System.out.println("\n * * Best-Sellers of All-Time * *");
            fillData_BestSellers(ProductSale.getAllSales());
            break;
          case 2:
            do {
              try {
                Scanner sc1 = new Scanner(System.in);
                int year;
                do {
                  System.out.print("Choose Year: ");
                  year = sc1.nextInt();
                } while (year < 1980 || year > 2070);
                System.out.println("\n * * Best-Sellers of " + year + " * * ");
                fillData_BestSellers(AnalyzeData.getSalesOfYear(ProductSale.getAllSales(), year));
                break;
              } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong input. Year must be an integer number. Try again...");
              } catch (Exception e) {
                e.printStackTrace();
                System.out.println(
                    "Something went wrong. Maybe try another year. Year must have 4 digits.");
              }
            } while (true);
            break;
          case 3:
            do {
              try {
                Scanner sc2 = new Scanner(System.in);
                int year1;
                do {
                  System.out.print("Choose Year: ");
                  year1 = sc2.nextInt();
                } while (year1 < 1980 || year1 > 2070);
                int month;
                do {
                  System.out.print("Choose Month (1-12): ");
                  month = sc2.nextInt();
                } while (month < 0 || month > 12);
                if (month == 12) {
                  month = 0;
                }
                String monthName = AnalyzeData.getMonth(month);
                System.out.println("\n * * Best-Sellers of " + monthName + " " + year1 + " * * ");
                fillData_BestSellers(
                    (AnalyzeData.sortSalesPerMonth(
                            AnalyzeData.getSalesOfYear(ProductSale.getAllSales(), year1)))
                        .get(month));
                break;
              } catch (java.util.InputMismatchException e) {
                System.out.println(
                    "Wrong input. Year and month must be integer numbers. Try again...");
                sc.nextLine();
              } catch (Exception e) {
                e.printStackTrace();
                System.out.println(
                    "Something went wrong. Maybe try another year. Year must have 4 digits.");
              }
            } while (true);
            break;
          case 4:
            break;
          default:
            System.out.println("Input must be 1, 2, 3 or 4.");
            break;
        }
      } catch (java.util.InputMismatchException e) {
        System.out.println("Your option must be an integer number. Try again...");
        choice = 0;
        sc.nextLine();
      } catch (Exception e) {
        System.out.println("Something went wrong.");
      }
    } while (choice != 4);
  }

  /** Creates chart of products with the greatest revenue. */
  public static void chartBestRevenue() {
    int choice = 0;
    do {
      try {
        // Menu
        Scanner sc = new Scanner(System.in);
        System.out.println("Press: ");
        System.out.println("    1) to show Best Revenue of all time");
        System.out.println("    2) to show Best Revenue of certain year");
        System.out.println("    3) to show Best Revenue of certain year and month");
        System.out.println("    4) Return to previous menu");
        System.out.print("Option: ");
        choice = sc.nextInt();
        switch (choice) {
          case 1:
            System.out.println("\n * * Best-Revenue of All-Time * * ");
            fillData_BestRevenue(ProductSale.getAllSales());
            break;
          case 2:
            do {
              try {
                Scanner sc1 = new Scanner(System.in);
                int year;
                do {
                  System.out.print("Choose Year: ");
                  year = sc1.nextInt();
                } while (year < 1980 || year > 2070);
                System.out.println("\n * * Best-Revenue of " + year + " * * ");
                fillData_BestRevenue(AnalyzeData.getSalesOfYear(ProductSale.getAllSales(), year));
                break;
              } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong input. Year must be an integer number. Try again...");
              } catch (Exception e) {
                System.out.println(
                    "Something went wrong. Maybe try another year. Year must have 4 digits.");
              }
            } while (true);
            break;
          case 3:
            do {
              try {
                Scanner sc2 = new Scanner(System.in);
                int year1;
                do {
                  System.out.print("Choose Year: ");
                  year1 = sc2.nextInt();
                } while (year1 < 1980 || year1 > 2070);
                int month;
                do {
                  System.out.print("Choose Month (1-12): ");
                  month = sc2.nextInt();
                } while (month < 0 || month > 12);
                if (month == 12) {
                  month = 0;
                }
                String monthName = AnalyzeData.getMonth(month);
                System.out.println("\n * * Best-Revenue of " + monthName + " " + year1 + " * * ");
                fillData_BestRevenue(
                    (AnalyzeData.sortSalesPerMonth(
                            AnalyzeData.getSalesOfYear(ProductSale.getAllSales(), year1)))
                        .get(month));
                break;
              } catch (java.util.InputMismatchException e) {
                System.out.println(
                    "Wrong input. Year and month must be integer numbers. Try again...");
              } catch (Exception e) {
                System.out.println(
                    "Something went wrong. Maybe try another year. Year must have 4 digits.");
              }
            } while (true);
            break;
          case 4:
            break;
          default:
            System.out.println("Input must be 1, 2, 3 or 4.");
            break;
        }
      } catch (java.util.InputMismatchException e) {
        System.out.println("Your option must be an integer number. Try again...");
        choice = 0;
      } catch (Exception e) {
        System.out.println("Something went wrong.");
      }
    } while (choice != 4);
  }

  /**
   * Fills data that are to be used for creating chart of Best-Sellers.
   *
   * @param sales ProductSale ArrayList.
   */
  public static void fillData_BestSellers(ArrayList<ProductSale> sales) {
    try {
      System.out.printf(
          "%-8s %-15s %3s    %-12s%-8s", "Rank", "Product Name", "ID", "Quantity", "BarChart");
      System.out.println();
      int[][] bestSellersQuants = AnalyzeData.getTop10_BestSellers(sales);
      String[] bestSellersNames = new String[bestSellersQuants.length];
      for (int i = 0; i < bestSellersQuants.length; i++) {
        bestSellersNames[i] = Storage.searchById(bestSellersQuants[i][0]).getName();
      }
      for (int i = 0; i < bestSellersQuants.length; i++) {
        System.out.printf(
            "%2d.      %-15s %3d    %-12d",
            i + 1, bestSellersNames[i], bestSellersQuants[i][0], bestSellersQuants[i][1]);
        for (int k = 0; k < bestSellersQuants[i][1] / 15; k++) {
          System.out.print("*");
        }
        System.out.println();
      }
    } catch (Exception e) {
      System.out.println("Something went wrong when trying to fill data for the chart.");
    }
  }

  /**
   * Fills data that are to be used for creating chart of Greatest Revenue products.
   *
   * @param sales ProductSale ArrayList.
   */
  public static void fillData_BestRevenue(ArrayList<ProductSale> sales) {
    try {
      System.out.printf(
          "%-8s %-15s %3s    %-12s%8s", "Rank", "Product Name", "ID", "Revenue", "BarChart");
      System.out.println();
      double[][] bestRevenueValues = AnalyzeData.getTop10_BestRevenue(sales);
      String[] bestRevenueNames = new String[bestRevenueValues.length];
      for (int i = 0; i < bestRevenueValues.length; i++) {
        bestRevenueNames[i] = Storage.searchById((int) bestRevenueValues[i][0]).getName();
      }
      for (int i = 0; i < bestRevenueValues.length; i++) {
        System.out.printf(
            "%2d.      %-15s %3.0f    %-12.2f",
            i + 1, bestRevenueNames[i], bestRevenueValues[i][0], bestRevenueValues[i][1]);
        for (int k = 0; k < bestRevenueValues[i][1] / 5000; k++) {
          System.out.print("*");
        }
        System.out.println();
      }
    } catch (Exception e) {
      System.out.println("Something went wrong when trying to fill data for the chart.");
    }
  }

  /** Creates chart of months of certain year and their top-selling product. */
  public static void chartBestPerMonthQuant() {
    int year = 0;
    do {
      try {
        do {
          Scanner sc = new Scanner(System.in);
          System.out.print("Choose Year: ");
          year = sc.nextInt();
        } while (year < 1980 || year > 2070);
        ArrayList<ArrayList<ProductSale>> salesOfEachMonth =
            AnalyzeData.sortSalesPerMonth(
                AnalyzeData.getSalesOfYear(ProductSale.getAllSales(), year));
        int[][] bestProductOfMonth = new int[12][2];
        System.out.println(" * * Best Seller of each month of " + year + " * * ");
        System.out.printf("%-15s %-15s %3s    %-5s", "Month", "Product Name", "ID", "Quantity");
        System.out.println();
        String[] names = new String[12];
        for (int i = 0; i < 12; i++) {
          int[][] top10BestSellersQuants =
              AnalyzeData.sortProductsBasedOnSoldQuantities(salesOfEachMonth.get(i));
          bestProductOfMonth[i][0] = top10BestSellersQuants[top10BestSellersQuants.length - 1][0];
          bestProductOfMonth[i][1] = top10BestSellersQuants[top10BestSellersQuants.length - 1][1];
          Product product = Storage.searchById(top10BestSellersQuants[i][0]);
          System.out.printf(
              "%-15s %-15s %3d    %-5s",
              AnalyzeData.getMonth(i),
              product.getName(),
              bestProductOfMonth[i][0],
              bestProductOfMonth[i][1]);
          System.out.println();
        }
      } catch (java.util.InputMismatchException e) {
        System.out.println("Wrong input. Year must be an integer number. Try again...");
        year = 0;
      } catch (Exception e) {
        System.out.println("Something went wrong when trying to create Chart of months.");
        year = 0;
      }
    } while (year == 0);
  }
}
