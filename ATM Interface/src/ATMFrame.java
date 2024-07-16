import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ATMFrame extends JFrame {
    private double balance;
    private JTextArea transactionArea;

    public ATMFrame() {
        setTitle("ATM Services");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        balance = 1000.00; // Initial balance

        // Create components
        JButton balanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transactionButton = new JButton("Transaction History");
        transactionArea = new JTextArea(10, 30);
        transactionArea.setEditable(false);

        // Add action listeners for buttons
        balanceButton.addActionListener(new BalanceButtonListener());
        depositButton.addActionListener(new DepositButtonListener());
        withdrawButton.addActionListener(new WithdrawButtonListener());
        transactionButton.addActionListener(new TransactionButtonListener());

        // Layout setup
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(balanceButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(transactionButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(transactionArea), BorderLayout.CENTER);

        add(mainPanel);
    }

    private class BalanceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            transactionArea.append("Current Balance: $" + balance + "\n");
        }
    }

    private class DepositButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(ATMFrame.this, "Enter amount to deposit:");
            if (input != null) {
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0) {
                        balance += amount;
                        transactionArea.append("Deposited: $" + amount + "\n");
                    } else {
                        JOptionPane.showMessageDialog(ATMFrame.this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ATMFrame.this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class WithdrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(ATMFrame.this, "Enter amount to withdraw:");
            if (input != null) {
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0 && amount <= balance) {
                        balance -= amount;
                        transactionArea.append("Withdrew: $" + amount + "\n");
                    } else {
                        JOptionPane.showMessageDialog(ATMFrame.this, "Invalid amount or insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ATMFrame.this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class TransactionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // No additional action needed since transactions are logged in the text area
        }
    }
}
