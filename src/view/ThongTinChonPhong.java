package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ThongTinChonPhong extends JPanel {
    Image imgBackround;
    JComboBox<String> genderBox,capacityBox,dormBox;
    JButton btnBack;
    JPanel topPanel, mainPanel;
    JScrollPane scrollPane;
    String[] roomsA, roomsB,roomsC,roomsD,roomsE,roomsF;
    public ThongTinChonPhong(JPanel cardPanel, CardLayout cardLayout, List<String> selectedAttributes) {
        if (selectedAttributes == null || selectedAttributes.isEmpty()) {
            throw new IllegalArgumentException("Danh sách thuộc tính đã chọn không được null hoặc rỗng!");
        }

        imgBackround = new ImageIcon("src/img/backroundKTX.jpg").getImage();

        genderBox = createStyledComboBox(new String[]{selectedAttributes.get(0)});
        capacityBox = createStyledComboBox(new String[]{selectedAttributes.get(1)});
        dormBox = createStyledComboBox(new String[]{selectedAttributes.get(2)});

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

        roomsA = new String[] {"A101 còn 1 chỗ trống", "A201 còn 1 chỗ trống", "A206 còn 1 chỗ trống","A207 còn 1 chỗ trống"};
         roomsB = new String[]{"B101 còn 1 chỗ trống", "B201 còn 1 chỗ trống", "B206 còn 1 chỗ trống"};
        roomsC = new String[]{"C101 còn 1 chỗ trống", "C201 còn 1 chỗ trống", "C206 còn 1 chỗ trống"};
        roomsD =new String[] {"D101 còn 1 chỗ trống", "D201 còn 1 chỗ trống", "D206 còn 1 chỗ trống"};
        roomsE = new String[]{"E101 còn 1 chỗ trống", "E201 còn 1 chỗ trống", "E206 còn 1 chỗ trống"};
         roomsF =new String[] {"F101 còn 1 chỗ trống", "F201 còn 1 chỗ trống", "F206 còn 1 chỗ trống"};
        // hien thi danh sach phong trong theo cu xa

        if (dormBox.getSelectedItem().equals("B")){
            for (String room : roomsB) {
                mainPanel.add(createRoomPanelA(room, cardPanel, cardLayout));
            }
        }else if (dormBox.getSelectedItem().equals("C")){
            for (String room : roomsC) {
                mainPanel.add(createRoomPanelA(room, cardPanel, cardLayout));
            }
        }else if (dormBox.getSelectedItem().equals("D")){
            for (String room : roomsD) {
                mainPanel.add(createRoomPanelA(room, cardPanel, cardLayout));
            }
        }else if (dormBox.getSelectedItem().equals("E")){
            for (String room : roomsE) {
                mainPanel.add(createRoomPanelA(room, cardPanel, cardLayout));
            }
        }else {
            for (String room : roomsF) {
                mainPanel.add(createRoomPanelA(room, cardPanel, cardLayout));
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

    private JComponent createRoomPanelA(String room, JPanel cardPanel, CardLayout cardLayout) {
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
