package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThongTinChonPhong extends JPanel {
//    JComboBox<String> comboboxGender;
//    JComboBox<String> comboboxNumOfPerson;
//    JComboBox<String> comboboxDormitory;
//    JButton btnHome,updateButton;
//    JPanel topPanel, mainPanel,roomPanel;
//    String[] genders = {"Nữ", "Nam"};
//    String[] capacities = {"6 người", "8 người"};
//    String[] dorms = {"Cư xá A", "Cư xá B", "Cư xá C","Cư xá D","Cư xá E","Cư xá F"};
//    JScrollPane scrollPane;

    Image imgBackround;
    public ThongTinChonPhong(JPanel cardPanel,CardLayout cardLayout) {
        imgBackround = new ImageIcon("src/img/backroundKTX.jpg").getImage();
        // Nút quay lại
        JButton homeButton = new JButton(new ImageIcon("home_icon.png")); // Thay icon thực tế
        homeButton.setPreferredSize(new Dimension(40, 40));
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.addActionListener(e -> System.out.println("Quay lại trang chính"));

        // ComboBox
        String[] genders = {"Nữ", "Nam"};
        String[] capacities = {"6 người","8 người"};
        String[] dorms = {"Cư xá A", "Cư xá B", "Cư xá C", "Cư xá D", "Cư xá E", "Cư xá F"};

        JComboBox<String> genderBox = createStyledComboBox(genders);
        JComboBox<String> capacityBox = createStyledComboBox(capacities);
        JComboBox<String> dormBox = createStyledComboBox(dorms);
        // create button back

        JButton btnBack = new JButton(new ImageIcon("src/img/arrow-back-icon.png"));
        btnBack.setBounds(20,10,25,25);
        this.add(btnBack);
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"chooseRoom");
            }
        });
        // Thêm vào panel trên
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(200, 220, 255));
        topPanel.add(homeButton);
        topPanel.add(genderBox);
        topPanel.add(capacityBox);
        topPanel.add(dormBox);



        // create nut xac nhan
        JButton xacNhan = new JButton(new ImageIcon("src/img/accept.png"));
        xacNhan.setPreferredSize(new Dimension(40, 40));
        xacNhan.setBackground(new Color(200, 220, 255));



        topPanel.add(xacNhan);


        // Thêm panel trên vào panel
        this.add(topPanel, BorderLayout.NORTH);



        // Tạo panel chính để hiển thị danh sách phòng
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        this.add(scrollPane, BorderLayout.CENTER);

        // Dữ liệu mẫu: danh sách phòng
        String[] rooms = {"P101 còn 1 chỗ trống", "P201 còn 1 chỗ trống", "P206 còn 1 chỗ trống", "P301 còn 1 chỗ trống", "P306 còn 1 chỗ trống"};

        // Thêm các phòng vào panel chính
        for (String room : rooms) {
            mainPanel.add(createRoomPanel(room, cardPanel, cardLayout));
        }




    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Arial", Font.BOLD, 14));
        comboBox.setBackground(new Color(150, 180, 255)); // Màu nền xanh nhạt
        comboBox.setForeground(Color.BLACK);
        comboBox.setPreferredSize(new Dimension(100, 30));
        return comboBox;
    }

    private JComponent createRoomPanel(String room,JPanel cardPanel,CardLayout cardLayout) {
        JPanel roomPanel = new JPanel(new BorderLayout());

        roomPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        roomPanel.setPreferredSize(new Dimension(700, 50));

        JButton roomButton = new JButton(room);
        roomButton.setFont(new Font("Arial", Font.BOLD, 16));
        roomButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel iconLabel = new JLabel(new ImageIcon("src/img/users-2-2-icon.png")); // Thay bằng icon thực tế
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"fillInformatinDK");
            }
        });


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
