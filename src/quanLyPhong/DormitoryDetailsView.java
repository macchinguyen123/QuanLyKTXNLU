package quanLyPhong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                ImageIcon originalIcon = new ImageIcon("src/img/nonglam.jpeg");
                Image scaledImage = originalIcon.getImage().getScaledInstance(1200, 700, Image.SCALE_SMOOTH);
                JLabel backgroundImage = new JLabel(new ImageIcon(scaledImage));
                backgroundImage.setBounds(0, 0, 800, 500);
                g.drawImage(scaledImage, 0, 0, null);
            }
        };
        mainPanel.setLayout(null);


        // Tiêu đề
        JLabel titleLabel = new JLabel("Chi Tiết Phòng - " + dormitoryName, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(50, 20, 700, 40);

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
        scrollPane.setBounds(50, 80, 700, 300);

        // Thêm sự kiện nhấp chuột để hiển thị danh sách thành viên
        roomTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = roomTable.getSelectedRow();
                if (selectedRow != -1) {
                    String roomNumber = tableModel.getValueAt(selectedRow, 0).toString();
                    int availableSlots = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());
                    int totalSlots = Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());
                    // Tính số thành viên trong phòng
                    int memberCount = totalSlots - availableSlots;
                    List<String> members = getRoomMembers(roomNumber, memberCount);
                    showRoomMembersDialog(roomNumber, members);
                }
            }
        });


        // Nút quay lại
        JButton backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBounds(100, 400, 150, 40); // Đặt vị trí và kích thước
        backButton.addActionListener(e -> {
            this.setVisible(false);
            new AdminRoomManagerView().setVisible(true);
        });
        mainPanel.add(backButton);

        // Nút danh sách thanh toán tiền điện nước
        JButton paymentButton = new JButton("Danh sách thanh toán tiền điện nước");
        paymentButton.setFont(new Font("Arial", Font.BOLD, 18));
        paymentButton.setBounds(300, 400, 400, 40); // Đặt vị trí và kích thước
        paymentButton.addActionListener(e -> {
            // Hiển thị giao diện thanh toán tiền điện nước
            PaymentDetailsView paymentDetailsView = new PaymentDetailsView(rooms, this);
            paymentDetailsView.setVisible(true);

            // Ẩn cửa sổ hiện tại
            this.setVisible(false);
        });


        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(paymentButton, BorderLayout.SOUTH);
//        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    // Hàm giả lập lấy danh sách thành viên của phòng
    private List<String> getRoomMembers(String roomNumber, int memberCount) {
        // Thay thế bằng logic lấy dữ liệu thực tế
        return List.of("Nguyễn Văn A", "Trần Thị B", "Lê Văn C", "Hoàng Thị D", "Phạm Văn E", "Vũ Thị F", "Ngô Văn G", "Đinh Thị H").subList(0, Math.min(memberCount, 8));
    }

    // Hiển thị danh sách thành viên trong một dialog
    private void showRoomMembersDialog(String roomNumber, List<String> members) {
        // Tạo panel chứa tiêu đề và danh sách thành viên
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Tiêu đề danh sách thành viên
        JLabel titleLabel = new JLabel("Danh sách thành viên phòng " + roomNumber);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);  // Căn giữa tiêu đề
        panel.add(titleLabel);

        // Tạo text area để hiển thị danh sách thành viên
        StringBuilder memberList = new StringBuilder();
        for (String member : members) {
            memberList.append("- ").append(member).append("\n");
        }

        JTextArea textArea = new JTextArea(memberList.toString());
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setCaretPosition(0); // Đưa con trỏ về đầu

        // Đưa textArea vào JScrollPane với kích thước cố định
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        // Tạo panel phụ để căn giữa khung danh sách thành viên
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalGlue());  // Tạo khoảng trắng phía trên
        centerPanel.add(scrollPane);  // Thêm JScrollPane vào panel chính
        centerPanel.add(Box.createVerticalGlue());  // Tạo khoảng trắng phía dưới

        // Thêm panel chứa tiêu đề và panel chính vào panel gốc
        panel.add(centerPanel);

        // Hiển thị dialog với panel này
        JOptionPane.showMessageDialog(this, panel, "Danh Sách Thành Viên", JOptionPane.INFORMATION_MESSAGE);
    }


}