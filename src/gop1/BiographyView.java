package gop1;

import javax.swing.*;
import java.awt.*;

public class BiographyView extends JFrame {
    private JMenuItem menuExit, menuManage, roomManage;
    JMenu manageMenu, exitMenu;
    JButton btnUpdate, btnBack;
    JPanel mainPanel, inforStudentPanel;
    JLabel labelName, labelBY, labelID, labelGender, labelPhoneNumber, labelAddress, labelDorm, labelRoom, labelIDCard;

    public BiographyView() {
        setTitle("Quản Lý Sinh Viên");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JMenuBar menuBar = new JMenuBar();

        exitMenu = new JMenu("Exit");
        menuExit = new JMenuItem("Exit");
        menuExit.setFont(new Font("Inter", Font.BOLD, 16));
        exitMenu.add(menuExit);
        menuBar.add(exitMenu);

        manageMenu = new JMenu("Quản Lý");
        menuManage = new JMenuItem("Quản Lý Sinh Viên");
        menuManage.setFont(new Font("Inter", Font.BOLD, 16));
        roomManage = new JMenuItem("Quản lý Phòng");
        roomManage.setFont(new Font("Inter", Font.BOLD, 16));
        manageMenu.add(menuManage);
        manageMenu.add(roomManage);
        menuBar.add(manageMenu);

        setJMenuBar(menuBar);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        inforStudentPanel = new JPanel();
        inforStudentPanel.setBounds(150, 20, 500, 400);
        inforStudentPanel.setLayout(new BoxLayout(inforStudentPanel, BoxLayout.PAGE_AXIS));
        inforStudentPanel.setBackground(Color.LIGHT_GRAY);

        labelName = new JLabel("Họ và tên: ");
        labelBY = new JLabel("Ngày sinh: ");
        labelID = new JLabel("MSSV: ");
        labelGender = new JLabel("Giới tính: ");
        labelPhoneNumber = new JLabel("SĐT: ");
        labelAddress = new JLabel("HKTT: ");
        labelDorm = new JLabel("Cư xá: ");
        labelRoom = new JLabel("Phòng: ");
        labelIDCard = new JLabel("CCCD: ");


        btnUpdate = new JButton("Cập nhật thông tin");
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 18));
        btnUpdate.setForeground(Color.BLACK);
        btnUpdate.setBackground(Color.GREEN);
        btnUpdate.setPreferredSize(new Dimension(100, 30));
        btnUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);

        inforStudentPanel.add(Box.createVerticalStrut(20));
        JLabel[] labels = {labelName, labelBY, labelID, labelGender, labelPhoneNumber, labelAddress, labelDorm, labelRoom, labelIDCard};
        for (JLabel label : labels) {
            label.setForeground(Color.BLACK);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setAlignmentX(Component.RIGHT_ALIGNMENT);
            inforStudentPanel.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các nhãn
            inforStudentPanel.add(label);
        }

        inforStudentPanel.add(Box.createVerticalStrut(20));
        inforStudentPanel.add(Box.createVerticalStrut(20));

        inforStudentPanel.add(btnUpdate);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnBack = new JButton("Quay về");
        btnBack.setBounds(20, 400, 100, 40);
        btnBack.setBackground(new Color(153, 0, 0));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        backPanel.add(btnBack);
        backPanel.setOpaque(false);


        ImageIcon originalIcon = new ImageIcon("src/img/hinhanh.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        JLabel backgroundImage = new JLabel(new ImageIcon(scaledImage));
        backgroundImage.setBounds(0, 0, 800, 500);
        backgroundImage.setLayout(new BorderLayout());

        backgroundImage.add(backPanel, BorderLayout.SOUTH);

        add(inforStudentPanel, BorderLayout.CENTER);
        add(backgroundImage);
    }
}
