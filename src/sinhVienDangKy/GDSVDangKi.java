package sinhVienDangKy;

import sinhVienDangO.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Stack;

public class GDSVDangKi extends JFrame {
    private JTable studentTable;
    private JTextField filterField;
    private JButton filterButton;
    private JButton backButton; // Nút Quay về
    private JMenuItem exitMenuItem;
    private JMenuItem manageMenuItem;
    private JMenuItem roomManageMenuItem;
    private JPanel mainPanel; // Panel chứa giao diện chính
    private JLabel backgroundImage;
    private LayDuLieuSV storage;

    public GDSVDangKi(MDSVDangKi mdsvDangKi) {
        setTitle("Quản Lý Sinh Viên");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Tạo nền ảnh
        ImageIcon originalIcon = new ImageIcon("src/img/hinhanh.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        backgroundImage = new JLabel(scaledIcon);
        backgroundImage.setBounds(0, 0, 800, 500);
        setContentPane(backgroundImage); // Đặt nền ảnh làm nền chính
        backgroundImage.setLayout(null);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Lấy kích thước mới của JFrame
                int width = getWidth();
                int height = getHeight();

                // Thay đổi kích thước ảnh theo JFrame
                Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                backgroundImage.setIcon(scaledIcon);

                // Đặt lại kích thước của JLabel
                backgroundImage.setBounds(0, 0, width, height);

            }
        });

        // Tạo panel chính
        mainPanel = new JPanel(null);
        mainPanel.setOpaque(false); // Cho phép hiển thị nền phía sau
        mainPanel.setBounds(0, 0, 800, 500);
        backgroundImage.add(mainPanel);

        // Thanh menu
//        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        exitMenuItem = new JMenuItem("Exit");
        manageMenuItem = new JMenuItem("Quản Lý");
        roomManageMenuItem = new JMenuItem("Quản Lý Phòng");

        fileMenu.add(manageMenuItem);
        fileMenu.add(roomManageMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
//        menuBar.add(fileMenu);
//        setJMenuBar(menuBar);

        // Thanh tìm kiếm (di chuyển lên trên)
        filterField = new JTextField();
        filterField.setBounds(20, 20, 600, 30);
        mainPanel.add(filterField);

        filterButton = new JButton("Lọc");
        filterButton.setBounds(640, 20, 100, 30);
        mainPanel.add(filterButton);

        // Bảng danh sách
        studentTable = new JTable(mdsvDangKi);
        studentTable.setFillsViewportHeight(true);
        // Bắt sự kiện khi nhấp chuột vào hàng
        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int rowIndex = studentTable.rowAtPoint(evt.getPoint());
                if (rowIndex >= 0) {
                    Student student = mdsvDangKi.getStudentDetails(rowIndex); // Lấy dữ liệu sinh viên
                    if (student != null) {
                        // Tạo mảng dữ liệu từ đối tượng Student
                        String[] studentData = {
                                student.getTen(),
                                student.getGioiTinh(),
                                student.getNamSinh(),
                                student.getMssv(),
                                student.getSđt(),
                                student.getDiaChi(),
                                student.getKhoa(),
                                student.getPhong(),
                                student.getCuXa(),
                                student.getIdCCCD(),
                                student.getDanToc(),
                                student.getDienChinhSach()
                        };

                        // Hiển thị giao diện GDXemChiTiet
                        GDXemChiTiet detailView = new GDXemChiTiet(studentData, GDSVDangKi.this);
                        detailView.setVisible(true);
                        detailView.getButtonQuayLai().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                setVisible(true);
                                detailView.dispose();
                            }
                        });
                        detailView.getButtonXacNhan().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                storage = LayDuLieuSV.getInstances();
                                storage.addStudent(student);
                                mdsvDangKi.removeStudent(rowIndex);
                                mdsvDangKi.removeStudentTimKiem(rowIndex);
                                detailView.dispose();
                                setVisible(true);
                            }
                        });
                        detailView.getButtonHuy().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                setVisible(true);
                                mdsvDangKi.removeStudentTimKiem(rowIndex);
                                mdsvDangKi.removeStudent(rowIndex);
                                detailView.dispose();
                            }
                        });
                    }
                }
                setVisible(false);
            }
        });
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(20, 60, 760, 300);
        mainPanel.add(scrollPane);

        backButton = new JButton("Quay về") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Vẽ nền nút bo tròn
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                // Vẽ viền bo tròn
                g2.setColor(new Color(200, 40, 50));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

                super.paintComponent(g);
            }

            @Override
            public void paint(Graphics g) {
                super.paint(g);
            }
        };
        backButton.setBounds(20, 380, 100, 30); // Kích thước nút
        backButton.setBackground(new Color(220, 53, 69)); // Màu đỏ đẹp hơn
        backButton.setForeground(Color.WHITE); // Chữ màu trắng
        backButton.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        backButton.setFocusPainted(false); // Loại bỏ viền khi chọn
        backButton.setBorder(BorderFactory.createLineBorder(new Color(200, 40, 50), 2, true)); // Viền bo tròn

        mainPanel.add(backButton);
        setLocationRelativeTo(null);
        // Gắn Renderer và Editor cho cột nút
        studentTable.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
//        studentTable.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JCheckBox(), studentTable));
    }

    public JTable getStudentTable() {
        return studentTable;
    }

    public JTextField getFilterField() {
        return filterField;
    }

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public JMenuItem getManageMenuItem() {
        return manageMenuItem;
    }

    public JMenuItem getRoomManageMenuItem() {
        return roomManageMenuItem;
    }

}
