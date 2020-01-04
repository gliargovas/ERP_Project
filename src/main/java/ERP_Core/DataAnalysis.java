package ERP_Core;

import java.util.ArrayList;

public class DataAnalysis {
    // Checks if certain product is in the basket of the certain order adds the sold quantity to the product sales.
    // Returns the updated product sales
    public static int salesPerProduct(int productId, Order order, int soldPieces) {
        ArrayList<int[]> basket = order.getBasket();
            for (int k = 0; k < basket.size(); k++){
                int[] prodIdQuant = basket.get(k);
                if (prodIdQuant[0] == productId) {
                 soldPieces = soldPieces + prodIdQuant[1];
                }
            }
        return soldPieces;
    }

    //Separates certain products sales of a certain year per month
    public static int[] salesPerProductPerMonth(int productId, ArrayList<Order> orders, int year) {
        int [] salesPerMonth = new int [12];
        for(int i = 0; i < orders.size(); i++){
            Order order = orders.get(i);
            if (AnalyzeOrders.getDateYear(order.getOrderDate()) == year) {
                int month = AnalyzeOrders.getDateMonth(order.getOrderDate());
                for (int k = 0; k <12; k++) {
                    if (month == k) {
                        salesPerMonth[k] = salesPerProduct(productId, order, salesPerMonth[k]);
                        break;
                    }
                }
            }
        }
        return salesPerMonth;
    }

    public static void barchart(int productId, int [] sales) {
        System.out.println("Product " + (productId) + ": ");
        for (int k = 0; k < 12; k++){
            System.out.print("Month " + (k + 1) + ": ");
            for (int i = 0; i < sales[k] / 5 ; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void salesOfAllProductsPerMonth(ArrayList<Product> products, ArrayList<Order> orders, int year) {
        for (int i = 0; i < products.size(); i++) {
            int [] salesPerMonth = salesPerProductPerMonth(products.get(i).getProductId(), orders, year);
            barchart(products.get(i).getProductId(), salesPerMonth);
        }
    }
}
