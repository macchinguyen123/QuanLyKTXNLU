package quanLyPhong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class PaymentDetailsView extends JFrame {
    private JTable paymentTable;
    private JButton backButton;
    private DormitoryDetailsView dormitoryDetailsView;
    private DefaultTableModel tableModel;
    private UnpaidRoomView unpaidRoomView;

    public PaymentDetailsView(List<Room> rooms, DormitoryDetailsView dormitoryDetailsView) {
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
            unpaidRoomView = new UnpaidRoomView(this, tableModel);
            unpaidRoomView.setVisible(true);
        });

        // Nút quay lại
        backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> {
            dormitoryDetailsView.setVisible(true);
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

    public JButton getBackButton() {
        return backButton;
    }
}
