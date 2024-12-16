package gop1;

import javax.swing.*;
import java.awt.*;

public class PasswordView extends JFrame {
    private JPasswordField passwordField;
    private JButton confirmButton;

    public PasswordView() {
        setTitle("Admin Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(800, 500));
        mainPanel.setBackground(new Color(161, 210, 224, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Password Label
        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setFont(new Font("Inter", Font.PLAIN, 12));
        passwordLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(passwordLabel, gbc);

        // Password Field Panel
        JPanel passwordPanel = new JPanel();
        passwordPanel.setPreferredSize(new Dimension(318, 69));
        passwordPanel.setBackground(new Color(217, 217, 217));
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        passwordPanel.setLayout(new BorderLayout());

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(298, 49));
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setFont(new Font("Inter", Font.PLAIN, 16));
        passwordField.setEchoChar('*');
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        gbc.gridy = 1;
        mainPanel.add(passwordPanel, gbc);

        // Confirm Button
        confirmButton = new JButton("Xác nhận");
        confirmButton.setFont(new Font("Inter", Font.BOLD, 20));
        confirmButton.setForeground(Color.BLACK);
        confirmButton.setBackground(Color.WHITE);
        gbc.gridy = 2;
        mainPanel.add(confirmButton, gbc);

        add(mainPanel);
    }

    public String getPasswordInput() {
        return new String(passwordField.getPassword());
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
