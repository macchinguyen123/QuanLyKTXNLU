package gop1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class View extends JFrame {
    private JMenuItem exitMenuItem;
    private JMenuItem manageMenuItem;
    private JMenuItem roomManageMenuItem;
    protected JPanel mainPanel;


    public View(String title) {
        // Thiết lập tiêu đề cho JFrame
        super(title);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Cửa sổ xuất hiện giữa màn hình
        setLayout(new BorderLayout());

        // Khởi tạo mainPanel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel); // Thêm mainPanel vào JFrame
    }

    public View() {
        setTitle("Quản Lý Sinh Viên");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(161, 210, 224, 50));
        setContentPane(mainPanel);

        // Tải và thay đổi kích thước ảnh nền để phù hợp với JFrame
        ImageIcon originalIcon = new ImageIcon("src/img/hinhanh.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel backgroundImage = new JLabel(scaledIcon);
        backgroundImage.setBounds(0, 0, 800, 500);
        mainPanel.add(backgroundImage);

        JMenuBar menuBar = new JMenuBar();

        JMenu exitMenu = new JMenu("Exit");
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setFont(new Font("Inter", Font.BOLD, 16));
        exitMenu.add(exitMenuItem);
        menuBar.add(exitMenu);

        JMenu manageMenu = new JMenu("Quản Lý");
        manageMenuItem = new JMenuItem("Quản Lý Sinh Viên");
        roomManageMenuItem = new JMenuItem("Quản Lý Phòng");
        manageMenuItem.setFont(new Font("Inter", Font.BOLD, 16));
        roomManageMenuItem = new JMenuItem("Quản Lý Phòng");
        roomManageMenuItem.setFont(new Font("Inter", Font.BOLD, 16));

        manageMenu.add(manageMenuItem);
        manageMenu.add(roomManageMenuItem);
        menuBar.add(manageMenu);

        setJMenuBar(menuBar);

        roomManageMenuItem.addActionListener(e -> openRoomManagerView());
    }

    private void openRoomManagerView() {
        // Gọi giao diện Quản Lý Phòng (phải tạo RoomManagerView trước)
        AdminRoomManagerView roomManagerView = new AdminRoomManagerView();
        roomManagerView.setVisible(true);
    }

    public void setExitMenuItemListener(ActionListener listener) {
        exitMenuItem.addActionListener(listener);
    }

    public void setManageMenuItemListener(ActionListener listener) {

        manageMenuItem.addActionListener(listener);
    }

    public void setRoomManageMenuItemListener(ActionListener listener) {
        roomManageMenuItem.addActionListener(listener);
    }
}
