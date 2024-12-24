package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelHeaderOfHome extends JPanel{
    JButton btnLogo ;
    JLabel titleLable;

    public PanelHeaderOfHome(JPanel panelCard, CardLayout cardLayout) {
        this.setBackground(new Color(59,185,94));
        this.setBounds(10,10,80,80);
        this.setLayout(new BorderLayout());

        //  add logo//
        btnLogo = new JButton(new ImageIcon("src/img/Logo_HCMUAF.svg.png"));
        btnLogo.setBackground(new Color(59,185,94));
        btnLogo.setBounds(10,10,80,80);
        btnLogo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelCard,"chooseStudentOrManager");
            }
        });
        this.add(btnLogo,BorderLayout.WEST);

        //add title
        titleLable = new JLabel("Hệ Thống Quản Lý KTX NLU");
        titleLable.setBounds(100,20,600,60);
        titleLable.setFont(new Font("Arial",Font.BOLD,24));
        titleLable.setForeground(Color.WHITE);
        titleLable.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLable,BorderLayout.CENTER);
    }
}
