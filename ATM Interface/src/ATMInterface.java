import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passField;

    public LoginFrame() {
        setTitle("ATM Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel userLabel = new JLabel("User ID:");
        userField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        passField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        // Add action listener for the login button
        loginButton.addActionListener(new LoginButtonListener());

        // Layout setup
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        add(panel);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userID = userField.getText();
            String password = new String(passField.getPassword());

            // Placeholder for actual authentication
            if (userID.equals("user") && password.equals("pass")) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Login Successful!");
                dispose();
                new ATMFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "Invalid User ID or Password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
