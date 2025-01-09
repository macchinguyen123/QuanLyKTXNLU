package sinhVienDangO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BiographyView extends JFrame {
    private JMenuItem menuExit, menuManage, roomManage;
    JMenu manageMenu, exitMenu;
    JButton btnUpdate, btnBack;
    JPanel mainPanel, inforStudentPanel;
    JLabel labelName, labelBY, labelID, labelGender, labelPhoneNumber, labelAddress, labelDorm, labelRoom, labelIDCard;
    StudentListView parentView;

    public BiographyView(StudentListView parentView) {
        this.parentView = parentView;
        setTitle("Thông tin cá nhân của sinh viên");
        setSize(900, 700);
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

        // Tạo một panel chính với layout BoxLayout
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        inforStudentPanel = new JPanel();
        inforStudentPanel.setPreferredSize(new Dimension(600, 400));
        inforStudentPanel.setLayout(new BoxLayout(inforStudentPanel, BoxLayout.PAGE_AXIS));
        inforStudentPanel.setBackground(new Color(173, 216, 230)); // Màu nền giống như trong hình

        // Tạo các JLabel cho thông tin sinh viên
        labelName = new JLabel("Họ và Tên: ");
        labelBY = new JLabel("Năm sinh: ");
        labelID = new JLabel("MSSV: ");
        labelGender = new JLabel("Giới tính: ");
        labelPhoneNumber = new JLabel("SĐT: ");
        labelAddress = new JLabel("HKTT: ");
        labelDorm = new JLabel("Cư xá: ");
        labelRoom = new JLabel("Phòng: ");
        labelIDCard = new JLabel("CCCD: ");

        // Thiết lập font và màu sắc cho các JLabel
        JLabel[] labels = {labelName, labelBY, labelID, labelGender, labelPhoneNumber, labelAddress, labelDorm, labelRoom, labelIDCard};
        for (JLabel label : labels) {
            label.setForeground(Color.BLACK);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa
            inforStudentPanel.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các nhãn
            inforStudentPanel.add(label);
        }

        // Nút cập nhật
        btnUpdate = new JButton("Cập nhật thông tin");
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 18));
        btnUpdate.setForeground(Color.BLACK);
        btnUpdate.setBackground(Color.GREEN);
        btnUpdate.setPreferredSize(new Dimension(200, 40));
        btnUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy ID sinh viên từ view hiện tại
                String studentID = labelID.getText().replace("MSSV: ", "").trim(); // Trích xuất ID từ label

                // Lấy thông tin sinh viên từ controller
                StudentController controller = new StudentController(parentView);
                StudentListView studentListView = new StudentListView();
                studentListView.setController(controller);
                Student student = studentListView.getController().getStudentById(studentID);
                if (student != null) {
                    // Tạo một instance của UpdateInforView
                    UpdateInforView updateView = new UpdateInforView(parentView, controller, student);
                    updateView.setStudentDetails(student); // Đặt thông tin sinh viên vào UpdateInforView
                    updateView.setVisible(true); // Hiển thị UpdateInforView
                    dispose(); // Đóng BiographyView
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên với ID: " + studentID);
                }
            }
        });
        inforStudentPanel.add(Box.createVerticalStrut(20));
        inforStudentPanel.add(btnUpdate);

        // Nút quay về
        btnBack = new JButton("Quay về");
        btnBack.setBackground(new Color(153, 0, 0));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.BOLD, 18));
        btnBack.setPreferredSize(new Dimension(200, 40));
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                parentView.setVisible(true);
            }
        });
        inforStudentPanel.add(Box.createVerticalStrut(20));
        inforStudentPanel.add(btnBack);

        // Hình nền
        ImageIcon originalIcon = new ImageIcon("src/img/hinhanh.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(900, 700, Image.SCALE_SMOOTH);
        JLabel backgroundImage = new JLabel(new ImageIcon(scaledImage));
        backgroundImage.setBounds(0, 0, 900, 700);
        backgroundImage.setLayout(new GridBagLayout()); // Đặt layout là GridBagLayout để căn giữa

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Cột giữa
        gbc.gridy = 0; // Hàng giữa
        gbc.anchor = GridBagConstraints.CENTER; // Căn bên trái
        backgroundImage.add(inforStudentPanel, gbc);

        add(backgroundImage);
    }

    public void setStudentDetails(String name, String birthYear, String id, String gender,
                                  String faculty, String address, String dorm, String room, String idCard) {
        labelName.setText("Họ và Tên: " + name);
        labelBY.setText("Năm sinh: " + birthYear);
        labelID.setText("MSSV: " + id);
        labelGender.setText("Giới tính: " + gender);
        labelPhoneNumber.setText("Khoa: " + faculty);
        labelAddress.setText("HKTT: " + address);
        labelDorm.setText("Cư xá: " + dorm);
        labelRoom.setText("Phòng: " + room);
        labelIDCard.setText("CCCD: " + idCard);
    }

    public void displayStudentDetails(Student student) {
        labelName.setText("Họ và Tên: " + student.getTen());
        labelBY.setText("Năm sinh: " + student.getNamSinh());
        labelID.setText("MSSV: " + student.getMssv());
        labelGender.setText("Giới tính: " + student.getGioiTinh());
        labelPhoneNumber.setText("SĐT: " + student.getSđt());
        labelAddress.setText("HKTT: " + student.getDiaChi());
        labelDorm.setText("Cư xá: " + student.getCuXa());
        labelRoom.setText("Phòng: " + student.getPhong());
        labelIDCard.setText("CCCD: " + student.getIdCCCD());
    }
}
