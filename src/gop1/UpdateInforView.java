package gop1;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UpdateInforView extends JFrame {
    JScrollBar s2;
    JLabel labelSchoolName, labelName, labelGender, labelBirthYear, labelID, labelPhone, labelAddress, labelDe, labelRoom, labelDorm, labelCardID, labelNation, policyArea;
    JTextField fieldName, fieldBY, fieldID, fieldPhone, fieldAddress, fieldRoom, fieldCardID;
    JCheckBox martyrs, poorHousehold, disability, ethnic, notSubject;
    JComboBox cbDe, cbGender, cbNation, cbDorm;
    JButton btnUpdate;

    public UpdateInforView() {
        setTitle("Quản Lý Sinh Viên");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        martyrs = new JCheckBox("Con liệt sĩ, thương binh, bệnh binh");
        poorHousehold = new JCheckBox("Hộ nghèo, gia đình khó khăn");
        disability = new JCheckBox("Khuyết tật, bệnh hiểm nghèo");
        ethnic = new JCheckBox("Dân tộc thiểu số");
        notSubject = new JCheckBox("Không thuộc các đối tượng trên");

        String[] department = {"Kinh tế", "Quản lý đất đai", "Thú y", "Nông học", "Công nghệ thông tin", "Công nghệ thực phẩm", "Công nghệ sinh học", "Lâm nghiệp", "Ngôn ngữ anh"};
        cbDe = new JComboBox(department);

        btnUpdate = new JButton("Cập nhật");

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
        labelBirthYear = new JLabel("Ngày sinh: ");
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
        fieldRoom = new JTextField(10);
        labelDorm = new JLabel("Cư xá: ");
        String[] dorm = {"Cư xá A", "Cư xá B", "Cư xá C", "Cư xá D", "Cư xá E", "Cư xá F"};
        cbDorm = new JComboBox(dorm);
        labelCardID = new JLabel("CCCD / CMND: ");
        fieldCardID = new JTextField(10);
        labelNation = new JLabel("Dân tộc: ");
        String[] nation = {"Kinh", "Hoa", "Ê đê", "Mông", "Thái", "Khác"};
        cbNation = new JComboBox(nation);
        policyArea = new JLabel("Diện chính sách: ");


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
                                        .addComponent(labelRoom)
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
                                                .addComponent(fieldRoom)
                                                .addComponent(labelDorm)
                                                .addComponent(cbDorm))
                                        .addComponent(fieldCardID)
                                        .addComponent(cbNation)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(martyrs)
                                                .addComponent(poorHousehold)
                                                .addComponent(disability)
                                                .addComponent(ethnic)
                                                .addComponent(notSubject))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(350) // Căn giữa nút
                                .addComponent(btnUpdate))
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
                                .addComponent(labelRoom)
                                .addComponent(fieldRoom)
                                .addComponent(labelDorm)
                                .addComponent(cbDorm))
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
                                .addComponent(poorHousehold)
                                .addComponent(disability)
                                .addComponent(ethnic)
                                .addComponent(notSubject))
                        .addGap(30) // Khoảng cách trước nút
                        .addComponent(btnUpdate)
        );

        add(panel, BorderLayout.CENTER);
//        add(s2, BorderLayout.EAST);
    }
}
