package AbstractClass;

public abstract class Shape {

     private String color;
     private double positionX;
     private double positionY;

     Shape(String color, double x, double y) {
          this.color = color;
          positionX = x;
          positionY = y;
     }

      abstract double calculateArea();
}
