package view;

import javax.swing.*;
import java.awt.*;

public class MyMainPanel extends JPanel {
    MyPanelHome panelHome ;
    JLabel labelStudent , labelManager;
    CardLayout cardLayout;
    PageLogin pageLogin;
    SVLoginOrSignIn svLoginOrSignIn;
    PageChooseRoom pageChooseRoom;
    PageTTCNcuaSVDaO svTTCNcuaSVDaO;
//
    public MyMainPanel() {

        panelHome = new MyPanelHome();
        this.add(panelHome, BorderLayout.CENTER);// hien thi ban dau






    }
}
