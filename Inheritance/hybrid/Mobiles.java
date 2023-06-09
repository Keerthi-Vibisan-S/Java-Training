package Inheritance.hybrid;

import java.util.HashMap;

public class Mobiles extends Electronics {

    private String version;
    private int ram;
    private int storage;
    private String camera;

    private static HashMap <Integer, Mobiles> mobile_list = new HashMap<>();

    Mobiles(){}

    Mobiles(String name, int price, String category, String brand, String version, int ram, int storage, String camera) {
        super(name, price, category, brand);
        this.ram = ram;
        this.version = version;
        this.storage = storage;
        this.camera = camera;

        mobile_list.put(getProduct_id(), this);
    }

    public String getVersion() {
        return version;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getCamera() {
        return camera;
    }

    public static HashMap<Integer, Mobiles> getMobile_list() {
        return mobile_list;
    }

    @Override
    Product getProduct(int id) {
        return mobile_list.get(id);
    }

    @Override
    void getData() {
        System.out.println(getMobile_list());
    }

    @Override
    void getBasicInfo() {
        super.getBasicInfo();
        System.out.println("Version: "+version);
        System.out.println("Ram: "+ram);
        System.out.println("Storage: "+storage);
        System.out.println("Camera: "+camera);
    }
}
