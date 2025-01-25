package model1;
import sinhVienDangKy.StudentRepository;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class RegisterStudent extends AbstractTableModel {
    public List<model1.Student> students;
    public StudentDataStorage studentDataStorage;
    TreeSet<model1.Student> filteredTreeSet;
    public final List<model1.Student> originalStudents = new ArrayList<>();

    public RegisterStudent() {
        students = new ArrayList<>();
        StudentRepository studentRepository = StudentRepository.getInstance();

        studentDataStorage = StudentDataStorage.getInstance();
        List<model1.Student> storedData1 = studentRepository.getAllStudents();
        // Sample data
        students.addAll(storedData1);
        List<model1.Student> storedData = studentDataStorage.getStudentData();
        if (storedData != null && !storedData.isEmpty()) {
            students.addAll(storedData);
        } else {
            System.out.println(" ");
        }
        originalStudents.addAll(students);
    }

    public void removeStudent(int rowIndex) {
        StudentRepository studentRepository = StudentRepository.getInstance();
        if (rowIndex >= 0 && rowIndex < students.size()) {
            // Lấy sinh viên cần xóa
            model1.Student removedStudent = students.get(rowIndex);

            // Xóa sinh viên khỏi danh sách students và originalStudents
            students.remove(rowIndex);
            originalStudents.remove(removedStudent);

            // Xóa sinh viên khỏi StudentRepository
            studentRepository.removeStudent(removedStudent.getMssv());

            // Xóa sinh viên khỏi StudentDataStorage
            studentDataStorage.removeStudent(removedStudent.getMssv());

            // Thông báo cập nhật bảng
            fireTableDataChanged();
        }
    }


    public void removeStudentTimKiem(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < originalStudents.size()) {
            model1.Student removedStudent = originalStudents.get(rowIndex);
            originalStudents.remove(rowIndex);
            fireTableDataChanged();

            // Cập nhật StudentDataStorage
            studentDataStorage.removeStudent(removedStudent.getMssv());
        }
    }

    public void filterData(String keyword) {

        if (keyword.isEmpty()) {
            students.clear();
            students.addAll(originalStudents);
            fireTableDataChanged();
            return;
        }

        // Comparator để sắp xếp theo ký tự cuối cùng, sau đó toàn bộ chuỗi
        Comparator<model1.Student> customComparator = (s1, s2) -> {
            String name1 = s1.getTen().trim();
            String name2 = s2.getTen().trim();
            char lastChar1 = name1.charAt(name1.length() - 1);
            char lastChar2 = name2.charAt(name2.length() - 1);

            if (lastChar1 != lastChar2) {
                return Character.compare(lastChar1, lastChar2);
            }
            return name1.compareTo(name2);
        };

        filteredTreeSet = originalStudents.stream()
                .filter(student -> student.getCuXa().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(() -> new TreeSet<>(customComparator)));

        if (filteredTreeSet.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không Tìm Thấy Cư Xá : " + keyword, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        students.clear();
        students.addAll(filteredTreeSet);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 9; // Số cột cố định
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "STT";
            case 1: return "Tên";
            case 2: return "Mã số";
            case 3: return "Giới tính";
            case 4: return "Khoa";
            case 5: return "Năm sinh";
            case 6: return "Cư xá";
            case 7: return "Phòng";
            case 8: return "Hành động";
            default: return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch (columnIndex) {
            case 0: return rowIndex + 1; // STT
            case 1: return student.getTen();
            case 2: return student.getMssv();
            case 3: return student.getGioiTinh();
            case 4: return student.getKhoa();
            case 5: return student.getNamSinh();
            case 6: return student.getCuXa();
            case 7: return student.getPhong();
            case 8: return "Xem xét"; // Giá trị hiển thị trên nút
            default: return "";
        }
    }

    public Student getStudentDetails(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < students.size()) {
            return students.get(rowIndex);
        }
        return null;
    }

//    public String checkRoomAndName(String room ){
//        if (students.contains(room)) {
//            return students.get(students.indexOf(room)).getTen();
//        }else{
//            return null ;
//        }
}