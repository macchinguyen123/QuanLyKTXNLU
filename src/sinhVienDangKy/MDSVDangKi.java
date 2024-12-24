// Class to store and manage student data
package sinhVienDangKy;

import gop1.Student;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MDSVDangKi extends AbstractTableModel {
    private final String[] columnNames = {"STT", "Tên", "Mã số", "Giới tính", "Khoa", "Năm sinh", "Cư xá", "Phòng"};
    private final List<Student> students;
    public StudentDataStorage studentDataStorage;

    public MDSVDangKi() {
        students = new ArrayList<>();
        studentDataStorage = StudentDataStorage.getInstance();
        // Sample data
        students.add(new Student("Nguyen Van Tung", "2313335", "Nam", "CNTT", "2002", "A", "101", "", "", "", "", ""));
        students.add(new Student("Le Thi Tuyet Van", "2313345", "Nữ", "Kinh tế", "2001", "B", "202", "", "", "", "", ""));
        List<Student> storedData = studentDataStorage.getStudentData();
        if (storedData != null && !storedData.isEmpty()) {
            students.add(storedData.get(0));
        } else {
            System.out.println("Danh sách lưu trữ trống hoặc không hợp lệ MDSVDAANGKY");
        }
    }


    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
            fireTableDataChanged();
            System.out.println("Đã thêm sinh viên: " + student);
        }
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


    public void filterData(String keyword) {
        students.removeIf(student -> !student.toString().toLowerCase().contains(keyword.toLowerCase()));
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
