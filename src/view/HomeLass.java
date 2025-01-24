package view;

import model1.RegisterStudent;
import quanLyPhong.Model;

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
    RegisterStudent mdsvDangKi = new RegisterStudent();
    String currentMSSV; // thêm biến currentMSSV
    PanelChooseRoom chooseRoom;
    PanelHeaderOfHome header;
    PanelInformationChooseRoom thongTinChonPhong;
    PanelFillInformatinDK fillInformatinDK;
    //    PanelChooseStudentOrManager chooseStudentOrManager;
    PanelRegisterAccount dangKiTaiKhoanSV;
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

        thongTinChonPhong = new PanelInformationChooseRoom(cardPanel, cardLayout, new ArrayList<>(chooseRoom.getSelectedAttributes()));
        fillInformatinDK = new PanelFillInformatinDK(cardPanel, cardLayout, mdsvDangKi, listSaveTaiKhoan, currentMSSV, chooseRoom,this);
        dangKiTaiKhoanSV = new PanelRegisterAccount(cardPanel, cardLayout, listSaveTaiKhoan);

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
