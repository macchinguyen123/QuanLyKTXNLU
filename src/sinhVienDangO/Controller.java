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
    Home home = new Home();

//    private Model mainModel1;
    private AdminRoomManagerView roomManagerView;
    public Controller(Model passwordModel, PasswordView passwordView) {
        this.passwordModel = passwordModel;
        this.passwordView = passwordView;

        passwordView.getConfirmButton().addActionListener(new ConfirmButtonListener());
    }

    private void openMainView() {
        mainModel = new Model();
        mainView = new View();

        // Main View listeners
        mainView.setManageMenuItemListener(new ManageMenuItemListener());
        mainView.setRoomManageMenuItemListener(new RoomManageMenuItemListener());


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
        studentView.getBtnRegisteredStudents().addActionListener(new SVDangKiTheHien(studentView, home.getMdsvDangKi(), mainView));
        studentView.getBtnBack().addActionListener(new BackButtonListener(studentView));
        mainView.setVisible(false);
        studentView.setVisible(true);
    }

    // Inner Classes for Listeners

    private class ConfirmButtonListener implements ActionListener {
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
    }


    private class ManageMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openStudentManagementView();
        }
    }

    private class RoomManageMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            JOptionPane.showMessageDialog(mainView, mainModel.getRoomManage());
        }
    }

    private class ExitDialogThoatButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }


    private class StudentListButtonListener implements ActionListener {
        private StudentView studentView;
        private StudentController studentController;

        public StudentListButtonListener(StudentView studentView, StudentController studentController) {
            this.studentView = studentView;
            this.studentController = studentController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            StudentListView studentListView = new StudentListView();

            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String student : studentController.getStudentStrings()) { // Giả sử getStudents() trả về danh sách sinh viên
                listModel.addElement(student);
            }

            studentListView.updateStudentList(studentController.getStudents());

            studentListView.setVisible(true);
            studentView.setVisible(false);

            studentListView.getBackButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    studentListView.dispose();
                    studentListView.setVisible(false);
                    studentView.setVisible(true);
                }
            });
        }
    }


    private class BackButtonListener implements ActionListener {
        private StudentView studentView;

        public BackButtonListener(StudentView studentView) {
            this.studentView = studentView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            studentView.setVisible(false);
            mainView.setVisible(true);
        }
    }
}
