package gop1;

import javax.swing.*;
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
        MDSVDangKi model = new MDSVDangKi();
        GDSVDangKi view = new GDSVDangKi();
        new HĐSVDangKi(model, view);

        view.setVisible(true);
        view.getBackButton().addActionListener(event -> {
            studentView.dispose();
            studentView.setVisible(true);
        });
    }

}
