package quanLyPhong;

import model1.ManageRoom;
import model1.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PaymentDetails extends JFrame {
    private JTable paymentTable;
    private JButton backButton;
    private DormitoryDetails dormitoryDetailsView;
    private DefaultTableModel tableModel;
    private List<Map<String, Object>> initialData = new ArrayList<>();
    private static List<Map<String, Object>> savedData = new ArrayList<>();
    private ManageRoom managerRoom;

    public PaymentDetails(List<Room> rooms, DormitoryDetails dormitoryDetailsView) {
        this.dormitoryDetailsView = dormitoryDetailsView;
        initializeData(rooms);
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

        loadSavedOrInitialData();

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
        paymentTable.getColumnModel().getColumn(4).setPreferredWidth(0);
        JScrollPane scrollPane = new JScrollPane(paymentTable);

        JButton filterUnpaidButton = new JButton("Lọc phòng chưa thanh toán");
        filterUnpaidButton.setFont(new Font("Arial", Font.BOLD, 18));
        filterUnpaidButton.addActionListener(e -> {
            this.dispose();
            showFilteredRooms(false);
        });

        backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> handleBackAction());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(filterUnpaidButton);
        buttonPanel.add(backButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void initializeData(List<Room> rooms) {
        if (savedData.isEmpty()) { // Chỉ khởi tạo dữ liệu ban đầu nếu chưa có dữ liệu đã lưu
            for (Room room : rooms) {
                Map<String, Object> rowData = new TreeMap<>();
                rowData.put("Số Phòng", room.getRoomNumber());
                rowData.put("Loại Phòng", room.getRoomType());
                rowData.put("Đã Thanh Toán", false);
                rowData.put("Chưa Thanh Toán", true);
                rowData.put("Số Tiền", room.getPaymentAmount() + "K");
                initialData.add(rowData);
            }
        }
    }

    private void loadSavedOrInitialData() {
        List<Map<String, Object>> dataToLoad = savedData.isEmpty() ? initialData : savedData;
        for (Map<String, Object> rowData : dataToLoad) {
            tableModel.addRow(new Object[]{
                    rowData.get("Số Phòng"),
                    rowData.get("Loại Phòng"),
                    rowData.get("Đã Thanh Toán"),
                    rowData.get("Chưa Thanh Toán"),
                    rowData.get("Số Tiền")
            });
        }
    }

    private void handleBackAction() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Bạn có muốn lưu các thay đổi không?",
                "Xác nhận",
                JOptionPane.YES_NO_CANCEL_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            saveTableData();
            dormitoryDetailsView.setVisible(true);
            this.dispose();
        } else if (choice == JOptionPane.NO_OPTION) {
            resetTableDataToInitial();
            dormitoryDetailsView.setVisible(true);
            this.dispose();
        }
    }

    private void saveTableData() {
        savedData.clear();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Map<String, Object> rowData = new TreeMap<>();
            rowData.put("Số Phòng", tableModel.getValueAt(i, 0));
            rowData.put("Loại Phòng", tableModel.getValueAt(i, 1));
            rowData.put("Đã Thanh Toán", tableModel.getValueAt(i, 2));
            rowData.put("Chưa Thanh Toán", tableModel.getValueAt(i, 3));
            rowData.put("Số Tiền", tableModel.getValueAt(i, 4));
            savedData.add(rowData);
        }
    }

    private void resetTableDataToInitial() {
        tableModel.setRowCount(0);
        for (Map<String, Object> rowData : initialData) {
            tableModel.addRow(new Object[]{
                    rowData.get("Số Phòng"),
                    rowData.get("Loại Phòng"),
                    rowData.get("Đã Thanh Toán"),
                    rowData.get("Chưa Thanh Toán"),
                    rowData.get("Số Tiền")
            });
        }
    }

    private void showFilteredRooms(boolean showPaid) {
        String[] columnNames = {"Số Phòng", "Loại Phòng", "Số Tiền"};
        DefaultTableModel filteredTableModel = new DefaultTableModel(columnNames, 0);

        List<Object[]> filteredRooms = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            filteredRooms.add(new Object[]{
                    tableModel.getValueAt(i, 0), // Số Phòng
                    tableModel.getValueAt(i, 1), // Loại Phòng
                    tableModel.getValueAt(i, 2), // Đã Thanh Toán
                    tableModel.getValueAt(i, 3), // Chưa Thanh Toán
                    tableModel.getValueAt(i, 4)  // Số Tiền
            });
        }


        // Gọi hàm lọc và sắp xếp từ ManagerRoom
        List<Object[]> filteredRoom = managerRoom.filterRoomsByPaymentStatus(filteredRooms, showPaid);
        managerRoom.sortRoomsByAmount(filteredRoom); // Sắp xếp các phòng sau khi đã lọc

        for (Object[] rowData : filteredRoom) {
            filteredTableModel.addRow(new Object[]{rowData[0], rowData[1], rowData[2] + "K"});
        }


        JFrame filteredRoomFrame = new JFrame(showPaid ? "Phòng đã thanh toán" : "Phòng chưa thanh toán");
        filteredRoomFrame.setSize(900, 700);
        filteredRoomFrame.setLocationRelativeTo(null);

        JTable filteredTable = new JTable(filteredTableModel);
        filteredTable.setRowHeight(30);
        filteredTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        filteredTable.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(filteredTable);

        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> {
            filteredRoomFrame.dispose();
            this.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        filteredRoomFrame.setLayout(new BorderLayout());
        filteredRoomFrame.add(scrollPane, BorderLayout.CENTER);
        filteredRoomFrame.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(false);
        filteredRoomFrame.setVisible(true);
    }
}
