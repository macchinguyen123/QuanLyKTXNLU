package view;

import quanLyPhong.Model;
import sinhVienDangKy.MRegister;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class HomeLass extends  JFrame {
    private static Set<Map<String, String>> listSaveTaiKhoan = new HashSet<>();
//    PanelChooseStudentOrManager mainPanel;
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
//    PanelChooseStudentOrManager chooseStudentOrManager;
    PanelDangKiTaiKhoan dangKiTaiKhoanSV;
    PanelHeaderOfHome headerPanel;
    Model model;


    public HomeLass() throws HeadlessException {
        setTitle("Dormitory management system");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);

        selectedAttributes = new ArrayList<>();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

//        studentPanel = new SVLoginOrSignIn(cardPanel, cardLayout);
//        pageLogin = new PanelLogin(cardPanel, cardLayout, listSaveTaiKhoan);
//        pageTTCNcuaSVDaO = new PanelTTCNcuaSVDaO(cardPanel, cardLayout, listSaveTaiKhoan);
        chooseRoom = new PanelChooseRoom(cardPanel, cardLayout,this);
        header = new PanelHeaderOfHome(cardPanel, cardLayout,this);

        thongTinChonPhong = new ThongTinChonPhong(cardPanel, cardLayout, new ArrayList<>(chooseRoom.getSelectedAttributes()));
        fillInformatinDK = new PanelFillInformatinDK(cardPanel, cardLayout, pageTTCNcuaSVDaO, mdsvDangKi, listSaveTaiKhoan, currentMSSV, chooseRoom,this);
        dangKiTaiKhoanSV = new PanelDangKiTaiKhoan(cardPanel, cardLayout, listSaveTaiKhoan);

//        cardPanel.add(studentPanel, "studentPanel");
//        cardPanel.add(pageLogin, "login");
//        cardPanel.add(pageTTCNcuaSVDaO, "TTCNcuaSVDaO");
        cardPanel.add(chooseRoom, "chooseRoom");
        cardPanel.add(thongTinChonPhong, "thongTinChonPhong");
        cardPanel.add(fillInformatinDK, "fillInformatinDK");
//        cardPanel.add(dangKiTaiKhoanSV, "dangKiTaiKhoanSV");

        headerPanel = new PanelHeaderOfHome(cardPanel, cardLayout,this);
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(cardPanel, BorderLayout.CENTER);

        // Đặt trang mặc định là "chooseRoom"
        cardLayout.show(cardPanel, "chooseRoom");

        this.setLocationRelativeTo(null);
        setVisible(true);
    }

}
