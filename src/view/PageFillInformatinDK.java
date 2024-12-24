package view;

import sinhVienDangKy.MDSVDangKi;
import sinhVienDangKy.StudentDataStorage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private String currentMSSV;

    private MDSVDangKi tableModel;
    private List<JTextField> textFields = new java.util.ArrayList<>();
    private List<JComboBox<String>> comboBoxes = new java.util.ArrayList<>();
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private Set<Map<String, String>> listSaveTaiKhoan;

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
        mainPanel.add(createTwoInputFields1("Giới tính:", "Ngày sinh:"));
        mainPanel.add(createTwoInputFields2("Mã số sinh viên:", "Số điện thoại:"));
        mainPanel.add(createInputField("Hộ khẩu thường trú:"));
        mainPanel.add(createInputField("Khoa:"));
        mainPanel.add(createTwoInputFields3("Cư xá:", "Phòng:"));
        mainPanel.add(createInputField("CCCD / CMND:"));
        mainPanel.add(createInputField("Dân tộc:"));

        JPanel policyPanel = new JPanel();
        policyPanel.setLayout(new BoxLayout(policyPanel, BoxLayout.Y_AXIS));
        policyPanel.setBorder(BorderFactory.createTitledBorder("Diện chính sách"));
        checkBox1 = new JCheckBox("Con liệt sĩ, thương binh, bệnh binh");
        checkBox2 = new JCheckBox("Gia đình đặc biệt khó khăn");
        policyPanel.add(checkBox1);
        policyPanel.add(checkBox2);
        mainPanel.add(policyPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        JButton confirmButton = new JButton("XÁC NHẬN");
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

        JButton backToPagePrevious = new JButton(new ImageIcon("src/img/arrow-back-icon.png"));
        backToPagePrevious.setForeground(Color.WHITE);
        backToPagePrevious.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToPagePrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "chooseRoom");
            }
        });
        mainPanel.add(backToPagePrevious);

        scrollPane = new JScrollPane(mainPanel);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private boolean allFieldsFilled() {
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

    private void saveData() {
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
        textFields.add(textField);
        return panel;
    }

    private JPanel createTwoInputFields1(String label1Text, String label2Text) {
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
        comboBoxes.add(comboBox1);
        textFields.add(textField2);
        return panel;
    }

    private void clearFields() {
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
