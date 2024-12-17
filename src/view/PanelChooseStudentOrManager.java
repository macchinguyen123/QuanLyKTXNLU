package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelChooseStudentOrManager extends JPanel {
    JButton btnChooseStudent, btnChooseManager;
    JPanel panelStudent, panelManager;
    JLabel labelStudent, labelManager, labelLogin;
    Image background;

    public PanelChooseStudentOrManager(JPanel cardPanel, CardLayout cardLayout) {
        background = new ImageIcon("src/img/backroundKTX.jpg").getImage();
        this.setLayout(new BorderLayout());

        // Student panel
        panelStudent = new JPanel();
        panelStudent.setPreferredSize(new Dimension(100, 200));
        panelStudent.setOpaque(false);

        btnChooseStudent = new JButton(new ImageIcon("src/img/student1.jpg"));
        btnChooseStudent.setBounds(20, 20, 100, 100);
        btnChooseStudent.setActionCommand("student");
        btnChooseStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the Student Panel
                cardLayout.show(cardPanel, "studentPanel");
            }
        });

        panelStudent.add(btnChooseStudent);
        labelStudent = new JLabel("Student", JLabel.CENTER);
        labelStudent.setForeground(Color.WHITE);
        panelStudent.add(labelStudent);

        // Manager panel
        panelManager = new JPanel();
        panelManager.setPreferredSize(new Dimension(100, 200));
        panelManager.setOpaque(false);

        btnChooseManager = new JButton(new ImageIcon("src/img/manager1.jpg"));
        btnChooseManager.setBounds(20, 20, 100, 100);
        btnChooseManager.setActionCommand("manager");
        panelManager.add(btnChooseManager);
        labelManager = new JLabel("Manager", JLabel.CENTER);
        labelManager.setForeground(Color.WHITE);
        panelManager.add(labelManager);

        // Add panels to this container
        this.add(panelStudent, BorderLayout.WEST);
        this.add(panelManager, BorderLayout.EAST);

        // Label Login
        labelLogin = new JLabel("Login", JLabel.CENTER);
        labelLogin.setFont(new Font("Arial", Font.BOLD, 25));
        labelLogin.setForeground(Color.BLACK);
        this.add(labelLogin, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
