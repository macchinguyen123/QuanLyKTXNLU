package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelChooseRoom extends JPanel {
    Image imgBackround;
    JComboBox<String> gender, typeOfRoom, chooseDorm;
    String[] optionsGender, optionsTypeOfRoom, optionsChooseDorm;

    public PanelChooseRoom(JPanel cardPanel, CardLayout cardLayout) {
        this.setLayout(null);

        imgBackround = new ImageIcon("src/img/backroundKTX.jpg").getImage();

        // Các tùy chọn ComboBox
        optionsGender = new String[]{"Nam", "Nữ"};
        optionsTypeOfRoom = new String[]{"6 người", "8 người"};
        optionsChooseDorm = new String[]{"A", "B", "C", "D", "E", "F"};

        gender = new JComboBox<>(optionsGender);
        gender.setSelectedIndex(0);
        gender.setBounds(50, 40, 150, 30);
        this.add(gender);

        typeOfRoom = new JComboBox<>(optionsTypeOfRoom);
        typeOfRoom.setSelectedIndex(0);
        typeOfRoom.setBounds(250, 40, 150, 30);
        this.add(typeOfRoom);

        chooseDorm = new JComboBox<>(optionsChooseDorm);
        chooseDorm.setSelectedIndex(0);
        chooseDorm.setBounds(500, 40, 150, 30);
        this.add(chooseDorm);

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
        btnBack.addActionListener(e -> cardLayout.show(cardPanel, "studentPanel"));
    }

    public List<String> getSelectedAttributes() {
        List<String> selected = new ArrayList<>();
        if (gender != null && gender.getSelectedItem() != null) {
            selected.add(gender.getSelectedItem().toString());
        }
        if (typeOfRoom != null && typeOfRoom.getSelectedItem() != null) {
            selected.add(typeOfRoom.getSelectedItem().toString());
        }
        if (chooseDorm != null && chooseDorm.getSelectedItem() != null) {selected.add(chooseDorm.getSelectedItem().toString());
        }
        return selected;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgBackround, 0, 0, getWidth(), getHeight(), this);
    }




}