package gop1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PaymentDetailsView extends JFrame {
    private JTable paymentTable;
    private JButton filterButton;
    private JButton sortButton;
    private JButton backButton;

    public PaymentDetailsView(List<Room> rooms) {
        setTitle("Danh sách thanh toán tiền điện nước");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Danh sách thanh toán tiền điện nước", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        // Tạo bảng
        String[] columnNames = {"Số Phòng", "Loại Phòng", "Đã Thanh Toán", "Chưa Thanh Toán", "Số Tiền"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 2 || column == 3) ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 3; // Chỉ chỉnh sửa 2 cột checkbox
            }
        };

        Random random = new Random();
        for (Room room : rooms) {
            boolean isPaid = random.nextBoolean();
            int amountDue = room.getRoomType().contains("6 người") ? 600 : 800;
            tableModel.addRow(new Object[]{
                    room.getRoomNumber(),
                    room.getRoomType(),
                    isPaid, // Đã Thanh Toán
                    !isPaid, // Chưa Thanh Toán
                    amountDue + "K" // Số Tiền
            });
        }

        paymentTable = new JTable(tableModel);
        paymentTable.setRowHeight(30);
        paymentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 22));
        paymentTable.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(paymentTable);

        // Nút lọc phòng chưa thanh toán
        filterButton = new JButton("Lọc phòng chưa thanh toán");
        filterButton.setFont(new Font("Arial", Font.BOLD, 18));
        filterButton.addActionListener(e -> showUnpaidRooms(rooms, tableModel));

        // Nút sắp xếp
        sortButton = new JButton("Sắp Xếp");
        sortButton.setFont(new Font("Arial", Font.BOLD, 18));
        sortButton.addActionListener(e -> showSortMessage(tableModel));

        // Nút quay lại
        backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(filterButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(backButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void showUnpaidRooms(List<Room> rooms, DefaultTableModel originalModel) {
        List<Room> unpaidRooms = rooms.stream()
                .filter(room -> {
                    for (int i = 0; i < originalModel.getRowCount(); i++) {
                        if (originalModel.getValueAt(i, 0).equals(room.getRoomNumber())) {
                            return (Boolean) originalModel.getValueAt(i, 3);
                        }
                    }
                    return false;
                }).collect(Collectors.toList());

        JFrame unpaidFrame = new JFrame("Phòng chưa thanh toán");
        unpaidFrame.setSize(800, 500);
        unpaidFrame.setLocationRelativeTo(null);

        String[] columnNames = {"Số Phòng", "Loại Phòng", "Số Tiền"};
        DefaultTableModel unpaidTableModel = new DefaultTableModel(columnNames, 0);

        for (Room room : unpaidRooms) {
            int amountDue = room.getRoomType().contains("6 người") ? 600 : 800;
            unpaidTableModel.addRow(new Object[]{
                    room.getRoomNumber(),
                    room.getRoomType(),
                    amountDue + "K"
            });
        }

        JTable unpaidTable = new JTable(unpaidTableModel);
        unpaidTable.setRowHeight(30);
        unpaidTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        unpaidTable.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane unpaidScrollPane = new JScrollPane(unpaidTable);

        JButton backToMainButton = new JButton("Quay Lại");
        backToMainButton.setFont(new Font("Arial", Font.BOLD, 18));
        backToMainButton.addActionListener(e -> unpaidFrame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backToMainButton);

        unpaidFrame.add(unpaidScrollPane, BorderLayout.CENTER);
        unpaidFrame.add(buttonPanel, BorderLayout.SOUTH);
        unpaidFrame.setVisible(true);
    }

    private void showSortMessage(DefaultTableModel tableModel) {
        Object[] options = {"Nhỏ theo giá", "Lớn theo giá"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Chọn sắp xếp theo",
                "Sắp xếp",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            sortTable(tableModel, true); // Sắp xếp tăng dần
        } else if (choice == 1) {
            sortTable(tableModel, false); // Sắp xếp giảm dần
        }
    }

    private void sortTable(DefaultTableModel tableModel, boolean ascending) {
        List<Object[]> tableData = new java.util.ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableData.add(new Object[]{
                    tableModel.getValueAt(i, 0),
                    tableModel.getValueAt(i, 1),
                    tableModel.getValueAt(i, 2),
                    tableModel.getValueAt(i, 3),
                    Integer.parseInt(tableModel.getValueAt(i, 4).toString().replace("K", ""))
            });
        }

        tableData.sort((o1, o2) -> {
            int price1 = (int) o1[4];
            int price2 = (int) o2[4];
            return ascending ? price1 - price2 : price2 - price1;
        });

        tableModel.setRowCount(0);
        for (Object[] rowData : tableData) {
            tableModel.addRow(new Object[]{rowData[0], rowData[1], rowData[2], rowData[3], rowData[4] + "K"});
        }
    }
}
