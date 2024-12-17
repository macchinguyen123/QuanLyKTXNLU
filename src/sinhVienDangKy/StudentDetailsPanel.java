package sinhVienDangKy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StudentDetailsPanel extends JPanel {
    private JButton backButton;
    private JButton confirmButton;
    private JButton cancelButton;

    public StudentDetailsPanel(String[] studentDetails, ActionListener onBack, ActionListener onConfirm, ActionListener onCancel) {
        setLayout(null);
        setOpaque(true);
        setBackground(new Color(173, 216, 230)); // Màu xanh nhạt
        setBounds(150, 50, 500, 400);

        // Hiển thị thông tin sinh viên
        JLabel nameLabel = new JLabel("Họ Và Tên: " + studentDetails[0]);
        nameLabel.setBounds(20, 20, 460, 30);
        add(nameLabel);

        JLabel idLabel = new JLabel("Mã số: " + studentDetails[1]);
        idLabel.setBounds(20, 60, 460, 30);
        add(idLabel);

        JLabel genderLabel = new JLabel("Giới tính: " + studentDetails[2]);
        genderLabel.setBounds(20, 100, 460, 30);
        add(genderLabel);

        JLabel facultyLabel = new JLabel("Khoa: " + studentDetails[3]);
        facultyLabel.setBounds(20, 140, 460, 30);
        add(facultyLabel);

        JLabel birthLabel = new JLabel("Năm sinh: " + studentDetails[4]);
        birthLabel.setBounds(20, 180, 460, 30);
        add(birthLabel);

        JLabel dormLabel = new JLabel("Cư xá: " + studentDetails[5]);
        dormLabel.setBounds(20, 220, 460, 30);
        add(dormLabel);

        JLabel roomLabel = new JLabel("Phòng: " + studentDetails[6]);
        roomLabel.setBounds(20, 260, 460, 30);
        add(roomLabel);

        // Nút Quay lại
        backButton = new JButton("Quay lại");
        backButton.setBounds(50, 320, 100, 30);
        backButton.addActionListener(onBack);
        add(backButton);

        // Nút Xác nhận
        confirmButton = new JButton("Xác nhận");
        confirmButton.setBounds(200, 320, 100, 30);
        confirmButton.setBackground(new Color(34, 139, 34)); // Xanh lá cây
        confirmButton.setForeground(Color.WHITE);
        confirmButton.addActionListener(onConfirm);
        add(confirmButton);

        // Nút Huỷ
        cancelButton = new JButton("Huỷ");
        cancelButton.setBounds(350, 320, 100, 30);
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(onCancel);
        add(cancelButton);
    }
}
