package sinhVienDangKy;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MDSVDangKi extends AbstractTableModel {
    private final String[] columnNames = {"STT", "Tên", "Mã số", "Giới tính", "Khoa", "Năm sinh", "Cư xá", "Phòng"};
    private List<String[]> data;
    private List<String[]> dataa;
    public String[] arr = {"Dang Ngoc Quyen", "23130263", "Nu", "CNTT", "2005", "D", "304F"};
    public String[] data1 = {
            "Nguyen Van A", "Nữ", "01/01/2000", "23130341", "0123456789",
            "123 ABC Street", "Khoa Công Nghệ Thông Tin", "P101", "Ký túc xá A", "123456789",
            "Kinh", "Diện 1"
    };

    public MDSVDangKi() {
        data = new ArrayList<>();
        data.add(new String[]{"Nguyen Van Tung", "2313335", "Nam", "CNTT", "2002", "A", "101"});
        data.add(new String[]{"Le Thi Tuyet Van", "2313345", "Nữ", "Kinh tế", "2001", "B", "202"});
        data.add(Arrays.copyOfRange(arr, 0, 7));
        data.add(new String[]{data1[0], data1[3], data1[1], data1[6], data1[2], data1[8], data1[7]});

    }

    public String[] getData1() {
        return data1;
    }

    public void filterData(String keyword) {
        List<String[]> filteredData = new ArrayList<>();
        for (String[] row : data) {
            if (String.join(" ", row).toLowerCase().contains(keyword.toLowerCase())) {
                filteredData.add(row);
            }
        }
        data = filteredData;
        fireTableDataChanged();
    }

    public String[] getStudentDetails(int rowIndex) {
        return data.get(rowIndex);
    }
    public String[] layHetDuLieu(int rowIndex) {
        return data.get(rowIndex);
    }

    public void removeStudent(int rowIndex) {
        data.remove(rowIndex);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
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
        if (columnIndex == 0) {
            return rowIndex + 1; // STT
        }
        return data.get(rowIndex)[columnIndex - 1];
    }
}
