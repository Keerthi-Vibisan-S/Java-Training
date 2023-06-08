package AbstractClass;

import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.println("Select your shape to calculate Area: ");
            System.out.println("1. Circle");
            System.out.println("2. Square");
            System.out.println("3. Triangle");
            int ch = in.nextInt();
            in.nextLine();

            System.out.println("enter your color");
            String clr = in.nextLine();

            System.out.println("Enter X coordinate: ");
            int x = in.nextInt();
            System.out.println("Enter Y coordinate: ");
            int y = in.nextInt();

            switch (ch) {
                case 1: {
                    System.out.println("enter your radius: ");
                    double radius = in.nextDouble();
                    calc(new Circle(clr, x, y, radius));
                    break;
                }

                case 2: {
                    System.out.println("enter your side length: ");
                    double length = in.nextDouble();
                    calc(new Square(clr, x, y, length));
                    break;
                }

                case 3: {
                    System.out.println("enter your base: ");
                    double base = in.nextDouble();
                    System.out.println("enter your height: ");
                    double height = in.nextDouble();
                    calc(new Triangle(clr, x, y, base, height));
                    break;
                }

                default: {
                    System.out.println("Enter a Valid Option");
                }
            }
        }
    }

    static void calc(Shape obj) {
        System.out.println(obj.calculateArea());
    }
}
