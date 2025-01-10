package view;

import javax.swing.*;

import quanLyPhong.DormitoryDataManager;
import quanLyPhong.Room;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ThongTinChonPhong extends JPanel {
    Image imgBackround;
    JComboBox<String> genderBox, capacityBox, dormBox;
    JButton btnBack;
    JPanel topPanel, mainPanel;
    JScrollPane scrollPane;
    String[] roomsA, roomsB, roomsC, roomsD, roomsE, roomsF;
    private DormitoryDataManager dataManager;

    public ThongTinChonPhong(JPanel cardPanel, CardLayout cardLayout, List<String> selectedAttributes) {
        if (selectedAttributes == null || selectedAttributes.isEmpty()) {
            throw new IllegalArgumentException("Danh sách thuộc tính đã chọn không được null hoặc rỗng!");
        }
        dataManager = DormitoryDataManager.getInstance();

        imgBackround = new ImageIcon("src/img/backroundKTX.jpg").getImage();

        genderBox = createStyledComboBox(new String[] { selectedAttributes.get(0) });
        capacityBox = createStyledComboBox(new String[] { selectedAttributes.get(1) });
        dormBox = createStyledComboBox(new String[] { selectedAttributes.get(2) });

        // Nút quay lại
        btnBack = new JButton(new ImageIcon("src/img/arrow-back-icon.png"));
        btnBack.setBounds(20, 10, 25, 25);
        this.add(btnBack);
        btnBack.addActionListener(e -> cardLayout.show(cardPanel, "chooseRoom"));

        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(200, 220, 255));
        topPanel.add(genderBox);
        topPanel.add(capacityBox);
        topPanel.add(dormBox);

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);

        // Nội dung chính
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        scrollPane = new JScrollPane(mainPanel);
        this.add(scrollPane, BorderLayout.CENTER);

        ActionListener filterListener = e -> updateRoomList(getName(), cardPanel, cardLayout);
        genderBox.addActionListener(filterListener);
        capacityBox.addActionListener(filterListener);
        dormBox.addActionListener(filterListener);
        // hien thi danh sach phong trong theo cu xa
        updateRoomList(getName(), cardPanel, cardLayout);
    }

    private void updateRoomList(String dormitory, JPanel cardPanel, CardLayout cardLayout) {
        mainPanel.removeAll();

        String selectedGender = (String) genderBox.getSelectedItem();
        String selectedCapacity = (String) capacityBox.getSelectedItem();
        String selectedDorm = (String) dormBox.getSelectedItem();

        // Dữ liệu từ DormitoryDataManager
        DormitoryDataManager dataManager = DormitoryDataManager.getInstance();
        List<Room> rooms = dataManager.getRoomsByDormitory(selectedDorm);

        // Lọc danh sách phòng
        for (Room room : rooms) {
            boolean isGenderMatch = (selectedGender.equals("Nam") && room.getRoomNumber().startsWith("A")) ||
                    (selectedGender.equals("Nữ") && room.getRoomNumber().startsWith("B")) ||
                    (selectedGender.equals("Nam") && room.getRoomNumber().startsWith("C")) ||
                    (selectedGender.equals("Nữ") && room.getRoomNumber().startsWith("D")) ||
                    (selectedGender.equals("Nam") && room.getRoomNumber().startsWith("F")) ||
                    (selectedGender.equals("Nữ") && room.getRoomNumber().startsWith("E"));

            boolean isCapacityMatch = selectedCapacity.equals(room.getCapacity() + " người");
            if (isGenderMatch && isCapacityMatch && room.getCurrentOccupancy() > 0) {
                String roomInfo = room.getRoomNumber() + " - Còn " + room.getCurrentOccupancy() + " chỗ trống";
                mainPanel.add(createRoomPanelA(roomInfo, cardPanel, cardLayout));
            }
        }
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Arial", Font.BOLD, 14));
        comboBox.setBackground(new Color(150, 180, 255));
        comboBox.setForeground(Color.BLACK);
        comboBox.setPreferredSize(new Dimension(100, 30));
        return comboBox;
    }

    private JComponent createRoomPanelA(String roomInfo, JPanel cardPanel, CardLayout cardLayout) {
        JPanel roomPanel = new JPanel(new BorderLayout());
        roomPanel.setPreferredSize(new Dimension(700, 50));

        JButton roomButton = new JButton(roomInfo);
        roomButton.setFont(new Font("Arial", Font.BOLD, 16));
        roomButton.addActionListener(e -> cardLayout.show(cardPanel, "fillInformatinDK"));

        JLabel iconLabel = new JLabel(new ImageIcon("src/img/users-2-2-icon.png"));
        roomPanel.add(roomButton, BorderLayout.CENTER);
        roomPanel.add(iconLabel, BorderLayout.EAST);

        return roomPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgBackround, 0, 0, getWidth(), getHeight(), this);
    }
}