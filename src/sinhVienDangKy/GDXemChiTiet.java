package sinhVienDangKy;

import javax.swing.*;
import java.awt.*;

public class GDXemChiTiet extends JPanel {

    private JTextField[] textFields;
    private JComboBox<String> genderComboBox;

    public GDXemChiTiet(String[] data) {
        setLayout(null);
        setBackground(new Color(250, 250, 250));

        JLabel titleLabel = new JLabel("TRƯỜNG ĐẠI HỌC NÔNG LÂM TPHCM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(150, 10, 400, 30);
        add(titleLabel);

        // Labels và TextFields
        String[] labels = {
                "HỌ VÀ TÊN", "GIỚI TÍNH", "NGÀY SINH", "MÃ SỐ SINH VIÊN",
                "SỐ ĐIỆN THOẠI", "HỘ KHẨU THƯỜNG TRÚ", "KHOA", "PHÒNG",
                "CƯ XÁ", "CCCD / CMND", "DÂN TỘC", "DIỆN CHÍNH SÁCH"
        };

        textFields = new JTextField[labels.length];

        int xLabel = 50, xField = 200, y = 60, widthLabel = 120, widthField = 200, height = 30;

        JCheckBox checkBox1 = null, checkBox2 = null;

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(xLabel, y, widthLabel, height);
            add(label);

            if (i == 1) { // ComboBox cho "Giới Tính"
                genderComboBox = new JComboBox<>(new String[]{"Nam", "Nữ"});
                genderComboBox.setBounds(xField, y, widthField, height);
                genderComboBox.setEnabled(false); // Vô hiệu hóa
                add(genderComboBox);
            } else if (i == 11) { // CheckBox
                checkBox1 = new JCheckBox("Con liệt sĩ, thương binh, bệnh binh");
                checkBox2 = new JCheckBox("Gia đình đặc biệt khó khăn");
                checkBox1.setBounds(xField, y, widthField + 200, height);
                checkBox2.setBounds(xField, y + 30, widthField + 200, height);
                checkBox1.setEnabled(false); // Vô hiệu hóa
                checkBox2.setEnabled(false); // Vô hiệu hóa
                add(checkBox1);
                add(checkBox2);
                y += 30; // Tăng chiều cao vì có 2 dòng
            } else { // TextField cho các trường còn lại
                JTextField textField = new JTextField();
                textField.setBounds(xField, y, widthField, height);
                textField.setEditable(false); // Vô hiệu hóa chỉnh sửa
                add(textField);
                textFields[i] = textField;
            }
            y += 40; // Cách đều các dòng
        }

        // Nút Xác nhận
        JButton updateButton = new JButton("Xác Nhận");
        updateButton.setBounds(100, y, 120, 40);
        updateButton.setBackground(new Color(30, 144, 255));
        updateButton.setForeground(Color.WHITE);
        add(updateButton);

        // Nút Hủy
        JButton cancelButton = new JButton("Hủy");
        cancelButton.setBounds(300, y, 120, 40);
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        add(cancelButton);

        // Điền dữ liệu từ mảng vào các textFields và checkboxes
        fillData(data, checkBox1, checkBox2);
    }

    private void fillData(String[] data, JCheckBox checkBox1, JCheckBox checkBox2) {
        for (int i = 0; i < data.length && i < textFields.length; i++) {
            if (i == 1) { // Xử lý "Giới Tính"
                if ("Nam".equalsIgnoreCase(data[i])) {
                    genderComboBox.setSelectedItem("Nam");
                } else if ("Nữ".equalsIgnoreCase(data[i])) {
                    genderComboBox.setSelectedItem("Nữ");
                }
            } else if (i == 11) { // Xử lý "DIỆN CHÍNH SÁCH"
                if ("Diện 1".equalsIgnoreCase(data[i])) {
                    if (checkBox1 != null) checkBox1.setSelected(true);
                } else if ("Diện 2".equalsIgnoreCase(data[i])) {
                    if (checkBox2 != null) checkBox2.setSelected(true);
                }
            } else if (textFields[i] != null) {
                textFields[i].setText(data[i]);
            }
        }
    }

//    public static void main(String[] args) {
//        // Dữ liệu mẫu
//        MDSVDangKi mdsvDangKi = new MDSVDangKi();
//        String[] sampleData = {
//                "Nguyen Van A", "Nữ", "01/01/2000", "23130341", "0123456789",
//                "123 ABC Street", "Khoa Công Nghệ Thông Tin", "P101", "Ký túc xá A", "123456789",
//                "Kinh", "Diện 1"
//        };
//
//        JFrame frame = new JFrame("Chi Tiết Sinh Viên");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 650);
//        frame.add(new GDXemChiTiet(mdsvDangKi.data1));
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}
