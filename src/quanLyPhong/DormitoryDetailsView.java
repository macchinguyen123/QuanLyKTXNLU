package quanLyPhong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DormitoryDetailsView extends JFrame {
    private JTable roomTable; // Bảng hiển thị thông tin phòng
    private DormitoryDataManager dataManager; // Quản lý dữ liệu ký túc xá

    public DormitoryDetailsView(String dormitoryName, List<Room> rooms) {
        setTitle("Chi Tiết Cư Xá Cư Xá " + dormitoryName);
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = createMainPanel(dormitoryName);
        add(mainPanel);

        if (rooms != null && !rooms.isEmpty()) {
            populateTable(mainPanel, rooms);
            addButtons(mainPanel, rooms);
        } else {
            JLabel noRoomsLabel = new JLabel("Không có phòng nào trong ký túc xá.", JLabel.CENTER);
            noRoomsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            mainPanel.add(noRoomsLabel, BorderLayout.CENTER);
        }
    }

    private JPanel createMainPanel(String dormitoryName) {
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("src/img/nonglam.jpeg");
                Image scaledImage = originalIcon.getImage().getScaledInstance(1400, 900, Image.SCALE_SMOOTH);
                JLabel backgroundImage = new JLabel(new ImageIcon(scaledImage));
                backgroundImage.setBounds(0, 0, 900, 700);
                g.drawImage(scaledImage, 0, 0, null);
            }
        };
        mainPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Chi Tiết Phòng Cư Xá " + dormitoryName, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(50, 20, 700, 40);
        mainPanel.add(titleLabel);

        return mainPanel;
    }

    private void populateTable(JPanel mainPanel, List<Room> rooms) {
        String[] columnNames = {"Số Phòng", "Loại Phòng", "Số Chỗ Trống", "Tổng Số Chỗ"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Room room : rooms) {
            tableModel.addRow(new Object[]{
                    room.getRoomNumber(),
                    room.getRoomType(),
                    room.getCurrentOccupancy(),
                    room.getCapacity()
            });
        }

        roomTable = new JTable(tableModel);
        roomTable.setFont(new Font("Arial", Font.PLAIN, 20));
        roomTable.setRowHeight(40);
        roomTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 22));

        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBounds(50, 80, 800, 430);
        mainPanel.add(scrollPane);

        dataManager = new DormitoryDataManager();
        roomTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = roomTable.getSelectedRow();
                if (selectedRow != -1) {
                    String roomNumber = tableModel.getValueAt(selectedRow, 0).toString();
                    int availableSlots = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());
                    int totalSlots = Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());

                    List<String> members = dataManager.getRoomMembers(roomNumber, totalSlots - availableSlots);
                    showRoomMembersDialog(roomNumber, members);
                }
            }
        });
    }

    private void addButtons(JPanel mainPanel, List<Room> rooms) {
        JButton backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBounds(600, 550, 150, 40);
        backButton.addActionListener(e -> {
            this.setVisible(false);
            new AdminRoomManagerView().setVisible(true);
        });
        mainPanel.add(backButton);

        JButton paymentButton = new JButton("Danh sách thanh toán tiền điện nước");
        paymentButton.setFont(new Font("Arial", Font.BOLD, 18));
        paymentButton.setBounds(100, 550, 400, 40);
        paymentButton.addActionListener(e -> {
            PaymentDetailsView paymentDetailsView = new PaymentDetailsView(rooms, this);
            paymentDetailsView.setVisible(true);
            this.setVisible(false);
        });
        mainPanel.add(paymentButton);
    }

    private void showRoomMembersDialog(String roomNumber, List<String> members) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Danh sách thành viên phòng " + roomNumber);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JTextArea textArea = new JTextArea(String.join("\n", members));
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(this, panel, "Danh Sách Thành Viên", JOptionPane.INFORMATION_MESSAGE);
    }
}
