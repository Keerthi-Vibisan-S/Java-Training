package AbstractClass;

public class Square extends Shape {
    double sideLength;
    Square (String s, double x, double y, double sq) {
        super(s, x, y);
        this.sideLength = sq;
    }
    @Override
    double calculateArea() {
        return sideLength*sideLength;
    }
}
