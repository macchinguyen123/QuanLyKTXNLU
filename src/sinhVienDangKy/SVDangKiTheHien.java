package sinhVienDangKy;

import gop1.StudentView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lớp riêng cho RegisteredStudentsButtonListener
public class SVDangKiTheHien implements ActionListener {
    private StudentView studentView;
    private MDSVDangKi mdsvDangKi;

    public SVDangKiTheHien(StudentView studentView, MDSVDangKi mdsvDangKi) {
        this.studentView = studentView;
        this.mdsvDangKi = mdsvDangKi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GDSVDangKi view = new GDSVDangKi(mdsvDangKi);
        new HĐSVDangKi(mdsvDangKi, view);

        view.setVisible(true);
        view.getBackButton().addActionListener(event -> {
//            studentView.dispose();
            studentView.setVisible(true);
            view.setVisible(false);
        });
    }

}
