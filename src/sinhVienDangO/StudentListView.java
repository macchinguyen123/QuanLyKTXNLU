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
import java.util.Map;

public class StudentListView extends JFrame {
    private JTextField txtSearch;
    private JButton btnSearch, btnBack;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    private JMenuItem menuExit, menuManage, roomManage;

    private StudentController controller;
    private Map<String, Student> studentMap;
    private static StudentListView instance;

    public StudentListView() {
        setTitle("Quản Lý Sinh Viên");
        setSize(900, 700);
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

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(new Color(0, 0, 0, 0));
        txtSearch = new JTextField(25);
        btnSearch = new JButton("Tìm kiếm");
        btnSearch.setPreferredSize(new Dimension(120, 35));
        btnSearch.setFont(new Font("Inter", Font.BOLD, 14));
        txtSearch.setPreferredSize(new Dimension(300, 35));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        String[] columnNames = {"STT", "Tên", "MSSV", "Giới tính", "Khoa", "Năm sinh", "Cư xá", "Phòng", "Địa chỉ", "CCCD"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);

        studentTable.setRowHeight(25); // Đặt chiều cao mỗi hàng
        studentTable.getTableHeader().setFont(new Font("Inter", Font.BOLD, 14));
        studentTable.getTableHeader().setPreferredSize(new Dimension(0, 30));
        studentTable.setFont(new Font("Inter", Font.PLAIN, 14));
        studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        studentTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setPreferredSize(new Dimension(850, 400));

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
        btnBack.setPreferredSize(new Dimension(120, 35));
        btnBack.setBounds(20, 400, 100, 40);
        btnBack.setBackground(new Color(153, 0, 0));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Inter", Font.BOLD, 14));
        backPanel.add(btnBack);
        backPanel.setOpaque(false);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearchAction();
            }
        });


        ImageIcon originalIcon = new ImageIcon("src/img/hinhanh.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(900, 700, Image.SCALE_SMOOTH);
        JLabel backgroundImage = new JLabel(new ImageIcon(scaledImage));
        backgroundImage.setBounds(0, 0, 900, 700);
        backgroundImage.setLayout(new BorderLayout());

        backgroundImage.add(searchPanel, BorderLayout.NORTH);
        backgroundImage.add(scrollPane, BorderLayout.CENTER);
        backgroundImage.add(backPanel, BorderLayout.SOUTH);

        add(backgroundImage);
    }

    // Phương thức để lấy instance duy nhất của StudentListView
    public static StudentListView getInstance() {
        if (instance == null) {
            instance = new StudentListView();
        }
        return instance;
    }

    public StudentController getController() {
        return controller;
    }

    public void setController(StudentController controller) {
        this.controller = controller;
        updateStudentList(controller.getStudents());
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
        studentMap = new HashMap<>();
        int stt = 1;
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    stt++,                        // STT
                    student.getTen(),             // Tên
                    student.getMssv(),            // Mã số
                    student.getGioiTinh(),        // Giới tính
                    student.getKhoa(),            // Khoa
                    student.getNamSinh(),         // Năm sinh
                    student.getCuXa(),            // Cư xá
                    student.getPhong(),           // Phòng
                    student.getDiaChi(),          // Địa chỉ
                    student.getIdCCCD(),          // CCCD
            });
            studentMap.put(student.getMssv(), student);
        }
        // Làm mới bảng
        tableModel.fireTableDataChanged();
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
        List<Student> result = new ArrayList<>();
        Student foundStu = studentMap.get(mssv.trim());
        if(foundStu != null) {
            result.add(foundStu);
        }
        return result;
    }

    private  void handleSearchAction(){
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

public void removeStudentFromTable(String studentID) {
    boolean found = false; // Biến để kiểm tra xem sinh viên có được tìm thấy không

    // Duyệt qua bảng để tìm và xóa sinh viên theo MSSV
    for (int i = 0; i < tableModel.getRowCount(); i++) {
        String id = tableModel.getValueAt(i, 2).toString(); // Lấy MSSV từ cột 2
        if (id.equals(studentID)) {
            // Xóa dòng khỏi JTable
            tableModel.removeRow(i);
            found = true; // Đánh dấu rằng sinh viên đã được tìm thấy

            // Xóa sinh viên khỏi Map
            studentMap.remove(studentID);

            // Xóa sinh viên khỏi danh sách trong StudentController
            if (controller != null) {
                controller.removeStudentById(studentID);
            }

            // Cập nhật lại thứ tự (STT) trong bảng
            updateTableSTT();

            // Debug: In danh sách sinh viên còn lại
            System.out.println("Danh sách sau khi xóa: " + controller.getStudents());
            break; // Thoát khỏi vòng lặp sau khi xóa
        }
    }

    // Thông báo cho người dùng
    if (found) {
        JOptionPane.showMessageDialog(this, "Sinh viên đã được xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên để xóa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }
}


    public void updateTableSTT() {
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            // Cập nhật lại giá trị STT theo thứ tự hàng
            tableModel.setValueAt(row + 1, row, 0); // Cột 0 là cột STT
        }
    }


}