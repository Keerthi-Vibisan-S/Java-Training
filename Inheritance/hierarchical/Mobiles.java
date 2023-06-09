package Inheritance.hierarchical;

import java.util.HashMap;

public class Mobiles extends Product {
    private String android_version;
    private int ram;
    private int storage;
    private String camera;

    private static HashMap<Integer, Mobiles> mobile_list = new HashMap<>();

    Mobiles() {}

    Mobiles(String name, int price, String android_version, String camera, int ram, int storage) {
        super(name, price);
        this.android_version = android_version;
        this.ram = ram;
        this.camera = camera;
        this.storage = storage;

        mobile_list.put(getProduct_id(), this);
    }

    public String getAndroid_version() {
        return android_version;
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
    void getBasicInfo() {
        super.getBasicInfo();
        System.out.println("Android Version: "+android_version);
        System.out.println("Ram: "+ram);
        System.out.println("Rom: "+storage);
        System.out.println("Camera: "+camera);
        System.out.println();
    }

    @Override
    void getData() {
        System.out.println(getMobile_list());
    }

    @Override
    Product getProduct(int id) {
        return mobile_list.get(id);
    }
}
