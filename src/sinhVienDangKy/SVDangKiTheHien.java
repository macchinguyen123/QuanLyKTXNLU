package sinhVienDangKy;

import sinhVienDangO.StudentView;
import sinhVienDangO.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lớp riêng cho RegisteredStudentsButtonListener
public class SVDangKiTheHien implements ActionListener {
    private StudentView studentView;
    private MRegister mdsvDangKi;
    private View view1;

    public SVDangKiTheHien(StudentView studentView, MRegister mdsvDangKi, View view1) {
        this.studentView = studentView;
        this.mdsvDangKi = mdsvDangKi;
        this.view1=view1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VRegister view = new VRegister(mdsvDangKi);
        new CRegister(mdsvDangKi, view);

        view.setVisible(true);
        studentView.setVisible(false);
//        view.getBackButton().addActionListener(event -> {
//            view1.setVisible(true);
//            view.setVisible(false);
//        });
    }

}
