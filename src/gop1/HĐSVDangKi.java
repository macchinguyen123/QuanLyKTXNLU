package gop1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HĐSVDangKi {
    private  MDSVDangKi model;
    private GDSVDangKi view;

    public HĐSVDangKi (MDSVDangKi model, GDSVDangKi view) {
        this.model = model;
        this.view = view;

        view.getStudentTable().setModel(model);

        // Xử lý sự kiện bấm nút Lọc
        view.getFilterButton().addActionListener(e -> {
            String keyword = view.getFilterField().getText();
            model.filterData(keyword);
        });

        // Xử lý sự kiện Exit
        view.getExitMenuItem().addActionListener(e -> System.exit(0));

        // Xử lý sự kiện Quản Lý
        view.getManageMenuItem().addActionListener(e ->
                JOptionPane.showMessageDialog(view, "Chức năng quản lý chưa được triển khai."));

        // Xử lý sự kiện Quản Lý Phòng
        view.getRoomManageMenuItem().addActionListener(e ->
                JOptionPane.showMessageDialog(view, "Chức năng quản lý phòng chưa được triển khai."));

        // Xử lý khi bấm vào JTable
        view.getStudentTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = view.getStudentTable().getSelectedRow();
                if (selectedRow >= 0) {
                    // Lấy thông tin chi tiết sinh viên
                    String[] studentDetails = model.getStudentDetails(selectedRow);
                    showStudentDetailsPanel(studentDetails, selectedRow);
                }
            }
        });
    }


    private void showStudentDetailsPanel(String[] studentDetails, int selectedRow) {
        // Tạo panel chi tiết
        JPanel detailsPanel = new JPanel(null);
        detailsPanel.setOpaque(true);
        detailsPanel.setBackground(new Color(173, 216, 230)); // Màu xanh nhạt
        detailsPanel.setBounds(150, 50, 500, 400);

        // Hiển thị thông tin sinh viên
        JLabel nameLabel = new JLabel("Họ Và Tên: " + studentDetails[0]);
        nameLabel.setBounds(20, 20, 460, 30); // Kích thước hợp lý, đảm bảo không bị tràn
        detailsPanel.add(nameLabel);

        JLabel idLabel = new JLabel("Mã số: " + studentDetails[1]);
        idLabel.setBounds(20, 60, 460, 30);
        detailsPanel.add(idLabel);

        JLabel genderLabel = new JLabel("Giới tính: " + studentDetails[2]);
        genderLabel.setBounds(20, 100, 460, 30);
        detailsPanel.add(genderLabel);

        JLabel facultyLabel = new JLabel("Khoa: " + studentDetails[3]);
        facultyLabel.setBounds(20, 140, 460, 30);
        detailsPanel.add(facultyLabel);

        JLabel birthLabel = new JLabel("Năm sinh: " + studentDetails[4]);
        birthLabel.setBounds(20, 180, 460, 30);
        detailsPanel.add(birthLabel);

        JLabel dormLabel = new JLabel("Cư xá: " + studentDetails[5]);
        dormLabel.setBounds(20, 220, 460, 30);
        detailsPanel.add(dormLabel);

        JLabel roomLabel = new JLabel("Phòng: " + studentDetails[6]);
        roomLabel.setBounds(20, 260, 460, 30);
        detailsPanel.add(roomLabel);

        // Nút Quay lại
        JButton backButton = new JButton("Quay lại");
        backButton.setBounds(50, 320, 100, 30); // Nút nằm gọn trong panel
        backButton.addActionListener(e -> {
            view.showMainView();
            view.getContentPane().remove(detailsPanel);
            view.revalidate();
            view.repaint();
        });
        detailsPanel.add(backButton);

        // Nút Xác nhận
        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.setBounds(200, 320, 100, 30);
        confirmButton.setBackground(new Color(34, 139, 34)); // Xanh lá cây
        confirmButton.setForeground(Color.WHITE);
        confirmButton.addActionListener(e -> {
            model.removeStudent(selectedRow);
            view.showMainView();
            view.getContentPane().remove(detailsPanel);
            view.revalidate();
            view.repaint();
        });
        detailsPanel.add(confirmButton);

        // Nút Huỷ
        JButton cancelButton = new JButton("Huỷ");
        cancelButton.setBounds(350, 320, 100, 30);
        cancelButton.setBackground(Color.RED); // Đỏ
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(e -> {
            model.removeStudent(selectedRow);
            view.showMainView();
            view.getContentPane().remove(detailsPanel);
            view.revalidate();
            view.repaint();
        });
        detailsPanel.add(cancelButton);

        // Hiển thị panel chi tiết
        view.showDetailPanel(detailsPanel);
    }


}


