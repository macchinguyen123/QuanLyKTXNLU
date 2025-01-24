package model;

import model1.Student;
import quanLyPhong.DormitoryDataManager;
import quanLyPhong.Room;
import sinhVienDangKy.TakeData;
import sinhVienDangO.StudentListView;
import sinhVienDangO.UpdateInforView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentController {
    private static List<Student> students = new ArrayList<>();
    public TakeData layDuLieuSV;
    private Map<String, Student> studentMap = new HashMap<>();

    public StudentController() {
        this.students = new ArrayList<>();
        this.layDuLieuSV = TakeData.getInstances();
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

        for (Student student : students) {
            studentMap.put(student.getMssv(), student);
        }

    }


    public Student getStudentById(String id) {
        for (Student s : students) {
            if (s.getMssv().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public static List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // Tim sinh vien theo mssv
    public List<Student> searchStudentByMSSV(String mssv) {
        List<Student> result = new ArrayList<>();
        Student foundStu = studentMap.get(mssv.trim());
        if (foundStu != null) {
            result.add(foundStu);
        }
        return result;
    }

    // Cap nhat thong tin sinh vien
    public void updateStudent(Student updatedStudent) {
        if (updatedStudent == null || updatedStudent.getMssv() == null) {
            throw new IllegalArgumentException("Thông tin sinh viên không hợp lệ!");
        }
        boolean isUpdated = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getMssv().equals(updatedStudent.getMssv())) {
                students.set(i, updatedStudent); // Cập nhật thông tin
                isUpdated = true;
                break;
            }
        }
        if (!isUpdated) {
            throw new IllegalArgumentException("Không tìm thấy sinh viên có MSSV: " + updatedStudent.getMssv());
        }
    }

    // Xoa sinh vien
    public boolean removeStudentById(String studentID) {
        return students.removeIf(student -> student.getMssv().equals(studentID));
    }


    public List<String> getStudentStrings() {
        List<String> studentStrings = new ArrayList<>();
        for (Student student : students) {
            studentStrings.add(student.toString());
        }
        return studentStrings;
    }

    public Student getStudentAtRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < students.size()) {
            return students.get(rowIndex);
        }
        return null;
    }

}



