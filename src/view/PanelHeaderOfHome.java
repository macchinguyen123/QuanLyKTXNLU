package view;

import javax.swing.*;
import java.awt.*;

public class PanelHeaderOfHome extends JPanel{
    JLabel labelLogo ;
    JLabel titleLable;

    public PanelHeaderOfHome() {
        this.setBackground(new Color(59,185,94));
        this.setBounds(10,10,80,80);
        this.setLayout(new BorderLayout());

        //  add logo//
//        btnLogo = new JButton(new ImageIcon("src/img/Logo_HCMUAF.svg.png"));
//        btnLogo.setBackground(new Color(59,185,94));
        ImageIcon logo = new ImageIcon("src/img/Logo_HCMUAF.svg.png");
        labelLogo =  new JLabel(logo);
        labelLogo.setBounds(10,10,80,80);
        this.add(labelLogo,BorderLayout.WEST);

        //add title
        titleLable = new JLabel("Hệ Thống Quản Lý KTX NLU");
        titleLable.setBounds(100,20,600,60);
        titleLable.setFont(new Font("Arial",Font.BOLD,24));
        titleLable.setForeground(Color.WHITE);
        titleLable.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLable,BorderLayout.CENTER);
    }
}
