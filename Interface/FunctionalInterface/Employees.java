package Interface.FunctionalInterface;
import java.util.ArrayList;

interface empInterface {
    boolean filter(Employees e);
}

public class Employees {

    private String name;
    private int age;

    Employees(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String [] args) {

        ArrayList <Employees> arr = new ArrayList<>();

        Employees obj1 = new Employees("Keerthi Vibisan", 20);
        Employees obj2 = new Employees("Karthi", 10);
        Employees obj3 = new Employees("vibi", 15);
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        System.out.println("Employees age greater than 15");
        filterData(arr, obj -> obj.age > 15);

        System.out.println("Employees name starts with K");
        filterData(arr, obj -> obj.name.startsWith("K"));
    }

    static void filterData(ArrayList <Employees> arr, empInterface filter) {
        for(Employees e: arr ) {
            if(filter.filter(e)) System.out.println(e.name);
        }
    }

}

