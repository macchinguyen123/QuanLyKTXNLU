package sinhVienDangKy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GDXemChiTiet extends JFrame {

    private JTextField[] textFields;
    private JComboBox<String> genderComboBox;
    private GDSVDangKi view;
    private JButton buttonQuayLai;

    public GDXemChiTiet(String[] data, GDSVDangKi parentFrame) {
        this.view = parentFrame;
        setTitle("Chi Tiết Sinh Viên");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(800, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(250, 250, 250));

        JLabel titleLabel = new JLabel("TRƯỜNG ĐẠI HỌC NÔNG LÂM TPHCM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(250, 10, 400, 30);
        add(titleLabel);

        // Labels và TextFields
        String[] labels = {
                "HỌ VÀ TÊN", "GIỚI TÍNH", "NGÀY SINH", "MÃ SỐ SINH VIÊN",
                "SỐ ĐIỆN THOẠI", "HỘ KHẨU THƯỜNG TRÚ", "KHOA", "PHÒNG",
                "CƯ XÁ", "CCCD / CMND", "DÂN TỘC", "DIỆN CHÍNH SÁCH"
        };

        textFields = new JTextField[labels.length];

        int xLabel = 50, xField = 200, y = 60, widthLabel = 120, widthField = 200, height = 30;
        int gapBetweenFields = 250; // Khoảng cách giữa hai thành phần ngang hàng

        JCheckBox checkBox1 = null, checkBox2 = null;

        for (int i = 0; i < labels.length; i++) {
            if (i == 1) { // "GIỚI TÍNH" ngang hàng với "NGÀY SINH"
                JLabel label = new JLabel(labels[i]);
                label.setBounds(xLabel, y, widthLabel, height);
                add(label);

                genderComboBox = new JComboBox<>(new String[]{"Nam", "Nữ"});
                genderComboBox.setBounds(xField, y, widthField, height);
                genderComboBox.setEnabled(false); // Vô hiệu hóa
                add(genderComboBox);

                JLabel birthLabel = new JLabel(labels[2]);
                birthLabel.setBounds(xField + gapBetweenFields, y, widthLabel, height);
                add(birthLabel);

                JTextField birthField = new JTextField();
                birthField.setBounds(xField + gapBetweenFields + widthLabel, y, widthField, height);
                birthField.setEditable(false);
                add(birthField);
                textFields[2] = birthField; // Gán cho "NGÀY SINH"
            } else if (i == 3) { // "MÃ SỐ SINH VIÊN" ngang hàng với "SỐ ĐIỆN THOẠI"
                JLabel label = new JLabel(labels[i]);
                label.setBounds(xLabel, y, widthLabel, height);
                add(label);

                JTextField idField = new JTextField();
                idField.setBounds(xField, y, widthField, height);
                idField.setEditable(false);
                add(idField);
                textFields[i] = idField;

                JLabel phoneLabel = new JLabel(labels[4]);
                phoneLabel.setBounds(xField + gapBetweenFields, y, widthLabel, height);
                add(phoneLabel);

                JTextField phoneField = new JTextField();
                phoneField.setBounds(xField + gapBetweenFields + widthLabel, y, widthField, height);
                phoneField.setEditable(false);
                add(phoneField);
                textFields[4] = phoneField; // Gán cho "SỐ ĐIỆN THOẠI"
            } else if (i == 5) { // "HỘ KHẨU THƯỜNG TRÚ" ngang hàng với "KHOA"
                JLabel addressLabel = new JLabel(labels[i]);
                addressLabel.setBounds(xLabel, y, widthLabel, height);
                add(addressLabel);

                JTextField addressField = new JTextField();
                addressField.setBounds(xField, y, widthField, height);
                addressField.setEditable(false);
                add(addressField);
                textFields[i] = addressField;

                JLabel facultyLabel = new JLabel(labels[6]);
                facultyLabel.setBounds(xField + gapBetweenFields, y, widthLabel, height);
                add(facultyLabel);

                JTextField facultyField = new JTextField();
                facultyField.setBounds(xField + gapBetweenFields + widthLabel, y, widthField, height);
                facultyField.setEditable(false);
                add(facultyField);
                textFields[6] = facultyField; // Gán cho "KHOA"
            } else if (i == 7) { // "PHÒNG" ngang hàng với "CƯ XÁ"
                JLabel roomLabel = new JLabel(labels[i]);
                roomLabel.setBounds(xLabel, y, widthLabel, height);
                add(roomLabel);

                JTextField roomField = new JTextField();
                roomField.setBounds(xField, y, widthField, height);
                roomField.setEditable(false);
                add(roomField);
                textFields[i] = roomField;

                JLabel dormLabel = new JLabel(labels[8]);
                dormLabel.setBounds(xField + gapBetweenFields, y, widthLabel, height);
                add(dormLabel);

                JTextField dormField = new JTextField();
                dormField.setBounds(xField + gapBetweenFields + widthLabel, y, widthField, height);
                dormField.setEditable(false);
                add(dormField);
                textFields[8] = dormField; // Gán cho "CƯ XÁ"
            } else if (i == 11) { // CheckBox và Label "DIỆN CHÍNH SÁCH"
                JLabel policyLabel = new JLabel(labels[i]);
                policyLabel.setBounds(xLabel, y, widthLabel, height);
                add(policyLabel);

                checkBox1 = new JCheckBox("Con liệt sĩ, thương binh, bệnh binh");
                checkBox2 = new JCheckBox("Gia đình đặc biệt khó khăn");
                checkBox1.setBounds(xField, y, widthField + 200, height);
                checkBox2.setBounds(xField, y + 30, widthField + 200, height);
                checkBox1.setEnabled(false); // Vô hiệu hóa
                checkBox2.setEnabled(false); // Vô hiệu hóa
                add(checkBox1);
                add(checkBox2);
                y += 30; // Tăng chiều cao vì có 2 dòng
            } else if (i != 2 && i != 4 && i != 6 && i != 8) { // TextField cho các trường còn lại
                JLabel label = new JLabel(labels[i]);
                label.setBounds(xLabel, y, widthLabel, height);
                add(label);

                JTextField textField = new JTextField();
                textField.setBounds(xField, y, widthField, height);
                textField.setEditable(false); // Vô hiệu hóa chỉnh sửa
                add(textField);
                textFields[i] = textField;
            }

            // Tăng vị trí y cho dòng mới (trừ khi là các trường ngang hàng)
            if (i != 1 && i != 3 && i != 5 && i != 7) {
                y += 40; // Cách đều các dòng
            }
        }

        // Nút Quay lai
        buttonQuayLai = new JButton("Quay Lại");
        buttonQuayLai.setBounds(350, y, 120, 40);
        buttonQuayLai.setBackground(new Color(30, 144, 255));
        buttonQuayLai.setForeground(Color.WHITE);
        add(buttonQuayLai);

        // Điền dữ liệu từ mảng vào các textFields và checkboxes
        fillData(data, checkBox1, checkBox2);
    }

    private void fillData(String[] data, JCheckBox checkBox1, JCheckBox checkBox2) {
        for (int i = 0; i < data.length && i < textFields.length; i++) {
            if (i == 1) { // Xử lý "GIỚI TÍNH"
                if ("Nam".equalsIgnoreCase(data[i])) {
                    genderComboBox.setSelectedItem("Nam");
                } else if ("Nữ".equalsIgnoreCase(data[i])) {
                    genderComboBox.setSelectedItem("Nữ");
                }
            } else if (i == 11) { // Xử lý "DIỆN CHÍNH SÁCH"
                if ("Có".equalsIgnoreCase(data[i])) {
                    if (checkBox1 != null) checkBox1.setSelected(true);
                } else if ("Không".equalsIgnoreCase(data[i])) {
                    if (checkBox2 != null) checkBox2.setSelected(true);
                }
            } else if (textFields[i] != null) {
                textFields[i].setText(data[i]);
            }
        }
    }

    public JButton getButtonQuayLai() {
        return buttonQuayLai;
    }
}
