package view;

import sinhVienDangKy.MDSVDangKi;
import sinhVienDangKy.StudentDataStorage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageFillInformatinDK extends JPanel {
    DefaultTableModel model;
    JTable table;
    String[] data;
    JScrollPane scrollPane;
    JLabel titleLabel;
    JPanel mainPanel;

    private MDSVDangKi tableModel;
    // Lưu trữ các JTextField, JComboBox và JCheckBox
    private java.util.List<JTextField> textFields = new java.util.ArrayList<>();
    private java.util.List<JComboBox<String>> comboBoxes = new java.util.ArrayList<>();
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;

    public PageFillInformatinDK(JPanel cardPanel, CardLayout cardLayout, PageTTCNcuaSVDaO pageTTCN, MDSVDangKi tableModel) {
        this.tableModel = tableModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add title
        titleLabel = new JLabel("TRƯỜNG ĐẠI HỌC NÔNG LÂM TPHCM", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Các trường nhập dữ liệu
        mainPanel.add(createInputField("Họ và tên:"));
        mainPanel.add(createTwoInputFields1("Giới tính:", "Ngày sinh:"));
        mainPanel.add(createTwoInputFields2("Mã số sinh viên:", "Số điện thoại:"));
        mainPanel.add(createInputField("Hộ khẩu thường trú:"));
        mainPanel.add(createInputField("Khoa:"));
        mainPanel.add(createTwoInputFields3("Cư xá:", "Phòng:"));
        mainPanel.add(createInputField("CCCD / CMND:"));
        mainPanel.add(createInputField("Dân tộc:"));

        // Diện chính sách
        JPanel policyPanel = new JPanel();
        policyPanel.setLayout(new BoxLayout(policyPanel, BoxLayout.Y_AXIS));
        policyPanel.setBorder(BorderFactory.createTitledBorder("Diện chính sách"));
        checkBox1 = new JCheckBox("Con liệt sĩ, thương binh, bệnh binh");
        checkBox2 = new JCheckBox("Gia đình đặc biệt khó khăn");
        policyPanel.add(checkBox1);
        policyPanel.add(checkBox2);
        mainPanel.add(policyPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Nút xác nhận
        JButton confirmButton = new JButton("XÁC NHẬN");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBackground(new Color(173, 216, 230));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
                JOptionPane.showMessageDialog(mainPanel, "Đăng ký thành công!");
                pageTTCN.updateInformation(data);
                tableModel.addStudent(data);
            }
        });

        mainPanel.add(confirmButton);

        // Nút quay lại
        JButton backHome = new JButton(new ImageIcon("src/img/iconHome.png"));
        backHome.setForeground(Color.WHITE);
        backHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "studentPanel");
            }
        });
        mainPanel.add(backHome);

        // nut quay lai trang Thong tin chon phong\
        JButton backToPagePrevious = new JButton(new ImageIcon("src/img/arrow-back-icon.png"));
        backToPagePrevious.setForeground(Color.WHITE);
        backToPagePrevious.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToPagePrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"chooseRoom");
            }
        });
        mainPanel.add(backToPagePrevious);

        scrollPane = new JScrollPane(mainPanel);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void saveData() {
        // Tạo mảng data với kích thước phù hợp
        data = new String[textFields.size() + comboBoxes.size() + 2];

        // Lưu dữ liệu từ JTextField
        int index = 0;
        for (JTextField textField : textFields) {
            data[index++] = textField.getText();
        }

        // Lưu dữ liệu từ JComboBox
        for (JComboBox<String> comboBox : comboBoxes) {
            data[index++] = comboBox.getSelectedItem().toString();
        }

        // Lưu dữ liệu từ JCheckBox
        data[index++] = checkBox1.isSelected() ? "Có" : "Không";
        data[index] = checkBox2.isSelected() ? "Có" : "Không";

        // Debug: In mảng data ra console
        for (String item : data) {
            System.out.println(item);
        }
        // Lưu vào StudentDataStorage
        StudentDataStorage storage = StudentDataStorage.getInstance();
        storage.addStudent(data);


    }

    private JPanel createInputField(String labelText) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField();
        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(600, 60));

        // Lưu JTextField vào danh sách
        textFields.add(textField);

        return panel;
    }

    private JPanel createTwoInputFields1(String label1Text, String label2Text) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel label1 = new JLabel(label1Text);
        JComboBox<String> comboBox1 = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});
        JLabel label2 = new JLabel(label2Text);
        JTextField textField2 = new JTextField();

        panel.add(label1);
        panel.add(comboBox1);
        panel.add(label2);
        panel.add(textField2);
        panel.setMaximumSize(new Dimension(600, 80));

        // Lưu JComboBox và JTextField vào danh sách
        comboBoxes.add(comboBox1);
        textFields.add(textField2);

        return panel;
    }

    private JPanel createTwoInputFields2(String label1Text, String label2Text) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel label1 = new JLabel(label1Text);
        JTextField textField1 = new JTextField();
        JLabel label2 = new JLabel(label2Text);
        JTextField textField2 = new JTextField();

        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.setMaximumSize(new Dimension(600, 80));

        // Lưu JTextField vào danh sách
        textFields.add(textField1);
        textFields.add(textField2);

        return panel;
    }

    private JPanel createTwoInputFields3(String label1Text, String label2Text) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel label1 = new JLabel(label1Text);
        JComboBox<String> comboBox1 = new JComboBox<>(new String[]{"A", "B", "C", "D", "E", "F"});
        JLabel label2 = new JLabel(label2Text);
        JTextField textField2 = new JTextField();

        panel.add(label1);
        panel.add(comboBox1);
        panel.add(label2);
        panel.add(textField2);
        panel.setMaximumSize(new Dimension(600, 80));

        // Lưu JComboBox và JTextField vào danh sách
        comboBoxes.add(comboBox1);
        textFields.add(textField2);

        return panel;
    }
}

