package Inheritance.multilevel;

import java.util.HashMap;

public class Laptop extends Electronics{

    private String processor;
    private String ram;
    private String storage;
    private String graphics;

    private static HashMap<Integer, Laptop> laptop_list = new HashMap<>();

    Laptop() {}

    Laptop(String name, int price, String category, String brand, String processor, String ram, String storage, String graphics) {
        super(name, price, category, brand);
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.graphics = graphics;

        laptop_list.put(getProduct_id(), this);
    }

    @Override
    Product getProduct(int id) {
        return laptop_list.get(id);
    }

    @Override
    void getData() {
        System.out.println(laptop_list);
    }

    @Override
    void getBasicInfo() {
        super.getBasicInfo();
        System.out.println("Processor: "+processor);
        System.out.println("Ram: "+ram);
        System.out.println("Storage: "+storage);
        System.out.println("Graphics: "+graphics);
    }
}
