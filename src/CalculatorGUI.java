/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {

    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton, eqButton, dotButton, delButton, ceButton, cButton;

    private double num1, num2, result;
    private char operator;

    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Text Field
        textField = new JTextField();
        textField.setBounds(20, 20, 260, 53);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);
        add(textField);

        // Number Buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].addActionListener(new NumberButtonListener());
        }

        // Function Buttons
        functionButtons = new JButton[8];
        for (int i = 0; i < 8; i++) {
            functionButtons[i] = new JButton();
            functionButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
        }

        // Setting text for function buttons
        functionButtons[0].setText("Del");
        functionButtons[1].setText("CE");
        functionButtons[2].setText("C");
        functionButtons[3].setText("/");
        functionButtons[4].setText("*");
        functionButtons[5].setText("-");
        functionButtons[6].setText("+");
        functionButtons[7].setText("=");

        // Setting bounds for buttons
        setButtonBounds();

        // Adding buttons to frame
        for (JButton button : numberButtons) {
            add(button);
        }
        for (JButton button : functionButtons) {
            add(button);
        }

        // Adding ActionListener to function buttons
        for (JButton button : functionButtons) {
            button.addActionListener(new FunctionButtonListener());
        }

        // Adding ActionListener to dot button separately
        dotButton = new JButton(".");
        dotButton.setFont(new Font("Arial", Font.PLAIN, 18));
        dotButton.setBounds(180, 400, 60, 40);
        dotButton.addActionListener(new DotButtonListener());
        add(dotButton);
    }

    private void setButtonBounds() {
        int buttonWidth = 60;
        int buttonHeight = 40;
        int x = 20;
        int y = 100;

        // Setting bounds for number buttons
        for (int i = 1; i <= 9; i++) {
            numberButtons[i].setBounds(x, y, buttonWidth, buttonHeight);
            x += 80;
            if (i % 3 == 0) {
                x = 20;
                y += 60;
            }
        }

        // Button '0'
        numberButtons[0].setBounds(100, 400, buttonWidth, buttonHeight);

        // Setting bounds for function buttons
        x = 260;
        y = 100;
        for (int i = 0; i < 8; i++) {
            functionButtons[i].setBounds(x, y, buttonWidth, buttonHeight);
            y += 60;
        }
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            textField.setText(textField.getText() + source.getText());
        }
    }

    private class FunctionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            switch (buttonText) {
                case "Del":
                    String currentText = textField.getText();
                    if (!currentText.isEmpty()) {
                        textField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                    break;

                case "CE":
                    textField.setText("");
                    break;

                case "C":
                    textField.setText("");
                    num1 = num2 = result = 0;
                    operator = '\0';
                    break;

                case "=":
                    num2 = Double.parseDouble(textField.getText());
                    result = performOperation();
                    textField.setText(String.valueOf(result));
                    num1 = result;
                    num2 = 0;
                    operator = '\0';
                    break;

                default:
                    operator = buttonText.charAt(0);
                    if (!textField.getText().isEmpty()) {
                        num1 = Double.parseDouble(textField.getText());
                        textField.setText("");
                    }
            }
        }
    }

    private class DotButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String currentText = textField.getText();
            if (!currentText.contains(".")) {
                textField.setText(currentText + ".");
            }
        }
    }

    private double performOperation() {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
            default:
                return num2;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
