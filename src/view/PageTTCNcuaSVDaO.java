package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageTTCNcuaSVDaO extends JPanel {
    JPanel panelInformation, panelTraPhong;
    DefaultTableModel tableModel;
    JTable display;
    JScrollPane scrollPane;
    JLabel lbInfor;
    JButton btnOK, btnTraPhong;
    public PageTTCNcuaSVDaO(JPanel cardPanel,CardLayout cardLayout) {
        this.setLayout(new BorderLayout());
        lbInfor = new JLabel("Nguyen Van A",JLabel.CENTER);
        this.add(lbInfor, BorderLayout.NORTH);



        //panel thong tin
        panelInformation = new JPanel();
        tableModel = new DefaultTableModel();
        tableModel.addColumn(" ");
        tableModel.addColumn("Thông tin");
        // Dữ liệu cho bảng
        String[][] data = {
                {"Ngày sinh", "1/1/2005"},
                {"Giới tính", "Nam"},
                {"Sinh viên năm", "2"},
                {"Sinh viên trường", "NLU"},
                {"Mã số sinh viên", "12345"},
                {"Khoa", "CNTT"},
                {"Số CCCD", "080305000000"},
                {"Nơi cấp CCCD", "CCSHS"},
                {"Dân tộc", "Kinh"},
                {"Tôn giáo", "Không"},
                {"Số phòng", "404"}
        };
        // Thêm từng dòng dữ liệu vào model
        for (String[] row : data) {
            tableModel.addRow(row);
        }

        display = new JTable(tableModel);
        display.setRowHeight(40);
        scrollPane = new JScrollPane(display);



        panelInformation.add(scrollPane,BorderLayout.CENTER);
        this.add(panelInformation,BorderLayout.CENTER);

        // add button ok, tra phong
        panelTraPhong = new JPanel(new FlowLayout(FlowLayout.CENTER));
//
        btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"login");
            }
        });
        btnOK.setBackground(new Color(173, 216, 230));
        btnTraPhong = new JButton("Trả Phòng");
        btnTraPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"studentPanel");
            }
        });
        btnTraPhong.setBackground(new Color(173, 216, 230));
        panelTraPhong.add(btnOK);
        panelTraPhong.add(btnTraPhong);

        this.add(panelTraPhong,BorderLayout.SOUTH);

    }
}
