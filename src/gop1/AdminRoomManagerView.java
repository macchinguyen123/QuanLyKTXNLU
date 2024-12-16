package gop1;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AdminRoomManagerView extends JFrame {
    private JList<String> dormitoryList;
    private DefaultListModel<String> dormitoryModel;
    private JButton backButton;
    private JButton viewDetailsButton;
    private Map<String, List<Room>> dormitoryData;

    public AdminRoomManagerView() {
        setTitle("Quản Lý Cư Xá");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tạo dữ liệu giả lập
        initializeDormitoryData();

        // Panel chính chứa hình nền
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Sử dụng getResource để tải hình ảnh từ thư mục resources
                ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/hinhanh.jpg"));
                Image scaledImage = originalIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
                g.drawImage(scaledImage, 0, 0, null);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Danh sách cư xá
        dormitoryModel = new DefaultListModel<>();
        dormitoryList = new JList<>(dormitoryModel);
        dormitoryList.setFont(new Font("Arial", Font.PLAIN, 18));
        dormitoryList.setFixedCellHeight(40);
        dormitoryList.setFixedCellWidth(20);

        JScrollPane scrollPane = new JScrollPane(dormitoryList);
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setOpaque(true); // Để hiển thị hình nền
        JLabel titleLabel = new JLabel("Danh Sách Cư Xá:", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK); // Màu chữ trắng để nổi bật
        listPanel.add(titleLabel, BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        // Nút bấm
        backButton = new JButton("Quay Lại");
        viewDetailsButton = new JButton("Xem Chi Tiết");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        viewDetailsButton.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(backButton);

        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        // Sự kiện
        viewDetailsButton.addActionListener(e -> {
            String selectedDormitory = dormitoryList.getSelectedValue();
            if (selectedDormitory != null) {
                String dormitoryName = selectedDormitory.split(" -")[0];
                List<Room> rooms = dormitoryData.get(dormitoryName);
                if (rooms != null) {
                    new DormitoryDetailsView(dormitoryName, rooms).setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một cư xá!");
            }
        });

        backButton.addActionListener(e -> dispose());

        // Hiển thị danh sách cư xá
        updateDormitoryList();
    }

    private void initializeDormitoryData() {
        dormitoryData = new TreeMap<>();
        dormitoryData.put("Cư Xá A", List.of(
                new Room("A01", "Phòng 6 người", 6, 1),
                new Room("A02", "Phòng 8 người", 8, 4),
                new Room("A03", "Phòng 8 người", 8, 4),
                new Room("A04", "Phòng 6 người", 6, 3),
                new Room("A05", "Phòng 6 người", 6, 5),
                new Room("A06", "Phòng 6 người", 6, 4),
                new Room("A07", "Phòng 8 người", 8, 4),
                new Room("A08", "Phòng 8 người", 8, 2),
                new Room("A09", "Phòng 8 người", 8, 1),
                new Room("A10", "Phòng 8 người", 8, 7),
                new Room("A11", "Phòng 8 người", 8, 7),
                new Room("A12", "Phòng 8 người", 8, 7),
                new Room("A13", "Phòng 8 người", 8, 7),
                new Room("A14", "Phòng 8 người", 8, 7),
                new Room("A15", "Phòng 8 người", 8, 6)
        ));
        dormitoryData.put("Cư Xá B", List.of(
                new Room("B01", "Phòng 8 người", 8, 0),
                new Room("B02", "Phòng 8 người", 8, 1),
                new Room("B03", "Phòng 8 người", 8, 3),
                new Room("B04", "Phòng 8 người", 8, 4),
                new Room("B05", "Phòng 8 người", 8, 3),
                new Room("B06", "Phòng 8 người", 8, 4),
                new Room("B07", "Phòng 8 người", 8, 7),
                new Room("B08", "Phòng 8 người", 8, 6),
                new Room("B09", "Phòng 8 người", 8, 4),
                new Room("B10", "Phòng 8 người", 8, 5),
                new Room("B11", "Phòng 8 người", 8, 6),
                new Room("B12", "Phòng 8 người", 8, 6),
                new Room("B13", "Phòng 8 người", 8, 6),
                new Room("B14", "Phòng 8 người", 8, 6),
                new Room("B15", "Phòng 8 người", 8, 6),
                new Room("B16", "Phòng 8 người", 8, 6),
                new Room("B17", "Phòng 8 người", 8, 6),
                new Room("B18", "Phòng 8 người", 8, 8)
        ));
        dormitoryData.put("Cư Xá C", List.of(
                new Room("C01", "Phòng 8 người", 8, 0),
                new Room("C02", "Phòng 8 người", 8, 1),
                new Room("C03", "Phòng 6 người", 6, 3),
                new Room("C04", "Phòng 6 người", 6, 4),
                new Room("C05", "Phòng 6 người", 6, 3),
                new Room("C06", "Phòng 6 người", 6, 4),
                new Room("C07", "Phòng 8 người", 8, 7),
                new Room("C08", "Phòng 8 người", 8, 6),
                new Room("C09", "Phòng 8 người", 8, 6),
                new Room("C10", "Phòng 8 người", 8, 6),
                new Room("C11", "Phòng 8 người", 8, 6),
                new Room("C12", "Phòng 8 người", 8, 6),
                new Room("C13", "Phòng 8 người", 8, 6),
                new Room("C14", "Phòng 8 người", 8, 6),
                new Room("C15", "Phòng 8 người", 8, 6),
                new Room("C16", "Phòng 8 người", 8, 6)
        ));
        dormitoryData.put("Cư Xá D", List.of(
                new Room("D01", "Phòng 6 người", 6, 3),
                new Room("D02", "Phòng 6 người", 6, 1),
                new Room("D03", "Phòng 6 người", 6, 3),
                new Room("D04", "Phòng 6 người", 6, 4),
                new Room("D05", "Phòng 6 người", 6, 3),
                new Room("D06", "Phòng 6 người", 6, 4),
                new Room("D07", "Phòng 8 người", 8, 7),
                new Room("D08", "Phòng 8 người", 8, 6),
                new Room("D09", "Phòng 8 người", 8, 6),
                new Room("D10", "Phòng 8 người", 8, 6),
                new Room("D11", "Phòng 8 người", 8, 6),
                new Room("D12", "Phòng 8 người", 8, 6),
                new Room("D13", "Phòng 8 người", 8, 6),
                new Room("D14", "Phòng 8 người", 8, 6)
        ));
        dormitoryData.put("Cư Xá E", List.of(
                new Room("E01", "Phòng 6 người", 6, 3),
                new Room("E02", "Phòng 6 người", 6, 1),
                new Room("E03", "Phòng 6 người", 6, 3),
                new Room("E04", "Phòng 6 người", 6, 4),
                new Room("E05", "Phòng 6 người", 6, 3),
                new Room("E06", "Phòng 6 người", 6, 4),
                new Room("E07", "Phòng 8 người", 8, 7),
                new Room("E08", "Phòng 8 người", 8, 6),
                new Room("E09", "Phòng 8 người", 8, 6),
                new Room("E10", "Phòng 8 người", 8, 6),
                new Room("E11", "Phòng 8 người", 8, 6),
                new Room("E12", "Phòng 8 người", 8, 6)
        ));
        dormitoryData.put("Cư Xá F", List.of(
                new Room("F01", "Phòng 6 người", 6, 3),
                new Room("F02", "Phòng 6 người", 6, 1),
                new Room("F03", "Phòng 6 người", 6, 3),
                new Room("F04", "Phòng 6 người", 6, 4),
                new Room("F05", "Phòng 6 người", 6, 3),
                new Room("F06", "Phòng 6 người", 6, 4),
                new Room("F07", "Phòng 8 người", 8, 7),
                new Room("F08", "Phòng 8 người", 8, 6),
                new Room("F09", "Phòng 8 người", 8, 6),
                new Room("F10", "Phòng 8 người", 8, 6)
        ));
    }

    private void updateDormitoryList() {
        dormitoryModel.clear();
        for (String dormitory : dormitoryData.keySet()) {
            long emptyRooms = dormitoryData.get(dormitory).stream()
                    .filter(room -> room.getAvailableSlots() > 0)
                    .count();
            dormitoryModel.addElement(dormitory + " - Phòng Trống: " + emptyRooms);
        }
    }
}
