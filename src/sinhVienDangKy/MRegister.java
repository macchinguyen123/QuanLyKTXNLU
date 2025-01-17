package sinhVienDangKy;

import sinhVienDangO.Student;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MRegister extends AbstractTableModel {
    private StudentRepository studentRepository;
    public List<Student> students;
    public StudentDataStorage studentDataStorage;
    TreeSet<Student> filteredTreeSet;
    public final List<Student> originalStudents = new ArrayList<>();

    public MRegister() {
        students = new ArrayList<>();
        studentRepository = StudentRepository.getInstance();

        studentDataStorage = StudentDataStorage.getInstance();
        List<Student> storedData1 = studentRepository.getAllStudents();
        // Sample data
//        students.add(new Student("Nguyen Van Tung", "2313335", "Nam", "Công Nghệ Thông Tin", "22/08/2005", "A", "A101", "Kiên Giang", "091205014759", "0948088315", "Kinh", "Có"));
//        storedData1.add(new Student("Phạm Gia Bảo", "24120943", "Nam", "Chăn Nuôi Thú Y", "29/03/2004", "C", "C306", "Đà Nẳng", "09327864532", "0942987534", "Tày", "Có"));
//        storedData1.add(new Student("Hồ Văn Đạt", "24761290", "Nam", "Cơ Khí Công Nghệ", "01/07/2006", "F", "F100", "Bình Thuận", "0897654123", "0934231768", "Kinh", "Không"));
//        storedData1.add(new Student("Mai Van Hung", "23139990", "Nam", "Lâm Nghiệp", "22/08/2002", "A", "A101", "Kiên Giang", "091205014759", "0948088315", "Kinh", "Không"));
//        storedData1.add(new Student("Le Thi Ngoc Mai", "23130001", "Nữ", "Kinh tế", "23/01/2001", "B", "B202", "Ha Giang", "08347653421", "09320654332", "Hoa", "Không"));
//        storedData1.add(new Student("Chau Mai Tú", "23126578", "Nam", "Kinh tế", "23/01/2001", "C", "C202", "Ha Giang", "08347653421", "09320654332", "Hoa", "Có"));
        students.addAll(storedData1);
        List<Student> storedData = studentDataStorage.getStudentData();
        if (storedData != null && !storedData.isEmpty()) {
            students.addAll(storedData);
        } else {
            System.out.println(" ");
        }
        originalStudents.addAll(students);
    }

    public void removeStudent(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < students.size()) {
            // Lấy sinh viên cần xóa
            Student removedStudent = students.get(rowIndex);

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
            Student removedStudent = originalStudents.get(rowIndex);
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
        Comparator<Student> customComparator = (s1, s2) -> {
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
}
