package model1;


import java.util.ArrayList;
import java.util.List;

public class ManageStudent {
    private List<StayedStudent> stayedStudents = new ArrayList<>();
    private List<RegisterStudent> registerStudents = new ArrayList<>();

    public ManageStudent() {
    }

    // Tim sinh vien theo mssv
    public List<Student> searchStudentByMSSV(String mssv) {
        List<Student> result = new ArrayList<>();
        for (StayedStudent stayedStudent : stayedStudents) {
            Student foundStu = stayedStudent.findStudentById(mssv);
            if (foundStu != null) {
                result.add(foundStu);
            }
        }
        return result;
    }

    // Cap nhat thong tin sinh vien
    public void updateStudentInStayed(Student updatedStudent) {
        if (updatedStudent == null || updatedStudent.getIdStudent() == null) {
            throw new IllegalArgumentException("Thông tin sinh viên không hợp lệ!");
        }

        boolean isUpdated = false;
        for (StayedStudent stayedStudent : stayedStudents) {
            Student existingStudent = stayedStudent.findStudentById(updatedStudent.getIdStudent());
            if (existingStudent != null) {
                stayedStudent.updateStudent(updatedStudent);
                isUpdated = true;
                break;
            }
        }

        if (!isUpdated) {
            throw new IllegalArgumentException("Không tìm thấy sinh viên với MSSV: " + updatedStudent.getIdStudent());
        }
    }

    // Xoa sinh vien
    public boolean removeStudentInStayed(String mssv) {
        for (StayedStudent stayedStudent : stayedStudents) {
            boolean isRemoved = stayedStudent.removeStudentById(mssv);
            if (isRemoved) {
                return true;
            }
        }
        return false;
    }

    public List<String> getStudentStrings() {
        List<String> studentStrings = new ArrayList<>();
        for (StayedStudent stayedStudent : stayedStudents) {
            for (Student student : stayedStudent.getStudentMap().values()) {
                studentStrings.add(student.toString());
            }
        }
        return studentStrings;
    }

    public Student getStudentAtRow(int rowIndex) {
        int count = 0;
        for (StayedStudent stayedStudent : stayedStudents) {
            if (rowIndex < count + stayedStudent.getStudentMap().size()) {
                return stayedStudent.getStudentMap().get(rowIndex - count);
            }
            count += stayedStudent.getStudentMap().size();
        }
        return null;
    }

    public Student getStudentById(String id) {
        for (StayedStudent stayedStudent : stayedStudents) {
            Student foundStudent = stayedStudent.findStudentById(id);
            if (foundStudent != null) {
                return foundStudent;
            }
        }
        return null;
    }

}
