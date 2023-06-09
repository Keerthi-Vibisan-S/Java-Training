package Inheritance.multilevel;

public abstract class Electronics extends Product {
    private String category;
    private String brand;

    Electronics() {}

    Electronics(String name, int price, String category, String brand) {
        super(name, price);
        this.brand = brand;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    abstract void getData();
}
