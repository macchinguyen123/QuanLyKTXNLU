package quanLyPhong;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonEditorInput extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private String roomNumber;
    private Room currentRoom;
    private List<Room> rooms;

    public ButtonEditorInput(JButton button, List<Room> rooms) {
        this.button = button;
        this.rooms = rooms;
        this.button.addActionListener(this);
        this.button.setText("Nhập");
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        roomNumber = table.getValueAt(row, 0).toString();
        currentRoom = rooms.get(row);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Nhập";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Hiển thị dialog nhập chỉ số
        InputDialog.showElectricityInputDialog(roomNumber, currentRoom);
        fireEditingStopped(); // Dừng chỉnh sửa khi nhấn nút
    }
}
