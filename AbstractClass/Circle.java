package AbstractClass;

public class Circle extends Shape{
    double radius;
    Circle (String s, double x, double y, double radius) {
        super(s, x, y);
        this.radius = radius;
    }
    @Override
    double calculateArea() {
        return Math.PI*radius*radius;
    }
}
