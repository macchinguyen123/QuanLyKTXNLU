package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

public class PageLogin extends JPanel {

     JTextField textIdSV;
     JPasswordField txtPasswd;
     Set<Map<String, String>> listSaveTaiKhoan;
     CardLayout cardLayout;
     JPanel cardPanel;
     String currentMSSV;

    public PageLogin(JPanel cardPanel, CardLayout cardLayout, Set<Map<String, String>> listSaveTaiKhoan) {
        this.listSaveTaiKhoan = listSaveTaiKhoan;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.setBackground(new Color(200, 240, 240));

        // Sử dụng GridBagLayout để căn giữa
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Đặt khoảng cách giữa các component

        // Panel chứa các component đăng nhập để dễ dàng căn giữa
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(200, 240, 240)); // Để nền giống JPanel cha

        GridBagConstraints gbcLogin = new GridBagConstraints();
        gbcLogin.insets = new Insets(5, 5, 5, 5);
        gbcLogin.fill = GridBagConstraints.HORIZONTAL; // Để các component kéo dãn theo chiều ngang

        // Nút Home (đặt ở góc trên bên trái)
        JButton btnHome = new JButton(new ImageIcon("src/img/arrow-back-icon.png"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Neo nút về phía tây bắc
        add(btnHome, gbc);
        btnHome.addActionListener(e -> cardLayout.show(cardPanel, "studentPanel"));

        // Các label và text field
        JLabel labelIdSV = new JLabel("Mã số sinh viên");
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 0;
        loginPanel.add(labelIdSV, gbcLogin);

        textIdSV = new JTextField(15); // Đặt kích thước cột cho text field
        gbcLogin.gridx = 1;
        gbcLogin.gridy = 0;
        gbcLogin.weightx = 1; // Để text field chiếm hết chỗ trống
        loginPanel.add(textIdSV, gbcLogin);

        JLabel labelPasswd = new JLabel("Mật khẩu");
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 1;
        loginPanel.add(labelPasswd, gbcLogin);

        txtPasswd = new JPasswordField(15);
        gbcLogin.gridx = 1;
        gbcLogin.gridy = 1;
        gbcLogin.weightx = 1;
        loginPanel.add(txtPasswd, gbcLogin);

        // Nút đăng nhập
        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.setBackground(new Color(173, 216, 230));
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 2;
        gbcLogin.gridwidth = 2; // Chiếm 2 cột
        gbcLogin.anchor = GridBagConstraints.CENTER; // Căn giữa nút
        loginPanel.add(btnLogin, gbcLogin);

        btnLogin.addActionListener(e -> {
            String mssv = textIdSV.getText();
            String password = new String(txtPasswd.getPassword());
            boolean found = false;

            for (Map<String, String> account : listSaveTaiKhoan) {
                if (account.get("Mã số sinh viên").equals(mssv) && account.get("Mật khẩu").equals(password)) {
                    found = true;
                    currentMSSV = mssv;
                    break;
                }
            }

            if (found) {
                cardLayout.show(cardPanel, "TTCNcuaSVDaO");
                textIdSV.setText("");
                txtPasswd.setText("");
            } else {
                JOptionPane.showMessageDialog(PageLogin.this, "MSSV hoặc mật khẩu sai!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Đặt loginPanel vào giữa JPanel chính
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1; // Để panel chiếm hết không gian còn lại
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa panel
        add(loginPanel, gbc);

    }

    public String getCurrentMSSV() {
        return currentMSSV;
    }
}