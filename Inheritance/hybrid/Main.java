package Inheritance.hybrid;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int amt = 0;
    static Scanner in = new Scanner(System.in);

    static ArrayList <Product> cart = new ArrayList<>();

    public static void main(String args[]) {
        LoadData load = new LoadData();
        //load.LoadFashion();
        System.out.println("" +new Fashion("Formal Shirt", 400, "White", "L", "mens shirt").toString());
        System.out.println("" + new Grocery("Cinthol", 159,  "01 Feb 2023", "20 Jan 2025").toString());
        load.LoadGrocery();
        load.LoadMobiles();
        load.LoadLaptop();

        int i=2;
        while(i==1) {
            System.out.println("Select what are you looking for: ");
            System.out.println("1. Grocery");
            System.out.println("2. Mobiles");
            System.out.println("3. Laptops");
            System.out.println("4. Fashion");
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
                    purchase(new Laptop());
                    break;
                }
                case 4: {
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
