package sinhVienDangO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentView extends JFrame {
    private JButton btnStudentList;
    private JButton btnRegisteredStudents;
    private JButton btnBack; // Back button
    private JMenuItem menuExit;
    private JMenuItem menuManage;

    public StudentView() {

        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


//        studentList = new JList<>();
//        studentList.setPreferredSize(new Dimension(400, 300));
//        mainPanel.add(new JScrollPane(studentList));

        // Set background image
        ImageIcon originalIcon = new ImageIcon("src/img/hinhanh.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundImage = new JLabel(new ImageIcon(scaledImage));
        backgroundImage.setBounds(0, 0, getWidth(), getHeight());

        // Create buttons
        // Create buttons
        ImageIcon icon = new ImageIcon("src/img/vo.jpg"); // Đường dẫn đến hình ảnh
        Image img = icon.getImage().getScaledInstance(380, 120, Image.SCALE_SMOOTH); // Resize ảnh để vừa nút
        ImageIcon resizedIcon = new ImageIcon(img);
        btnStudentList = new JButton("Danh sách Sinh Viên Đang Ở", resizedIcon);
        btnStudentList.setBounds(60, 180, 380, 162);
        btnStudentList.setBackground(new Color(0, 51, 153)); // Không cần nếu ảnh bao phủ toàn bộ
        btnStudentList.setForeground(Color.WHITE); // Màu chữ
        btnStudentList.setFont(new Font("Arial", Font.BOLD, 16));
        btnStudentList.setHorizontalTextPosition(SwingConstants.CENTER); // Đặt text vào giữa theo chiều ngang
        btnStudentList.setVerticalTextPosition(SwingConstants.BOTTOM);   // Đặt text vào giữa theo chiều dọc
        btnStudentList.setIconTextGap(0); // Loại bỏ khoảng cách giữa text và ảnh

        ImageIcon icon1 = new ImageIcon("src/img/cong.jpg"); // Đường dẫn đến hình ảnh
        Image img1 = icon1.getImage().getScaledInstance(380, 120, Image.SCALE_SMOOTH); // Resize ảnh để vừa nút
        ImageIcon resizedIcon1 = new ImageIcon(img1);
        btnRegisteredStudents = new JButton("Danh Sách Sinh Viên Đăng Ký", resizedIcon1);
        btnRegisteredStudents.setBounds(480, 180, 380, 162);
        btnRegisteredStudents.setBackground(new Color(0, 51, 153));
        btnRegisteredStudents.setForeground(Color.WHITE);
        btnRegisteredStudents.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegisteredStudents.setHorizontalTextPosition(SwingConstants.CENTER); // Đặt text vào giữa theo chiều ngang
        btnRegisteredStudents.setVerticalTextPosition(SwingConstants.BOTTOM);   // Đặt text vào giữa theo chiều dọc
        btnRegisteredStudents.setIconTextGap(0); // Loại bỏ khoảng cách giữa text và ảnh

        // Add Back button
        btnBack = new JButton("Quay Về");
        btnBack.setBounds(45, 600, 120, 50);
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

}
