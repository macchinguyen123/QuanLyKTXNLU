package view;

import sinhVienDangKy.MDSVDangKi;
import sinhVienDangKy.StudentDataStorage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class PageFillInformatinDK extends JPanel {
    DefaultTableModel model;
    JTable table;
    String[] data;
    JScrollPane scrollPane;
    JLabel titleLabel;
    JPanel mainPanel;
    Stack<String> pageStack = new Stack<>();
    String currentMSSV;

    MDSVDangKi tableModel;
    List<JTextField> textFields = new java.util.ArrayList<>();
    List<JComboBox<String>> comboBoxes = new java.util.ArrayList<>();
    JCheckBox checkBox1;
    JCheckBox checkBox2;
    Set<Map<String, String>> listSaveTaiKhoan;
    JPanel policyPanel;
    JButton confirmButton,backHome,backToPagePrevious;
    StudentDataStorage storage;
    String[] listKhoa = {"Công nghệ thông tin","Chăn nuôi thú y","Cơ khí","Khoa học sinh học","Thủy sản","Nông học"};

    public PageFillInformatinDK(JPanel cardPanel, CardLayout cardLayout, PageTTCNcuaSVDaO pageTTCN, MDSVDangKi tableModel, Set<Map<String, String>> listSaveTaiKhoan, String currentMSSV) {
        this.tableModel = tableModel;
        this.listSaveTaiKhoan = listSaveTaiKhoan;
        this.currentMSSV = currentMSSV;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        titleLabel = new JLabel("TRƯỜNG ĐẠI HỌC NÔNG LÂM TPHCM", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        mainPanel.add(createInputField("Họ và tên:"));
        mainPanel.add(createTwoInputFields1("Giới tính:", "Ngày sinh (dd/MM/yyyy):"));
        mainPanel.add(createTwoInputFields2("Mã số sinh viên:", "Số điện thoại:"));
        mainPanel.add(createInputField("Hộ khẩu thường trú:"));
        mainPanel.add(createInputField1("Khoa:",listKhoa));
        mainPanel.add(createTwoInputFields3("Cư xá:", "Phòng:"));
        mainPanel.add(createInputField("CCCD / CMND:"));
        mainPanel.add(createInputField("Dân tộc:"));

        policyPanel = new JPanel();
        policyPanel.setLayout(new BoxLayout(policyPanel, BoxLayout.Y_AXIS));
        policyPanel.setBorder(BorderFactory.createTitledBorder("Diện chính sách"));
        checkBox1 = new JCheckBox("Con liệt sĩ, thương binh, bệnh binh");
        checkBox2 = new JCheckBox("Gia đình đặc biệt khó khăn");
        policyPanel.add(checkBox1);
        policyPanel.add(checkBox2);
        mainPanel.add(policyPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        confirmButton = new JButton("XÁC NHẬN");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBackground(new Color(173, 216, 230));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (allFieldsFilled()) {
                    saveData();
                    JOptionPane.showMessageDialog(mainPanel, "Đăng ký thành công!");
                    pageTTCN.updateInformation(data);
//                    tableModel.addStudent(data);
                    clearFields();
                    // Xóa tài khoản đăng nhập hiện tại khỏi danh sách
                    listSaveTaiKhoan.removeIf(account -> account.get("Mã số sinh viên").equals(currentMSSV));

                    cardLayout.show(cardPanel, "dangKiTaiKhoanSV");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Vui lòng điền đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.add(confirmButton);


        // Tạo một panel cho nút "Back"
        JPanel backButtonPanel = new JPanel(new BorderLayout());
        backButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm khoảng cách
        backButtonPanel.setOpaque(false);

        // Nút "Back"
        backToPagePrevious = new JButton(new ImageIcon("src/img/arrow-back-icon.png"));
        backToPagePrevious.setForeground(Color.WHITE);
        backToPagePrevious.setFocusPainted(false); // Loại bỏ đường viền focus
        backToPagePrevious.setBackground(new Color(240, 240, 240)); // Màu nền nút
        backToPagePrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "chooseRoom");
            }
        });

        backButtonPanel.add(backToPagePrevious, BorderLayout.WEST); // Đặt nút ở góc trái
        this.add(backButtonPanel, BorderLayout.NORTH); // Thêm panel chứa nút vào đầu màn hình

        scrollPane = new JScrollPane(mainPanel);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createInputField1(String s, String[] listKhoa) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 10, 10));
        JLabel label = new JLabel(s);
        panel.add(label);
        JComboBox<String> comboBox = new JComboBox<>(listKhoa);
        panel.setMaximumSize(new Dimension(600, 80));
        panel.add(comboBox);
        comboBoxes.add(comboBox);
        return panel;
    }

    public boolean allFieldsFilled() {
        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                return false;
            }
        }
        for (JComboBox<String> comboBox : comboBoxes) {
            if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void saveData() {
        data = new String[textFields.size() + comboBoxes.size() + 2];
        int index = 0;
        for (JTextField textField : textFields) {
            data[index++] = textField.getText();
        }
        for (JComboBox<String> comboBox : comboBoxes) {
            data[index++] = comboBox.getSelectedItem().toString();
        }
        data[index++] = checkBox1.isSelected() ? "Có" : "Không";
        data[index] = checkBox2.isSelected() ? "Có" : "Không";

        // Lấy thông tin cư xá và phòng
        String dorm = comboBoxes.get(0).getSelectedItem().toString();
        String room = textFields.get(textFields.size() - 1).getText();



        storage = StudentDataStorage.getInstance();
        storage.addStudent(data);
        System.out.println(Arrays.toString(data));
    }

    public JPanel createInputField(String labelText) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField();
        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(600, 60));
        textFields.add(textField);
        return panel;
    }

    public JPanel createTwoInputFields1(String label1Text, String label2Text) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        JLabel label1 = new JLabel(label1Text);
        JComboBox<String> comboBox1 = new JComboBox<>(new String[]{"Nam", "Nữ"});
        JLabel label2 = new JLabel(label2Text);
        JTextField textField2 = new JTextField();
        panel.add(label1);
        panel.add(comboBox1);
        panel.add(label2);
        panel.add(textField2);
        panel.setMaximumSize(new Dimension(600, 80));
        comboBoxes.add(comboBox1);
        textFields.add(textField2);
        return panel;
    }

    public JPanel createTwoInputFields2(String label1Text, String label2Text) {
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
        textFields.add(textField1);
        textFields.add(textField2);
        return panel;
    }

    public JPanel createTwoInputFields3(String label1Text, String label2Text) {
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
        comboBoxes.add(comboBox1);
        textFields.add(textField2);
        return panel;
    }

    public void clearFields() {
        for (JTextField textField : textFields) {
            textField.setText("");
        }
        for (JComboBox<String> comboBox : comboBoxes) {
            comboBox.setSelectedIndex(-1);
        }
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
    }



}
