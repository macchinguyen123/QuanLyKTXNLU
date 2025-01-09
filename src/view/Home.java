package view;

import quanLyPhong.Model;
import sinhVienDangKy.MRegister;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Home extends JFrame {
    private static Set<Map<String, String>> listSaveTaiKhoan = new HashSet<>();
    PanelChooseStudentOrManager mainPanel;
    CardLayout cardLayout;
    JPanel cardPanel;
    List<String> selectedAttributes;
    MRegister mdsvDangKi = new MRegister();
    String currentMSSV; // thêm biến currentMSSV
    SVLoginOrSignIn studentPanel;
    PanelLogin pageLogin;
    PanelTTCNcuaSVDaO pageTTCNcuaSVDaO;
    PanelChooseRoom chooseRoom;
    PanelHeaderOfHome header;
    ThongTinChonPhong thongTinChonPhong;
    PanelFillInformatinDK fillInformatinDK;
    PanelChooseStudentOrManager chooseStudentOrManager;
    PanelDangKiTaiKhoan dangKiTaiKhoanSV;
    PanelHeaderOfHome headerPanel;
    Model model;


    public Home() throws HeadlessException {
        setTitle("Dormitory management system");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);
//        this.setResizable(false);
        selectedAttributes = new ArrayList<>();
        // Initialize CardLayout and JPanel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add panels to cardPanel
        mainPanel = new PanelChooseStudentOrManager(cardPanel, cardLayout,this);
        studentPanel = new SVLoginOrSignIn(cardPanel, cardLayout);
        pageLogin = new PanelLogin(cardPanel, cardLayout, listSaveTaiKhoan);
        pageTTCNcuaSVDaO = new PanelTTCNcuaSVDaO(cardPanel, cardLayout, listSaveTaiKhoan);
        chooseRoom = new PanelChooseRoom(cardPanel, cardLayout);
        header = new PanelHeaderOfHome(cardPanel, cardLayout);

        thongTinChonPhong = new ThongTinChonPhong(cardPanel, cardLayout, new ArrayList<>(chooseRoom.getSelectedAttributes()));
        fillInformatinDK = new PanelFillInformatinDK(cardPanel, cardLayout, pageTTCNcuaSVDaO, mdsvDangKi, listSaveTaiKhoan, currentMSSV, chooseRoom);
        chooseStudentOrManager = new PanelChooseStudentOrManager(cardPanel, cardLayout,this);
        dangKiTaiKhoanSV = new PanelDangKiTaiKhoan(cardPanel, cardLayout, listSaveTaiKhoan);


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
        headerPanel = new PanelHeaderOfHome(cardPanel, cardLayout);
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Add cardPanel to the frame
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public MRegister getMdsvDangKi() {
        return mdsvDangKi;
    }

    public static void main(String[] args) {
        Home frame = new Home();
    }
}