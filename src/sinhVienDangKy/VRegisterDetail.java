package sinhVienDangKy;

import javax.swing.*;
import java.awt.*;

public class VRegisterDetail extends JFrame {

    private JTextField[] textFields;
    private JComboBox<String> genderComboBox;
    private VRegister view;
    private JButton buttonQuayLai, buttonXacNhan, buttonHuy;

    public VRegisterDetail(String[] data, VRegister parentFrame) {
        this.view = parentFrame;
        setTitle("Chi Tiết Sinh Viên");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(900, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(250, 250, 250));

        JLabel titleLabel = new JLabel("TRƯỜNG ĐẠI HỌC NÔNG LÂM TPHCM", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(250, 10, 400, 30);
        add(titleLabel);

        // Labels và TextFields
        String[] labels = {
                "HỌ VÀ TÊN", "GIỚI TÍNH", "NGÀY SINH", "MÃ SỐ SINH VIÊN",
                "SỐ ĐIỆN THOẠI", "HỘ KHẨU THƯỜNG TRÚ", "KHOA", "PHÒNG",
                "CƯ XÁ", "CCCD / CMND", "DÂN TỘC", "DIỆN CHÍNH SÁCH"
        };

        textFields = new JTextField[labels.length];

        int xLabelLeft = 50, xFieldLeft = 180;
        int xLabelRight = 500, xFieldRight = 630;
        int yStart = 60, yGap = 50, widthLabel = 120, widthField = 250, height = 30;

        JCheckBox checkBox1 = null, checkBox2 = null;

        for (int i = 0; i < labels.length; i++) {
            if (i == 1) { // "GIỚI TÍNH" ngang hàng với "NGÀY SINH"
                JLabel label = new JLabel(labels[i]);
                label.setBounds(xLabelLeft, yStart, widthLabel, height);
                add(label);

                genderComboBox = new JComboBox<>(new String[]{"Nam", "Nữ"});
                genderComboBox.setBounds(xFieldLeft, yStart, widthField, height);
                genderComboBox.setEnabled(false);
                add(genderComboBox);

                JLabel birthLabel = new JLabel(labels[2]);
                birthLabel.setBounds(xLabelRight, yStart, widthLabel, height);
                add(birthLabel);

                JTextField birthField = new JTextField();
                birthField.setBounds(xFieldRight, yStart, widthField, height);
                birthField.setEditable(false);
                add(birthField);
                textFields[2] = birthField; // Gán cho "NGÀY SINH"
            } else if (i == 3) { // "MÃ SỐ SINH VIÊN" ngang hàng với "SỐ ĐIỆN THOẠI"
                JLabel label = new JLabel(labels[i]);
                label.setBounds(xLabelLeft, yStart, widthLabel, height);
                add(label);

                JTextField idField = new JTextField();
                idField.setBounds(xFieldLeft, yStart, widthField, height);
                idField.setEditable(false);
                add(idField);
                textFields[i] = idField;

                JLabel phoneLabel = new JLabel(labels[4]);
                phoneLabel.setBounds(xLabelRight, yStart, widthLabel, height);
                add(phoneLabel);

                JTextField phoneField = new JTextField();
                phoneField.setBounds(xFieldRight, yStart, widthField, height);
                phoneField.setEditable(false);
                add(phoneField);
                textFields[4] = phoneField; // Gán cho "SỐ ĐIỆN THOẠI"
            } else if (i == 5) { // "HỘ KHẨU THƯỜNG TRÚ" ngang hàng với "KHOA"
                JLabel addressLabel = new JLabel(labels[i]);
                addressLabel.setBounds(xLabelLeft, yStart, widthLabel, height);
                add(addressLabel);

                JTextField addressField = new JTextField();
                addressField.setBounds(xFieldLeft, yStart, widthField, height);
                addressField.setEditable(false);
                add(addressField);
                textFields[i] = addressField;

                JLabel facultyLabel = new JLabel(labels[6]);
                facultyLabel.setBounds(xLabelRight, yStart, widthLabel, height);
                add(facultyLabel);

                JTextField facultyField = new JTextField();
                facultyField.setBounds(xFieldRight, yStart, widthField, height);
                facultyField.setEditable(false);
                add(facultyField);
                textFields[6] = facultyField; // Gán cho "KHOA"
            } else if (i == 7) { // "PHÒNG" ngang hàng với "CƯ XÁ"
                JLabel roomLabel = new JLabel(labels[i]);
                roomLabel.setBounds(xLabelLeft, yStart, widthLabel, height);
                add(roomLabel);

                JTextField roomField = new JTextField();
                roomField.setBounds(xFieldLeft, yStart, widthField, height);
                roomField.setEditable(false);
                add(roomField);
                textFields[i] = roomField;

                JLabel dormLabel = new JLabel(labels[8]);
                dormLabel.setBounds(xLabelRight, yStart, widthLabel, height);
                add(dormLabel);

                JTextField dormField = new JTextField();
                dormField.setBounds(xFieldRight, yStart, widthField, height);
                dormField.setEditable(false);
                add(dormField);
                textFields[8] = dormField; // Gán cho "CƯ XÁ"
            } else if (i == 11) { // CheckBox và Label "DIỆN CHÍNH SÁCH"
                JLabel policyLabel = new JLabel(labels[i]);
                policyLabel.setBounds(xLabelLeft, yStart, widthLabel, height);
                add(policyLabel);

                checkBox1 = new JCheckBox("Con liệt sĩ, thương binh, bệnh binh");
                checkBox2 = new JCheckBox("Gia đình đặc biệt khó khăn");
                checkBox1.setBounds(xFieldLeft, yStart, widthField + 200, height);
                checkBox2.setBounds(xFieldLeft, yStart + 30, widthField + 200, height);
                checkBox1.setEnabled(false);
                checkBox2.setEnabled(false);
                add(checkBox1);
                add(checkBox2);
                yStart += 30; // Tăng chiều cao vì có 2 dòng
            } else if (i != 2 && i != 4 && i != 6 && i != 8) { // TextField cho các trường còn lại
                JLabel label = new JLabel(labels[i]);
                label.setBounds(xLabelLeft, yStart, widthLabel, height);
                add(label);

                JTextField textField = new JTextField();
                textField.setBounds(xFieldLeft, yStart, widthField, height);
                textField.setEditable(false);
                add(textField);
                textFields[i] = textField;
            }

            // Tăng vị trí y cho dòng mới (trừ khi là các trường ngang hàng)
            if (i != 1 && i != 3 && i != 5 && i != 7) {
                yStart += yGap;
            }
        }

        // Nút Quay lại, Xác nhận, và Hủy
        buttonQuayLai = new JButton("Quay Lại");
        buttonQuayLai.setBounds(580, yStart + 20, 120, 40);
        buttonQuayLai.setBackground(new Color(30, 144, 255));
        buttonQuayLai.setForeground(Color.WHITE);

        buttonXacNhan = new JButton("Xác Nhận");
        buttonXacNhan.setBounds(180, yStart + 20, 120, 40);
        buttonXacNhan.setBackground(Color.GREEN);
        buttonXacNhan.setForeground(Color.WHITE);

        buttonHuy = new JButton("Từ Chối");
        buttonHuy.setBounds(380, yStart + 20, 120, 40);
        buttonHuy.setBackground(Color.RED);
        buttonHuy.setForeground(Color.WHITE);

        add(buttonQuayLai);
        add(buttonXacNhan);
        add(buttonHuy);

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

    public JButton getButtonHuy() {
        return buttonHuy;
    }

    public JButton getButtonXacNhan() {
        return buttonXacNhan;
    }

    public JButton getButtonQuayLai() {
        return buttonQuayLai;
    }
    @Override
    public boolean isShowing() {
        return super.isShowing(); // Gọi trực tiếp phương thức isShowing() từ JFrame
    }

}
