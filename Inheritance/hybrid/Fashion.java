package Inheritance.hybrid;

import java.util.HashMap;

public class Fashion extends Product {
    private String color;
    private String size;
    private String type;
    private static HashMap<Integer, Fashion> fashion_list = new HashMap<>();

    Fashion(){}

    Fashion(String name, int price, String color, String size, String type) {
        super(name, price);
        this.color = color;
        this.size = size;
        this.type = type;
        new Grocery().category="asdas";
        fashion_list.put(getProduct_id(), this);
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public static HashMap<Integer, Fashion> getFashion_list() {
        return fashion_list;
    }

    @Override
    void getBasicInfo() {
        super.getBasicInfo();
        System.out.println("Color: "+color);
        System.out.println("Size: "+size);
        System.out.println("Type: "+type);
        System.out.println();
    }

    @Override
    void getData() {
        System.out.println(getFashion_list());
    }

    @Override
    Product getProduct(int id) {
        return fashion_list.get(id);
    }
}