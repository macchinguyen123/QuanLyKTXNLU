package model1;

import sinhVienDangKy.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class RegisterStudent {
    private List<model.Student> students;
    public StudentDataStorage studentDataStorage;
    public TreeSet<Student> filteredTreeSet;
    private final List<model.Student> originalStudents = new ArrayList<model.Student>();

    public RegisterStudent() {

    }
}
