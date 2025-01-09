package sinhVienDangO;

import quanLyPhong.AdminRoomManagerView;
import quanLyPhong.Model;
import view.Home;
import view.PanelChooseStudentOrManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JMenuItem exitMenuItem;
    private JMenuItem manageMenuItem;
    private JMenuItem roomManageMenuItem;
    protected JPanel mainPanel;
    private static View currentView;

    public View() {
        currentView = this;

        setTitle("Quản Lý Sinh Viên");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tạo giao diện chính
        mainPanel = createMainPanel();
        setContentPane(mainPanel);

        // Tạo menu
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        // Xử lý sự kiện cho các menu item
        roomManageMenuItem.addActionListener(e -> openRoomManagerView());
        exitMenuItem.addActionListener(e -> showLogoutConfirmation());
    }

    // Cung cấp phương thức truy cập View hiện tại
    public static View getCurrentView() {
        return currentView;
    }

    // Tạo panel chính
    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(161, 210, 224, 50));

        // Tải và thay đổi kích thước ảnh nền
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/hinhanh.jpg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundImage = new JLabel(new ImageIcon(scaledImage));
        backgroundImage.setBounds(0, 0, getWidth(), getHeight());
        panel.add(backgroundImage);

        return panel;
    }

    // Tạo menu bar
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu "Exit"
        JMenu exitMenu = new JMenu("Exit");
        exitMenuItem = new JMenuItem("Đăng Xuất");
        exitMenuItem.setFont(new Font("Inter", Font.BOLD, 16));
        exitMenu.add(exitMenuItem);
        menuBar.add(exitMenu);

        // Menu "Quản Lý"
        JMenu manageMenu = new JMenu("Quản Lý");
        manageMenuItem = new JMenuItem("Quản Lý Sinh Viên");
        roomManageMenuItem = new JMenuItem("Quản Lý Phòng");
        manageMenuItem.setFont(new Font("Inter", Font.BOLD, 16));
        roomManageMenuItem.setFont(new Font("Inter", Font.BOLD, 16));
        manageMenu.add(manageMenuItem);
        manageMenu.add(roomManageMenuItem);
        menuBar.add(manageMenu);

        return menuBar;
    }

    // Mở giao diện Quản Lý Phòng
    private void openRoomManagerView() {
        AdminRoomManagerView roomManagerView = new AdminRoomManagerView();

        // Xử lý quay lại từ giao diện quản lý phòng
        roomManagerView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomManagerView.setVisible(false);
                setVisible(true);
            }
        });

        this.setVisible(false);
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
            this.dispose(); // Đóng cửa sổ hiện tại

            // Mở cửa sổ đăng nhập quản lý
            Model combinedModel = new Model();
            PasswordView passwordView = new PasswordView();
            new Controller(combinedModel, passwordView);
            passwordView.setVisible(true);
        }
    }

    public void setManageMenuItemListener(ActionListener listener) {
        manageMenuItem.addActionListener(listener);
    }
}
