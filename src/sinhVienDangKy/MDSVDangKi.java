package sinhVienDangKy;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MDSVDangKi extends AbstractTableModel {
    private final String[] columnNames = {"STT", "Tên", "Mã số", "Giới tính", "Khoa", "Năm sinh", "Cư xá", "Phòng"};
    private List<String[]> data;

    public MDSVDangKi() {
        data = new ArrayList<>();
        data.add(new String[]{"Nguyen Van A", "2313335", "Nam", "CNTT", "2002", "A", "101"});
        data.add(new String[]{"Le Thi B", "2313345", "Nữ", "Kinh tế", "2001", "B", "202"});
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
