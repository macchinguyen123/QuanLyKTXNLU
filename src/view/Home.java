package view;

import sinhVienDangKy.MDSVDangKi;

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
        PageTTCNcuaSVDaO pageTTCNcuaSVDaO = new PageTTCNcuaSVDaO(cardPanel, cardLayout);
        PageChooseRoom chooseRoom = new PageChooseRoom(cardPanel, cardLayout);
        ThongTinChonPhong thongTinChonPhong = new ThongTinChonPhong(cardPanel, cardLayout);
        MDSVDangKi mdsvDangKi = new MDSVDangKi();
        PageFillInformatinDK fillInformatinDK = new PageFillInformatinDK(cardPanel,cardLayout,pageTTCNcuaSVDaO,mdsvDangKi);
        PanelChooseStudentOrManager chooseStudentOrManager = new PanelChooseStudentOrManager(cardPanel, cardLayout);

        cardPanel.add(mainPanel, "choosePanel");
        cardPanel.add(studentPanel, "studentPanel");
        cardPanel.add(new PageLogin(cardPanel, cardLayout),"login");
        cardPanel.add(pageTTCNcuaSVDaO,"TTCNcuaSVDaO");
        cardPanel.add(new PageChooseRoom(cardPanel, cardLayout),"chooseRoom");
        cardPanel.add(new ThongTinChonPhong(cardPanel,cardLayout),"thongTinChonPhong");
        cardPanel.add(fillInformatinDK,"fillInformatinDK");
        cardPanel.add(chooseStudentOrManager,"chooseStudentOrManager");
        // Add cardPanel to the frame
        getContentPane().add(cardPanel, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        Home frame = new Home();
    }
}
