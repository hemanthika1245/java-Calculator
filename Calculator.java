import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JTextField display;


    Calculator() {
        // Frame settings
        setTitle("Calculator");
        setSize(300, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== COLORS =====
        Color bgColor = new Color(30, 30, 30);     
        Color btnColor = new Color(60, 60, 60);    
        Color textColor = Color.WHITE;
        Color operatorColor = new Color(255, 149, 0); 
        // ===== DISPLAY =====
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(new Color(0,200,0));
        display.setForeground(textColor);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        // ===== BUTTON PANEL =====
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(bgColor);

        String[] buttons = {
            "7","8","9","/",
            "4","5","6","*",
            "1","2","3","-",
            "C","0","=","+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setFocusPainted(false);
            btn.addActionListener(this);

            // Button colors
            if (text.matches("[+\\-*/=]")) {
                btn.setBackground(operatorColor);
                btn.setForeground(Color.BLACK);
            } else if (text.equals("C")) {
                btn.setBackground(new Color(200, 60, 60)); 
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(btnColor);
                btn.setForeground(textColor);
            }

            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        getContentPane().setBackground(bgColor);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        // Append numbers and operators
        if (input.matches("[0-9+\\-*/]")) {
            display.setText(display.getText() + input);
        }

        // Equal
        else if (input.equals("=")) {
            try {
                double result = evaluate(display.getText());
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }

        // Clear
        else if (input.equals("C")) {
            display.setText("");
        }
    }

    // Expression evaluation
    double evaluate(String expr) {
        double result = 0;
        char operator = '+';
        String num = "";

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (Character.isDigit(ch)) {
                num += ch;
            } else {
                double n = Double.parseDouble(num);

                switch (operator) {
                    case '+': result += n; break;
                    case '-': result -= n; break;
                    case '*': result *= n; break;
                    case '/': result /= n; break;
                }

                operator = ch;
                num = "";
            }
        }

        double n = Double.parseDouble(num);
        switch (operator) {
            case '+': result += n; break;
            case '-': result -= n; break;
            case '*': result *= n; break;
            case '/': result /= n; break;
        }

        return result;
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
