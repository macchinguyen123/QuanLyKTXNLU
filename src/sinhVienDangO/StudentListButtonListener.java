package sinhVienDangO;

import model1.StayedStudent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentListButtonListener implements ActionListener {
    private StudentView studentView;
    private StayedStudent stayedStudent;

    public StudentListButtonListener(StudentView studentView, StayedStudent stayedStudent) {
        this.studentView = studentView;
        this.stayedStudent = stayedStudent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StudentListView studentListView = new StudentListView();

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String student : stayedStudent.getStudentStrings()) { // Giả sử getStudents() trả về danh sách sinh viên
            listModel.addElement(student);
        }

        studentListView.updateStudentList(stayedStudent.getStudents());

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
