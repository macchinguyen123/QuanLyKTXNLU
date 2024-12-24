package sinhVienDangKy;

import gop1.StudentView;
import gop1.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lớp riêng cho RegisteredStudentsButtonListener
public class SVDangKiTheHien implements ActionListener {
    private StudentView studentView;
    private MDSVDangKi mdsvDangKi;
    private View view1;

    public SVDangKiTheHien(StudentView studentView, MDSVDangKi mdsvDangKi, View view1) {
        this.studentView = studentView;
        this.mdsvDangKi = mdsvDangKi;
        this.view1=view1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GDSVDangKi view = new GDSVDangKi(mdsvDangKi);
        new HĐSVDangKi(mdsvDangKi, view);

        view.setVisible(true);
        view.getBackButton().addActionListener(event -> {
//            studentView.dispose();
            view1.setVisible(true);
            view.setVisible(false);
        });
    }

}
