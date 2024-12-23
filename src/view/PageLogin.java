package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class PageLogin extends JPanel {
    JLabel homeIcon;
    JTextField textIdSV;
    JLabel labelIdSV, labelPasswd;
    JPasswordField txtPasswd;
    JButton btnLogin;
    List<Map<String, String>> listSaveTaiKhoan;
    CardLayout cardLayout;
    JPanel cardPanel;
    String currentMSSV; // thêm biến currentMSSV

    public PageLogin(JPanel cardPanel, CardLayout cardLayout, List<Map<String, String>> listSaveTaiKhoan) {
        this.listSaveTaiKhoan = listSaveTaiKhoan;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.setBackground(new Color(200, 240, 240));
        this.setLayout(null);

        // icon home
        JButton btnHome = new JButton(new ImageIcon("src/img/arrow-back-icon.png"));
        btnHome.setBounds(10, 10, 30, 30);
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "studentPanel");
            }
        });
        this.add(btnHome);

        // nhãn và text field cho MSSV
        labelIdSV = new JLabel("Mã số sinh viên");
        labelIdSV.setBounds(50, 60, 120, 25);
        this.add(labelIdSV);

        textIdSV = new JTextField();
        textIdSV.setBounds(200, 60, 150, 30);
        this.add(textIdSV);

        // nhãn và text field cho mật khẩu
        labelPasswd = new JLabel("Pass word");
        labelPasswd.setBounds(50, 100, 120, 25);
        this.add(labelPasswd);

        txtPasswd = new JPasswordField();
        txtPasswd.setBounds(200, 100, 150, 30);
        this.add(txtPasswd);

        // Nút đăng nhập
        btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 180, 100, 40);
        btnLogin.setBackground(new Color(173, 216, 230));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mssv = textIdSV.getText();
                String password = new String(txtPasswd.getPassword());
                boolean found = false;

                // Kiểm tra thông tin tài khoản
                for (Map<String, String> account : listSaveTaiKhoan) {
                    if (account.get("Mã số sinh viên").equals(mssv) && account.get("Mật khẩu").equals(password)) {
                        found = true;
                        currentMSSV = mssv; // Lưu mã số sinh viên hiện tại
                        break;
                    }
                }

                if (found) {
                    cardLayout.show(cardPanel, "TTCNcuaSVDaO");
                    textIdSV.setText("");
                    txtPasswd.setText("");
                } else {
                    JOptionPane.showMessageDialog(PageLogin.this, "MSSV hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.add(btnLogin);
    }

    public String getCurrentMSSV() {
        return currentMSSV;
    }
}
