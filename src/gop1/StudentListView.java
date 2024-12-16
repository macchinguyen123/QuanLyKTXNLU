package gop1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentListView extends JFrame {
    private JTextField txtSearch;
    private JButton btnSearch, btnBack;
    private JList<String> studentList;
    private DefaultListModel<String> listModel;

    private JMenuItem menuExit, menuManage, roomManage;

    private StudentController controller;

    public StudentListView() {
        setTitle("Quản Lý Sinh Viên");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu exitMenu = new JMenu("Exit");
        menuExit = new JMenuItem("Exit");
        menuExit.setFont(new Font("Inter", Font.BOLD, 16));
        exitMenu.add(menuExit);
        menuBar.add(exitMenu);

        JMenu manageMenu = new JMenu("Quản Lý");
        menuManage = new JMenuItem("Quản Lý Sinh Viên");
        menuManage.setFont(new Font("Inter", Font.BOLD, 16));
        roomManage = new JMenuItem("Quản lý Phòng");
        roomManage.setFont(new Font("Inter", Font.BOLD, 16));
        manageMenu.add(menuManage);
        manageMenu.add(roomManage);
        menuBar.add(manageMenu);

        setJMenuBar(menuBar);

         JLabel titleLabel = new JLabel("Danh sách sinh viên", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(new Color(0, 0, 0, 0));
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Tìm kiếm");
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        listModel = new DefaultListModel<>();
        studentList = new JList<>(listModel);
        studentList.setBackground(Color.LIGHT_GRAY);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(studentList);

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

        backgroundImage.add(searchPanel, BorderLayout.NORTH);
        backgroundImage.add(scrollPane, BorderLayout.CENTER);
        backgroundImage.add(backPanel, BorderLayout.SOUTH);

        add(backgroundImage);
    }

    public JTextField getSearchField() {
        return txtSearch;
    }

    public JButton getSearchButton() {
        return btnSearch;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public JList<String> getStudentList() {
        return studentList;
    }

    public void addSearchActionListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }

    public JButton getBackButton() {
        return btnBack;
    }

    public void addBackButtonListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }

    public void addMenuActionListener(ActionListener listener) {
        roomManage.addActionListener(listener);
    }

    public String getSearchQuery() {
        return txtSearch.getText();
    }

    public void updateStudentList(List<Student> students) {
        listModel.clear();
        for (Student student : students) {
            listModel.addElement(student.toString());
        }
    }
}
