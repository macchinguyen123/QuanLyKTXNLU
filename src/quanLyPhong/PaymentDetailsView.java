package quanLyPhong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PaymentDetailsView extends JFrame {
    private JTable paymentTable;
    JButton backButton;
    private DormitoryDetailsView dormitoryDetailsView;
//    DormitoryDetailsView dormitoryDetailsView= new DormitoryDetailsView();

    public PaymentDetailsView(List<Room> rooms,DormitoryDetailsView dormitoryDetailsView) {
        this.dormitoryDetailsView = dormitoryDetailsView;
        setTitle("Danh sách thanh toán tiền điện nước");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Danh sách thanh toán tiền điện nước", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        // Tạo bảng chính
        String[] columnNames = {"Số Phòng", "Loại Phòng", "Đã Thanh Toán", "Chưa Thanh Toán", "Số Tiền"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 2 || column == 3) ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 3;
            }
        };

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
        JButton filterButton = new JButton("Lọc phòng chưa thanh toán");
        filterButton.setFont(new Font("Arial", Font.BOLD, 18));
        filterButton.addActionListener(e -> {
            this.setVisible(false);
            showUnpaidRooms(tableModel);
        });

        // Nút quay lại
        JButton backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> {
//            dormitoryDetailsView.setVisible(true);
            this.setVisible(false);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(filterButton);
        buttonPanel.add(backButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    // Hiển thị phòng chưa thanh toán
    private void showUnpaidRooms(DefaultTableModel originalModel) {
        JFrame unpaidFrame = new JFrame("Phòng chưa thanh toán");
        unpaidFrame.setSize(800, 500);
        unpaidFrame.setLocationRelativeTo(null);

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

        JTable unpaidTable = new JTable(unpaidTableModel);
        unpaidTable.setRowHeight(30);
        unpaidTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        unpaidTable.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane unpaidScrollPane = new JScrollPane(unpaidTable);

        // Panel chính giữa để căn chỉnh
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(unpaidScrollPane, new GridBagConstraints());

        // Nút sắp xếp
        JButton sortButton = new JButton("Sắp Xếp");
        sortButton.setFont(new Font("Arial", Font.BOLD, 18));
        sortButton.addActionListener(e -> showSortOptions(unpaidFrame, unpaidTableModel));

        // Nút quay lại
        backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> {
            dormitoryDetailsView.setVisible(true);
                    unpaidFrame.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sortButton);
        buttonPanel.add(backButton);

        unpaidFrame.add(unpaidScrollPane, BorderLayout.CENTER);
        unpaidFrame.add(buttonPanel, BorderLayout.SOUTH);
        unpaidFrame.setVisible(true);
    }

    // Hiển thị hộp thoại sắp xếp
    private void showSortOptions(JFrame parentFrame, DefaultTableModel tableModel) {
        Object[] options = {"Nhỏ theo giá", "Lớn theo giá"};
        int choice = JOptionPane.showOptionDialog(
                parentFrame, // Hiển thị trong phạm vi cửa sổ hiện tại
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

    // Hàm sắp xếp dữ liệu
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

    public JButton getBackButton() {
        return backButton;
    }
}