package Calculator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator implements ActionListener {
    private JFrame frame;

    private String operator = "";
    private StringBuffer lastString = new StringBuffer();

    private StringBuffer exp = new StringBuffer();
    private String answer = "";

    private JLabel label = new JLabel();
    private JTextField textField = new JTextField();
    private MyJButton buttonZero = new MyJButton("0");
    private MyJButton buttonOne = new MyJButton("1");
    private MyJButton buttonTwo = new MyJButton("2");
    private MyJButton buttonThree = new MyJButton("3");
    private MyJButton buttonFour = new MyJButton("4");
    private MyJButton buttonFive = new MyJButton("5");
    private MyJButton buttonSix = new MyJButton("6");
    private MyJButton buttonSeven = new MyJButton("7");
    private MyJButton buttonEight = new MyJButton("8");
    private MyJButton buttonNine = new MyJButton("9");
    private MyJButton buttonDot = new MyJButton(".");
    private MyJButton buttonClear = new MyJButton("C");
    private MyJButton buttonDelete = new MyJButton("DEL");
    private MyJButton buttonEqual = new MyJButton("=");
    private MyJButton buttonMul = new MyJButton("*");
    private MyJButton buttonDiv = new MyJButton("/");
    private MyJButton buttonPlus = new MyJButton("+");
    private MyJButton buttonMinus = new MyJButton("-");
    private MyJButton leftBracket = new MyJButton("(");
    private MyJButton rightBracket = new MyJButton(")");
    private MyJButton modulo = new MyJButton("%");

    Calculator() {
        prepareGUI();
        addComponents();
        addActionEvent();
    }

    public void prepareGUI() {
        //Setting properties of JFrame(Window)
        frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setSize(300, 490);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addComponents() {
        //Label (TOP)
        label.setBounds(250, 0, 50, 50);
        label.setForeground(Color.white);
        frame.add(label);

        //Setting property of text field
        textField.setBounds(10, 40, 270, 40);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(textField);

        //Setting property of button 7
        buttonSeven.setBounds(10, 230, 60, 40);
        buttonSeven.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(buttonSeven);

        // Setting property of button 8
        buttonEight.setBounds(80, 230, 60, 40);
        buttonEight.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(buttonEight);

        //Setting property of button 9
        buttonNine.setBounds(150, 230, 60, 40);
        buttonNine.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(buttonNine);

        //Setting property of button 4
        buttonFour.setBounds(10, 290, 60, 40);
        buttonFour.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(buttonFour);

        //Setting property of button 5
        buttonFive.setBounds(80, 290, 60, 40);
        buttonFive.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(buttonFive);

        //Setting property of button 6
        buttonSix.setBounds(150, 290, 60, 40);
        buttonSix.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(buttonSix);

        //Setting property of button 1
        buttonOne.setBounds(10, 350, 60, 40);
        buttonOne.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(buttonOne);

        //Setting property of button 2
        buttonTwo.setBounds(80, 350, 60, 40);
        buttonTwo.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(buttonTwo);

        //Setting property of button 3
        buttonThree.setBounds(150, 350, 60, 40);
        buttonThree.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(buttonThree);

        //Setting property of dot button
        buttonDot.setBounds(150, 410, 60, 40);
        buttonDot.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(buttonDot);

        //Setting property of button 0
        buttonZero.setBounds(10, 410, 130, 40);
        buttonZero.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(buttonZero);

        //Setting property of button =
        buttonEqual.setBounds(220, 350, 60, 100);
        buttonEqual.setFont(new Font("Arial", Font.BOLD, 20));
        buttonEqual.setBackground(Color.green);
        frame.add(buttonEqual);

        //Setting property of button /
        buttonDiv.setBounds(220, 110, 60, 40);
        buttonDiv.setFont(new Font("Arial", Font.BOLD, 20));
        buttonDiv.setBackground(new Color(239, 188, 2));
        frame.add(buttonDiv);

        //Setting property of button square %
        modulo.setBounds(10, 170, 60, 40);
        modulo.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(modulo);

        //Setting property of button *
        buttonMul.setBounds(220, 230, 60, 40);
        buttonMul.setFont(new Font("Arial", Font.BOLD, 20));
        buttonMul.setBackground(new Color(239, 188, 2));
        frame.add(buttonMul);

        //Setting property of button -
        buttonMinus.setBounds(220, 170, 60, 40);
        buttonMinus.setFont(new Font("Arial", Font.BOLD, 20));
        buttonMinus.setBackground(new Color(239, 188, 2));
        frame.add(buttonMinus);

        //Setting property of button +
        buttonPlus.setBounds(220, 290, 60, 40);
        buttonPlus.setFont(new Font("Arial", Font.BOLD, 20));
        buttonPlus.setBackground(new Color(239, 188, 2));
        frame.add(buttonPlus);

        //Setting property of button left bracket
        leftBracket.setBounds(80, 170, 60, 40);
        leftBracket.setFont(new Font("Arial", Font.BOLD, 15));
        frame.add(leftBracket);

        //Setting property of right bracket button
        rightBracket.setBounds(150, 170, 60, 40);
        rightBracket.setFont(new Font("Arial", Font.BOLD, 15));
        frame.add(rightBracket);

        //Setting property of delete button
        buttonDelete.setBounds(150, 110, 60, 40);
        buttonDelete.setFont(new Font("Arial", Font.BOLD, 12));
        buttonDelete.setBackground(Color.white);
        buttonDelete.setForeground(Color.black);
        frame.add(buttonDelete);

        //Setting property of clear button
        buttonClear.setBounds(80, 110, 60, 40);
        buttonClear.setFont(new Font("Arial", Font.BOLD, 12));
        buttonClear.setBackground(Color.black);
        buttonClear.setForeground(Color.black);
        frame.add(buttonClear);
    }

    private void addActionEvent() {
        //Registering ActionListener to buttons
        buttonClear.addActionListener(this);
        buttonDelete.addActionListener(this);
        buttonDiv.addActionListener(this);
        leftBracket.addActionListener(this);
        rightBracket.addActionListener(this);
        modulo.addActionListener(this);
        buttonMinus.addActionListener(this);
        buttonSeven.addActionListener(this);
        buttonEight.addActionListener(this);
        buttonNine.addActionListener(this);
        buttonMul.addActionListener(this);
        buttonFour.addActionListener(this);
        buttonFive.addActionListener(this);
        buttonSix.addActionListener(this);
        buttonPlus.addActionListener(this);
        buttonOne.addActionListener(this);
        buttonTwo.addActionListener(this);
        buttonThree.addActionListener(this);
        buttonEqual.addActionListener(this);
        buttonZero.addActionListener(this);
        buttonDot.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        MyJButton btn = (MyJButton) source;

        if (source == buttonClear) {
            //Clearing texts of label and text field
            operator = "";
            lastString.setLength(0);
            exp.setLength(0);
            textField.setText("");
        }
        else if (source == buttonDelete) {
            //Setting functionality for delete button
            if (exp.length() > 0) {
                System.out.println(exp+" Length "+exp.length());
                exp.deleteCharAt(exp.length()-1);
                textField.setText(exp.toString());
            }
        }
        else if (source == buttonEqual) {
            System.out.println(exp);
            if(answer.equals(exp.toString())) {
                exp.append(operator).append(lastString.toString());
                System.out.println("Here: "+exp);
                System.out.println("Last String: "+lastString);
            }
            try {
                Evaluate();
            } catch (Exception ex) {
                exp.setLength(0);
                operator="";
                lastString.setLength(0);
                textField.setText(exp.toString());
            }
        }
        else {
            // All other button works here
            setExp(btn.getValue());
        }
    }

    // To make conditions simple
    private ArrayList<Character> symbols = new ArrayList<>(Arrays.asList('+', '-', '*', '/', '%', '.'));
    private ArrayList<Character> minusPair = new ArrayList<>(Arrays.asList('*','/', '%', '.'));
    private void setExp(String txt) {
        if(exp.length() > 0) {
            // To allow minus pair combos like 1*-1
            if(minusPair.contains(exp.toString().charAt(exp.length()-1)) && txt.charAt(0)=='-') {}

            // To prevent operators come together and un-necessary 000000
            else if ((symbols.contains(exp.toString().charAt(exp.length()-1)) && symbols.contains(txt.toString().charAt(0))) || (exp.length() == 1 && (exp.toString().charAt(exp.length()-1))=='0' && txt.equals("0")))
            {
                return;
            }
        }

        // Symbol Backup
        if(symbols.contains(txt.charAt(0))) {
            System.out.println("Third Condition before: "+lastString);
            operator = txt;
            lastString.setLength(0);
            System.out.println("Third Condition after: "+lastString);
        }
        else {
            lastString.append(txt);
        }
        exp.append(txt);
        textField.setText(exp.toString());
    }

    //Evaluating using JS eval method using ScriptEngineManager
    private void Evaluate() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println("In evaluate function for Check: "+exp);
        Object obj = engine.eval(exp.toString());
        exp.setLength(0);
        answer = obj.toString();
        exp.append(answer);
        textField.setText(exp.toString());
    }


    public static void main(String[] args) {
        new Calculator();
    }
}
