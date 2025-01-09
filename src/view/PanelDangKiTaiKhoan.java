package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PanelDangKiTaiKhoan extends JPanel {
    JPanel mainPanel;
    JLabel mssv, passwordLabel,labelDangKy,pp;
    JTextField mssvTextField;
    JPasswordField passWordField;
    JButton registerButton;
    Image background;
    Set<Map<String, String>> listSaveTaiKhoan;


    public PanelDangKiTaiKhoan(JPanel cardPanel, CardLayout cardLayout, Set<Map<String, String>> listSaveTaiKhoan) {
        this.listSaveTaiKhoan = listSaveTaiKhoan;

        mainPanel = new JPanel(new GridLayout(5, 2));
         labelDangKy = new JLabel("Đăng ký tài khoản");
         pp = new JLabel("");// lam trong mot dong
        mainPanel.add(labelDangKy);
        mainPanel.setBackground(new Color(173, 216, 230));
        mainPanel.add(pp);
        mainPanel.setFocusable(false);
        background = new ImageIcon("src/img/backroundKTX.jpg").getImage();

        // tạo các thành phần giao diện
        mssv = new JLabel("Mã số sinh viên");
        mssvTextField = new JTextField();

        passwordLabel = new JLabel("Mật khẩu");
        passWordField = new JPasswordField();

        registerButton = new JButton("Đăng ký");

        // thêm các thành phần vào panel
        mainPanel.add(mssv);
        mainPanel.add(mssvTextField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passWordField);
        mainPanel.add(new JLabel()); // tạo một khoảng trống

        mainPanel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mssv = mssvTextField.getText();
                String password = new String(passWordField.getPassword());
                if (mssv.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Vui lòng điền đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    Map<String, String> taiKhoan = new HashMap<>();
                    taiKhoan.put("Mã số sinh viên", mssv);
                    taiKhoan.put("Mật khẩu", password);

                    // lưu tài khoản vào danh sách
                    listSaveTaiKhoan.add(taiKhoan);

                    // hiển thị đăng ký thành công
                    JOptionPane.showMessageDialog(mainPanel, "Đăng ký thành công!\nMSSV: " + mssv);
                    // sau khi tạo tài khoản, xóa các trường thông tin
                    mssvTextField.setText("");
                    passWordField.setText("");

                    // quay về trang đăng nhập
                    cardLayout.show(cardPanel, "login");
                }
            }
        });

        this.add(mainPanel);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
