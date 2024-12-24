package view;

import javax.swing.*;
import java.awt.*;

public class MyPanelHome extends JPanel {
    PanelHeaderOfHome header;
    PanelChooseStudentOrManager panelChoose;


    public MyPanelHome(JPanel cardpanel, CardLayout cardLayout) {
        setLayout(new BorderLayout());
//

        header = new PanelHeaderOfHome(cardpanel, cardLayout);
//        panelChoose = new PanelChooseStudentOrManager();
        this.add(header, BorderLayout.NORTH);
        this.add(panelChoose, BorderLayout.CENTER);

    }
}
