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
        String gender = data[9];
        String birthYear = data[1]; // Giả sử birthYear là một số nguyên
        String MSSV = data[2];
        String SĐT = data[3];
        String HKTT = data[4];
        String Khoa = data[5];
        String CuXa = data[10];
        String Phong = data[6];
        String cMND = data[7];
        String danToc = data[8];
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
