package view;

import javax.swing.*;
import java.awt.*;

public class PageLogin extends JPanel {
    JLabel homeIcon ;
    JTextField textIdSV ;
    JTextField textPassword ,textPasswd;
    JLabel labelIdSV, labelPasswd;
    JPasswordField txtPasswd;
    JButton btnLogin;
    JPanel panelContent;

    public PageLogin() {
        this.setBackground(new Color(200,240,240));
        this.setLayout(null);


        // icon home
        homeIcon = new JLabel(new ImageIcon("src/img/iconHome.png"));
        homeIcon.setBounds(10,10,30,30);
        this.add(homeIcon);


        // nhan va text field cho password
        labelIdSV= new JLabel("Mã số sinh viên");
        labelIdSV.setBounds(50,60,120   ,25);
        this.add(labelIdSV);

        textIdSV = new JTextField();
        textIdSV.setBounds(200, 60, 150, 30);
        this.add(textIdSV);

        // nhan va text field cho pass word
        labelPasswd = new JLabel("Pass word");
        labelPasswd.setBounds(50,100,120   ,25);
        this.add(labelPasswd);

//
        txtPasswd = new JPasswordField();
        txtPasswd.setBounds(200, 100, 150, 30);
        this.add(txtPasswd);


        // Nut dang nhap
        btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 180, 100, 40);
        btnLogin.setBackground(new Color(173, 216, 230));
        this.add(btnLogin);




    }
}
