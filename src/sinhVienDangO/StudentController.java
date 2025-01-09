package sinhVienDangO;

import sinhVienDangKy.TakeData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private List<Student> students;
    private List<String[]> data1 = new ArrayList<>();
    private StudentListView view;
    public TakeData layDuLieuSV;

    public StudentController(StudentListView view) {
        this.students = new ArrayList<>();
        this.layDuLieuSV = TakeData.getInstances();
        this.view = view;
        Student st1 = new Student("Nguyễn Văn A", "23130001", "Nam", "Công nghệ thông tin", "24/01/2005", "A", "A04", "Bình Định", "123456", "0987654321", "Kinh", "Con liệt sĩ, thương binh, bệnh binh");
        Student st2 = new Student("Nguyễn Thị B", "23130002", "Nữ", "Công nghệ sinh học", "20/05/2004", "D", "D10", "Tiền Giang", "234567", "0345678990", "Mông", "Gia đình đặc biệt khó khăn");
        Student st3 = new Student("Nguyễn Văn C", "23130003", "Nam", "Công nghệ thực phẩm", "02/10/2005", "C", "C02", "Long An", "341678", "0168390591", "Kinh", "");
        Student st4 = new Student("Nguyễn Văn D", "23130004", "Nam", "Công nghệ thông tin", "04/08/2005", "A", "A03", "Kiên Giang", "401231", "0636036812", "Kinh", "Con liệt sĩ, thương binh, bệnh binh");
        Student st5 = new Student("Nguyễn Thị E", "23130005", "Nữ", "Lâm nghiệp", "09/11/2004", "B", "B05", "Dak Lak", "579130", "0470641237", "Kinh", "Gia đình đặc biệt khó khăn");
        Student st6 = new Student("Đinh Thị M", "23130014", "Nữ", "Thú y", "19/05/2006", "E", "E04", "Kiên Giang", "428450", "0470646432", "Thái", "");
        Student st7 = new Student("Trần Văn N", "23130015", "Nam", "Kinh tế", "17/01/2003", "F", "F06", "TP.HCM", "085342", "0470646289", "Kinh", "Gia đình đặc biệt khó khăn");
        Student st8 = new Student("Đặng Thị O", "23130021", "Nữ", "Ngôn ngữ anh", "09/11/2001", "B", "B11", "Long An", "581534", "0260641237", "Kinh", "");
        List<Student> storedData = layDuLieuSV.getsVLuu();

        students.add(st1);
        students.add(st2);
        students.add(st3);
        students.add(st4);
        students.add(st5);
        students.add(st6);
        students.add(st7);
        students.add(st8);
        if (storedData != null && !storedData.isEmpty()) {
            students.addAll(storedData);
        } else {
            System.out.println(" ");
        }

        this.view.addSearchActionListener(new searchAction());
        this.view.addMenuActionListener(new menuAction());
    }


    public Student getStudentById(String id) {
        for (Student s : students) {
            if (s.getMssv().equals(id)) {
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



