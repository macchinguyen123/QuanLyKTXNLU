// Class to store and manage student data
package sinhVienDangKy;

import sinhVienDangO.Student;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MDSVDangKi extends AbstractTableModel {
    private final String[] columnNames = {"STT", "Tên", "Mã số", "Giới tính", "Khoa", "Năm sinh", "Cư xá", "Phòng"};
    private final List<Student> students;
    public StudentDataStorage studentDataStorage;
    TreeSet<Student> filteredTreeSet;
    // Lưu danh sách sinh viên gốc (chỉ thực hiện một lần, ví dụ trong constructor hoặc khởi tạo)
    private final List<Student> originalStudents = new ArrayList<>();

    public MDSVDangKi() {
        students = new ArrayList<>();
        studentDataStorage = StudentDataStorage.getInstance();
        // Sample data
        students.add(new Student("Nguyen Van Tung", "2313335", "Nam", "Công Nghệ Thông Tin", "22/08/2005", "A", "101", "Kiên Giang", "091205014759", "0948088315", "Kinh", "Có"));
        students.add(new Student("Phạm Gia Bảo", "24120943", "Nam", "Chăn Nuôi Thú Y", "29/03/2004", "C", "306", "Đà Nẳng", "09327864532", "0942987534", "Tày", "Có"));
        students.add(new Student("Hồ Văn Đạt", "24761290", "Nam", "Cơ Khí Công Nghệ", "01/07/2006", "F", "100", "Bình Thuận", "0897654123", "0934231768", "Kinh", "Không"));
        students.add(new Student("Mai Van Hung", "23139990", "Nam", "Lâm Nghiệp", "22/08/2002", "A", "101", "Kiên Giang", "091205014759", "0948088315", "Kinh", "Không"));
        students.add(new Student("Le Thi Ngoc Mai", "23130001", "Nữ", "Kinh tế", "23/01/2001", "B", "202", "Ha Giang", "08347653421", "09320654332", "Hoa", "Không"));
        students.add(new Student("Chau Mai Tú", "23126578", "Nam", "Kinh tế", "23/01/2001", "C", "202", "Ha Giang", "08347653421", "09320654332", "Hoa", "Có"));

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
            Student removedStudent = students.get(rowIndex);
            students.remove(rowIndex);
            fireTableDataChanged();

            // Cập nhật StudentDataStorage
            studentDataStorage.removeStudent(removedStudent.getMssv());
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
            // Lấy tên của từng sinh viên
            String name1 = s1.getTen().trim();
            String name2 = s2.getTen().trim();

            // Lấy ký tự cuối cùng
            char lastChar1 = name1.charAt(name1.length() - 1);
            char lastChar2 = name2.charAt(name2.length() - 1);

            // So sánh ký tự cuối cùng trước
            if (lastChar1 != lastChar2) {
                return Character.compare(lastChar1, lastChar2);
            }

            // Nếu giống nhau, so sánh toàn bộ chuỗi
            return name1.compareTo(name2);
        };

        // Sử dụng TreeSet với Comparator tùy chỉnh
        filteredTreeSet = originalStudents.stream()
                .filter(student -> student.getCuXa().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(() -> new TreeSet<>(customComparator)));

        if (filteredTreeSet.isEmpty()) {
            // Hiển thị hộp thoại thông báo
            JOptionPane.showMessageDialog(null, "Không Tìm Thấy Cư Xá : " + keyword, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Cập nhật danh sách sinh viên
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
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1; // STT
            case 1:
                return student.getTen();
            case 2:
                return student.getMssv();
            case 3:
                return student.getGioiTinh();
            case 4:
                return student.getKhoa();
            case 5:
                return student.getNamSinh();
            case 6:
                return student.getCuXa();
            case 7:
                return student.getPhong();
            default:
                return "";
        }
    }

    public Student getStudentDetails(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < students.size()) {
            return students.get(rowIndex);
        }
        return null;
    }
}
