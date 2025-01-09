package sinhVienDangO;

import view.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordView extends JFrame {
    private JPasswordField passwordField;
    private JButton confirmButton, backButton;

    public PasswordView() {
        setTitle("Admin Login");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel chính với hình nền
        JPanel backgroundPanel = new JPanel() {
            ImageIcon backgroundImage = new ImageIcon("src/img/hinhanhpassword.png");
            Image scaledImage = backgroundImage.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(scaledImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Back Button Panel
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topLeftPanel.setOpaque(false);

        ImageIcon backIcon = new ImageIcon("src/img/arrow-back-icon.png"); // Đường dẫn đến file hình ảnh
        Image scaledBackIcon = backIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Điều chỉnh kích thước
        backButton = new JButton(backIcon);
        backButton.setIcon(new ImageIcon(scaledBackIcon));
        backButton.setText(null);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng cửa sổ PasswordView
                Home home = new Home(); // Tạo cửa sổ Home
                home.setVisible(true); // Hiển thị cửa sổ Home
            }
        });


        topLeftPanel.add(backButton);
        backgroundPanel.add(topLeftPanel, BorderLayout.NORTH);

        // Main content in the center
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new GridBagLayout());
        mainContentPanel.setOpaque(false);

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
        mainContentPanel.add(passwordLabel, gbc);

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
        mainContentPanel.add(passwordPanel, gbc);

        // Confirm Button
        confirmButton = new JButton("Xác nhận");
        confirmButton.setFont(new Font("Inter", Font.BOLD, 20));
        confirmButton.setForeground(Color.BLACK);
        confirmButton.setBackground(Color.WHITE);
        gbc.gridy = 2;
        mainContentPanel.add(confirmButton, gbc);

        backgroundPanel.add(mainContentPanel, BorderLayout.CENTER);

        add(backgroundPanel);
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
