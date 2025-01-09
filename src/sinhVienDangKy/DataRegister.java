package sinhVienDangKy;

import sinhVienDangO.Student;

import java.util.ArrayList;
import java.util.List;

public class DataRegister {
    private static DataRegister instance;
    private List<Student> studentData;

    private DataRegister() {
        studentData = new ArrayList<>();
    }

    public static DataRegister getInstance() {
        if (instance == null) {
            instance = new DataRegister();
        }
        return instance;
    }

    public void addStudent(String[] data) {
        String name = data[0];
        String gender = data[8];
        String birthYear = data[1]; // Giả sử birthYear là một số nguyên
        String MSSV = data[2];
        String SĐT = data[3];
        String HKTT = data[4];
        String Khoa = data[9];
        String CuXa = data[10];
        String Phong = data[5];
        String cMND = data[6];
        String danToc = data[7];
        String dienChinhSach = data[11];

        // Khởi tạo đối tượng Student
        Student student = new Student(name, MSSV, gender, Khoa, birthYear, CuXa, Phong, HKTT, cMND, SĐT, danToc, dienChinhSach);

        // Thêm vào danh sách
        studentData.add(student);
    }

    public List<Student> getStudentData() {
        return studentData;
    }

    public void removeStudent(String mssv) {
        studentData.removeIf(student -> student.getMssv().equals(mssv));
    }
    // Lấy thông tin sinh viên theo MSSV
    public Student getStudent(String mssv) {
        for (Student student : studentData) {
            if (student.getMssv().equals(mssv)) {
                return student;
            }
        }
        return null; // Không tìm thấy
    }

}
