import java.util.ArrayList;



public class ProductSale {

    private int productId;
    private String date;
    private int quantity;
    private double price;
    private static ArrayList<ProductSale> allSales = new ArrayList<>();

    public ProductSale (int productId, int quantity, double price, String date) {
        this.productId = productId;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    //getters
    public int getProductId() {
        return productId;
    }
    public String getDate() {
        return date;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public static ArrayList<ProductSale> getAllSales() {
        return allSales;
    }

    public int getDay() {
        String date = getDate();
        int day = Integer.parseInt(date.substring(0,2));
        return day;
    }

    public int getMonth() {
        String date = getDate();
        String monthstr = date.substring(3, 6);
        switch (monthstr) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 0;
            default:
                return -1;
        }
    }

    public int getYear(){
        String date = getDate();
        int year = Integer.parseInt(date.substring(7,11));
        return year;
    }


    // Checks if certain product is in the basket of the certain order adds the sold quantity to the product sales.
    // Returns the updated product sales
    public static void orderToSale(Order order) {
        String date = order.getOrderDate();
        ArrayList<int[]> basket = order.getBasket();
        int[] prodSale = new int [2];
        for (int i = 0; i < basket.size(); i++) {
            prodSale = basket.get(i);
            Product product = Storage.searchById(prodSale[0]);
            assert product != null;
            ProductSale sale = new ProductSale(prodSale[0], prodSale[1], product.getSalePrice(), date);
            product.getSales().add(sale);
            allSales.add(sale);
        }
    }

    @Override
    public String toString() {
        return ("Product id: "+ getProductId() + " quantity: " + getQuantity()+ " " + getDate());

    }

    public void setProductSaleDate(String date){
        this.date = date;
    }

    public void setMonth(String month) {
        String date = getDate();
        String newDate = (date.substring(0,3) + month + date.substring(6, 11));
        setProductSaleDate(newDate);
    }



}
