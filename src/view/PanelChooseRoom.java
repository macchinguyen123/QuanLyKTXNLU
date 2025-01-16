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
import java.util.ArrayList;
import java.util.List;

public class PanelChooseRoom extends JPanel {
    Image imgBackround;
    JComboBox<String> gender, typeOfRoom, chooseDorm;
    String[] optionsGender, optionsTypeOfRoom;
    String[] dormitoryNam = {"A", "C", "F"};
    String[] dormitoryNu = {"B", "D", "E"};
    HomeLass parentFrame;

    public PanelChooseRoom(JPanel cardPanel, CardLayout cardLayout, HomeLass homeLass) {
        this.parentFrame = homeLass;
        this.setLayout(null);

        imgBackround = new ImageIcon("src/img/backroundKTX.jpg").getImage();

        // Các tùy chọn ComboBox
        optionsGender = new String[]{"Nam", "Nữ"};
        optionsTypeOfRoom = new String[]{"6 người", "8 người"};

        gender = new JComboBox<>(optionsGender);
        gender.setSelectedIndex(0);
        gender.setBounds(50, 40, 150, 30);
        this.add(gender);

        typeOfRoom = new JComboBox<>(optionsTypeOfRoom);
        typeOfRoom.setSelectedIndex(0);
        typeOfRoom.setBounds(250, 40, 150, 30);
        this.add(typeOfRoom);

        chooseDorm = new JComboBox<>(dormitoryNam);
        chooseDorm.setSelectedIndex(0);
        chooseDorm.setBounds(500, 40, 150, 30);
        this.add(chooseDorm);
        gender.addActionListener(e -> updateDormitoryOptions());

        // Nút "Tìm kiếm"
        JButton btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(new Color(173, 216, 230));
        btnTimKiem.setBounds(300, 100, 150, 30);

        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedAttributes = getSelectedAttributes();
                if (selectedAttributes.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tất cả các tùy chọn!");
                    return;
                }

                ThongTinChonPhong thongTinChonPhong = new ThongTinChonPhong(cardPanel, cardLayout, selectedAttributes);
                cardPanel.add(thongTinChonPhong, "thongTinChonPhong");
                cardLayout.show(cardPanel, "thongTinChonPhong");
            }
        });

        this.add(btnTimKiem);

        // Nút "Quay lại"
        JButton btnBack = new JButton(new ImageIcon("src/img/arrow-back-icon.png"));
        btnBack.setBounds(20, 10, 25, 25);
        this.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parentFrame != null) {
                    parentFrame.dispose(); // Đóng JFrame Home
                }
                Model passwordModel = new Model();
                PasswordView passwordView = new PasswordView();
                Controller controller = new Controller(passwordModel,passwordView);
                MRegister mRegister = new MRegister();
                VRegister vRegister = new VRegister(mRegister);
                CRegister cRegister = new CRegister(mRegister, vRegister);
                vRegister.setVisible(true);
                setVisible(false);
            }
        });
    }

    private void updateDormitoryOptions() {
        chooseDorm.removeAllItems();
        if ("Nam".equals(gender.getSelectedItem().toString())) {
            for (String dorm : dormitoryNam) {
                chooseDorm.addItem(dorm);
            }
        } else if ("Nữ".equals(gender.getSelectedItem().toString())) {
            for (String dorm : dormitoryNu) {
                chooseDorm.addItem(dorm);
            }
        }
    }

    public List<String> getSelectedAttributes() {
        List<String> selected = new ArrayList<>();
        if (gender != null && gender.getSelectedItem() != null) {
            selected.add(gender.getSelectedItem().toString());
        }
        if (typeOfRoom != null && typeOfRoom.getSelectedItem() != null) {
            selected.add(typeOfRoom.getSelectedItem().toString());
        }
        if (chooseDorm != null && chooseDorm.getSelectedItem() != null) {
            selected.add(chooseDorm.getSelectedItem().toString());
        }
        return selected;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgBackround, 0, 0, getWidth(), getHeight(), this);
    }


}