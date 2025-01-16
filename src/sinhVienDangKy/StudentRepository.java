package sinhVienDangKy;

import sinhVienDangO.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static StudentRepository instance;
    private final List<Student> students;

    StudentRepository() {
        students = new ArrayList<>();
//        // Sample data
        students.add(new Student("Nguyen Van Tung", "2313335", "Nam", "Công Nghệ Thông Tin", "22/08/2005", "A", "A101", "Kiên Giang", "091205014759", "0948088315", "Kinh", "Có"));
        students.add(new Student("Phạm Gia Bảo", "24120943", "Nam", "Chăn Nuôi Thú Y", "29/03/2004", "C", "C306", "Đà Nẳng", "09327864532", "0942987534", "Tày", "Có"));
        students.add(new Student("Hồ Văn Đạt", "24761290", "Nam", "Cơ Khí Công Nghệ", "01/07/2006", "F", "F100", "Bình Thuận", "0897654123", "0934231768", "Kinh", "Không"));
        students.add(new Student("Mai Van Hung", "23139990", "Nam", "Lâm Nghiệp", "22/08/2002", "A", "A101", "Kiên Giang", "091205014759", "0948088315", "Kinh", "Không"));
        students.add(new Student("Le Thi Ngoc Mai", "23130001", "Nữ", "Kinh tế", "23/01/2001", "B", "B202", "Ha Giang", "08347653421", "09320654332", "Hoa", "Không"));
        students.add(new Student("Chau Mai Tú", "23126578", "Nam", "Kinh tế", "23/01/2001", "C", "C202", "Ha Giang", "08347653421", "09320654332", "Hoa", "Có"));
    }

    public static synchronized StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // Trả về bản sao của danh sách
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addStudents(List<Student> newStudents) {
        if (newStudents != null) {
            students.addAll(newStudents);
        }
    }

    public void removeStudent(String mssv) {
        students.removeIf(student -> student.getMssv().equals(mssv));
    }
}
