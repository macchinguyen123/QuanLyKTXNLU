package gop1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private List<Student> students;
    private StudentListView view;

    public StudentController(StudentListView view) {
        this.students = new ArrayList<Student>();
        this.view = view;
        Student st1 = new Student( "Nguyễn Văn A", "23130001", "Nam", "Công nghệ thông tin", LocalDate.parse("2005-01-24"), "Cư xá A", "101", "Bình Định", "123456", "0987654321", "Kinh", "Con liệt sĩ, thương binh, bệnh binh");
        Student st2 = new Student("Nguyễn Thị B", "23130002", "Nữ", "Công nghệ sinh học", LocalDate.parse("2004-05-20"), "Cư xá D", "201", "Tiền Giang", "234567", "0345678990", "Mông", "Dân tộc thiểu số");
        Student st3 = new Student("Nguyễn Văn C", "23130003", "Nam", "Công nghệ thực phẩm", LocalDate.parse("2005-10-02"), "Cư xá C", "102", "Long An", "341678", "0168390591", "Kinh", "Không thuộc các đối tượng trên");
        Student st4 = new Student("Nguyễn Văn D", "23130004", "Nam", "Công nghệ thông tin", LocalDate.parse("2005-08-04"), "Cư xá A", "103", "Kiên Giang", "401231", "0636036812", "Kinh", "Không thuộc các đối tượng trên");
        Student st5 = new Student("Nguyễn Thị E", "23130005", "Nữ", "Lâm nghiệp", LocalDate.parse("2005-12-08"), "Cư xá B", "205", "Dak Lak", "579130", "0470641237", "Kinh", "Không thuộc các đối tượng trên");

        students.add(st1);
        students.add(st2);
        students.add(st3);
        students.add(st4);
        students.add(st5);

        this.view.addSearchActionListener(new searchAction());
        this.view.addMenuActionListener(new menuAction());
    }


    public Student getStudentById(String id) {
        for (Student s : students) {
            if(s.getMssv().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public StudentListView getView() {
        return view;
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

    public void openUpdateInforView(Student currentStudent) {
        UpdateInforView updateView = new UpdateInforView(view, this, currentStudent);
        updateView.setStudentDetails(currentStudent);
        updateView.setVisible(true);
    }

    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getMssv().equals(updatedStudent.getMssv())) {
                students.set(i, updatedStudent); // Cập nhật thông tin
                break;
            }
        }
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



