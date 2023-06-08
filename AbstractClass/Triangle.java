package AbstractClass;

public class Triangle extends Shape{
    double base, height;
    Triangle(String color, double x, double y, double base, double height) {
        super(color, x, y);
        this.base = base;
        this.height = height;
    }
    @Override
    double calculateArea() {
        return 0.5*base*height;
    }
}
