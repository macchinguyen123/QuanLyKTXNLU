package quanLyPhong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PaymentDetailsView extends JFrame {
    private JTable paymentTable;
    private JButton backButton;
    private DormitoryDetailsView dormitoryDetailsView;
    private DefaultTableModel tableModel;

    public PaymentDetailsView(List<Room> rooms, DormitoryDetailsView dormitoryDetailsView) {
        this.dormitoryDetailsView = dormitoryDetailsView;
        setTitle("Danh sách thanh toán tiền điện nước");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Danh sách thanh toán tiền điện nước", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        // Tạo bảng chính
        String[] columnNames = {"Số Phòng", "Loại Phòng", "Đã Thanh Toán", "Chưa Thanh Toán", "Số Tiền"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 2 || column == 3) ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 3;
            }
        };

        // Thêm dữ liệu vào bảng
        Random random = new Random();
        for (Room room : rooms) {
            boolean isPaid = random.nextBoolean();
            tableModel.addRow(new Object[]{
                    room.getRoomNumber(),
                    room.getRoomType(),
                    isPaid,       // Đã Thanh Toán
                    !isPaid,      // Chưa Thanh Toán
                    (500 + random.nextInt(301)) + "K" // Số Tiền (500K - 800K)
            });
        }

        // Thêm TableModelListener để xử lý logic chọn chỉ một cột
        tableModel.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 2) { // "Đã Thanh Toán" được chọn
                boolean isPaid = (Boolean) tableModel.getValueAt(row, 2);
                if (isPaid) {
                    tableModel.setValueAt(false, row, 3); // Bỏ chọn "Chưa Thanh Toán"
                }
            } else if (column == 3) { // "Chưa Thanh Toán" được chọn
                boolean isUnpaid = (Boolean) tableModel.getValueAt(row, 3);
                if (isUnpaid) {
                    tableModel.setValueAt(false, row, 2); // Bỏ chọn "Đã Thanh Toán"
                }
            }
        });

        paymentTable = new JTable(tableModel);
        paymentTable.setRowHeight(30);
        paymentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 22));
        paymentTable.setFont(new Font("Arial", Font.PLAIN, 18));

        // Ẩn cột "Số Tiền"
        paymentTable.getColumnModel().getColumn(4).setMinWidth(0);
        paymentTable.getColumnModel().getColumn(4).setMaxWidth(0);
        paymentTable.getColumnModel().getColumn(4).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(paymentTable);

        // Nút lọc phòng chưa thanh toán
        JButton filterUnpaidButton = new JButton("Lọc phòng chưa thanh toán");
        filterUnpaidButton.setFont(new Font("Arial", Font.BOLD, 18));
        filterUnpaidButton.addActionListener(e -> {
            this.dispose(); // Đóng cửa sổ hiện tại
            showFilteredRooms(false); // Mở trang mới hiển thị danh sách phòng chưa thanh toán
        });

        // Nút quay lại
        backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> {
            dormitoryDetailsView.setVisible(true);
            this.setVisible(false);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(filterUnpaidButton);
        buttonPanel.add(backButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    private void showFilteredRooms(boolean showPaid) {
        String[] columnNames = {"Số Phòng", "Loại Phòng", "Số Tiền"};
        DefaultTableModel filteredTableModel = new DefaultTableModel(columnNames, 0);

        // Lọc và thêm các phòng dựa trên trạng thái thanh toán
        List<Object[]> filteredRooms = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            boolean isPaid = (Boolean) tableModel.getValueAt(i, 2); // Cột "Đã Thanh Toán"
            if (isPaid == showPaid) {
                filteredRooms.add(new Object[]{
                        tableModel.getValueAt(i, 0), // Số Phòng
                        tableModel.getValueAt(i, 1), // Loại Phòng
                        Integer.parseInt(tableModel.getValueAt(i, 4).toString().replace("K", "")) // Số Tiền
                });
            }
        }

        // Sắp xếp theo số tiền giảm dần
        filteredRooms.sort((o1, o2) -> (int) o2[2] - (int) o1[2]);

        // Thêm dữ liệu vào bảng đã sắp xếp
        for (Object[] rowData : filteredRooms) {
            filteredTableModel.addRow(new Object[]{rowData[0], rowData[1], rowData[2] + "K"});
        }

        // Hiển thị cửa sổ mới
        JFrame filteredRoomFrame = new JFrame(showPaid ? "Phòng đã thanh toán" : "Phòng chưa thanh toán");
        filteredRoomFrame.setSize(900, 700);
        filteredRoomFrame.setLocationRelativeTo(null);

        JTable filteredTable = new JTable(filteredTableModel);
        filteredTable.setRowHeight(30);
        filteredTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        filteredTable.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(filteredTable);

        // Nút quay lại
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> {
            filteredRoomFrame.dispose(); // Đóng cửa sổ hiện tại
            this.setVisible(true); // Hiển thị lại cửa sổ PaymentDetailsView
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        filteredRoomFrame.setLayout(new BorderLayout());
        filteredRoomFrame.add(scrollPane, BorderLayout.CENTER);
        filteredRoomFrame.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(false); // Ẩn cửa sổ hiện tại
        filteredRoomFrame.setVisible(true); // Hiển thị cửa sổ mới
    }


    private void showRooms(boolean showPaid) {
        String[] columnNames = {"Số Phòng", "Loại Phòng", "Số Tiền"};
        DefaultTableModel filteredTableModel = new DefaultTableModel(columnNames, 0);

        // Lọc và thêm các phòng dựa trên trạng thái thanh toán
        List<Object[]> filteredRooms = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            boolean isPaid = (Boolean) tableModel.getValueAt(i, 2); // Cột "Đã Thanh Toán"
            if (isPaid == showPaid) {
                filteredRooms.add(new Object[]{
                        tableModel.getValueAt(i, 0), // Số Phòng
                        tableModel.getValueAt(i, 1), // Loại Phòng
                        Integer.parseInt(tableModel.getValueAt(i, 4).toString().replace("K", "")) // Số Tiền
                });
            }
        }

        // Sắp xếp theo số tiền giảm dần
        filteredRooms.sort((o1, o2) -> (int) o2[2] - (int) o1[2]);

        // Thêm dữ liệu vào bảng đã sắp xếp
        for (Object[] rowData : filteredRooms) {
            filteredTableModel.addRow(new Object[]{rowData[0], rowData[1], rowData[2] + "K"});
        }

        // Hiển thị cửa sổ mới
        JFrame filteredRoomFrame = new JFrame(showPaid ? "Phòng đã thanh toán" : "Phòng chưa thanh toán");
        filteredRoomFrame.setSize(900, 700);
        filteredRoomFrame.setLocationRelativeTo(this);
        filteredRoomFrame.setVisible(true);
        dormitoryDetailsView.setVisible(false);

        JTable filteredTable = new JTable(filteredTableModel);
        filteredTable.setRowHeight(30);
        filteredTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        filteredTable.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(filteredTable);

        JButton closeButton = new JButton("Đóng");
        closeButton.setFont(new Font("Arial", Font.BOLD, 18));
        closeButton.addActionListener(e -> filteredRoomFrame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        filteredRoomFrame.setLayout(new BorderLayout());
        filteredRoomFrame.add(scrollPane, BorderLayout.CENTER);
        filteredRoomFrame.add(buttonPanel, BorderLayout.SOUTH);
        filteredRoomFrame.setVisible(true);
    }

    public JButton getBackButton() {
        return backButton;
    }
}
