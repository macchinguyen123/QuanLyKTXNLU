package sinhVienDangO;

import quanLyPhong.AdminRoomManagerView;
import view.PanelChooseStudentOrManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JMenuItem exitMenuItem;
    private JMenuItem manageMenuItem;
    private JMenuItem roomManageMenuItem;
    protected JPanel mainPanel;

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
        exitMenuItem = new JMenuItem("Đăng Xuất");
        exitMenuItem.setFont(new Font("Inter", Font.BOLD, 16));
        exitMenu.add(exitMenuItem);
        menuBar.add(exitMenu);

        JMenu manageMenu = new JMenu("Quản Lý");
        manageMenuItem = new JMenuItem("Quản Lý Sinh Viên");
        roomManageMenuItem = new JMenuItem("Quản Lý Phòng");
        manageMenuItem.setFont(new Font("Inter", Font.BOLD, 16));
        roomManageMenuItem.setFont(new Font("Inter", Font.BOLD, 16));

        manageMenu.add(manageMenuItem);
        manageMenu.add(roomManageMenuItem);
        menuBar.add(manageMenu);

        setJMenuBar(menuBar);

        roomManageMenuItem.addActionListener(e -> openRoomManagerView());
        exitMenuItem.addActionListener(e -> showLogoutConfirmation());
    }

    private void openRoomManagerView() {
        // Gọi giao diện Quản Lý Phòng
        AdminRoomManagerView roomManagerView = new AdminRoomManagerView();
        roomManagerView.setVisible(true);
    }

    private void showLogoutConfirmation() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Bạn có muốn đăng xuất?",
                "Xác nhận đăng xuất",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            // Mở giao diện PanelChooseStudentOrManager
            JFrame newFrame = new JFrame("Chọn chế độ");
            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            // Thêm PanelChooseStudentOrManager vào cardPanel
            cardPanel.add(new PanelChooseStudentOrManager(cardPanel, cardLayout), "choosePanel");

            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setSize(800, 500);
            newFrame.setLocationRelativeTo(null);
            newFrame.setContentPane(cardPanel);
            newFrame.setVisible(true);

            // Đóng giao diện hiện tại
            this.dispose();
        }
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
