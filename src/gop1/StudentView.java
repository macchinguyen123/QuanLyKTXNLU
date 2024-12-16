package gop1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StudentView extends JFrame {
    private JButton btnStudentList;
    private JButton btnRegisteredStudents;
    private JButton btnBack; // Back button
    private JMenuItem menuExit;
    private JMenuItem menuManage;

    public StudentView() {

        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


//        studentList = new JList<>();
//        studentList.setPreferredSize(new Dimension(400, 300));
//        mainPanel.add(new JScrollPane(studentList));

        // Set background image
        ImageIcon originalIcon = new ImageIcon("src/img/hinhanh.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        JLabel backgroundImage = new JLabel(new ImageIcon(scaledImage));
        backgroundImage.setBounds(0, 0, 800, 500);

        // Create buttons
        btnStudentList = new JButton("Danh sách Sinh Viên Đang Ở");
        btnStudentList.setBounds(50, 200, 320, 60);
        btnStudentList.setBackground(new Color(0, 51, 153));
        btnStudentList.setForeground(Color.WHITE);
        btnStudentList.setFont(new Font("Arial", Font.BOLD, 16));

        btnRegisteredStudents = new JButton("Danh Sách Sinh Viên Đăng Ký");
        btnRegisteredStudents.setBounds(430, 200, 320, 60);
        btnRegisteredStudents.setBackground(new Color(0, 51, 153));
        btnRegisteredStudents.setForeground(Color.WHITE);
        btnRegisteredStudents.setFont(new Font("Arial", Font.BOLD, 16));

        // Add Back button
        btnBack = new JButton("Quay Về");
        btnBack.setBounds(20, 400, 100, 40);
        btnBack.setBackground(new Color(153, 0, 0));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to background image
        backgroundImage.add(btnStudentList);
        backgroundImage.add(btnRegisteredStudents);
        backgroundImage.add(btnBack);

        // Create menu bar with "Exit" and "Quản Lý Sinh Viên"
        JMenuBar menuBar = new JMenuBar();

        JMenu exitMenu = new JMenu("Exit");
        menuExit = new JMenuItem("Exit");
        menuExit.setFont(new Font("Inter", Font.BOLD, 16));
        exitMenu.add(menuExit);
        menuBar.add(exitMenu);

        JMenu manageMenu = new JMenu("Quản Lý");
        menuManage = new JMenuItem("Quản Lý Sinh Viên");
        menuManage.setFont(new Font("Inter", Font.BOLD, 16));
        manageMenu.add(menuManage);
        menuBar.add(manageMenu);

        setJMenuBar(menuBar);
        add(backgroundImage);
    }


    public JButton getBtnStudentList() {
        return btnStudentList;
    }

    public JButton getBtnRegisteredStudents() {
        return btnRegisteredStudents;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public JMenuItem getMenuExit() {
        return menuExit;
    }

    public JMenuItem getMenuManage() {
        return menuManage;
    }

    // Method to set the exit action listener
    public void setExitMenuItemListener(ActionListener listener) {
        menuExit.addActionListener(listener);
    }

    // Method to set the manage action listener
    public void setManageMenuItemListener(ActionListener listener) {
        menuManage.addActionListener(listener);
    }
}
