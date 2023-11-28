import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton, clearButton;
    private double num1, num2, result;
    private String operator;

    public CalculatorGUI() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display
        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new NumberButtonClickListener());
        }

        // Operation buttons
        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        for (int i = 0; i < 4; i++) {
            operationButtons[i].addActionListener(new OperationButtonClickListener());
        }

        // Equals and Clear buttons
        equalsButton = new JButton("=");
        equalsButton.addActionListener(new EqualsButtonClickListener());

        clearButton = new JButton("C");
        clearButton.addActionListener(new ClearButtonClickListener());

        // Panel for number buttons
        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(4, 3));
        for (int i = 1; i <= 9; i++) {
            numberPanel.add(numberButtons[i]);
        }
        numberPanel.add(clearButton);
        numberPanel.add(numberButtons[0]);
        numberPanel.add(equalsButton);

        // Panel for operation buttons
        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new GridLayout(4, 1));
        for (int i = 0; i < 4; i++) {
            operationPanel.add(operationButtons[i]);
        }

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(numberPanel, BorderLayout.CENTER);
        mainPanel.add(operationPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class NumberButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            display.setText(display.getText() + source.getText());
        }
    }

    private class OperationButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            num1 = Double.parseDouble(display.getText());
            operator = source.getText();
            display.setText("");
        }
    }

    private class EqualsButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
        }
    }

    private class ClearButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI());
    }
}
