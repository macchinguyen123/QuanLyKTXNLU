package sinhVienDangO;

import quanLyPhong.DormitoryDataManager;
import quanLyPhong.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateInforView extends JFrame {
    JScrollBar s2;
    JLabel labelName, labelGender, labelBirthYear, labelID, labelPhone, labelAddress, labelDe, labelRoom, labelDorm, labelCardID, labelNation, policyArea;
    JTextField fieldName, fieldBY, fieldID, fieldPhone, fieldAddress, fieldCardID;
    JCheckBox martyrs, poorHousehold;
    JComboBox cbDe, cbGender, cbNation, cbDorm, cbRoom;
    JButton btnUpdate, btnBack;
    StudentListView studentListView;
    StudentController studentController;
    Student currentStudent;

    public UpdateInforView(StudentListView studentListView, StudentController studentController, Student currentStudent) {
        this.studentListView = studentListView;
        this.studentController = studentController;
        this.currentStudent = currentStudent;
        setTitle("Cập nhật thông tin");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        martyrs = new JCheckBox("Con liệt sĩ, thương binh, bệnh binh");
        poorHousehold = new JCheckBox("Gia đình đặc biệt khó khăn");
        martyrs.setBounds(50, 50, 400, 30);
        poorHousehold.setBounds(50, 90, 400, 30);

        // Tạo ButtonGroup để nhóm các JCheckBox
        ButtonGroup group = new ButtonGroup();
        group.add(martyrs);
        group.add(poorHousehold);

        String[] department = {"Kinh tế", "Quản lý đất đai", "Thú y", "Nông học", "Công nghệ thông tin", "Công nghệ thực phẩm", "Công nghệ sinh học", "Lâm nghiệp", "Ngôn ngữ anh"};
        cbDe = new JComboBox(department);

        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setBounds(300, 620, 150, 40); // Dịch xuống và đặt ở giữa
        btnUpdate.setFont(new Font("Inter", Font.BOLD, 16));

        // Nút quay lại
        btnBack = new JButton("Quay lại");
        btnBack.setBounds(500, 620, 150, 40); // Dịch xuống và đặt ở giữa
        btnBack.setFont(new Font("Inter", Font.BOLD, 16));
//        btnBack.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
//                studentListView.setVisible(true);
//            }
//        });
//        btnBack.addActionListener(e -> {
//            StudentListView listView = StudentListView.getInstance();
//            listView.setController(studentController); // Đảm bảo đồng bộ danh sách
//            listView.setVisible(true);
//           setVisible(false); // Đóng giao diện hiện tại
//        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Làm mới danh sách sinh viên trong StudentListView
                if (studentListView != null && studentListView.getController() != null) {
                    List<Student> students = studentListView.getController().getStudents();
                    studentListView.updateStudentList(students);
                }
                setVisible(false);
                studentListView.setVisible(true);
            }
        });



        s2 = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 200);

        JPanel panel = new JPanel();

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        labelName = new JLabel("Họ và tên: ");
        fieldName = new JTextField(10);
        labelGender = new JLabel("Giới tính: ");
        String[] gender = {"Nam", "Nữ"};
        cbGender = new JComboBox(gender);
        labelBirthYear = new JLabel("Ngày sinh (dd/MM/yyyy): ");
        fieldBY = new JTextField(10);
        labelID = new JLabel("Mã số sinh viên: ");
        fieldID = new JTextField(10);
        labelPhone = new JLabel("Số điện thoại: ");
        fieldPhone = new JTextField(10);
        labelAddress = new JLabel("Hộ khẩu thường trú: ");
        fieldAddress = new JTextField(10);
        labelDe = new JLabel("Khoa: ");
        cbDe = new JComboBox(department);
        labelRoom = new JLabel("Phòng: ");
        cbRoom = new JComboBox();

        labelDorm = new JLabel("Cư xá: ");
        String[] dorm = {"A", "B", "C", "D", "E", "F"};
        cbDorm = new JComboBox(dorm);
        cbDorm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDormitory = (String) cbDorm.getSelectedItem();
                System.out.println("Cư xá được chọn: " + selectedDormitory);  // Kiểm tra xem cư xá có đúng không
                updateRoomComboBox(selectedDormitory); // Cập nhật danh sách phòng dựa trên cư xá
            }
        });


        labelCardID = new JLabel("CCCD / CMND: ");
        fieldCardID = new JTextField(10);
        labelNation = new JLabel("Dân tộc: ");
        String[] nation = {"Kinh", "Hoa", "Ê đê", "Mông", "Thái", "Khác"};
        cbNation = new JComboBox(nation);
        policyArea = new JLabel("Diện chính sách: ");

        // Điều chỉnh kích thước các thành phần
        Font labelFont = new Font("Inter", Font.BOLD, 16); // Tăng kích thước chữ cho label
        Font fieldFont = new Font("Inter", Font.PLAIN, 16); // Tăng kích thước chữ cho field

        martyrs.setBounds(50, 50, 400, 30);
        martyrs.setFont(labelFont);

        poorHousehold.setBounds(50, 90, 400, 30);
        poorHousehold.setFont(labelFont);

        labelName.setBounds(50, 140, 150, 30);
        labelName.setFont(labelFont);
        fieldName.setBounds(220, 140, 250, 30);
        fieldName.setFont(fieldFont);

        labelGender.setBounds(50, 180, 150, 30);
        labelGender.setFont(labelFont);
        cbGender.setBounds(220, 180, 120, 30);
        cbGender.setFont(fieldFont);

        labelBirthYear.setBounds(50, 220, 200, 30);
        labelBirthYear.setFont(labelFont);
        fieldBY.setBounds(220, 220, 250, 30);
        fieldBY.setFont(fieldFont);

        labelID.setBounds(50, 260, 150, 30);
        labelID.setFont(labelFont);
        fieldID.setBounds(220, 260, 250, 30);
        fieldID.setFont(fieldFont);

        labelPhone.setBounds(50, 300, 150, 30);
        labelPhone.setFont(labelFont);
        fieldPhone.setBounds(220, 300, 250, 30);
        fieldPhone.setFont(fieldFont);

        labelAddress.setBounds(50, 340, 200, 30);
        labelAddress.setFont(labelFont);
        fieldAddress.setBounds(220, 340, 250, 30);
        fieldAddress.setFont(fieldFont);

        labelDe.setBounds(50, 380, 150, 30);
        labelDe.setFont(labelFont);
        cbDe.setBounds(220, 380, 250, 30);
        cbDe.setFont(fieldFont);

        labelDorm.setBounds(50, 420, 150, 30);
        labelDorm.setFont(labelFont);
        cbDorm.setBounds(220, 420, 120, 30);
        cbDorm.setFont(fieldFont);

        labelRoom.setBounds(370, 420, 100, 30);
        labelRoom.setFont(labelFont);
        cbRoom.setBounds(470, 420, 120, 30);
        cbRoom.setFont(fieldFont);

        labelCardID.setBounds(50, 460, 150, 30);
        labelCardID.setFont(labelFont);
        fieldCardID.setBounds(220, 460, 250, 30);
        fieldCardID.setFont(fieldFont);

        labelNation.setBounds(50, 500, 150, 30);
        labelNation.setFont(labelFont);
        cbNation.setBounds(220, 500, 250, 30);
        cbNation.setFont(fieldFont);

        policyArea.setBounds(50, 540, 200, 30);
        policyArea.setFont(labelFont);


        // Đặt Layout (Horizontal Group)
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(labelName)
                                        .addComponent(labelGender)
                                        .addComponent(labelID)
                                        .addComponent(labelAddress)
                                        .addComponent(labelDe)
                                        .addComponent(labelDorm)
                                        .addComponent(labelCardID)
                                        .addComponent(labelNation)
                                        .addComponent(policyArea))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(fieldName)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbGender)
                                                .addComponent(labelBirthYear)
                                                .addComponent(fieldBY))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(fieldID)
                                                .addComponent(labelPhone)
                                                .addComponent(fieldPhone))
                                        .addComponent(fieldAddress)
                                        .addComponent(cbDe)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbDorm)
                                                .addGap(30)
                                                .addComponent(labelRoom)
                                                .addComponent(cbRoom))
                                        .addComponent(fieldCardID)
                                        .addComponent(cbNation)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(martyrs)
                                                .addComponent(poorHousehold))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(350)
                                .addComponent(btnUpdate) // Nút cập nhật
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED) // Khoảng cách giữa hai nút
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addGap(350) // Để căn giữa
                                .addComponent(btnBack) // Nút quay lại
                                .addGap(0, 0, Short.MAX_VALUE)) // Để căn giữa
        );

        // Đặt Layout (Vertical Group)
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelName)
                                .addComponent(fieldName))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelGender)
                                .addComponent(cbGender)
                                .addComponent(labelBirthYear)
                                .addComponent(fieldBY))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelID)
                                .addComponent(fieldID)
                                .addComponent(labelPhone)
                                .addComponent(fieldPhone))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelAddress)
                                .addComponent(fieldAddress))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelDe)
                                .addComponent(cbDe))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelDorm)
                                .addComponent(cbDorm)
                                .addComponent(labelRoom)
                                .addComponent(cbRoom))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelCardID)
                                .addComponent(fieldCardID))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelNation)
                                .addComponent(cbNation))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(policyArea))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(martyrs)
                                .addComponent(poorHousehold))
                        .addGap(30) // Khoảng cách trước nút
                        .addComponent(btnUpdate) // Nút cập nhật
                        .addGap(5) // Khoảng cách trước nút quay lại
                        .addComponent(btnBack) // Nút quay lại
                        .addGap(30) // Khoảng cách dưới cùng
        );

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudentInfor();
            }
        });

        add(panel, BorderLayout.CENTER);
    }

    public void setStudentDetails(Student student) {
        fieldName.setText(student.getTen());
        fieldBY.setText(student.getNamSinh());
        fieldID.setText(student.getMssv());
        cbGender.setSelectedItem(student.getGioiTinh());
        fieldPhone.setText(student.getSđt());
        fieldAddress.setText(student.getDiaChi());
        cbDe.setSelectedItem(student.getKhoa());
        cbDorm.setSelectedItem(student.getCuXa());
        fieldCardID.setText(student.getIdCCCD());
        cbNation.setSelectedItem(student.getDanToc());

        // Cập nhật phòng sau khi cư xá thay đổi
        updateRoomComboBox(student.getCuXa());
        cbRoom.setSelectedItem(student.getPhong()); // Chọn phòng của sinh viên

        // Cập nhật checkbox chính sách
        martyrs.setSelected(student.isMartyrs());
        poorHousehold.setSelected(student.isPoorHousehold());
    }

    /**
     * Phương thức cập nhật JComboBox phòng dựa trên cư xá được chọn
     */
    private void updateRoomComboBox(String dormitory) {
        cbRoom.removeAllItems(); // Xóa tất cả các mục hiện tại
        List<Room> rooms = DormitoryDataManager.getInstance().getRoomsByDormitory(dormitory); // Lấy danh sách phòng

        if (rooms != null) {
            for (Room room : rooms) {
                System.out.println("Thêm phòng vào JComboBox: " + room.getRoomNumber()); // Kiểm tra việc thêm phòng
                cbRoom.addItem(room.getRoomNumber()); // Thêm từng phòng vào JComboBox
            }
        } else {
            System.out.println("Không có phòng nào cho cư xá: " + dormitory); // Kiểm tra nếu không có phòng
        }
    }

    private void updateStudentInfor(){
        String name = fieldName.getText();
        String birthYear = fieldBY.getText();
        String id = fieldID.getText();
        String gender = cbGender.getSelectedItem().toString();
        String phone = fieldPhone.getText();
        String address = fieldAddress.getText();
        String department = cbDe.getSelectedItem().toString();
        String room = cbRoom.getSelectedItem().toString();
        String dorm = cbDorm.getSelectedItem().toString();
        String cardID = fieldCardID.getText();
        String nation = cbNation.getSelectedItem().toString();

        boolean isMartyrs = martyrs.isSelected();
        boolean isPoorHousehold = poorHousehold.isSelected();

        currentStudent.setTen(name);
        currentStudent.setMssv(id);
        currentStudent.setGioiTinh(gender);
        currentStudent.setKhoa(department);
        currentStudent.setNamSinh(birthYear);
        currentStudent.setCuXa(dorm);
        currentStudent.setPhong(room);
        currentStudent.setDiaChi(address);
        currentStudent.setIdCCCD(cardID);
        currentStudent.setDanToc(nation);

        studentController.updateStudent(currentStudent);

//        studentListView.updateStudentList(studentController.getStudents());
        // Đồng bộ hóa lại danh sách sinh viên trong StudentListView
//         if (studentListView != null) {
//        studentListView.updateStudentList(studentController.getStudents()); // Cập nhật bảng với danh sách mới
//    }
        JOptionPane.showMessageDialog(UpdateInforView.this, "Thông tin đã được cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        setVisible(false);
        studentListView.updateStudentList(studentController.getStudents());
        studentListView.setVisible(true);
    }




}
