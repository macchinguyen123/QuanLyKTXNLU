package sinhVienDangKy;

import sinhVienDangO.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDetailsPanel {
    private MDSVDangKi model;
    private GDSVDangKi view;
    private JLabel backgroundImage;
    private JFrame detailsFrame;

    public StudentDetailsPanel(Student studentDetails, int selectedRow, MDSVDangKi model, GDSVDangKi view) {
        this.model = model;
        this.view = view;
        // Tạo JFrame chi tiết
        detailsFrame = new JFrame("Chi Tiết Sinh Viên");
        detailsFrame.setLayout(null);
        detailsFrame.setSize(800, 500);
        detailsFrame.setLocationRelativeTo(null);

        ImageIcon originalIcon = new ImageIcon("src/img/hinhanh.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        backgroundImage = new JLabel(scaledIcon);
        backgroundImage.setBounds(0, 0, 800, 500);
        detailsFrame.setContentPane(backgroundImage); // Đặt nền ảnh làm nền chính
        backgroundImage.setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setOpaque(true);
        panel.setBackground(new Color(173, 216, 230)); // Màu xanh nhạt
        panel.setBounds(150, 50, 500, 400);
        // Hiển thị thông tin sinh viên
        JLabel nameLabel = new JLabel("Họ Và Tên: " + studentDetails.getTen());
        nameLabel.setBounds(20, 20, 460, 30); // Kích thước hợp lý, đảm bảo không bị tràn
        panel.add(nameLabel);

        JLabel idLabel = new JLabel("Mã số: " + studentDetails.getMssv());
        idLabel.setBounds(20, 60, 460, 30);
        panel.add(idLabel);

        JLabel genderLabel = new JLabel("Giới tính: " + studentDetails.getGioiTinh());
        genderLabel.setBounds(20, 100, 460, 30);
        panel.add(genderLabel);

        JLabel facultyLabel = new JLabel("Khoa: " + studentDetails.getKhoa());
        facultyLabel.setBounds(20, 140, 460, 30);
        panel.add(facultyLabel);

        JLabel birthLabel = new JLabel("Năm sinh: " + studentDetails.getNamSinh());
        birthLabel.setBounds(20, 180, 460, 30);
        panel.add(birthLabel);

        JLabel dormLabel = new JLabel("Cư xá: " + studentDetails.getCuXa());
        dormLabel.setBounds(20, 220, 460, 30);
        panel.add(dormLabel);

        JLabel roomLabel = new JLabel("Phòng: " + studentDetails.getPhong());
        roomLabel.setBounds(20, 260, 460, 30);
        panel.add(roomLabel);

        // Nút Quay lại
        JButton backButton = new JButton("Quay lại");
        backButton.setBounds(15, 320, 100, 30); // Nút nằm gọn trong frame
        backButton.addActionListener(e -> {
            view.setVisible(true);
            detailsFrame.dispose();
        });
        panel.add(backButton);

        // Nút Xác nhận
        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.setBounds(140, 320, 100, 30);
        confirmButton.setBackground(new Color(34, 139, 34)); // Xanh lá cây
        confirmButton.setForeground(Color.WHITE);
        confirmButton.addActionListener(e -> {
            LayDuLieuSV storage = LayDuLieuSV.getInstances();
            storage.addStudent(studentDetails);
            model.removeStudent(selectedRow);
            model.removeStudentTimKiem(selectedRow);
            detailsFrame.setVisible(false);
            detailsFrame.dispose();
            view.setVisible(true);
        });
        panel.add(confirmButton);

        // Nút Huỷ
        JButton cancelButton = new JButton("Huỷ");
        cancelButton.setBounds(265, 320, 100, 30);
        cancelButton.setBackground(Color.RED); // Đỏ
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(e -> {
            view.setVisible(true);
            model.removeStudent(selectedRow);
            model.removeStudentTimKiem(selectedRow);
            detailsFrame.dispose();
        });
        panel.add(cancelButton);

        // Nút Chi Tiết
        JButton xemCT = new JButton("Chi Tiết");
        xemCT.setBounds(390, 320, 100, 30);
        xemCT.setForeground(Color.BLACK);
        xemCT.addActionListener(e -> {
            // Lấy dữ liệu từ đối tượng Student
            String[] studentData = new String[]{
                    studentDetails.getTen(),           // Họ và tên
                    studentDetails.getGioiTinh(),     // Giới tính
                    studentDetails.getNamSinh(),      // Ngày sinh
                    studentDetails.getMssv(),         // Mã số sinh viên
                    studentDetails.getSđt(),          // Số điện thoại
                    studentDetails.getDiaChi(),       // Hộ khẩu thường trú
                    studentDetails.getKhoa(),         // Khoa
                    studentDetails.getPhong(),        // Phòng
                    studentDetails.getCuXa(),         // Cư xá
                    studentDetails.getIdCCCD(),       // CCCD/CMND
                    studentDetails.getDanToc(),       // Dân tộc
                    studentDetails.getDienChinhSach() // Diện chính sách
            };

            // Tạo giao diện chi tiết
            GDXemChiTiet detailPanel = new GDXemChiTiet(studentData, view);
            detailsFrame.setVisible(false);
            detailPanel.setVisible(true);
            detailPanel.getButtonQuayLai().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    detailsFrame.setVisible(true);
                    detailPanel.dispose();
                }
            });
        });
        panel.add(xemCT);

        detailsFrame.add(panel);
        view.setVisible(false);
        // Hiển thị JFrame
        detailsFrame.setVisible(true);
    }

    public boolean isShowing() {
        return detailsFrame != null && detailsFrame.isShowing();
    }
}
