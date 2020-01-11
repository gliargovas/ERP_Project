package ERP_Core;
import java.util.ArrayList;

/**
 * This class contains methods that are used in order to analyze data of sales.
 * Methods in here are used as tools for creating charts in Charts class.
 *
 * @author Rania Pilioura
 * @version 1.0
 */
public class AnalyzeData {

    /**
     * Sorts products that their id is found in an ArrayList of product sales
     * based on their total sold quantities of the sales listed in the ArrayList.
     *
     * @param productSales an ArrayList of ProductSale objects that are to be sorted.
     * @return sorted [][] array that contains product ids and total quantities, type integer
     *
     */
    public static int[][] sortProductsBasedOnSoldQuantities(ArrayList<ProductSale> productSales) {
        //[0][1]: [0] = id of product, [1] = total sold quantity. array length = allProducts.size()
        int [][] productIDAndSoldQuantity = new int [Storage.getProducts().size()] [2];
        for (int i = 0; i < productIDAndSoldQuantity.length; i++) {
            productIDAndSoldQuantity [i][0] = Storage.getProducts().get(i).getProductId();
        }
        for (int k = 0; k < productSales.size(); k++) {
            for (int i = 0; i < productIDAndSoldQuantity.length; i++) {
                if (productSales.get(k).getProductId() == productIDAndSoldQuantity[i][0]) {
                    productIDAndSoldQuantity[i][1] = productIDAndSoldQuantity[i][1] + productSales.get(k).getQuantity();
                }
            }
        }
        quickSort(productIDAndSoldQuantity, 0, (productIDAndSoldQuantity.length - 1));
        return  productIDAndSoldQuantity;
    }

    /**
     * Sorts products that their id is found in an ArrayList of product sales
     * based on the total revenue per product of the sales listed in the ArrayList.
     *
     * @param productSales an ArrayList of ProductSale objects that are to be sorted.
     * @return sorted [][] array that contains product ids and total revenues, type double
     */
    public static double[][] sortProductsBasedOnSalesRevenue(ArrayList<ProductSale> productSales) {
        //[0][1]: [0] = id of product, [1] = total revenue of sale. array length = allProducts.size()
        double[][] productIDAndSaleRevenue = new double [Storage.getProducts().size()] [2];
        for (int i = 0; i < productIDAndSaleRevenue.length; i++) {
            productIDAndSaleRevenue [i][0] = Storage.getProducts().get(i).getProductId();
        }
        for (int k = 0; k < productSales.size(); k++) {
            for (int i = 0; i < productIDAndSaleRevenue.length; i++) {
                if (productSales.get(k).getProductId() == productIDAndSaleRevenue[i][0]) {
                    productIDAndSaleRevenue[i][1] =
                            productIDAndSaleRevenue[i][1] + productSales.get(k).getQuantity() * productSales.get(k).getPrice();
                }
            }
        }
        quickSort(productIDAndSaleRevenue, 0, (productIDAndSaleRevenue.length - 1));
        return  productIDAndSaleRevenue;
    }

    /**
     * Returns top 10 Best Seller products, based on quantity.
     *
     * @param productSales the ArrayList of ProductSales that is used to find the best sellers.
     * @return array [][] that contains top10 selling product ids and quantities, type integer
     */
    public static int[][] getTop10_BestSellers(ArrayList<ProductSale> productSales) {
        int[][] top10 = new int[10][2];
        int [][] productIDAndSoldQuantity = sortProductsBasedOnSoldQuantities(productSales);
        for (int i = 0; i < top10.length; i++) {
            top10[i][1] = productIDAndSoldQuantity[productIDAndSoldQuantity.length - 1 - i][1];
            top10[i][0] = productIDAndSoldQuantity[productIDAndSoldQuantity.length - 1 - i][0];
        }
        return top10;
    }

    /**
     * Returns top 10 products that had the greatest revenue.
     *
     * @param productSales the ArrayList of ProductSales that is used to find top revenues.
     * @return array [][] that contains top10 selling product ids and revenues, type double
     */
    public static double[][] getTop10_BestRevenue(ArrayList<ProductSale> productSales) {
        double[][] top10 = new double[10][2];
        double [][] productIDAndSaleRevenue = sortProductsBasedOnSalesRevenue(productSales);
        for (int i = 0; i < top10.length; i++) {
            top10[i][1] = productIDAndSaleRevenue[productIDAndSaleRevenue.length - 1 - i][1];
            top10[i][0] = productIDAndSaleRevenue[productIDAndSaleRevenue.length - 1 - i][0];
        }
        return top10;
    }

    /**
     *Sorts products Sales to the month they were created.
     *
     * @param sales an ArrayList of ProductSales objects.
     * @return an ArrayList of 12 ArrayLists of sorted-per-month product sales, type ProductSale
     */
    public static ArrayList<ArrayList<ProductSale>> sortSalesPerMonth(ArrayList<ProductSale> sales) {
        ArrayList<ArrayList<ProductSale>> salesPerMonth = new ArrayList();
        for (int i = 0; i<12; i++){
            ArrayList<ProductSale> monthsale = new ArrayList<>();
            salesPerMonth.add(monthsale);
        }
        for(int i = 0; i < sales.size(); i++) {
            ProductSale sale = sales.get(i);
            int month = sale.getMonth();
            for (int k = 0; k < 12; k++) {
                if (month == k) {
                    salesPerMonth.get(k).add(sale);
                    break;
                }
            }
        }
        return salesPerMonth;
    }

    /**
     *Separates certain product sales to corresponding year.
     *
     * @param sales an ArrayList of ProductSales objects.
     * @param year the year of which Product Sales are returned.
     * @return an ArrayList of sales of a certain year, type ProductSale
     */
    public static ArrayList<ProductSale> getSalesOfYear (ArrayList<ProductSale> sales, int year) {
        ArrayList<ProductSale> salesOfYear = new ArrayList();
        for(int i = 0; i < sales.size(); i++) {
            ProductSale sale = sales.get(i);
            int yearOfSale = sale.getYear();
            if (yearOfSale == year) {
                salesOfYear.add(sale);
            }
        }
        return salesOfYear;
    }

    /**
     * Returns name of given month.
     *
     * @param m the number of a month.
     * @return the name that corresponds to the number of the month, type String.
     */
    public static String getMonth(int m) {
        switch (m) {
            case 0:
            case 12:
                return "December";
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            default:
                return "Wrong Input";
        }
    }

    /**
     * QuickSort method for two dimensional array of integers.
     *
     * @param arr array of integers.
     * @param begin begin flag.
     * @param end end flag.
     */
    //quickSort for int[][] array
    public static void quickSort(int arr[][], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    /**
     * Partition method used in quickSort.
     * Checks and trades values of an array on order for it to be sorted.
     *
     * @param arr array of integers.
     * @param begin begin flag.
     * @param end end flag.
     * @return new begin or end flag, type integer
     */
    private static int partition(int arr[][], int begin, int end) {
        int pivot = arr[end][1];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j][1] <= pivot) {
                i++;

                int swapTemp = arr[i][0];
                arr[i][0] = arr[j][0];
                arr[j][0] = swapTemp;

                int swapTemp2 = arr[i][1];
                arr[i][1] = arr[j][1];
                arr[j][1] = swapTemp2;
            }
        }

        int swapTemp = arr[i+1][0];
        arr[i+1][0] = arr[end][0];
        arr[end][0] = swapTemp;

        int swapTemp2 = arr[i+1][1];
        arr[i+1][1] = arr[end][1];
        arr[end][1] = swapTemp2;

        return i+1;
    }

    /**
     * QuickSort method for two dimensional array of double values.
     *
     * @param arr array of double values.
     * @param begin begin flag.
     * @param end end flag.
     */
    //quickSort for double[][] array
    public static void quickSort(double arr[][], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    /**
     * Partition method used in quickSort.
     * Checks and trades values of an array on order for it to be sorted.
     *
     * @param arr array of double values.
     * @param begin begin flag.
     * @param end end flag.
     * @return new begin or end flag, type integer
     */
    private static int partition(double arr[][], int begin, int end) {
        double pivot = arr[end][1];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j][1] <= pivot) {
                i++;

                double swapTemp = arr[i][0];
                arr[i][0] = arr[j][0];
                arr[j][0] = swapTemp;

                double swapTemp2 = arr[i][1];
                arr[i][1] = arr[j][1];
                arr[j][1] = swapTemp2;
            }
        }

        double swapTemp = arr[i+1][0];
        arr[i+1][0] = arr[end][0];
        arr[end][0] = swapTemp;

        double swapTemp2 = arr[i+1][1];
        arr[i+1][1] = arr[end][1];
        arr[end][1] = swapTemp2;

        return i+1;
    }
}