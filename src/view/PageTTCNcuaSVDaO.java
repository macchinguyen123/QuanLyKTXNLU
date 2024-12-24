package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PageTTCNcuaSVDaO extends JPanel {
    // Các thành phần khác không thay đổi
    JLabel lbInfor;
    DefaultTableModel tableModel;
    JPanel panelInformation,panelTraPhong;
    JTable display;
    JScrollPane scrollPane;
    JButton btnOK,btnTraPhong;


    public PageTTCNcuaSVDaO(JPanel cardPanel, CardLayout cardLayout) {
        this.setLayout(new BorderLayout());
        lbInfor = new JLabel("", JLabel.CENTER); // Hiện ban đầu để trống
        this.add(lbInfor, BorderLayout.NORTH);

        // Panel thông tin
        panelInformation = new JPanel();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Thông tin");
        tableModel.addColumn("Giá trị");

        display = new JTable(tableModel);
        display.setRowHeight(40);
        scrollPane = new JScrollPane(display);

        panelInformation.add(scrollPane, BorderLayout.CENTER);
        this.add(panelInformation, BorderLayout.CENTER);

        // Các nút
        panelTraPhong = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "login");
            }
        });
        btnOK.setBackground(new Color(173, 216, 230));

        btnTraPhong = new JButton("Trả Phòng");
        btnTraPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "studentPanel");
            }
        });
        btnTraPhong.setBackground(new Color(173, 216, 230));

        panelTraPhong.add(btnOK);
        panelTraPhong.add(btnTraPhong);
        this.add(panelTraPhong, BorderLayout.SOUTH);
    }

    // Phương thức cập nhật thông tin sinh viên
    public void updateInformation(String[] data) {
        if (data == null || data.length == 0) {
            return;
        }

        // Cập nhật tiêu đề
        lbInfor.setText(data[0]); // Họ và tên

        // Xóa thông tin cũ
        tableModel.setRowCount(0);

        // Cập nhật thông tin mới
//        String[] labels = {
//                "Họ và tên", "Giới tính", "Ngày sinh", "Mã số sinh viên",
//                "Số điện thoại", "Hộ khẩu thường trú", "Khoa",
//                "Phòng", "Cư xá", "CCCD / CMND", "Dân tộc",
//                "Con liệt sĩ, thương binh", "Gia đình khó khăn"
//        };
        String[] labels1 = {
                "Họ và tên","Ngày sinh", "Mã số sinh viên","Số điện thoại", "Hộ khẩu thường trú",
                "Khoa","Phòng","CCCD / CMND","Dân tộc","Giới tính","Cư xá", "Con liệt sĩ, thương binh", "Gia đình khó khăn"
        };

        for (int i = 0; i < labels1.length && i < data.length; i++) {
            tableModel.addRow(new Object[]{labels1[i], data[i]});
        }
    }
}

