package view;

import sinhVienDangO.Controller;
import quanLyPhong.Model;
import sinhVienDangO.PasswordView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelChooseStudentOrManager extends JPanel {
    JButton btnChooseStudent, btnChooseManager, btnExit;
    JLabel labelStudent, labelManager, labelLogin;
    Image background;

    public PanelChooseStudentOrManager(JPanel cardPanel, CardLayout cardLayout) {
        // Load background image
        background = new ImageIcon("src/img/backroundKTX.jpg").getImage();
        this.setLayout(new BorderLayout());

        // Panel for login label (with padding to adjust position)
        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setOpaque(false);

        labelLogin = new JLabel("Login", JLabel.CENTER);
        labelLogin.setFont(new Font("Arial", Font.BOLD, 25));
        labelLogin.setForeground(Color.BLACK);
        loginPanel.add(labelLogin, BorderLayout.CENTER);

        // Add padding/margin below the "Login" label
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.SOUTH);

        this.add(loginPanel, BorderLayout.NORTH);

        // Create main center panel to hold buttons
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // Make it transparent for the background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 40, 20, 40); // Add spacing between components

        // Student panel with button and label
        JPanel panelStudent = new JPanel(new BorderLayout());
        panelStudent.setOpaque(false);

        btnChooseStudent = new JButton(new ImageIcon("src/img/student1.jpg"));
        btnChooseStudent.setPreferredSize(new Dimension(100, 100));
        btnChooseStudent.setActionCommand("student");
        btnChooseStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the Student Panel
                cardLayout.show(cardPanel, "studentPanel");
                disable();
            }
        });
        panelStudent.add(btnChooseStudent, BorderLayout.CENTER);

        labelStudent = new JLabel("Student", JLabel.CENTER);
        labelStudent.setFont(new Font("Arial", Font.BOLD, 20)); // Increased font size
        labelStudent.setForeground(Color.WHITE);
        panelStudent.add(labelStudent, BorderLayout.SOUTH);

        // Manager panel with button and label
        JPanel panelManager = new JPanel(new BorderLayout());
        panelManager.setOpaque(false);

        btnChooseManager = new JButton(new ImageIcon("src/img/manager1.jpg"));
        btnChooseManager.setPreferredSize(new Dimension(100, 100));
        btnChooseManager.setActionCommand("manager");
        btnChooseManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open manager login window
                Model combinedModel = new Model();
                PasswordView passwordView = new PasswordView();
                new Controller(combinedModel, passwordView);
                passwordView.setVisible(true);
            }
        });
        panelManager.add(btnChooseManager, BorderLayout.CENTER);

        labelManager = new JLabel("Manager", JLabel.CENTER);
        labelManager.setFont(new Font("Arial", Font.BOLD, 20)); // Increased font size
        labelManager.setForeground(Color.WHITE);
        panelManager.add(labelManager, BorderLayout.SOUTH);

        // Add panels to the center panel with proper positioning
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST; // Align to the left
        centerPanel.add(panelStudent, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Align to the right
        centerPanel.add(panelManager, gbc);

        // Add center panel to the main layout
        this.add(centerPanel, BorderLayout.CENTER);

        // Add Exit button at the bottom-right corner
        btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Arial", Font.BOLD, 14));
        btnExit.setForeground(Color.WHITE);
        btnExit.setBackground(Color.RED);
        btnExit.setFocusPainted(false);
        btnExit.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnExit.setPreferredSize(new Dimension(80, 30)); // Smaller size
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the program
                System.exit(0);
            }
        });

        JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomRightPanel.setOpaque(false); // Transparent for background
        bottomRightPanel.add(btnExit);

        this.add(bottomRightPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
