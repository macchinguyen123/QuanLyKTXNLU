package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ThongTinChonPhong extends JPanel {
    Image imgBackround;

    public ThongTinChonPhong(JPanel cardPanel, CardLayout cardLayout, List<String> selectedAttributes) {
        if (selectedAttributes == null || selectedAttributes.isEmpty()) {
            throw new IllegalArgumentException("Danh sách thuộc tính đã chọn không được null hoặc rỗng!");
        }

        imgBackround = new ImageIcon("src/img/backroundKTX.jpg").getImage();

        JComboBox<String> genderBox = createStyledComboBox(new String[]{selectedAttributes.get(0)});
        JComboBox<String> capacityBox = createStyledComboBox(new String[]{selectedAttributes.get(1)});
        JComboBox<String> dormBox = createStyledComboBox(new String[]{selectedAttributes.get(2)});

        // Nút quay lại
        JButton btnBack = new JButton(new ImageIcon("src/img/arrow-back-icon.png"));
        btnBack.setBounds(20, 10, 25, 25);
        this.add(btnBack);
        btnBack.addActionListener(e -> cardLayout.show(cardPanel, "chooseRoom"));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(200, 220, 255));
        topPanel.add(genderBox);
        topPanel.add(capacityBox);
        topPanel.add(dormBox);

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);

        // Nội dung chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        this.add(scrollPane, BorderLayout.CENTER);

        String[] rooms = {"P101 còn 1 chỗ trống", "P201 còn 1 chỗ trống", "P206 còn 1 chỗ trống"};
        for (String room : rooms) {
            mainPanel.add(createRoomPanel(room, cardPanel, cardLayout));
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

    private JComponent createRoomPanel(String room, JPanel cardPanel, CardLayout cardLayout) {
        JPanel roomPanel = new JPanel(new BorderLayout());
        roomPanel.setPreferredSize(new Dimension(700, 50));

        JButton roomButton = new JButton(room);
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
