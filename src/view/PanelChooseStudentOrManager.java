package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelChooseStudentOrManager extends JPanel {
    JButton btnChooseStudent, btnChooseManager;
    JPanel panelStudent, panelManager, cards;
    JLabel labelStudent, labelManager, textStudent, textManager, labelLogin;
    Image backround;

    public PanelChooseStudentOrManager() {

        backround = new ImageIcon("src/img/backroundKTX.jpg").getImage();
        this.setLayout(new BorderLayout());



        // panel student
//        panelStudent = new JPanel(new GridLayout(3, 1));
        panelStudent = new JPanel();
        panelStudent.setPreferredSize(new Dimension(100, 200));
        panelStudent.setOpaque(false);
        btnChooseStudent = new JButton(new ImageIcon("src/img/student1.jpg"));
        btnChooseStudent.setBounds(20, 20, 100, 100);
//
        panelStudent.add(btnChooseStudent, BorderLayout.CENTER);
//        panelStudent.
        panelStudent.add(labelStudent = new JLabel("Student", JLabel.CENTER));
        //panel manager
        panelManager = new JPanel();
        panelManager.setLayout(new GridLayout(3, 1));
        panelManager.setOpaque(false);
        panelManager.setPreferredSize(new Dimension(100, 200));
        btnChooseManager = new JButton(new ImageIcon("src/img/manager1.jpg"));
        btnChooseManager.setBounds(20, 20, 100, 100);
        panelManager.add(btnChooseManager, BorderLayout.CENTER);
        panelManager.add(labelStudent = new JLabel("Manager", JLabel.CENTER));

        // add panel student va panel manager vao panel choose option
        this.add(panelStudent, BorderLayout.WEST);
        this.add(panelManager, BorderLayout.EAST);

        //label login


        labelLogin = new JLabel("Login", JLabel.CENTER);
        labelLogin.setBounds(100, 20, 600, 60);
        labelLogin.setFont(new Font("Arial", Font.BOLD, 25));
        labelLogin.setForeground(new Color(59, 185, 94));

        this.add(labelLogin, BorderLayout.NORTH);


    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backround,0,0,getWidth(),getHeight(),this);
    }
}



