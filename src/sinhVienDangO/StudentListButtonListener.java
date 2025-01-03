package sinhVienDangO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentListButtonListener implements ActionListener {
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
