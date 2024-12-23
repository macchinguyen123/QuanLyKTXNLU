package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageDangKiTaiKhoan extends JPanel {
    JPanel mainPanel;
    JLabel mssv, passwordLabel;
    JTextField mssvTextField;
    JPasswordField passWordField;
    JButton registerButton;
    Image background;
    List<Map<String, String>> listSaveTaiKhoan;


    public PageDangKiTaiKhoan(JPanel cardPanel, CardLayout cardLayout, List<Map<String, String>> listSaveTaiKhoan) {
        this.listSaveTaiKhoan = listSaveTaiKhoan;

        mainPanel = new JPanel(new GridLayout(5, 2));
        JLabel labelDangKy = new JLabel("Đăng ký tài khoản");
        JLabel pp = new JLabel("");
        mainPanel.add(labelDangKy);
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

    public List<Map<String, String>> getListSaveTaiKhoan() {
        return listSaveTaiKhoan;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
