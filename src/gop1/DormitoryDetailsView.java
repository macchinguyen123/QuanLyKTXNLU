package gop1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DormitoryDetailsView extends JFrame {
    private JTable roomTable;
    private JButton backButton;

    public DormitoryDetailsView(String dormitoryName, List<Room> rooms) {
        setTitle("Chi Tiết Cư Xá - " + dormitoryName);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel chính chứa hình nền
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("/img/hinhanh.jpg");
                Image scaledImage = originalIcon.getImage().getScaledInstance(1200, 700, Image.SCALE_SMOOTH);
                g.drawImage(scaledImage, 0, 0, null);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Tiêu đề
        JLabel titleLabel = new JLabel("Chi Tiết Phòng - " + dormitoryName, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);

        // Tạo bảng
        String[] columnNames = {"Số Phòng", "Loại Phòng", "Số Chỗ Trống", "Tổng Số Chỗ"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Room room : rooms) {
            tableModel.addRow(new Object[]{
                    room.getRoomNumber(),
                    room.getRoomType(),
                    room.getAvailableSlots(),
                    room.getTotalSlots()
            });
        }

        roomTable = new JTable(tableModel);
        roomTable.setFont(new Font("Arial", Font.PLAIN, 20));
        roomTable.setRowHeight(40);
        roomTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 22));

        JScrollPane scrollPane = new JScrollPane(roomTable);

        // Nút quay lại
        backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> dispose());

        // Thêm nút "Danh sách thanh toán tiền điện nước"
        JButton paymentButton = new JButton("Danh sách thanh toán tiền điện nước");
        paymentButton.setFont(new Font("Arial", Font.BOLD, 18));
        paymentButton.addActionListener(e -> {
            new PaymentDetailsView(rooms).setVisible(true);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        buttonPanel.add(paymentButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }
}
