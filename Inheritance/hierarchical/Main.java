package Inheritance.hierarchical;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int amt = 0;
    static Scanner in = new Scanner(System.in);

    static ArrayList <Product> cart = new ArrayList<>();

    public static void main(String args[]) {
        LoadData load = new LoadData();
        load.LoadFashion();
        load.LoadGrocery();
        load.LoadMobiles();

        while(true) {
            System.out.println("Select what are you looking for: ");
            System.out.println("1. Grocery");
            System.out.println("2. Mobiles");
            System.out.println("3. Fashion");
            System.out.println("999. Exit");
            System.out.println("Your Cart: "+cart);
            System.out.println("Amount: " + amt);
            int ch = in.nextInt();

            switch (ch) {
                case 1: {
                    purchase(new Grocery());
                    break;
                }
                case 2: {
                    purchase(new Mobiles());
                    break;
                }
                case 3: {
                    purchase(new Fashion());
                    break;
                }

                default: {
                    System.out.println("Enter a valid Option.");
                }
            }
        }
    }

    static void purchase(Product obj) {
        obj.getData();
        while(true){
            System.out.println("Enter the product Id you wish to purchase or -1 to cancel: ");
            int id = in.nextInt();
            if(id == -1) break;
            amt = amt+obj.getProduct(id).getProduct_price();
            cart.add(obj.getProduct(id));
        }
    }
}
