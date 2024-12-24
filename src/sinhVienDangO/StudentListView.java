package sinhVienDangO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentListView extends JFrame {
    private JTextField txtSearch;
    private JButton btnSearch, btnBack;
    private JTable studentTable;
    private DefaultTableModel tableModel;

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

        String[] columnNames = {"STT", "Tên", "MSSV", "Giới tính", "Khoa", "Năm sinh", "Cư xá", "Phòng", "Địa chỉ", "CCCD"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        studentTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Chỉ xử lý khi chọn xong
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy dữ liệu từ bảng
                    String name = tableModel.getValueAt(selectedRow, 1).toString();       // Tên
                    String id = tableModel.getValueAt(selectedRow, 2).toString();         // MSSV
                    String gender = tableModel.getValueAt(selectedRow, 3).toString();     // Giới tính
                    String faculty = tableModel.getValueAt(selectedRow, 4).toString();    // Khoa
                    String birthYear = tableModel.getValueAt(selectedRow, 5).toString();  // Năm sinh
                    String dorm = tableModel.getValueAt(selectedRow, 6).toString();       // Cư xá
                    String room = tableModel.getValueAt(selectedRow, 7).toString();       // Phòng
                    String address = tableModel.getValueAt(selectedRow, 8).toString();    // Địa chỉ
                    String idCard = tableModel.getValueAt(selectedRow, 9).toString();     // CCCD

                    // Hiển thị giao diện BiographyView
                    BiographyView bioView = new BiographyView(this);
                    bioView.setStudentDetails(name, birthYear, id, gender, faculty, address, dorm, room, idCard);
                    bioView.setVisible(true);
                    this.dispose();
                }
            }
        });

        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                studentTableMouseClicked(e);
            }
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnBack = new JButton("Quay về");
        btnBack.setBounds(20, 400, 100, 40);
        btnBack.setBackground(new Color(153, 0, 0));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        backPanel.add(btnBack);
        backPanel.setOpaque(false);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentController studentController = new StudentController(StudentListView.this);
                StudentListView studentListView = new StudentListView();
                studentListView.setController(studentController);
                String search = txtSearch.getText().trim();

                if (search.isEmpty()) {
                    JOptionPane.showMessageDialog(StudentListView.this, "Vui lòng nhập mã số sinh viên để tìm kiếm!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                List<Student> result = searchStudentByMSSV(search);

                if (result.isEmpty()) {
                    JOptionPane.showMessageDialog(StudentListView.this, "Không tìm thấy sinh viên với mã số: " + search, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    updateStudentList(studentController.getStudents()); // Hiển thị lại danh sách sinh viên ban đầu
                } else {
                    updateStudentList(result); // Hiển thị danh sách kết quả tìm kiếm
                }
            }
        });


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

    public StudentController getController() {
        return controller;
    }

    public void setController(StudentController controller) {
        this.controller = controller;
    }

    public JTextField getSearchField() {
        return txtSearch;
    }

    public JButton getSearchButton() {
        return btnSearch;
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
        tableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        int stt = 1;
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    stt++,                        // STT
                    student.getTen(),             // Tên
                    student.getMssv(),            // Mã số
                    student.getGioiTinh(),        // Giới tính
                    student.getKhoa(),            // Khoa
                    student.getNamSinh(),        // Năm sinh
                    student.getCuXa(),            // Cư xá
                    student.getPhong(),           // Phòng
                    student.getDiaChi(),          // Địa chỉ
                    student.getIdCCCD(),          // CCCD
            });
        }
    }

    public void onStudentSelected(Student selectedStudent) {
        // Lấy thông tin mới nhất từ StudentController
        Student updatedStudent = controller.getStudentById(selectedStudent.getMssv());
        if (updatedStudent != null) {
            UpdateInforView updateDetailView = new UpdateInforView(this, controller, selectedStudent);
            updateDetailView.setStudentDetails(updatedStudent);
        }
    }

    public void studentTableMouseClicked(MouseEvent e) {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            Student selectedStudent = controller.getStudents().get(selectedRow);
            onStudentSelected(selectedStudent);
        }
    }

    private List<Student> searchStudentByMSSV(String mssv) {
        StudentController studentController = new StudentController(StudentListView.this);
        StudentListView studentListView = new StudentListView();
        studentListView.setController(studentController);
        // Tạo HashMap tạm thời để ánh xạ MSSV với Student
        HashMap<String, Student> studentMap = new HashMap<>();
        for (Student student : studentController.getStudents()) {
            studentMap.put(student.getMssv(), student);
        }

        // Tìm kiếm sinh viên trong HashMap
        List<Student> result = new ArrayList<>();
        Student foundStudent = studentMap.get(mssv.trim());
        if (foundStudent != null) {
            result.add(foundStudent);
        }
        return result;
    }

}