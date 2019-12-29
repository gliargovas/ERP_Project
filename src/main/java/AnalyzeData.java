import java.util.ArrayList;

public class AnalyzeData {

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
        Tools.quickSort(productIDAndSoldQuantity, 0, (productIDAndSoldQuantity.length - 1));
        return  productIDAndSoldQuantity;
    }

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
        Tools.quickSort(productIDAndSaleRevenue, 0, (productIDAndSaleRevenue.length - 1));
        return  productIDAndSaleRevenue;
    }

    public static int[][] getTop10_BestSellers(ArrayList<ProductSale> productSales) {
        int[][] top10 = new int[10][2];
        int [][] productIDAndSoldQuantity = sortProductsBasedOnSoldQuantities(productSales);
        for (int i = 0; i < top10.length; i++) {
            top10[i][1] = productIDAndSoldQuantity[productIDAndSoldQuantity.length - 1 - i][1];
            top10[i][0] = productIDAndSoldQuantity[productIDAndSoldQuantity.length - 1 - i][0];
        }
        return top10;
    }

    public static double[][] getTop10_BestRevenue(ArrayList<ProductSale> productSales) {
        double[][] top10 = new double[10][2];
        double [][] productIDAndSaleRevenue = sortProductsBasedOnSalesRevenue(productSales);
        for (int i = 0; i < top10.length; i++) {
            top10[i][1] = productIDAndSaleRevenue[productIDAndSaleRevenue.length - 1 - i][1];
            top10[i][0] = productIDAndSaleRevenue[productIDAndSaleRevenue.length - 1 - i][0];
        }
        return top10;
    }


    public static ArrayList<ArrayList<ProductSale>> sortSalesPerMonth(ArrayList<ProductSale> sales) {
        //Separates certain products sales of a certain year per month
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

    public static ArrayList<ProductSale> getSalesOfYear (ArrayList<ProductSale> sales, int year) {
        //Separates certain products sales to corresponding year
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
}
