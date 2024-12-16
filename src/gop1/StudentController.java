package gop1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private List<Student> students;
    private StudentListView view;

    public StudentController(StudentListView view) {
        this.students = new ArrayList<Student>();
        this.view = view;
        Student st1 = new Student(1, "Nguyen Van A", "23130001", "Nam", "CNTT", 2005, "Cu xa A", "101", "Binh Dinh", "123456", "0987654321");
        Student st2 = new Student(2, "Nguyen Thi B", "23130002", "Nu", "CNHH", 2004, "Cu xa D", "201", "Tien Giang", "234567", "0345678990");
        Student st3 = new Student(3, "Nguyen Van C", "23130003", "Nam", "CNTP", 2005, "Cu xa C", "102", "Long An", "341678", "0168390591");
        Student st4 = new Student(4, "Nguyen Van D", "23130004", "Nam", "CNTT", 2005, "Cu xa A", "103", "Kien Giang", "401231", "0636036812");
        Student st5 = new Student(5, "Nguyen Thi E", "23130005", "Nu", "Nong Nghiep", 2005, "Cu xa B", "205", "Dak Lak", "579130", "0470641237");

        students.add(st1);
        students.add(st2);
        students.add(st3);
        students.add(st4);
        students.add(st5);

        this.view.addSearchActionListener(new searchAction());
        this.view.addMenuActionListener(new menuAction());
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<String> getStudentStrings() {
        List<String> studentStrings = new ArrayList<>();
        for (Student student : students) {
            studentStrings.add(student.toString());
        }
        return studentStrings;
    }

    private class menuAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class searchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = view.getSearchQuery().trim();
            List<Student> results = searchStudents(query);
            view.updateStudentList(results);
        }

        private List<Student> searchStudents(String query) {
            List<Student> result = new ArrayList<>();

            for (Student s : students) {
                if (s.getTen().toLowerCase().contains(query.toLowerCase())
                        || s.getMssv().contains(query)
                        || s.getKhoa().toLowerCase().contains(query.toLowerCase())) {
                    result.add(s);
                }
            }
            return result;
        }

    }
}



