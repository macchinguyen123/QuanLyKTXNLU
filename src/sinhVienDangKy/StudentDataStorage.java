package sinhVienDangKy;

import sinhVienDangO.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDataStorage {
    private static StudentDataStorage instance;
    private List<Student> studentData;

    private StudentDataStorage() {
        studentData = new ArrayList<>();
    }

    public static StudentDataStorage getInstance() {
        if (instance == null) {
            instance = new StudentDataStorage();
        }
        return instance;
    }

    public void addStudent(String[] data) {
        String name = data[0];
        String gender = data[1];
        String birthYear = data[2]; // Giả sử birthYear là một số nguyên
        String MSSV = data[3];
        String SĐT = data[4];
        String HKTT = data[5];
        String Khoa = data[6];
        String CuXa = data[7];
        String Phong = data[8];
        String cMND = data[9];
        String danToc = data[10];
        String dienChinhSach = data[11];

        // Khởi tạo đối tượng Student
        Student student = new Student(name, MSSV, gender, Khoa, birthYear, CuXa, Phong, HKTT, cMND, SĐT, danToc, dienChinhSach);

        // Thêm vào danh sách
        studentData.add(student);
    }

    public List<Student> getStudentData() {
        return studentData;
    }

    public void clearData() {
        studentData.clear();
    }

    public void removeStudent(String mssv) {
        studentData.removeIf(student -> student.getMssv().equals(mssv));
    }

}
