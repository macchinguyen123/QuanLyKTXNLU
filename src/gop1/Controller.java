package gop1;

import sinhVienDangKy.MDSVDangKi;
import sinhVienDangKy.SVDangKiTheHien;
import view.Home;
import view.PageFillInformatinDK;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model passwordModel;
    private PasswordView passwordView;
    private Model mainModel;
    private View mainView;
    private ExitDialog mainExitDialog;
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
        mainExitDialog = new ExitDialog(mainView);

        // Main View listeners
        mainView.setExitMenuItemListener(new ExitMenuItemListener());
        mainView.setManageMenuItemListener(new ManageMenuItemListener());
        mainView.setRoomManageMenuItemListener(new RoomManageMenuItemListener());

        // ExitDialog listeners
        mainExitDialog.setThoatButtonListener(new ExitDialogThoatButtonListener());
        mainExitDialog.setHuyButtonListener(new ExitDialogHuyButtonListener());

        mainView.setVisible(true);
    }

    private void openStudentManagementView() {
        Model studentModel = new Model();
        StudentView studentView = new StudentView();
        ExitDialog studentExitDialog = new ExitDialog(studentView);
        StudentController studentController = new StudentController(new StudentListView());
        MDSVDangKi mdsvDangKi = new MDSVDangKi();

        // Student View listeners
        studentView.getBtnStudentList().addActionListener(new StudentListButtonListener(studentView, studentController));
        studentView.getBtnRegisteredStudents().addActionListener(new SVDangKiTheHien(studentView, home.getMdsvDangKi()));
        studentView.setExitMenuItemListener(new StudentExitMenuItemListener(studentExitDialog));
        studentExitDialog.setThoatButtonListener(new ExitDialogThoatButtonListener());
        studentExitDialog.setHuyButtonListener(new ExitDialogHuyButtonListener());
        studentView.getBtnBack().addActionListener(new BackButtonListener(studentView));

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
//                passwordView.displayMessage("Mật khẩu đã được xác nhận!");
//                passwordView.dispose();
                openMainView();
            }
        }
    }

    private class ExitMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainExitDialog.setVisible(true);
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

    private class ExitDialogHuyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainExitDialog.setVisible(false);
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

            studentListView.getBackButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    studentListView.dispose();
                    studentView.setVisible(true);
                }
            });
        }
    }

    private class StudentExitMenuItemListener implements ActionListener {
        private ExitDialog studentExitDialog;

        public StudentExitMenuItemListener(ExitDialog studentExitDialog) {
            this.studentExitDialog = studentExitDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            studentExitDialog.setVisible(true);
        }
    }

    private class BackButtonListener implements ActionListener {
        private StudentView studentView;

        public BackButtonListener(StudentView studentView) {
            this.studentView = studentView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            studentView.dispose();
            mainView.setVisible(true);
        }
    }
}
