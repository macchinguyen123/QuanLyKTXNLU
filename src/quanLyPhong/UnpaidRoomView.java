package quanLyPhong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UnpaidRoomView extends JFrame {
    private JTable unpaidTable;

    public UnpaidRoomView(JFrame parentFrame, DefaultTableModel originalModel) {
        setTitle("Phòng chưa thanh toán");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Số Phòng", "Loại Phòng", "Số Tiền"};
        DefaultTableModel unpaidTableModel = new DefaultTableModel(columnNames, 0);

        for (int i = 0; i < originalModel.getRowCount(); i++) {
            boolean unpaid = (Boolean) originalModel.getValueAt(i, 3); // Cột "Chưa Thanh Toán"
            if (unpaid) {
                unpaidTableModel.addRow(new Object[]{
                        originalModel.getValueAt(i, 0), // Số Phòng
                        originalModel.getValueAt(i, 1), // Loại Phòng
                        originalModel.getValueAt(i, 4)  // Số Tiền
                });
            }
        }

        unpaidTable = new JTable(unpaidTableModel);
        unpaidTable.setRowHeight(30);
        unpaidTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        unpaidTable.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane unpaidScrollPane = new JScrollPane(unpaidTable);

        // Nút sắp xếp
        JButton sortButton = new JButton("Sắp Xếp");
        sortButton.setFont(new Font("Arial", Font.BOLD, 18));
        sortButton.addActionListener(e -> showSortOptions(unpaidTableModel));

        // Nút quay lại
        JButton backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> {
            parentFrame.setVisible(true);
            this.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sortButton);
        buttonPanel.add(backButton);

        setLayout(new BorderLayout());
        add(unpaidScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void showSortOptions(DefaultTableModel tableModel) {
        Object[] options = {"Nhỏ theo giá", "Lớn theo giá"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Chọn sắp xếp theo", "Sắp xếp",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]
        );

        if (choice == 0) {
            sortTable(tableModel, true);
        } else if (choice == 1) {
            sortTable(tableModel, false);
        }
    }

    private void sortTable(DefaultTableModel tableModel, boolean ascending) {
        List<Object[]> tableData = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableData.add(new Object[]{
                    tableModel.getValueAt(i, 0),
                    tableModel.getValueAt(i, 1),
                    Integer.parseInt(tableModel.getValueAt(i, 2).toString().replace("K", ""))
            });
        }

        tableData.sort((o1, o2) -> {
            int price1 = (int) o1[2];
            int price2 = (int) o2[2];
            return ascending ? price1 - price2 : price2 - price1;
        });

        tableModel.setRowCount(0);
        for (Object[] rowData : tableData) {
            tableModel.addRow(new Object[]{rowData[0], rowData[1], rowData[2] + "K"});
        }
    }
}
