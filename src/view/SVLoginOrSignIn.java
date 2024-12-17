package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SVLoginOrSignIn extends JPanel {
    Image imgBackround;
    JButton btnLogin, btnSignIn;
    JLabel lblLogin, lblSignIn, labelBackround;
    JPanel panelLogin, panelSignIn, panelBackround;

    public SVLoginOrSignIn(JPanel cardPanel,CardLayout cardLayout) {

        //set backround
      imgBackround = new ImageIcon("src/img/backroundKTX.jpg").getImage();

      // button dang nhap
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setBounds(200,600,100,200);
        btnLogin.setBackground(new Color(173, 216, 230));
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the page login
                cardLayout.show(cardPanel, "login");
            }
        });
        this.add(btnLogin);

        // button dang ky
        btnSignIn = new JButton("Đăng kí");
        btnSignIn.setBounds(400,600,100,200);
        btnSignIn.setBackground(new Color(173, 216, 230));
        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"chooseRoom");
            }
        });


        this.add(btnSignIn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgBackround, 0, 0, getWidth(),getHeight(),this);
    }
}





