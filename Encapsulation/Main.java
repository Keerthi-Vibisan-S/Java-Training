package Encapsulation;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main (String args []) {
        String name [] = {"Keerthi Vibisan", "John", "Snow", "Ragnar"};
        ArrayList <ArrayList<String>> stack = new ArrayList<>();
        stack.add(new ArrayList<>(Arrays.asList("Java", "Node", "Docker", "AWS")));
        stack.add(new ArrayList<>(Arrays.asList("Java")));
        stack.add(new ArrayList<>(Arrays.asList("React", "Vue JS")));
        stack.add(new ArrayList<>(Arrays.asList("UI/UX")));

        for(int i = 0; i < 4; i++) {
            EmployeePojo e = new EmployeePojo(1000+i, name[i], 20+i, "100000", stack.get(i));
            System.out.println(e);
        }
    }
}
