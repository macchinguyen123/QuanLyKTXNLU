package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SVLoginOrSignIn extends JPanel {
    Image imgBackround;
    JButton btnLogin, btnSignIn, backToHome;

    public SVLoginOrSignIn(JPanel cardPanel, CardLayout cardLayout) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // set background
        imgBackround = new ImageIcon("src/img/backroundKTX.jpg").getImage();

        // panel chứa nút Home
        JPanel panelTopLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTopLeft.setOpaque(false);

//        // button back to home
//        backToHome = new JButton(new ImageIcon("src/img/iconHome.png"));
//        backToHome.setBackground(new Color(173, 216, 230));
//        backToHome.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cardLayout.show(cardPanel, "chooseStudentOrManager");
//            }
//        });
//        panelTopLeft.add(backToHome);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(panelTopLeft, gbc);

        // panel chứa các nút Đăng nhập và Đăng ký
        JPanel panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setOpaque(false);
        GridBagConstraints gbcCenter = new GridBagConstraints();
        gbcCenter.insets = new Insets(10, 10, 10, 10);
        gbcCenter.gridx = 0;
        gbcCenter.gridy = 0;

        // button dang nhap
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setPreferredSize(new Dimension(150, 50)); // Thiết lập kích thước ưa thích
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 20));
        btnLogin.setBackground(new Color(173, 216, 230));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the page login
                cardLayout.show(cardPanel, "login");
            }
        });
        panelCenter.add(btnLogin, gbcCenter);

        // button dang ky
        btnSignIn = new JButton("Đăng kí");
        btnSignIn.setFont(new Font("Arial", Font.PLAIN, 20));
        btnSignIn.setPreferredSize(new Dimension(150, 50)); // Thiết lập kích thước ưa thích
        btnSignIn.setBackground(new Color(173, 216, 230));
        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "chooseRoom");
            }
        });
        gbcCenter.gridx = 1; // đặt nút Đăng ký ở ngay cạnh nút Đăng nhập
        panelCenter.add(btnSignIn, gbcCenter);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2; // sử dụng 2 cột để căn giữa
        this.add(panelCenter, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgBackround, 0, 0, getWidth(), getHeight(), this);
    }
}
