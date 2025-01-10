package quanLyPhong;

import sinhVienDangO.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminRoomManagerView extends JFrame {
    private JList<String> maleDormitoryList;
    private JList<String> femaleDormitoryList;
    private DefaultListModel<String> maleDormitoryModel;
    private DefaultListModel<String> femaleDormitoryModel;
    private JButton backButton;
    private JButton viewDetailsButton;
    private Map<String, List<Room>> dormitoryData;
    private List<Room> rooms = new ArrayList<>();
    private String dormitoryName;

    public AdminRoomManagerView() {
        setTitle("Quản Lý Cư Xá");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tạo dữ liệu cư xá từ DormitoryDataManager
        initializeDormitoryData();;

        // Panel chính chứa hình nền
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Sử dụng getResource để tải hình ảnh từ thư mục resources
                ImageIcon originalIcon = new ImageIcon("src/img/ktx.jpg");
                Image scaledImage = originalIcon.getImage().getScaledInstance(900, 700, Image.SCALE_SMOOTH);
                JLabel backgroundImage = new JLabel(new ImageIcon(scaledImage));
                backgroundImage.setBounds(0, 0, 900, 700);
                g.drawImage(scaledImage, 0, 0, null);
            }
        };
        mainPanel.setLayout(new BorderLayout());


// Danh sách cư xá nam
        maleDormitoryModel = new DefaultListModel<>();
        maleDormitoryList = new JList<>(maleDormitoryModel);
        maleDormitoryList.setFont(new Font("Arial", Font.PLAIN, 25));  // Kích thước chữ nhỏ hơn
//        maleDormitoryList.setFixedCellHeight(30);  // Chiều cao mỗi ô nhỏ hơn
//        maleDormitoryList.setVisibleRowCount(3);   // Hiển thị tối đa 4 hàng (giảm chiều dài)
        maleDormitoryList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                femaleDormitoryList.clearSelection();
            }
        });

        JPanel malePanel = new JPanel(new BorderLayout());
        malePanel.setOpaque(true); // Hiển thị màu nền trắng
        malePanel.setBackground(Color.WHITE);
        malePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Đường viền xám
        JLabel maleLabel = new JLabel("Cư Xá Nam", JLabel.CENTER);
        maleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        malePanel.add(maleLabel, BorderLayout.NORTH);
        malePanel.add(new JScrollPane(maleDormitoryList), BorderLayout.CENTER);
//        malePanel.setPreferredSize(new Dimension(400, 150)); // Giảm chiều dài và chiều cao


// Danh sách cư xá nữ
        femaleDormitoryModel = new DefaultListModel<>();
        femaleDormitoryList = new JList<>(femaleDormitoryModel);
        femaleDormitoryList.setFont(new Font("Arial", Font.PLAIN, 25));  // Kích thước chữ nhỏ hơn
//        femaleDormitoryList.setFixedCellHeight(30);  // Chiều cao mỗi ô nhỏ hơn
//        femaleDormitoryList.setVisibleRowCount(3);   // Hiển thị tối đa 4 hàng (giảm chiều dài)
        femaleDormitoryList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                maleDormitoryList.clearSelection();
            }
        });



        JPanel femalePanel = new JPanel(new BorderLayout());
        femalePanel.setOpaque(true); // Hiển thị màu nền trắng
        femalePanel.setBackground(Color.WHITE);
        femalePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Đường viền xám
        JLabel femaleLabel = new JLabel("Cư Xá Nữ", JLabel.CENTER);
        femaleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        femalePanel.add(femaleLabel, BorderLayout.NORTH);
        femalePanel.add(new JScrollPane(femaleDormitoryList), BorderLayout.CENTER);
//        femalePanel.setPreferredSize(new Dimension(400, 150));


        // Panel chứa cả hai danh sách với BoxLayout
        JPanel listsPanel = new JPanel();
        listsPanel.setLayout(new BoxLayout(listsPanel, BoxLayout.X_AXIS)); // Bố trí theo chiều ngang
        listsPanel.setOpaque(false);

// Thiết lập kích thước nhỏ hơn cho các panel cư xá nam và nữ
//        malePanel.setPreferredSize(new Dimension(150, 100));
//        femalePanel.setPreferredSize(new Dimension(150, 100));

// Thêm khoảng trống hai bên để căn giữa
        listsPanel.add(Box.createHorizontalStrut(60)); // Khoảng cách từ viền trái
        listsPanel.add(malePanel);
        listsPanel.add(Box.createHorizontalStrut(60)); // Khoảng cách giữa hai panel
        listsPanel.add(femalePanel);
        listsPanel.add(Box.createHorizontalStrut(60)); // Khoảng cách từ viền phải

        mainPanel.add(listsPanel, BorderLayout.CENTER);

// Thiết lập BorderLayout để bố trí nội dung chính giữa
        mainPanel.setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(listsPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);


        JLabel titleLabel = new JLabel("Danh Sách Cư Xá:", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(listsPanel, BorderLayout.CENTER);

        backButton = new JButton("Quay Lại");
        viewDetailsButton = new JButton("Xem Chi Tiết");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        viewDetailsButton.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        // Sự kiện
        viewDetailsButton.addActionListener(e -> {
            String selectedMaleDormitory = maleDormitoryList.getSelectedValue();
            String selectedFemaleDormitory = femaleDormitoryList.getSelectedValue();
            String selectedDormitory = selectedMaleDormitory != null ? selectedMaleDormitory : selectedFemaleDormitory;

            if (selectedDormitory != null) {
                dormitoryName = selectedDormitory.split(" -")[0];
                rooms = dormitoryData.get(dormitoryName);
                if (rooms != null) {
                    new DormitoryDetailsView(dormitoryName, rooms).setVisible(true);
                    setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một cư xá!");
            }
        });

        backButton.addActionListener(e -> {
            setVisible(false);
            View.getCurrentView().setVisible(true);
        });

        updateDormitoryLists();
    }

    private void initializeDormitoryData() {
        DormitoryDataManager dataManager = new DormitoryDataManager();
        dormitoryData = dataManager.getDormitoryData();
    }

    private void updateDormitoryLists() {
        maleDormitoryModel.clear();
        femaleDormitoryModel.clear();

        dormitoryData.forEach((dormitory, rooms) -> {
            // Lọc danh sách phòng còn trống
            long totalEmptyRooms = rooms.stream()
                    .filter(room -> room.getCurrentOccupancy() < room.getCapacity())
                    .count();

            if (totalEmptyRooms > 0) {
                String displayText = dormitory + " - Phòng trống: " + totalEmptyRooms;

                // Phân loại cư xá theo giới tính
                if (dormitory.equals("B") || dormitory.equals("D") || dormitory.equals("E")) {
                    femaleDormitoryModel.addElement(displayText);
                } else if (dormitory.equals("A") || dormitory.equals("C") || dormitory.equals("F")) {
                    maleDormitoryModel.addElement(displayText);
                }
            }
        });

    }


    private List<Room> filterRoomsByGender(String dormitoryName) {
        String gender = getDormitoryGender(dormitoryName);
        return dormitoryData.get(dormitoryName).stream()
                .filter(room -> {
                    String roomGender = getDormitoryGender(dormitoryName);
                    return roomGender.equalsIgnoreCase(gender);
                })
                .collect(Collectors.toList());
    }


    private void openRoomManagerView() {
        AdminRoomManagerView roomManagerView = new AdminRoomManagerView();
        this.setVisible(false); // Ẩn View hiện tại
        roomManagerView.setVisible(true); // Hiển thị AdminRoomManagerView
    }

    public JButton getBackButton() {
        return backButton;
    }


    public String getDormitoryGender(String dormitoryName) {
        if (dormitoryName.equals("B") || dormitoryName.equals("D") || dormitoryName.equals("E")) {
            return "Nữ"; // Cư xá nữ
        } else {
            return "Nam"; // Cư xá nam
        }
    }


}
