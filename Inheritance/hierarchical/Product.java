package Inheritance.hierarchical;

public abstract class Product {
    private static int id = 1000;
    private int product_id;
    private String product_name;
    private int product_price;
//    private String offer;

    Product() {}

    Product(String name, int price) {
        product_price = price;
        product_name = name;
        product_id = id++;
    }

    void getBasicInfo()
    {
        System.out.println();
        System.out.println("P_Id: "+product_id);
        System.out.println("Name: "+product_name);
        System.out.println("Price: "+product_price);
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    void getData() {
    }

    abstract Product getProduct(int id);

    public String toString() {
        getBasicInfo();
        return "Name: "+product_name;
    }

    // Real Abstract methods
    //abstract void addProduct();
}
