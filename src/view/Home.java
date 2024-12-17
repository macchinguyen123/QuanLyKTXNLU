package view;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    PanelChooseStudentOrManager mainPanel;
    CardLayout cardLayout;
    JPanel cardPanel;

    public Home() throws HeadlessException {
        setTitle("Dormitory management system");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setResizable(false);

        // Initialize CardLayout and JPanel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add panels to cardPanel
        mainPanel = new PanelChooseStudentOrManager(cardPanel, cardLayout);
        SVLoginOrSignIn studentPanel = new SVLoginOrSignIn(cardPanel, cardLayout);
        PageLogin pageLogin = new PageLogin(cardPanel, cardLayout);
        PageChooseRoom chooseRoom = new PageChooseRoom(cardPanel, cardLayout);



        cardPanel.add(mainPanel, "choosePanel");
        cardPanel.add(studentPanel, "studentPanel");
        cardPanel.add(new PageLogin(cardPanel, cardLayout),"login");
        cardPanel.add(new PageTTCNcuaSVDaO(cardPanel, cardLayout),"TTCNcuaSVDaO");
        cardPanel.add(new PageChooseRoom(cardPanel, cardLayout),"chooseRoom");
        // Add cardPanel to the frame
        getContentPane().add(cardPanel, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        Home frame = new Home();
    }
}
