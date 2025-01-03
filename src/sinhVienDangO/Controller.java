package sinhVienDangO;

import quanLyPhong.AdminRoomManagerView;
import quanLyPhong.Model;
import sinhVienDangKy.GDSVDangKi;
import sinhVienDangKy.MDSVDangKi;
import sinhVienDangKy.SVDangKiTheHien;
import view.Home;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model passwordModel;
    private PasswordView passwordView;
    private Model mainModel;
    private View mainView;
    private StudentView studentView;

    public Controller(Model passwordModel, PasswordView passwordView) {
        this.passwordModel = passwordModel;
        this.passwordView = passwordView;

        passwordView.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordView.getPasswordInput();
                if (password.isEmpty()) {
                    passwordView.displayMessage("Mật khẩu không được để trống!");
                } else if (password.length() != 6) {
                    passwordView.displayMessage("Mật khẩu phải có đúng 6 ký tự!");
                } else {
                    passwordView.setVisible(false);
                    openMainView();
                }
            }
        });
    }

    private void openMainView() {
        mainModel = new Model();
        mainView = new View();

        // Main View listeners
        mainView.setManageMenuItemListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStudentManagementView();
            }
        });

        mainView.setVisible(true);
    }

    private void openStudentManagementView() {
        Model studentModel = new Model();
        StudentView studentView = new StudentView();
        StudentController studentController = new StudentController(new StudentListView());
        MDSVDangKi mdsvDangKi = new MDSVDangKi();
        GDSVDangKi g = new GDSVDangKi(mdsvDangKi);

        // Student View listeners
        studentView.getBtnStudentList().addActionListener(new StudentListButtonListener(studentView, studentController));
        studentView.getBtnRegisteredStudents().addActionListener(new SVDangKiTheHien(studentView, mdsvDangKi, mainView));
        studentView.getBtnBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentView.setVisible(false);
                mainView.setVisible(true);
            }
        });
        mainView.setVisible(false);
        studentView.setVisible(true);
    }

}
