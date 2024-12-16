package gop1;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PaymentDetailsView extends JFrame {
    private JTable paymentTable;
    private JButton filterButton;
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
        String[] columnNames = {"Số Phòng", "Loại Phòng", "Đã Thanh Toán", "Chưa Thanh Toán"};
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
            boolean isPaid = random.nextBoolean(); // Xen kẽ phòng thanh toán và chưa thanh toán
            tableModel.addRow(new Object[]{
                    room.getRoomNumber(),
                    room.getRoomType(),
                    isPaid,       // Đã Thanh Toán
                    !isPaid       // Chưa Thanh Toán
            });
        }

        paymentTable = new JTable(tableModel);
        paymentTable.setRowHeight(30);
        paymentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 22));
        paymentTable.setFont(new Font("Arial", Font.PLAIN, 18));

        // Đảm bảo chỉ chọn 1 checkbox tại một thời điểm
        tableModel.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 2 || column == 3) {
                Boolean isPaid = (Boolean) tableModel.getValueAt(row, 2);
                Boolean isUnpaid = (Boolean) tableModel.getValueAt(row, 3);

                if (column == 2 && isPaid) {
                    tableModel.setValueAt(false, row, 3);
                } else if (column == 3 && isUnpaid) {
                    tableModel.setValueAt(false, row, 2);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(paymentTable);

        // Nút lọc phòng chưa thanh toán
        filterButton = new JButton("Lọc phòng chưa thanh toán");
        filterButton.setFont(new Font("Arial", Font.BOLD, 18));
        filterButton.addActionListener(e -> showUnpaidRooms(rooms, tableModel));

        // Nút quay lại
        backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(filterButton);
        buttonPanel.add(backButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Phương thức lọc và hiển thị phòng chưa thanh toán
    private void showUnpaidRooms(List<Room> rooms, DefaultTableModel originalModel) {
        // Tạo danh sách các phòng chưa thanh toán
        List<Room> unpaidRooms = rooms.stream()
                .filter(room -> {
                    for (int i = 0; i < originalModel.getRowCount(); i++) {
                        if (originalModel.getValueAt(i, 0).equals(room.getRoomNumber())) {
                            return (Boolean) originalModel.getValueAt(i, 3); // Chưa Thanh Toán
                        }
                    }
                    return false;
                }).collect(Collectors.toList());

        // Hiển thị cửa sổ mới với danh sách phòng chưa thanh toán
        JFrame unpaidFrame = new JFrame("Phòng chưa thanh toán");
        unpaidFrame.setSize(800, 500); // Kích thước giống trang ban đầu
        unpaidFrame.setLocationRelativeTo(null);

        String[] columnNames = {"Số Phòng", "Loại Phòng", "Chưa Thanh Toán"};
        DefaultTableModel unpaidTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 2 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Cột không chỉnh sửa
            }
        };

        for (Room room : unpaidRooms) {
            unpaidTableModel.addRow(new Object[]{
                    room.getRoomNumber(),
                    room.getRoomType(),
                    true
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
}
