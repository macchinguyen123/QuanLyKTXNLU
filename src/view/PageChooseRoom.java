package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageChooseRoom extends JPanel {
    Image imgBackround;
    JComboBox gender, typeOfRoom, chooseDorm;
    String[] optionsGender, optionsTypeOfRoom, optionsChooseDorm;
    public PageChooseRoom(JPanel cardPanel,CardLayout cardLayout) {


        this.setLayout(null);

        // create backround
//        lbBackRound = new JLabel(new ImageIcon("src/img/backroundKTX.jpg"));
//        lbBackRound.setBounds(0, 0, this.getWidth(), this.getHeight());
//        this.setBackground(new Color(200,240,240));

        imgBackround = new ImageIcon("src/img/backroundKTX.jpg").getImage();

//

        // create comboBox gender
        optionsGender = new String[]{"Nam", "Nữ"};
        optionsTypeOfRoom = new String[]{"6 người ", "8 người"};
        optionsChooseDorm = new String[]{"A", "B","C","D","E","F"};


        gender = new JComboBox(optionsGender);
        gender.setBounds(50,40,150,30);
        this.add(gender);


        typeOfRoom = new JComboBox(optionsTypeOfRoom);
        typeOfRoom.setBounds(250,40,150,30);
        this.add(typeOfRoom);

        chooseDorm = new JComboBox(optionsChooseDorm);
        chooseDorm.setBounds(500,40,150,30);
        this.add(chooseDorm);

        JButton btnTimKiem = new JButton("tìm kiếm");
        btnTimKiem.setBackground(new Color(173, 216, 230));
        btnTimKiem.setBounds(300,100,150,30);
        btnTimKiem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"thongTinChonPhong");
            }
        });

        this.add(btnTimKiem);



        // btnBack
        JButton btnBack = new JButton(new ImageIcon("src/img/arrow-back-icon.png"));
        btnBack.setBounds(20,10,25,25);
        this.add(btnBack);
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"studentPanel");
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgBackround, 0, 0, getWidth(), getHeight(), this);
    }
}
