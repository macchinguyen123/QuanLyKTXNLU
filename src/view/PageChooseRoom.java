package view;

import javax.swing.*;
import java.awt.*;

public class PageChooseRoom extends JPanel {
    Image imgBackround;
    JComboBox gender, typeOfRoom, chooseDorm;
    String[] optionsGender, optionsTypeOfRoom, optionsChooseDorm;
    public PageChooseRoom() {


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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgBackround, 0, 0, getWidth(), getHeight(), this);
    }
}
