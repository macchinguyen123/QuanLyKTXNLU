package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PageFillInformatinDK extends JPanel {
        DefaultTableModel model;
        JTable table;
        String[] data;
        JScrollPane scrollPane;
        JLabel titleLabel;
        JPanel mainPanel;

    public PageFillInformatinDK() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // create main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //  add title
        titleLabel = new JLabel("TRƯỜNG ĐẠI HỌC NÔNG LÂM TPHCM",JLabel.CENTER);
        titleLabel.setFont(new Font("Arial",Font.BOLD,18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));


        //cac truong nhap du lieu
        mainPanel.add(createInputField("Họ và tên:"));
        mainPanel.add(createTwoInputFields1("Giới tính:", "Ngày sinh:"));
        mainPanel.add(createTwoInputFields2("Mã số sinh viên:", "Số điện thoại:"));
        mainPanel.add(createInputField("Hộ khẩu thường trú:"));
        mainPanel.add(createInputField("Khoa:"));
        mainPanel.add(createTwoInputFields3("Phòng:", "Cư xá:"));
        mainPanel.add(createInputField("CCCD / CMND:"));
        mainPanel.add(createInputField("Dân tộc:"));
        // dien chinh sach
        JPanel policyPanel = new JPanel();
        policyPanel.setLayout(new BoxLayout(policyPanel,BoxLayout.Y_AXIS));
        policyPanel.setBorder(BorderFactory.createTitledBorder("Diện chính sách"));
        JCheckBox checkBox1= new JCheckBox("Con liệt sĩ, thương binh, bệnh binh");
        JCheckBox checkBox2= new JCheckBox("Gia đình đặc biệt khó khăn");
        policyPanel.add(checkBox1);
        policyPanel.add(checkBox2);
        mainPanel.add(policyPanel);
        mainPanel.add(Box.createVerticalStrut(20));


        // nut xac nhan
        JButton confirmButton =  new JButton("XÁC NHẬN");
        confirmButton.setFont(new Font("Arial",Font.BOLD,16));
        confirmButton.setBackground(new Color(173, 216, 230));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(confirmButton);


        scrollPane = new JScrollPane(mainPanel);
        this.add(scrollPane,BorderLayout.CENTER);


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
        return panel;

    }
    private JPanel createTwoInputFields2(String label1Text, String label2Text) {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel label1 = new JLabel(label1Text);
//        JComboBox<String> comboBox1 = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});
        JTextField textField1 = new JTextField();

        JLabel label2 = new JLabel(label2Text);
        JTextField textField2 = new JTextField();

        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.setMaximumSize(new Dimension(600, 80));
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
        return panel;

    }//

    private JPanel createInputField(String labelText) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField();
        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(600, 60));
        return panel;
    }
}
