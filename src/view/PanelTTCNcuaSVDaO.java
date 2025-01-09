package view;

import sinhVienDangKy.DataSVDangKi;
import sinhVienDangKy.StudentDataStorage;
import sinhVienDangO.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Map;
import java.util.Set;


public class PanelTTCNcuaSVDaO extends JPanel {
    // Các thành phần khác không thay đổi
    JLabel lbInfor;
    DefaultTableModel tableModel;
    JPanel panelInformation,panelTraPhong;
    JTable display;
    JScrollPane scrollPane;
    JButton btnOK,btnTraPhong;
    // Thuộc tính lưu trữ dữ liệu
    private final DataSVDangKi dataSVDangKi;


    public PanelTTCNcuaSVDaO(JPanel cardPanel, CardLayout cardLayout, Set<Map<String, String>> listSaveTaiKhoan) {
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
                listSaveTaiKhoan.clear();
                cardLayout.show(cardPanel, "studentPanel");
            }
        });
        btnTraPhong.setBackground(new Color(173, 216, 230));

        panelTraPhong.add(btnOK);
        panelTraPhong.add(btnTraPhong);
        this.add(panelTraPhong, BorderLayout.SOUTH);
        // Khởi tạo StudentDataStorage
        this.dataSVDangKi = DataSVDangKi.getInstance();
        if (!dataSVDangKi.getStudentData().isEmpty()) {
            updateInformation(dataSVDangKi.getStudentData().get(0).getMssv());
        } else {
            lbInfor.setText("Danh sách sinh viên trống.");
            tableModel.setRowCount(0);
        }
    }

    // Phương thức cập nhật thông tin sinh viên
    public void updateInformation(String mssv) {
        // Lấy thông tin sinh viên từ StudentDataStorage
        Student student = dataSVDangKi.getStudent(mssv);

        if (student == null) {
            lbInfor.setText("Không tìm thấy thông tin sinh viên");
            tableModel.setRowCount(0); // Xóa bảng cũ
            return;
        }

        // Cập nhật tiêu đề
        lbInfor.setText(student.getTen()); // Hiển thị tên sinh viên

        // Xóa thông tin cũ
        tableModel.setRowCount(0);

        // Cập nhật thông tin mới
        String[][] data = {
                {"Họ và tên", student.getTen()},
                {"Ngày sinh", student.getNamSinh()},
                {"Mã số sinh viên", student.getMssv()},
                {"Số điện thoại", student.getSđt()},
                {"Hộ khẩu thường trú", student.getDiaChi()},
                {"Phòng", student.getPhong()},
                {"CCCD / CMND", student.getIdCCCD()},
                {"Dân tộc", student.getDanToc()},
                {"Giới tính", student.getGioiTinh()},
                {"Khoa", student.getKhoa()},
                {"Cư xá", student.getCuXa()},
                {"Con liệt sĩ, thương binh", student.getDienChinhSach()},
                {"Gia đình khó khăn", student.getDienChinhSach()}
        };

        for (String[] row : data) {
            tableModel.addRow(row);
        }
    }

}

