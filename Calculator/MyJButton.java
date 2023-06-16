package Calculator;

import javax.swing.*;

public class MyJButton extends JButton {
    private String name;
    public MyJButton(String name) {
        super(name);
        this.name = name;
    }
    String getValue() {
        return name;
    }
}
