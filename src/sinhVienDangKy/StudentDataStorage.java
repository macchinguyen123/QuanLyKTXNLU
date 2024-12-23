package sinhVienDangKy;

import java.util.ArrayList;
import java.util.List;

public class StudentDataStorage {
    private static StudentDataStorage instance;
    private List<String[]> studentData;

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
        studentData.add(data);
    }

    public List<String[]> getStudentData() {
        return studentData;
    }

    public void clearData() {
        studentData.clear();
    }
}
