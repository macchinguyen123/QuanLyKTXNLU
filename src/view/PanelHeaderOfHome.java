package view;

import quanLyPhong.Model;
import sinhVienDangKy.CRegister;
import sinhVienDangKy.MRegister;
import sinhVienDangKy.VRegister;
import sinhVienDangO.Controller;
import sinhVienDangO.PasswordView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelHeaderOfHome extends JPanel {
    JButton btnLogo;
    JLabel titleLable;
    HomeLass parentFrame;

    public PanelHeaderOfHome(JPanel panelCard, CardLayout cardLayout, HomeLass homeLass) {
        this.parentFrame = homeLass;
        this.setBackground(new Color(59, 185, 94));
        this.setBounds(10, 10, 80, 80);
        this.setLayout(new BorderLayout());

        //  add logo//
        btnLogo = new JButton(new ImageIcon("src/img/Logo_HCMUAF.svg.png"));
        btnLogo.setBackground(new Color(59, 185, 94));
        btnLogo.setBounds(10, 10, 80, 80);
        btnLogo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (parentFrame != null) {
                    parentFrame.dispose(); // Đóng JFrame Home
                }
                Model passwordModel = new Model();
                PasswordView passwordView = new PasswordView();
                Controller controller = new Controller(passwordModel, passwordView);
                MRegister mRegister = new MRegister();
                VRegister vRegister = new VRegister(mRegister);
                CRegister cRegister = new CRegister(mRegister, vRegister);
                vRegister.setVisible(true);
                setVisible(false);
            }
        });
        this.add(btnLogo, BorderLayout.WEST);

        //add title
        titleLable = new JLabel("Hệ Thống Quản Lý KTX NLU");
        titleLable.setBounds(100, 20, 600, 60);
        titleLable.setFont(new Font("Arial", Font.BOLD, 24));
        titleLable.setForeground(Color.WHITE);
        titleLable.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLable, BorderLayout.CENTER);
    }
}
