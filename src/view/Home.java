package view;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    MyPanelHome mainPanel;
    CardLayout cards;

    public Home() throws HeadlessException {
        setTitle("Dormitory management system");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,500);
        this.setResizable(false);
        mainPanel = new MyPanelHome();
        getContentPane().add(mainPanel,BorderLayout.CENTER);

//




        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        Home frame = new Home();
    }
}
