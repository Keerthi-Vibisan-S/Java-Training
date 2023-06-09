package Inheritance.hierarchical;

import java.util.HashMap;

public class Grocery extends Product {
    private String category;
    private String manufacture_date;
    private String expire_date;

    private static HashMap <Integer, Grocery> grocery_list = new HashMap<>();

    Grocery() {}

    Grocery(String name, int price, String category, String manufacture_date, String expire_date) {
        super(name, price);
        this.category = category;
        this.manufacture_date = manufacture_date;
        this.expire_date = expire_date;

        grocery_list.put(getProduct_id(), this);
    }

    public String getCategory() {
        return category;
    }

    public String getManufacture_date() {
        return manufacture_date;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public static HashMap<Integer, Grocery> getGrocery_list() {
        return grocery_list;
    }

    @Override
    void getBasicInfo() {
        super.getBasicInfo();
        System.out.println("Category: "+category);
        System.out.println("Manufacture Date: "+manufacture_date);
        System.out.println("Expire Date: "+expire_date);
        System.out.println();
    }

    @Override
    void getData() {
        System.out.println(getGrocery_list());
    }

    @Override
    Product getProduct(int id) {
        return grocery_list.get(id);
    }
}
