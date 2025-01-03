package sinhVienDangKy;


import sinhVienDangO.Student;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HĐSVDangKi {
    private MDSVDangKi model;
    private GDSVDangKi view;
    private StudentDetailsPanel showStudentDetailsPanel;

    public HĐSVDangKi(MDSVDangKi model, GDSVDangKi view1) {
        this.model = model;
        this.view = view1;

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

        view.getStudentTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = view.getStudentTable().getSelectedRow();
                if (selectedRow >= 0) {
                    // Kiểm tra nếu khung chi tiết đã được hiển thị
                    if (showStudentDetailsPanel != null && showStudentDetailsPanel.isShowing()) {
                        return;
                    }

                    // Lấy đối tượng Student từ mô hình
                    Student studentDetails = model.getStudentDetails(selectedRow);

                    // Hiển thị chi tiết thông tin sinh viên
                    showStudentDetailsPanel = new StudentDetailsPanel(studentDetails, selectedRow, model, view);
                }
            }
        });


    }


}


