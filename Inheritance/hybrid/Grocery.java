package Inheritance.hybrid;

import java.util.HashMap;

public class Grocery extends Product {
    public String category;
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
    Grocery(String name, int price, String manufacture_date, String expire_date) {
        super(name, price);
       // this.category = category;
        this.manufacture_date = manufacture_date;
        this.expire_date = expire_date;

        grocery_list.put(getProduct_id(), this);
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

    /*@Override
    public String toString() {
        return "Grocery{" +
                "category='" + category + '\'' +
                ", manufacture_date='" + manufacture_date + '\'' +
                ", expire_date='" + expire_date + '\'' +
                '}';
    }*/

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
