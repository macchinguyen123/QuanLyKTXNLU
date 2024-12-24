package view;

import sinhVienDangKy.MDSVDangKi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Home extends JFrame {
    private static List<Map<String, String>> listSaveTaiKhoan = new ArrayList<>();
    PanelChooseStudentOrManager mainPanel;
    CardLayout cardLayout;
    JPanel cardPanel;
    List<String> selectedAttributes;
    MDSVDangKi mdsvDangKi = new MDSVDangKi();
    String currentMSSV; // thêm biến currentMSSV

    public Home() throws HeadlessException {
        setTitle("Dormitory management system");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setResizable(false);
        selectedAttributes = new ArrayList<>();
        // Initialize CardLayout and JPanel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add panels to cardPanel
        mainPanel = new PanelChooseStudentOrManager(cardPanel, cardLayout);
        SVLoginOrSignIn studentPanel = new SVLoginOrSignIn(cardPanel, cardLayout);
        PageLogin pageLogin = new PageLogin(cardPanel, cardLayout, listSaveTaiKhoan);
        PageTTCNcuaSVDaO pageTTCNcuaSVDaO = new PageTTCNcuaSVDaO(cardPanel, cardLayout, listSaveTaiKhoan);
        PageChooseRoom chooseRoom = new PageChooseRoom(cardPanel, cardLayout);

        ThongTinChonPhong thongTinChonPhong = new ThongTinChonPhong(cardPanel, cardLayout, new ArrayList<>(chooseRoom.getSelectedAttributes()));
        PageFillInformatinDK fillInformatinDK = new PageFillInformatinDK(cardPanel, cardLayout, pageTTCNcuaSVDaO, mdsvDangKi, listSaveTaiKhoan, currentMSSV);
        PanelChooseStudentOrManager chooseStudentOrManager = new PanelChooseStudentOrManager(cardPanel, cardLayout);
        PageDangKiTaiKhoan dangKiTaiKhoanSV = new PageDangKiTaiKhoan(cardPanel, cardLayout, listSaveTaiKhoan);

        cardPanel.add(mainPanel, "choosePanel");
        cardPanel.add(studentPanel, "studentPanel");
        cardPanel.add(pageLogin, "login");
        cardPanel.add(pageTTCNcuaSVDaO, "TTCNcuaSVDaO");
        cardPanel.add(chooseRoom, "chooseRoom");
        cardPanel.add(thongTinChonPhong, "thongTinChonPhong");
        cardPanel.add(fillInformatinDK, "fillInformatinDK");
        cardPanel.add(chooseStudentOrManager, "chooseStudentOrManager");
        cardPanel.add(dangKiTaiKhoanSV, "dangKiTaiKhoanSV");

        // Add header panel
        PanelHeaderOfHome headerPanel = new PanelHeaderOfHome();
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Add cardPanel to the frame
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public MDSVDangKi getMdsvDangKi() {
        return mdsvDangKi;
    }

    public static void main(String[] args) {
        Home frame = new Home();
    }
}
